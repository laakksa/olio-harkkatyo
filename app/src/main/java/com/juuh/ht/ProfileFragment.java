package com.juuh.ht;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO: toastit, salasanan vaihto, logout, käyttäjänimi, tallennettujen pelien määrä ei toimi
public class ProfileFragment extends Fragment {
    View v;
    Button btnreset;
    EditText crnpassword, newpassword, newrepassword;
    String usrname = "ilmari"; //TODO tähän oikea muuttuja

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_profile, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        crnpassword = (EditText) view.findViewById(R.id.currentpassword);
        newpassword = (EditText) view.findViewById(R.id.newpassword);
        newrepassword = (EditText) view.findViewById(R.id.newrepassword);
        btnreset = (Button) view.findViewById(R.id.resetbtn);
        DataBaseHelper DB = new DataBaseHelper(getContext());
        TextInputLayout crnLayout = (TextInputLayout) view.findViewById(R.id.crnLayout);
        TextInputLayout newLayout = (TextInputLayout) view.findViewById(R.id.newLayout);
        TextInputLayout renewLayout = (TextInputLayout) view.findViewById(R.id.renewLayout);

        btnreset.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (crnpassword.getText().toString().equals("")) {
                    crnLayout.setError("Please enter your current password");
                } if (newpassword.equals("")) {
                    newLayout.setError("Please enter your new password");
                } if (newrepassword.equals("")) {
                    renewLayout.setError("Please enter your new password again");
                    //TODO: tästä eteenpäin pitää korjata jotta salasanan uusiminen toimii
                } else if (checkpasswordcriterias(newpassword.toString())) {
                    if(newpassword.equals(newrepassword.toString())){
                        Boolean checkpass = DB.checkusernamepassword(usrname, crnpassword.toString());
                        if(checkpass == false) {
                            byte[] salt = HashSalt.getSalt();
                            String encryptedpassword = HashSalt.encrypt(newpassword.toString(), salt);
                            String saltString = Base64.getEncoder().encodeToString(salt);
                            Boolean insert = DB.insertData(usrname, encryptedpassword, saltString);
                            if(insert == true) {
                                Toast.makeText(getContext(), "Password reseted.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Reseting failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getContext(), "Wrong current password.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Passwords not matching.", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(getContext(), "Passwords does not meet the criteria.", Toast.LENGTH_SHORT).show();
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