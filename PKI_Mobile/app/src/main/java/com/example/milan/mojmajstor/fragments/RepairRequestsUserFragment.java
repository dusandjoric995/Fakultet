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

import com.example.milan.mojmajstor.R;
import com.example.milan.mojmajstor.utils.Data;
import com.example.milan.mojmajstor.utils.RepairRequestsExpandableListViewAdapter;
import com.example.milan.mojmajstor.utils.RepairRequest;

import java.util.ArrayList;
import java.util.Collections;

public class RepairRequestsUserFragment extends Fragment {

    private ImageButton btAdditionalOptions;
    private PopupMenu.OnMenuItemClickListener popupMenuListener;
    private Data data;
    private ExpandableListView elvRepairRequests;
    private RepairRequestsExpandableListViewAdapter elvRepairRequestsAdapter;
    ArrayList<RelativeLayout> repairRequestsLayouts;
    ArrayList<RelativeLayout> repairRequestsDetailLayouts;
    ArrayList<RepairRequest> repairRequests;
    ArrayList<Integer> checkedRequests;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_repair_request_user, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        data = Data.getInstance();
        elvRepairRequests = view.findViewById(R.id.elvRR);
        btAdditionalOptions = view.findViewById(R.id.btRRAdditionalOptions);

        repairRequestsLayouts = new ArrayList<>();
        repairRequestsDetailLayouts = new ArrayList<>();
        repairRequests = data.getCurrentUserRepairRequests();
        checkedRequests = new ArrayList<>();

        elvRepairRequestsAdapter = new RepairRequestsExpandableListViewAdapter(getActivity(), elvRepairRequests, repairRequests, checkedRequests, btAdditionalOptions);
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
    }

    @Override
    public void onResume() {
        super.onResume();
        MainFragmentController.removeBackButton();
        elvRepairRequestsAdapter.notifyDataSetChanged();
        for(int i = 0; i < repairRequests.size(); i++){
            elvRepairRequests.collapseGroup(i);
        }
    }
}
