package com.shahab.i180731_i180650;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.internal.ActivityLifecycleObserver;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shahab.i180731_i180650.databinding.NavigationBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NavigationActivity extends AppCompatActivity implements LifecycleObserver {
    public static String userid;
    private NavigationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);


        binding = NavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_camera,  R.id.navigation_chat, R.id.navigation_contacts)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_navigation);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void appInResumeState() {
        RequestQueue queue = Volley.newRequestQueue(this);
        SharedPreferences sharedPref = getSharedPreferences("app_values", Context.MODE_PRIVATE);
        String logged_user_id = sharedPref.getString("curr_user_id", "none");

        String url = sharedPref.getString("server_ip", "none") + "updateContacts.php?user_id=" + logged_user_id + "&online_status=1";
        Toast.makeText(NavigationActivity.this, url, Toast.LENGTH_LONG).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NavigationActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("ERROR", error.toString());
            }
        });
        queue.add(stringRequest);

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void appInPauseState() {
        RequestQueue queue = Volley.newRequestQueue(this);
        SharedPreferences sharedPref = getSharedPreferences("app_values", Context.MODE_PRIVATE);
        String logged_user_id = sharedPref.getString("curr_user_id", "none");

        String url = sharedPref.getString("server_ip", "none") + "updateContacts.php?user_id=" + logged_user_id + "&online_status=0";
        Toast.makeText(NavigationActivity.this, url, Toast.LENGTH_LONG).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NavigationActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("ERROR", error.toString());
            }
        });
        queue.add(stringRequest);



    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void appInStopState() {
        RequestQueue queue = Volley.newRequestQueue(this);
        SharedPreferences sharedPref = getSharedPreferences("app_values", Context.MODE_PRIVATE);
        String logged_user_id = sharedPref.getString("curr_user_id", "none");

        String url = sharedPref.getString("server_ip", "none") + "updateContacts.php?user_id=" + logged_user_id + "&online_status=0";
        Toast.makeText(NavigationActivity.this, url, Toast.LENGTH_LONG).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NavigationActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("ERROR", error.toString());
            }
        });
        queue.add(stringRequest);

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void appInDestroyState() {
        RequestQueue queue = Volley.newRequestQueue(this);
        SharedPreferences sharedPref = getSharedPreferences("app_values", Context.MODE_PRIVATE);
        String logged_user_id = sharedPref.getString("curr_user_id", "none");

        String url = sharedPref.getString("server_ip", "none") + "updateContacts.php?user_id=" + logged_user_id + "&online_status=0";
        Toast.makeText(NavigationActivity.this, url, Toast.LENGTH_LONG).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NavigationActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("ERROR", error.toString());
            }
        });
        queue.add(stringRequest);

    }

}