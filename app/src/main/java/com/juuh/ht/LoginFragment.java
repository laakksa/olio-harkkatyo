package com.juuh.ht;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.text.TextUtils.isEmpty;

public class LoginFragment extends Fragment {
    View v;
    EditText username, password;
    Button btnlogin, btnsignup;
    DataBaseHelper DB;
    SharedPreferences preferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_login, container, false);
        preferences = getContext().getSharedPreferences("settings", Context.MODE_PRIVATE);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        username = (EditText) view.findViewById(R.id.username1);
        password = (EditText) view.findViewById(R.id.password1);
        btnlogin = (Button) view.findViewById(R.id.btnsignin1);
        btnsignup = (Button) view.findViewById(R.id.btnsignup1);
        DB = new DataBaseHelper(getActivity());

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equalsIgnoreCase("")) {
                    username.setError("Please enter your username");
                } if (pass.equalsIgnoreCase("")) {
                    password.setError("Please enter your password");
                }
                else {
                    String salt = DB.getSalt(user);
                    try {
                        byte[] saltArray = Base64.getDecoder().decode(salt);
                        String encryptedpass = HashSalt.encrypt(pass, saltArray);
                        Boolean checkuserpass = DB.checkusernamepassword(user, encryptedpass);
                        if (checkuserpass == true) {
                            preferences.edit().putString("currentUser", user).commit();
                            if (getArguments().getString("selectedFragment").equals("Scorecard")) {
                                getFragmentManager().beginTransaction().replace(R.id.frag_container,
                                        new Scorecard_fragment()).commit();
                            } else if (getArguments().getString("selectedFragment").equals("Profile")) {
                                getFragmentManager().beginTransaction().replace(R.id.frag_container,
                                        new ProfileFragment()).commit();
                            }


                        } else {
                            password.setError("Invalid credentials");
                        }
                    } catch (NullPointerException e) {
                        password.setError("Invalid credentials");
                    }


                }
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //TODO: fragmentin vaihto rekisteröitymis fragmenttiin
                Fragment selectedFragment = new RegisterFragment();
                getFragmentManager().beginTransaction().replace(R.id.frag_container,
                        selectedFragment).commit();
                Bundle bundle = new Bundle();
                if (getArguments().getString("selectedFragment").equals("Scorecard")) {
                    bundle.putString("selectedFragment", "Scorecard");
                    selectedFragment.setArguments(bundle);
                } else if (getArguments().getString("selectedFragment").equals("Profile")) {
                    bundle.putString("selectedFragment", "Profile");
                    selectedFragment.setArguments(bundle);
                }


            }
        });
    }
}