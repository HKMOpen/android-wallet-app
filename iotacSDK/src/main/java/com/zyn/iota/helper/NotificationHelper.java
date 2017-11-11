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

package com.zyn.iota.helper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import org.iota.wallet.R;
import org.iota.wallet.ui.activity.MainActivity;

public class NotificationHelper {

    private static NotificationManager notificationManager;

    public static void responseNotification(Context context, int image, String title, int id) {

        notificationManager = (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        long[] vibrateLength = {500, 1000};

        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setAction(Constants.ACTION_MAIN);
        notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                id, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(image)
                .setColor(ContextCompat.getColor(context, R.color.colorAccent))
                .setContentTitle(title)
                .setContentIntent(pendingIntent)
                .setVibrate(vibrateLength)
                .setAutoCancel(true)
                .build();

        notificationManager.notify(id, notification);
    }

    public static void requestNotification(Context context, int image, String title, int id) {

        notificationManager = (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setAction(Constants.ACTION_MAIN);
        notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                id, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(image)
                .setColor(ContextCompat.getColor(context, R.color.colorAccent))
                .setContentTitle(title)
                .setProgress(0, 0, true)
                .setContentIntent(pendingIntent)
                .setAutoCancel(false)
                .build();

        notificationManager.notify(id, notification);
    }
}
