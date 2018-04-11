package com.celusion.library.mobiforms.fields;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.celusion.library.mobiforms.valuetypes.ValueSelectionType;

/**
 * Created by swapnilnandgave on 09/04/18.
 */

public abstract class AbstractUISelectionField extends AbstractUIField {

    protected Snackbar snackbar;
    protected ValueSelectionType valueSelectionType;

    public AbstractUISelectionField(String key, View rootView, String errorMsg) {
        super(key);
        snackbar = Snackbar.make(
                rootView.findViewById(android.R.id.content),
                errorMsg, Snackbar.LENGTH_LONG);
        valueSelectionType = ValueSelectionType.TextOnly;
        this.errorMsg = errorMsg;
    }

    @Override
    public void showError() {
        snackbar.show();
    }

    @Override
    public void resetError() {
        snackbar.dismiss();
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
