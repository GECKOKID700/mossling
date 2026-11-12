package com.axton.mossling

/**
 * Apps that stay locked until your goals are done.
 * To block another app, add its package name (find it on its Play Store link: ...?id=PACKAGE).
 * To stop blocking one, delete its line.
 */
object Blocklist {
    val PACKAGES = setOf(
        "com.zhiliaoapp.musically",   // TikTok
        "com.ss.android.ugc.trill",   // TikTok (some regions)
        "com.instagram.android",      // Instagram
        "com.snapchat.android",       // Snapchat
        "com.facebook.katana",        // Facebook
        "com.twitter.android",        // X / Twitter
        "com.reddit.frontpage",       // Reddit
        "com.instagram.barcelona",    // Threads
        // "com.google.android.youtube", // YouTube (remove the // to block it)
    )
}
