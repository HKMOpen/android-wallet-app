package org.iota.wallet.var.bip47;

import android.content.Context;
import android.widget.Toast;


import org.bitcoinj.core.AddressFormatException;
import org.bitcoinj.core.DumpedPrivateKey;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.MnemonicException;
import org.iota.wallet.var.SamouraiWallet;
import org.iota.wallet.var.bip47.rpc.BIP47Wallet;
import org.iota.wallet.var.bip47.rpc.NotSecp256k1Exception;
import org.iota.wallet.var.bip47.rpc.PaymentAddress;
import org.iota.wallet.var.bip47.rpc.PaymentCode;
import org.iota.wallet.var.bip47.rpc.SecretPoint;
import org.iota.wallet.var.hd.HD_Address;
import org.iota.wallet.var.hd.HD_WalletFactory;

import java.io.IOException;

public class BIP47Util {

    private static BIP47Wallet wallet = null;

    private static Context context = null;
    private static BIP47Util instance = null;

    private BIP47Util() {
        ;
    }

    public static BIP47Util getInstance(Context ctx) {

        context = ctx;

        if (instance == null || wallet == null) {

            try {
                wallet = HD_WalletFactory.getInstance(context).getBIP47();
            } catch (IOException ioe) {
                ioe.printStackTrace();
                Toast.makeText(context, "HD wallet error", Toast.LENGTH_SHORT).show();
            } catch (MnemonicException.MnemonicLengthException mle) {
                mle.printStackTrace();
                Toast.makeText(context, "HD wallet error", Toast.LENGTH_SHORT).show();
            }

            instance = new BIP47Util();
        }

        return instance;
    }

    public void reset() {
        wallet = null;
    }

    public BIP47Wallet getWallet() {
        return wallet;
    }

    public HD_Address getNotificationAddress() {
        return wallet.getAccount(0).getNotificationAddress();
    }

    public PaymentCode getPaymentCode() throws AddressFormatException {
        String payment_code = wallet.getAccount(0).getPaymentCode();
        return new PaymentCode(payment_code);
    }

    public PaymentAddress getReceiveAddress(PaymentCode pcode, int idx) throws AddressFormatException, NotSecp256k1Exception {
        HD_Address address = wallet.getAccount(0).addressAt(idx);
        return getPaymentAddress(pcode, 0, address);
    }

    public PaymentAddress getSendAddress(PaymentCode pcode, int idx) throws AddressFormatException, NotSecp256k1Exception {
        HD_Address address = wallet.getAccount(0).addressAt(0);
        return getPaymentAddress(pcode, idx, address);
    }

    public byte[] getIncomingMask(byte[] pubkey, byte[] outPoint) throws AddressFormatException, Exception {

        HD_Address notifAddress = getNotificationAddress();
        DumpedPrivateKey dpk = new DumpedPrivateKey(SamouraiWallet.getInstance().getCurrentNetworkParams(), notifAddress.getPrivateKeyString());
        ECKey inputKey = dpk.getKey();
        byte[] privkey = inputKey.getPrivKeyBytes();
        byte[] mask = PaymentCode.getMask(new SecretPoint(privkey, pubkey).ECDHSecretAsBytes(), outPoint);

        return mask;
    }

    public PaymentAddress getPaymentAddress(PaymentCode pcode, int idx, HD_Address address) throws AddressFormatException, NotSecp256k1Exception {
        DumpedPrivateKey dpk = new DumpedPrivateKey(SamouraiWallet.getInstance().getCurrentNetworkParams(), address.getPrivateKeyString());
        ECKey eckey = dpk.getKey();
        PaymentAddress paymentAddress = new PaymentAddress(pcode, idx, eckey.getPrivKeyBytes());
        return paymentAddress;
    }

}
