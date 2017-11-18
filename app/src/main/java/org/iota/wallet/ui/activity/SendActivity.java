package org.iota.wallet.ui.activity;

import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.iota.wallet.R;
import org.iota.wallet.ui.util.utilActivity;

/**
 * Created by hesk on 18/11/2017.
 */

public class SendActivity extends utilActivity {

    private final static int SCAN_QR = 2012;
    private final static int RICOCHET = 2013;

    private TextView tvMaxPrompt = null;
    private TextView tvMax = null;
    private TextView tvCurrentFeePrompt = null;
    private long balance = 0L;

    private EditText edAddress = null;
    private String strDestinationBTCAddress = null;
    private TextWatcher textWatcherAddress = null;

    private EditText edAmountBTC = null;
    private EditText edAmountFiat = null;
    private TextWatcher textWatcherBTC = null;
    private TextWatcher textWatcherFiat = null;

    private String defaultSeparator = null;

    private Button btFee = null;

    private final static int FEE_LOW = 0;
    private final static int FEE_NORMAL = 1;
    private final static int FEE_PRIORITY = 2;
    private final static int FEE_CUSTOM = 3;
    private int FEE_TYPE = 0;

    public final static int SPEND_SIMPLE = 0;
    public final static int SPEND_BIP126 = 1;
    public final static int SPEND_RICOCHET = 2;
    private int SPEND_TYPE = SPEND_BIP126;
    //    private CheckBox cbSpendType = null;
    private Switch swRicochet = null;

    private String strFiat = null;

    private double btc_fx = 286.0;
    private TextView tvFiatSymbol = null;

    private Button btSend = null;

    private int selectedAccount = 0;

    private String strPCode = null;

    private boolean bViaMenu = false;

    @Override
    protected int layout() {
        return R.layout.activity_main;
    }
}
