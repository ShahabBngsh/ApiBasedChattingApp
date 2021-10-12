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
import com.shahab.i180731_i180650.MessagesPageActivity;
import com.shahab.i180731_i180650.MyAdapter;
import com.shahab.i180731_i180650.MyModel;
import com.shahab.i180731_i180650.R;
import com.shahab.i180731_i180650.databinding.FragmentChatBinding;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {

    private ChatViewModel dashboardViewModel;
    private FragmentChatBinding binding;

    List<ChatRVModel> ls;
    RecyclerView rv;
    ChatRVAdapter adapter;


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
        ls.add(new ChatRVModel("Hey","i201550"));
        ls.add(new ChatRVModel("Lets meet up at 8pm","i201550"));
        ls.add(new ChatRVModel("LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM LOREM IPSUM","i201550"));

        adapter=new ChatRVAdapter(getActivity(),ls);
        RecyclerView.LayoutManager lm=new LinearLayoutManager(getActivity());
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}