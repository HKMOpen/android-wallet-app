package org.iota.wallet.var;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by pang on 9/24/17.
 */

public class ContexUtil {

    public static void HiScreen(AppCompatActivity c) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.System.canWrite(c)) {
                ScreenBrightness(230, c);
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + c.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);
            }
        } else {
            ScreenBrightness(230, c);
        }
    }

    public static void LoScreen(AppCompatActivity c) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.System.canWrite(c)) {
                ScreenBrightness(50, c);
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + c.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);
            }
        } else {
            ScreenBrightness(50, c);
        }
    }

    public static void AutoBrightness(Context context) {
        try {
            Settings.System.putInt(context.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE,
                    Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
        } catch (Exception e) {
        }
    }

    private static boolean ScreenBrightness(int level, Context context) {
        try {
            Settings.System.putInt(context.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE,
                    Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            Settings.System.putInt(
                    context.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS,
                    level);
            return true;
        } catch (Exception e) {
            Log.e("Screen Brightness", "error changing screen brightness");
            return false;
        }
    }


}
