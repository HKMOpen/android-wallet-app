package org.iota.wallet.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.hkm.advancedtoolbar.V5.BeastBar;

import org.iota.wallet.BuildConfig;
import org.iota.wallet.IOTA;
import org.iota.wallet.R;
import org.iota.wallet.helper.Constants;
import org.iota.wallet.ui.activity.menu.ExpSec;
import org.iota.wallet.ui.activity.menu.HeaderItem;
import org.iota.wallet.ui.activity.menu.ItemFr;
import org.iota.wallet.ui.fragment.GenerateQRCodeFragment;
import org.iota.wallet.ui.fragment.PasswordLoginFragment;
import org.iota.wallet.ui.fragment.SeedLoginFragment;
import org.iota.wallet.ui.fragment.TangleExplorerTabFragment;
import org.iota.wallet.ui.fragment.WalletTabFragment;
import org.iota.wallet.ui.util.ComSetup;
import org.iota.wallet.var.LocaleUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.madcyph3r.materialnavigationdrawer.MaterialNavigationDrawer;
import de.madcyph3r.materialnavigationdrawer.activity.MaterialNavNoHeaderActivity;
import de.madcyph3r.materialnavigationdrawer.listener.MaterialSectionOnClickListener;
import de.madcyph3r.materialnavigationdrawer.menu.MaterialMenu;
import de.madcyph3r.materialnavigationdrawer.menu.item.MaterialMenuItem;
import de.madcyph3r.materialnavigationdrawer.menu.item.custom.MaterialItemCustom;
import de.madcyph3r.materialnavigationdrawer.menu.item.section.MaterialItemSection;
import de.madcyph3r.materialnavigationdrawer.menu.item.section.MaterialItemSectionFragment;


/**
 * Created by hesk on 16/11/2017.
 */

public class MainNewIota extends MaterialNavNoHeaderActivity {
    private SharedPreferences prefs;
    private MaterialNavigationDrawer drawer = null;
    private BeastBar mbar;
    public static int request_code_parent_menu = 1549;
    private MaterialDialog dialog;
    private String store_id;

    @Override
    public void changeToolbarColor(MaterialItemSection section) {
        if (section instanceof MaterialItemSectionFragment) {
            MaterialItemSectionFragment n = (MaterialItemSectionFragment) section;
            setActionBarOverlay(false);
            ofBeastBar(n.getFragmentTitle());
        }
        changeToolbarColor(ContextCompat.getColor(this, R.color.transparent), ContextCompat.getColor(this, R.color.colorFPrimary));
    }

    private void ofBeastBar(String id) {
        ofBeastBar(id, null);
    }

