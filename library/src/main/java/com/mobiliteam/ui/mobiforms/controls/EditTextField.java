package com.mobiliteam.ui.mobiforms.controls;

import android.widget.EditText;

import com.mobiliteam.ui.mobiforms.valuetypes.EValidationType;
import com.mobiliteam.ui.mobiforms.valuetypes.ValidationType;
import com.mobiliteam.ui.mobiforms.fields.AbstractUIInputField;

/**
 * Created by swapnilnandgave on 04/04/18.
 */

public class EditTextField extends AbstractUIInputField {

    public EditTextField(String key, EditText editText, EValidationType validationType) {
        this(key, editText, validationType, null);
    }

    public EditTextField(String key, EditText editText, EValidationType validationType, String errorMsg) {
        this(key, editText, validationType.get(), errorMsg != null ? errorMsg : validationType.errorMsg());
        if (validationType == EValidationType.Default) {
            super.validationType.setInputType(editText.getInputType());
        }
    }

    public EditTextField(String key, EditText editText, ValidationType validationType, String errorMsg) {
        super(key, editText, validationType, errorMsg);
    }

}
