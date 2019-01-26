package com.example.milan.mojmajstor.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.milan.mojmajstor.LoginActivity;
import com.example.milan.mojmajstor.R;
import com.example.milan.mojmajstor.utils.Data;
import com.example.milan.mojmajstor.utils.RepairRequest;
import com.example.milan.mojmajstor.utils.RepairRequestsCraftsmanExpandableListViewAdapter;

import java.util.ArrayList;

public class RepairRequestsCraftsmanFragment extends Fragment {

    private Activity thisActivity;
    private ExpandableListView elv;
    private RepairRequestsCraftsmanExpandableListViewAdapter elvAdapter;
    private ArrayList<RepairRequest> repairRequests;
    private Data data;
    private EditText etDescription, etClient, etDisctrict, etAddress;
    private ScrollView svFilter;
    private RelativeLayout rlFilterBorderTop, rlFilterBorderBottom;
    private TextView tvShowFilter, tvDescription, tvDate, tvSeverity, tvStatus;
    private boolean filterVisible;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_repair_requests_craftsman, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        repairRequests = new ArrayList<>();
        data = Data.getInstance();
        data.getCurrentUserRepairRequests(repairRequests, LoginActivity.UserType.CRAFTSMAN);
        thisActivity = getActivity();

        svFilter = view.findViewById(R.id.svFilter);
        rlFilterBorderTop = view.findViewById(R.id.rlFilterBorderTop);
        rlFilterBorderBottom = view.findViewById(R.id.rlFilterBorderBottom);
        tvShowFilter = view.findViewById(R.id.tvShowFilter);
        tvDescription = view.findViewById(R.id.tvHeaderDescription);
        tvDate = view.findViewById(R.id.tvHeaderDate);
        tvSeverity = view.findViewById(R.id.tvHeaderSeverity);
        tvStatus = view.findViewById(R.id.tvHeaderStatus);

        tvDescription.setOnClickListener(new HeaderOnClickListener(0));
        tvSeverity.setOnClickListener(new HeaderOnClickListener(2));
        tvStatus.setOnClickListener(new HeaderOnClickListener(3));

        filterVisible = false;
        svFilter.setVisibility(View.GONE);
        rlFilterBorderTop.setVisibility(View.GONE);
        rlFilterBorderBottom.setVisibility(View.GONE);
        tvShowFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(filterVisible){
                    svFilter.setVisibility(View.GONE);
                    rlFilterBorderTop.setVisibility(View.GONE);
                    rlFilterBorderBottom.setVisibility(View.GONE);
                    tvShowFilter.setText(thisActivity.getResources().getString(R.string.show_filter));
                }
                else{
                    svFilter.setVisibility(View.VISIBLE);
                    rlFilterBorderTop.setVisibility(View.VISIBLE);
                    rlFilterBorderBottom.setVisibility(View.VISIBLE);
                    tvShowFilter.setText(thisActivity.getResources().getString(R.string.hide_filter));
                }
                filterVisible = !filterVisible;
            }
        });

        elv = view.findViewById(R.id.elv);
        elvAdapter = new RepairRequestsCraftsmanExpandableListViewAdapter(thisActivity, repairRequests);
        elv.setAdapter(elvAdapter);

    }

    private class HeaderOnClickListener implements View.OnClickListener {

        private int sortingOption;

        public HeaderOnClickListener(int sortingOption){
            this.sortingOption = sortingOption;
        }

        @Override
        public void onClick(View v) {
            data.sortRepairRequests(repairRequests, sortingOption);
            elvAdapter.notifyDataSetChanged();
        }
    }
}
