package com.celusion.wagongitexample;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.celusion.library.AwesomeConvertor;
import com.celusion.library.mobiforms.UIFormValidator;
import com.celusion.library.mobiforms.controls.CheckBoxField;
import com.celusion.library.mobiforms.controls.CheckBoxListField;
import com.celusion.library.mobiforms.controls.EditTextField;
import com.celusion.library.mobiforms.controls.RadioGroupField;
import com.celusion.library.mobiforms.controls.SpinnerField;
import com.celusion.library.mobiforms.listeners.IFormValidation;
import com.celusion.library.mobiforms.valuetypes.EValidationType;
import com.celusion.library.mobiforms.valuetypes.ListSelectionType;
import com.celusion.library.mobiforms.valuetypes.ValueSelectionType;
import com.celusion.wagongitexample.databinding.ActivityMainBinding;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MainActivity extends CommonActivity {

    ActivityMainBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupToolbar();
        setTitle("Create Form");
        bindData(dataBinding.spinnerSource);
        bindData(dataBinding.spinnerSubsource);
        bindData(dataBinding.spinnerCampaign);
        validateOwn();
    }

    private void bindData(Spinner spinner) {

        ArrayList<String> spinnerArray = new ArrayList<>();
        spinnerArray.add("Rajnikant");
        spinnerArray.add("Bhaijaan");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        spinnerArray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setSelection(AdapterView.INVALID_POSITION);

    }

    private void validateOwn() {
        final UIFormValidator formValidator = new UIFormValidator(this);

        formValidator.check(new CheckBoxListField("checkboxlisttest", dataBinding.linechecks, "Choose All Checkboxes", ListSelectionType.AllSelected, ValueSelectionType.TextOnly));
        formValidator.check(new CheckBoxField("checkboxitemtest", dataBinding.checkboxItem, "Select Check Me", ValueSelectionType.ItemOnly));
        formValidator.check(new EditTextField("personnametest", dataBinding.edittextName, EValidationType.PersonName));
        formValidator.check(new EditTextField("mobiletest", dataBinding.edittextMobile, EValidationType.Mobile));
        formValidator.check(new RadioGroupField("radiogrptest", dataBinding.radiogroupTest, "Select radio buton in radiogroup", ValueSelectionType.TagOnly));
        formValidator.check(new SpinnerField("spinnertest1", dataBinding.spinnerSource, "Select spinner item 1", ValueSelectionType.ItemOnly));
        formValidator.optional(new SpinnerField("spinnertest2", dataBinding.spinnerSubsource, "Select spinner item 2", ValueSelectionType.TextOnly));
        formValidator.optional(new SpinnerField("spinneroptionaltest", dataBinding.spinnerCampaign, "Optional message", ValueSelectionType.ItemOnly));

        dataBinding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formValidator.validate(new IFormValidation() {
                    @Override
                    public void errorWith(String msg) {
                        showMsg(msg);
                    }

                    @Override
                    public void successWith(LinkedHashMap<String, Object> fieldValues) {
                        JSONObject jsonObject = new JSONObject(fieldValues);
                        //LeadTest leadTest = new Gson().fromJson(jsonObject.toString(),LeadTest.class);
                        Log.i(this.getClass().getSimpleName(), "" + jsonObject.toString());
                    }
                });
            }
        });

    }

}
