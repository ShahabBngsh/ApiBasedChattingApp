package com.shahab.i180731_i180650.ui.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.shahab.i180731_i180650.R;

import io.agora.rtc.Constants;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;


public class GroupCallActivity extends AppCompatActivity {

    // Fill the App ID of your project generated on Agora Console.
    private String appId = "b6d46e33d71040658795fc6a038133d1";
    // app certificate
    // 9ee1afce77654eda95fef70619aec6a7
    // Fill the channel name.
    private String channelName = "BeyondTheWall";
    // Fill the temp token generated on Agora Console.
    private String token = "006b6d46e33d71040658795fc6a038133d1IAA0UJDbMvt/gr8vKv4f/lxscXizb5TGCgLiHylT81ktCPyZu/cAAAAAEAAi+yLhEz6XYQEAAQATPpdh";
    private RtcEngine mRtcEngine;

    // Handle SDK Events
    private final IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() {    };
    // Permissions
    private static final int PERMISSION_REQ_ID = 22;
    private static final String[] REQUESTED_PERMISSIONS = {Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA};

    public boolean checkSelfPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(this,
                permission)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    REQUESTED_PERMISSIONS,
                    requestCode);
            return false;
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.i("LOG_TAG", "onRequestPermissionsResult " + grantResults[0] + " " + requestCode);

        switch (requestCode) {
            case PERMISSION_REQ_ID: {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED) {
                    Log.i("LOG_TAG", "Need permissions " + Manifest.permission.RECORD_AUDIO + "/" + Manifest.permission.CAMERA);
                    break;
                }
                // if permission granted, initialize the engine
                initAgoraEngine();
                break;
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_call);

        if (checkSelfPermission(REQUESTED_PERMISSIONS[0], PERMISSION_REQ_ID) &&
                checkSelfPermission(REQUESTED_PERMISSIONS[1], PERMISSION_REQ_ID)){
            initAgoraEngine();
        }
        onjoinChannelClicked(new View(this));
    }



    private void initAgoraEngine() {
        try {
            mRtcEngine = RtcEngine.create(getBaseContext(), appId, mRtcEventHandler);
        } catch (Exception e) {
            Log.e("LOG_TAG", Log.getStackTraceString(e));

            throw new RuntimeException("NEED TO check rtc sdk init fatal error\n" + Log.getStackTraceString(e));
        }
        setupSession();
    }

    private void setupSession() {
        mRtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_COMMUNICATION);
    }


    public void onjoinChannelClicked(View view) {
        mRtcEngine.joinChannel(token, channelName, "", 0);
    }
    public void onLeaveChannelClicked(View view) {
        leaveChannel();
        finish();
        Toast.makeText(this, "Call ended", Toast.LENGTH_SHORT).show();
    }
    private void leaveChannel() {
        mRtcEngine.leaveChannel();
        Toast.makeText(this, "You left the channel", Toast.LENGTH_SHORT).show();
    }
    public void onVideoMuteClicked(View view) {
        Toast.makeText(this, "This feature will be added later", Toast.LENGTH_SHORT).show();
    }
    public void onAudioMuteClicked(View view) {
        ImageView btn = (ImageView) view;
        if (btn.isSelected()) {
            mRtcEngine.enableAudio();
            btn.setSelected(false);
            btn.setImageResource(R.drawable.ic_baseline_mic_24);
            Toast.makeText(this, "mic turned on", Toast.LENGTH_SHORT).show();
        } else {
            mRtcEngine.disableAudio();
            btn.setSelected(true);
            btn.setImageResource(R.drawable.ic_baseline_mic_off_24);
            Toast.makeText(this, "mic turned off", Toast.LENGTH_SHORT).show();
        }

        mRtcEngine.muteLocalAudioStream(btn.isSelected());
    }
}
