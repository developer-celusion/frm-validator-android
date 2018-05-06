package com.mobiliteam.ui.mobiforms.fields;

import com.mobiliteam.ui.mobiforms.listeners.IValidationField;

/**
 * Created by swapnilnandgave on 06/04/18.
 */

public abstract class AbstractUIField implements IValidationField {

    protected String key;
    protected String errorMsg;

    public AbstractUIField(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object obj) {
        return this.key.equalsIgnoreCase(((AbstractUIField) obj).getKey());
    }
}
