package org.iota.wallet.var;

/**
 * Created by hesk on 16/11/2017.
 */

public class Constants {

    public static final boolean BANNER_POSTER_BOTTOM = false;
    public static final boolean CRAZY_AD = false;
    public static final boolean ENABLED_BOOKMARK_FEATURE = false;
    // public static final String AUTH_URL = "http://" + BuildConfig.REALM_IP + ":9080/auth";
    // public static final String REALM_URL = "realm://" + BuildConfig.REALM_IP + ":9080/~/staffpos";

    public static final String DATE_TIME_1 = "dd MMM yyyy HH:mm:ss z";
    public static final String DATE_TIME_2 = "dd MMM yyyy";
    public static final String DATE_TIME_14 = "yyyy'年'MM'月'dd'日'";

    public static final String DATE_TIME_3 = "dd/MM/yyyy";
    public static final String DATE_TIME_20 = "yyyy-MM-dd";
    public static final String DATE_TIME_21 = "MM'月'dd'日'";
    public static final String DATE_TIME_22 = "dd MMM";
    //pair 1 ====
    public static final String DATE_TIME_4 = "dd MMM (EEE)";
    public static final String DATE_TIME_11 = "MM'月'dd'日' (EE)";
    //============
    // public static final String DATE_TIME_EN_TICKETING = "dd MMM";
    // public static final String DATE_TIME_ZH_TICKETING = "MM'月'dd'日'";
    public static final String DATE_TIME_5 = "dd/MM/yyyy HH:mm";
    public static final String DATE_TIME_6 = "EEE dd MMM yyyy";
    public static final String DATE_TIME_12 = "yyyy'年'MM'月'dd'日' EEEE";

    //need to show the 12 hour loop instead of 24 hr base
    public static final String DATE_TIME_7 = "hh:mm a";
    public static final String DATE_TIME_15 = "hh:mm a";

    public static final String DATE_TIME_8 = "dd MMM yyyy hh:mm a";
    public static final String DATE_TIME_13 = "yyyy'年'MM'月'dd'日'hh:mma";

    public static final String DATE_TIME_9 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_10 = "dd MMM yyyy (EEE)";

    public static final String INTENT_TABLE_FUNCTION = "ifunction";
    public static final String INTENT_BILL_ID = "transaction_id";
    public static final String INTENT_TABLE_FILTER = "extrefilter";
    public static final String RESTCODE = "rest_code";
    public static final String CAT_ID = "cat_id";
    public static final String BG_URI = "bg_run";
    public static final String SAMPLE_IMAGE_URL_HEAD_D2 = "https://s3.heskeyo.com/gallerygosys/demo_d2.gif";
    public static final int SINGLE_PAGE_ITEM = 15;

    public static final String intent_title_tag = "title";
    public static final String intent_account_upgrade = "isUpgradeVIP";
    public static final String intent_group_param_type = "param_type_request_id";
    public static final String intent_postID_tag = "post_id";
    public static final String intent_showID_tag = "show_id";
    public static final String intent_promo_type = "promotion_news_type";
    public static final String intent_object_parcel = "obj_parcel";
    public static final String intent_roller_date_order = "r_date_order_id";
    public static final String intent_no_timetable = "no_movie_schedule";
    public static final String intent_Loc_tag = "location_array";
    public static final String intent_meals = "meal_selection_items";
    public static final String intent_seat_label = "meal_seat_label";
    public static final String intent_seatid = "meal_seat_id";
    public static final String intent_seat_plan_objects = "seat_plan_objects";     //INT OBJECT
    public static final String poster_roll_index = "poster_roll_index";            //INT CONSTANT
    public static final String poster_roll_index_timeer = "poster_roll_t_index";   //INT CONSTANT
    public static final String poster_display_style = "poster_display_style";      //INT CONSTANT

    public static final int intent_use_movieID = 31;                               //STRING CONSTANT
    public static final int intent_use_movieVersionGroupID = 32;                   //STRING CONSTANT
    public static final int intent_use_movieGroupID = 33;
    public static final int intent_use_offerID = 34;
    public static final int HOLD_SEAT_TIMEOUT = 10; //in minutes
    public static final int MOVIE_CUT_OFF_TIME = 15; //in minutes

    public static final int
            TAG_NEWPROMO = 104,
            TAG_CINEMA = 101,
            TAG_CINEMA_SEATPLAN = 104,
            TAG_VERSION_GP = 102,
            TAG_TIMEDATE = 105,
            TAG_TIMEFRAME = 103,
            TAG_TIME_DATE_CINEMA = 106,
    TRACK_RECORD_TRANSACTIONS = 107,
            TRACK_RECORD_YEARS = 108;

    public static final String
            AG_C = "AGaramondPro-Bold.otf",
            AG_B = "fonts/AGaramondPro-Bold.otf",
            AG_IB = "fonts/AGaramondPro-BoldItalic.otf",
            AG_I = "fonts/AGaramondPro-Italic.otf",
            AG_R = "fonts/AGaramondPro-Regular.otf",
            AK_B = "fonts/AkkuratStd-Bold.otf",
            AK_BI = "fonts/AkkuratStd-BoldItalic.otf",
            AK_I = "fonts/AkkuratStd-Italic.otf",
            AK_L = "fonts/AkkuratStd-Light.otf",
            AK_LI = "fonts/AkkuratStd-LightItalic.otf",
            AK_R = "fonts/AkkuratStd-Regular.otf",
            DF_HK_W3 = "fonts/DFLiHeiHK-W3.otf",
            DF_HK_W5 = "fonts/DFLiHeiHK-W5.otf",
            DF_HK_W7 = "fonts/DFLiHeiHK-W7.otf";

    public static String[] referalno = new String[]{
            "142522",
            "142498",
            "142516"
    };

    public static String[] phonenum = new String[]{
            "96463658",
            "97262002",
            "96632822"
    };
    public static final String[] emails = new String[]{

    };
}
