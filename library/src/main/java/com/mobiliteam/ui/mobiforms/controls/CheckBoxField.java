package com.mobiliteam.ui.mobiforms.controls;

import android.widget.CheckBox;

import com.mobiliteam.ui.mobiforms.fields.AbstractUISelectionField;
import com.mobiliteam.ui.mobiforms.valuetypes.ValueSelectionType;

//import com.celusion.library.mobiforms.fields.AbstractUISelectionField;
//import com.celusion.library.mobiforms.valuetypes.ValueSelectionType;



/**
 * Created by swapnilnandgave on 09/04/18.
 */

public class CheckBoxField extends AbstractUISelectionField {

    private CheckBox checkBox;

    public CheckBoxField(String key, CheckBox checkBox, String errorMsg, ValueSelectionType valueSelectionType) {
        super(key, checkBox.getRootView(), errorMsg);
        this.valueSelectionType = valueSelectionType;
        this.checkBox = checkBox;
    }

    @Override
    public Object getValue() {
        return valueSelectionType == ValueSelectionType.TagOnly ? checkBox.getTag() : checkBox.isChecked();
    }

    @Override
    public Object getRawValue() {
        return getValue();
    }

    @Override
    public boolean validate() {
        boolean status = checkBox.isChecked();
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