    private void ofBeastBar(String id, @Nullable Bundle cap) {
        setActionBarOverlay(false);
        if (mbar == null) return;
        /*if (id.equalsIgnoreCase("peek_level_btn_ticketing")) {
            mbar.setNewButtonLayout(panelTicketingEntrance(cap));
            mbar.hideMainLogo();
            mbar.setActionTitle(getString(R.string.peek_level_btn_ticketing));
        } else if (id.equalsIgnoreCase("peek_level_btn_comingsoon")) {
            mbar.setNewButtonLayout(panelComingSoonEntrance(cap));
            mbar.hideMainLogo();
            mbar.setActionTitle(getString(R.string.peek_level_btn_comingsoon));
        } else if (id.equalsIgnoreCase("peek_level_btn_entbuild")) {
            mbar.hideMainLogo();
            mbar.resetRightSideButtonLayout();
            mbar.setActionTitle(getString(R.string.peek_level_btn_cinemas));
        } else if (id.equalsIgnoreCase("peek_level_btn_specialprogram")) {
            mbar.hideMainLogo();
            mbar.resetRightSideButtonLayout();
            mbar.setActionTitle(getString(R.string.peek_level_btn_specialprogram));
        } else if (id.equalsIgnoreCase("peek_level_btn_redeemoffer")) {
            mbar.hideMainLogo();
            mbar.resetRightSideButtonLayout();
            mbar.setActionTitle(getString(R.string.peek_level_btn_redeemoffer));
        } else if (id.equalsIgnoreCase("peek_level_btn_aboutempc")) {
            mbar.hideMainLogo();
            mbar.resetRightSideButtonLayout();
            mbar.setActionTitle(getString(R.string.peek_level_btn_aboutempc));
        } else if (id.equalsIgnoreCase("peek_level_btn_eticket")) {
            mbar.hideMainLogo();
            mbar.resetRightSideButtonLayout();
            mbar.setActionTitle(getString(R.string.peek_level_btn_eticket));
        } else if (id.equalsIgnoreCase("peek_level_btn_emembercard")) {
            mbar.hideMainLogo();
            mbar.resetRightSideButtonLayout();
            mbar.setActionTitle(getString(R.string.peek_level_btn_emembercard));
        } else if (id.equalsIgnoreCase("peek_level_btn_membership")) {
            mbar.hideMainLogo();
            mbar.resetRightSideButtonLayout();
            mbar.setActionTitle(getString(R.string.peek_level_btn_membership));
        } else if (id.equalsIgnoreCase("peek_level_btn_promotion")) {
            mbar.hideMainLogo();
            mbar.resetRightSideButtonLayout();
            mbar.setActionTitle(getString(R.string.peek_level_btn_promotion));
        } else if (id.equalsIgnoreCase("peek_level_btn_eatndrink")) {
            mbar.hideMainLogo();
            mbar.resetRightSideButtonLayout();
            mbar.setActionTitle(getString(R.string.peek_level_btn_eatndrink));
        } else if (id.equalsIgnoreCase("-------home")) {
            home_toolbar_set();
        } else if (id.equalsIgnoreCase("peek_home")) {
            home_toolbar_set();
        } else {
            mbar.resetRightSideButtonLayout();
            mbar.showMainLogo();
        }*/

        mbar.resetRightSideButtonLayout();
        mbar.showMainLogo();

        store_id = id;
    }

    private void home_display() {
        home_display(null);
    }

    /**
     * from facebook login only
     */
    public void home_display_close_drawer() {
        home_display();
        MaterialMenu menu = parent_menu();
        drawer.loadStartFragmentFromMenu(menu);
        drawer.closeDrawer();
    }

    private void home_display(@Nullable Bundle b) {
        /**
         * phrase - 2
         */
        home_toolbar_set();
        SeedLoginFragment instance_home = new SeedLoginFragment();
        if (b != null) {
            instance_home.setArguments(b);
        }
        changeFragment(instance_home, "");
        store_id = "-------home";
    }

    private void home_toolbar_set() {
        mbar.removeToolbarBackground();
       // mbar.setActionTitle("");
        mbar.showMainLogo();
        /*
        if (core.isLogin()) {
            buttonBuilder api = new buttonBuilder();
            api.addCustomIconButtonFunction(new Runnable() {
                @Override
                public void run() {
                    setActionBarOverlay(false);
                    mbar.resetRightSideButtonLayout();
                    changeFragment(new personal_membership_card(), "peek_level_btn_membership");
                }
            }, R.drawable.icon_creditcard);


            mbar.setNewButtonLayout(api);
        } else {
            mbar.resetRightSideButtonLayout();
        }
*/
        mbar.resetRightSideButtonLayout();
        setActionBarOverlay(true);
    }

    private void ofContent(String id) {
        ofContent(id, null);
    }

