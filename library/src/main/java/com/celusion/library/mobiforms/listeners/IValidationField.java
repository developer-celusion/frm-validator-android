package com.celusion.library.mobiforms.listeners;

import com.celusion.library.mobiforms.listeners.IValidationErrorField;

/**
 * Created by swapnilnandgave on 04/04/18.
 */

public interface IValidationField<T> extends IValidationErrorField {

    T getValue();
    T getRawValue();
    boolean validate();
    String getKey();

}
