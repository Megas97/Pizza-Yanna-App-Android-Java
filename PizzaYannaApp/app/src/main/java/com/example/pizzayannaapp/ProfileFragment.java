package com.example.pizzayannaapp;

import android.content.Intent;
import android.os.Bundle;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DBHelper db;

    public ProfileFragment() {
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
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        ((HideShowIconInterface)getActivity()).showBackIcon();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        EditText usernameInput = v.findViewById(R.id.usernameInput);
        EditText emailInput = v.findViewById(R.id.emailInput);
        EditText phoneInput = v.findViewById(R.id.phoneInput);
        EditText addressInput = v.findViewById(R.id.addressInput);

        final String[] loggedInUsername = ((MenuActivity) getActivity()).loggedInUsername != null ? new String[]{((MenuActivity) getActivity()).loggedInUsername} : null;
        usernameInput.setHint(loggedInUsername != null ? loggedInUsername[0] : "");
        db = new DBHelper(getActivity());
        String savedEmail = loggedInUsername != null ? db.getEmail(loggedInUsername[0]) : "";
        emailInput.setHint(savedEmail);
        String savedPhoneNumber = loggedInUsername != null ? db.getPhoneNumber(loggedInUsername[0]) : "";
        phoneInput.setHint(savedPhoneNumber);
        String savedHomeAddress = loggedInUsername != null ? db.getHomeAddress(loggedInUsername[0]) : "";
        addressInput.setHint(savedHomeAddress);

        Button updateProfileButton = v.findViewById(R.id.updateProfileButton);
        updateProfileButton.setOnClickListener(v1 -> {
            boolean updated = false;
            if (usernameInput.getText().toString().equals("") && emailInput.getText().toString().equals("") && phoneInput.getText().toString().equals("") && addressInput.getText().toString().equals("")) {
                Toast.makeText(getContext(), "Please input at least one value", Toast.LENGTH_LONG).show();
            } else {
                if (loggedInUsername != null) {
                    if (!usernameInput.getText().toString().equals("")) {
                        if (!db.isUsernameTaken(usernameInput.getText().toString())) {
                            db.updateUsername(loggedInUsername[0], usernameInput.getText().toString());
                            ((MenuActivity)getActivity()).loggedInUsername = usernameInput.getText().toString();
                            loggedInUsername[0] = usernameInput.getText().toString();
                            usernameInput.setText("");
                            usernameInput.setHint(loggedInUsername[0]);
                            updated = true;
                        } else {
                            Toast.makeText(getContext(), "This username is already taken", Toast.LENGTH_LONG).show();
                        }
                    }
                    if (!emailInput.getText().toString().equals("")) {
                        db.updateEmail(loggedInUsername[0], emailInput.getText().toString());
                        emailInput.setText("");
                        emailInput.setHint(db.getEmail(loggedInUsername[0]));
                        updated = true;
                    }
                    if (!phoneInput.getText().toString().equals("")) {
                        db.updatePhoneNumber(loggedInUsername[0], phoneInput.getText().toString());
                        phoneInput.setText("");
                        phoneInput.setHint(db.getPhoneNumber(loggedInUsername[0]));
                        updated = true;
                    }
                    if (!addressInput.getText().toString().equals("")) {
                        db.updateHomeAddress(loggedInUsername[0], addressInput.getText().toString());
                        addressInput.setText("");
                        addressInput.setHint(db.getHomeAddress(loggedInUsername[0]));
                        updated = true;
                    }
                    if (updated) {
                        Toast.makeText(getContext(), "Profile updated successfully", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        return v;
    }

    @Override
    public void onDestroy() {
        db.close();
        super.onDestroy();
    }
}