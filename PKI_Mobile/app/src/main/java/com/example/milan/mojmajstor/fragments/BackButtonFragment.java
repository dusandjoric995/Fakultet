package com.example.milan.mojmajstor.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.milan.mojmajstor.R;

public class BackButtonFragment extends Fragment {

    private ImageButton btBackButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_back_button, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        btBackButton = view.findViewById(R.id.btBackButton);
        btBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragmentController.showLastMainFragment();
            }
        });
    }

}
