package com.shahab.i180731_i180650;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewChatRVAdapter extends RecyclerView.Adapter<NewChatRVAdapter.newChatViewHolder>{
    Context c;
    List<NewChatRVModel> ls;
    ArrayList<NewChatRVModel> lsCopy;
    Activity thisActivity;

    public NewChatRVAdapter(Context c, List<NewChatRVModel> ls, Activity thisActivity) {
        this.c = c;
        this.ls = ls;
        this.lsCopy = new ArrayList<NewChatRVModel>();
        this.thisActivity = thisActivity;
        lsCopy.addAll(ls);
    }

    @NonNull
    @Override
    public newChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(c).inflate(R.layout.new_chat_row, parent, false);
        return new newChatViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull NewChatRVAdapter.newChatViewHolder holder, int position) {
        holder.newChatName.setText(ls.get(position).getName());
        holder.newChatUserID.setText(ls.get(position).getUser_id());
        rowClickListeners(holder, holder.newChatUserID.getText().toString());
    }

    private void rowClickListeners(@NonNull newChatViewHolder holder, String friend_id) {
        holder.newChatName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences shr = c.getSharedPreferences("app_values",Context.MODE_PRIVATE);
                String user_id = shr.getString("userid","none");

                FirebaseDatabase database = FirebaseDatabase.getInstance();

                Date time_now_in_obj = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
                String time_now = dateFormat.format(time_now_in_obj);

                DatabaseReference messages_ref = database.getReference("users/"+user_id+"/messages/"+friend_id);
                messages_ref.push().setValue(new SpecificChatRVModel("You can now enter messages below",time_now, 1));

                DatabaseReference myRef = database.getReference("users/" + friend_id + "/messages/" + user_id);
                myRef.push().setValue(new SpecificChatRVModel("You can now enter messages below",time_now, 1));

                thisActivity.finish();



            }
        });
    }


    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class newChatViewHolder extends RecyclerView.ViewHolder{
        TextView newChatName, newChatUserID;

        public newChatViewHolder(View itemView) {
            super(itemView);
            this.newChatName = itemView.findViewById(R.id.new_chat_name);
            this.newChatUserID = itemView.findViewById(R.id.new_chat_user_id);
        }
    }



}
