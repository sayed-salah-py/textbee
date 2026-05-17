# Security Report

## Security Hardening Applied
- Enforced **HTTPS‑only** API base URL with validation.
- Disabled **cleartext traffic** globally.
- **Encrypted SharedPreferences** for API keys, device IDs, and settings (with legacy migration).
- Disabled **app backups** to reduce data extraction risk.
- Restricted **SMS broadcast receiver** to system sender.
- Marked internal receiver **non‑exported**.
- Set **immutable PendingIntent** flags for SMS status intents.
- Enabled **R8/ProGuard** with keep rules for Retrofit/Gson.
- Reduced notification priority for foreground service (low importance).

## Security‑Relevant Findings (Remaining)
| Finding | Risk | Notes |
|---|---|---|
| SMS permissions (`SEND_SMS`, `RECEIVE_SMS`, `READ_PHONE_STATE`) | High | Restricted permissions require Play policy approval and clear user disclosure. |
| Foreground service used to keep gateway alive | Medium | Must remain user‑visible and user‑controlled, with clear explanation. |
| User‑configurable API endpoint without allowlist | Medium | Potential abuse if a malicious endpoint is entered; optional allowlist recommended for enterprise builds. |
| AGP/Gradle versions are dated | Medium | Upgrade recommended to keep up with security patches and targetSdk requirements. |

## Explicitly Not Present
- No WebView usage.
- No dynamic code loading (DexClassLoader/Reflection for loading code).
- No analytics/ads/crash reporting SDKs.
- No device admin or accessibility services.

## Recommendations
1. **Policy approval** for SMS permissions (see Play policy requirements).
2. **Upgrade build tooling** (AGP/Gradle) before final release.
3. If distributing to a controlled environment, **enable API endpoint allowlist**.
