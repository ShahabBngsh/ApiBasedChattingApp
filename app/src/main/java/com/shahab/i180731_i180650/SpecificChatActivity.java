package com.shahab.i180731_i180650;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import java.time.LocalTime;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SpecificChatActivity extends AppCompatActivity {


    List<SpecificChatRVModel> ls;
    RecyclerView rv;
    SpecificChatRVAdapter adapter;

    ImageButton imgbtn_send;
    EditText edittxt_message;

    Button btn_back;

    private FirebaseAuth mAuth;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_chat);

        imgbtn_send = findViewById(R.id.specific_send);
        imgbtn_send.setOnClickListener(view -> sendMessage());
        edittxt_message = findViewById(R.id.specific_typemessage);

        btn_back = findViewById(R.id.specific_chat_backarrow);
        btn_back.setOnClickListener(view -> goBack2Chats());

        rv=findViewById(R.id.specific_chatRV);
        ls=new ArrayList<>();
        ls.add(new SpecificChatRVModel("Hey","i201550", 1));
        ls.add(new SpecificChatRVModel("Lets meet up at 8pm","i201550", 0));
        ls.add(new SpecificChatRVModel("LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM","i201550", 1));

        updateMessages();


        adapter=new SpecificChatRVAdapter(SpecificChatActivity.this, ls);
        RecyclerView.LayoutManager lm=new LinearLayoutManager(SpecificChatActivity.this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);

    }

    private void updateMessages() {
        SharedPreferences sharedPref = getSharedPreferences("app_values",Context.MODE_PRIVATE);
        String user_id = sharedPref.getString("userid", "none");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference messages_ref = database.getReference("users/"+user_id+"/messages/"+"userid");

        messages_ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data:snapshot.getChildren()){
                    SpecificChatRVModel message_to_display = data.getValue(SpecificChatRVModel.class);
                    ls.add(message_to_display);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void goBack2Chats() {
        finish();
    }

    //    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendMessage() {
//        Toast.makeText(this, this.edittxt_message.getText().toString(), Toast.LENGTH_SHORT).show();
        String message = edittxt_message.getText().toString();

        SharedPreferences sharedPref = getSharedPreferences("app_values",Context.MODE_PRIVATE);
        String user_id = sharedPref.getString("userid", "none");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference messages_ref = database.getReference("users/"+user_id+"/messages/"+"userid");

        messages_ref.push().setValue(new SpecificChatRVModel(message,"1", 0));

//        LocalTime lt = LocalTime.now()





//        contact_ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot data:snapshot.getChildren()){
//                    String frienduserid = data.getKey().toString();
//                    DatabaseReference frienduser = contact_ref.child(frienduserid);
//                    DatabaseReference profileref = frienduser.child("Profile");
//                    DatabaseReference contact_no_ref = profileref.child("contact_no");
//
//                    contact_no_ref.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            String contact_no = snapshot.getValue().toString();
//
//                            if (contact_no.equals(phone)){
//                                Toast.makeText(getActivity(), name+" "+phone, Toast.LENGTH_SHORT).show();
//                                ls.add(new ContactRVModel(name, phone));
////                                            Toast.makeText(getActivity(), ls.toString(), Toast.LENGTH_SHORT).show();
//
//                                adapter.notifyDataSetChanged();
//
//                            }
//
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//                            Log.d("TAG", phone);
//
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        Toast.makeText(this, "message sent", Toast.LENGTH_SHORT).show();

        ls.add(new SpecificChatRVModel(message, "9:30", 0));
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
