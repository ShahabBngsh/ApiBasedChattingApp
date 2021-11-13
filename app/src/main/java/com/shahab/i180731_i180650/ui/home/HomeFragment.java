package com.shahab.i180731_i180650.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shahab.i180731_i180650.CallRVAdapter;
import com.shahab.i180731_i180650.CallRVModel;
import com.shahab.i180731_i180650.R;
import com.shahab.i180731_i180650.databinding.FragmentHomeBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements SearchView.OnQueryTextListener {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    List<CallRVModel> ls;
    RecyclerView rv;
    CallRVAdapter adapter;

    SearchView searchView;
    ArrayList<CallRVModel> arrayList = new ArrayList<CallRVModel>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });



        rv = root.findViewById(R.id.callRV);
        ls = new ArrayList<>();
        ls.add(new CallRVModel("Shahab", "work"));
        ls.add(new CallRVModel("Piyush", "play"));
        ls.add(new CallRVModel("Usama", "work"));
        ls.add(new CallRVModel("Zain", "sad laptop"));

        arrayList.addAll(ls);

        adapter = new CallRVAdapter(getActivity(), arrayList);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);

        searchView = root.findViewById(R.id.home_search);
        searchView.setOnQueryTextListener(this);

        return root;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}