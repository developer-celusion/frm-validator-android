package com.mobiliteam.ui.mobiforms.fields;

import android.text.InputFilter;
import android.text.TextUtils;
import android.widget.EditText;

import com.mobiliteam.ui.mobiforms.valuetypes.ValidationType;

import java.util.regex.Pattern;

/**
 * Created by swapnilnandgave on 09/04/18.
 */

public class AbstractUIInputField extends AbstractUIField {

    private EditText editText;
    protected ValidationType validationType;
    protected int controlInputType;
    protected String hint;

    public AbstractUIInputField(String key, EditText editText, ValidationType validationType, String errorMsg) {
        super(key);
        this.editText = editText;
        this.validationType = validationType;
        this.errorMsg = errorMsg;
        controlInputType = editText.getInputType();
        render();
    }

    @Override
    public void showError() {
        editText.setError(errorMsg);
    }

    @Override
    public void resetError() {
        editText.setError(null);
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public String getValue() {
        return editText.getText().toString().trim();
    }

    @Override
    public String getRawValue() {
        return editText.getText().toString();
    }

    @Override
    public boolean validate() {
        resetError();
        boolean status;
        final String text = getValue();
        if (!TextUtils.isEmpty(validationType.getPattern())) {
            Pattern pattern = Pattern.compile(validationType.getPattern());
            status = pattern.matcher(text).matches();
        } else {
            status = text.length() <= validationType.getLength();
        }
        if (!status) {
            showError();
        }
        return status;
    }

    @Override
    public String getKey() {
        return key;
    }

    private void render() {
        this.editText.setInputType(validationType.getInputType());
        this.editText.setMaxLines(validationType.getLines());
        this.editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(validationType.getLength())});
    }

    protected void resetInputType() {
        validationType.setInputType(controlInputType);
        this.editText.setInputType(validationType.getInputType());
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
        this.editText.setHint(hint);
    }
}
