package com.shahab.i180731_i180650;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MessagesPageActivity extends AppCompatActivity {
    List<MyModel> ls;
    RecyclerView rv;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages_page);
        rv=findViewById(R.id.rv);
        ls=new ArrayList<>();
        ls.add(new MyModel("Saad","i201550"));
        ls.add(new MyModel("Saad","i201550"));
        ls.add(new MyModel("Saad","i201550"));
        ls.add(new MyModel("Saad","i201550"));
        ls.add(new MyModel("Saad","i201550"));
        ls.add(new MyModel("Saad","i201550"));
        ls.add(new MyModel("Saad","i201550"));
        ls.add(new MyModel("Saad","i201550"));
        ls.add(new MyModel("Saad","i201550"));
        ls.add(new MyModel("Saad","i201550"));
        ls.add(new MyModel("Saad","i201550"));
        ls.add(new MyModel("Saad","i201550"));
        ls.add(new MyModel("Saad","i201550"));
        ls.add(new MyModel("Saad","i201550"));
        ls.add(new MyModel("Saad","i201550"));

        adapter=new MyAdapter(MessagesPageActivity.this,ls);
        RecyclerView.LayoutManager lm=new LinearLayoutManager(MessagesPageActivity.this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);

    }
}