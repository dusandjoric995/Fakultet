package com.example.milan.mojmajstor.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.milan.mojmajstor.R;
import com.example.milan.mojmajstor.dialogs.NewRequestDialog;
import com.example.milan.mojmajstor.utils.CommentsListViewAdapter;
import com.example.milan.mojmajstor.utils.Data;
import com.example.milan.mojmajstor.utils.User;

public class ProfilePictureFragment extends Fragment {

    private ImageButton btBack;
    private TextView tvProfileName;
    private TextView tvProfession;
    private TextView tvEmailI;
    private TextView tvRateValue;
    private TextView tvPassword;
    private TextView tvShowComments;
    private EditText etProfessionI;
    private EditText etEmailI;
    private EditText etPasswordI;
    private ImageView ivRateStar;
    private Button btRequestRepairOrEditProfile;
    private Button btSaveChanges;
    private Data data;
    private User selectedUser;
    private Activity thisActivity;
    private ListView lvComments;
    private CommentsListViewAdapter lvCommentsAdapter;
    private boolean showComments;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        tvProfileName = view.findViewById(R.id.tvPProfileName);
        tvProfession = view.findViewById(R.id.tvPProfessionI);
        tvEmailI = view.findViewById(R.id.tvPEmailI);
        tvRateValue = view.findViewById(R.id.tvPRateValue);
        tvShowComments = view.findViewById(R.id.tvPShowComments);
        tvPassword = view.findViewById(R.id.tvPPassword);
        etProfessionI = view.findViewById(R.id.etPProfessionI);
        etEmailI = view.findViewById(R.id.etPEmailI);
        etPasswordI = view.findViewById(R.id.etPPasswordI);
        ivRateStar = view.findViewById(R.id.ivPRateStar);
        btRequestRepairOrEditProfile = view.findViewById(R.id.btPRequestRepairOrEditProfile);
        btSaveChanges = view.findViewById(R.id.btPSaveChanges);
        data = Data.getInstance();
        thisActivity = getActivity();
        selectedUser = (User) getArguments().getSerializable(thisActivity.getResources().getString(R.string.selected_user));
        lvComments = view.findViewById(R.id.lvPComments);
        lvCommentsAdapter = new CommentsListViewAdapter(thisActivity, selectedUser.getComments());
        lvComments.setAdapter(lvCommentsAdapter);

        tvProfileName.setText(selectedUser.getFirstAndLastName());
        tvProfession.setText(selectedUser.getProfession());
        tvEmailI.setText(selectedUser.getEmail());
        tvRateValue.setText(String.format("%.1f", (selectedUser.getRateCount() == 0) ? 0 : selectedUser.getRate() / selectedUser.getRateCount() ));
        showComments = false;
        lvComments.setVisibility(View.INVISIBLE);

        tvShowComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!showComments){
                    lvComments.setVisibility(View.VISIBLE);
                    tvShowComments.setText(getResources().getString(R.string.hide_comments));
                    showComments = true;
                }
                else {
                    lvComments.setVisibility(View.INVISIBLE);
                    tvShowComments.setText(getResources().getString(R.string.show_comments));
                    showComments = false;
                }
            }
        });

        tvProfession.setVisibility(View.VISIBLE);
        tvEmailI.setVisibility(View.VISIBLE);
        etProfessionI.setVisibility(View.INVISIBLE);
        etEmailI.setVisibility(View.INVISIBLE);
        btSaveChanges.setVisibility(View.INVISIBLE);
        tvPassword.setVisibility(View.GONE);
        etPasswordI.setVisibility(View.GONE);

        if(selectedUser == data.currentUser){
            btRequestRepairOrEditProfile.setText(getResources().getString(R.string.edit_profile));
            btRequestRepairOrEditProfile.setBackgroundResource(R.drawable.button_primary);
            btRequestRepairOrEditProfile.setVisibility(View.VISIBLE);
            btRequestRepairOrEditProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvProfession.setVisibility(View.INVISIBLE);
                    tvEmailI.setVisibility(View.INVISIBLE);
                    btRequestRepairOrEditProfile.setVisibility(View.INVISIBLE);
                    etProfessionI.setVisibility(View.VISIBLE);
                    etEmailI.setVisibility(View.VISIBLE);
                    btSaveChanges.setVisibility(View.VISIBLE);
                    tvPassword.setVisibility(View.VISIBLE);
                    etPasswordI.setVisibility(View.VISIBLE);

                    etProfessionI.setText(tvProfession.getText().toString());
                    etEmailI.setText(tvEmailI.getText().toString());
                    etPasswordI.setText(data.currentUser.getPassword());
                }
            });
            btSaveChanges.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    tvProfession.setVisibility(View.VISIBLE);
                    tvEmailI.setVisibility(View.VISIBLE);
                    btRequestRepairOrEditProfile.setVisibility(View.VISIBLE);
                    etProfessionI.setVisibility(View.INVISIBLE);
                    etEmailI.setVisibility(View.INVISIBLE);
                    btSaveChanges.setVisibility(View.INVISIBLE);
                    tvPassword.setVisibility(View.GONE);
                    etPasswordI.setVisibility(View.GONE);

                    data.currentUser.setEmail(etEmailI.getText().toString());
                    data.currentUser.setProfession(etProfessionI.getText().toString());
                    data.currentUser.setPassword(etPasswordI.getText().toString());
                    tvProfession.setText(data.currentUser.getProfession());
                    tvEmailI.setText(data.currentUser.getEmail());
                }
            });
        }
        else{
            btRequestRepairOrEditProfile.setText(getResources().getString(R.string.request_repair));
            btRequestRepairOrEditProfile.setBackgroundResource(R.drawable.button_accent);
            switch (selectedUser.getUserType()){
                case USER:{
                    btRequestRepairOrEditProfile.setVisibility(View.GONE);
                    break;
                }
                case CRAFTSMAN:{
                    btRequestRepairOrEditProfile.setVisibility(View.VISIBLE);
                    btRequestRepairOrEditProfile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new NewRequestDialog(thisActivity, selectedUser).show();
                        }
                    });
                    break;
                }
            }
        }


    }


}
