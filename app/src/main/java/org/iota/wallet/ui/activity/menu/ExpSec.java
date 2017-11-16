package org.iota.wallet.ui.activity.menu;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.widget.ImageView;

import com.zyntauri.gdnet.BuildConfig;
import com.zyntauri.gdnet.R;

import de.madcyph3r.materialnavigationdrawer.MaterialNavigationDrawer;
import de.madcyph3r.materialnavigationdrawer.listener.MaterialSectionOnClickListener;
import de.madcyph3r.materialnavigationdrawer.menu.item.section.MaterialItemSectionOnClick;

/**
 * Created by hesk on 25/9/2017.
 */

public class ExpSec extends MaterialItemSectionOnClick {

    public ExpSec(MaterialNavigationDrawer drawer, String title) {
        super(drawer, title);
    }

    public ExpSec(MaterialNavigationDrawer drawer, String title, Drawable icon) {
        super(drawer, title, icon);
    }

    public ExpSec(MaterialNavigationDrawer drawer, Drawable icon, boolean iconBanner) {
        super(drawer, icon, iconBanner);
    }

    public ExpSec(MaterialNavigationDrawer drawer, @DrawableRes final int draw, @StringRes final int res, MaterialSectionOnClickListener caller) {
        super(drawer, drawer.getString(res), drawer.getDrawable(draw));
        setOnSectionClickListener(caller);
        if (res == R.string.peek_level_btn_logout) {
            hideRightIcon();
        }
    }

    public ExpSec(MaterialNavigationDrawer drawer, String title, @StringRes final int res, MaterialSectionOnClickListener caller) {
        super(drawer, title);
        setOnSectionClickListener(caller);
        if (res == R.string.peek_level_btn_logout) {
            hideRightIcon();
        }
    }

    @Override
    protected void applyIconL(ImageView RightIconView, boolean has, Drawable icon) {
        if (has) RightIconView.setImageDrawable(icon);
    }

    @Override
    protected int getLayoutRes() {
        return isHasIcon() ? R.layout.menu_profile : R.layout.menu_peek2;
    }


    private void va() {
        StringBuilder sb = new StringBuilder();
        sb.append(BuildConfig.VERSION_NAME);
        sb.append(" (");
        sb.append(BuildConfig.VERSION_CODE);
        sb.append(")");
    }
}
