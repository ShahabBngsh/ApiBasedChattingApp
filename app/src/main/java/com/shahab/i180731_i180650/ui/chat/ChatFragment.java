package com.shahab.i180731_i180650.ui.chat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shahab.i180731_i180650.ChatRVAdapter;
import com.shahab.i180731_i180650.ChatRVModel;
import com.shahab.i180731_i180650.R;
import com.shahab.i180731_i180650.SpecificChatRVModel;
import com.shahab.i180731_i180650.databinding.FragmentChatBinding;

import java.util.ArrayList;
import java.util.List;

import android.widget.SearchView;


public class ChatFragment extends Fragment implements SearchView.OnQueryTextListener{

    private ChatViewModel dashboardViewModel;
    private FragmentChatBinding binding;

    List<ChatRVModel> ls;
    RecyclerView rv;
    ChatRVAdapter adapter;

    SearchView searchView;
    ArrayList<ChatRVModel> arraylist = new ArrayList<ChatRVModel>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(ChatViewModel.class);

        binding = FragmentChatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textChat;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        rv=root.findViewById(R.id.chatRV);

        arraylist.add(new ChatRVModel("john snow", "she is ma queen", "01:35"));
        arraylist.add(new ChatRVModel("mad queen","drakarys ...", "06:40"));
        arraylist.add(new ChatRVModel("dumb and dumber","dany forgot ...", "06:09"));

        adapter=new ChatRVAdapter(getActivity(),arraylist);
        RecyclerView.LayoutManager lm=new LinearLayoutManager(getActivity());
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);

        getMessages();


        // Locate the EditText in listview_main.xml
        searchView = root.findViewById(R.id.chat_search);
        searchView.setOnQueryTextListener(this);

        return root;
    }

    private void getMessages() {
        SharedPreferences sharedPref = getActivity().getSharedPreferences("app_values", Context.MODE_PRIVATE);
        String user_id = sharedPref.getString("userid", "none");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference messages_ref = database.getReference("users/"+user_id+"/messages");

        messages_ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data:snapshot.getChildren()){
                    String friend_id = data.getKey();

                    FirebaseDatabase database2 = FirebaseDatabase.getInstance();
                    DatabaseReference profile_name = database.getReference("users/"+friend_id+"/Profile/name");



                    profile_name.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String friend_name = snapshot.getValue().toString();
                            arraylist.add(new ChatRVModel(friend_name,"idk", "idk", friend_id));
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return true;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}