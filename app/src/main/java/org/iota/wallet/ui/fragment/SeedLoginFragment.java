/*
 * Copyright (C) 2017 IOTA Foundation
 *
 * Authors: pinpong, adrianziser, saschan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.iota.wallet.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;

import org.iota.wallet.IOTA;
import org.iota.wallet.R;
import org.iota.wallet.var.SeedValidator;
import org.iota.wallet.ui.dialog.CopySeedDialog;
import org.iota.wallet.ui.dialog.EncryptSeedDialog;
import org.iota.wallet.ui.util.utilFragment;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import jota.utils.SeedRandomGenerator;

public class SeedLoginFragment extends utilFragment {

    private static final String SEED = "seed";
    // @BindView(R.id.login_toolbar)
    // Toolbar loginToolbar;
    @BindView(R.id.seed_login_seed_text_input_layout)
    TextInputLayout seedEditTextLayout;
    @BindView(R.id.seed_login_seed_input)
    TextInputEditText seedEditText;
    @BindView(R.id.seed_login_store_seed_check_box)
    CheckBox storeSeedCheckBox;

    @Override
    protected int getLayout() {
        return R.layout.fragment_seed_login;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //  ((AppCompatActivity) getActivity()).setSupportActionBar(loginToolbar);
    }


    @OnClick(R.id.seed_login_button)
    public void onSeedLoginClick() {
        loginDialog();
    }

    @OnClick(R.id.seed_login_generate_seed)
    public void onSeedLoginGenerateSeedClick() {
        final String generatedSeed = SeedRandomGenerator.generateNewSeed();
        seedEditText.setText(generatedSeed);
        Bundle bundle = new Bundle();
        bundle.putString("generatedSeed", generatedSeed);
        CopySeedDialog dialog = new CopySeedDialog();
        dialog.setArguments(bundle);
        dialog.show(getFragmentManager(), null);
    }

    @OnEditorAction(R.id.seed_login_seed_input)
    public boolean onSeedLoginSeedInputEditorAction(int actionId, KeyEvent event) {
        if ((actionId == EditorInfo.IME_ACTION_DONE)
                || ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN))) {
            loginDialog();
        }
        return true;
    }

    private void loginDialog() {
        if (seedEditText.getText().toString().isEmpty()) {
            seedEditTextLayout.setError(getString(R.string.messages_empty_seed));
            if (seedEditTextLayout.getError() != null)
                return;
        }
        String seed = seedEditText.getText().toString();
        if (SeedValidator.isSeedValid(getActivity(), seed) == null) {
            login();
        } else {
            loginMessage(SeedValidator.isSeedValid(getActivity(), seed), (dialog, which) -> login());
        }
    }

    private void login() {
        String seed = SeedValidator.getSeed(seedEditText.getText().toString());
        if (storeSeedCheckBox.isChecked()) {
            Bundle bundle = new Bundle();
            bundle.putString("seed", seed);
            EncryptSeedDialog encryptSeedDialog = new EncryptSeedDialog();
            encryptSeedDialog.setArguments(bundle);
            encryptSeedDialog.show(getFragmentManager(), null);
        } else {
            IOTA.seed = seed.toCharArray();
            // Intent intent = new Intent(getActivity().getIntent());
            // getActivity().startActivityForResult(intent, Constants.REQUEST_CODE_LOGIN);
            getParent().home_display_close_drawer();
            getParent().pageDirect(new WalletTabFragment(), "WalletTabFragment");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SEED, seedEditText.getText().toString());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            seedEditText.setText(savedInstanceState.getString(SEED));
        }
    }
}
