package org.iota.wallet.var.hf;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import org.iota.wallet.R;
import org.iota.wallet.var.WebUtil;
import org.json.JSONObject;

import java.util.Date;

public class HardForkUtil {

    private static boolean DO_SPEND = true;

    private static Context context = null;

    private final static long BITCOIN_ABC_FORK_ACTIVATE_TIME = 1501590000L; // Aug 1 2017, 12:20PM

    private static HardForkUtil instance = null;

    private HardForkUtil()    { ; }

    public static HardForkUtil getInstance(Context ctx) {

        context = ctx;

        if(instance == null)    {
            instance = new HardForkUtil();
        }

        return instance;
    }

    public long getBitcoinABCForkActivateTime() {
        return BITCOIN_ABC_FORK_ACTIVATE_TIME;
    }

    public boolean isBitcoinABCForkActivateTime()  {

        Date date = new Date();

        Log.d("HardForkUtil", "time:" + date.getTime() / 1000L);

        if((date.getTime() / 1000L) >= BITCOIN_ABC_FORK_ACTIVATE_TIME)    {
            return true;
        }

        return false;
    }

    public boolean bccPushTx(String hexTx) {

        String response = null;
        boolean isOK = false;

        try {
            if(DO_SPEND)    {
                response = WebUtil.getInstance(context).postURL(WebUtil.SAMOURAI_API2 + "abc/pushtx", "tx=" + hexTx);
                if(response != null)    {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.has("status"))    {
                        if(jsonObject.getString("status").equals("ok"))    {
                            isOK = true;
                        }
                    }
                }
                else    {
                    Toast.makeText(context, R.string.message_pushtx_returns_null, Toast.LENGTH_SHORT).show();
                }
            }
            else    {
                Log.d("PushTx", hexTx);
                isOK = true;
            }

        }
        catch(Exception e) {
            return false;
        }

        return isOK;
    }

    public String bccTxOut(String tx, int idx) {

        try {
            String response = WebUtil.getInstance(context).getURL(WebUtil.SAMOURAI_API2 + "abc/txout/" + tx + "/" + idx);
            return response;
        }
        catch(Exception e) {
            return null;
        }

    }

    public String forkStatus() {

        try {
            String response = WebUtil.getInstance(context).getURL(WebUtil.SAMOURAI_API2 + "fork-status");
            return response;
        }
        catch(Exception e) {
            return null;
        }

    }

}
