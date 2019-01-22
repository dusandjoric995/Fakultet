package com.example.milan.mojmajstor.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.milan.mojmajstor.R;
import com.example.milan.mojmajstor.utils.Data;
import com.example.milan.mojmajstor.utils.User;

public class ProfilePictureFragment extends Fragment {

    private ImageButton btBack;
    TextView tvProfileName;
    TextView tvProfession;
    TextView tvEmailI;
    TextView tvRateValue;
    ImageView ivRateStar;
    Button btRequestRepairOrEditProfile;
    Data data;
    private User selectedUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        tvProfileName = view.findViewById(R.id.tvPProfileName);
        tvProfession = view.findViewById(R.id.tvPProfession);
        tvEmailI = view.findViewById(R.id.tvPEmailI);
        tvRateValue = view.findViewById(R.id.tvPRateValue);
        ivRateStar = view.findViewById(R.id.ivPRateStar);
        btRequestRepairOrEditProfile = view.findViewById(R.id.btPRequestRepairOrEditProfile);
        data = Data.getInstance();
        selectedUser = (User) getArguments().getSerializable("selectedUser");

        tvProfileName.setText(selectedUser.getFirstAndLastName());
        tvProfession.setText(selectedUser.getProfession());
        tvEmailI.setText(selectedUser.getEmail());
        tvRateValue.setText(String.format("%.1f", (selectedUser.getRateCount() == 0) ? 0 : selectedUser.getRate() / selectedUser.getRateCount() ));

        if(getArguments().getSerializable("selectedUser") == data.currentUser){
            btRequestRepairOrEditProfile.setText(getResources().getString(R.string.edit_profile));
            btRequestRepairOrEditProfile.setVisibility(View.VISIBLE);
            btRequestRepairOrEditProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        else{
            btRequestRepairOrEditProfile.setText(getResources().getString(R.string.request_repair));
            switch (data.currentUser.getUserType()){
                case USER:{
                    btRequestRepairOrEditProfile.setVisibility(View.GONE);
                    break;
                }
                case CRAFTSMAN:{
                    btRequestRepairOrEditProfile.setVisibility(View.VISIBLE);
                    btRequestRepairOrEditProfile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    break;
                }
            }
        }


    }


}
