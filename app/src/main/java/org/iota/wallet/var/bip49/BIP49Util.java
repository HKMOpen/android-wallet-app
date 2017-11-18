package org.iota.wallet.var.bip49;

import android.content.Context;
import android.widget.Toast;


import org.bitcoinj.crypto.MnemonicException;
import org.iota.wallet.var.SamouraiWallet;
import org.iota.wallet.var.hd.HD_Address;
import org.iota.wallet.var.hd.HD_Wallet;
import org.iota.wallet.var.hd.HD_WalletFactory;

import java.io.IOException;

public class BIP49Util {

    private static HD_Wallet wallet = null;

    private static Context context = null;
    private static BIP49Util instance = null;

    private BIP49Util() { ; }

    public static BIP49Util getInstance(Context ctx) {

        context = ctx;

        if(instance == null || wallet == null) {

            try {
                wallet = HD_WalletFactory.getInstance(context).getBIP49();
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
                Toast.makeText(context, "HD wallet error", Toast.LENGTH_SHORT).show();
            }
            catch (MnemonicException.MnemonicLengthException mle) {
                mle.printStackTrace();
                Toast.makeText(context, "HD wallet error", Toast.LENGTH_SHORT).show();
            }

            instance = new BIP49Util();
        }

        return instance;
    }

    public void reset()  {
        wallet = null;
    }

    public HD_Wallet getWallet() {
        return wallet;
    }

    public P2SH_P2WPKH getAddressAt(int chain, int idx) {
        HD_Address addr = getWallet().getAccount(0).getChain(chain).getAddressAt(idx);
        P2SH_P2WPKH p2shp2wpkh = new P2SH_P2WPKH(addr.getPubKey(), SamouraiWallet.getInstance().getCurrentNetworkParams());
        return p2shp2wpkh;
    }

}
