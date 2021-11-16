package com.shahab.i180731_i180650;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        if (user != null) {
            userid = user.getUid();
            DatabaseReference myRef = database.getReference("users/" + userid + "/Profile/online_status");
            myRef.setValue("online");

        } else {
            Log.d("WARNING", "user id is null");
            DatabaseReference myRef = database.getReference("users/" + userid + "/Profile/online_status");
            myRef.setValue("online");
        }

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void appInPauseState() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        if (user != null) {
            userid = user.getUid();
            DatabaseReference myRef = database.getReference("users/" + userid + "/Profile/online_status");
            myRef.setValue("offline");

        } else {
            Log.d("WARNING", "user id is null");
            DatabaseReference myRef = database.getReference("users/" + userid + "/Profile/online_status");
            myRef.setValue("offline");
        }

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void appInStopState() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        if (user != null) {
            userid = user.getUid();
            DatabaseReference myRef = database.getReference("users/" + userid + "/Profile/online_status");
            myRef.setValue("offline");

        } else {
            Log.d("WARNING", "user id is null");
            DatabaseReference myRef = database.getReference("users/" + userid + "/Profile/online_status");
            myRef.setValue("offline");
        }

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void appInDestroyState() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        if (user != null) {
            userid = user.getUid();
            DatabaseReference myRef = database.getReference("users/" + userid + "/Profile/online_status");
            myRef.setValue("offline");

        } else {
            Log.d("WARNING", "user id is null");
            DatabaseReference myRef = database.getReference("users/" + userid + "/Profile/online_status");
            myRef.setValue("offline");
        }

    }

}