    private void ofContent(String id, @Nullable Bundle cap) {
        if (id.equalsIgnoreCase("peek_level_btn_promotion")) {
            //  changeFragment(new new_promotions_bm(), "peek_level_btn_promotion");
        }/* else if (id.equalsIgnoreCase("peek_level_btn_comingsoon")) {
            if (cap == null) {
                mv_h_roller_coming instance = new mv_h_roller_coming();
                changeFragment(instance, "peek_level_btn_comingsoon");
            } else {
                switch (cap.getInt(poster_display_style)) {
                    case STYLE_GRID:
                        changeFragment(new mv_g_coming(), "peek_level_btn_comingsoon");
                        break;
                    case STYLE_STACK:
                        changeFragment(new mv_v_comingsoon(), "peek_level_btn_comingsoon");
                        break;
                    case STYLE_ROLLER:
                        mv_h_roller_coming instance = new mv_h_roller_coming();
                        if (cap.getInt(poster_roll_index) > -1) {
                            Bundle config = new Bundle();
                            config.putInt(poster_roll_index, cap.getInt(poster_roll_index));
                            config.putInt(poster_roll_index_timeer, cap.getInt(poster_roll_index_timeer));
                            instance.setArguments(config);
                        }
                        changeFragment(instance, "peek_level_btn_comingsoon");
                        break;
                    default:
                        break;
                }
            }
        } else if (id.equalsIgnoreCase("peek_level_btn_specialprogram")) {
            changeFragment(new mv_special_program(), "peek_level_btn_specialprogram");
        } else if (id.equalsIgnoreCase("peek_level_btn_ticketing")) {
            if (cap == null) {
                mv_h_roller_v3_ticketing instance = new mv_h_roller_v3_ticketing();
                changeFragment(instance, "peek_level_btn_ticketing");
            } else {
                switch (cap.getInt(poster_display_style)) {
                    case STYLE_GRID:
                        changeFragment(new mv_g_ticketing(), "peek_level_btn_ticketing");
                        break;
                    case STYLE_STACK:
                        changeFragment(new mv_v_ticketing(), "peek_level_btn_ticketing");
                        break;
                    case STYLE_ROLLER:
                        mv_h_roller_v3_ticketing instance = new mv_h_roller_v3_ticketing();
                        if (cap.getInt(poster_roll_index) > -1) {
                            Bundle config = new Bundle();
                            config.putInt(poster_roll_index, cap.getInt(poster_roll_index));
                            config.putInt(poster_roll_index_timeer, cap.getInt(poster_roll_index_timeer));
                            instance.setArguments(config);
                        }
                        changeFragment(instance, "peek_level_btn_ticketing");
                        break;
                    default:
                        break;
                }
            }
        } else if (id.equalsIgnoreCase("peek_level_btn_c_membership")) {
            changeFragment(new c_membership(), "peek_level_btn_c_membership");
        }*/ else if (id.equalsIgnoreCase("-------home")) {
            home_toolbar_set();
            if (cap == null) {
            } else {
            }
        }
    }
/*
    public final void pageDirect(material_base object, int step) {
        if (object instanceof membership_base) {
            changeFragment(object, "peek_level_btn_profile_ed");
        } else if (object instanceof c_membership) {
            changeFragment(object, "peek_level_btn_c_membership");
        }
    }*/

    private MaterialMenu parent_menu() {
        //   final Intent eat_drink_intent = new Intent(this, StepperTicketingActivity.class);
        MaterialMenu menu = new MaterialMenu();
        //phrase 1: remove the head items
        menu.add(new HeaderMembershipBar(this));
        String encSeed = prefs.getString(Constants.PREFERENCE_ENC_SEED, "");
        if (!encSeed.isEmpty() && IOTA.seed == null) {
            menu.add(new ItemFr(this, getString(R.string.menu_wallet), new PasswordLoginFragment(), "PasswordLoginFragment"));
        } else if (IOTA.seed == null) {
            menu.add(new ItemFr(this, getString(R.string.menu_wallet), new SeedLoginFragment(), "SeedLoginFragment"));
        } else {
            menu.add(new ItemFr(this, getString(R.string.menu_wallet), new WalletTabFragment(), "SeedLoginFragment"));
        }
        menu.add(new ItemFr(this, getString(R.string.menu_tangle_explorer), new TangleExplorerTabFragment(), "TangleExplorerTabFragment"));
     /*
        menu.add(getListener(R.string.peek_level_btn_movies, fn_item_from_movie));
        menu.add(getListener(R.string.peek_level_btn_cinemas, fn_item_from_cinemas));
        if (BuildConfig.DEBUG) {
            menu.add(new ItemFr(this, getString(R.string.peek_level_btn_eatndrink), CWebHybrid.feature(CWebHybrid.FEATURE_FNB), "peek_level_btn_eatndrink"));
        }
        // if (BuildConfig.DEBUG) {
        menu.add(new ItemFr(this, getString(R.string.peek_level_btn_membership), new c_membership(), "peek_level_btn_c_membership"));
        //  }

        menu.add(new ItemFr(this, getString(R.string.peek_level_btn_party), CWebHybrid.feature(FEATURE_PARTY), "peek_level_btn_party"));

        menu.add(new ItemFr(this, getString(R.string.peek_level_btn_promotion), new new_promotions_bm(), "peek_level_btn_promotion"));
        menu.add(getListener(R.string.peek_level_btn_others, fn_item_other));*/


        loadMenu(menu);
        return menu;
    }

