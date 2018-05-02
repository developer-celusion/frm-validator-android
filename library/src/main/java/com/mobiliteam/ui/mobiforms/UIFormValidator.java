package com.mobiliteam.ui.mobiforms;

import android.content.Context;

import com.mobiliteam.ui.mobiforms.controls.CheckBoxField;
import com.mobiliteam.ui.mobiforms.controls.CheckBoxListField;
import com.mobiliteam.ui.mobiforms.controls.EditTextField;
import com.mobiliteam.ui.mobiforms.controls.RadioGroupField;
import com.mobiliteam.ui.mobiforms.controls.SpinnerField;
import com.mobiliteam.ui.mobiforms.controls.StaticUIField;
import com.mobiliteam.ui.mobiforms.controls.TextInputEditTextField;
import com.mobiliteam.ui.mobiforms.fields.AbstractUIField;
import com.mobiliteam.ui.mobiforms.listeners.IFormValidation;
import com.mobiliteam.ui.mobiforms.listeners.IRuleValidation;
import com.mobiliteam.ui.mobiforms.listeners.IValidationField;
import com.mobiliteam.ui.mobiforms.rules.AbstractFormRule;
import com.mobiliteam.ui.mobiforms.rules.FormRule;
import com.mobiliteam.ui.mobiforms.rules.SumFormRule;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by swapnilnandgave on 04/04/18.
 */

public class UIFormValidator {

    private Context context;
    private List<AbstractUIField> validationFields;
    private List<Integer> optionalIndexes;
    private List<AbstractFormRule> validationRules;

    public UIFormValidator(Context context) {
        this.context = context;
        validationFields = new ArrayList<>();
        optionalIndexes = new ArrayList<>();
        validationRules = new ArrayList<>();
    }

    public UIFormValidator check(EditTextField inputField) {
        validationFields.add(inputField);
        return this;
    }

    public UIFormValidator optional(EditTextField inputField) {
        check(inputField);
        setAsOptional();
        return this;
    }

    public UIFormValidator check(TextInputEditTextField inputField) {
        validationFields.add(inputField);
        return this;
    }

    public UIFormValidator optional(TextInputEditTextField inputField) {
        check(inputField);
        setAsOptional();
        return this;
    }

    public UIFormValidator check(RadioGroupField selectionField) {
        validationFields.add(selectionField);
        return this;
    }

    public UIFormValidator optional(RadioGroupField selectionField) {
        check(selectionField);
        setAsOptional();
        return this;
    }

    public UIFormValidator check(CheckBoxField selectionField) {
        validationFields.add(selectionField);
        return this;
    }

    public UIFormValidator optional(CheckBoxField selectionField) {
        check(selectionField);
        setAsOptional();
        return this;
    }

    public UIFormValidator check(SpinnerField selectionField) {
        validationFields.add(selectionField);
        return this;
    }

    public UIFormValidator optional(SpinnerField selectionField) {
        check(selectionField);
        setAsOptional();
        return this;
    }

    public UIFormValidator check(CheckBoxListField selectionField) {
        validationFields.add(selectionField);
        return this;
    }

    public UIFormValidator optional(CheckBoxListField selectionField) {
        check(selectionField);
        setAsOptional();
        return this;
    }

    public UIFormValidator check(StaticUIField staticUIField) {
        validationFields.add(staticUIField);
        return this;
    }

    public UIFormValidator optional(StaticUIField staticUIField) {
        check(staticUIField);
        setAsOptional();
        return this;
    }

    public UIFormValidator addRule(FormRule formRule) {
        validationRules.add(formRule);
        return this;
    }

    public UIFormValidator addRule(SumFormRule formRule) {
        validationRules.add(formRule);
        return this;
    }

    private void setAsOptional() {
        optionalIndexes.add(validationFields.size() - 1);
    }

    public void validate(IFormValidation formValidation) {
        List<Boolean> statuses = new ArrayList<>();
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        String errorMsg = null;
        for (int i = 0; i < validationFields.size(); i++) {
            IValidationField uiField = validationFields.get(i);
            boolean valid;
            if (isOptional(i)) {
                valid = true;
            } else {
                valid = uiField.validate();
            }
            if (!valid) {
                statuses.add(valid);
                if (errorMsg == null) {
                    errorMsg = uiField.getErrorMsg();
                }
            } else {
                //setField(item,uiField.getKey(),uiField.getValue());
                linkedHashMap.put(uiField.getKey(), uiField.getValue());
            }
        }
        if (statuses.size() > 0) {
            formValidation.errorWith(errorMsg);
        } else {
            for (int i = 0; i < validationRules.size(); i++) {
                IRuleValidation ruleValidation = validationRules.get(i);
                boolean valid = ruleValidation.validate(linkedHashMap);
                if (!valid) {
                    statuses.add(valid);
                    if (errorMsg == null) {
                        errorMsg = ruleValidation.getErrorMsg();
                    }
                }
            }
            if (statuses.size() > 0) {
                formValidation.errorWith(errorMsg);
            } else {
                formValidation.successWith(linkedHashMap);
            }
        }
    }

    private boolean isOptional(int i) {
        boolean status = false;
        for (Integer index : optionalIndexes) {
            status = (index.intValue() == i);
            if (status) {
                break;
            }
        }
        return status;
    }

    public boolean setField(Object targetObject, String fieldName, Object fieldValue) {
        Field field;
        try {
            field = targetObject.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            field = null;
        }
        Class superClass = targetObject.getClass().getSuperclass();
        while (field == null && superClass != null) {
            try {
                field = superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                superClass = superClass.getSuperclass();
            }
        }
        if (field == null) {
            return false;
        }
        field.setAccessible(true);
        try {
            field.set(targetObject, fieldValue);
            return true;
        } catch (IllegalAccessException e) {
            return false;
        }
    }

}
