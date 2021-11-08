package com.shahab.i180731_i180650.ui.contacts;

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

import com.shahab.i180731_i180650.ContactRVAdapter;
import com.shahab.i180731_i180650.ContactRVModel;
import com.shahab.i180731_i180650.R;
import com.shahab.i180731_i180650.databinding.FragmentContactsBinding;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment {

    private ContactsViewModel notificationsViewModel;
    private FragmentContactsBinding binding;

    List<ContactRVModel> ls;
    RecyclerView rv;
    ContactRVAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(ContactsViewModel.class);

        binding = FragmentContactsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        rv = root.findViewById(R.id.contactRV);
        ls = new ArrayList<>();
        ls.add(new ContactRVModel("Shahab", "0"));
        ls.add(new ContactRVModel("Piyush", "1"));
        ls.add(new ContactRVModel("Usama", "2"));
        ls.add(new ContactRVModel("Zain", "3"));
        ls.add(new ContactRVModel("Jenny", "4"));
        ls.add(new ContactRVModel("Janet", "5"));
        ls.add(new ContactRVModel("Chad", "6"));

        adapter = new ContactRVAdapter(getActivity(), ls);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
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