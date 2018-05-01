package com.mobiliteam.ui.mobiforms.fields;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.mobiliteam.ui.mobiforms.valuetypes.ValueSelectionType;

import java.lang.reflect.Field;

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

    public Object getFieldValue(Object sourceObject, String fieldName) {
        Field field;
        try {
            field = sourceObject.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            field = null;
        }
        Class superClass = sourceObject.getClass().getSuperclass();
        while (field == null && superClass != null) {
            try {
                field = superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                superClass = superClass.getSuperclass();
            }
        }
        if (field == null) {
            return null;
        }
        field.setAccessible(true);
        try {
            return field.get(sourceObject);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

}
