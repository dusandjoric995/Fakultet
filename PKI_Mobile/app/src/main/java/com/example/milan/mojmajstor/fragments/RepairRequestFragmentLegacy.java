package com.example.milan.mojmajstor.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.milan.mojmajstor.R;
import com.example.milan.mojmajstor.dialogs.CommentDialog;
import com.example.milan.mojmajstor.dialogs.RateDialog;
import com.example.milan.mojmajstor.utils.RepairRequest;

public class RepairRequestFragmentLegacy extends Fragment {

    private View thisView;
    private TextView tvClient;
    private TextView tvCraftsman;
    private TextView tvDate;
    private TextView tvDistrict;
    private TextView tvAddress;
    private TextView tvDescription;
    private TextView tvPrice;
    private TextView tvStatus;
    private TextView tvPaid;
    private Button btNewPayment;
    private Button btRateCraftsman;
    private Button btCommentCraftsman;
    private RepairRequest repairRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        thisView = inflater.inflate(R.layout.fragment_repair_request_legacy, container, false);
        return thisView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        MainFragmentController.setBackButton();

        tvDistrict = thisView.findViewById(R.id.tvRRCDistrictI);
        tvAddress = thisView.findViewById(R.id.tvRRCAddressI);
        tvPrice = thisView.findViewById(R.id.tvRRCPriceI);
        tvPaid = thisView.findViewById(R.id.tvRRCPaidI);
        btNewPayment = thisView.findViewById(R.id.btRRCPay);
        btRateCraftsman = thisView.findViewById(R.id.btRRCRateCraftsman);
        btCommentCraftsman = thisView.findViewById(R.id.btRRCCommentCraftsman);
        repairRequest = (RepairRequest) getArguments().getSerializable("RepairRequest");

        btNewPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new PaymentDialog(getActivity(), repairRequest, tvPaid, tvStatus, btNewPayment).show();
            }
        });

        btRateCraftsman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RateDialog(getActivity(), repairRequest).show();
            }
        });

        btCommentCraftsman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CommentDialog(getActivity(), repairRequest).show();
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        tvClient.setText(repairRequest.getClient().getFirstAndLastName());
        tvCraftsman.setText(repairRequest.getCraftsman().getFirstAndLastName());
        tvDate.setText(repairRequest.getDate());
        tvDistrict.setText(repairRequest.getDistrict());
        tvAddress.setText(repairRequest.getAddress());
        tvDescription.setText(repairRequest.getDescription());
        tvPrice.setText(String.format("%.2f", repairRequest.getPrice()) + " RSD");
        tvStatus.setText(repairRequest.getStatus());
        if(repairRequest.isCreditCard()){
            tvPaid.setText(String.format("%.2f", repairRequest.getPaid()) + " RSD");
            btNewPayment.setVisibility(View.VISIBLE);
        }
        else{
            tvPaid.setText(getActivity().getResources().getString(R.string.paying_with_cash));
            btNewPayment.setVisibility(View.GONE);
        }
        if(repairRequest.getPaid() == repairRequest.getPrice()){
            btNewPayment.setEnabled(false);
        }
        if(repairRequest.getStatus().equals(getActivity().getResources().getString(R.string.repair_status_refused))){
            btNewPayment.setEnabled(false);
            btRateCraftsman.setEnabled(false);
            btCommentCraftsman.setEnabled(false);
        }
    }
}
