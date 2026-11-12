package com.axton.mossling

import android.content.Context
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/** Today's goal status, written by the Mossling screen and read by the blocker. */
object GoalState {
    private const val PREFS = "mossling"

    private fun today(): String = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())

    fun save(context: Context, allDone: Boolean, goalsLeft: Int) {
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit()
            .putString("day", today())
            .putBoolean("unlocked", allDone)
            .putInt("left", goalsLeft)
            .apply()
    }

    /** Unlocked only if every goal was finished today. A new day locks again. */
    fun isUnlocked(context: Context): Boolean {
        val p = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        return p.getString("day", null) == today() && p.getBoolean("unlocked", false)
    }

    fun goalsLeft(context: Context): Int {
        val p = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        return if (p.getString("day", null) == today()) p.getInt("left", -1) else -1
    }
}
