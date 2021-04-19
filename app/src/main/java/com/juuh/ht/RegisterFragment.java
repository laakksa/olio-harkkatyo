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

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterFragment extends Fragment {
    View v;
    EditText username, password, repassword;
    Button signup, signin;
    DataBaseHelper DB;
    SharedPreferences preferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_register, container, false);
        preferences = getContext().getSharedPreferences("settings", Context.MODE_PRIVATE);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        username = (EditText) view.findViewById(R.id.username);
        password = (EditText) view.findViewById(R.id.password);
        repassword = (EditText) view.findViewById(R.id.repassword);
        signup = (Button) view.findViewById(R.id.btnsignup);
        signin = (Button) view.findViewById(R.id.btnsignin);
        DB = new DataBaseHelper(getActivity());

        signup.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                //if(user.equals("")||pass.equals("")||repass.equals(""))
                  //  Toast.makeText(getActivity(), "Please enter all the fields", Toast.LENGTH_SHORT).show();

                if (user.equalsIgnoreCase("")) {
                    username.setError("Please enter your username");
                } if (pass.equalsIgnoreCase("")) {
                    password.setError("Please enter your password");
                } if (repass.equalsIgnoreCase("")) {
                    repassword.setError("Please enter your password again");
                } if (DB.checkusername(user)) {
                    username.setError("User already exists! Please signin.");
                } else if (!checkpasswordcriterias(pass)) {
                    password.setError("Passwords does not meet the criteria.");
                } else if (!pass.equals(repass)) {
                    repassword.setError("Passwords not matching.");
                } else {
                    byte[] salt = HashSalt.getSalt();
                    String encryptedpassword = HashSalt.encrypt(pass, salt);
                    String saltString = Base64.getEncoder().encodeToString(salt);
                    Boolean insert = DB.insertData(user, encryptedpassword, saltString);
                    if(insert == true) {
                        preferences.edit().putString("currentUser", user).commit();
                        if (getArguments().getString("selectedFragment").equals("Scorecard")) {
                            getFragmentManager().beginTransaction().replace(R.id.frag_container,
                                    new Scorecard_fragment()).commit();
                        } else if (getArguments().getString("selectedFragment").equals("Profile")) {
                            getFragmentManager().beginTransaction().replace(R.id.frag_container,
                                    new ProfileFragment()).commit();
                        }
                    } else {
                        username.setError("Registration failed: Unknown error");
                    }
                }






                /*
                else if (checkpasswordcriterias(pass)) {
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser == false) {
                            byte[] salt = HashSalt.getSalt();
                            String encryptedpassword = HashSalt.encrypt(pass, salt);
                            String saltString = Base64.getEncoder().encodeToString(salt);
                            Boolean insert = DB.insertData(user, encryptedpassword, saltString);
                            if(insert == true) {
                                if (getArguments().getString("selectedFragment").equals("Scorecard")) {
                                    getFragmentManager().beginTransaction().replace(R.id.frag_container,
                                            new Scorecard_fragment()).commit();
                                } else if (getArguments().getString("selectedFragment").equals("Profile")) {
                                    getFragmentManager().beginTransaction().replace(R.id.frag_container,
                                            new ProfileFragment()).commit();
                                }

                            } else {
                                username.setError("Registration failed: Unknown error");
                                //Toast.makeText(getActivity(), "Registration failes", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            username.setError("User already exists! Please signin.");
                            //Toast.makeText(getActivity(), "User already exists! Please signin.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        repassword.setError("Passwords not matching.");
                        //Toast.makeText(getActivity(), "Passwords not matching.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    password.setError("Passwords does not meet the criteria.");
                    //Toast.makeText(getActivity(), "Passwords does not meet the criteria.", Toast.LENGTH_SHORT).show();
                }*/
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment selectedFragment = new LoginFragment();
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

    public Boolean checkpasswordcriterias(String password) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        boolean flag = false;
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(password);
        boolean containsSpecialChar = matcher.find();
        for(int i=0;i < password.length();i++) {
            ch = password.charAt(i);
            if(Character.isDigit(ch)) {
                numberFlag = true;
            }
            else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            }
            else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }
            if(numberFlag && capitalFlag && lowerCaseFlag && containsSpecialChar)
                flag = true;
        }
        if (password.length()>11) {
            if (flag) {
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }


}