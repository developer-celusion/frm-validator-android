package com.celusion.library.mobiforms.controls;

import android.text.TextUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.celusion.library.mobiforms.fields.AbstractUISelectionField;
import com.celusion.library.mobiforms.valuetypes.ValueSelectionType;

/**
 * Created by swapnilnandgave on 09/04/18.
 */

public class RadioGroupField extends AbstractUISelectionField {

    private RadioGroup radioGroup;
    private RadioButton selectedRadioButton;

    public RadioGroupField(String key, RadioGroup radioGroup, String errorMsg, ValueSelectionType valueSelectionType) {
        super(key, radioGroup.getRootView(), errorMsg);
        this.radioGroup = radioGroup;
        this.valueSelectionType = valueSelectionType;
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
            if (radioButton.isChecked()) {
                selectedRadioButton = radioButton;
                break;
            }
        }
        this.radioGroup.setOnCheckedChangeListener(itemChangedListener);
    }

    private RadioGroup.OnCheckedChangeListener itemChangedListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedItemId) {
            selectedRadioButton = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        }
    };

    @Override
    public Object getValue() {
        Object object = null;
        switch (valueSelectionType) {
            case TagOnly:
                object = selectedRadioButton.getTag();
                break;
            default:
                object = selectedRadioButton.getText().toString();
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
        boolean status = (selectedRadioButton != null && !TextUtils.isEmpty(selectedRadioButton.getText()));
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
