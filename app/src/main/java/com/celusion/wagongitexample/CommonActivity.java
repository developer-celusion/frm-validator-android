package com.celusion.wagongitexample;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by swapnilnandgave on 30/03/18.
 */

public class CommonActivity extends AppCompatActivity {

    protected Toolbar toolbar;
    protected ProgressDialog progressDialog = null;
    protected Snackbar snackbar;

    protected void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showTitle("");
    }

    protected void showBack() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    protected void showTitle(final String title) {
        getSupportActionBar().setTitle(title);
    }

    protected void showSubtitle(final String subtitle) {
        getSupportActionBar().setSubtitle(subtitle);
    }

    public void hideKeypad() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case android.R.id.home:
                makeFinish();
                break;
            default:
                break;
        }
        return true;
    }

    public void makeFinish() {
        hideKeypad();
        setResult(RESULT_CANCELED);
        finish();
    }

    public void allowFinish() {
        hideKeypad();
        setResult(RESULT_OK);
        finish();
    }

    public void allowFinish(Intent intent) {
        setResult(RESULT_OK, intent);
        finish();
    }

    protected void setMaterialBackAsClose() {
        toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
    }

    public void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    public void showMsg(String text) {
        Snackbar.make(
                getWindow().getDecorView().findViewById(android.R.id.content),
                text, Snackbar.LENGTH_LONG).show();
    }

    public void shortMsg(String text) {
        Snackbar.make(
                getWindow().getDecorView().findViewById(android.R.id.content),
                text, Snackbar.LENGTH_SHORT).show();
    }

    public void keepMsg(String text) {
        snackbar = Snackbar.make(
                getWindow().getDecorView().findViewById(android.R.id.content),
                text, Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
    }

    public void hideKeepMsg() {
        if(snackbar != null) {
            snackbar.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        dismissProgressDialog();
        super.onDestroy();
    }



}
