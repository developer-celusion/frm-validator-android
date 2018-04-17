package com.mobiliteam.ui.mobiforms.valuetypes;

import android.text.InputType;

/**
 * Created by swapnilnandgave on 05/04/18.
 */

public class ValidationType {

    private int length;
    private String pattern;
    private int lines;
    private int inputType;

    public ValidationType(int length) {
        this(length, InputType.TYPE_CLASS_TEXT);
    }

    public ValidationType(int length, int inputType) {
        this(length, inputType, null);
    }

    public ValidationType(int length, int inputType, String regex) {
        this.length = length;
        this.inputType = inputType;
        this.lines = 1;
        this.pattern = regex;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public int getInputType() {
        return inputType;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

}
