package com.shahab.i180731_i180650;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LoginPageActivity extends AppCompatActivity {

    Button forgotButton;
    Button createAccountButton;
    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
//        forgotButton = (Button) findViewById(R.id.forgotButton);
//        createAccountButton = (Button) findViewById(R.id.createAccountButton);
//        signInButton = (Button) findViewById(R.id.signInButton);
//
//        forgotButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                Intent SwitchActivity = new Intent(LoginPageActivity.this, ForgotPasswordEmailActivity.class);
//                view.getContext().startActivity(SwitchActivity);}
//        });
//
//        createAccountButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                Intent SwitchActivity = new Intent(LoginPageActivity.this, CreateAccountActivity.class);
//                view.getContext().startActivity(SwitchActivity);}
//        });
//
//        signInButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                Intent SwitchActivity = new Intent(LoginPageActivity.this, AccountPageActivity.class);
//                view.getContext().startActivity(SwitchActivity);}
//        });
    }

}