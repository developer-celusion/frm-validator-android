package com.mobiliteam.ui.mobiforms.controls;

import android.text.TextUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mobiliteam.ui.mobiforms.fields.AbstractUISelectionField;
import com.mobiliteam.ui.mobiforms.valuetypes.ValueSelectionType;

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
        //this.radioGroup.setOnCheckedChangeListener(itemChangedListener);
    }

//    private RadioGroup.OnCheckedChangeListener itemChangedListener = new RadioGroup.OnCheckedChangeListener() {
//        @Override
//        public void onCheckedChanged(RadioGroup radioGroup, int checkedItemId) {
//            selectedRadioButton = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
//        }
//    };

    @Override
    public Object getValue() {
        Object object = null;
        if (selectedRadioButton != null) {
            switch (valueSelectionType) {
                case TagOnly:
                    object = selectedRadioButton.getTag();
                    break;
                default:
                    object = selectedRadioButton.getText().toString();
                    break;
            }
        }
        return object;
    }

    @Override
    public Object getRawValue() {
        return getValue();
    }

    @Override
    public boolean validate() {
        checkSelected();
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

    private void checkSelected() {
        final int selected = this.radioGroup.getCheckedRadioButtonId();
        if (selected != -1) {
            selectedRadioButton = this.radioGroup.findViewById(selected);
        } else {
            selectedRadioButton = null;
        }
    }

}
