package org.iota.wallet.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;

import org.iota.wallet.R;
import org.iota.wallet.var.LocaleUtils;

/**
 * Created by hesk on 5/2/16.
 */
public class SetupDash extends AppCompatActivity {
  //  private UserSessionAPI user_api;
  //  private MovieTicketingAPI movie_api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
       // Fabric.with(this, new Crashlytics());
        setContentView(R.layout.as_dash);
       /* user_api = Appliance.getGDCore().getUserSession();
        movie_api = Appliance.getGDCore().getTicketingApi();
        if (BuildConfig.PRODUCTION) {
            step1();
        } else {
            nextSetup();
        }*/

        nextSetup();
    }

    private MaterialDialog dialog;

    private void popStore(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    private void forceupgrade(final String url) {
        dialog = new MaterialDialog.Builder(this)
                .content(getString(R.string.force_upgrade_msg_true))
                .cancelable(false)
                .positiveText(getString(R.string.button_update))
                .negativeText(getString(R.string.buttons_cancel))
                .positiveColorRes(R.color.colorFPrimary)
                .negativeColorRes(R.color.colorFPrimary)
                .onPositive((dialog, which) -> popStore(url))
                .onNegative((dialog, which) -> finish())
                .show();
    }

    private void optional(final String url) {
        dialog = new MaterialDialog.Builder(this)
                .content(getString(R.string.force_upgrade_msg_optional))
                .cancelable(false)
                .positiveColorRes(R.color.colorFPrimary)
                .positiveText(getString(R.string.buttons_ok))
                .negativeColorRes(R.color.colorFPrimary)
                .negativeText(getString(R.string.buttons_cancel))
                .onPositive((dialog, which) -> popStore(url))
                .onNegative((dialog, which) -> nextSetup())
                .show();
    }

   /* private void step1() {
        user_api.checkVersion(BuildConfig.VERSION_CODE, new CheckVersion() {
            @Override
            public void forceUpgrade(String url_app_store) {
                forceupgrade(url_app_store);
            }

            @Override
            public void optionalUpgrade(String url_app_store) {
                optional(url_app_store);
            }

            @Override
            public void proceed() {
                nextSetup();
            }

            @Override
            public void fail() {
                nextSetup();
            }
        });
    }
*/
    private void nextSetup() {
        if (LocaleUtils.onBootFromNewLocale(getApplicationContext())) {
            LocaleUtils.setBootFromLocale(getApplicationContext(), false);
            stepFinal();
        } else {
            stepFinal();
        }
    }

/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PromotionNewsPhoto.requestCode:
                last_exe_step();
                break;
            default:
                break;
        }
    }
*/

    private void stepFinal() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final boolean u_Concert_fin = preferences.getBoolean("SH_ORDER_SWITCH", false);
        laststep();
        /*
        if (user_api.isLogin()) {
            laststep();
        } else {
            user_api.initGuest(new ObjectCallback<memberLogin>() {
                @Override
                public void onSuccess(memberLogin map) {
                    laststep();
                }

                @Override
                public void onError(Throwable t, int error_code) {
                    Log.d("ee", t.getMessage());
                }
            });
        }*/
    }

    private void laststep() {
    /*    movie_api.crazy_ad_implementation(new ObjectCallback<String>() {
            @Override
            public void onSuccess(String map) {
                if (map == null) {
                    last_exe_step();
                } else {
                    PromotionNewsPhoto.crazyAd(SetupDash.this, map);
                }
            }

            @Override
            public void onError(Throwable t, int error_code) {
                last_exe_step();
            }
        });*/
        last_exe_step();
    }

    private void last_exe_step() {
        Handler h = new Handler();
        h.postDelayed(() -> {
            Intent in = new Intent(SetupDash.this, MainNewIota.class);
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            SetupDash.this.startActivity(in);
            SetupDash.this.finish();
        }, 500);
    }

    public static void startUp(Activity on_activity) {
        Intent in = new Intent(on_activity, SetupDash.class);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        on_activity.startActivity(in);
        on_activity.finish();
    }
}
