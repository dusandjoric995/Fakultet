package com.example.milan.mojmajstor.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import com.example.milan.mojmajstor.R;
import com.example.milan.mojmajstor.utils.Data;

import java.util.ArrayList;

public class RepairRequestFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_repair_request, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
