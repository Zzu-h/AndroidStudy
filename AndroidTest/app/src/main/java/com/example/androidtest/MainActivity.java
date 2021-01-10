package com.example.androidtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText TextInputEditText_password, TextInputEditText_email;
    RelativeLayout RelativeLayout_login;

    String MyEmail = "admin@gmail.com", MyPassword = "admin";
    String inputEmail = "", inputPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextInputEditText_email = findViewById(R.id.TextInputEditText_email);
        TextInputEditText_password = findViewById(R.id.TextInputEditText_password);
        RelativeLayout_login = findViewById(R.id.RelativeLayout_login);

        RelativeLayout_login.setClickable(false);

        TextInputEditText_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                Log.d("Senti", s.toString());
                if(s != null) {
                    inputEmail = s.toString();
                    RelativeLayout_login.setClickable(check());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        TextInputEditText_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Log.d("Sent before", charSequence.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                Log.d("Sent", s.toString());
                if(s != null) {
                    inputPassword = s.toString();
                    RelativeLayout_login.setClickable(check());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Log.d("Sent after", editable.toString());
            }
        });

        RelativeLayout_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = TextInputEditText_email.getText().toString();
                String password = TextInputEditText_password.getText().toString();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                startActivity(intent);
            }
        });
    }

    boolean check(){
        boolean flag1 = (MyEmail.equals(inputEmail));
        System.out.println(MyEmail + " / "+ inputEmail);
        boolean flag2 =  MyPassword.equals(inputPassword);
        System.out.println(MyPassword + " / " + inputPassword);
        return flag1 && flag2;
    }
}