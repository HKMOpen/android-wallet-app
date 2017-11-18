package org.iota.wallet.ui.util;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import org.iota.wallet.R;
import org.iota.wallet.ui.activity.MainNewIota;
import org.iota.wallet.var.LocaleUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static org.iota.wallet.var.Constants.AG_B;
import static org.iota.wallet.var.Constants.AK_L;
import static org.iota.wallet.var.Constants.DF_HK_W3;
import static org.iota.wallet.var.Constants.intent_title_tag;

/**
 * Created by hesk on 16/11/2017.
 */

public abstract class utilFragment extends Fragment {
    protected Unbinder unbinder;

    @LayoutRes
    protected abstract int getLayout();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        super.onDestroyView();
    }

    protected final boolean postThreadExist() {
        if (getFragmentManager() == null || getFragmentManager().isDestroyed())
            return false;
        else return true;
    }

    private MaterialDialog dialog;


    protected void showProgressDialog() {
        if (!postThreadExist()) return;
        dialog = new MaterialDialog.Builder(getActivity())
                .content("please wait")
                .progress(true, 0)
                .cancelable(false)
                .show();
    }

    protected void hideProgress() {
        if (!postThreadExist()) return;
        if (dialog != null) {
            dialog.dismiss();
        }
    }


    protected void applyStyleFontTopic(TextView title_top) {
        Typeface face = Typeface.createFromAsset(getActivity().getAssets(), AG_B);
        title_top.setTypeface(face);
    }

    protected void titleEnhancement(TextView title_top) {
        applyStyleFontTopic(title_top);
        String t = getActivity().getIntent().getExtras().getString(intent_title_tag);
        title_top.setText(t);
    }


    protected void fieldEnhancement(TextView title_top) {
        Typeface face;
        if (LocaleUtils.getLanguageIndex(getActivity().getApplication()) == 0) {
            face = Typeface.createFromAsset(getActivity().getAssets(), AK_L);
        } else {
            face = Typeface.createFromAsset(getActivity().getAssets(), DF_HK_W3);
        }
        title_top.setTypeface(face);
        String t = getActivity().getIntent().getExtras().getString(intent_title_tag);
        title_top.setText(t);
    }

    protected Bundle getCurrentBundle() {
        Bundle data = getActivity().getIntent().getExtras();
        if (data == null) {
            data = new Bundle();
        }
        return data;
    }

    protected final void error(String error_message) {
        //ErrorMessage.alert("error " + error_code + " " + t.getMessage(), getChildFragmentManager());
        new MaterialDialog.Builder(getActivity())
                .content(error_message)
                .positiveText(R.string.okay_now)
                .cancelable(false)
                .show();
    }

    protected final void loginMessage(String error_message, final MaterialDialog.SingleButtonCallback run) {
        new MaterialDialog.Builder(getActivity())
                .cancelable(false)
                .content(error_message)
                .onPositive(run)
                .positiveColor(ContextCompat.getColor(getActivity().getBaseContext(), R.color.colorFPrimary))
                .positiveText(getString(R.string.buttons_login))
                .negativeText(getString(R.string.buttons_cancel))
                .show();
    }

    protected final void errorAction(String error_message, final MaterialDialog.SingleButtonCallback run) {
        new MaterialDialog.Builder(getActivity())
                .cancelable(false)
                .content(error_message)
                .onPositive(run)
                .positiveColor(ContextCompat.getColor(getActivity().getBaseContext(), R.color.colorFPrimary))
                .positiveText(getString(R.string.card_label_persistence_yes))
                .show();
    }

    protected final MaterialDialog.Builder getMaterialDialog() {
        return new MaterialDialog.Builder(getActivity()).cancelable(false)
                .negativeText(getString(R.string.buttons_cancel))
                .onNegative((dialog, which) -> dialog.dismiss())
                .positiveColor(ContextCompat.getColor(getActivity().getBaseContext(), R.color.colorFPrimary))
                .positiveText(getString(R.string.card_label_persistence_yes));
    }

    protected final void submissionConfirm(final MaterialDialog.SingleButtonCallback run) {
        new MaterialDialog.Builder(getActivity())
                .cancelable(false)
                .content(getString(R.string.message_confirm_transfer))
                .onPositive(run)
                .positiveColor(ContextCompat.getColor(getActivity().getBaseContext(), R.color.colorFPrimary))
                .positiveText(getString(R.string.card_label_persistence_yes))
                .show();

    }

    protected final void exitConfirm() {
        new MaterialDialog.Builder(getActivity())
                .cancelable(false)
                .content("Do you want to exit from the payment?")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        getActivity().finish();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .positiveColor(ContextCompat.getColor(getActivity().getBaseContext(), R.color.colorFPrimary))
                .positiveText(getString(R.string.card_label_persistence_yes))
                .negativeText("No")
                .show();
    }

    protected MainNewIota getParent() {
        if (getActivity() instanceof MainNewIota) {
            return (MainNewIota) getActivity();
        } else {
            return null;
        }
    }
}
