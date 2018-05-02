package com.mobiliteam.ui.mobiforms.rules;

import com.mobiliteam.ui.mobiforms.fields.AbstractUIField;

import java.util.LinkedHashMap;

/**
 * Created by swapnilnandgave on 02/05/18.
 */

public class FormRule extends AbstractFormRule {

    private AbstractUIField leftSideField;
    private AbstractUIField rightSideField;

    public FormRule(AbstractUIField leftSideField, AbstractUIField rightSideField, EFormRuleType ruleType, String errorMsg) {
        this.leftSideField = leftSideField;
        this.rightSideField = rightSideField;
        this.ruleType = ruleType;
        this.errorMsg = errorMsg;
    }

    @Override
    public boolean validate(LinkedHashMap<String, Object> linkedHashMap) {
        double leftSide = Double.parseDouble(linkedHashMap.get(leftSideField.getKey()).toString());
        double rightSide = Double.parseDouble(linkedHashMap.get(rightSideField.getKey()).toString());
        return check(leftSide, rightSide);
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
