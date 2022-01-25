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
 * Use the {@link DessertFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DessertFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DessertFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DessertFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DessertFragment newInstance(String param1, String param2) {
        DessertFragment fragment = new DessertFragment();
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
        View v = inflater.inflate(R.layout.fragment_dessert, container, false);

        //region // Choco Pie Sizes Dropdown
        Spinner pieDessertDropdown = v.findViewById(R.id.pieDessertDropdown);
        String[] items = new String[]{"Small", "Medium", "Large"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        pieDessertDropdown.setAdapter(adapter);
        pieDessertDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                TextView price = v.findViewById(R.id.pieDessertPriceText);
                if (item.toString().equals("Small")) {
                    price.setText(R.string.dessertSmallPriceText);
                } else if (item.toString().equals("Medium")) {
                    price.setText(R.string.dessertMediumPriceText);
                } else if (item.toString().equals("Large")) {
                    price.setText(R.string.dessertLargePriceText);
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        //endregion

        //region // Choco Icecream Sizes Dropdown
        Spinner icecreamDrinkDropdown = v.findViewById(R.id.icecreamDessertDropdown);
        icecreamDrinkDropdown.setAdapter(adapter);
        icecreamDrinkDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                TextView price = v.findViewById(R.id.icecreamDessertPriceText);
                if (item.toString().equals("Small")) {
                    price.setText(R.string.dessertSmallPriceText);
                } else if (item.toString().equals("Medium")) {
                    price.setText(R.string.dessertMediumPriceText);
                } else if (item.toString().equals("Large")) {
                    price.setText(R.string.dessertLargePriceText);
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        //endregion

        //region // Choco Flan Sizes Dropdown
        Spinner flanDrinkDropdown = v.findViewById(R.id.flanDessertDropdown);
        flanDrinkDropdown.setAdapter(adapter);
        flanDrinkDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                TextView price = v.findViewById(R.id.flanDessertPriceText);
                if (item.toString().equals("Small")) {
                    price.setText(R.string.dessertSmallPriceText);
                } else if (item.toString().equals("Medium")) {
                    price.setText(R.string.dessertMediumPriceText);
                } else if (item.toString().equals("Large")) {
                    price.setText(R.string.dessertLargePriceText);
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        //endregion

        //region // Choco Pie Quantity Buttons
        final int[] minimumPieQuantity = {1};

        Button pieDessertIncreaseQuantityButton = v.findViewById(R.id.pieDessertIncreaseQuantityButton);
        pieDessertIncreaseQuantityButton.setOnClickListener(v1 -> {
            if (minimumPieQuantity[0] + 1 > 9) {
                Toast.makeText(getContext(), "Quantity cannot be greater than 9!", Toast.LENGTH_LONG).show();
            } else {
                minimumPieQuantity[0] += 1;
                TextView displayQuantity = getView().findViewById(R.id.pieDessertQuantityAmountText);
                displayQuantity.setText("" + minimumPieQuantity[0]);
            }
        });

        Button pieDessertDecreaseQuantityButton = v.findViewById(R.id.pieDessertDecreaseQuantityButton);
        pieDessertDecreaseQuantityButton.setOnClickListener(v12 -> {
            if (minimumPieQuantity[0] - 1 < 1) {
                Toast.makeText(getContext(), "Quantity cannot be less than 1!", Toast.LENGTH_LONG).show();
            } else {
                minimumPieQuantity[0] -= 1;
                TextView displayQuantity = getView().findViewById(R.id.pieDessertQuantityAmountText);
                displayQuantity.setText("" + minimumPieQuantity[0]);
            }
        });
        //endregion

        //region // Choco Icecream Quantity Buttons
        final int[] minimumIcecreamQuantity = {1};

        Button icecreamDessertIncreaseQuantityButton = v.findViewById(R.id.icecreamDessertIncreaseQuantityButton);
        icecreamDessertIncreaseQuantityButton.setOnClickListener(v13 -> {
            if (minimumIcecreamQuantity[0] + 1 > 9) {
                Toast.makeText(getContext(), "Quantity cannot be greater than 9!", Toast.LENGTH_LONG).show();
            } else {
                minimumIcecreamQuantity[0] += 1;
                TextView displayQuantity = getView().findViewById(R.id.icecreamDessertQuantityAmountText);
                displayQuantity.setText("" + minimumIcecreamQuantity[0]);
            }
        });

        Button icecreamDessertDecreaseQuantityButton = v.findViewById(R.id.icecreamDessertDecreaseQuantityButton);
        icecreamDessertDecreaseQuantityButton.setOnClickListener(v14 -> {
            if (minimumIcecreamQuantity[0] - 1 < 1) {
                Toast.makeText(getContext(), "Quantity cannot be less than 1!", Toast.LENGTH_LONG).show();
            } else {
                minimumIcecreamQuantity[0] -= 1;
                TextView displayQuantity = getView().findViewById(R.id.icecreamDessertQuantityAmountText);
                displayQuantity.setText("" + minimumIcecreamQuantity[0]);
            }
        });
        //endregion

        //region // Choco Flan Quantity Buttons
        final int[] minimumFlanQuantity = {1};

        Button flanDessertIncreaseQuantityButton = v.findViewById(R.id.flanDessertIncreaseQuantityButton);
        flanDessertIncreaseQuantityButton.setOnClickListener(v15 -> {
            if (minimumFlanQuantity[0] + 1 > 9) {
                Toast.makeText(getContext(), "Quantity cannot be greater than 9!", Toast.LENGTH_LONG).show();
            } else {
                minimumFlanQuantity[0] += 1;
                TextView displayQuantity = getView().findViewById(R.id.flanDessertQuantityAmountText);
                displayQuantity.setText("" + minimumFlanQuantity[0]);
            }
        });

        Button flanDessertDecreaseQuantityButton = v.findViewById(R.id.flanDessertDecreaseQuantityButton);
        flanDessertDecreaseQuantityButton.setOnClickListener(v16 -> {
            if (minimumFlanQuantity[0] - 1 < 1) {
                Toast.makeText(getContext(), "Quantity cannot be less than 1!", Toast.LENGTH_LONG).show();
            } else {
                minimumFlanQuantity[0] -= 1;
                TextView displayQuantity = getView().findViewById(R.id.flanDessertQuantityAmountText);
                displayQuantity.setText("" + minimumFlanQuantity[0]);
            }
        });
        //endregion

        //region Choco Pie Add To Cart Button
        Button pieDessertAddToCartButton = v.findViewById(R.id.pieDessertAddToCartButton);
        pieDessertAddToCartButton.setOnClickListener(v17 -> {
            DessertItem dessertItem = new DessertItem();
            dessertItem.setName(getResources().getString(R.string.pieDessertText));
            TextView price = v.findViewById(R.id.pieDessertPriceText);
            dessertItem.setPrice(Double.parseDouble(price.getText().toString().substring(0, 5)));
            Spinner size = v.findViewById(R.id.pieDessertDropdown);
            dessertItem.setSize(size.getSelectedItem().toString());
            TextView quantity = v.findViewById(R.id.pieDessertQuantityAmountText);
            dessertItem.setQuantity(Integer.parseInt(quantity.getText().toString()));
            ArrayList<DessertItem> dessertList = ((MenuActivity) getActivity()).dessertList;
            int dessertExists = -1;
            for (int i = 0; i < dessertList.size(); i++) {
                if ((dessertList.get(i).getName().equalsIgnoreCase(dessertItem.getName())) && (dessertList.get(i).getSize().equalsIgnoreCase(dessertItem.getSize()))) {
                    dessertExists = i;
                    break;
                }
            }
            if (dessertExists != -1) {
                int oldQuantity = dessertList.get(dessertExists).getQuantity();
                ((MenuActivity) getActivity()).dessertList.get(dessertExists).setQuantity(oldQuantity + Integer.parseInt(quantity.getText().toString()));
                Toast.makeText(getContext(), "Dessert in cart successfully updated", Toast.LENGTH_SHORT).show();
            } else {
                ((MenuActivity) getActivity()).dessertList.add(dessertItem);
                Toast.makeText(getContext(), "Dessert successfully added to cart", Toast.LENGTH_SHORT).show();
            }
        });
        //endregion

        //region Choco Icecream Add To Cart Button
        Button icecreamDessertAddToCartButton = v.findViewById(R.id.icecreamDessertAddToCartButton);
        icecreamDessertAddToCartButton.setOnClickListener(v17 -> {
            DessertItem dessertItem = new DessertItem();
            dessertItem.setName(getResources().getString(R.string.icecreamDessertText));
            TextView price = v.findViewById(R.id.icecreamDessertPriceText);
            dessertItem.setPrice(Double.parseDouble(price.getText().toString().substring(0, 5)));
            Spinner size = v.findViewById(R.id.icecreamDessertDropdown);
            dessertItem.setSize(size.getSelectedItem().toString());
            TextView quantity = v.findViewById(R.id.icecreamDessertQuantityAmountText);
            dessertItem.setQuantity(Integer.parseInt(quantity.getText().toString()));
            ArrayList<DessertItem> dessertList = ((MenuActivity) getActivity()).dessertList;
            int dessertExists = -1;
            for (int i = 0; i < dessertList.size(); i++) {
                if ((dessertList.get(i).getName().equalsIgnoreCase(dessertItem.getName())) && (dessertList.get(i).getSize().equalsIgnoreCase(dessertItem.getSize()))) {
                    dessertExists = i;
                    break;
                }
            }
            if (dessertExists != -1) {
                int oldQuantity = dessertList.get(dessertExists).getQuantity();
                ((MenuActivity) getActivity()).dessertList.get(dessertExists).setQuantity(oldQuantity + Integer.parseInt(quantity.getText().toString()));
                Toast.makeText(getContext(), "Dessert in cart successfully updated", Toast.LENGTH_SHORT).show();
            } else {
                ((MenuActivity) getActivity()).dessertList.add(dessertItem);
                Toast.makeText(getContext(), "Dessert successfully added to cart", Toast.LENGTH_SHORT).show();
            }
        });
        //endregion

        //region Choco Flan Add To Cart Button
        Button flanDessertAddToCartButton = v.findViewById(R.id.flanDessertAddToCartButton);
        flanDessertAddToCartButton.setOnClickListener(v17 -> {
            DessertItem dessertItem = new DessertItem();
            dessertItem.setName(getResources().getString(R.string.flanDessertText));
            TextView price = v.findViewById(R.id.flanDessertPriceText);
            dessertItem.setPrice(Double.parseDouble(price.getText().toString().substring(0, 5)));
            Spinner size = v.findViewById(R.id.flanDessertDropdown);
            dessertItem.setSize(size.getSelectedItem().toString());
            TextView quantity = v.findViewById(R.id.flanDessertQuantityAmountText);
            dessertItem.setQuantity(Integer.parseInt(quantity.getText().toString()));
            ArrayList<DessertItem> dessertList = ((MenuActivity) getActivity()).dessertList;
            int dessertExists = -1;
            for (int i = 0; i < dessertList.size(); i++) {
                if ((dessertList.get(i).getName().equalsIgnoreCase(dessertItem.getName())) && (dessertList.get(i).getSize().equalsIgnoreCase(dessertItem.getSize()))) {
                    dessertExists = i;
                    break;
                }
            }
            if (dessertExists != -1) {
                int oldQuantity = dessertList.get(dessertExists).getQuantity();
                ((MenuActivity) getActivity()).dessertList.get(dessertExists).setQuantity(oldQuantity + Integer.parseInt(quantity.getText().toString()));
                Toast.makeText(getContext(), "Dessert in cart successfully updated", Toast.LENGTH_SHORT).show();
            } else {
                ((MenuActivity) getActivity()).dessertList.add(dessertItem);
                Toast.makeText(getContext(), "Dessert successfully added to cart", Toast.LENGTH_SHORT).show();
            }
        });
        //endregion

        return v;
    }
}