package com.shahab.i180731_i180650;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class
LoginActivity extends AppCompatActivity {
    Button btn_login;
    private static final int pic_id = 1;
    TextView txtView_signup;
    ImageView img_cap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtView_signup = findViewById(R.id.login_register);
        txtView_signup.setOnClickListener(view -> launchSignupActivity());

        btn_login = findViewById(R.id.login_loginbtn);
        btn_login.setOnClickListener(view -> launchMessagesPageActivity());
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
    private void launchMessagesPageActivity() {
        Intent StartIntent = new Intent(this, MessagesPageActivity.class);
        startActivityForResult(StartIntent, pic_id);
    }
    

    private void launchSignupActivity() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }
}
