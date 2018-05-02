package com.mobiliteam.ui.mobiforms.rules;

import com.mobiliteam.ui.mobiforms.fields.AbstractUIField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by swapnilnandgave on 02/05/18.
 */

public class SumFormRule extends AbstractFormRule {

    private List<AbstractUIField> leftSideFields;
    private List<AbstractUIField> rightSideFields;

    public SumFormRule(List<AbstractUIField> leftSideFields, List<AbstractUIField> rightSideFields, EFormRuleType ruleType, String errorMsg) {
        this.leftSideFields = leftSideFields;
        this.rightSideFields = rightSideFields;
        this.errorMsg = errorMsg;
        this.ruleType = ruleType;
    }

    public SumFormRule(AbstractUIField sourceField, String errorMsg, EFormRuleType ruleType, AbstractUIField... rightSideFields) {
        this.leftSideFields.add(sourceField);
        this.rightSideFields = new ArrayList<>(Arrays.asList(rightSideFields));
        this.errorMsg = errorMsg;
        this.ruleType = ruleType;
    }

    public SumFormRule(AbstractUIField sourceField, AbstractUIField destinationField, EFormRuleType ruleType, String errorMsg) {
        this.leftSideFields.add(sourceField);
        this.rightSideFields.add(destinationField);
        this.errorMsg = errorMsg;
        this.ruleType = ruleType;
    }


    @Override
    public boolean validate(LinkedHashMap<String, Object> linkedHashMap) {
        double sourceSum = 0;
        double destinationSum = 0;
        for (AbstractUIField uiField : leftSideFields) {
            sourceSum += Double.parseDouble(linkedHashMap.get(uiField.getKey()).toString());
        }
        for (AbstractUIField uiField : rightSideFields) {
            destinationSum += Double.parseDouble(linkedHashMap.get(uiField.getKey()).toString());
        }
        return check(sourceSum, destinationSum);
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
