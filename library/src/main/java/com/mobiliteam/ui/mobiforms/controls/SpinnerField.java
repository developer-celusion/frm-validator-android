package com.mobiliteam.ui.mobiforms.controls;

import android.widget.AdapterView;
import android.widget.Spinner;

import com.mobiliteam.ui.mobiforms.fields.AbstractUISelectionField;
import com.mobiliteam.ui.mobiforms.valuetypes.ValueSelectionType;

/**
 * Created by swapnilnandgave on 10/04/18.
 */

public class SpinnerField extends AbstractUISelectionField {

    private Spinner spinner;

    public SpinnerField(String key, Spinner spinner, String errorMsg, ValueSelectionType valueSelectionType) {
        super(key, spinner.getRootView(), errorMsg);
        this.spinner = spinner;
        this.valueSelectionType = valueSelectionType;
    }

    @Override
    public Object getValue() {
        Object object = null;
        switch (valueSelectionType) {
            case ItemOnly:
                object = spinner.getSelectedItem();
                break;
            case IndexOnly:
                object = spinner.getSelectedItemPosition();
                break;
            default:
                object = (spinner.getSelectedItem() != null) ? spinner.getSelectedItem().toString() : spinner.getSelectedItem();
                break;
        }
        return object;
    }

    @Override
    public Object getRawValue() {
        return getValue();
    }

    @Override
    public boolean validate() {
        boolean status = (spinner.getSelectedItem() != null && spinner.getSelectedItemPosition() != AdapterView.INVALID_POSITION);
//        if(!status) {
//            showError();
//        }
        return status;
    }

    @Override
    public String getKey() {
        return key;
    }
}
