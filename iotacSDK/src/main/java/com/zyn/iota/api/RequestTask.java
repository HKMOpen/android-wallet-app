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

package com.zyn.iota.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.iota.wallet.BuildConfig;
import org.iota.wallet.helper.Constants;
import org.iota.wallet.model.api.requests.ApiRequest;
import org.iota.wallet.model.api.responses.ApiResponse;

import java.util.Date;

class RequestTask extends AsyncTask<ApiRequest, String, ApiResponse> {

    private final Context context;
    private EventBus bus;
    private Date start;
    private String tag = "";

    public RequestTask(Context context) {
        this.context = context;
        this.bus = EventBus.getDefault();
    }

    @Override
    protected ApiResponse doInBackground(ApiRequest... params) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String host = prefs.getString(Constants.PREFERENCE_NODE_IP, Constants.PREFERENCE_NODE_DEFAULT_IP);
        int port = Integer.parseInt(prefs.getString(Constants.PREFERENCE_NODE_PORT, Constants.PREFERENCE_NODE_DEFAULT_PORT));

        if (BuildConfig.DEBUG) {
            Log.i("ApiRequest", params[0].toString());
            start = new Date();
            Log.i("started at", start.getTime() + "");
        }

        ApiRequest apiRequest = params[0];
        tag = apiRequest.getClass().getName();

        ApiProvider apiProvider = new IotaApiProvider(host, port, context); /* lol */

        return apiProvider.processRequest(apiRequest);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ApiResponse result) {
        if (this.isCancelled()) return;
        if (BuildConfig.DEBUG) {
            if (result != null)
                Log.i("ApiResponse", new Gson().toJson(result));
            Log.i("duration", (new Date().getTime()) - start.getTime() + "");
        }

        bus.post(result);
        bus = null;
        TaskManager.removeTask(tag);
    }
}