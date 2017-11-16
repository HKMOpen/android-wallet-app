package org.iota.wallet.ui.util;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.hkm.advancedtoolbar.Util.ErrorMessage;
import com.hkm.advancedtoolbar.V5.BeastBar;
import org.iota.wallet.R;
import org.iota.wallet.var.LocaleUtils;

import butterknife.ButterKnife;

import static org.iota.wallet.var.Constants.AG_B;
import static org.iota.wallet.var.Constants.AK_L;
import static org.iota.wallet.var.Constants.DF_HK_W3;
import static org.iota.wallet.var.Constants.intent_title_tag;

/**
 * Created by hesk on 16/11/2017.
 */

public abstract class utilActivity extends AppCompatActivity {


    protected final boolean postThreadExist() {
        if (getFragmentManager() == null || getFragmentManager().isDestroyed())
            return false;
        else return true;
    }

    private MaterialDialog dialog;


    protected void showProgressDialog() {
        if (!postThreadExist()) return;
        dialog = new MaterialDialog.Builder(this)
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


    protected abstract int layout();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  Fabric.with(this, new Crashlytics());
        setContentView(layout());
        ButterKnife.bind(this);
      //  api_user = Appliance.getGDCore().getUserSession();
     //   api_movie = Appliance.getGDCore().getTicketingApi();
    }

    protected final void error(int error_code, Throwable t) {
        if (getFragmentManager() == null) return;
        ErrorMessage.alert("error " + error_code + " " + t.getMessage(), getFragmentManager());
    }

    protected void applyStyleFontTopic(TextView title_top) {
        Typeface face = Typeface.createFromAsset(getAssets(), AG_B);
        title_top.setTypeface(face);
    }

    protected void titleEnhancement(TextView title_top) {
        applyStyleFontTopic(title_top);
        String t = getIntent().getExtras().getString(intent_title_tag);
        title_top.setText(t);
    }


    protected void fieldEnhancement(TextView title_top) {
        Typeface face;
        if (LocaleUtils.getLanguageIndex(getApplication()) == 0) {
            face = Typeface.createFromAsset(getAssets(), AK_L);
        } else {
            face = Typeface.createFromAsset(getAssets(), DF_HK_W3);
        }
        title_top.setTypeface(face);
        String t = getIntent().getExtras().getString(intent_title_tag);
        title_top.setText(t);
    }


/*
    protected void initSelector(DiscreteScrollView roller) {
        roller.setOrientation(Orientation.HORIZONTAL);
        roller.addOnItemChangedListener(this);
        roller.setItemTransitionTimeMillis(209);
        roller.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.9f)
                .setMaxScale(1.0f)
                .build());
    }


    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {

    }
*/

    protected BeastBar.onButtonPressListener beastbar_back_to_finish = new BeastBar.onButtonPressListener() {
        @Override
        public boolean onBackPress(int previousTitleSteps) {
            finish();
            return false;
        }

        @Override
        public void onSearchPress() {

        }
    };

    protected Bundle getCurrentBundle() {
        Bundle data = getIntent().getExtras();
        if (data == null) {
            data = new Bundle();
        }
        return data;
    }

    protected final void error(String error_message) {
        //ErrorMessage.alert("error " + error_code + " " + t.getMessage(), getChildFragmentManager());
        new MaterialDialog.Builder(this)
                .content(error_message)
                .positiveText(R.string.okay_now)
                .cancelable(false)
                .show();
    }

    protected final void errorAction(String error_message, final MaterialDialog.SingleButtonCallback run) {
        new MaterialDialog.Builder(this)
                .cancelable(false)
                .content(error_message)
                .onPositive(run)
                .positiveColor(ContextCompat.getColor(getBaseContext(), R.color.colorFPrimary))
                .positiveText(getString(R.string.card_label_persistence_yes))
                .show();
    }

    protected final void submissionConfirm(final MaterialDialog.SingleButtonCallback run) {
        new MaterialDialog.Builder(this)
                .cancelable(false)
                .content(getString(R.string.message_confirm_transfer))
                .onPositive(run)
                .positiveColor(ContextCompat.getColor(getBaseContext(), R.color.colorFPrimary))
                .positiveText(getString(R.string.card_label_persistence_yes))
                .show();

    }

    protected final void exitConfirm() {
        new MaterialDialog.Builder(this)
                .cancelable(false)
                .content("Do you want to exit from the payment?")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        finish();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .positiveColor(ContextCompat.getColor(getBaseContext(), R.color.colorFPrimary))
                .positiveText(getString(R.string.card_label_persistence_yes))
                .negativeText("No")
                .show();
    }


    protected void init_recyclerview_horizontal(RecyclerView recyclerView, RecyclerView.RecycledViewPool pool, RecyclerView.Adapter adapter) {
        // pool.setMaxRecycledViews(R.layout.i_food_item, Integer.MAX_VALUE);
        // recyclerView.setRecycledViewPool(pool);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        // recyclerView.getRecycledViewPool().setMaxRecycledViews(R.layout.i_food_item, Integer.MAX_VALUE);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        // l_recycler.addItemDecoration(new GridSpacingItemDecoration(3, 1, false));
        //recyclerView.setItemAnimator(new FadeInItemAnimator());
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
