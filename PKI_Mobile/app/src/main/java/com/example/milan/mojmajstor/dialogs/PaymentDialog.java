package com.example.milan.mojmajstor.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.milan.mojmajstor.R;
import com.example.milan.mojmajstor.utils.RepairRequestsUserExpandableListViewAdapter;
import com.example.milan.mojmajstor.utils.ToastWriter;
import com.example.milan.mojmajstor.utils.RepairRequest;

public class PaymentDialog extends Dialog {

    private RepairRequest repairRequest;
    private Button btCancel;
    private Button btPay;
    private Button btNewPaymenn;
    private EditText etAmount;
    private TextView tvPaid;
    private TextView tvStatus;
    private Activity thisActivity;
    private RepairRequestsUserExpandableListViewAdapter adapter;

    public PaymentDialog(Activity activity, RepairRequest repairRequest, TextView tvPaid, RepairRequestsUserExpandableListViewAdapter adapter, Button btNewPayment){
        super(activity);
        thisActivity = activity;
        this.repairRequest = repairRequest;
        this.tvPaid = tvPaid;
        this.tvStatus = tvStatus;
        this.btNewPaymenn = btNewPayment;
        this.adapter = adapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_payment);

        etAmount = findViewById(R.id.etAmount);
        btCancel = findViewById(R.id.btPDCancel);
        btPay = findViewById(R.id.btPDPay);

        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    btPay.setEnabled(false);
                }
                else{
                    btPay.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btPay.setEnabled(false);
        btPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double newPayment = Double.parseDouble(etAmount.getText().toString());
                if(repairRequest.getPaid() + newPayment >= repairRequest.getPrice()){
                    repairRequest.setPaid(repairRequest.getPrice());
                    repairRequest.setStatus(RepairRequest.Status.PAID);
                    btNewPaymenn.setEnabled(false);
                    adapter.notifyDataSetChanged();
                }
                else{
                    repairRequest.setPaid(repairRequest.getPaid() + newPayment);
                }
                tvPaid.setText(String.format("%.2f", repairRequest.getPaid()) + " RSD");
                ToastWriter.write(thisActivity, thisActivity.getResources().getString(R.string.payment_successful));
                dismiss();
            }
        });
    }
}
