package org.iota.wallet.ui.activity.menu;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zyntauri.gdnet.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.madcyph3r.materialnavigationdrawer.menu.item.custom.MaterialItemCustom;

/**
 * Created by hesk on 21/8/2017.
 */

public abstract class HeaderItem extends MaterialItemCustom implements View.OnClickListener {
    @Nullable
    @BindView(R.id.elgoin_button)
    TextView display_tv;
    @Nullable
    @BindView(R.id.back_button)
    ImageView back_button;
    @Nullable
    @BindView(R.id.icon_lock)
    ImageView icon;
    @Nullable
    @BindView(R.id.area_touch)
    RelativeLayout area_touch;

    public HeaderItem(Context ctx, @LayoutRes int res) {
        super(ctx, res);
        ButterKnife.bind(this, getView());
        if (area_touch != null)
            area_touch.setOnClickListener(this);
        if (back_button != null)
            back_button.setOnClickListener(this);

    }

    protected void setText(String s) {
        if (display_tv != null)
            display_tv.setText(s);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.area_touch:
                touched_big();
                break;
            case R.id.back_button:
                back_press();
                break;
        }
    }

    protected void back_press() {
    }

    protected void touched_big() {

    }
}
