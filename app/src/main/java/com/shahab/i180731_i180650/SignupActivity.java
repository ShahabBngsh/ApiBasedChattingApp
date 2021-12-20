package com.shahab.i180731_i180650;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class SignupActivity extends AppCompatActivity {
    Button btn_signup;
    TextView txtView_login;

    TextView txtView_email;
    TextView txtView_password;
    TextView txtView_confirmpassword;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        txtView_login = findViewById(R.id.signup_login);
        txtView_login.setOnClickListener(view -> launchLoginActivity());

        txtView_email = findViewById(R.id.signup_email);
        txtView_password = findViewById(R.id.signup_password);
        txtView_confirmpassword = findViewById(R.id.signup_confirmpassword);

        btn_signup = findViewById(R.id.signup_signup);
        btn_signup.setOnClickListener(view -> initiateSignUp());

    }

    private void launchLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void initiateSignUp() {

        String email = txtView_email.getText().toString();
        String password = txtView_password.getText().toString();
        String confirmpassword = txtView_confirmpassword.getText().toString();

        if (password.equals(confirmpassword)) {
//            mAuth.createUserWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                        @RequiresApi(api = Build.VERSION_CODES.O)
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                // Sign in success, update UI with the signed-in user's information
//                                Toast.makeText(SignupActivity.this, password,
//                                        Toast.LENGTH_SHORT).show();
//
//                                Log.d("TAG", "createUserWithEmail:success");
//
//                                FirebaseUser user = mAuth.getCurrentUser();
//                                //----------------------------------- move this code to new page
//                                String userid = user.getUid();
//
//                                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                                DatabaseReference myRef = database.getReference("users/" + userid + "/Profile");
//                                Profile userProfile = new Profile(email, "03335522162", "ad", "i like whatever");
//                                myRef.setValue(userProfile);
//                                //----------------------------------
//
//
//                                FirebaseAuth.getInstance().signOut();
//
//                                Intent loginIntent = new Intent(SignupActivity.this, LoginActivity.class);
//                                startActivity(loginIntent);
//
//
//                            } else {
//                                if (password.length()<8) {
//                                    Toast.makeText(SignupActivity.this, "Password should be at least 8 characters long",
//                                            Toast.LENGTH_SHORT).show();
//                                } else {
//                                    Toast.makeText(SignupActivity.this, "Email is already registered!",
//                                            Toast.LENGTH_SHORT).show();
//                                }
//
//                                Log.w("TAG", "createUserWithEmail:failure", task.getException());
//
//                            }
//                        }
//                    });

        } else {
            Toast.makeText(this, "Password are not matching", Toast.LENGTH_SHORT).show();
        }
    }
}
