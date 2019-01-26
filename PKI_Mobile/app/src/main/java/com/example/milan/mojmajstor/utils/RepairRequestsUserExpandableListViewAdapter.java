package com.example.milan.mojmajstor.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.milan.mojmajstor.R;
import com.example.milan.mojmajstor.dialogs.CommentDialog;
import com.example.milan.mojmajstor.dialogs.PaymentDialog;
import com.example.milan.mojmajstor.dialogs.RateDialog;

import java.util.ArrayList;

public class RepairRequestsUserExpandableListViewAdapter extends BaseExpandableListAdapter {
    private ArrayList<RepairRequest> repairRequests;
    private Activity thisActivity;
    private ExpandableListView evRepairRequests;
    private ArrayList<Integer> checkedRequests;
    ImageButton btAddidionalOptions;
    private RepairRequestsUserExpandableListViewAdapter thisAdapter;

    public RepairRequestsUserExpandableListViewAdapter(Activity thisActivity, ExpandableListView evRepairRequests, ArrayList<RepairRequest> repairRequests, ArrayList<Integer> checkedReequests, ImageButton btAddidionalOptions) {
        this.thisActivity = thisActivity;
        this.evRepairRequests = evRepairRequests;
        this.repairRequests = repairRequests;
        this.checkedRequests = checkedReequests;
        this.btAddidionalOptions = btAddidionalOptions;
        this.thisAdapter = this;
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
            LayoutInflater inflater = (LayoutInflater) thisActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_view_group_item_repair_request_user, null);
        }

        TextView tvDescription = convertView.findViewById(R.id.tvRRGDescription);
        TextView tvCraftsman = convertView.findViewById(R.id.tvRRGCraftsman);
        TextView tvDate = convertView.findViewById(R.id.tvRRGDate);
        TextView tvStatus = convertView.findViewById(R.id.tvRRGStatus);
        LinearLayout llCheckBox = convertView.findViewById(R.id.llCheckBox);
        CheckBox cbSelected = llCheckBox.findViewById(R.id.cbURSelected);
        cbSelected.setChecked(false);

        tvDescription.setText(repairRequest.getDescription());
        tvCraftsman.setText(repairRequest.getCraftsman().getFirstAndLastName());
        tvDate.setText(repairRequest.getDate());
        tvStatus.setText(repairRequest.getStatus().toString());

        cbSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkedRequests.add(groupPosition);
                    btAddidionalOptions.setVisibility(View.VISIBLE);
                }
                else{
                    for(int i = 0; i < checkedRequests.size(); i++){
                        if(checkedRequests.get(i).intValue() == groupPosition){
                            checkedRequests.remove(i);
                            break;
                        }
                    }
                    if(checkedRequests.isEmpty()){
                        btAddidionalOptions.setVisibility(View.INVISIBLE);
                    }
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
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final RepairRequest repairRequest = repairRequests.get(groupPosition);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) thisActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_view_child_item_repair_request_user, null);
        }

        TextView tvDistrict = convertView.findViewById(R.id.tvRRCDistrictI);
        TextView tvAddress = convertView.findViewById(R.id.tvRRCAddressI);
        TextView tvSeverity = convertView.findViewById(R.id.tvRRCSeverityI);
        TextView tvPrice = convertView.findViewById(R.id.tvRRCPriceI);
        final TextView tvPaid = convertView.findViewById(R.id.tvRRCPaidI);
        final Button btNewPayment = convertView.findViewById(R.id.btRRCPay);
        Button btRateCraftsman = convertView.findViewById(R.id.btRRCRateCraftsman);
        Button btCommentCraftsman = convertView.findViewById(R.id.btRRCCommentCraftsman);

        btNewPayment.setEnabled(true);
        btRateCraftsman.setEnabled(true);
        btCommentCraftsman.setEnabled(true);

        btNewPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PaymentDialog(thisActivity, repairRequest, tvPaid, thisAdapter, btNewPayment).show();
            }
        });

        btRateCraftsman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RateDialog(thisActivity, repairRequest).show();
            }
        });

        btCommentCraftsman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CommentDialog(thisActivity, repairRequest).show();
            }
        });

        tvDistrict.setText(repairRequest.getDistrict());
        tvAddress.setText(repairRequest.getAddress());
        tvSeverity.setText(repairRequest.getSeverity().toString());
        tvPrice.setText(String.format("%.2f", repairRequest.getPrice()) + " RSD");
        if(repairRequest.isCreditCard()){
            tvPaid.setText(String.format("%.2f", repairRequest.getPaid()) + " RSD");
            btNewPayment.setVisibility(View.VISIBLE);
        }
        else{
            tvPaid.setText(thisActivity.getResources().getString(R.string.paying_with_cash));
            btNewPayment.setVisibility(View.GONE);
        }
        if(repairRequest.getPaid() == repairRequest.getPrice()){
            btNewPayment.setEnabled(false);
        }
        if(repairRequest.getStatus().toString().equals(thisActivity.getResources().getString(R.string.repair_status_refused)) || repairRequest.getStatus().equals(thisActivity.getResources().getString(R.string.repair_status_on_hold))){
            btNewPayment.setEnabled(false);
            btRateCraftsman.setEnabled(false);
            btCommentCraftsman.setEnabled(false);
        }

        if(groupPosition % 2 == 0){
            convertView.setBackgroundResource(R.drawable.table_row_even);
        }
        else{
            convertView.setBackgroundResource(R.drawable.table_row_odd);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
