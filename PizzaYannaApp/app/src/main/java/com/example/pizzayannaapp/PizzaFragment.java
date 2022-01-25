package com.example.pizzayannaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PizzaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PizzaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PizzaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PizzaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PizzaFragment newInstance(String param1, String param2) {
        PizzaFragment fragment = new PizzaFragment();
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
        View v = inflater.inflate(R.layout.fragment_pizza, container, false);

        //region // DIY Image Link
        ImageView dIYImage = v.findViewById(R.id.dIYPizzaImage);
        dIYImage.setOnClickListener(v1 -> {
            Intent intent = new Intent(getContext(), DIYPizzaActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("pizzaList", (Serializable) ((MenuActivity) getActivity()).pizzaList);
            bundle.putSerializable("drinkList", (Serializable) ((MenuActivity) getActivity()).drinkList);
            bundle.putSerializable("dessertList", (Serializable) ((MenuActivity) getActivity()).dessertList);
            intent.putExtras(bundle);
            intent.putExtra("loggedInUsername", ((MenuActivity) getActivity()).loggedInUsername);
            startActivity(intent);
        });
        //endregion

        //region // Barbecue Sizes Dropdown
        Spinner barbecuePizzaDropdown = v.findViewById(R.id.barbecuePizzaDropdown);
        String[] items = new String[]{"Small", "Medium", "Large"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        barbecuePizzaDropdown.setAdapter(adapter);
        barbecuePizzaDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                TextView price = v.findViewById(R.id.barbecuePizzaPriceText);
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

        //region // Pepperoni Sizes Dropdown
        Spinner pepperoniPizzaDropdown = v.findViewById(R.id.pepperoniPizzaDropdown);
        pepperoniPizzaDropdown.setAdapter(adapter);
        pepperoniPizzaDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                TextView price = v.findViewById(R.id.pepperoniPizzaPriceText);
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

        //region // Barbecue Quantity Buttons
        final int[] minimumBarbecueQuantity = {1};

        Button barbecuePizzaIncreaseQuantityButton = v.findViewById(R.id.barbecuePizzaIncreaseQuantityButton);
        barbecuePizzaIncreaseQuantityButton.setOnClickListener(v12 -> {
            if (minimumBarbecueQuantity[0] + 1 > 9) {
                Toast.makeText(getContext(), "Quantity cannot be greater than 9!", Toast.LENGTH_LONG).show();
            } else {
                minimumBarbecueQuantity[0] += 1;
                TextView displayQuantity = getView().findViewById(R.id.barbecuePizzaQuantityAmountText);
                displayQuantity.setText("" + minimumBarbecueQuantity[0]);
            }
        });

        Button barbecuePizzaDecreaseQuantityButton = v.findViewById(R.id.barbecuePizzaDecreaseQuantityButton);
        barbecuePizzaDecreaseQuantityButton.setOnClickListener(v13 -> {
            if (minimumBarbecueQuantity[0] - 1 < 1) {
                Toast.makeText(getContext(), "Quantity cannot be less than 1!", Toast.LENGTH_LONG).show();
            } else {
                minimumBarbecueQuantity[0] -= 1;
                TextView displayQuantity = getView().findViewById(R.id.barbecuePizzaQuantityAmountText);
                displayQuantity.setText("" + minimumBarbecueQuantity[0]);
            }
        });
        //endregion

        //region // Pepperoni Quantity Buttons
        final int[] minimumPepperoniQuantity = {1};

        Button pepperoniPizzaIncreaseQuantityButton = v.findViewById(R.id.pepperoniPizzaIncreaseQuantityButton);
        pepperoniPizzaIncreaseQuantityButton.setOnClickListener(v14 -> {
            if (minimumPepperoniQuantity[0] + 1 > 9) {
                Toast.makeText(getContext(), "Quantity cannot be greater than 9!", Toast.LENGTH_LONG).show();
            } else {
                minimumPepperoniQuantity[0] += 1;
                TextView displayQuantity = getView().findViewById(R.id.pepperoniPizzaQuantityAmountText);
                displayQuantity.setText("" + minimumPepperoniQuantity[0]);
            }
        });

        Button pepperoniPizzaDecreaseQuantityButton = v.findViewById(R.id.pepperoniPizzaDecreaseQuantityButton);
        pepperoniPizzaDecreaseQuantityButton.setOnClickListener(v15 -> {
            if (minimumPepperoniQuantity[0] - 1 < 1) {
                Toast.makeText(getContext(), "Quantity cannot be less than 1!", Toast.LENGTH_LONG).show();
            } else {
                minimumPepperoniQuantity[0] -= 1;
                TextView displayQuantity = getView().findViewById(R.id.pepperoniPizzaQuantityAmountText);
                displayQuantity.setText("" + minimumPepperoniQuantity[0]);
            }
        });
        //endregion

        //region Barbecue Add To Cart Button
        Button barbecuePizzaAddToCartButton = v.findViewById(R.id.barbecuePizzaAddToCartButton);
        barbecuePizzaAddToCartButton.setOnClickListener(v16 -> {
            PizzaItem pizzaItem = new PizzaItem();
            pizzaItem.setName(getResources().getString(R.string.barbecuePizzaText));
            TextView price = v.findViewById(R.id.barbecuePizzaPriceText);
            pizzaItem.setPrice(Double.parseDouble(price.getText().toString().substring(0, 5)));
            Spinner size = v.findViewById(R.id.barbecuePizzaDropdown);
            pizzaItem.setSize(size.getSelectedItem().toString());
            TextView quantity = v.findViewById(R.id.barbecuePizzaQuantityAmountText);
            pizzaItem.setQuantity(Integer.parseInt(quantity.getText().toString()));
            ArrayList<PizzaItem> pizzaList = ((MenuActivity) getActivity()).pizzaList;
            int pizzaExists = -1;
            for (int i = 0; i < pizzaList.size(); i++) {
                if ((pizzaList.get(i).getName().equalsIgnoreCase(pizzaItem.getName())) && (pizzaList.get(i).getSize().equalsIgnoreCase(pizzaItem.getSize()))) {
                    pizzaExists = i;
                    break;
                }
            }
            if (pizzaExists != -1) {
                int oldQuantity = pizzaList.get(pizzaExists).getQuantity();
                ((MenuActivity) getActivity()).pizzaList.get(pizzaExists).setQuantity(oldQuantity + Integer.parseInt(quantity.getText().toString()));
                Toast.makeText(getContext(), "Pizza in cart successfully updated", Toast.LENGTH_SHORT).show();
            } else {
                ((MenuActivity) getActivity()).pizzaList.add(pizzaItem);
                Toast.makeText(getContext(), "Pizza successfully added to cart", Toast.LENGTH_SHORT).show();
            }
        });
        //endregion

        //region Pepperoni Add To Cart Button
        Button pepperoniPizzaAddToCartButton = v.findViewById(R.id.pepperoniPizzaAddToCartButton);
        pepperoniPizzaAddToCartButton.setOnClickListener(v17 -> {
            PizzaItem pizzaItem = new PizzaItem();
            pizzaItem.setName(getResources().getString(R.string.pepperoniPizzaText));
            TextView price = v.findViewById(R.id.pepperoniPizzaPriceText);
            pizzaItem.setPrice(Double.parseDouble(price.getText().toString().substring(0, 5)));
            Spinner size = v.findViewById(R.id.pepperoniPizzaDropdown);
            pizzaItem.setSize(size.getSelectedItem().toString());
            TextView quantity = v.findViewById(R.id.pepperoniPizzaQuantityAmountText);
            pizzaItem.setQuantity(Integer.parseInt(quantity.getText().toString()));
            ArrayList<PizzaItem> pizzaList = ((MenuActivity) getActivity()).pizzaList;
            int pizzaExists = -1;
            for (int i = 0; i < pizzaList.size(); i++) {
                if ((pizzaList.get(i).getName().equalsIgnoreCase(pizzaItem.getName())) && (pizzaList.get(i).getSize().equalsIgnoreCase(pizzaItem.getSize()))) {
                    pizzaExists = i;
                    break;
                }
            }
            if (pizzaExists != -1) {
                int oldQuantity = pizzaList.get(pizzaExists).getQuantity();
                ((MenuActivity) getActivity()).pizzaList.get(pizzaExists).setQuantity(oldQuantity + Integer.parseInt(quantity.getText().toString()));
                Toast.makeText(getContext(), "Pizza in cart successfully updated", Toast.LENGTH_SHORT).show();
            } else {
                ((MenuActivity) getActivity()).pizzaList.add(pizzaItem);
                Toast.makeText(getContext(), "Pizza successfully added to cart", Toast.LENGTH_SHORT).show();
            }
        });
        //endregion

        return v;
    }
}