package com.shahab.i180731_i180650;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CallRVAdapter extends RecyclerView.Adapter<CallRVAdapter.chatViewHolder> {
    Context c;
    List<CallRVModel> ls;

    public CallRVAdapter(Context c, List<CallRVModel> ls) {
        this.c = c;
        this.ls = ls;
    }

    @NonNull
    @Override
    public chatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(c).inflate(R.layout.call_row, parent,false);
        return new chatViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull chatViewHolder holder, int position) {
        holder.call_name.setText(ls.get(position).getName());
        holder.call_status.setText(ls.get(position).getStatus());
        holder.call_name.setOnClickListener(new View.OnClickListener() {
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
        TextView call_name, call_status;
        public chatViewHolder(@NonNull View itemView) {
            super(itemView);
            call_name =itemView.findViewById(R.id.call_name);
            call_status =itemView.findViewById(R.id.call_status);
        }
    }
}

