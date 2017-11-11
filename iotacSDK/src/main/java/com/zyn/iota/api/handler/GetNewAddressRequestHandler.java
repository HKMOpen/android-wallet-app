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

package com.zyn.iota.api.handler;

import android.app.NotificationManager;
import android.content.Context;

import org.iota.wallet.R;
import org.iota.wallet.helper.NotificationHelper;
import org.iota.wallet.helper.Utils;
import org.iota.wallet.model.api.requests.ApiRequest;
import org.iota.wallet.model.api.requests.GetNewAddressRequest;
import org.iota.wallet.model.api.responses.ApiResponse;
import org.iota.wallet.model.api.responses.GetNewAddressResponse;
import org.iota.wallet.model.api.responses.error.NetworkError;

import jota.IotaAPI;
import jota.error.InvalidAddressException;
import jota.error.InvalidSecurityLevelException;

public class GetNewAddressRequestHandler extends IotaRequestHandler {
    public GetNewAddressRequestHandler(IotaAPI apiProxy, Context context) {
        super(apiProxy, context);
    }

    @Override
    public Class<? extends ApiRequest> getType() {
        return GetNewAddressRequest.class;
    }

    @Override
    public ApiResponse handle(ApiRequest request) {
        ApiResponse response;
        int notificationId = Utils.createNewID();

            NotificationHelper.requestNotification(context,
                    R.drawable.ic_address, context.getString(R.string.notification_generate_new_address_request_title), notificationId);
        try {
            response = new GetNewAddressResponse(apiProxy.getNewAddress(((GetNewAddressRequest) request).getSeed(),
                    ((GetNewAddressRequest) request).getSecurity(),
                    ((GetNewAddressRequest) request).getIndex(),
                    ((GetNewAddressRequest) request).isChecksum(),
                    ((GetNewAddressRequest) request).getTotal(),
                    ((GetNewAddressRequest) request).isReturnAll()));
        } catch (InvalidSecurityLevelException | InvalidAddressException e) {
            response = new NetworkError();
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.cancel(notificationId);
        }

        if (response instanceof GetNewAddressResponse) {
            NotificationHelper.responseNotification(context, R.drawable.ic_address, context.getString(R.string.notification_generating_new_address_response_succeeded_title), notificationId);
        }
        return response;
    }
}