    private void item_from_cinemas() {
        final MaterialMenu menu = new MaterialMenu();
        menu.add(BackButton());
     /*   menu.add(new ItemFr(this, getString(R.string.peek_level_btn_entbuild), new mv_cinema_movie_table(), "peek_level_btn_entbuild"));
        menu.add(new ItemFr(this, getString(R.string.peek_level_btn_aboutempc), CWebHybrid.feature(FEATURE_ABOUT), "peek_level_btn_aboutempc"));*/
        loadMenu(menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void item_other() {
        MaterialMenu menu = new MaterialMenu();
        menu.add(BackButton());
/*
        menu.add(new ItemFr(this, getString(R.string.peek_level_btn_purchaserecord), new purchase_record(), "peek_level_btn_purchaserecord"));

        CommonWebView c1 = CommonWebView.url(empPages.faq());
        menu.add(new ItemFr(this, getString(R.string.peek_level_btn_faqs), c1, "peek_level_btn_faqs"));
        CommonWebView c2 = CommonWebView.url(empPages.contact());
        menu.add(new ItemFr(this, getString(R.string.peek_level_btn_contactus), c2, "peek_level_btn_contactus"));
        //    CommonWebView c3 = CommonWebView.url(empPages.join_us());
        //     menu.add(new ItemFr(this, getString(R.string.peek_level_btn_joinus), c3, "peek_level_btn_joinus"));
        CommonWebView c4 = CommonWebView.url(empPages.privacy());
        menu.add(new ItemFr(this, getString(R.string.peek_level_btn_privacypolicy), c4, "peek_level_btn_privacypolicy"));
        // CommonWebView c5 = CommonWebView.url(empPages.personal());
        // menu.add(new ItemFr(this, getString(R.string.peek_level_btn_pics), c5, "peek_level_btn_pics"));
        CommonWebView c6 = CommonWebView.url(empPages.general_t_and_c());
        menu.add(new ItemFr(this, getString(R.string.peek_level_btn_tnc), c6, "peek_level_btn_tnc"));
        CommonWebView c7 = CommonWebView.url(empPages.disclaimer());
        menu.add(new ItemFr(this, getString(R.string.peek_level_btn_disclaimer), c7, "peek_level_btn_disclaimer"));
        menu.add(new Language_choose(this));

        if (BuildConfig.DEBUG) {
            final Intent emp_agreement = new Intent(this, EmpInstruction.class);
            final Bundle a = new Bundle();
            EMPStaticPages empPages = EMPStaticPages.instance(getApplication());
            String url = empPages.privacy();
            a.putString("url", url);
            a.putString("title", getString(R.string.peek_level_btn_privacypolicy));

            a.putInt("layout", 12);

            emp_agreement.putExtras(a);

            menu.add(new ItemAc(this, "privacy policy", emp_agreement));

            Intent emp_tnc = new Intent(this, EmpInstruction.class);
            // in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Bundle b = new Bundle();
            EMPStaticPages empPagesf = EMPStaticPages.instance(getApplication());
            String urlp = empPagesf.general_t_and_c();
            b.putString("url", urlp);
            b.putString("title", getString(R.string.transaction_completed_btn_tnc));

            b.putInt("layout", 13);

            emp_tnc.putExtras(b);

            menu.add(new ItemAc(this, "tnc", emp_tnc));

            Intent emp_ins = new Intent(this, EmpInstruction.class);
            // in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Bundle c = new Bundle();
            c.putBoolean("useVIP", true);
            emp_ins.putExtras(c);

            menu.add(new ItemAc(this, "instructions vip", emp_ins));

            Intent emp_inss = new Intent(this, EmpInstruction.class);
            // in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Bundle d = new Bundle();
            d.putBoolean("useVIP", false);
            emp_inss.putExtras(d);

            menu.add(new ItemAc(this, "instructions nonvip", emp_inss));


            Intent emp_export_image = new Intent(this, PageMemberShip.class);
            // in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            emp_export_image.putExtra("case", CASE_EXPORT);
            menu.add(new ItemAc(this, "export images", emp_export_image));


            Intent sms_test = new Intent(this, RegPrefep6Sms.class);
            // in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            //sms_test.putExtra("case", CASE_EXPORT);
            sms_test.putExtra("demo_phone_num", "+85296463658");
            sms_test.putExtra("title", getString(R.string.vipmember_card_label_memtype));
            menu.add(new ItemAc(this, "SMS test case", sms_test));

            Intent regReg = new Intent(this, PartyReg0.class);
            Bundle f = new Bundle();
            f.putString("title", "---");
            regReg.putExtras(f);
            menu.add(new ItemAc(this, "party form", regReg));
        }*/

        loadMenu(menu);
    }

    private void item_from_movie() {
        final MaterialMenu menu = new MaterialMenu();
        menu.add(BackButton());
        /*menu.add(new ItemFr(this, getString(R.string.peek_level_btn_ticketing), new mv_h_roller_v3_ticketing(), "peek_level_btn_ticketing"));
        menu.add(new ItemFr(this, getString(R.string.peek_level_btn_comingsoon), new mv_h_roller_coming(), "peek_level_btn_comingsoon"));
        menu.add(new ItemFr(this, getString(R.string.peek_level_btn_specialprogram), new mv_special_program(), "peek_level_btn_specialprogram"));*/
        loadMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      /*  if (requestCode == LoginActivity.requestCode) {
            if (resultCode == RESULT_OK) {
                display_rendering_menu();
            } else if (resultCode == CONTINUE_NO_REGISTRATION) {
                mbar.resetRightSideButtonLayout();
                setActionBarOverlay(false);
                drawer.closeDrawer();
                changeFragment(new c_membership(), "peek_level_btn_c_membership");
            }
        } else if (FacebookSdk.isFacebookRequestCode(requestCode)) {
            LoginEvent.postJustLogin(requestCode, resultCode, data);
        } else if (requestCode == TicketingDetail.requestCode) {
            ofBeastBar(store_id, data.getExtras());
            ofContent(store_id, data.getExtras());
        } else if (requestCode == EmpInstruction.MEMBERSHIP) {

        } else if (store_id != null) {
            ofBeastBar(store_id);
            ofContent(store_id);
        }*/


        ofBeastBar(store_id);
        ofContent(store_id);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        drawer = this;
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //  core = Appliance.getGDCore().getUserSession();
        // information text for the fragment
        // load first ItemFr in the menu, because there is no start position
        MaterialMenu menu = parent_menu();
        //menu.setStartIndex(1);
        drawer.loadStartFragmentFromMenu(menu);
        // set back pattern
        drawer.setBackPattern(MaterialNavigationDrawer.BACKPATTERN_BACK_ANYWHERE);
        mbar = BeastBar.withToolbar(this, getToolbar(), ComSetup.buildBeastHome(getApplication()));
        if (isBelowToolbar()) {

        }
//        empPages = EMPStaticPages.instance(getApplication());

        mbar.setBackIconFunc(new BeastBar.onButtonPressListener() {
            @Override
            public boolean onBackPress(int previousTitleSteps) {
                drawer.openDrawer();
                return false;
            }

            @Override
            public void onSearchPress() {

            }
        });
        home_display();
    }

    @Override
    public void afterInit(Bundle savedInstanceState) {

    }
/*


    private buttonBuilder panelTicketingEntrance(@Nullable Bundle cap) {
        if (cap == null) return beastBarTicketingBoxSwitcher(STYLE_ROLLER);
        return beastBarTicketingBoxSwitcher(cap.getInt(poster_display_style));
    }

    private buttonBuilder beastBarTicketingBoxSwitcher(int flag) {
        buttonBuilder api = new buttonBuilder();
        switch (flag) {
            case STYLE_GRID:
                api.addCustomIconButtonFunction(new Runnable() {
                    @Override
                    public void run() {
                        changeFragment(new mv_v_ticketing(), "");
                        mbar.setNewButtonLayout(beastBarTicketingBoxSwitcher(STYLE_STACK));
                    }
                }, R.drawable.btn_listviewview);



                api.addCustomIconButtonFunction(new Runnable() {
                    @Override
                    public void run() {
                        changeFragment(new mv_h_roller_v3_ticketing(), "");
                        mbar.setNewButtonLayout(beastBarTicketingBoxSwitcher(STYLE_ROLLER));
                    }
                }, R.drawable.btn_posterview);
                break;
            case STYLE_STACK:
                api.addCustomIconButtonFunction(new Runnable() {
                    @Override
                    public void run() {
                        changeFragment(new mv_h_roller_v3_ticketing(), "");
                        mbar.setNewButtonLayout(beastBarTicketingBoxSwitcher(STYLE_ROLLER));
                    }
                }, R.drawable.btn_posterview);
                break;
            case STYLE_ROLLER:
                api.addCustomIconButtonFunction(new Runnable() {
                    @Override
                    public void run() {
                        changeFragment(new mv_g_ticketing(), "");
                        mbar.setNewButtonLayout(beastBarTicketingBoxSwitcher(STYLE_GRID));
                    }
                }, R.drawable.btn_listview);

            default:
                break;
        }

        return api;
    }

    private buttonBuilder panelComingSoonEntrance(@Nullable Bundle cap) {
        if (cap == null) return beastBarComingSoonSwitcher(STYLE_ROLLER);
        return beastBarComingSoonSwitcher(cap.getInt(poster_display_style));
    }

    private buttonBuilder beastBarComingSoonSwitcher(int flag) {
        buttonBuilder api = new buttonBuilder();
        switch (flag) {
            case STYLE_GRID:
               api.addCustomIconButtonFunction(new Runnable() {
                    @Override
                    public void run() {
                        changeFragment(new mv_v_comingsoon(), "");
                         mbar.setNewButtonLayout(beastBarComingSoonSwitcher(STYLE_STACK));      }
                }, R.drawable.btn_listviewview);
                api.addCustomIconButtonFunction(new Runnable() {
                    @Override
                    public void run() {
                        changeFragment(new mv_h_roller_coming(), "");
                        mbar.setNewButtonLayout(beastBarComingSoonSwitcher(STYLE_ROLLER));
                    }
                }, R.drawable.btn_posterview);
                break;
            case STYLE_STACK:
                api.addCustomIconButtonFunction(new Runnable() {
                    @Override
                    public void run() {
                        changeFragment(new mv_h_roller_coming(), "");
                        mbar.setNewButtonLayout(beastBarComingSoonSwitcher(STYLE_ROLLER));
                    }
                }, R.drawable.btn_posterview);

                break;
            case STYLE_ROLLER:
                api.addCustomIconButtonFunction(new Runnable() {
                    @Override
                    public void run() {
                        changeFragment(new mv_g_coming(), "");
                        mbar.setNewButtonLayout(beastBarComingSoonSwitcher(STYLE_GRID));
                    }
                }, R.drawable.btn_listview);

            default:
                break;
        }

        return api;
    }

*/

    @Override
    protected boolean finishActivityOnNewIntent() {
        return false;
    }


    @Override
    protected int getNewIntentRequestCode(Class clazz) {
        return request_code_parent_menu;
    }


    //other support items
    public class Language_choose extends MaterialItemCustom implements View.OnClickListener {
        @BindView(R.id.chinese)
        TextView cn;
        @BindView(R.id.english)
        TextView en;

        public Language_choose(Context ctx) {
            super(ctx, R.layout.menu_langopt);
            ButterKnife.bind(this, getView());
            applybuttons();
        }

        private void applybuttons() {
            if (LocaleUtils.getLanguageIndex(getApplication()) == 0) {
                cn.setOnClickListener(this);
                en.setVisibility(View.GONE);
                cn.setVisibility(View.VISIBLE);
            } else {
                en.setOnClickListener(this);
                cn.setVisibility(View.GONE);
                en.setVisibility(View.VISIBLE);
            }
        }

        private void cont(int lang_code) {
           /* if (LocaleUtils.setLocale(getApplication(), lang_code)) {
                LocaleUtils.setBootFromLocale(getApplication(), true);
                SetupDash.startUp(InitialPage.this);
            }*/
        }


        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.chinese:
                    cont(1);
                    break;
                case R.id.english:
                    cont(0);
                    break;
                default:
                    break;
            }
        }
    }

