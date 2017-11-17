package org.iota.wallet.var;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.security.Key;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by zJJ on 1/23/2016.
 */
public class Util {
    public final static String not_empty_chi_eng = "[\\u3000-\\u301e\\ufe10-\\ufe19\\ufe30-\\ufe44\\ufe50-\\ufe6b\\uff01-\\uffee]";
    public final static String filter_google_sheet_id = "([a-zA-Z0-9-_]+)";

    public static boolean isSheetId(@Nullable String text) {
        if (text == null || text.isEmpty()) return false;
        Pattern patternc = Pattern.compile(filter_google_sheet_id);
        Matcher fm = patternc.matcher(text);
        return fm.find();
    }

    //apply for all character chinese and english not empty
    public final static String not_empty = ".{2,}";
    //apply for at least one single digit
    public final static String not_empty_digit = "^\\d{1,}$";
    public final static String filter_linkconfirmer = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)";

    public static String imageEnsurer(@Nullable String text) {
        if (text == null || text.isEmpty()) return "";
        if (text.trim().startsWith("http")) {
            return text.trim();
        } else {
            return ExtractImagesFromBBCode(text);
        }
    }

    public static String LinkConfirmer(@Nullable String text) {
        if (text == null || text.isEmpty()) return "";
        Pattern patternc = Pattern.compile(filter_linkconfirmer);
        Matcher fm = patternc.matcher(text);
        if (fm.find()) {
            // Log.d("hackResult", fm.group(0));
            String k_start = fm.group(0);
            if (!k_start.isEmpty()) {
                return k_start;
            }
        }
        return "";
    }

    public final static String filter_bbcode = "(?:(?:https?)+\\:\\/\\/+[a-zA-Z0-9\\/\\._-]{1,})+(?:(?:jpe?g|png|gif))";

    public static String ExtractImagesFromBBCode(@Nullable String text) {
        if (text == null || text.isEmpty()) return "";
        Pattern patternc = Pattern.compile(filter_bbcode);
        Matcher fm = patternc.matcher(text);
        if (fm.find()) {
            // Log.d("hackResult", fm.group(0));
            String k_start = fm.group(0);
            if (!k_start.isEmpty()) {
                return k_start;
            }
        }
        return "";
    }

    /**
     * birthday checker is now on with mm/dd
     */
    public final static String birthday_dob_ddmm = "^(0?[1-9]{1}|1[0-2]{1})\\/([0-3]{0,1}[0-9]{1})$";
    public final static String checker_phone = "(^\\+\\d+)?[0-9\\s()-]*.{8,}";
    /**
     * at least 7 characters, english and number
     * ^(?=.*?\d)(?=.*?[a-zA-Z])(?=[a-zA-Z\d\~\!\@\#\$\%\^\&\*\(\)\_\+\`\-\=\{\}\[\]\;\:\|\<\>\,\.\?]{8,20}$).*$
     */
    public final static String checker_password = "^(?=.*?\\d)(?=.*?[A-Z])(?=[a-zA-Z\\d\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\`\\-\\=\\{\\}\\[\\]\\;\\:\\|\\<\\>\\,\\.\\?]{8,}$).*$";

    public static boolean CheckPasswordRule(@Nullable String text) {
        if (text == null || text.isEmpty()) return false;
        Pattern patternc = Pattern.compile(checker_password);
        Matcher fm = patternc.matcher(text);
        return fm.find();
    }

    /**
     * RUN AES 128 bit
     *
     * @param mode    encryption or decryption
     * @param key     the key for everything
     * @param content the content for everything.
     * @return the string for return
     */
    public static byte[] run_cipher(final int mode, final String key, String content) {
        try {
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            if (mode == Cipher.ENCRYPT_MODE) {
                // String text = "Hello World";
                // String key = "Bar12345Bar12345";
                // 128 bit key
                // Create key and cipher
                // encrypt the text
                cipher.init(mode, aesKey);
                byte[] encrypted = cipher.doFinal(content.getBytes());

              /*  StringBuilder sb = new StringBuilder();
                for (byte b : encrypted) {
                    sb.append((char) b);
                }
*/
                // the encrypted String
                //  String enc = sb.toString();
                //   System.out.println("encrypted:" + enc);
                //out = sb.toString();
                return encrypted;
            } else if (mode == Cipher.DECRYPT_MODE) {
                // now convert the string to byte array
                // for decryption
                byte[] bb = new byte[content.length()];
                for (int i = 0; i < content.length(); i++) {
                    bb[i] = (byte) content.charAt(i);
                }

                // decrypt the text
                cipher.init(mode, aesKey);
                // String decrypted = new String(cipher.doFinal(bb));
                // System.err.println("decrypted:" + decrypted);
                //out = decrypted;
                return bb;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * simple time parser for formatting
     *
     * @param format_from_raw the raw data format
     * @param format_out_put  the output data format
     * @param time_now        the current time in string
     * @return the string for return format
     */
    public static String timeFormatConvert(String format_from_raw, String format_out_put, String time_now) {
        String reportDate = "- NA -";
        if (time_now.isEmpty()) return reportDate;
        try {
            final DateFormat outputformat = new SimpleDateFormat(format_out_put, Locale.US);
            final SimpleDateFormat formatter = new SimpleDateFormat(format_from_raw, Locale.US);
            Date date = formatter.parse(time_now);
            reportDate = outputformat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return reportDate;
    }

    public static Date parseTime(String format, @Nullable String current_time) {
        final SimpleDateFormat outputformat = new SimpleDateFormat(format);
        Date n = new Date();
        if (current_time == null) return n;
        try {
            n = outputformat.parse(current_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return n;
    }

    public static String biLangualTimeFormatFromNow(Context ctx, String format_en, String format_ch) {
        String reportDate = "- NA -";
        if (format_ch.isEmpty() || format_en.isEmpty()) return reportDate;
        Locale loc = LocaleUtils.getLanguageIndex(ctx) == 0 ? Locale.US : Locale.TRADITIONAL_CHINESE;
        String parse_output = LocaleUtils.getLanguageIndex(ctx) == 0 ? format_en : format_ch;
        final DateFormat outputformat = new SimpleDateFormat(parse_output, loc);
        Date date = new Date();
        return outputformat.format(date);
    }

    public static String biLangualTimeFormat(Context ctx, String raw_format, String current_time, String format_en, String format_ch) {
        String reportDate = "- NA -";
        if (current_time.isEmpty() || format_ch.isEmpty() || format_en.isEmpty()) return reportDate;

        try {
            Locale loc = LocaleUtils.getLanguageIndex(ctx) == 0 ? Locale.US : Locale.TRADITIONAL_CHINESE;
            String parse_output = LocaleUtils.getLanguageIndex(ctx) == 0 ? format_en : format_ch;
            final DateFormat outputformat = new SimpleDateFormat(parse_output, loc);
            final SimpleDateFormat formatter = new SimpleDateFormat(raw_format, Locale.US);
            Date date = formatter.parse(current_time);
            reportDate = outputformat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return reportDate;
    }


    public static String timeFormatConvert(Context ctx, String format_from_raw, String format_out_put, String time_now) {
        String reportDate = "- NA -";
        if (time_now.isEmpty()) return reportDate;
        try {
            Locale loc = LocaleUtils.getLanguageIndex(ctx) == 0 ? Locale.US : Locale.TRADITIONAL_CHINESE;
            final DateFormat outputformat = new SimpleDateFormat(format_out_put, loc);
            final SimpleDateFormat formatter = new SimpleDateFormat(format_from_raw, Locale.US);
            Date date = formatter.parse(time_now);
            reportDate = outputformat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return reportDate;
    }


    public static String timeFormatConvert(Locale local, String format_out_put, Date time_stamp) {
        String reportDate = "- NA -";
        try {
            final DateFormat outputformat = new SimpleDateFormat(format_out_put, local);
            reportDate = outputformat.format(time_stamp);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return reportDate;
    }


    /**
     * converter for base64 data string to imageview
     *
     * @param imageView image view layout
     * @param data      the string src
     */
    public static void Base64ToImageView(ImageView imageView, String data) {
        try {
            String imge = data.replace("data:image/png;base64,", "");
            byte[] imageBytes = Base64.decode(imge, Base64.DEFAULT);
            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageBitmap(decodedImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String implode(String delimiter, HashMap<Integer, String> map) {
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<Integer, String>> b = map.entrySet().iterator();
        while (b.hasNext()) {
            Map.Entry<Integer, String> nex = b.next();
            sb.append(nex.getValue());
            if (b.hasNext()) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public static String implode(String delimiter, Map<String, String> map) {
        boolean first = true;
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> e : map.entrySet()) {
            if (!first) sb.append(" " + delimiter + " ");
            sb.append(" " + e.getKey() + " = '" + e.getValue() + "' ");
            first = false;
        }

        return sb.toString();
    }

    public static String implodeIn(String delimiter, ArrayList<Integer> map) {
        StringBuilder sb = new StringBuilder();
        Iterator<Integer> f = map.iterator();
        while (f.hasNext()) {
            sb.append(f.next());
            if (f.hasNext()) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    public static String implode(String delimiter, ArrayList<String> map) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> f = map.iterator();
        while (f.hasNext()) {
            sb.append(f.next());
            if (f.hasNext()) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    public static int explodeCount(String delimiter, @Nullable String data) {
        if (data == null) return 0;
        int max_tickets = data.split(delimiter).length;
        return max_tickets;
    }

    public static ArrayList<String> explode(String delimiter, @Nullable String data) {
        ArrayList<String> k = new ArrayList<String>();
        if (data == null) return k;
        String[] list_max = data.split(delimiter);
        return new ArrayList<String>(Arrays.asList(list_max));
    }
}
