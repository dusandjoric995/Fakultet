package com.example.milan.mojmajstor.utils;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
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
import android.widget.RelativeLayout;
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
        tvStatus.setTextColor(repairRequest.getStatusColor());

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

        ConstraintLayout clPayment = convertView.findViewById(R.id.clPayment);
        ConstraintLayout clOffer = convertView.findViewById(R.id.clOffer);
        RelativeLayout rlRateAndComment = convertView.findViewById(R.id.rlRateAndComment);
        RelativeLayout rlDistancerPayment = convertView.findViewById(R.id.rlDistancerPayment);
        RelativeLayout rlDistancerOffer = convertView.findViewById(R.id.rlDistancerOffer);
        RelativeLayout rlDistancerRateAndComment = convertView.findViewById(R.id.rlDistancerRateAndComment);
        TextView tvDistrict = convertView.findViewById(R.id.tvDistrictI);
        TextView tvAddress = convertView.findViewById(R.id.tvAddressI);
        TextView tvSeverity = convertView.findViewById(R.id.tvSeverityI);
        TextView tvPrice = convertView.findViewById(R.id.tvPriceI);
        TextView tvPayingWithCash = convertView.findViewById(R.id.tvPayingWithCash);
        final TextView tvPaid = convertView.findViewById(R.id.tvPaid);
        final TextView tvPaidI = convertView.findViewById(R.id.tvPaidI);
        final Button btNewPayment = convertView.findViewById(R.id.btPay);
        Button btRateCraftsman = convertView.findViewById(R.id.btRateCraftsman);
        Button btCommentCraftsman = convertView.findViewById(R.id.btCommentCraftsman);
        Button btRefuse = convertView.findViewById(R.id.btRefuse);
        Button btAccept = convertView.findViewById(R.id.btAccept);

        btNewPayment.setEnabled(true);
        btRateCraftsman.setEnabled(true);
        btCommentCraftsman.setEnabled(true);

        btNewPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PaymentDialog(thisActivity, repairRequest, tvPaidI, thisAdapter, btNewPayment).show();
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

        btRefuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repairRequest.setStatus(RepairRequest.Status.REFUSED);
                notifyDataSetChanged();
            }
        });

        btAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repairRequest.setStatus(RepairRequest.Status.ACCEPTED);
                notifyDataSetChanged();
            }
        });

        tvDistrict.setText(repairRequest.getDistrict());
        tvAddress.setText(repairRequest.getAddress());
        tvSeverity.setText(repairRequest.getSeverity().toString());
        tvPrice.setText(String.format("%.2f", repairRequest.getPrice()) + " RSD");
        if(repairRequest.isCreditCard()){
            tvPaidI.setText(String.format("%.2f", repairRequest.getPaid()) + " RSD");
            btNewPayment.setVisibility(View.VISIBLE);
            tvPaid.setVisibility(View.VISIBLE);
            tvPaidI.setVisibility(View.VISIBLE);
            tvPayingWithCash.setVisibility(View.GONE);
        }
        else{
            tvPayingWithCash.setVisibility(View.VISIBLE);
            btNewPayment.setVisibility(View.GONE);
            tvPaid.setVisibility(View.GONE);
            tvPaidI.setVisibility(View.GONE);
        }

        switch (repairRequest.getStatus()){
            case ON_HOLD:{
                clPayment.setVisibility(View.GONE);
                rlDistancerPayment.setVisibility(View.GONE);
                clOffer.setVisibility(View.GONE);
                rlDistancerOffer.setVisibility(View.GONE);
                rlRateAndComment.setVisibility(View.GONE);
                rlDistancerRateAndComment.setVisibility(View.GONE);
                break;
            }
            case OFFERED:{
                clPayment.setVisibility(View.GONE);
                rlDistancerPayment.setVisibility(View.GONE);
                clOffer.setVisibility(View.VISIBLE);
                rlDistancerOffer.setVisibility(View.VISIBLE);
                rlRateAndComment.setVisibility(View.GONE);
                rlDistancerRateAndComment.setVisibility(View.GONE);
                break;
            }
            case ACCEPTED:{
                clPayment.setVisibility(View.VISIBLE);
                rlDistancerPayment.setVisibility(View.VISIBLE);
                clOffer.setVisibility(View.GONE);
                rlDistancerOffer.setVisibility(View.GONE);
                rlRateAndComment.setVisibility(View.GONE);
                rlDistancerRateAndComment.setVisibility(View.GONE);
                break;
            }
            case PAID:{
                clPayment.setVisibility(View.GONE);
                rlDistancerPayment.setVisibility(View.GONE);
                clOffer.setVisibility(View.GONE);
                rlDistancerOffer.setVisibility(View.GONE);
                rlRateAndComment.setVisibility(View.VISIBLE);
                rlDistancerRateAndComment.setVisibility(View.VISIBLE);
                break;
            }
            case REFUSED:{
                clPayment.setVisibility(View.GONE);
                rlDistancerPayment.setVisibility(View.GONE);
                clOffer.setVisibility(View.GONE);
                rlDistancerOffer.setVisibility(View.GONE);
                rlRateAndComment.setVisibility(View.GONE);
                rlDistancerRateAndComment.setVisibility(View.GONE);
                break;
            }
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
