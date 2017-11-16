package org.iota.wallet.var;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;

/**
 * Created by hesk on 2/7/2017.
 */

public class LocaleUtils {

    @Retention(RetentionPolicy.SOURCE)

    @StringDef({
            ENGLISH,
            //  FRENCH,
            //   SPANISH,
            TRADITIONAL_CHINESE,
            //   SIMPLIFIED_CHINESE,
            //   JAPANESE,
            //   KOREAN,
            //   ARABIC
    })

    public @interface LocaleDef {
        String[] SUPPORTED_LOCALES = {
                ENGLISH,
                //    FRENCH,
                //    SPANISH,
                TRADITIONAL_CHINESE,
                //     SIMPLIFIED_CHINESE,
                //    JAPANESE,
                //    KOREAN,
                //    ARABIC
        };
    }

    public static final String ENGLISH = "en";
    public static final String FRENCH = "fr";
    public static final String SPANISH = "es";
    public static final String TRADITIONAL_CHINESE = "zh";
    public static final String SIMPLIFIED_CHINESE = "cn";
    public static final String JAPANESE = "jp";
    public static final String KOREAN = "ko";
    public static final String ARABIC = "ar";


    public static final String SELECTED_LANGUAGE = "app_language";
    public static final String BOOT_LANGUAGE = "app_language_onboot";

    public static void initialize(Context context) {
        @LocaleDef String lang = getPersistedData(context, Locale.getDefault().getLanguage());
        setLocale(context, lang);
    }

    public static void initialize(Context context, @LocaleDef String defaultLanguage) {
        String lang = getPersistedData(context, defaultLanguage);
        setLocale(context, defaultLanguage);
    }

    public static String getLanguage(Context context) {
        return getPersistedData(context, Locale.getDefault().getLanguage());
    }

    public static int getLanguageIndex(@Nullable Context context) {
        try {
            final String lang = getPersistedData(context, ENGLISH);
            for (int i = 0; i < LocaleDef.SUPPORTED_LOCALES.length; i++) {
                if (LocaleDef.SUPPORTED_LOCALES[i].equalsIgnoreCase(lang)) {
                    return i;
                }
            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

    public static boolean setLocale(Context context, @LocaleDef String language) {
        persist(context, language);
        return updateResources(context, language);
    }

    public static boolean setLocale(Context context, int languageIndex) {
        if (languageIndex >= LocaleDef.SUPPORTED_LOCALES.length && languageIndex >= 0) {
            return false;
        }
        persist(context, LocaleDef.SUPPORTED_LOCALES[languageIndex]);
        return updateResources(context, LocaleDef.SUPPORTED_LOCALES[languageIndex]);
    }

    private static String getPersistedData(Context context, String defaultLanguage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(SELECTED_LANGUAGE, defaultLanguage);
    }

    private static void persist(Context context, String language) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SELECTED_LANGUAGE, language);
        editor.apply();
    }

    private static boolean updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return true;
    }

    public static boolean onBootFromNewLocale(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(BOOT_LANGUAGE, false);
    }

    public static void setBootFromLocale(Context context, boolean b) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(BOOT_LANGUAGE, b);
        editor.apply();
    }
}
