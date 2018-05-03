package com.mobiliteam.ui.mobiforms.valuetypes;

import android.text.InputType;
import android.util.Patterns;

/**
 * Created by swapnilnandgave on 04/04/18.
 */

public enum EValidationType {
    Default,
    Mobile,
    Mobile1T9,
    Email,
    PANCard,
    PersonName,
    Aadhar,
    Age,
    Pincode;

    public ValidationType get() {
        ValidationType validationType = new ValidationType(100);
        switch (this) {
            case Email:
                //local-part@domain
                //{64}@{255}
                validationType.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                validationType.setPattern(Patterns.EMAIL_ADDRESS.pattern());
                validationType.setLength(320);
                validationType.setLines(1);
                break;
            case Mobile:
                //"^[1-9][0-9]{9}$"
                validationType.setInputType(InputType.TYPE_CLASS_NUMBER);
                validationType.setPattern("^[6-9][0-9]{9}$");
                validationType.setLength(10);
                validationType.setLines(1);
                break;
            case Mobile1T9:
                //"^[1-9][0-9]{9}$"
                validationType.setInputType(InputType.TYPE_CLASS_NUMBER);
                validationType.setPattern("^[1-9][0-9]{9}$");
                validationType.setLength(10);
                validationType.setLines(1);
                break;
            case PANCard:
                validationType.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                validationType.setPattern("^[A-Z]{5}[0-9]{4}[A-Z]{1}$");
                validationType.setLength(10);
                validationType.setLines(1);
                break;
            case PersonName:
                validationType.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
                validationType.setPattern("^.{1,120}$");
                validationType.setLength(120);
                validationType.setLines(1);
                break;
            case Aadhar:
                validationType.setInputType(InputType.TYPE_CLASS_TEXT);
                //validationType.setPattern("^\\d{4}\\s\\d{4}\\s\\d{4}$");
                validationType.setPattern("^\\d{4}\\d{4}\\d{4}$");
                validationType.setLength(10);
                validationType.setLines(1);
                break;
            case Age:
                validationType.setInputType(InputType.TYPE_CLASS_NUMBER);
                validationType.setPattern("^[1-9]{1}[0-9]{0,1}$");
                validationType.setLength(2);
                validationType.setLines(1);
                break;
            case Pincode:
                //^([0-9]{6}|[0-9]{3}\s[0-9]{3})$
                validationType.setInputType(InputType.TYPE_CLASS_NUMBER);
                validationType.setPattern("^[0-9]{6}$");
                validationType.setLength(6);
                validationType.setLines(1);
                break;
            case Default:
                validationType.setInputType(InputType.TYPE_CLASS_TEXT);
                validationType.setPattern("^.{1,100}$");
                validationType.setLength(100);
                validationType.setLines(1);
                break;
        }
        return validationType;
    }

    public String errorMsg() {
        String errorMsg = null;
        switch (this) {
            case Email:
                errorMsg = "Input valid email address";
                break;
            case Mobile:
            case Mobile1T9:
                errorMsg = "Input valid mobile no";
                break;
            case PANCard:
                errorMsg = "Input valid pan number";
                break;
            case PersonName:
                errorMsg = "Input valid person name";
                break;
            case Aadhar:
                errorMsg = "Input valid aadhar no";
                break;
            case Age:
                errorMsg = "Input valid age";
                break;
            case Pincode:
                errorMsg = "Input valid pincode";
                break;
            case Default:
                errorMsg = "Input valid text";
                break;
        }
        return errorMsg;
    }

}
