package com.axton.mossling

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

/** The screen you see when you try to open a locked app. */
class BlockActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dp = resources.displayMetrics.density
        val left = GoalState.goalsLeft(this)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setBackgroundColor(Color.parseColor("#E8F1EC"))
            setPadding((32 * dp).toInt(), 0, (32 * dp).toInt(), 0)
        }

        root.addView(TextView(this).apply {
            text = "🔒"
            textSize = 64f
            gravity = Gravity.CENTER
        })
        root.addView(TextView(this).apply {
            text = "Goals first, Sir"
            textSize = 28f
            setTextColor(Color.parseColor("#23302B"))
            gravity = Gravity.CENTER
            setPadding(0, (12 * dp).toInt(), 0, (8 * dp).toInt())
        })
        root.addView(TextView(this).apply {
            text = when {
                left > 0 -> "You have $left goal${if (left == 1) "" else "s"} left today. Finish them and this app unlocks."
                else -> "Open Mossling and finish today's goals to unlock this app."
            }
            textSize = 17f
            setTextColor(Color.parseColor("#5F7068"))
            gravity = Gravity.CENTER
        })
        root.addView(Button(this).apply {
            text = "Open Mossling"
            textSize = 17f
            isAllCaps = false
            setTextColor(Color.WHITE)
            background = GradientDrawable().apply {
                setColor(Color.parseColor("#3E7A4F"))
                cornerRadius = 16 * dp
            }
            setPadding((24 * dp).toInt(), (14 * dp).toInt(), (24 * dp).toInt(), (14 * dp).toInt())
            setOnClickListener {
                startActivity(
                    Intent(this@BlockActivity, MainActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                )
                finish()
            }
        }, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply { topMargin = (28 * dp).toInt() })

        setContentView(root)
    }

    @Deprecated("Back goes home instead of into the locked app")
    override fun onBackPressed() {
        startActivity(Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        finish()
    }
}
