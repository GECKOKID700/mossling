# Mossling for Android

Your private self-care pet app, with a real social media lock.

- Daily goals, workout rep counters, weekly 1-minute strength tests, water, check-ins, breathing, closet and achievements.
- **Social media lock:** TikTok, Instagram, Snapchat, Facebook, X, Reddit and Threads send you straight back to a "Goals first, Sir" screen until every goal is checked off. It locks again each new day.
- Progress saves on your phone.

## Option A: Build on your laptop (Android Studio)

1. Unzip `Mossling.zip` and open the `Mossling` folder in Android Studio (**File → Open**).
2. Let Gradle sync finish. If it asks to install an SDK platform, say yes.
3. Plug in your phone with USB debugging on (same as for your FTC robot code).
4. Pick your phone in the device menu and press **Run ▶**.

## Option B: Build from your phone (GitHub, no laptop)

1. In your phone's browser, sign in to github.com and create a new **private** repository called `mossling`.
2. On the repo page, tap **Add file → Upload files** and upload everything inside the `Mossling` folder, keeping the folders. (Easiest with a file manager app that can unzip, or the GitHub mobile site in desktop mode.)
3. Open the **Actions** tab. The "Build Mossling APK" job runs on its own. Wait for the green check (about 5 minutes).
4. Open the finished run, download **Mossling-apk** under Artifacts, unzip it and tap `app-debug.apk` to install. Allow "Install unknown apps" for your browser or Files app when asked.

## Turn on the lock (one time)

1. Open Mossling. It will ask to turn on the lock. Tap **Open settings**.
2. In Accessibility, open **Installed apps → Mossling social media lock** and switch it on.
3. If the switch is greyed out ("Restricted setting"), go to **Settings → Apps → Mossling**, tap **⋮** at the top right, choose **Allow restricted settings**, then try step 2 again.

## Change which apps are locked

Edit `app/src/main/java/com/axton/mossling/Blocklist.kt`. YouTube is listed but turned off, since you post there. Remove the `//` in front of it to lock it too.

## Honest limits

- You can switch the lock off in Accessibility settings any time. It's a strong nudge, not a parental control.
- Some phones (Xiaomi, Oppo, some Samsung battery modes) stop accessibility services to save battery. If the lock stops working, set Mossling's battery usage to **Unrestricted**.
- The app version saves progress on your phone, separately from the web version in Claude.
