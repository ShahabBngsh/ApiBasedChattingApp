package com.shahab.i180731_i180650.ui.chat;

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

import com.shahab.i180731_i180650.ChatRVAdapter;
import com.shahab.i180731_i180650.ChatRVModel;
import com.shahab.i180731_i180650.R;
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
        ls=new ArrayList<>();
        ls.add(new ChatRVModel("john snow", "she is ma queen", "01:35"));
        ls.add(new ChatRVModel("mad queen","drakarys ...", "06:40"));
        ls.add(new ChatRVModel("dumb and dumber","dany forgot ...", "06:09"));

        arraylist.add(new ChatRVModel("john snow", "she is ma queen", "01:35"));
        arraylist.add(new ChatRVModel("mad queen","drakarys ...", "06:40"));
        arraylist.add(new ChatRVModel("dumb and dumber","dany forgot ...", "06:09"));

        adapter=new ChatRVAdapter(getActivity(),arraylist);
        RecyclerView.LayoutManager lm=new LinearLayoutManager(getActivity());
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        searchView = root.findViewById(R.id.chat_search);
        searchView.setOnQueryTextListener(this);

        return root;
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