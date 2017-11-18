package org.iota.wallet.ui.activity.menu;

import android.content.Intent;
import android.graphics.drawable.Drawable;

import de.madcyph3r.materialnavigationdrawer.MaterialNavigationDrawer;
import de.madcyph3r.materialnavigationdrawer.menu.item.section.MaterialItemSectionActivity;

/**
 * Created by pang on 10/16/17.
 */

public class ItemAc extends MaterialItemSectionActivity {
    public ItemAc(MaterialNavigationDrawer drawer, String title, Intent intent) {
        super(drawer, title, intent);
    }

    public ItemAc(MaterialNavigationDrawer drawer, String title, Drawable icon, Intent intent) {
        super(drawer, title, icon, intent);
    }

    public ItemAc(MaterialNavigationDrawer drawer, Drawable icon, boolean iconBanner, Intent intent) {
        super(drawer, icon, iconBanner, intent);
    }
}
