package com.example.milan.mojmajstor.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.milan.mojmajstor.R;

import java.util.ArrayList;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private ArrayList<RepairRequest> repairRequests;
    private Context context;
    private ExpandableListView evContent;
    private ArrayList<Integer> checkedRequests;

    public ExpandableListViewAdapter(Context context, ExpandableListView evContent, ArrayList<RepairRequest> repairRequests, ArrayList<Integer> checkedReequests) {
        this.context = context;
        this.evContent = evContent;
        this.repairRequests = repairRequests;
        this.checkedRequests = checkedReequests;
    }

    @Override
    public int getGroupCount() {
        return repairRequests.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return repairRequests.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return repairRequests.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        RepairRequest repairRequest = repairRequests.get(groupPosition);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.table_row_user_request, null);
        }

        TextView tvDescription = convertView.findViewById(R.id.tvURDescription);
        TextView tvCraftsman = convertView.findViewById(R.id.tvURCraftsman);
        TextView tvDate = convertView.findViewById(R.id.tvURDate);
        TextView tvStatus = convertView.findViewById(R.id.tvURStatus);
        CheckBox cbSelected = convertView.findViewById(R.id.cbURSelected);
        cbSelected.setChecked(false);

        tvDescription.setText(repairRequest.getDescription());
        tvCraftsman.setText(repairRequest.getCraftsman().getNameAndSurname());
        tvDate.setText(repairRequest.getDate());
        tvStatus.setText(repairRequest.getStatus());

        cbSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkedRequests.add(groupPosition);
                }
            }
        });

        if(groupPosition % 2 == 0){
            convertView.setBackgroundResource(R.drawable.table_row_even);
        }
        else{
            convertView.setBackgroundResource(R.drawable.table_row_odd);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_repair_request, null);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        Log.d("DDJ", "oneExpanded");
        return true;
    }

}
