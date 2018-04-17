package com.mobiliteam.ui.mobiforms.listeners;

/**
 * Created by swapnilnandgave on 04/04/18.
 */

public interface IValidationField<T> extends IValidationErrorField {

    T getValue();
    T getRawValue();
    boolean validate();
    String getKey();

}
