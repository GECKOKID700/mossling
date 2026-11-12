package com.axton.mossling

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : Activity() {

    private lateinit var web: WebView
    private var askedThisSession = false

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        web = WebView(this).apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true   // keeps your progress between launches
            webViewClient = WebViewClient()
            addJavascriptInterface(Bridge(this@MainActivity), "Android")
        }
        setContentView(web)
        if (savedInstanceState == null) web.loadUrl("file:///android_asset/mossling.html")
        else web.restoreState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        web.saveState(outState)
    }

    override fun onResume() {
        super.onResume()
        if (!isBlockerOn(this) && !askedThisSession) {
            askedThisSession = true
            AlertDialog.Builder(this)
                .setTitle("Turn on the social media lock")
                .setMessage(
                    "To keep social apps closed until your goals are done, turn on " +
                    "\"Mossling social media lock\" in Accessibility settings.\n\n" +
                    "If it's greyed out: open App info for Mossling, tap ⋮, then " +
                    "\"Allow restricted settings\", and try again."
                )
                .setPositiveButton("Open settings") { _, _ -> openBlockerSettings(this) }
                .setNegativeButton("Later", null)
                .show()
        }
    }

    companion object {
        fun isBlockerOn(context: Context): Boolean {
            val enabled = Settings.Secure.getString(
                context.contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
            ) ?: return false
            val me = ComponentName(context, BlockerService::class.java).flattenToString()
            return enabled.split(':').any { it.equals(me, ignoreCase = true) }
        }

        fun openBlockerSettings(context: Context) {
            context.startActivity(
                Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }
    }

    /** Functions the Mossling page can call as window.Android.* */
    class Bridge(private val context: Context) {
        @JavascriptInterface
        fun setGoalsDone(allDone: Boolean, goalsLeft: Int) = GoalState.save(context, allDone, goalsLeft)

        @JavascriptInterface
        fun isBlockerOn(): Boolean = MainActivity.isBlockerOn(context)

        @JavascriptInterface
        fun openBlockerSettings() = MainActivity.openBlockerSettings(context)

        @JavascriptInterface
        fun openUpdates() {
            context.startActivity(
                Intent(Intent.ACTION_VIEW, android.net.Uri.parse("https://github.com/GECKOKID700/mossling/releases/latest"))
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }

        @JavascriptInterface
        fun version(): String =
            context.packageManager.getPackageInfo(context.packageName, 0).versionName ?: "?"
    }
}
