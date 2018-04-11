package com.celusion.library.mobiforms.fields;

import com.celusion.library.mobiforms.listeners.IValidationField;

/**
 * Created by swapnilnandgave on 06/04/18.
 */

public abstract class AbstractUIField implements IValidationField {

    protected String key;
    protected String errorMsg;

    public AbstractUIField(String key) {
        this.key = key;
    }

}
