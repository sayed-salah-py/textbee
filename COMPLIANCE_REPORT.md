# Compliance Report (Android / Google Play)

## Scope
- Repository: `android/` module
- Focus: Google Play policy compliance, Play Protect safety, permission usage, and SDK hygiene.

## SDK & Build Configuration
- **minSdkVersion**: 24
- **targetSdkVersion**: 34
- **compileSdkVersion**: 34
- **R8/ProGuard**: Enabled for release (minify + resource shrinking)
- **Cleartext traffic**: Disabled

## Permissions (Declared)
| Permission | Type | Purpose | Notes |
|---|---|---|---|
| `SEND_SMS` | Restricted | Send outbound SMS for gateway functionality | Core app purpose |
| `RECEIVE_SMS` | Restricted | Receive SMS for gateway forwarding | Core app purpose |
| `READ_PHONE_STATE` | Restricted | SIM selection + SIM metadata | Required for multi‑SIM |
| `RECEIVE_BOOT_COMPLETED` | Normal | Restore services/heartbeat after reboot | Conditional on user enablement |
| `FOREGROUND_SERVICE` | Normal | Persistent service for reliability | User‑controlled |
| `FOREGROUND_SERVICE_DATA_SYNC` | Normal | Foreground type declaration | Required for targetSdk 34 |
| `POST_NOTIFICATIONS` | Runtime | Foreground notification visibility | Required on Android 13+ |
| `INTERNET` | Normal | API communication | Required |
| `ACCESS_NETWORK_STATE` | Normal | Network state checks | Optional, used for heartbeat |

## Exported Components (Manifest Review)
- **Exported (intended)**:
  - `MainActivity` (launcher)
  - `SMSBroadcastReceiver` (system SMS broadcast, restricted by `android.permission.BROADCAST_SMS`)
  - `BootCompletedReceiver` (system boot broadcast)
- **Not exported**:
  - `StickyNotificationService`
  - `SMSStatusReceiver`
  - `SMSFilterActivity`
  - `CaptureActivity` (ZXing)

## SDK / Dependency Audit
| Dependency | Version | Notes |
|---|---|---|
| `androidx.appcompat:appcompat` | 1.3.0 | Outdated; update recommended before release |
| `com.google.android.material:material` | 1.8.0 | Update recommended |
| `androidx.constraintlayout:constraintlayout` | 2.0.4 | Update recommended |
| `com.google.code.gson:gson` | 2.9.0 | OK |
| `com.squareup.retrofit2:retrofit` | 2.9.0 | OK |
| `com.squareup.retrofit2:converter-gson` | 2.9.0 | OK |
| `com.journeyapps:zxing-android-embedded` | 4.1.0 | OK |
| `androidx.work:work-runtime` | 2.7.1 | Update recommended |
| `androidx.security:security-crypto` | 1.0.0 | Added for encrypted local storage |

## Policy‑Sensitive Areas
1. **SMS permissions**: Restricted; must qualify under Google Play SMS policy (core functionality and disclosure required).
2. **Foreground service**: Must remain user‑visible and user‑controlled.
3. **Boot receiver**: Allowed if used for user‑initiated persistent functionality.
4. **User‑configurable API endpoint**: Must not enable hidden behavior; recommend optional domain allowlist for enterprise builds.

## Compliance Fixes Applied
- Removed unused/invalid SMS permission.
- Added runtime notification permission handling for Android 13+.
- Disabled backups to prevent unintended data extraction.
- Restricted SMS broadcast receiver to system sender.
- Disabled cleartext traffic.
- Enabled R8/ProGuard for release with safe keep rules.

## Required Play Console Declarations (Summary)
1. **SMS Permissions Declaration**: Provide justification and functional screenshots.
2. **Data Safety Form**: Declare SMS content, phone numbers, device identifiers, diagnostics.
3. **Privacy Policy**: Must cover SMS content handling and server communications.
4. **Foreground Service**: Declare type and user‑visible notification behavior.
