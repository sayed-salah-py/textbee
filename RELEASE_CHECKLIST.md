# Release Checklist (Android)

## Build & Signing
- [ ] Configure **release keystore** (set `RELEASE_STORE_FILE`, `RELEASE_STORE_PASSWORD`, `RELEASE_KEY_ALIAS`, `RELEASE_KEY_PASSWORD`).
- [ ] Build `assembleRelease` and verify APK/AAB signing.
- [ ] Ensure **Play App Signing** enrollment.

## SDK / Target Compliance
- [ ] Confirm targetSdkVersion meets **current Play requirement** at release time.
- [ ] Verify **foreground service type** declared and justified.

## Permissions & Policy
- [ ] Submit **SMS permissions declaration** in Play Console.
- [ ] Provide **in‑app disclosure** for SMS data usage.
- [ ] Verify runtime **POST_NOTIFICATIONS** flow on Android 13+.

## Security
- [ ] R8/ProGuard **release build** passes smoke tests.
- [ ] Verify **HTTPS‑only** endpoints.
- [ ] Confirm local storage is **encrypted**.

## Testing
- [ ] Run unit tests (`./gradlew test`).
- [ ] Run lint (`./gradlew lint`).
- [ ] Test on Android 13/14 devices (notifications, SMS send/receive, boot).

## Play Console
- [ ] Complete **Data Safety** form (see `PRIVACY_AUDIT.md`).
- [ ] Provide **Privacy Policy** URL.
- [ ] Provide **feature screenshots** demonstrating SMS usage.
