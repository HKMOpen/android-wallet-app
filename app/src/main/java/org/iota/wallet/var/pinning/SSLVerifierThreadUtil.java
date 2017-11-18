package org.iota.wallet.var.pinning;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import org.iota.wallet.R;
import org.iota.wallet.var.ConnectivityStatus;


public class SSLVerifierThreadUtil {

    private static SSLVerifierThreadUtil instance = null;
    private static Context context = null;

    private AlertDialog alertDialog = null;

    private SSLVerifierThreadUtil() {
        ;
    }

    public static SSLVerifierThreadUtil getInstance(Context ctx) {

        context = ctx;

        if (instance == null) {
            instance = new SSLVerifierThreadUtil();
        }

        return instance;
    }

    public void validateSSLThread() {

        final Handler handler = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();

                if (ConnectivityStatus.hasConnectivity(context)) {

                    //Pin SSL certificate
                    switch (SSLVerifierUtil.getInstance(context).certificatePinned()) {
                        case SSLVerifierUtil.STATUS_POTENTIAL_SERVER_DOWN:
                            //On connection issue: 2 choices - retry or exit
                            showAlertDialog(context.getString(R.string.ssl_no_connection), false);
                            break;
                        case SSLVerifierUtil.STATUS_PINNING_FAIL:
                            //On fail: only choice is to exit app
                            showAlertDialog(context.getString(R.string.ssl_pinning_invalid), true);
                            break;
                        case SSLVerifierUtil.STATUS_PINNING_SUCCESS:
                            //Certificate pinning successful: safe to continue
                            break;
                    }
                } else {
                    //On connection issue: 2 choices - retry or exit
                    showAlertDialog(context.getString(R.string.ssl_no_connection), false);
                }

                handler.post(() -> {
                    ;
                });

                Looper.loop();

            }
        }).start();
    }

    private void showAlertDialog(final String message, final boolean forceExit) {
        if (!((Activity) context).isFinishing()) {
            MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
            builder.content(message);
            builder.cancelable(false);
            if (!forceExit) {
                builder.positiveText(R.string.button_retry);

                builder.onPositive((dialog, which) -> {
                    dialog.dismiss();    validateSSLThread();
                });
            }
            builder.negativeText(R.string.button_exit);
            builder.onNegative((dialog, which) -> {
                dialog.dismiss();
                ((Activity) context).finish();
            });
            builder.show();
        }
    }
}
