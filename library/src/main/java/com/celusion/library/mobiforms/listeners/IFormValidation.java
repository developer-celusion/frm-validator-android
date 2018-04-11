package com.celusion.library.mobiforms.listeners;

import java.util.LinkedHashMap;

/**
 * Created by swapnilnandgave on 04/04/18.
 */

public interface IFormValidation {

    void errorWith(String errorMsg);
    void successWith(LinkedHashMap<String, Object> fieldValues);

}
