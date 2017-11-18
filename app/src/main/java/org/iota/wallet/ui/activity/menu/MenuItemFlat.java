package org.iota.wallet.ui.activity.menu;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import org.iota.wallet.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.madcyph3r.materialnavigationdrawer.menu.item.custom.MaterialItemCustom;

/**
 * Created by hesk on 24/9/2017.
 */

public abstract class MenuItemFlat extends MaterialItemCustom implements View.OnClickListener {
    @BindView(R.id.amd_layout_text)
    TextView item_display;
    @BindView(R.id.amd_layout_right_icon)
    ImageView arrow_a;
    @BindView(R.id.amd_layout_notification)
    TextView notification_num;
    @BindView(R.id.amd_layout_touch_area)
    View touch_base_area;

    public MenuItemFlat(Context ctx, @StringRes final int display_txt, boolean hassub) {
        super(ctx, R.layout.menu_item_peek_menu);
        ButterKnife.bind(this, getView());
        if (hassub) arrow_a.setVisibility(View.VISIBLE);
        touch_base_area.setOnClickListener(this);
        item_display.setText(ctx.getString(display_txt));
    }

    public void setNotificaionNumber(int h) {
        if (h > 0) {
            notification_num.setVisibility(View.VISIBLE);
            notification_num.setText(String.valueOf(h));
        } else {
            notification_num.setVisibility(View.GONE);
        }
    }

}
