package com.mobiliteam.ui.mobiforms.controls;

import android.widget.AdapterView;
import android.widget.Spinner;

import com.mobiliteam.ui.mobiforms.fields.AbstractUISelectionField;
import com.mobiliteam.ui.mobiforms.valuetypes.ValueSelectionType;

import java.lang.reflect.Field;

/**
 * Created by swapnilnandgave on 10/04/18.
 */

public class SpinnerField extends AbstractUISelectionField {

    private Spinner spinner;
    private String propertyName;

    public SpinnerField(String key, Spinner spinner, String errorMsg, ValueSelectionType valueSelectionType) {
        super(key, spinner.getRootView(), errorMsg);
        this.spinner = spinner;
        this.valueSelectionType = valueSelectionType;
    }

    public SpinnerField(String key, Spinner spinner, String errorMsg, String propertyName) {
        super(key, spinner.getRootView(), errorMsg);
        this.spinner = spinner;
        this.valueSelectionType = ValueSelectionType.PropertyOnly;
        this.propertyName = propertyName;
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
            case PropertyOnly:
                object = getFieldValue(spinner.getSelectedItem(), propertyName);
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
