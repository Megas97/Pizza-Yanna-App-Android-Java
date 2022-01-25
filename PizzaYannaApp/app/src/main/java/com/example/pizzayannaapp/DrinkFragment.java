package com.example.pizzayannaapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DrinkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrinkFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DrinkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DrinkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DrinkFragment newInstance(String param1, String param2) {
        DrinkFragment fragment = new DrinkFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_drink, container, false);

        //region // Coca Cola Sizes Dropdown
        Spinner colaDrinkDropdown = v.findViewById(R.id.colaDrinkDropdown);
        String[] items = new String[]{"Small", "Medium", "Large"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        colaDrinkDropdown.setAdapter(adapter);
        colaDrinkDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                TextView price = v.findViewById(R.id.colaDrinkPriceText);
                if (item.toString().equals("Small")) {
                    price.setText(R.string.drinkSmallPriceText);
                } else if (item.toString().equals("Medium")) {
                    price.setText(R.string.drinkMediumPriceText);
                } else if (item.toString().equals("Large")) {
                    price.setText(R.string.drinkLargePriceText);
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        //endregion

        //region // Sprite Sizes Dropdown
        Spinner spriteDrinkDropdown = v.findViewById(R.id.spriteDrinkDropdown);
        spriteDrinkDropdown.setAdapter(adapter);
        spriteDrinkDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                TextView price = v.findViewById(R.id.spriteDrinkPriceText);
                if (item.toString().equals("Small")) {
                    price.setText(R.string.drinkSmallPriceText);
                } else if (item.toString().equals("Medium")) {
                    price.setText(R.string.drinkMediumPriceText);
                } else if (item.toString().equals("Large")) {
                    price.setText(R.string.drinkLargePriceText);
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        //endregion

        //region // Fanta Sizes Dropdown
        Spinner fantaDrinkDropdown = v.findViewById(R.id.fantaDrinkDropdown);
        fantaDrinkDropdown.setAdapter(adapter);
        fantaDrinkDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                TextView price = v.findViewById(R.id.fantaDrinkPriceText);
                if (item.toString().equals("Small")) {
                    price.setText(R.string.drinkSmallPriceText);
                } else if (item.toString().equals("Medium")) {
                    price.setText(R.string.drinkMediumPriceText);
                } else if (item.toString().equals("Large")) {
                    price.setText(R.string.drinkLargePriceText);
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        //endregion

        //region // Coca Cola Quantity Buttons
        final int[] minimumColaQuantity = {1};

        Button colaDrinkIncreaseQuantityButton = v.findViewById(R.id.colaDrinkIncreaseQuantityButton);
        colaDrinkIncreaseQuantityButton.setOnClickListener(v1 -> {
            if (minimumColaQuantity[0] + 1 > 9) {
                Toast.makeText(getContext(), "Quantity cannot be greater than 9!", Toast.LENGTH_LONG).show();
            } else {
                minimumColaQuantity[0] += 1;
                TextView displayQuantity = getView().findViewById(R.id.colaDrinkQuantityAmountText);
                displayQuantity.setText("" + minimumColaQuantity[0]);
            }
        });

        Button colaDrinkDecreaseQuantityButton = v.findViewById(R.id.colaDrinkDecreaseQuantityButton);
        colaDrinkDecreaseQuantityButton.setOnClickListener(v12 -> {
            if (minimumColaQuantity[0] - 1 < 1) {
                Toast.makeText(getContext(), "Quantity cannot be less than 1!", Toast.LENGTH_LONG).show();
            } else {
                minimumColaQuantity[0] -= 1;
                TextView displayQuantity = getView().findViewById(R.id.colaDrinkQuantityAmountText);
                displayQuantity.setText("" + minimumColaQuantity[0]);
            }
        });
        //endregion

        //region // Sprite Quantity Buttons
        final int[] minimumSpriteQuantity = {1};

        Button spriteDrinkIncreaseQuantityButton = v.findViewById(R.id.spriteDrinkIncreaseQuantityButton);
        spriteDrinkIncreaseQuantityButton.setOnClickListener(v13 -> {
            if (minimumSpriteQuantity[0] + 1 > 9) {
                Toast.makeText(getContext(), "Quantity cannot be greater than 9!", Toast.LENGTH_LONG).show();
            } else {
                minimumSpriteQuantity[0] += 1;
                TextView displayQuantity = getView().findViewById(R.id.spriteDrinkQuantityAmountText);
                displayQuantity.setText("" + minimumSpriteQuantity[0]);
            }
        });

        Button spriteDrinkDecreaseQuantityButton = v.findViewById(R.id.spriteDrinkDecreaseQuantityButton);
        spriteDrinkDecreaseQuantityButton.setOnClickListener(v14 -> {
            if (minimumSpriteQuantity[0] - 1 < 1) {
                Toast.makeText(getContext(), "Quantity cannot be less than 1!", Toast.LENGTH_LONG).show();
            } else {
                minimumSpriteQuantity[0] -= 1;
                TextView displayQuantity = getView().findViewById(R.id.spriteDrinkQuantityAmountText);
                displayQuantity.setText("" + minimumSpriteQuantity[0]);
            }
        });
        //endregion

        //region // Fanta Quantity Buttons
        final int[] minimumFantaQuantity = {1};

        Button fantaDrinkIncreaseQuantityButton = v.findViewById(R.id.fantaDrinkIncreaseQuantityButton);
        fantaDrinkIncreaseQuantityButton.setOnClickListener(v15 -> {
            if (minimumFantaQuantity[0] + 1 > 9) {
                Toast.makeText(getContext(), "Quantity cannot be greater than 9!", Toast.LENGTH_LONG).show();
            } else {
                minimumFantaQuantity[0] += 1;
                TextView displayQuantity = getView().findViewById(R.id.fantaDrinkQuantityAmountText);
                displayQuantity.setText("" + minimumFantaQuantity[0]);
            }
        });

        Button fantaDrinkDecreaseQuantityButton = v.findViewById(R.id.fantaDrinkDecreaseQuantityButton);
        fantaDrinkDecreaseQuantityButton.setOnClickListener(v16 -> {
            if (minimumFantaQuantity[0] - 1 < 1) {
                Toast.makeText(getContext(), "Quantity cannot be less than 1!", Toast.LENGTH_LONG).show();
            } else {
                minimumFantaQuantity[0] -= 1;
                TextView displayQuantity = getView().findViewById(R.id.fantaDrinkQuantityAmountText);
                displayQuantity.setText("" + minimumFantaQuantity[0]);
            }
        });
        //endregion

        //region Coca Cola Add To Cart Button
        Button colaDrinkAddToCartButton = v.findViewById(R.id.colaDrinkAddToCartButton);
        colaDrinkAddToCartButton.setOnClickListener(v17 -> {
            DrinkItem drinkItem = new DrinkItem();
            drinkItem.setName(getResources().getString(R.string.colaDrinkText));
            TextView price = v.findViewById(R.id.colaDrinkPriceText);
            drinkItem.setPrice(Double.parseDouble(price.getText().toString().substring(0, 5)));
            Spinner size = v.findViewById(R.id.colaDrinkDropdown);
            drinkItem.setSize(size.getSelectedItem().toString());
            TextView quantity = v.findViewById(R.id.colaDrinkQuantityAmountText);
            drinkItem.setQuantity(Integer.parseInt(quantity.getText().toString()));
            ArrayList<DrinkItem> drinkList = ((MenuActivity) getActivity()).drinkList;
            int drinkExists = -1;
            for (int i = 0; i < drinkList.size(); i++) {
                if ((drinkList.get(i).getName().equalsIgnoreCase(drinkItem.getName())) && (drinkList.get(i).getSize().equalsIgnoreCase(drinkItem.getSize()))) {
                    drinkExists = i;
                    break;
                }
            }
            if (drinkExists != -1) {
                int oldQuantity = drinkList.get(drinkExists).getQuantity();
                ((MenuActivity) getActivity()).drinkList.get(drinkExists).setQuantity(oldQuantity + Integer.parseInt(quantity.getText().toString()));
                Toast.makeText(getContext(), "Drink in cart successfully updated", Toast.LENGTH_SHORT).show();
            } else {
                ((MenuActivity) getActivity()).drinkList.add(drinkItem);
                Toast.makeText(getContext(), "Drink successfully added to cart", Toast.LENGTH_SHORT).show();
            }
        });
        //endregion

        //region Sprite Add To Cart Button
        Button spriteDrinkAddToCartButton = v.findViewById(R.id.spriteDrinkAddToCartButton);
        spriteDrinkAddToCartButton.setOnClickListener(v17 -> {
            DrinkItem drinkItem = new DrinkItem();
            drinkItem.setName(getResources().getString(R.string.spriteDrinkText));
            TextView price = v.findViewById(R.id.spriteDrinkPriceText);
            drinkItem.setPrice(Double.parseDouble(price.getText().toString().substring(0, 5)));
            Spinner size = v.findViewById(R.id.spriteDrinkDropdown);
            drinkItem.setSize(size.getSelectedItem().toString());
            TextView quantity = v.findViewById(R.id.spriteDrinkQuantityAmountText);
            drinkItem.setQuantity(Integer.parseInt(quantity.getText().toString()));
            ArrayList<DrinkItem> drinkList = ((MenuActivity) getActivity()).drinkList;
            int drinkExists = -1;
            for (int i = 0; i < drinkList.size(); i++) {
                if ((drinkList.get(i).getName().equalsIgnoreCase(drinkItem.getName())) && (drinkList.get(i).getSize().equalsIgnoreCase(drinkItem.getSize()))) {
                    drinkExists = i;
                    break;
                }
            }
            if (drinkExists != -1) {
                int oldQuantity = drinkList.get(drinkExists).getQuantity();
                ((MenuActivity) getActivity()).drinkList.get(drinkExists).setQuantity(oldQuantity + Integer.parseInt(quantity.getText().toString()));
                Toast.makeText(getContext(), "Drink in cart successfully updated", Toast.LENGTH_SHORT).show();
            } else {
                ((MenuActivity) getActivity()).drinkList.add(drinkItem);
                Toast.makeText(getContext(), "Drink successfully added to cart", Toast.LENGTH_SHORT).show();
            }
        });
        //endregion

        //region Fanta Add To Cart Button
        Button fantaDrinkAddToCartButton = v.findViewById(R.id.fantaDrinkAddToCartButton);
        fantaDrinkAddToCartButton.setOnClickListener(v17 -> {
            DrinkItem drinkItem = new DrinkItem();
            drinkItem.setName(getResources().getString(R.string.fantaDrinkText));
            TextView price = v.findViewById(R.id.fantaDrinkPriceText);
            drinkItem.setPrice(Double.parseDouble(price.getText().toString().substring(0, 5)));
            Spinner size = v.findViewById(R.id.fantaDrinkDropdown);
            drinkItem.setSize(size.getSelectedItem().toString());
            TextView quantity = v.findViewById(R.id.fantaDrinkQuantityAmountText);
            drinkItem.setQuantity(Integer.parseInt(quantity.getText().toString()));
            ArrayList<DrinkItem> drinkList = ((MenuActivity) getActivity()).drinkList;
            int drinkExists = -1;
            for (int i = 0; i < drinkList.size(); i++) {
                if ((drinkList.get(i).getName().equalsIgnoreCase(drinkItem.getName())) && (drinkList.get(i).getSize().equalsIgnoreCase(drinkItem.getSize()))) {
                    drinkExists = i;
                    break;
                }
            }
            if (drinkExists != -1) {
                int oldQuantity = drinkList.get(drinkExists).getQuantity();
                ((MenuActivity) getActivity()).drinkList.get(drinkExists).setQuantity(oldQuantity + Integer.parseInt(quantity.getText().toString()));
                Toast.makeText(getContext(), "Drink in cart successfully updated", Toast.LENGTH_SHORT).show();
            } else {
                ((MenuActivity) getActivity()).drinkList.add(drinkItem);
                Toast.makeText(getContext(), "Drink successfully added to cart", Toast.LENGTH_SHORT).show();
            }
        });
        //endregion

        return v;
    }
}