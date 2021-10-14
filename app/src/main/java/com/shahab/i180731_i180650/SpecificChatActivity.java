package com.shahab.i180731_i180650;

import static java.security.AccessController.getContext;

import android.database.sqlite.SQLiteDatabase;
import java.time.LocalTime;

import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SpecificChatActivity extends AppCompatActivity {
    DBHandler dbHelper;
//    SQLiteDatabase db = dbHelper.getWritableDatabase();

    List<SpecificChatRVModel> ls;
    RecyclerView rv;
    SpecificChatRVAdapter adapter;

    ImageButton imgbtn_send;
    EditText edittxt_message;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_chat);

        imgbtn_send = findViewById(R.id.specific_send);
        imgbtn_send.setOnClickListener(view -> sendMessage());

        edittxt_message = findViewById(R.id.specific_typemessage);

        rv=findViewById(R.id.specific_chatRV);
        ls=new ArrayList<>();
        ls.add(new SpecificChatRVModel("Hey","i201550", 1));
        ls.add(new SpecificChatRVModel("Lets meet up at 8pm","i201550", 0));
        ls.add(new SpecificChatRVModel("LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM","i201550", 1));

        adapter=new SpecificChatRVAdapter(SpecificChatActivity.this, ls);
        RecyclerView.LayoutManager lm=new LinearLayoutManager(SpecificChatActivity.this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);

        dbHelper = new DBHandler(this);
    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendMessage() {
//        Toast.makeText(this, this.edittxt_message.getText().toString(), Toast.LENGTH_SHORT).show();
        String message = edittxt_message.getText().toString();
//        LocalTime lt = LocalTime.now()

        long id = dbHelper.insertMessage(message, "09:30", 1, 0);
        Toast.makeText(this, "message sent", Toast.LENGTH_SHORT).show();

        ls.add(new SpecificChatRVModel(message, "9:30", 0));
    }

    private void getAllMessages(int id) {
        String array = dbHelper.getChatHistory();
        Toast.makeText(this, array, Toast.LENGTH_SHORT).show();
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
