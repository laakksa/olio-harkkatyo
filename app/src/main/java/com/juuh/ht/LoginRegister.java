package com.juuh.ht;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginRegister extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup, signin;
    DataBaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginregister);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DataBaseHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(LoginRegister.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else if (checkpasswordcriterias(pass)) {
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser == false) {
                            Boolean insert = DB.insertData(user, pass);
                            if(insert == true) {
                                Toast.makeText(LoginRegister.this, "Registered succesfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginRegister.this, "Registration failes", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(LoginRegister.this, "User already exists! Please signin.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginRegister.this, "Passwords not matching.", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(LoginRegister.this, "Passwords does not meet the criteria.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

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