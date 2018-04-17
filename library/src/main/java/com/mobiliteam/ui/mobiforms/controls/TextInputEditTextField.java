package com.mobiliteam.ui.mobiforms.controls;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.ViewParent;

import com.mobiliteam.ui.mobiforms.valuetypes.EValidationType;
import com.mobiliteam.ui.mobiforms.valuetypes.ValidationType;

/**
 * Created by swapnilnandgave on 09/04/18.
 */

public class TextInputEditTextField extends EditTextField {

    private TextInputLayout textInputLayout;

    public TextInputEditTextField(String key, TextInputEditText textInputEditText, EValidationType validationType) {
        super(key, textInputEditText, validationType);
        checkParent(textInputEditText);
    }

    public TextInputEditTextField(String key, TextInputEditText textInputEditText, EValidationType validationType, String errorMsg) {
        super(key, textInputEditText, validationType, errorMsg);
        checkParent(textInputEditText);
    }

    public TextInputEditTextField(String key, TextInputEditText textInputEditText, ValidationType validationType, String errorMsg) {
        super(key, textInputEditText, validationType, errorMsg);
        checkParent(textInputEditText);
    }

    private void checkParent(TextInputEditText textInputEditText) {
        ViewParent viewParent = textInputEditText.getParent().getParent();
        if (viewParent instanceof TextInputLayout) {
            textInputLayout = (TextInputLayout) viewParent;
        }
    }

    @Override
    public void showError() {
        if (textInputLayout != null) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(errorMsg);
        } else {
            super.showError();
        }
    }

    @Override
    public void resetError() {
        if (textInputLayout != null) {
            textInputLayout.setErrorEnabled(false);
        } else {
            super.resetError();
        }
    }

    @Override
    public void setHint(String hint) {
        if (textInputLayout != null) {
            this.textInputLayout.setHint(hint);
        } else {
            super.setHint(hint);
        }
    }

}
