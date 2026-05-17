package com.vernu.sms.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class SharedPreferenceHelper {
    private static final String PREF_FILE = "PREF";
    private static final String ENCRYPTED_PREF_FILE = "PREF_SECURE";
    private static SharedPreferences encryptedPrefs;
    private static SharedPreferences legacyPrefs;
    private static boolean encryptionUnavailable = false;

    private static SharedPreferences getLegacyPrefs(Context context) {
        if (legacyPrefs == null) {
            legacyPrefs = context.getApplicationContext().getSharedPreferences(PREF_FILE, 0);
        }
        return legacyPrefs;
    }

    private static SharedPreferences getEncryptedPrefs(Context context) {
        if (encryptionUnavailable) {
            return null;
        }
        if (encryptedPrefs != null) {
            return encryptedPrefs;
        }
        try {
            MasterKey masterKey = new MasterKey.Builder(context.getApplicationContext())
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build();
            encryptedPrefs = EncryptedSharedPreferences.create(
                context.getApplicationContext(),
                ENCRYPTED_PREF_FILE,
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
            return encryptedPrefs;
        } catch (GeneralSecurityException | IOException e) {
            encryptionUnavailable = true;
            return null;
        }
    }

    public static void setSharedPreferenceString(Context context, String key, String value) {
        SharedPreferences settings = getEncryptedPrefs(context);
        if (settings == null) {
            settings = getLegacyPrefs(context);
        }
        settings.edit().putString(key, value).apply();
    }

    public static void setSharedPreferenceInt(Context context, String key, int value) {
        SharedPreferences settings = getEncryptedPrefs(context);
        if (settings == null) {
            settings = getLegacyPrefs(context);
        }
        settings.edit().putInt(key, value).apply();
    }

    public static void setSharedPreferenceBoolean(Context context, String key, boolean value) {
        SharedPreferences settings = getEncryptedPrefs(context);
        if (settings == null) {
            settings = getLegacyPrefs(context);
        }
        settings.edit().putBoolean(key, value).apply();
    }

    public static String getSharedPreferenceString(Context context, String key, String defValue) {
        SharedPreferences encrypted = getEncryptedPrefs(context);
        SharedPreferences legacy = getLegacyPrefs(context);
        if (encrypted != null) {
            if (encrypted.contains(key)) {
                return encrypted.getString(key, defValue);
            }
            if (legacy.contains(key)) {
                String value = legacy.getString(key, defValue);
                encrypted.edit().putString(key, value).apply();
                legacy.edit().remove(key).apply();
                return value;
            }
            return defValue;
        }
        return legacy.getString(key, defValue);
    }

    public static int getSharedPreferenceInt(Context context, String key, int defValue) {
        SharedPreferences encrypted = getEncryptedPrefs(context);
        SharedPreferences legacy = getLegacyPrefs(context);
        if (encrypted != null) {
            if (encrypted.contains(key)) {
                return encrypted.getInt(key, defValue);
            }
            if (legacy.contains(key)) {
                int value = legacy.getInt(key, defValue);
                encrypted.edit().putInt(key, value).apply();
                legacy.edit().remove(key).apply();
                return value;
            }
            return defValue;
        }
        return legacy.getInt(key, defValue);
    }

    public static boolean getSharedPreferenceBoolean(Context context, String key, boolean defValue) {
        SharedPreferences encrypted = getEncryptedPrefs(context);
        SharedPreferences legacy = getLegacyPrefs(context);
        if (encrypted != null) {
            if (encrypted.contains(key)) {
                return encrypted.getBoolean(key, defValue);
            }
            if (legacy.contains(key)) {
                boolean value = legacy.getBoolean(key, defValue);
                encrypted.edit().putBoolean(key, value).apply();
                legacy.edit().remove(key).apply();
                return value;
            }
            return defValue;
        }
        return legacy.getBoolean(key, defValue);
    }

    public static void clearSharedPreference(Context context, String key) {
        SharedPreferences encrypted = getEncryptedPrefs(context);
        SharedPreferences legacy = getLegacyPrefs(context);
        if (encrypted != null) {
            encrypted.edit().remove(key).apply();
        }
        legacy.edit().remove(key).apply();
    }
}
