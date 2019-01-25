package com.example.milan.mojmajstor.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
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

        btCancel = findViewById(R.id.btNRCancel);
        btNewRequest = findViewById(R.id.btNRConfirmRequest);
        etDescription = findViewById(R.id.etNRDescription);
        etDistrict = findViewById(R.id.etNRDistrict);
        etAddress = findViewById(R.id.etNRAddress);
        rbCash = findViewById(R.id.rbNRCash);
        rbLow = findViewById(R.id.rbNRLow);
        rg = findViewById(R.id.rgNRSeverity);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case 0:{
                        severity = RepairRequest.Severity.LOW;
                        break;
                    }
                    case 1:{
                        severity = RepairRequest.Severity.MEDIUM;
                        break;
                    }
                    case 2:{
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
                data.repairRequests.add(new RepairRequest(etDescription.getText().toString(), craftsman, "10.10.2018", thisActivity.getResources().getString(R.string.repair_status_on_hold), data.currentUser, etDistrict.getText().toString(), etAddress.getText().toString(), !rbCash.isChecked(), 0, 0, severity));
                ToastWriter.write(thisActivity, thisActivity.getResources().getString(R.string.request_sent));
                dismiss();
            }
        });
    }


}