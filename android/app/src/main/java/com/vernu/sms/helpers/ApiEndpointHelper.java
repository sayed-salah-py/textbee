package com.vernu.sms.helpers;

import android.content.Context;
import android.net.Uri;

import com.vernu.sms.AppConstants;

import java.util.Arrays;
import java.util.Locale;

public class ApiEndpointHelper {
    public static String getApiBaseUrl(Context context) {
        String stored = normalizeApiBaseUrl(
            SharedPreferenceHelper.getSharedPreferenceString(
                context,
                AppConstants.SHARED_PREFS_API_BASE_URL_KEY,
                ""
            )
        );

        if (isValidApiBaseUrl(stored)) {
            return stored;
        }

        return normalizeApiBaseUrl(AppConstants.DEFAULT_API_BASE_URL);
    }

    public static boolean saveApiBaseUrl(Context context, String input) {
        String normalized = normalizeApiBaseUrl(input);
        if (!isValidApiBaseUrl(normalized)) {
            return false;
        }

        SharedPreferenceHelper.setSharedPreferenceString(
            context,
            AppConstants.SHARED_PREFS_API_BASE_URL_KEY,
            normalized
        );
        return true;
    }

    public static String normalizeApiBaseUrl(String url) {
        if (url == null) {
            return "";
        }
        String trimmed = url.trim();
        if (trimmed.isEmpty()) {
            return "";
        }
        if (!trimmed.endsWith("/")) {
            trimmed = trimmed + "/";
        }
        return trimmed;
    }

    public static boolean isValidApiBaseUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            return false;
        }

        Uri uri = Uri.parse(url.trim());
        if (!"https".equalsIgnoreCase(uri.getScheme())) {
            return false;
        }

        String host = uri.getHost();
        if (host == null || host.trim().isEmpty()) {
            return false;
        }

        if (uri.getUserInfo() != null || uri.getQuery() != null || uri.getFragment() != null) {
            return false;
        }

        if (AppConstants.API_BASE_URL_HOST_ALLOWLIST.length > 0) {
            String normalizedHost = host.toLowerCase(Locale.ROOT);
            boolean allowed = Arrays.stream(AppConstants.API_BASE_URL_HOST_ALLOWLIST)
                .map(entry -> entry.toLowerCase(Locale.ROOT))
                .anyMatch(entry -> entry.equals(normalizedHost));
            if (!allowed) {
                return false;
            }
        }

        return true;
    }
}
