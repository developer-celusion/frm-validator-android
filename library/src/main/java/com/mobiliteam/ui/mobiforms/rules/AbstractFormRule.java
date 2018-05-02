package com.mobiliteam.ui.mobiforms.rules;

import com.mobiliteam.ui.mobiforms.fields.AbstractUIField;
import com.mobiliteam.ui.mobiforms.listeners.IRuleValidation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swapnilnandgave on 02/05/18.
 */

public abstract class AbstractFormRule implements IRuleValidation {

    protected String errorMsg;
    protected EFormRuleType ruleType;

    protected boolean check(double leftSide, double rightSide) {
        boolean status = false;
        switch (ruleType) {
            case Equal:
                status = (leftSide == rightSide);
                break;
            case LessThan:
                status = (leftSide < rightSide);
                break;
            case NotEqual:
                status = (leftSide != rightSide);
                break;
            case GreaterThan:
                status = (leftSide > rightSide);
                break;
            case LessThanEqual:
                status = (leftSide <= rightSide);
                break;
            case GreaterThanEqual:
                status = (leftSide >= rightSide);
                break;
        }
        return status;
    }

}
