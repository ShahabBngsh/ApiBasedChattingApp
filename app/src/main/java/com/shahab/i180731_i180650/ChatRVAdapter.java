package com.shahab.i180731_i180650;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatRVAdapter extends RecyclerView.Adapter<ChatRVAdapter.chatViewHolder> {
    Context c;
    List<ChatRVModel> ls;

    public ChatRVAdapter(Context c, List<ChatRVModel> ls) {
        this.c = c;
        this.ls = ls;
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

    public class chatViewHolder extends RecyclerView.ViewHolder {
        TextView name, message, time;
        public chatViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.chat_name);
            message = itemView.findViewById(R.id.chat_message);
            time = itemView.findViewById(R.id.chat_time);
        }
    }

    private void launchSpecificChatActicity() {
        Intent intent = new Intent(c, SpecificChatActivity.class);
        c.startActivity(intent);
    }

}

