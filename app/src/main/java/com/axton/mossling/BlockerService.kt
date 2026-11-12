package com.axton.mossling

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.accessibility.AccessibilityEvent

/** Watches which app comes to the front and sends you back to Mossling if it's a locked one. */
class BlockerService : AccessibilityService() {

    private var lastBlockAt = 0L

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) return
        val pkg = event.packageName?.toString() ?: return
        if (pkg !in Blocklist.PACKAGES) return
        if (GoalState.isUnlocked(this)) return

        // Avoid firing many times for the same launch
        val now = System.currentTimeMillis()
        if (now - lastBlockAt < 800) return
        lastBlockAt = now

        performGlobalAction(GLOBAL_ACTION_HOME)
        startActivity(
            Intent(this, BlockActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }

    override fun onInterrupt() {}
}
