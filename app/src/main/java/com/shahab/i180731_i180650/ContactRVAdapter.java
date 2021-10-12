package com.shahab.i180731_i180650;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactRVAdapter extends RecyclerView.Adapter<ContactRVAdapter.contactViewHolder> {
    Context c;
    List<ContactRVModel> ls;

    public ContactRVAdapter(Context c, List<ContactRVModel> ls) {
        this.c = c;
        this.ls = ls;
    }

    @NonNull
    @Override
    public contactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(c).inflate(R.layout.contact_row, parent, false);
        return new contactViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull contactViewHolder holder, int position) {
        holder.name.setText(ls.get(position).getName());
        holder.pid.setText(ls.get(position).getPid());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class contactViewHolder extends RecyclerView.ViewHolder {
        TextView name, pid;
        public contactViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.contact_name);
            pid = itemView.findViewById(R.id.contact_pid);
        }
    }
}
