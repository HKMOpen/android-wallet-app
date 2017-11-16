package org.iota.wallet.ui.util;

import android.app.Application;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.Base64;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;
import com.hkm.advancedtoolbar.V5.BeastBar;

import org.iota.wallet.R;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import static org.iota.wallet.var.Constants.AG_C;


/**
 * Created by hesk on 15/8/2017.
 */

public final class ComSetup {

    public static BeastBar.Builder buildEditPersonal() {
        BeastBar.Builder bb = new BeastBar.Builder();
        bb.companyIcon(R.drawable.logocom);
        //bb.back(R.drawable.ic_chevron_left_24dp);
        bb.enableLogoAnimation(false);
        return bb;
    }

    public static BeastBar.Builder builderBeastEditImageInfo() {
        BeastBar.Builder bb = new BeastBar.Builder();
        //this is the heart button
        // bb.companyIcon(R.drawable.actionbar_bg_hb_logo);
        bb.useNoPaddingWidgetButton();
        bb.back(R.drawable.arrow_l);
        bb.disableTitle();
        bb.enableLogoAnimation(false);
        return bb;
    }


    public static BeastBar.Builder buildBeastHomeWhite(Application context) {
        BeastBar.Builder bb = BeastbarEmporerBasic(context);
        //this is the heart button
        bb.back(R.drawable.arrow_l);
        bb.enableLogoAnimation(true);
        return bb;
    }

    public static BeastBar.Builder buildBeastHome(Application context) {
        BeastBar.Builder bb = BeastbarEmporerBasic(context);
        //this is the heart button
        //bb.search(R.drawable.ic_action_action_search);
        bb.back(R.drawable.btn);
        bb.background(R.drawable.trans);
        bb.disableTitle();
        bb.enableLogoAnimation(true);
        bb.useCustomWidgetStylingCompact40();
        return bb;
    }

    public static BeastBar.Builder buildBeastSingle(@DrawableRes final int k) {
        BeastBar.Builder bb = new BeastBar.Builder();
        //this is the heart button
        // bb.search(k);
        bb.companyIcon(R.drawable.logocom);
        bb.back(R.drawable.arrow_l);
        bb.disableTitle();
        bb.enableLogoAnimation(true);
        return bb;
    }

    public static BeastBar.Builder builderRegBar(Application app) {
        BeastBar.Builder bb = BeastbarEmporerBasic(app);
        //this is the heart button
        bb.back(R.drawable.arrow_l);
        bb.useNoPaddingWidgetButton();
        bb.enableLogoAnimation(true);
        return bb;
    }

    private static BeastBar.Builder BeastbarEmporerBasic(Application context) {
        BeastBar.Builder bb = new BeastBar.Builder();
        bb.setFontFace(context, AG_C);
        bb.companyIcon(R.drawable.ic_logo_emporer_padding);
        bb.setToolBarTitleSize(R.dimen.tag_home_title);
        bb.background(R.drawable.toolbar_background);
        bb.setToolBarTitleColor(R.color.colorFPrimary);
        bb.setCustomLayoutID(R.layout.p_bs_bar);
        bb.enableLogoAnimation(true);
        return bb;
    }

    public static BeastBar.Builder builderMovieDetailBar(Application context) {
        BeastBar.Builder bb = BeastbarEmporerBasic(context);
        bb.useLayoutV2();
        bb.back(R.drawable.arrow_l);
        bb.useCustomWidgetStylingCompact40();
        bb.enableLogoAnimation(true);
        return bb;
    }

    public static BeastBar.Builder builderPromotion(Application context) {
        BeastBar.Builder bb = BeastbarEmporerBasic(context);
        bb.back(R.drawable.emp_arrow_left);
        bb.useCustomWidgetStylingCompact40();
        bb.enableLogoAnimation(true);
        return bb;
    }

    public static BeastBar.Builder builderFlow(Application context) {
        BeastBar.Builder bb = BeastbarEmporerBasic(context);
        bb.useNoPaddingWidgetButton();
        bb.useCustomWidgetStyling(R.style.actionButtonCompact30);
        bb.back(R.drawable.emp_arrow_left);
        bb.enableLogoAnimation(false);
        return bb;
    }

   /* public static BeastBar.Builder builderComment() {
        BeastBar.Builder bb = new BeastBar.Builder();
        bb.search(R.drawable.ic_edit_black_24dp);
        // bb.companyIcon(R.drawable.actionbar_bg_hb_logo);
        bb.back(R.drawable.ic_chevron_left_24dp);
        // bb.background(R.drawable.actionbar_bg_hb_white);
        bb.enableLogoAnimation(false);
        return bb;
    }
    */

    /**
     * Building the glide url for accessing the content
     * User Name,Access Key Id,Secret Access Key
     * "weber",
     * AKIAIFJ374AD35GTWGNQ,
     * 2vGVpy9uvbSKIIWHLzo3JFdoNEXhPe7POqsLmdCS
     *
     * @param infodata url
     * @return the glide url is now here
     */
    public static GlideUrl build(@Nullable String[] infodata) {
        String p1 = "2vGVpy9uvbSKIIWHLz";
        String p2 = "o3JFdoNEXhPe7POqsLmdCS";
        String p3 = "AKIAIFJ374A";
        String p4 = "D35GTWGNQ";
        String action = "AWS %s:%s";
        String AWSSecretKey = p1 + p2;
        String signature = "";
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            byte[] keyBytes = AWSSecretKey.getBytes("UTF8");
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");
            mac.init(signingKey);
            String signString = infodata[1];
            byte[] signBytes = mac.doFinal(signString.getBytes("UTF8"));
            signature = Base64.encodeToString(signBytes, Base64.DEFAULT);
            signature = signature.substring(0, -2);
        } catch (Exception e) {

        }

        //java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))
        return new GlideUrl(infodata[0], new LazyHeaders.Builder()
                .addHeader("Authorization", String.format(action, p3 + p4, signature))
                .build());
    }

    public static GlideUrl build(@Nullable String infodata) {
        return new GlideUrl(infodata == null || infodata.isEmpty() ? SAMPLE_IMAGE_URL_HEAD_D2 : infodata, new LazyHeaders.Builder().addHeader("Authorization", "").build());
    }

    public static RequestOptions glideOpt() {
        RequestOptions opt = new RequestOptions();
        opt.placeholder(R.drawable.ic_logo_emporer_padding);
        opt.diskCacheStrategy(DiskCacheStrategy.ALL);

        return opt;
    }

    public static RequestOptions cropCenter() {
        RequestOptions rp = glideOpt();
        rp.centerCrop();
        return rp;
    }
}
