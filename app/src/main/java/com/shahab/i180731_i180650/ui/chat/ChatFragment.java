package com.shahab.i180731_i180650.ui.chat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shahab.i180731_i180650.ChatRVAdapter;
import com.shahab.i180731_i180650.ChatRVModel;
import com.shahab.i180731_i180650.LoginActivity;
import com.shahab.i180731_i180650.NavigationActivity;
import com.shahab.i180731_i180650.NewChatActivity;
import com.shahab.i180731_i180650.R;
import com.shahab.i180731_i180650.databinding.FragmentChatBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import android.widget.SearchView;
import android.widget.Toast;

import javax.security.auth.callback.Callback;


public class ChatFragment extends Fragment implements SearchView.OnQueryTextListener{
    boolean onlineStatus;

    private ChatViewModel dashboardViewModel;
    private FragmentChatBinding binding;

    List<ChatRVModel> ls;
    RecyclerView rv;
    ChatRVAdapter adapter;

    SearchView searchView;
    ArrayList<ChatRVModel> arraylist = new ArrayList<ChatRVModel>();

    ImageView add_chat;


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


        add_chat = root.findViewById(R.id.add_chat);
        add_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newChatIntent = new Intent(getActivity(), NewChatActivity.class);
                startActivity(newChatIntent);
            }
        });


        // Locate the EditText in listview_main.xml
        searchView = root.findViewById(R.id.chat_search);
        searchView.setOnQueryTextListener(this);

        return root;
    }

    private void getMessages() {
        SharedPreferences sharedPref = getActivity().getSharedPreferences("app_values", Context.MODE_PRIVATE);
        String user_id = sharedPref.getString("userid", "none");

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url ="http://192.168.100.108/smd21/getContacts.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String user_id, name, onlinestatus, desc, lastseen;
                        StringTokenizer st = new StringTokenizer(response,",");
                        while (st.hasMoreTokens()) {
                            user_id = st.nextToken();
                            st.hasMoreTokens();
                            name = st.nextToken();
                            st.hasMoreTokens();
                            desc = st.nextToken();
                            st.hasMoreTokens();
                            lastseen = st.nextToken();
                            st.hasMoreTokens();
                            onlinestatus = st.nextToken();
                            Boolean isOnline = Boolean.getBoolean(onlinestatus);

                            arraylist.add(new ChatRVModel(name, desc, lastseen, user_id, isOnline));
                            adapter.notifyDataSetChanged();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("ERROR", error.toString());
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


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