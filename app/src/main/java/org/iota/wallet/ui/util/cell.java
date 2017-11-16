package org.iota.wallet.ui.util;

import android.app.Application;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.iota.wallet.R;
import org.iota.wallet.var.Constants;
import org.iota.wallet.var.LocaleUtils;

import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import static org.iota.wallet.var.Constants.AG_R;
import static org.iota.wallet.var.Constants.AK_R;


/**
 * Created by zJJ on 4/2/2016.
 */
public final class cell {
    public final static String bra_presentation_v1 = "%s(%s)-%s-%s";
    public final static String nick_names = "%s (%s)";
    public final static String phone_numbers = "%s, %s";
    public final static String address_format = "[%s] %s";
    public final static String fillStar = "\u2605";
    public final static String blankStar = "\u2606";
    //public final static String notebook = Character.convert"U+1F4D3";

    private static class ColorTintSpan extends CharacterStyle implements UpdateAppearance {
        private final int color;
        private final Context context;

        public ColorTintSpan(Context context, @ColorRes int color_paint) {
            color = color_paint;
            this.context = context;
        }

        @Override
        public void updateDrawState(TextPaint paint) {
            paint.setStyle(Paint.Style.FILL);
            int colorcode = ContextCompat.getColor(context, color);
            paint.setColor(colorcode);
        }
    }

    public static String cm(final int val) {
        StringBuilder sb = new StringBuilder();
        sb.append(val);
        sb.append(" cm");
        return sb.toString();
    }

    public static String kg(final int val) {
        float actual_weight = (float) val / (float) 1000;
        StringBuilder sb = new StringBuilder();
        sb.append(actual_weight);
        sb.append(" kg");
        return sb.toString();
    }

    public static String cmMeasurement(final int valw, final int valh) {
        StringBuilder sb = new StringBuilder();
        sb.append(valw);
        sb.append(" x ");
        sb.append(valh);
        sb.append(" cm");
        return sb.toString();
    }

    public static String getIntToStar(float fstarCount) {

        String star = "";
        int starCount = (int) Math.floor((double) fstarCount);
        for (int i = 0; i < starCount; i++) {
            star = star.concat(" " + fillStar);
        }

        for (int j = (5 - starCount); j > 0; j--) {
            star = star.concat(" " + blankStar);
        }
        return star;
    }

    public static String labelMealPlan(Context c, String charac) {
        StringBuilder sb = new StringBuilder();
        sb.append("Set.");
        sb.append(charac);
        return sb.toString();
    }

    public static String date2time(Date e) {
        Timestamp timestamp_now = new Timestamp(e.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_TIME_1);
        String currentTimeStamp = dateFormat.format(timestamp_now); // Find todays date
        return currentTimeStamp;
    }

    /**
     * cast the float to two letters only
     *
     * @param full_float the float input
     * @return string output
     */
    public static String fromFloat(float full_float) {
        return String.format("%1$,.2f", full_float);
    }

    public static StringBuilder toPercentSymbol(float decimal_percent) {
        float f = decimal_percent * 100f;
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%1$,.2f", f));
        sb.append("%");
        return sb;
    }
/*
    public static StringBuilder currency_dollar_zero_hkd_00(Context context, float full_to_zero) {
        String tag = context.getString(R.string.payment_selection_label_dollar);
        return currency_dollar_zero(tag, full_to_zero / 100);
    }

    public static StringBuilder currency_dollar_zero_hkd(float full_to_zero) {
        return currency_dollar_zero("HKD", full_to_zero);
    }

    public static StringBuilder currency_dollar_zero_hkd(Context ctx, float full_to_zero) {
        String tag = ctx.getString(R.string.payment_selection_label_dollar);
        return currency_dollar_zero(tag, full_to_zero);
    }*/

    public static StringBuilder currency_dollar_zero(String currency, float full_to_zero) {
        StringBuilder spm = new StringBuilder();
        NumberFormat format = NumberFormat.getInstance();
        spm.append(currency);
        //Currency c = Currency.getInstance(currency.toUpperCase());
        //  DecimalFormat df = new DecimalFormat("#,###,##0.00");
        String output = format.format(full_to_zero);
        // spm.append("$ ");
        spm.append(output);
        return spm;
    }

    public static String roundUpFormat(double amount) {
        DecimalFormat df = new DecimalFormat("#.#");

        for (Number n : Arrays.asList(12, 123.15345, 0.23, 0.15, 232234.212431324)) {
            Double d = n.doubleValue();
            System.out.println(df.format(d));
            Log.d("amount", "DF amount:: " + df.format(d));
        }


        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(amount);
    }

