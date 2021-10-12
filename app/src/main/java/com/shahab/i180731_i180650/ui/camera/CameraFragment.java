package com.shahab.i180731_i180650.ui.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
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

import com.shahab.i180731_i180650.R;
import com.shahab.i180731_i180650.databinding.FragmentCameraBinding;

public class CameraFragment extends Fragment {

    private CameraViewModel cameraViewModel;
    private FragmentCameraBinding binding;
    ImageView img_cap;
    private static final int pic_id = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cameraViewModel =
                new ViewModelProvider(this).get(CameraViewModel.class);

        binding = FragmentCameraBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
//        img_cap.findViewById(R.id.camera_result);
        cameraViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        final ImageView img_cap = binding.cameraResult;
        Intent StartIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(StartIntent);
        return root;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == pic_id) {
            cameraViewModel.getmImg().observe(getViewLifecycleOwner(), new Observer<Integer>() {
                @Override
                public void onChanged(@Nullable Integer imgid) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    img_cap.setImageBitmap(photo);
                }
            });

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}