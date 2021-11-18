package com.shahab.i180731_i180650;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SpecificChatRVAdapter extends RecyclerView.Adapter<SpecificChatRVAdapter.specificChatViewHolder> {
    Context c;
    List<SpecificChatRVModel> ls;

    public SpecificChatRVAdapter(Context c, List<SpecificChatRVModel> ls) {
        this.c = c;
        this.ls = ls;
    }

    @NonNull
    @Override
    public specificChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0){
            View row= LayoutInflater.from(c).inflate(R.layout.specific_chat_row_sender, parent,false);
            return new specificChatViewHolder(row);
        }

        else {
            View row= LayoutInflater.from(c).inflate(R.layout.specific_chat_row_receiver, parent,false);
            return new specificChatViewHolder(row);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull specificChatViewHolder holder, int position) {
        holder.message.setText(ls.get(position).getMessage());
        holder.time.setText(ls.get(position).getTime());
        holder.message.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ls.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (ls.get(position).getViewType() == 0) {
            return 0;
        } else {
            return 1;
        }
    }


    public class specificChatViewHolder extends RecyclerView.ViewHolder {
        TextView message, time;
        public specificChatViewHolder(@NonNull View itemView) {
            super(itemView);
            message =itemView.findViewById(R.id.specific_chat_message);
            time =itemView.findViewById(R.id.specific_chat_time);
        }
    }



}