    private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";


    private static String generateRandomString(int RANDOM_STRING_LENGTH) {
        StringBuffer randStr = new StringBuffer();
        for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
            Random rnd = new Random();
            int number = rnd.nextInt(CHAR_LIST.length());
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }


    public static String generateRandomString3() {
        return generateRandomString(4);
    }

    public static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    protected static SpannableString underline_textview(String termscon) {
        SpannableString content = new SpannableString(termscon);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        return content;
    }

    public static void underLineDecor(TextView tv) {
        tv.setText(underline_textview(tv.getText().toString()));
    }

    public static SpannableString registration_color(String txt, Application app) {
        int lang = LocaleUtils.getLanguageIndex(app);
        int length = lang == 0 ? "Register".length() : "立即加入".length();
        SpannableString spannableString = new SpannableString(txt);
        ForegroundColorSpan foregroundSpan = new ForegroundColorSpan(ContextCompat.getColor(app, R.color.colorFPrimary));
        spannableString.setSpan(foregroundSpan, txt.length() - length, txt.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    public static void stylingExtraRollerFieldMovieDetailEMP(TextView mText) {
        Typeface face = Typeface.createFromAsset(mText.getContext().getAssets(), AK_R);
        mText.setTypeface(face);
        mText.setAllCaps(true);
        mText.setTextColor(ContextCompat.getColor(mText.getContext(), R.color.colorFAccent));
    }

    public static void applyFont(Context context, TextView title_top) {
        try {
            Typeface face = Typeface.createFromAsset(context.getResources().getAssets(), AG_R);
            title_top.setTypeface(face);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stylingExtraRollerSeatPlanEMP(TextView mText) {
        Typeface face = Typeface.createFromAsset(mText.getContext().getAssets(), AK_R);
        mText.setTypeface(face);
        mText.setAllCaps(true);
        mText.setTextColor(ContextCompat.getColor(mText.getContext(), R.color.colorFPrimary));
    }
/*

    public static String timeFormatDateEmp(Context context, String time_raw_print) {
        final String dateformat = LocaleUtils.getLanguageIndex(context) == 0 ? DATE_TIME_4 : DATE_TIME_11;
        return Util.timeFormatConvert(context, DATE_TIME_9, dateformat, time_raw_print);
    }

    public static String timeFormatDateEmpNoWeekDays(Context context, String time_raw_print) {
        final String dateformat = LocaleUtils.getLanguageIndex(context) == 0 ? DATE_TIME_2 : DATE_TIME_14;
        return Util.timeFormatConvert(context, DATE_TIME_9, dateformat, time_raw_print);
    }

    public static String timeFormatTicketingDetail(Context context, String time_raw) {
        String releaes_date_format = LocaleUtils.getLanguageIndex(context) == 0 ? DATE_TIME_6 : DATE_TIME_12;
        return Util.timeFormatConvert(context, DATE_TIME_3, releaes_date_format, time_raw);
    }
*/

    public static void ofTermsConditions(final AppCompatActivity activity, TextView tx) {
     /*   String termscon = tx.getText().toString();
        SpannableString content = new SpannableString(termscon);
        if (LocaleUtils.getLanguageIndex(activity) == 0) {
            int a1 = termscon.indexOf("Terms");
            int l = "Terms & Conditions and Privacy Policy".length();
            content.setSpan(new UnderlineSpan(), a1, a1 + l, 0);
        } else {//條款細則及私隱政策
            int a1 = termscon.indexOf("條款");
            int l = "條款細則及私隱政策".length();
            content.setSpan(new UnderlineSpan(), a1, a1 + l, 0);
        }
        tx.setText(content);*/
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // EmpInstruction.init_tnc(activity);
            }
        });
    }

    public static void ofTermsConditionsReg(final AppCompatActivity activity, TextView tx) {
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  EmpInstruction.init_tnc(activity);
            }
        });
    }

    public static boolean renderHTML_field(@Nullable TextView txt_view, @Nullable String json_txt) {
        if (json_txt == null || json_txt.isEmpty() || txt_view == null) return false;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            txt_view.setText(Html.fromHtml(json_txt, Html.FROM_HTML_MODE_LEGACY));
        } else {
            txt_view.setText(Html.fromHtml(json_txt));
        }
        return true;
    }
}
