package com.shahab.i180731_i180650;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CallRVAdapter extends RecyclerView.Adapter<CallRVAdapter.chatViewHolder> implements Filterable {
    Context c;
    List<CallRVModel> ls;
    ArrayList<CallRVModel> lsCopy;

    ImageView img_groupcall;

    public CallRVAdapter(Context c, List<CallRVModel> ls) {
        this.c = c;
        this.ls = ls;
        this.lsCopy = new ArrayList<CallRVModel>();
        this.lsCopy.addAll(ls);
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
        rowClickListeners(holder);
    }
    private void rowClickListeners(@NonNull chatViewHolder holder) {
        holder.call_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSpecificPersonActicity();
            }
        });
        holder.call_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSpecificPersonActicity();
            }
        });
    }

    private void launchSpecificPersonActicity() {
        Toast.makeText(c, "Clicked ...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public void filter(String text) {
        ls.clear();
        if(text.isEmpty()){
            ls.addAll(lsCopy);
        } else{
            text = text.toLowerCase();
            for(CallRVModel item: lsCopy){
                if(item.name.toLowerCase().contains(text) || item.status.toLowerCase().contains(text)){
                    ls.add(item);
                }
            }
        }
        notifyDataSetChanged();
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

