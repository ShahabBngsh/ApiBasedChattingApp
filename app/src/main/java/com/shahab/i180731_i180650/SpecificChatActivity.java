package com.shahab.i180731_i180650;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class SpecificChatActivity extends AppCompatActivity {


    List<SpecificChatRVModel> ls;
    RecyclerView rv;
    SpecificChatRVAdapter adapter;

    ImageButton imgbtn_send;
    EditText edittxt_message;

    Button btn_back;
    String my_name = "l@a.com";

    ImageButton camera;

    private static final int pic_id = 1;

    public String friend_id;
    private SpecificChatRVModel to_add;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_chat);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            friend_id = extras.getString("friend_id");
        }
        else{
            friend_id = "NA";
        }


        imgbtn_send = findViewById(R.id.specific_send);
        imgbtn_send.setOnClickListener(view -> sendMessage(friend_id));
        edittxt_message = findViewById(R.id.specific_typemessage);

        btn_back = findViewById(R.id.specific_chat_backarrow);
        btn_back.setOnClickListener(view -> goBack2Chats());

        camera = findViewById(R.id.specific_camera);
        camera.setOnClickListener(view -> startCamera(friend_id));

        rv=findViewById(R.id.specific_chatRV);
        ls=new ArrayList<>();

        updateMessages(friend_id);


        adapter=new SpecificChatRVAdapter(SpecificChatActivity.this, ls);
        RecyclerView.LayoutManager lm=new LinearLayoutManager(SpecificChatActivity.this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);

    }

    private void updateMessages(String friend_id) {
        SharedPreferences sharedPref = getSharedPreferences("app_values", Context.MODE_PRIVATE);
        String user_id = sharedPref.getString("curr_user_id", "none");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = sharedPref.getString("server_ip", "none") + "getChats.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String message, time, sender, reciever;
                        Boolean matches = false;
                        StringTokenizer st = new StringTokenizer(response,",");
                        while (st.hasMoreTokens()) {
                            sender = st.nextToken();
                            st.hasMoreTokens();
                            reciever = st.nextToken();
                            st.hasMoreTokens();
                            message = st.nextToken();
                            st.hasMoreTokens();
                            time = st.nextToken();
                            if (user_id.equals(sender) && friend_id.equals(reciever)) {
                                //display message
                                SpecificChatRVModel message_to_display = new SpecificChatRVModel(message, time, 0);
                                ls.add(message_to_display);
                            }
                            else if (user_id.equals(reciever) && friend_id.equals(sender)){
                                //display message
                                SpecificChatRVModel message_to_display = new SpecificChatRVModel(message, time, 1);
                                ls.add(message_to_display);
                            }
                        }
                        adapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SpecificChatActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("ERROR", error.toString());
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    private void goBack2Chats() {
        finish();
    }

    //    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendMessage(String friend_id) {
//        Toast.makeText(this, this.edittxt_message.getText().toString(), Toast.LENGTH_SHORT).show();
        String message = edittxt_message.getText().toString();

        SharedPreferences sharedPref = getSharedPreferences("app_values",Context.MODE_PRIVATE);
        String logged_user_id = sharedPref.getString("curr_user_id", "none");


        Date time_now_in_obj = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
        String time_now = dateFormat.format(time_now_in_obj);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = sharedPref.getString("server_ip", "none") + "storeSentChat.php?sender=" + logged_user_id + "&reciever=" + friend_id + "&message=" + message;
        Toast.makeText(SpecificChatActivity.this, url, Toast.LENGTH_LONG).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SpecificChatActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("ERROR", error.toString());
            }
        });
        edittxt_message.setText("");
        updateMessages(friend_id);
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }


    private void startCamera(String friend_id) {
        Intent StartIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        this.startActivityForResult(StartIntent, pic_id);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == pic_id && resultCode == Activity.RESULT_OK) {
//            SharedPreferences sharedPref = getSharedPreferences("app_values",Context.MODE_PRIVATE);
//            String user_id = sharedPref.getString("userid", "none");
//
//            if (friend_id.equals("???")) {}
//            else {
//

//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference messages_ref = database.getReference("users/" + user_id + "/messages/" + friend_id);
//                messages_ref.push().setValue(new SpecificChatRVModel(message, time_now, 0));
//
//                DatabaseReference myRef = database.getReference("users/" + friend_id + "/messages/" + user_id);
//                myRef.push().setValue(new SpecificChatRVModel(message, time_now, 1));

//            ls.add()



//            }

            to_add = new SpecificChatRVModel("", "",0);
            Bitmap photo = (Bitmap) data.getExtras().get("data");

            to_add.setPicid(photo);
            ls.add(to_add);
            
            adapter.notifyDataSetChanged();

        }


    }


}
//    // Gets the data repository in write mode
//    SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//    // Create a new map of values, where column names are the keys
//    ContentValues values = new ContentValues();
//values.put(FeedEntry.COLUMN_NAME_TITLE, title);
//        values.put(FeedEntry.COLUMN_NAME_SUBTITLE, subtitle);
//
//// Insert the new row, returning the primary key value of the new row
//        long newRowId = db.insert(FeedEntry.TABLE_NAME, null, values);
