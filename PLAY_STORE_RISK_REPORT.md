# Play Store Risk Report

## Overall Risk Summary
**Risk Level: High (due to restricted SMS permissions and background service usage).**

## Risk Matrix
| Area | Risk | Why |
|---|---|---|
| SMS/Call Log Policy | **High** | Uses `SEND_SMS`, `RECEIVE_SMS`, `READ_PHONE_STATE` and handles SMS content. Requires Play Console SMS permissions declaration and core‑feature justification. |
| Foreground Service Policy | **Medium** | Persistent foreground service must be user‑visible and user‑controlled. |
| Background Execution | **Medium** | Boot receiver and WorkManager usage must align with user‑expected behavior. |
| Data Safety / Privacy | **Medium** | Handles SMS content, phone numbers, and SIM identifiers; must be clearly disclosed. |
| Network Security | **Low** | HTTPS enforced; cleartext blocked. |
| Third‑party SDKs | **Low** | No analytics/ads/crash SDKs present. |

## Mitigations Implemented
- Cleartext traffic blocked and endpoint validation enforced.
- Encrypted local storage for sensitive data.
- Restricted exported components and immutable pending intents.
- Notification permission and foreground service type declared.

## Additional Mitigations Recommended
1. Provide **explicit in‑app disclosure** of SMS usage and data transmission.
2. Ensure **Play Console SMS permissions declaration** is complete with screenshots/videos.
3. Consider **domain allowlist** for API endpoint (enterprise builds).
4. Upgrade build tooling to latest supported versions before release.
