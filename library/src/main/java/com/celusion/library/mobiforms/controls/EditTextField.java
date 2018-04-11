package com.celusion.library.mobiforms.controls;

import android.widget.EditText;

import com.celusion.library.mobiforms.valuetypes.EValidationType;
import com.celusion.library.mobiforms.valuetypes.ValidationType;
import com.celusion.library.mobiforms.fields.AbstractUIInputField;

/**
 * Created by swapnilnandgave on 04/04/18.
 */

public class EditTextField extends AbstractUIInputField {

    public EditTextField(String key, EditText editText, EValidationType validationType) {
        this(key, editText, validationType, null);
    }

    public EditTextField(String key, EditText editText, EValidationType validationType, String errorMsg) {
        this(key, editText, validationType.get(), errorMsg != null ? errorMsg : validationType.errorMsg());
    }

    public EditTextField(String key, EditText editText, ValidationType validationType, String errorMsg) {
        super(key, editText, validationType, errorMsg);
    }

}
