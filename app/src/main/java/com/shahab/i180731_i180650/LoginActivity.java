package com.shahab.i180731_i180650;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.preference.PreferenceManager;
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


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class LoginActivity extends AppCompatActivity {
    Button btn_login;
    private static final int pic_id = 1;
    TextView txtView_signup;

    EditText login_email;
    EditText login_password;;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        GoogleSignInOptions gso = new GoogleSignInOptions
//                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken("235662107360-8jb6pq7i9rp2qectdan6riurf02gm75n.apps.googleusercontent.com")
//                .requestEmail()
//                .build();


        SharedPreferences sharedPref = getSharedPreferences("app_values",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("server_ip", "http://172.17.60.179/smd21/");
        editor.apply();

        Toast.makeText(LoginActivity.this, sharedPref.getString("server_ip", ""), Toast.LENGTH_SHORT).show();



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
//        Intent navigationIntent = new Intent(LoginActivity.this, NavigationActivity.class);
//        startActivity(navigationIntent);
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
    private void launchMessagesPageActivity() {
        String login_email_check = login_email.getText().toString();
        String login_password_check = login_password.getText().toString();

        final TextView textView = (TextView) findViewById(R.id.login_email);
// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        SharedPreferences sharedPref = getSharedPreferences("app_values",Context.MODE_PRIVATE);
        String url = sharedPref.getString("server_ip", "none") + "getLogin.php";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String email, pass, userid;
                        Boolean matches = false;
                        StringTokenizer st = new StringTokenizer(response,",");
                        while (st.hasMoreTokens()) {
                            email = st.nextToken();
                            st.hasMoreTokens();
                            pass = st.nextToken();
                            st.hasMoreTokens();
                            userid = st.nextToken();
                            if (email.equals(login_email_check) && pass.equals(login_password_check)) {
                                SharedPreferences sharedPref = getSharedPreferences("app_values",Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString("curr_user_id", userid);
                                editor.apply();
                                matches = true;
                                Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
                                startActivity(intent);
                            }
                        }
                        if (matches == false) {
                            Toast.makeText(LoginActivity.this, "WRONG CREDENTIALS", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("ERROR", error.toString());
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);




    }

    private void launchSignupActivity() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }
}
