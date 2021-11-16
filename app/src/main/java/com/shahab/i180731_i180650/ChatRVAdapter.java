package com.shahab.i180731_i180650;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

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
        holder.friend_id.setText(ls.get(position).getFriend_id());
        if (ls.get(position).isOnline)
            holder.itemView.findViewById(R.id.chat_row_greendot).setVisibility(View.VISIBLE);
        else
            holder.itemView.findViewById(R.id.chat_row_greendot).setVisibility(View.GONE);

        rowClickListeners(holder);
    }

    private void rowClickListeners(@NonNull chatViewHolder holder) {
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSpecificChatActicity(holder.friend_id);
            }
        });
        holder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSpecificChatActicity(holder.friend_id);
            }
        });
        holder.time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSpecificChatActicity(holder.friend_id);
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
        TextView name, message, time, friend_id;
        public chatViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.chat_name);
            message = itemView.findViewById(R.id.chat_message);
            time = itemView.findViewById(R.id.chat_time);
            friend_id = itemView.findViewById(R.id.chat_friend_id);
        }
    }

    //launch single chat screen
    private void launchSpecificChatActicity(TextView friend_id) {
        Intent intent = new Intent(c, SpecificChatActivity.class);
        intent.putExtra("friend_id", friend_id.getText().toString());
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

