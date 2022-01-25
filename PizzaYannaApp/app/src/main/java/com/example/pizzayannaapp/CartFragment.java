package com.example.pizzayannaapp;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        View v = inflater.inflate(R.layout.fragment_cart, container, false);

        LinearLayout cartContainer = v.findViewById(R.id.cartContainer);

        ArrayList<PizzaItem> pizzaList = ((MenuActivity) getActivity()).pizzaList;
        ArrayList<DrinkItem> drinkList = ((MenuActivity) getActivity()).drinkList;
        ArrayList<DessertItem> dessertList = ((MenuActivity) getActivity()).dessertList;

        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams nameParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams orderTotalParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams emptyCartParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        //region Pizza Items
        if (pizzaList.size() > 0) {
            TextView pizzaTitle = new TextView(getActivity());
            pizzaTitle.setText(R.string.pizzaText);
            pizzaTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp22));
            titleParams.setMargins(0, pixelToDP(50), 0, 0);
            pizzaTitle.setLayoutParams(titleParams);
            pizzaTitle.setGravity(Gravity.CENTER_HORIZONTAL);
            cartContainer.addView(pizzaTitle);

            for (int i = 0; i < pizzaList.size(); i++) {
                TextView pizzaName = new TextView(getActivity());
                pizzaName.setText(pizzaList.get(i).getName());
                pizzaName.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp27));
                nameParams.setMargins(0, pixelToDP(20), 0, 0);
                pizzaName.setLayoutParams(nameParams);
                pizzaName.setGravity(Gravity.CENTER_HORIZONTAL);
                pizzaName.setTypeface(null, Typeface.ITALIC);
                cartContainer.addView(pizzaName);

                ImageView pizzaImage = new ImageView(getActivity());
                String image;
                if (pizzaList.get(i).getName().toLowerCase().contains("custom pizza #")) {
                    image = "custom_pizza";
                } else {
                    image = pizzaList.get(i).getName().toLowerCase() + "_pizza";
                }
                pizzaImage.setImageResource(getResources().getIdentifier(image, "drawable", getContext().getPackageName()));
                cartContainer.addView(pizzaImage);

                TextView pizzaSize = new TextView(getActivity());
                pizzaSize.setText("Size: " + pizzaList.get(i).getSize());
                pizzaSize.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp20));
                pizzaSize.setLayoutParams(params);
                pizzaSize.setGravity(Gravity.CENTER_HORIZONTAL);
                pizzaSize.setTypeface(null, Typeface.ITALIC);
                cartContainer.addView(pizzaSize);

                TextView pizzaPrice = new TextView(getActivity());
                pizzaPrice.setText("Single Price: " + pizzaList.get(i).getPrice() + " BGN");
                pizzaPrice.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp20));
                pizzaPrice.setLayoutParams(params);
                pizzaPrice.setGravity(Gravity.CENTER_HORIZONTAL);
                pizzaPrice.setTypeface(null, Typeface.ITALIC);
                cartContainer.addView(pizzaPrice);

                TextView pizzaQuantity = new TextView(getActivity());
                pizzaQuantity.setText("Quantity: " + pizzaList.get(i).getQuantity() + "");
                pizzaQuantity.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp20));
                pizzaQuantity.setLayoutParams(params);
                pizzaQuantity.setGravity(Gravity.CENTER_HORIZONTAL);
                pizzaQuantity.setTypeface(null, Typeface.ITALIC);
                cartContainer.addView(pizzaQuantity);

                TextView pizzaTotalPrice = new TextView(getActivity());
                pizzaTotalPrice.setText("Total Price: " + (pizzaList.get(i).getPrice() * pizzaList.get(i).getQuantity()) + " BGN");
                pizzaTotalPrice.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp20));
                pizzaTotalPrice.setLayoutParams(params);
                pizzaTotalPrice.setGravity(Gravity.CENTER_HORIZONTAL);
                pizzaTotalPrice.setTypeface(null, Typeface.ITALIC);
                cartContainer.addView(pizzaTotalPrice);

                Button removePizza = new Button(getActivity());
                removePizza.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.design_default_color_primary)));
                removePizza.setTextColor(getResources().getColor(R.color.white));
                removePizza.setText(R.string.removeButtonText);
                removePizza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp15));
                buttonParams.gravity = Gravity.CENTER_HORIZONTAL;
                removePizza.setLayoutParams(buttonParams);
                int index = i;
                removePizza.setOnClickListener(v17 -> {
                    int pizzaExists = -1;
                    for (int j = 0; j < pizzaList.size(); j++) {
                        if ((pizzaList.get(j).getName().equalsIgnoreCase(pizzaList.get(index).getName())) && (pizzaList.get(j).getSize().equalsIgnoreCase(pizzaList.get(index).getSize()))) {
                            pizzaExists = j;
                            break;
                        }
                    }
                    if (pizzaExists != -1) {
                        PizzaItem pizzaItem = pizzaList.get(pizzaExists);
                        if (pizzaItem.getQuantity() > 1) {
                            int oldQuantity = pizzaList.get(pizzaExists).getQuantity();
                            ((MenuActivity) getActivity()).pizzaList.get(pizzaExists).setQuantity(oldQuantity - 1);
                            Toast.makeText(getContext(), "Pizza quantity successfully reduced by one", Toast.LENGTH_SHORT).show();
                        } else {
                            ((MenuActivity) getActivity()).pizzaList.remove(pizzaItem);
                            Toast.makeText(getContext(), "Pizza successfully removed from cart", Toast.LENGTH_SHORT).show();
                        }
                        FragmentTransaction firstFragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        firstFragmentTransaction.detach(this).commit();
                        FragmentTransaction secondFragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        secondFragmentTransaction.attach(this).commit();
                    }
                });
                cartContainer.addView(removePizza);
            }
        }
        //endregion

        //region Drink Items
        if (drinkList.size() > 0) {
            TextView drinkTitle = new TextView(getActivity());
            drinkTitle.setText(R.string.drinkText);
            drinkTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp22));
            titleParams.setMargins(0, pixelToDP(50), 0, 0);
            drinkTitle.setLayoutParams(titleParams);
            drinkTitle.setGravity(Gravity.CENTER_HORIZONTAL);
            cartContainer.addView(drinkTitle);

            for (int i = 0; i < drinkList.size(); i++) {
                TextView drinkName = new TextView(getActivity());
                drinkName.setText(drinkList.get(i).getName());
                drinkName.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp27));
                nameParams.setMargins(0, pixelToDP(20), 0, 0);
                drinkName.setLayoutParams(nameParams);
                drinkName.setGravity(Gravity.CENTER_HORIZONTAL);
                drinkName.setTypeface(null, Typeface.ITALIC);
                cartContainer.addView(drinkName);

                ImageView drinkImage = new ImageView(getActivity());
                String image = drinkList.get(i).getName().toLowerCase().replace(" ", "_");
                drinkImage.setImageResource(getResources().getIdentifier(image, "drawable", getContext().getPackageName()));
                cartContainer.addView(drinkImage);

                TextView drinkSize = new TextView(getActivity());
                drinkSize.setText("Size: " + drinkList.get(i).getSize());
                drinkSize.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp20));
                drinkSize.setLayoutParams(params);
                drinkSize.setGravity(Gravity.CENTER_HORIZONTAL);
                drinkSize.setTypeface(null, Typeface.ITALIC);
                cartContainer.addView(drinkSize);

                TextView drinkPrice = new TextView(getActivity());
                drinkPrice.setText("Single Price: " + drinkList.get(i).getPrice() + " BGN");
                drinkPrice.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp20));
                drinkPrice.setLayoutParams(params);
                drinkPrice.setGravity(Gravity.CENTER_HORIZONTAL);
                drinkPrice.setTypeface(null, Typeface.ITALIC);
                cartContainer.addView(drinkPrice);

                TextView drinkQuantity = new TextView(getActivity());
                drinkQuantity.setText("Quantity: " + drinkList.get(i).getQuantity() + "");
                drinkQuantity.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp20));
                drinkQuantity.setLayoutParams(params);
                drinkQuantity.setGravity(Gravity.CENTER_HORIZONTAL);
                drinkQuantity.setTypeface(null, Typeface.ITALIC);
                cartContainer.addView(drinkQuantity);

                TextView drinkTotalPrice = new TextView(getActivity());
                drinkTotalPrice.setText("Total Price: " + (drinkList.get(i).getPrice() * drinkList.get(i).getQuantity()) + " BGN");
                drinkTotalPrice.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp20));
                drinkTotalPrice.setLayoutParams(params);
                drinkTotalPrice.setGravity(Gravity.CENTER_HORIZONTAL);
                drinkTotalPrice.setTypeface(null, Typeface.ITALIC);
                cartContainer.addView(drinkTotalPrice);

                Button removeDrink = new Button(getActivity());
                removeDrink.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.design_default_color_primary)));
                removeDrink.setTextColor(getResources().getColor(R.color.white));
                removeDrink.setText(R.string.removeButtonText);
                removeDrink.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp15));
                buttonParams.gravity = Gravity.CENTER_HORIZONTAL;
                removeDrink.setLayoutParams(buttonParams);
                int index = i;
                removeDrink.setOnClickListener(v17 -> {
                    int drinkExists = -1;
                    for (int j = 0; j < drinkList.size(); j++) {
                        if ((drinkList.get(j).getName().equalsIgnoreCase(drinkList.get(index).getName())) && (drinkList.get(j).getSize().equalsIgnoreCase(drinkList.get(index).getSize()))) {
                            drinkExists = j;
                            break;
                        }
                    }
                    if (drinkExists != -1) {
                        DrinkItem drinkItem = drinkList.get(drinkExists);
                        if (drinkItem.getQuantity() > 1) {
                            int oldQuantity = drinkList.get(drinkExists).getQuantity();
                            ((MenuActivity) getActivity()).drinkList.get(drinkExists).setQuantity(oldQuantity - 1);
                            Toast.makeText(getContext(), "Drink quantity successfully reduced by one", Toast.LENGTH_SHORT).show();
                        } else {
                            ((MenuActivity) getActivity()).drinkList.remove(drinkItem);
                            Toast.makeText(getContext(), "Drink successfully removed from cart", Toast.LENGTH_SHORT).show();
                        }
                        FragmentTransaction firstFragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        firstFragmentTransaction.detach(this).commit();
                        FragmentTransaction secondFragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        secondFragmentTransaction.attach(this).commit();
                    }
                });
                cartContainer.addView(removeDrink);
            }
        }
        //endregion

        //region Dessert Items
        if (dessertList.size() > 0) {
            TextView dessertTitle = new TextView(getActivity());
            dessertTitle.setText(R.string.dessertText);
            dessertTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp22));
            titleParams.setMargins(0, pixelToDP(50), 0, 0);
            dessertTitle.setLayoutParams(titleParams);
            dessertTitle.setGravity(Gravity.CENTER_HORIZONTAL);
            cartContainer.addView(dessertTitle);

            for (int i = 0; i < dessertList.size(); i++) {
                TextView dessertName = new TextView(getActivity());
                dessertName.setText(dessertList.get(i).getName());
                dessertName.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp27));
                nameParams.setMargins(0, pixelToDP(20), 0, 0);
                dessertName.setLayoutParams(nameParams);
                dessertName.setGravity(Gravity.CENTER_HORIZONTAL);
                dessertName.setTypeface(null, Typeface.ITALIC);
                cartContainer.addView(dessertName);

                ImageView dessertImage = new ImageView(getActivity());
                String image = dessertList.get(i).getName().toLowerCase().replace(" ", "_");
                dessertImage.setImageResource(getResources().getIdentifier(image, "drawable", getContext().getPackageName()));
                cartContainer.addView(dessertImage);

                TextView dessertSize = new TextView(getActivity());
                dessertSize.setText("Size: " + dessertList.get(i).getSize());
                dessertSize.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp20));
                dessertSize.setLayoutParams(params);
                dessertSize.setGravity(Gravity.CENTER_HORIZONTAL);
                dessertSize.setTypeface(null, Typeface.ITALIC);
                cartContainer.addView(dessertSize);

                TextView dessertPrice = new TextView(getActivity());
                dessertPrice.setText("Single Price: " + dessertList.get(i).getPrice() + " BGN");
                dessertPrice.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp20));
                dessertPrice.setLayoutParams(params);
                dessertPrice.setGravity(Gravity.CENTER_HORIZONTAL);
                dessertPrice.setTypeface(null, Typeface.ITALIC);
                cartContainer.addView(dessertPrice);

                TextView dessertQuantity = new TextView(getActivity());
                dessertQuantity.setText("Quantity: " + dessertList.get(i).getQuantity() + "");
                dessertQuantity.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp20));
                dessertQuantity.setLayoutParams(params);
                dessertQuantity.setGravity(Gravity.CENTER_HORIZONTAL);
                dessertQuantity.setTypeface(null, Typeface.ITALIC);
                cartContainer.addView(dessertQuantity);

                TextView dessertTotalPrice = new TextView(getActivity());
                dessertTotalPrice.setText("Total Price: " + (dessertList.get(i).getPrice() * dessertList.get(i).getQuantity()) + " BGN");
                dessertTotalPrice.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp20));
                dessertTotalPrice.setLayoutParams(params);
                dessertTotalPrice.setGravity(Gravity.CENTER_HORIZONTAL);
                dessertTotalPrice.setTypeface(null, Typeface.ITALIC);
                cartContainer.addView(dessertTotalPrice);

                Button removeDessert = new Button(getActivity());
                removeDessert.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.design_default_color_primary)));
                removeDessert.setTextColor(getResources().getColor(R.color.white));
                removeDessert.setText(R.string.removeButtonText);
                removeDessert.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp15));
                buttonParams.gravity = Gravity.CENTER_HORIZONTAL;
                removeDessert.setLayoutParams(buttonParams);
                int index = i;
                removeDessert.setOnClickListener(v17 -> {
                    int dessertExists = -1;
                    for (int j = 0; j < dessertList.size(); j++) {
                        if ((dessertList.get(j).getName().equalsIgnoreCase(dessertList.get(index).getName())) && (dessertList.get(j).getSize().equalsIgnoreCase(dessertList.get(index).getSize()))) {
                            dessertExists = j;
                            break;
                        }
                    }
                    if (dessertExists != -1) {
                        DessertItem dessertItem = dessertList.get(dessertExists);
                        if (dessertItem.getQuantity() > 1) {
                            int oldQuantity = dessertList.get(dessertExists).getQuantity();
                            ((MenuActivity) getActivity()).dessertList.get(dessertExists).setQuantity(oldQuantity - 1);
                            Toast.makeText(getContext(), "Dessert quantity successfully reduced by one", Toast.LENGTH_SHORT).show();
                        } else {
                            ((MenuActivity) getActivity()).dessertList.remove(dessertItem);
                            Toast.makeText(getContext(), "Dessert successfully removed from cart", Toast.LENGTH_SHORT).show();
                        }
                        FragmentTransaction firstFragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        firstFragmentTransaction.detach(this).commit();
                        FragmentTransaction secondFragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        secondFragmentTransaction.attach(this).commit();
                    }
                });
                cartContainer.addView(removeDessert);
            }
        }
        //endregion

        //region Order Total & Checkout Button / Cart Empty Text
        if (pizzaList.size() > 0 || drinkList.size() > 0 || dessertList.size() > 0) {
            double totalPrice = 0;
            for (PizzaItem pizza : pizzaList) {
                totalPrice += pizza.getPrice() * pizza.getQuantity();
            }
            for (DrinkItem drink : drinkList) {
                totalPrice += drink.getPrice() * drink.getQuantity();
            }
            for (DessertItem dessert : dessertList) {
                totalPrice += dessert.getPrice() * dessert.getQuantity();
            }
            TextView orderTotalText = new TextView(getActivity());
            orderTotalText.setText("Order Total: " + String.format("%.2f", totalPrice) + " BGN");
            orderTotalText.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp22));
            orderTotalParams.setMargins(0, pixelToDP(63), 0, 0);
            orderTotalText.setLayoutParams(orderTotalParams);
            orderTotalText.setGravity(Gravity.CENTER_HORIZONTAL);
            orderTotalText.setTypeface(null, Typeface.ITALIC);
            cartContainer.addView(orderTotalText);

            Button checkoutButton = new Button(getActivity());
            checkoutButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.design_default_color_primary)));
            checkoutButton.setTextColor(getResources().getColor(R.color.white));
            checkoutButton.setText(R.string.checkoutButtonText);
            checkoutButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp15));
            buttonParams.gravity = Gravity.CENTER_HORIZONTAL;
            checkoutButton.setLayoutParams(buttonParams);
            checkoutButton.setOnClickListener(v18 -> {
                ((MenuActivity) getActivity()).pizzaList = new ArrayList<>();
                ((MenuActivity) getActivity()).drinkList = new ArrayList<>();
                ((MenuActivity) getActivity()).dessertList = new ArrayList<>();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new CheckoutFragment()).commit();
            });
            cartContainer.addView(checkoutButton);
        } else {
            TextView cartEmptyText = new TextView(getActivity());
            cartEmptyText.setText(R.string.cartEmptyText);
            cartEmptyText.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp22));
            emptyCartParams.setMargins(0, pixelToDP(20), 0, 0);
            cartEmptyText.setLayoutParams(emptyCartParams);
            cartEmptyText.setGravity(Gravity.CENTER_HORIZONTAL);
            cartEmptyText.setTypeface(null, Typeface.ITALIC);
            cartContainer.addView(cartEmptyText);
        }
        //endregion

        TextView emptyView = new TextView(getActivity());
        emptyView.setText("");
        emptyView.setLayoutParams(params);
        cartContainer.addView(emptyView);

        return v;
    }

    int pixelToDP(int pixel) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) ((pixel * scale) + 0.5f);
    }
}