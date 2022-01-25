package com.example.pizzayannaapp;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DBHelper db;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        String text = getString(R.string.termsOfUseText);
        String link = "terms of use";
        setClickableString(link, text, v.findViewById(R.id.registerCheckbox));

        Button registerButton = v.findViewById(R.id.registerButton);
        registerButton.setOnClickListener(v1 -> {
            CheckBox checkbox = getView().findViewById(R.id.registerCheckbox);
            if (checkbox.isChecked()) {
                EditText usernameInput = v.findViewById(R.id.usernameInput);
                EditText emailInput = v.findViewById(R.id.emailInput);
                EditText phoneInput = v.findViewById(R.id.phoneInput);
                EditText addressInput = v.findViewById(R.id.addressInput);
                EditText passwordInput = v.findViewById(R.id.passwordInput);
                EditText confirmPasswordInput = v.findViewById(R.id.confirmPasswordInput);
                if (usernameInput.getText().toString().equals("") || emailInput.getText().toString().equals("") || phoneInput.getText().toString().equals("") || addressInput.getText().toString().equals("") || passwordInput.getText().toString().equals("") || confirmPasswordInput.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please input all values", Toast.LENGTH_LONG).show();
                } else {
                    if (!passwordInput.getText().toString().equals(confirmPasswordInput.getText().toString())) {
                        Toast.makeText(getContext(), "The two passwords do not match", Toast.LENGTH_LONG).show();
                    } else {
                        db = new DBHelper(getActivity());
                        boolean isUsernameTaken = db.isUsernameTaken(usernameInput.getText().toString());
                        if (!isUsernameTaken) {
                            boolean isUserRegistered = db.registerUser(usernameInput.getText().toString(), emailInput.getText().toString(), phoneInput.getText().toString(), addressInput.getText().toString(), passwordInput.getText().toString());
                            if (isUserRegistered) {
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new LoginFragment()).commit();
                                Toast.makeText(getContext(), "You have registered successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "This username is already taken", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            } else {
                Toast.makeText(getContext(), "Please read & accept the terms of use first", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

    public void setClickableString(String clickableValue, String wholeValue, TextView yourTextView) {
        String value = wholeValue;
        SpannableString spannableString = new SpannableString(value);
        int startIndex = value.indexOf(clickableValue);
        int endIndex = startIndex + clickableValue.length();
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }

            @Override
            public void onClick(View widget) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new PrivacyPolicyFragment(), "privacyPolicyFragment").addToBackStack(null).commit();
            }
        }, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        yourTextView.setText(spannableString);
        yourTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onDestroy() {
        db.close();
        super.onDestroy();
    }
}