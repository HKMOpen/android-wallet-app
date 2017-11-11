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

import android.content.Context;

import org.iota.wallet.model.api.requests.ApiRequest;
import org.iota.wallet.model.api.requests.GetAccountDataRequest;
import org.iota.wallet.model.api.responses.ApiResponse;
import org.iota.wallet.model.api.responses.GetAccountDataResponse;
import org.iota.wallet.model.api.responses.error.NetworkError;

import jota.IotaAPI;
import jota.error.ArgumentException;
import jota.error.InvalidAddressException;
import jota.error.InvalidBundleException;
import jota.error.InvalidSecurityLevelException;
import jota.error.InvalidSignatureException;
import jota.error.InvalidTrytesException;
import jota.error.NoInclusionStatesException;
import jota.error.NoNodeInfoException;

public class GetAccountDataRequestHandler extends IotaRequestHandler {
    public GetAccountDataRequestHandler(IotaAPI iotaApi, Context context) {
        super(iotaApi, context);
    }

    @Override
    public Class<? extends ApiRequest> getType() {
        return org.iota.wallet.model.api.requests.GetAccountDataRequest.class;
    }

    @Override
    public ApiResponse handle(ApiRequest request) {
        ApiResponse response;

        try {
            response = new GetAccountDataResponse(apiProxy.getAccountData(((GetAccountDataRequest) request).getSeed(),
                    ((org.iota.wallet.model.api.requests.GetAccountDataRequest) request).getSecurity(),
                    ((org.iota.wallet.model.api.requests.GetAccountDataRequest) request).getIndex(),
                    ((org.iota.wallet.model.api.requests.GetAccountDataRequest) request).isChecksum(),
                    ((org.iota.wallet.model.api.requests.GetAccountDataRequest) request).getTotal(),
                    ((org.iota.wallet.model.api.requests.GetAccountDataRequest) request).isReturnAll(),
                    ((org.iota.wallet.model.api.requests.GetAccountDataRequest) request).getStart(),
                    ((org.iota.wallet.model.api.requests.GetAccountDataRequest) request).getEnd(),
                    ((org.iota.wallet.model.api.requests.GetAccountDataRequest) request).isInclusionState(),
                    ((org.iota.wallet.model.api.requests.GetAccountDataRequest) request).getThreshold()));
        } catch (ArgumentException | InvalidSecurityLevelException | InvalidAddressException | InvalidBundleException | InvalidSignatureException | NoNodeInfoException | InvalidTrytesException | NoInclusionStatesException e) {
            response = new NetworkError();
        }
        return response;
    }
}
