package com.shahab.i180731_i180650;

import android.app.Notification;
import android.app.NotificationManager;
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

import com.shahab.i180731_i180650.ui.contacts.ContactsViewModel;

import java.util.ArrayList;
import java.util.List;

public class ContactRVAdapter extends RecyclerView.Adapter<ContactRVAdapter.contactViewHolder> implements Filterable {
    Context c;
    List<ContactRVModel> ls;
    ArrayList<ContactRVModel> lsCopy;


    public ContactRVAdapter(Context c, List<ContactRVModel> ls) {
        this.c = c;
        this.ls = ls;
        this.lsCopy = new ArrayList<ContactRVModel>();
        this.lsCopy.addAll(ls);
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
        rowClickListeners(holder);
    }
    private void rowClickListeners(@NonNull contactViewHolder holder) {
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSpecificCallActicity();
            }
        });
        holder.pid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSpecificCallActicity();
            }
        });
    }

    private void launchSpecificCallActicity() {
        String tittle="CALLING";
        String subject="calling someone";
        String body="calling notification";

        NotificationManager notif=(NotificationManager) c.getSystemService((Context.NOTIFICATION_SERVICE));
        Notification notify=new Notification.Builder
                (c.getApplicationContext()).setContentTitle(tittle).setContentText(body).
                setContentTitle(subject).setSmallIcon(R.drawable.notification_icon).build();

        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        notif.notify(0, notify);

        Intent intent = new Intent(c, CallingActivity.class);
        c.startActivity(intent);
//        Toast.makeText(c, "Calling", Toast.LENGTH_SHORT).show();
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

    //for search and filtering RV
    @Override
    public Filter getFilter() {
        return null;
    }
    //for search and filtering RV
    public void filter(String text) {
        ls.clear();
        if(text.isEmpty()){
            ls.addAll(lsCopy);
        } else{
            text = text.toLowerCase();
            for(ContactRVModel item: lsCopy){
                if(item.name.toLowerCase().contains(text) || item.pid.toLowerCase().contains(text)){
                    ls.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}
