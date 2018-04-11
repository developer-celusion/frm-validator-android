package com.celusion.library.mobiforms.controls;

import android.view.ViewGroup;
import android.widget.CheckBox;

import com.celusion.library.mobiforms.fields.AbstractUISelectionField;
import com.celusion.library.mobiforms.valuetypes.ListSelectionType;
import com.celusion.library.mobiforms.valuetypes.ValueSelectionType;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by swapnilnandgave on 10/04/18.
 */

public class CheckBoxListField extends AbstractUISelectionField {

    private int requiredCheckItems;
    private ViewGroup container;

    public CheckBoxListField(String key, ViewGroup container, String errorMsg, ListSelectionType selectionType, ValueSelectionType valueSelectionType) {
        super(key, container.getRootView(), errorMsg);
        if (isValid(selectionType)) {
            this.container = container;
            this.valueSelectionType = valueSelectionType;
            switch (selectionType) {
                case AllSelected:
                    requiredCheckItems = container.getChildCount();
                    break;
                case AtleastOneSelected:
                case MultipleSelection:
                    requiredCheckItems = 1;
                    break;
            }
        } else {
            throw new RuntimeException("ListSelectionType should be All, Atleast or multiple");
        }
    }

    public CheckBoxListField(String key, ViewGroup container, String errorMsg, final int requiredCheckItems, ValueSelectionType valueSelectionType) {
        this(key, container, errorMsg, ListSelectionType.MultipleSelection, valueSelectionType);
        if (requiredCheckItems <= container.getChildCount()) {
            this.requiredCheckItems = requiredCheckItems;
        } else {
            throw new RuntimeException("Container childs and check items are not matching");
        }
    }

    @Override
    public Object getValue() {
        LinkedHashMap<String,Object> values = new LinkedHashMap<>();
        for (int i = 0; i < container.getChildCount(); i++) {
            if (container.getChildAt(i) instanceof CheckBox && ((CheckBox) container.getChildAt(i)).isChecked()) {
                CheckBox checkBox = ((CheckBox) container.getChildAt(i));
                values.put(checkBox.getText().toString(),valueSelectionType == ValueSelectionType.TagOnly ? checkBox.getTag() : checkBox.isChecked());
            }
        }
        return values;
    }

    @Override
    public Object getRawValue() {
        return getValue();
    }

    @Override
    public boolean validate() {
        List<Boolean> statuses = new ArrayList<>();
        for (int i = 0; i < container.getChildCount(); i++) {
            if (container.getChildAt(i) instanceof CheckBox && ((CheckBox) container.getChildAt(i)).isChecked()) {
                statuses.add(true);
            }
        }
        return (statuses.size() >= requiredCheckItems);
    }

    @Override
    public String getKey() {
        return key;
    }

    private boolean isValid(ListSelectionType selectionType) {
        return selectionType == ListSelectionType.AllSelected || selectionType == ListSelectionType.MultipleSelection || selectionType == ListSelectionType.AtleastOneSelected;
    }

}
