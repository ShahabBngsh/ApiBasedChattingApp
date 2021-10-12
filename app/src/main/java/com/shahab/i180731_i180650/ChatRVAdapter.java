package com.shahab.i180731_i180650;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        holder.message.setText(ls.get(position).getMessage());
        holder.time.setText(ls.get(position).getTime());
        holder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class chatViewHolder extends RecyclerView.ViewHolder {
        TextView message, time;
        public chatViewHolder(@NonNull View itemView) {
            super(itemView);
            message =itemView.findViewById(R.id.message);
            time =itemView.findViewById(R.id.time);
        }
    }
}

