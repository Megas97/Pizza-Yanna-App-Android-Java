package com.example.pizzayannaapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ForgotPasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForgotPasswordFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DBHelper db;

    public ForgotPasswordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ForgotPasswordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ForgotPasswordFragment newInstance(String param1, String param2) {
        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
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
        View v = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        EditText codeInput = v.findViewById(R.id.forgotPasswordCodeInput);
        EditText usernameInput = v.findViewById(R.id.usernameInput);
        EditText newPasswordInput = v.findViewById(R.id.newPasswordInput);
        Button changePasswordButton = v.findViewById(R.id.changePasswordButton);
        changePasswordButton.setOnClickListener(v1 -> {
            if (codeInput.getText().toString().equals("") || usernameInput.getText().toString().equals("") || newPasswordInput.getText().toString().equals("")) {
                Toast.makeText(getContext(), "Please input all values", Toast.LENGTH_LONG).show();
            } else {
                db = new DBHelper(getActivity());
                boolean isUsernameTaken = db.isUsernameTaken(usernameInput.getText().toString());
                if (isUsernameTaken) {
                    boolean passwordUpdated = db.updatePassword(usernameInput.getText().toString(), newPasswordInput.getText().toString());
                    if (passwordUpdated) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new LoginFragment()).commit();
                        Toast.makeText(getContext(), "You have changed your password successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Password change failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "No such user exists", Toast.LENGTH_LONG).show();
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