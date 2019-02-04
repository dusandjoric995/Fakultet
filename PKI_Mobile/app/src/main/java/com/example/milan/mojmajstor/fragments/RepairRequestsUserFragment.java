package com.example.milan.mojmajstor.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.milan.mojmajstor.LoginActivity;
import com.example.milan.mojmajstor.R;
import com.example.milan.mojmajstor.utils.Data;
import com.example.milan.mojmajstor.utils.RepairRequestsUserExpandableListViewAdapter;
import com.example.milan.mojmajstor.utils.RepairRequest;

import java.util.ArrayList;
import java.util.Collections;

public class RepairRequestsUserFragment extends Fragment {

    private ImageButton btAdditionalOptions;
    private PopupMenu.OnMenuItemClickListener popupMenuListener;
    private Data data;
    private ExpandableListView elvRepairRequests;
    private RepairRequestsUserExpandableListViewAdapter elvRepairRequestsAdapter;
    private ArrayList<RelativeLayout> repairRequestsLayouts;
    private ArrayList<RelativeLayout> repairRequestsDetailLayouts;
    private ArrayList<RepairRequest> repairRequests;
    private ArrayList<Integer> checkedRequests;
    private TextView tvHeaderStatus;
    private TextView tvHeaderCraftsman;
    private TextView tvHeaderDescription;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_repair_requests_user, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        data = Data.getInstance();
        elvRepairRequests = view.findViewById(R.id.elvRR);
        btAdditionalOptions = view.findViewById(R.id.btRRAdditionalOptions);
        tvHeaderDescription= view.findViewById(R.id.tvHeaderDescription);
        tvHeaderCraftsman = view.findViewById(R.id.tvHeaderCraftsman);
        tvHeaderStatus = view.findViewById(R.id.tvHeaderStatus);

        repairRequestsLayouts = new ArrayList<>();
        repairRequestsDetailLayouts = new ArrayList<>();
        repairRequests = new ArrayList<>();
        data.getCurrentUserRepairRequests(repairRequests, LoginActivity.UserType.USER);
        checkedRequests = new ArrayList<>();

        elvRepairRequestsAdapter = new RepairRequestsUserExpandableListViewAdapter(getActivity(), elvRepairRequests, repairRequests, checkedRequests, btAdditionalOptions);
        elvRepairRequests.setAdapter(elvRepairRequestsAdapter);
        btAdditionalOptions.setVisibility(View.INVISIBLE);

        popupMenuListener = new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.menu_item_ur_delete:{
                        Collections.sort(checkedRequests);
                        for(int i = 0; i < checkedRequests.size(); i++){
                            data.removeRepairRequest(repairRequests.get(checkedRequests.get(i) - i));
                            repairRequests.remove(checkedRequests.get(i) - i);
                        }
                        checkedRequests.clear();
                        elvRepairRequestsAdapter.notifyDataSetChanged();
                        return true;
                    }
                    default:{
                        return false;
                    }
                }
            }
        };

        btAdditionalOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), btAdditionalOptions);
                popupMenu.setOnMenuItemClickListener(popupMenuListener);
                popupMenu.inflate(R.menu.menu_ur_additional_options);
                popupMenu.show();
            }
        });

        tvHeaderStatus.setOnClickListener(new HeaderOnClickListener(3));
        tvHeaderDescription.setOnClickListener(new HeaderOnClickListener(0));
        tvHeaderCraftsman.setOnClickListener(new HeaderOnClickListener(4));
    }

    @Override
    public void onResume() {
        super.onResume();
        MainFragmentController.removeBackButton();
        elvRepairRequestsAdapter.notifyDataSetChanged();
        for(int i = 0; i < repairRequests.size(); i++){
            elvRepairRequests.collapseGroup(i);
        }
        if(repairRequests.size() > 0){
            if (repairRequests.get(0).getStatus().ordinal() >= repairRequests.get(repairRequests.size() - 1).getStatus().ordinal()) {
                data.sortRepairRequests(repairRequests, 3);
            }
        } // Hack :)
    }

    private class HeaderOnClickListener implements View.OnClickListener {

        private int sortingOption;

        public HeaderOnClickListener(int sortingOption){
            this.sortingOption = sortingOption;
        }

        @Override
        public void onClick(View v) {
            data.sortRepairRequests(repairRequests, sortingOption);
            elvRepairRequestsAdapter.notifyDataSetChanged();
        }
    }
}
