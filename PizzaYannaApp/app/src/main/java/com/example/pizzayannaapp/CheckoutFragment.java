package com.example.pizzayannaapp;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckoutFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DBHelper db;

    public CheckoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckoutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckoutFragment newInstance(String param1, String param2) {
        CheckoutFragment fragment = new CheckoutFragment();
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
        View v = inflater.inflate(R.layout.fragment_checkout, container, false);

        final String[] loggedInUsername = ((MenuActivity) requireActivity()).loggedInUsername != null ? new String[]{((MenuActivity) requireActivity()).loggedInUsername} : null;
        db = new DBHelper(requireActivity());
        String savedHomeAddress = loggedInUsername != null ? db.getHomeAddress(loggedInUsername[0]) : "";
        TextView checkoutInfo = v.findViewById(R.id.checkoutInfo);
        if (savedHomeAddress != "") {
            checkoutInfo.setText(getString(R.string.orderText) + " to: \n\n" + savedHomeAddress + "\n\n");
        } else {
            checkoutInfo.setText(getString(R.string.orderText) + "\n\n");
        }
        checkoutInfo.setTypeface(null, Typeface.ITALIC);

        return v;
    }

    @Override
    public void onDestroy() {
        db.close();
        super.onDestroy();
    }
}