# Privacy & Data Safety Audit

## Data Collected (On‑Device)
| Data Type | Examples | Purpose |
|---|---|---|
| SMS content | Message body, sender/recipient numbers | Gateway send/receive |
| Device identifiers | Device ID, SIM ICCID, subscription ID, card ID | Device registration & routing |
| Device info | Brand, model, build ID, OS, app version, device name | Diagnostics & compatibility |
| Diagnostics | Battery %, charging, uptime, memory/storage stats | Heartbeat/health |
| Locale/timezone | Locale, timezone | Diagnostics |
| Configuration | API endpoint, send delay, feature toggles | User preferences |

## Data Transmitted to Server (via HTTPS)
- **Register/Update device**: Device info, SIM info, app version, device name.
- **Heartbeat**: Diagnostics + SIM info + feature flags.
- **SMS send/receive**: Message content, sender/recipient numbers, status, timestamps.
- **SMS status**: Delivery/sent status and errors.

## Data Stored Locally
- **Encrypted SharedPreferences**:
  - API key
  - Device ID
  - API endpoint
  - Feature toggles and settings

## Third‑Party Sharing
None detected. No analytics, ads, or crash SDKs.

## Play Console Data Safety Draft (Summary)
**Data Collected**
1. **Personal info**: Phone numbers (sender/recipient), SMS content.
2. **Device identifiers**: Device ID, SIM identifiers.
3. **App activity**: Feature toggles, SMS activity metadata.
4. **Diagnostics**: Battery, network type, memory/storage stats, uptime.

**Data Usage**
- Core app functionality (SMS gateway, device registration, health checks)
- App diagnostics and reliability

**Data Sharing**
- Shared **only with the user‑configured backend** (first‑party server).

**Data Processing**
- Encrypted locally.
- Transmitted over HTTPS only.

## Privacy Policy Requirements (Checklist)
- State that the app **collects, transmits, and processes SMS content**.
- Disclose collection of **phone numbers, device identifiers, and SIM identifiers**.
- Explain **why** each data type is collected (gateway functionality).
- Provide **data retention** and **deletion** policy.
- Describe **user control** (feature toggles, endpoint configuration).
- Provide **contact method** for privacy requests.