    public class HeaderMembershipBar extends HeaderItem {

        @Override
        protected void touched_big() {
           /* if (core.isLogin()) {
                item_from_membership();
            } else {
                LoginActivity.normalLogin(InitialPage.this);
            }*/
        }

        public HeaderMembershipBar(Context ctx) {
        /*    super(ctx, core.isLogin() ? R.layout.menu_header_o : R.layout.menu_header_i);
            if (core.isLogin()) {
                setText(core.getGreetingUserName());
            }
*/
            super(ctx, R.layout.menu_header_o);
        }
    }


    public class HeaderSubMeunTop extends HeaderItem {

        public HeaderSubMeunTop(Context ctx) {
          /* only phrase two
*/
          /*  super(ctx, core.isLogin() ? R.layout.menu_back_o : R.layout.menu_back_i);
            if (core.isLogin()) {
                setText(core.getGreetingUserName());
            }*/
            //phrase 1 only
            super(ctx, R.layout.menu_back_phrase_one);
        }


        private int getLayout() {
            //return core.isLogin() ? R.layout.menu_back_o : R.layout.menu_back_i;
            return R.layout.menu_back_o;
        }

        @Override
        protected void back_press() {
            super.back_press();
            parent_menu();
        }

        @Override
        protected void touched_big() {
            parent_menu();
        }
    }

