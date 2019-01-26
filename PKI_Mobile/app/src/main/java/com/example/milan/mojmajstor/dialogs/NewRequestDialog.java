package com.example.milan.mojmajstor.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.milan.mojmajstor.R;
import com.example.milan.mojmajstor.utils.Data;
import com.example.milan.mojmajstor.utils.RepairRequest;
import com.example.milan.mojmajstor.utils.ToastWriter;
import com.example.milan.mojmajstor.utils.User;

public class NewRequestDialog extends Dialog {

    private Button btCancel;
    private Button btNewRequest;
    private EditText etDescription;
    private EditText etDistrict;
    private EditText etAddress;
    private RadioButton rbCash;
    private RadioButton rbLow;
    private RadioGroup rg;
    private Activity thisActivity;
    private Data data;
    private User craftsman;
    private RepairRequest.Severity severity;

    public NewRequestDialog(Activity activity, User craftsman){
        super(activity);
        thisActivity = activity;
        this.craftsman = craftsman;
        data = Data.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_new_request);

        btCancel = findViewById(R.id.btSFCancel);
        btNewRequest = findViewById(R.id.btSFConfirm);
        etDescription = findViewById(R.id.etSFDescription);
        etDistrict = findViewById(R.id.etSFClient);
        etAddress = findViewById(R.id.etSFLocation);
        rbCash = findViewById(R.id.rbNRCash);
        rbLow = findViewById(R.id.rbNRLow);
        rg = findViewById(R.id.rgNRSeverity);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rbNRLow:{
                        severity = RepairRequest.Severity.LOW;
                        break;
                    }
                    case R.id.rbNRMedium:{
                        severity = RepairRequest.Severity.MEDIUM;
                        break;
                    }
                    case R.id.rbNRHigh:{
                        severity = RepairRequest.Severity.HIGH;
                        break;
                    }
                }
            }
        });

        rbCash.setChecked(true);
        rbLow.setChecked(true);
        severity = RepairRequest.Severity.LOW;

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btNewRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.repairRequests.add(new RepairRequest(etDescription.getText().toString(), craftsman, "10.10.2018", RepairRequest.Status.ON_HOLD, data.currentUser, etDistrict.getText().toString(), etAddress.getText().toString(), !rbCash.isChecked(), 0, 0, severity));
                ToastWriter.write(thisActivity, thisActivity.getResources().getString(R.string.request_sent));
                dismiss();
            }
        });
    }


}