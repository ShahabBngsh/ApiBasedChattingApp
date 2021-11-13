package com.shahab.i180731_i180650;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatRVAdapter extends RecyclerView.Adapter<ChatRVAdapter.chatViewHolder> implements Filterable {
    Context c;
    List<ChatRVModel> ls;
    ArrayList<ChatRVModel> lsCopy;

    public ChatRVAdapter(Context c, List<ChatRVModel> ls) {
        this.c = c;
        this.ls = ls;
        this.lsCopy = new ArrayList<ChatRVModel>();
        this.lsCopy.addAll(ls);
    }

    @NonNull
    @Override
    public chatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(c).inflate(R.layout.chat_row, parent,false);
        return new chatViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull chatViewHolder holder, int position) {
        holder.name.setText(ls.get(position).getName());
        holder.message.setText(ls.get(position).getMessage());
        holder.time.setText(ls.get(position).getTime());

        rowClickListeners(holder);
    }

    private void rowClickListeners(@NonNull chatViewHolder holder) {
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSpecificChatActicity();
            }
        });
        holder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSpecificChatActicity();
            }
        });
        holder.time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSpecificChatActicity();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public class chatViewHolder extends RecyclerView.ViewHolder {
        TextView name, message, time;
        public chatViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.chat_name);
            message = itemView.findViewById(R.id.chat_message);
            time = itemView.findViewById(R.id.chat_time);
        }
    }

    //launch signle chat screen
    private void launchSpecificChatActicity() {
        Intent intent = new Intent(c, SpecificChatActivity.class);
        c.startActivity(intent);
    }

    //for search and filtering RV
    public void filter(String text) {
        ls.clear();
        if(text.isEmpty()){
            ls.addAll(lsCopy);
        } else{
            text = text.toLowerCase();
            for(ChatRVModel item: lsCopy){
                if(item.name.toLowerCase().contains(text) || item.message.toLowerCase().contains(text)){
                    ls.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

}