    private MaterialMenuItem BackButton() {
        return new HeaderSubMeunTop(this);
    }

    private MaterialSectionOnClickListener fn_item_from_movie = new MaterialSectionOnClickListener() {
        @Override
        public void onClick(MaterialItemSection section, View view) {
            if (BuildConfig.DEBUG)
                Toast.makeText(drawer, "this is fn_item_from_movie", Toast.LENGTH_LONG).show();
            item_from_movie();
        }
    };
    private MaterialSectionOnClickListener fn_item_from_cinemas = new MaterialSectionOnClickListener() {
        @Override
        public void onClick(MaterialItemSection section, View view) {
            if (BuildConfig.DEBUG)
                Toast.makeText(drawer, "this is fn_item_from_cinemas", Toast.LENGTH_LONG).show();
            item_from_cinemas();
        }
    };
    private MaterialSectionOnClickListener fn_item_from_membership = new MaterialSectionOnClickListener() {
        @Override
        public void onClick(MaterialItemSection section, View view) {
           /* if (core.isLogin()) {
                item_from_membership();
            } else {
                LoginActivity.normalLogin(InitialPage.this);
            }*/
        }
    };
    private MaterialSectionOnClickListener fn_item_other = new MaterialSectionOnClickListener() {
        @Override
        public void onClick(MaterialItemSection section, View view) {
            if (BuildConfig.DEBUG)
                Toast.makeText(drawer, "this is fn_item_other", Toast.LENGTH_LONG).show();
            item_other();
        }
    };

