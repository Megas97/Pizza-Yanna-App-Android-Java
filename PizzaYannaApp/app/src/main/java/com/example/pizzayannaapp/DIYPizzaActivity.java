package com.example.pizzayannaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class DIYPizzaActivity extends AppCompatActivity {
    boolean hasOrderedCustomPizza = false;
    Intent intent;
    static int counter = 1;
    String loggedInUsername;
    ArrayList<PizzaItem> pizzaList;
    ArrayList<DrinkItem> drinkList;
    ArrayList<DessertItem> dessertList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diy_pizza);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //region // DIY Pizza Greenery Dropdown
        ArrayList<Integer> greeneryList = new ArrayList<>();
        String[] greeneryArray = {"Tomatoes", "Mushrooms", "Parmesan", "Iceberg Salad", "Onions", "Pickles"};
        TextView greeneryDropdown = findViewById(R.id.greeneryDropdown);
        boolean[] selectedGreenery = new boolean[greeneryArray.length];

        greeneryDropdown.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(DIYPizzaActivity.this);
            builder.setTitle("Select Greenery");
            builder.setCancelable(false);

            builder.setMultiChoiceItems(greeneryArray, selectedGreenery, (dialogInterface, i, b) -> {
                if (b) {
                    greeneryList.add(i);
                    Collections.sort(greeneryList);
                } else {
                    greeneryList.remove(i);
                }
            });

            builder.setPositiveButton("OK", (dialogInterface, i) -> {
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < greeneryList.size(); j++) {
                    stringBuilder.append(greeneryArray[greeneryList.get(j)]);
                    if (j != greeneryList.size() - 1) {
                        stringBuilder.append(", ");
                    }
                }
                greeneryDropdown.setText(stringBuilder.toString());
            });

            builder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());
            builder.setNeutralButton("Clear All", (dialogInterface, i) -> {
                for (int j = 0; j < selectedGreenery.length; j++) {
                    selectedGreenery[j] = false;
                    greeneryList.clear();
                    greeneryDropdown.setText("");
                }
            });
            builder.show();
        });
        //endregion

        //region // DIY Pizza Meats Dropdown
        ArrayList<Integer> meatsList = new ArrayList<>();
        String[] meatsArray = {"Bacon", "Tuna", "Pepperoni", "Chicken", "Smoked Ham", "Pork"};
        TextView meatsDropdown = findViewById(R.id.meatsDropdown);
        boolean[] selectedMeats = new boolean[meatsArray.length];

        meatsDropdown.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(DIYPizzaActivity.this);
            builder.setTitle("Select Meats");
            builder.setCancelable(false);

            builder.setMultiChoiceItems(meatsArray, selectedMeats, (dialogInterface, i, b) -> {
                if (b) {
                    meatsList.add(i);
                    Collections.sort(meatsList);
                } else {
                    meatsList.remove(i);
                }
            });

            builder.setPositiveButton("OK", (dialogInterface, i) -> {
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < meatsList.size(); j++) {
                    stringBuilder.append(meatsArray[meatsList.get(j)]);
                    if (j != meatsList.size() - 1) {
                        stringBuilder.append(", ");
                    }
                }
                meatsDropdown.setText(stringBuilder.toString());
            });

            builder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());
            builder.setNeutralButton("Clear All", (dialogInterface, i) -> {
                for (int j = 0; j < selectedMeats.length; j++) {
                    selectedMeats[j] = false;
                    meatsList.clear();
                    meatsDropdown.setText("");
                }
            });
            builder.show();
        });
        //endregion

        //region // DIY Pizza Sauces Dropdown
        ArrayList<Integer> saucesList = new ArrayList<>();
        String[] saucesArray = {"BBQ", "Sweet Onion", "Wasabi \uD83C\uDF36", "Mustard", "Ketchup", "Burger"};
        TextView saucesDropdown = findViewById(R.id.saucesDropdown);
        boolean[] selectedSauces = new boolean[saucesArray.length];

        saucesDropdown.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(DIYPizzaActivity.this);
            builder.setTitle("Select Sauces");
            builder.setCancelable(false);

            builder.setMultiChoiceItems(saucesArray, selectedSauces, (dialogInterface, i, b) -> {
                if (b) {
                    saucesList.add(i);
                    Collections.sort(saucesList);
                } else {
                    saucesList.remove(i);
                }
            });

            builder.setPositiveButton("OK", (dialogInterface, i) -> {
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < saucesList.size(); j++) {
                    stringBuilder.append(saucesArray[saucesList.get(j)]);
                    if (j != saucesList.size() - 1) {
                        stringBuilder.append(", ");
                    }
                }
                saucesDropdown.setText(stringBuilder.toString());
            });

            builder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());
            builder.setNeutralButton("Clear All", (dialogInterface, i) -> {
                for (int j = 0; j < selectedSauces.length; j++) {
                    selectedSauces[j] = false;
                    saucesList.clear();
                    saucesDropdown.setText("");
                }
            });
            builder.show();
        });
        //endregion

        //region // DIY Pizza Addons Dropdown
        ArrayList<Integer> addonsList = new ArrayList<>();
        String[] addonsArray = {"Salt", "Curry", "Chilly Powder", "Marjoram", "Pineapple", "Peppers"};
        TextView addonsDropdown = findViewById(R.id.addonsDropdown);
        boolean[] selectedAddons = new boolean[addonsArray.length];

        addonsDropdown.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(DIYPizzaActivity.this);
            builder.setTitle("Select Addons");
            builder.setCancelable(false);

            builder.setMultiChoiceItems(addonsArray, selectedAddons, (dialogInterface, i, b) -> {
                if (b) {
                    addonsList.add(i);
                    Collections.sort(addonsList);
                } else {
                    addonsList.remove(i);
                }
            });

            builder.setPositiveButton("OK", (dialogInterface, i) -> {
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < addonsList.size(); j++) {
                    stringBuilder.append(addonsArray[addonsList.get(j)]);
                    if (j != addonsList.size() - 1) {
                        stringBuilder.append(", ");
                    }
                }
                addonsDropdown.setText(stringBuilder.toString());
            });

            builder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());
            builder.setNeutralButton("Clear All", (dialogInterface, i) -> {
                for (int j = 0; j < selectedAddons.length; j++) {
                    selectedAddons[j] = false;
                    addonsList.clear();
                    addonsDropdown.setText("");
                }
            });
            builder.show();
        });
        //endregion

        //region // DIY Pizza Sizes Dropdown
        Spinner dIYPizzaDropdown = findViewById(R.id.dIYPizzaDropdown);
        String[] items = new String[]{"Small", "Medium", "Large"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dIYPizzaDropdown.setAdapter(adapter);
        dIYPizzaDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                TextView price = findViewById(R.id.dIYPizzaPriceText);
                if (item.toString().equals("Small")) {
                    price.setText(R.string.pizzaSmallPriceText);
                } else if (item.toString().equals("Medium")) {
                    price.setText(R.string.pizzaMediumPriceText);
                } else if (item.toString().equals("Large")) {
                    price.setText(R.string.pizzaLargePriceText);
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        //endregion

        //region // DIY Pizza Quantity Buttons
        final int[] minimumDIYPizzaQuantity = {1};

        Button dIYPizzaIncreaseQuantityButton = findViewById(R.id.dIYPizzaIncreaseQuantityButton);
        dIYPizzaIncreaseQuantityButton.setOnClickListener(v1 -> {
            if (minimumDIYPizzaQuantity[0] + 1 > 9) {
                Toast.makeText(getApplicationContext(), "Quantity cannot be greater than 9!", Toast.LENGTH_LONG).show();
            } else {
                minimumDIYPizzaQuantity[0] += 1;
                TextView displayQuantity = findViewById(R.id.dIYPizzaQuantityAmountText);
                displayQuantity.setText("" + minimumDIYPizzaQuantity[0]);
            }
        });

        Button dIYPizzaDencreaseQuantityButton = findViewById(R.id.dIYPizzaDecreaseQuantityButton);
        dIYPizzaDencreaseQuantityButton.setOnClickListener(v12 -> {
            if (minimumDIYPizzaQuantity[0] - 1 < 1) {
                Toast.makeText(getApplicationContext(), "Quantity cannot be less than 1!", Toast.LENGTH_LONG).show();
            } else {
                minimumDIYPizzaQuantity[0] -= 1;
                TextView displayQuantity = findViewById(R.id.dIYPizzaQuantityAmountText);
                displayQuantity.setText("" + minimumDIYPizzaQuantity[0]);
            }
        });
        //endregion

        //region DIY Pizza Add To Cart Button
        loggedInUsername = getIntent().getStringExtra("loggedInUsername");
        Bundle bundle = getIntent().getExtras();
        pizzaList = (ArrayList<PizzaItem>) bundle.getSerializable("pizzaList");
        drinkList = (ArrayList<DrinkItem>) bundle.getSerializable("drinkList");
        dessertList = (ArrayList<DessertItem>) bundle.getSerializable("dessertList");
        Button dIYPizzaAddToCartButton = findViewById(R.id.dIYPizzaAddToCartButton);
        dIYPizzaAddToCartButton.setOnClickListener(v13 -> {
            String name = "Custom Pizza #" + counter++;
            TextView price = findViewById(R.id.dIYPizzaPriceText);
            Spinner size = findViewById(R.id.dIYPizzaDropdown);
            TextView quantity = findViewById(R.id.dIYPizzaQuantityAmountText);
            intent = new Intent(this, MenuActivity.class);
            PizzaItem pizzaItem = new PizzaItem();
            pizzaItem.setName(name);
            pizzaItem.setPrice(Double.parseDouble(price.getText().toString().substring(0, 5)));
            pizzaItem.setSize(size.getSelectedItem().toString());
            pizzaItem.setQuantity(Integer.parseInt(quantity.getText().toString()));
            pizzaList.add(pizzaItem);
            Bundle bndl = new Bundle();
            bndl.putSerializable("pizzaList", (Serializable) pizzaList);
            bndl.putSerializable("drinkList", (Serializable) drinkList);
            bndl.putSerializable("dessertList", (Serializable) dessertList);
            intent.putExtras(bndl);
            intent.putExtra("loggedInUsername", loggedInUsername);
            Toast.makeText(this, "Custom pizza successfully added to cart", Toast.LENGTH_SHORT).show();
            hasOrderedCustomPizza = true;
        });
        //endregion
    }

    @Override
    public void onBackPressed() {
        if (hasOrderedCustomPizza) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (hasOrderedCustomPizza) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}