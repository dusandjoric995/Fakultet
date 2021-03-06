package com.example.milan.mojmajstor.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.milan.mojmajstor.R;
import com.example.milan.mojmajstor.utils.RepairRequest;
import com.example.milan.mojmajstor.utils.ToastWriter;

import java.util.ArrayList;

public class RateDialog extends Dialog {

    private RepairRequest repairRequest;
    private Button btCancel;
    private Button btRate;
    private int rate;
    private ArrayList<ImageButton> buttonStars;
    private Activity thisActivity;

    public RateDialog(Activity activity, RepairRequest repairRequest){
        super(activity);
        this.repairRequest = repairRequest;
        thisActivity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_rate);

        rate = 0;
        btCancel = findViewById(R.id.btRDCancel);
        btRate = findViewById(R.id.btRDRate);
        buttonStars = new ArrayList<>();
        buttonStars.add((ImageButton) findViewById(R.id.btRDRateStar1));
        buttonStars.add((ImageButton) findViewById(R.id.btRDRateStar2));
        buttonStars.add((ImageButton) findViewById(R.id.btRDRateStar3));
        buttonStars.add((ImageButton) findViewById(R.id.btRDRateStar4));
        buttonStars.add((ImageButton) findViewById(R.id.btRDRateStar5));

        for(int i = 0; i < buttonStars.size(); i++){
            final int finalI = i;
            buttonStars.get(i).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    for(int j = buttonStars.size() - 1; j >= 0; j--){
                        if(j <= finalI){
                            buttonStars.get(j).setBackgroundResource(R.drawable.ic_rate_star_gold);
                            rate = finalI + 1;
                        }
                        else{
                            buttonStars.get(j).setBackgroundResource(R.drawable.ic_rate_star_gold_border);
                            rate = finalI + 1;
                        }
                    }
                    return true;
                }
            });
        }

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repairRequest.getCraftsman().setRateCount(repairRequest.getCraftsman().getRateCount() + 1);
                repairRequest.getCraftsman().setRate(repairRequest.getCraftsman().getRate() + rate);
                ToastWriter.write(thisActivity, thisActivity.getResources().getString(R.string.thanks_for_rating));
                dismiss();
            }
        });


    }
}
