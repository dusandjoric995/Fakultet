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
import com.example.milan.mojmajstor.dialogs.PaymentDialog;
import com.example.milan.mojmajstor.dialogs.RateDialog;
import com.example.milan.mojmajstor.utils.UserRequest;

import java.text.DecimalFormat;

public class RepairRequestFragment extends Fragment {

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
    private UserRequest userRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        thisView = inflater.inflate(R.layout.fragment_repair_request, container, false);
        return thisView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        MainFragmentController.setBackButton();

        tvClient = thisView.findViewById(R.id.tvRRClientI);
        tvCraftsman = thisView.findViewById(R.id.tvRRCraftsmanI);
        tvDate = thisView.findViewById(R.id.tvRRDateI);
        tvDistrict = thisView.findViewById(R.id.tvRRDistrictI);
        tvAddress = thisView.findViewById(R.id.tvRRAddressI);
        tvDescription = thisView.findViewById(R.id.tvRRDescriptionI);
        tvPrice = thisView.findViewById(R.id.tvRRPriceI);
        tvStatus = thisView.findViewById(R.id.tvRRStatusI);
        tvPaid = thisView.findViewById(R.id.tvRRPaidI);
        btNewPayment = thisView.findViewById(R.id.btRRPay);
        btRateCraftsman = thisView.findViewById(R.id.btRRRateCraftsman);
        btCommentCraftsman = thisView.findViewById(R.id.btRRCommentCraftsman);
        userRequest = (UserRequest) getArguments().getSerializable("UserRequest");

        btNewPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PaymentDialog(getActivity(), userRequest, tvPaid, tvStatus, btNewPayment).show();
            }
        });

        btRateCraftsman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RateDialog(getActivity(), userRequest).show();
            }
        });

        btCommentCraftsman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CommentDialog(getActivity(), userRequest).show();
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        tvClient.setText(userRequest.getClient().getNameAndSurname());
        tvCraftsman.setText(userRequest.getCraftsman().getNameAndSurname());
        tvDate.setText(userRequest.getDate());
        tvDistrict.setText(userRequest.getDistrict());
        tvAddress.setText(userRequest.getAddress());
        tvDescription.setText(userRequest.getDescription());
        tvPrice.setText(String.format("%.2f", userRequest.getPrice()) + " RSD");
        tvStatus.setText(userRequest.getStatus());
        if(userRequest.isCreditCard()){
            tvPaid.setText(String.format("%.2f", userRequest.getPaid()) + " RSD");
            btNewPayment.setVisibility(View.VISIBLE);
        }
        else{
            tvPaid.setText(getActivity().getResources().getString(R.string.paying_with_cash));
            btNewPayment.setVisibility(View.GONE);
        }
        if(userRequest.getPaid() == userRequest.getPrice()){
            btNewPayment.setEnabled(false);
        }
        if(userRequest.getStatus().equals(getActivity().getResources().getString(R.string.repair_status_refused))){
            btNewPayment.setEnabled(false);
            btRateCraftsman.setEnabled(false);
            btCommentCraftsman.setEnabled(false);
        }
    }
}
