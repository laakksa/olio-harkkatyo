package com.juuh.ht;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class ProfileFragment extends Fragment {
    View v;
    Button btnreset, btnlogout;
    TextView userTextView, savedTextView;
    EditText crnpassword, newpassword, newrepassword;
    String usrname;
    Integer savedScorecards = -1;
    SharedPreferences preferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        preferences = getContext().getSharedPreferences("settings", Context.MODE_PRIVATE);
        usrname = preferences.getString("currentUser", null);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        JSONWriteAndRead jwr = JSONWriteAndRead.getInstance();
        ArrayList<Match> matches = jwr.readIndex(usrname);
        for (int i = 0; i < matches.size(); i++) {
            savedScorecards++;
        }

        userTextView = (TextView) view.findViewById(R.id.usernameBox);
        userTextView.setText("Your username: \n" + usrname);
        savedTextView = (TextView) view.findViewById(R.id.playedgames);
        savedTextView.setText(String.valueOf(savedScorecards));
        crnpassword = (EditText) view.findViewById(R.id.currentpassword);
        newpassword = (EditText) view.findViewById(R.id.newpassword);
        newrepassword = (EditText) view.findViewById(R.id.newrepassword);
        btnreset = (Button) view.findViewById(R.id.resetbtn);
        DataBaseHelper DB = new DataBaseHelper(getContext());
        TextInputLayout crnLayout = (TextInputLayout) view.findViewById(R.id.crnLayout);
        TextInputLayout newLayout = (TextInputLayout) view.findViewById(R.id.newLayout);
        TextInputLayout renewLayout = (TextInputLayout) view.findViewById(R.id.renewLayout);

        /*When btnreset button is pressed onClick() checks if current password and new passwords
        fills the criterias and resets the password. Current password is also compared to the password in the database.
        If everything is fine the new password will get also a new salt.*/
        btnreset.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                //Check if fields are empty or not
                if (crnpassword.getText().toString().equals("")) {
                    crnLayout.setError("Please enter your current password");
                } else {
                    crnLayout.setError(null);
                }
                if (newpassword.getText().toString().equals("")) {
                    newLayout.setError("Please enter your new password");
                } else {
                    newLayout.setError(null);
                }
                if (newrepassword.getText().toString().equals("")) {
                    renewLayout.setError("Please enter your new password again");
                } else {
                    renewLayout.setError(null);
                }
                //Check old and new password
                String salt = DB.getSalt(usrname);
                byte[] saltArray = Base64.getDecoder().decode(salt);
                String encryptedpass = HashSalt.encrypt(crnpassword.getText().toString(),
                        saltArray);
                Boolean checkuserpassword = DB.checkusernamepassword(usrname, encryptedpass);
                if (checkuserpassword) {
                    crnLayout.setError(null);
                    if (checkpasswordcriterias(newpassword.getText().toString())) {
                        newLayout.setError(null);
                        if(newpassword.getText().toString().equals(newrepassword.getText().
                                toString())){
                            byte[] saltNew = HashSalt.getSalt();
                            String encryptedpassword = HashSalt.encrypt(newpassword.getText().
                                    toString(), saltNew);
                            String saltString = Base64.getEncoder().encodeToString(saltNew);
                            DB.deleteData(usrname);
                            Boolean insert = DB.insertData(usrname, encryptedpassword, saltString);
                            if(insert) {
                                getFragmentManager().beginTransaction().replace(R.id.frag_container,
                                        new ProfileFragment()).commit();
                            } else {
                                crnLayout.setError("Something went wrong. Password not reseted.");
                            }
                        } else {
                            renewLayout.setError("Passwords not matching.");
                        }
                    } else{
                        newLayout.setError("Passwords does not meet the criteria.");
                    }
                } else {
                    crnLayout.setError("Wrong current password.");
                }

            }
        });

        /*Btnlogout sends null to currentUser in preferences so that the application knows
        that the user is not anymore logged in.Btnlogout also changes the fragment to login fragment
        because the user is not not logged in.         */
        btnlogout = v.findViewById(R.id.logoutbtn);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.edit().putString("currentUser", null).commit();
                Bundle bundle2 = new Bundle();
                bundle2.putString("selectedFragment", "Profile");
                Fragment fragment = new LoginFragment();
                fragment.setArguments(bundle2);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_container, fragment).commit();
            }
        });


    }

        /*This method takes in the password as an string parameter and returns boolean. It checks if
        the password meets the criterias for a good password. If the password meets the criterias
        the method will return true, otherwise false.         */
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