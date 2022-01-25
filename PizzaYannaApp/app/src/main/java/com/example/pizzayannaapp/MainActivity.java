package com.example.pizzayannaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new LoginFragment()).addToBackStack(null).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        PrivacyPolicyFragment privacyPolicyFragment = (PrivacyPolicyFragment)getSupportFragmentManager().findFragmentByTag("privacyPolicyFragment");
        if (privacyPolicyFragment != null && privacyPolicyFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new RegisterFragment()).commit();
        } else {
            switch (item.getItemId()) {
                case android.R.id.home:
                    View view = this.getCurrentFocus();
                    if (view != null) {
                        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new LoginFragment()).addToBackStack(null).commit();
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        PrivacyPolicyFragment privacyPolicyFragment = (PrivacyPolicyFragment)getSupportFragmentManager().findFragmentByTag("privacyPolicyFragment");
        if (privacyPolicyFragment != null && privacyPolicyFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new RegisterFragment()).addToBackStack(null).commit();
        } else {
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new LoginFragment()).addToBackStack(null).commit();
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }
}