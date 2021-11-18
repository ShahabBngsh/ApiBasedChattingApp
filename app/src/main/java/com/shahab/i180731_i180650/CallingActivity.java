package com.shahab.i180731_i180650;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static androidx.core.app.ActivityCompat.requestPermissions;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import io.agora.rtc.Constants;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoCanvas;
import io.agora.rtc.video.VideoEncoderConfiguration;

public class CallingActivity extends AppCompatActivity {
    Button btn_videoCall, btn_call_disconnect, btn_mic;

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
    private final IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() {
        @Override
        public void onUserJoined(final int uid, int elapsed) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setupRemoteVideoStream(uid);
                }
            });
        }

        // remote user has left channel
        @Override
        public void onUserOffline(int uid, int reason) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    onRemoteUserLeft();
                }
            });
        }

        // remote user has toggled their video
        @Override
        public void onRemoteVideoStateChanged(final int uid, final int state, int reason, int elapsed) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    onRemoteUserVideoToggle(uid, state);
                }
            });
        }
    };
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
        setContentView(R.layout.activity_call);

        if (checkSelfPermission(REQUESTED_PERMISSIONS[0], PERMISSION_REQ_ID) &&
                checkSelfPermission(REQUESTED_PERMISSIONS[1], PERMISSION_REQ_ID)){
            initAgoraEngine();
        }
        onjoinChannelClicked(new View(this));
//        onVideoMuteClicked(new View(this));
//        onAudioMuteClicked(new View(this));
////        btn_mic = findViewById(R.id.calling_mic);
//        btn_mic.setOnClickListener(this::onAudioMuteClicked);
////        btn_videoCall = findViewById(R.id.calling_videocall);
//        btn_videoCall.setOnClickListener(this::onVideoMuteClicked);
////        btn_call_disconnect = findViewById(R.id.calling_disconnect);
//        btn_call_disconnect.setOnClickListener(this::onLeaveChannelClicked);
//        //set visibility for buttones
//        //findViewById(R.id.calling)
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

//        mRtcEngine.enableVideo();

        mRtcEngine.setVideoEncoderConfiguration(new VideoEncoderConfiguration(VideoEncoderConfiguration.VD_640x360, VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_30,
                VideoEncoderConfiguration.STANDARD_BITRATE,
                VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT));
    }
    private void setupLocalVideoFeed() {
        FrameLayout videoContainer = findViewById(R.id.local_video_view_container);
        SurfaceView videoSurface = RtcEngine.CreateRendererView(getBaseContext());
        videoSurface.setZOrderMediaOverlay(true);
        videoContainer.addView(videoSurface);
        mRtcEngine.setupLocalVideo(new VideoCanvas(videoSurface, VideoCanvas.RENDER_MODE_FIT, 0));
    }

    private void setupRemoteVideoStream(int uid) {
        FrameLayout videoContainer = findViewById(R.id.remote_video_view_container);
        SurfaceView videoSurface = RtcEngine.CreateRendererView(getBaseContext());
        videoContainer.addView(videoSurface);
        mRtcEngine.setupRemoteVideo(new VideoCanvas(videoSurface, VideoCanvas.RENDER_MODE_FIT, uid));
        mRtcEngine.setRemoteSubscribeFallbackOption(io.agora.rtc.Constants.STREAM_FALLBACK_OPTION_AUDIO_ONLY);
    }

    private void onRemoteUserVideoToggle(int uid, int state) {
        FrameLayout videoContainer = findViewById(R.id.remote_video_view_container);

        SurfaceView videoSurface = (SurfaceView) videoContainer.getChildAt(0);
        videoSurface.setVisibility(state == 0 ? View.GONE : View.VISIBLE);

        // add an icon to let the other user know remote video has been disabled
        if(state == 0){
            ImageView noCamera = new ImageView(this);
            noCamera.setImageResource(R.drawable.ic_baseline_videocam_off_24);
            videoContainer.addView(noCamera);
        } else {
            ImageView noCamera = (ImageView) videoContainer.getChildAt(1);
            if(noCamera != null) {
                videoContainer.removeView(noCamera);
            }
        }
    }

    private void onRemoteUserLeft() {
        removeVideo(R.id.remote_video_view_container);
    }

    private void removeVideo(int containerID) {
        FrameLayout videoContainer = findViewById(containerID);
        videoContainer.removeAllViews();
    }

    public void onjoinChannelClicked(View view) {
        mRtcEngine.joinChannel(token, channelName, "", 0);
        setupLocalVideoFeed();
////        findViewById(R.id.joinBtn).setVisibility(View.GONE); // set the join button hidden
//        findViewById(R.id.calling_mic).setVisibility(View.VISIBLE); // set the audio button hidden
//        findViewById(R.id.calling_disconnect).setVisibility(View.VISIBLE); // set the leave button hidden
//        findViewById(R.id.calling_videocall).setVisibility(View.VISIBLE); // set the video button hidden
    }
    public void onLeaveChannelClicked(View view) {
        leaveChannel();
        removeVideo(R.id.local_video_view_container);
        removeVideo(R.id.remote_video_view_container);
        finish();
        Toast.makeText(this, "Call ended", Toast.LENGTH_SHORT).show();
    }
    private void leaveChannel() {
        mRtcEngine.leaveChannel();
        Toast.makeText(this, "You left the channel", Toast.LENGTH_SHORT).show();
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
    public void onVideoMuteClicked(View view) {
        ImageView btn = (ImageView) view;
        if (btn.isSelected()) {
            mRtcEngine.enableVideo();
            btn.setSelected(false);
            btn.setImageResource(R.drawable.ic_baseline_videocam_24);
            Toast.makeText(this, "camera turned on", Toast.LENGTH_SHORT).show();
        } else {
            mRtcEngine.disableVideo();
            btn.setSelected(true);
            btn.setImageResource(R.drawable.ic_baseline_videocam_off_24);
            Toast.makeText(this, "camera turned off", Toast.LENGTH_SHORT).show();
        }

        mRtcEngine.muteLocalVideoStream(btn.isSelected());

        FrameLayout container = findViewById(R.id.local_video_view_container);
        container.setVisibility(btn.isSelected() ? View.GONE : View.VISIBLE);
        SurfaceView videoSurface = (SurfaceView) container.getChildAt(0);
        videoSurface.setZOrderMediaOverlay(!btn.isSelected());
        videoSurface.setVisibility(btn.isSelected() ? View.GONE : View.VISIBLE);
    }
}