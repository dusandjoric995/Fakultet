package com.example.milan.mojmajstor.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.milan.mojmajstor.LoginActivity;
import com.example.milan.mojmajstor.R;

import java.util.ArrayList;

public class RepairRequestsCraftsmanExpandableListViewAdapter extends BaseExpandableListAdapter {
    private ArrayList<RepairRequest> repairRequests;
    private Activity thisActivity;
    private RepairRequestsCraftsmanExpandableListViewAdapter thisAdapter;

    public RepairRequestsCraftsmanExpandableListViewAdapter(Activity thisActivity, ArrayList<RepairRequest> repairRequests) {
        this.thisActivity = thisActivity;
        this.repairRequests = repairRequests;
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
            convertView = inflater.inflate(R.layout.list_view_group_item_repair_request_craftsman, null);
        }

        TextView tvDescription = convertView.findViewById(R.id.tvRRCCDescription);
        TextView tvDate = convertView.findViewById(R.id.tvRRCCDate);
        TextView tvSeverity = convertView.findViewById(R.id.tvRRCCSeverity);
        TextView tvStatus = convertView.findViewById(R.id.tvRRCCStatus);

        tvDescription.setText(repairRequest.getDescription());
        tvDate.setText(repairRequest.getDate());
        tvSeverity.setText(repairRequest.getSeverity().toString());
        tvStatus.setText(repairRequest.getStatus().toString());
        tvStatus.setTextColor(repairRequest.getStatusColor());

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
            convertView = inflater.inflate(R.layout.list_view_child_item_repair_request_craftsman, null);
        }

        TextView tvClient = convertView.findViewById(R.id.tvClientI);
        TextView tvDistrict = convertView.findViewById(R.id.tvDistrictI);
        TextView tvAddress = convertView.findViewById(R.id.tvAddressI);
        final EditText etPrice = convertView.findViewById(R.id.etPrice);
        final TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        final TextView tvPriceI = convertView.findViewById(R.id.tvPriceI);
        final Button btRefuse = convertView.findViewById(R.id.btRefuse);
        final Button btAccept = convertView.findViewById(R.id.btAccept);

        tvClient.setText(repairRequest.getClient().getFirstAndLastName());
        tvDistrict.setText(repairRequest.getDistrict());
        tvAddress.setText(repairRequest.getAddress());
        tvPriceI.setText(String.format("%.2f", repairRequest.getPrice()) + " RSD");

        etPrice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    if(etPrice.getCurrentTextColor() == thisActivity.getResources().getColor(R.color.colorAccent, null)){
                        etPrice.setText("");
                        etPrice.setTextColor(thisActivity.getResources().getColor(android.R.color.black, null));
                    }
                }
            }
        });

        btAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etPrice.clearFocus();
                if(etPrice.getText().toString().equals("") || etPrice.getText().toString().equals("Invalid value")){
                    etPrice.setTextColor(thisActivity.getResources().getColor(R.color.colorAccent, null));
                    etPrice.setText("Invalid value");
                }
                else{
                    repairRequest.setPrice(Double.parseDouble(etPrice.getText().toString()));
                    repairRequest.setStatus(RepairRequest.Status.OFFERED);
                    notifyDataSetChanged();
                    ToastWriter.write(thisActivity, thisActivity.getResources().getString(R.string.repair_request_accepted));
                }
            }
        });

        btRefuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastWriter.write(thisActivity, thisActivity.getResources().getString(R.string.repair_request_refused));
                repairRequest.setStatus(RepairRequest.Status.REFUSED);
                notifyDataSetChanged();
            }
        });

        if(groupPosition % 2 == 0){
            convertView.setBackgroundResource(R.drawable.table_row_even);
        }
        else{
            convertView.setBackgroundResource(R.drawable.table_row_odd);
        }

        if(repairRequest.getStatus() != RepairRequest.Status.ON_HOLD){
            btAccept.setVisibility(View.GONE);
            btRefuse.setVisibility(View.GONE);
            etPrice.setVisibility(View.GONE);
            tvPrice.setVisibility(View.VISIBLE);
            tvPriceI.setVisibility(View.VISIBLE);
        }
        else{
            btAccept.setVisibility(View.VISIBLE);
            btRefuse.setVisibility(View.VISIBLE);
            etPrice.setVisibility(View.VISIBLE);
            tvPrice.setVisibility(View.GONE);
            tvPriceI.setVisibility(View.GONE);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
