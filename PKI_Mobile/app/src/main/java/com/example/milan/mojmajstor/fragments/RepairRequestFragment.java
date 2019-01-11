package com.example.milan.mojmajstor.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.milan.mojmajstor.R;
import com.example.milan.mojmajstor.utils.Data;
import com.example.milan.mojmajstor.utils.UserRequest;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class RepairRequestFragment extends Fragment {

    private View thisView;
    private TextView tvClient;
    private TextView tvCraftsman;
    private TextView tvDate;
    private TextView tvDistrict;
    private TextView tvAddress;
    private TextView tvDescription;
    private TextView tvStatus;
    private TextView tvPaid;
    private Button btPay;
    private UserRequest userRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        thisView = inflater.inflate(R.layout.fragment_repair_request, container, false);
        return thisView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        tvClient = thisView.findViewById(R.id.tvRRClientI);
        tvCraftsman = thisView.findViewById(R.id.tvRRCraftsmanI);
        tvDate = thisView.findViewById(R.id.tvRRDateI);
        tvDistrict = thisView.findViewById(R.id.tvRRDistrictI);
        tvAddress = thisView.findViewById(R.id.tvRRAddressI);
        tvDescription = thisView.findViewById(R.id.tvRRDescriptionI);
        tvStatus = thisView.findViewById(R.id.tvRRStatusI);
        tvPaid = thisView.findViewById(R.id.tvRRPaidI);
        btPay = thisView.findViewById(R.id.btRRPay);
        userRequest = (UserRequest) getArguments().getSerializable("UserRequest");
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
        tvStatus.setText(userRequest.getStatus());
        if(userRequest.isCreditCard()){
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            Double correctValue = Double.parseDouble(decimalFormat.format(userRequest.getPaid()));
            tvPaid.setText(correctValue + " RSD");
            btPay.setVisibility(View.VISIBLE);
        }
        else{
            tvPaid.setText(getActivity().getResources().getString(R.string.paying_with_cash));
            btPay.setVisibility(View.GONE);
        }
    }
}
