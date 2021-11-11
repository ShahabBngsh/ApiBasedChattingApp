package com.shahab.i180731_i180650;

import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.provider.MediaStore;
import android.sax.StartElementListener;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class
LoginActivity extends AppCompatActivity {
    Button btn_login;
    private static final int pic_id = 1;
    TextView txtView_signup;

    EditText login_email;
    EditText login_password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        GoogleSignInOptions gso = new GoogleSignInOptions
//                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken("235662107360-8jb6pq7i9rp2qectdan6riurf02gm75n.apps.googleusercontent.com")
//                .requestEmail()
//                .build();

        // ...
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();




        txtView_signup = findViewById(R.id.login_register);
        txtView_signup.setOnClickListener(view -> launchSignupActivity());

        btn_login = findViewById(R.id.login_loginbtn);
        btn_login.setOnClickListener(view -> launchMessagesPageActivity());

        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);


        HandlerThread handlerThread = new HandlerThread("content_observer");
        handlerThread.start();
        final Handler handler = new Handler(handlerThread.getLooper()) {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };

        //------------------------- SS
        getContentResolver().registerContentObserver(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                true,
                new ContentObserver(handler) {
                    @Override
                    public boolean deliverSelfNotifications() {
                        Log.d("Tag", "deliverSelfNotifications");
                        return super.deliverSelfNotifications();
                    }

                    @Override
                    public void onChange(boolean selfChange) {
                        super.onChange(selfChange);
                    }

                    @Override
                    public void onChange(boolean selfChange, Uri uri) {
                        Log.d("TAG", "onChange " + uri.toString());

                        Toast.makeText(LoginActivity.this, MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString(),
                                Toast.LENGTH_SHORT).show();

                        if (uri.toString().matches(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString() + "/[0-9]+")) {



                            NotificationCompat.Builder builder = new NotificationCompat.Builder(LoginActivity.this, "123")
                                    .setSmallIcon(R.drawable.notification_icon)
                                    .setContentTitle("Notification Taken")
                                    .setContentText("Please don't do anything unethical")
                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(LoginActivity.this);

                            // notificationId is a unique int for each notification that you must define
                            notificationManager.notify(1, builder.build());

                        }
                        super.onChange(selfChange, uri);
                    }
                }
        );
        //------------------------------------



    }

    @Override
    public void onStart() {
        super.onStart();


        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent navigationIntent = new Intent(LoginActivity.this, NavigationActivity.class);
            startActivity(navigationIntent);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();

        FirebaseAuth.getInstance().signOut();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
    private void launchMessagesPageActivity() {

        String login_email_check = login_email.getText().toString();
        String login_password_check = login_password.getText().toString();

        mAuth.signInWithEmailAndPassword(login_email_check, login_password_check)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();


//                            //------------------------------- move it to the message screen
//                            String userid = user.getUid();
//                            FirebaseDatabase database = FirebaseDatabase.getInstance();
//                            DatabaseReference myRef = database.getReference("users/" + userid + "/messages/" + "CnJgGmNbjuhYJOb0LRSzWcG23F92");
//
//                            List<String> list = new ArrayList<>();
//                            list.add("another random message");
//                            list.add(Calendar.getInstance().getTime().toString());
//                            myRef.push().setValue(list);
//
//
//                            DatabaseReference myRef1 = database.getReference("users/" + "CnJgGmNbjuhYJOb0LRSzWcG23F92" + "/messages/" + userid);
//                            myRef1.push().setValue(list);
//
//                            //------------------------------------





                            Intent navigationIntent = new Intent(LoginActivity.this, NavigationActivity.class);
                            startActivity(navigationIntent);


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
    

    private void launchSignupActivity() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }
}
