package com.example.pizzayannaapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements HideShowIconInterface {
    private BottomNavigationView bottomNavigationView;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private final int[] currentlySelectedItemID = new int[1];
    public String loggedInUsername = null;
    public ArrayList<PizzaItem> pizzaList = new ArrayList<>();
    public ArrayList<DrinkItem> drinkList = new ArrayList<>();
    public ArrayList<DessertItem> dessertList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new PizzaFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.nav_pizzas);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.nav_pizzas:
                    fragment = new PizzaFragment();
                    break;
                case R.id.nav_drinks:
                    fragment = new DrinkFragment();
                    break;
                case R.id.nav_desserts:
                    fragment = new DessertFragment();
                    break;
                case R.id.nav_cart:
                    fragment = new CartFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
            return true;
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            Fragment fragment = null;
            String fragmentName = null;
            switch (item.getItemId()) {
                case R.id.nav_promo_codes:
                    fragment = new PromoCodesFragment();
                    navigationView.getMenu().findItem(item.getItemId()).setChecked(true);
                    currentlySelectedItemID[0] = item.getItemId();
                    break;
                case R.id.nav_privacy_policy:
                    fragment = new PrivacyPolicyFragment();
                    fragmentName = "privacyPolicyFragment";
                    navigationView.getMenu().findItem(item.getItemId()).setChecked(true);
                    currentlySelectedItemID[0] = item.getItemId();
                    break;
                case R.id.nav_about_us:
                    fragment = new AboutUsFragment();
                    navigationView.getMenu().findItem(item.getItemId()).setChecked(true);
                    currentlySelectedItemID[0] = item.getItemId();
                    break;
                case R.id.nav_profile:
                    fragment = new ProfileFragment();
                    navigationView.getMenu().findItem(item.getItemId()).setChecked(true);
                    currentlySelectedItemID[0] = item.getItemId();
                    break;
                case R.id.nav_logout:
                    finish();
                    Toast.makeText(this, "You have logged out successfully", Toast.LENGTH_SHORT).show();
                    break;
            }
            if (fragment != null) {
                Fragment currentlyDisplayedFragment = getSupportFragmentManager().findFragmentByTag(fragmentName);
                if (currentlyDisplayedFragment != null && currentlyDisplayedFragment.isVisible()) {
                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                } else {
                    getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment, fragmentName).addToBackStack(null).commit();
                    bottomNavigationView.setVisibility(View.GONE);
                }
            }
            drawer.closeDrawer(GravityCompat.START);
            return true;
        });
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigationDrawerOpenText, R.string.navigationDrawerCloseText);
        toggle.setToolbarNavigationClickListener(v -> {
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            getSupportFragmentManager().popBackStack();
            showHamburgerIcon();
            navigationView.getMenu().findItem(currentlySelectedItemID[0]).setChecked(false);
            bottomNavigationView.setVisibility(View.VISIBLE);
        });
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        loggedInUsername = getIntent().getStringExtra("loggedInUsername");
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getSerializable("pizzaList") != null && bundle.getSerializable("drinkList") != null && bundle.getSerializable("dessertList") != null) {
                pizzaList = (ArrayList<PizzaItem>) bundle.getSerializable("pizzaList");
                drinkList = (ArrayList<DrinkItem>) bundle.getSerializable("drinkList");
                dessertList = (ArrayList<DessertItem>) bundle.getSerializable("dessertList");
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            getSupportFragmentManager().popBackStack();
            showHamburgerIcon();
            navigationView.getMenu().findItem(currentlySelectedItemID[0]).setChecked(false);
            bottomNavigationView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showHamburgerIcon() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toggle.setDrawerIndicatorEnabled(true);
    }

    @Override
    public void showBackIcon() {
        toggle.setDrawerIndicatorEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}