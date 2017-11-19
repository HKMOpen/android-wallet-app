/*
 * Copyright (C) 2017 IOTA Foundation
 *
 * Authors: pinpong, adrianziser, saschan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.iota.wallet;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.support.v7.preference.PreferenceManager;

import org.iota.wallet.var.AESCrypt;
import org.iota.wallet.var.PrefsUtil;

import static org.iota.wallet.var.Constants.intent_password_default;
import static org.iota.wallet.var.PrefsUtil.IOTA_ENC_SEED;

public class IOTA extends Application {
    public static char[] seed = null;

    @Override
    public void onTerminate() {
        super.onTerminate();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (seed != null) {
            try {
                AESCrypt aes = new AESCrypt(intent_password_default);
                PrefsUtil.getInstance(getApplicationContext()).setValue(IOTA_ENC_SEED, aes.encrypt(seed.toString()));
                //  prefs.edit().putString(IOTA_ENC_SEED, aes.encrypt(seed.toString())).apply();
            } catch (Exception e) {
                e.getStackTrace();
            }
            seed = null;
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        try {
            AESCrypt aes = new AESCrypt(intent_password_default);
            String encSeed = PrefsUtil.getInstance(getApplicationContext()).getValue(IOTA_ENC_SEED, "");
            if (!encSeed.isEmpty())
                IOTA.seed = aes.decrypt(encSeed).toCharArray();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}

