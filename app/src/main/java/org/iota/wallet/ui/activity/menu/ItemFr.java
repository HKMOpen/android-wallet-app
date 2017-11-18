package org.iota.wallet.ui.activity.menu;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;


import org.iota.wallet.R;

import de.madcyph3r.materialnavigationdrawer.MaterialNavigationDrawer;
import de.madcyph3r.materialnavigationdrawer.menu.item.section.MaterialItemSectionFragment;

/**
 * Created by hesk on 25/9/2017.
 */

public class ItemFr extends MaterialItemSectionFragment {


    public ItemFr(MaterialNavigationDrawer drawer, String title, Object o, String fragmentTitle) {
        super(drawer, title, o, fragmentTitle);
    }

    public ItemFr(MaterialNavigationDrawer drawer, String title, Drawable icon, Object o, String fragmentTitle) {
        super(drawer, title, icon, o, fragmentTitle);
    }

    public ItemFr(MaterialNavigationDrawer drawer, Drawable icon, boolean iconBanner, Object o, String fragmentTitle) {
        super(drawer, icon, iconBanner, o, fragmentTitle);
    }


    @Override
    protected void applyIconL(ImageView rrrr, boolean has, Drawable icon) {
        rrrr.setImageDrawable(icon);
    }

    @Override
    protected int getLayoutRes() {
        return isHasIcon() ? R.layout.menu_profile : R.layout.menu_item_peek_menu;
    }

}
