package com.mobiliteam.ui.mobiforms.controls;

import android.text.TextUtils;
import android.widget.TextView;

import com.mobiliteam.ui.mobiforms.fields.AbstractUISelectionField;
import com.mobiliteam.ui.mobiforms.valuetypes.ValueSelectionType;

/**
 * Created by swapnilnandgave on 01/05/18.
 */

public class StaticUIField extends AbstractUISelectionField {

    private TextView textView;
    private ValueSelectionType valueSelectionType;

    public StaticUIField(String key, TextView textView, String errorMsg, ValueSelectionType valueSelectionType) {
        super(key, textView.getRootView(), errorMsg);
        this.textView = textView;
        this.valueSelectionType = valueSelectionType;
    }

    @Override
    public Object getValue() {
        Object object = null;
        switch (valueSelectionType) {
            case TagOnly:
                object = textView.getTag();
                break;
            default:
                object = "" + textView.getText().toString();
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
        return !TextUtils.isEmpty(textView.getText());
    }

    @Override
    public String getKey() {
        return key;
    }
}
