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

package org.iota.wallet.helper.price;

import android.content.Context;
import android.os.AsyncTask;

import org.iota.wallet.helper.IOTAUtils;
import org.knowm.xchange.currency.Currency;

class ExchangeRateUpdateTask {

    private final Context context;
    private final Currency baseCurrency;
    private final ExchangeRateStorage exchangeRateProvider;

    public ExchangeRateUpdateTask(Context context, Currency baseCurrency, ExchangeRateStorage exchangeRateProvider) {
        this.context = context;
        this.baseCurrency = baseCurrency;
        this.exchangeRateProvider = exchangeRateProvider;
    }

    public void startNewRequestTask(boolean updateSelective) {
        ExchangeRateUpdateTaskHandler rt = new ExchangeRateUpdateTaskHandler(baseCurrency,
                IOTAUtils.getConfiguredAlternateCurrency(context), updateSelective);

        rt.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, exchangeRateProvider);
    }
}
