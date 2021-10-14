package com.shahab.i180731_i180650;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SpecificChatActivity extends AppCompatActivity {

    List<SpecificChatRVModel> ls;
    RecyclerView rv;
    SpecificChatRVAdapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_chat);
        rv=findViewById(R.id.specific_chatRV);
        ls=new ArrayList<>();
        ls.add(new SpecificChatRVModel("Hey","i201550", 1));
        ls.add(new SpecificChatRVModel("Lets meet up at 8pm","i201550", 0));
        ls.add(new SpecificChatRVModel("LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM","i201550", 1));

        adapter=new SpecificChatRVAdapter(SpecificChatActivity.this, ls);
        RecyclerView.LayoutManager lm=new LinearLayoutManager(SpecificChatActivity.this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
    }

}