    private ExpSec getListener(@StringRes final int title, MaterialSectionOnClickListener caller) {
        return new ExpSec(this, getString(title), title, caller);
    }

    private void display_rendering_menu() {
        MaterialMenu menu = parent_menu();
        drawer.loadStartFragmentFromMenu(menu);
        home_display();
        hideProgress();
    }

    private void showProgress() {
        if (dialog != null && dialog.isShowing()) return;
        dialog = new MaterialDialog.Builder(this)
                .content("please wait")
                .progress(true, 0)
                .cancelable(false)
                .show();
    }

    private void hideProgress() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    private MaterialSectionOnClickListener fn_logout = new MaterialSectionOnClickListener() {
        @Override
        public void onClick(MaterialItemSection section, View v) {
            new MaterialDialog.Builder(MainNewIota.this).cancelable(false).title(getString(R.string.menu_logout)).content("Do u want to logout?").positiveText(getString(android.R.string.yes)).negativeText(getString(android.R.string.no)).onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull final MaterialDialog dialog, @NonNull DialogAction which) {
                   /* showProgress();
                    if (core.hasfacebookID()) {
                        LoginManager.getInstance().logOut();
                    }
                    core.logout(new ObjectCallback() {
                        @Override
                        public void onSuccess(Object map) {
                            core.initGuest(new ObjectCallback<memberLogin>() {
                                @Override
                                public void onSuccess(memberLogin map) {
                                    dialog.dismiss();
                                    display_rendering_menu();
                                }

                                @Override
                                public void onError(Throwable t, int error_code) {
                                    dialog.dismiss();
                                    display_rendering_menu();
                                }
                            });
                        }

                        @Override
                        public void onError(Throwable t, int error_code) {
                            dialog.dismiss();
                            display_rendering_menu();
                        }
                    });*/
                }
            }).onNegative(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    dialog.dismiss();
                }
            }).show();
        }
    };


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (store_id != null) {
            outState.putString("menu_fragment", store_id);
        }
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            store_id = savedInstanceState.getString("menu_fragment");
        }
    }

    private void item_from_membership() {
        MaterialMenu menu = new MaterialMenu();
        menu.add(BackButton());

       /* if (core.isVip()) {
            menu.add(new ItemFr(this, getString(R.string.peek_level_btn_profile), ContextCompat.getDrawable(this, R.drawable.icon_menu_profile), new personal_profile_vip(), "peek_level_btn_profile"));
        } else {
            menu.add(new ItemFr(this, getString(R.string.peek_level_btn_profile), ContextCompat.getDrawable(this, R.drawable.icon_menu_profile), new personal_profile_free(), "peek_level_btn_profile"));
        }

        menu.add(new ItemFr(this, getString(R.string.peek_level_btn_emembercard), ContextCompat.getDrawable(this, R.drawable.icon_menu_emembercard), new personal_membership_card(), "peek_level_btn_emembercard"));
        menu.add(new ItemFr(this, getString(R.string.peek_level_btn_eticket), ContextCompat.getDrawable(this, R.drawable.icon_menu_eticket), new personal_eticket(), "peek_level_btn_eticket"));

        if (core.isVip()) {
            menu.add(new ItemFr(this, getString(R.string.ewallet_label_title), ContextCompat.getDrawable(this, R.drawable.icon_menu_ewallet), new personal_ewallet(), "peek_level_btn_ecoupon"));
        }

        if (BuildConfig.DEBUG) {
            menu.add(new ItemFr(this, getString(R.string.peek_level_btn_redeemoffer), ContextCompat.getDrawable(this, R.drawable.icon_menu_redeemoffer), new redeem_offer(), "peek_level_btn_redeemoffer"));
        }

        menu.add(new ItemFr(this, getString(R.string.peek_level_btn_checkrecords), ContextCompat.getDrawable(this, R.drawable.icon_menu_checkrecord), new personal_check_records_history(), "peek_level_btn_checkrecords"));
           menu.add(new ItemFr(this, getString(R.string.peek_level_btn_benefits), ContextCompat.getDrawable(this, R.drawable.icon_menu_benefits), new FragmentInstruction(), "peek_level_btn_benefits"));
           menu.add(new ItemFr(this, getString(R.string.peek_level_btn_message), ContextCompat.getDrawable(this, R.drawable.icon_menu_message), new FragmentInstruction(), "peek_level_btn_message"));*/
        menu.add(new ExpSec(this, R.drawable.icon_menu_logout, R.string.menu_logout, fn_logout));
        loadMenu(menu);
    }

}
