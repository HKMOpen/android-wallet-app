package org.iota.wallet.ui.util;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.ButterKnife;

/**
 * Created by hesk on 31/8/2017.
 */

public abstract class TakeViewer {
    private final View mView;

    public TakeViewer(LinearLayout container, final @LayoutRes int res) {
        mView = LayoutInflater.from(container.getContext()).inflate(res, container, false);
        ButterKnife.bind(this, mView);
    }

    public View getView() {
        return mView;
    }
}