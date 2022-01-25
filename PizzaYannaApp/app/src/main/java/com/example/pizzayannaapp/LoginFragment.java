package com.example.pizzayannaapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DBHelper db;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        EditText usernameInput = v.findViewById(R.id.usernameInput);
        EditText passwordInput = v.findViewById(R.id.passwordInput);

        Button loginButton = v.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v1 -> {
            if (usernameInput.getText().toString().equals("") || passwordInput.getText().toString().equals("")) {
                Toast.makeText(getContext(), "Please input all values", Toast.LENGTH_LONG).show();
            } else {
                db = new DBHelper(getActivity());
                boolean isUserRegistered = db.isUserRegistered(usernameInput.getText().toString(), passwordInput.getText().toString());
                if (isUserRegistered) {
                    new android.os.Handler(Looper.getMainLooper()).postDelayed(() -> {
                        usernameInput.setText("");
                        passwordInput.setText("");
                    },500);
                    Toast.makeText(getContext(), "You have logged in successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getContext(), MenuActivity.class);
                    intent.putExtra("loggedInUsername", usernameInput.getText().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "Invalid credentials", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button googleButton = v.findViewById(R.id.googleButton);
        googleButton.setOnClickListener(v12 -> {
            new android.os.Handler(Looper.getMainLooper()).postDelayed(() -> {
                usernameInput.setText("");
                passwordInput.setText("");
            },500);
            startActivity(new Intent(getContext(), MenuActivity.class));
        });

        Button facebookButton = v.findViewById(R.id.facebookButton);
        facebookButton.setOnClickListener(v13 -> {
            new android.os.Handler(Looper.getMainLooper()).postDelayed(() -> {
                usernameInput.setText("");
                passwordInput.setText("");
            },500);
            startActivity(new Intent(getContext(), MenuActivity.class));
        });

        Button forgotPasswordButton = v.findViewById(R.id.forgotPasswordButton);
        forgotPasswordButton.setOnClickListener(v14 -> getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new ForgotPasswordFragment()).addToBackStack(null).commit());

        Button registerButton = v.findViewById(R.id.registerButton);
        registerButton.setOnClickListener(v15 -> getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new RegisterFragment()).addToBackStack(null).commit());

        return v;
    }

    @Override
    public void onDestroy() {
        db.close();
        super.onDestroy();
    }
}