package com.mobiliteam.ui.mobiforms.listeners;

import java.util.LinkedHashMap;

/**
 * Created by swapnilnandgave on 02/05/18.
 */

public interface IRuleValidation {

    boolean validate(LinkedHashMap<String, Object> linkedHashMap);
    String getErrorMsg();

}
