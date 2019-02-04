package com.example.milan.mojmajstor.utils;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
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

        ConstraintLayout clOffer = convertView.findViewById(R.id.clOffer);
        ConstraintLayout clPaidI = convertView.findViewById(R.id.clPaidI);
        TextView tvClientI = convertView.findViewById(R.id.tvClientI);
        TextView tvDistrictI = convertView.findViewById(R.id.tvDistrictI);
        TextView tvAddressI = convertView.findViewById(R.id.tvAddressI);
        TextView tvPaymentMethodI = convertView.findViewById(R.id.tvPaymentMethodI);
        TextView tvPaid = convertView.findViewById(R.id.tvPaid);
        TextView tvPaidI = convertView.findViewById(R.id.tvPaidI);
        final EditText etPrice = convertView.findViewById(R.id.etPrice);
        final TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        final TextView tvPriceI = convertView.findViewById(R.id.tvPriceI);
        final Button btRefuse = convertView.findViewById(R.id.btRefuse);
        final Button btAccept = convertView.findViewById(R.id.btAccept);
        Button btConfirmPayment = convertView.findViewById(R.id.btConfirmPayment);

        tvClientI.setText(repairRequest.getClient().getFirstAndLastName());
        tvDistrictI.setText(repairRequest.getDistrict());
        tvAddressI.setText(repairRequest.getAddress());
        tvPriceI.setText(String.format("%.2f", repairRequest.getPrice()) + " RSD");
        tvPaymentMethodI.setText(repairRequest.isCreditCard() ? "Creddit card" : "Cash");
        tvPaidI.setText(repairRequest.getPaid() + "");

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

        btConfirmPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repairRequest.setStatus(RepairRequest.Status.PAID);
                notifyDataSetChanged();
            }
        });

        if(groupPosition % 2 == 0){
            convertView.setBackgroundResource(R.drawable.table_row_even);
        }
        else{
            convertView.setBackgroundResource(R.drawable.table_row_odd);
        }

        switch (repairRequest.getStatus()){
            case ON_HOLD:{
                clOffer.setVisibility(View.VISIBLE);
                tvPrice.setVisibility(View.GONE);
                tvPriceI.setVisibility(View.GONE);
                tvPaid.setVisibility(View.GONE);
                clPaidI.setVisibility(View.GONE);
                break;
            }
            case OFFERED:{
                clOffer.setVisibility(View.GONE);
                tvPrice.setVisibility(View.VISIBLE);
                tvPriceI.setVisibility(View.VISIBLE);
                tvPaid.setVisibility(View.GONE);
                clPaidI.setVisibility(View.GONE);
                break;
            }
            case ACCEPTED:{
                clOffer.setVisibility(View.GONE);
                tvPrice.setVisibility(View.VISIBLE);
                tvPriceI.setVisibility(View.VISIBLE);
                tvPaid.setVisibility(View.VISIBLE);
                clPaidI.setVisibility(View.VISIBLE);
                if(repairRequest.isCreditCard()){
                    tvPaidI.setVisibility(View.VISIBLE);
                    btConfirmPayment.setVisibility(View.GONE);
                }
                else{
                    tvPaidI.setVisibility(View.GONE);
                    btConfirmPayment.setVisibility(View.VISIBLE);
                }
                break;
            }
            case PAID:{
                clOffer.setVisibility(View.GONE);
                tvPrice.setVisibility(View.VISIBLE);
                tvPriceI.setVisibility(View.VISIBLE);
                tvPaid.setVisibility(View.GONE);
                clPaidI.setVisibility(View.GONE);
                break;
            }
            case REFUSED:{
                clOffer.setVisibility(View.GONE);
                tvPrice.setVisibility(View.VISIBLE);
                tvPriceI.setVisibility(View.VISIBLE);
                tvPaid.setVisibility(View.GONE);
                clPaidI.setVisibility(View.GONE);
                break;
            }
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
