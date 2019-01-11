package com.example.milan.mojmajstor.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.milan.mojmajstor.R;
import com.example.milan.mojmajstor.utils.Data;
import com.example.milan.mojmajstor.utils.UserRequest;

import java.util.ArrayList;

public class UserRequestFragment extends Fragment {

    private TextView tvDescription;
    private TextView tvCraftsman;
    private TextView tvDate;
    private TextView tvStatus;
    private CheckBox cbSelected;
    private TableLayout tableLayout;
    private TableLayout tableRowLayout;
    private ImageButton btAdditionalOptions;
    private PopupMenu.OnMenuItemClickListener popupMenuListener;
    private ArrayList<CheckBox> checkBoxArrayList;
    private View fragmentView;
    boolean even;
    int checked_count;
    private Data data;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_user_request, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        fragmentView = view;
        tableLayout = fragmentView.findViewById(R.id.tlUserRequests);
        btAdditionalOptions = fragmentView.findViewById(R.id.btURAdditionalOptions);
        data = Data.getInstance();
        even = true;
        popupMenuListener = new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.menu_item_ur_delete:{
                        ArrayList<Integer> toRemove = new ArrayList<>();
                        for(int i = 0, j = 0; i < checkBoxArrayList.size(); i++, j++){
                            if(checkBoxArrayList.get(i).isChecked()){
                                data.userRequests.remove(j);
                                j--;
                            }
                        }
                        inflateTable(fragmentView, data.userRequests);
                        return false;
                    }
                    default:{
                        return false;
                    }
                }
            }
        };
        checkBoxArrayList = new ArrayList<>();

        btAdditionalOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), btAdditionalOptions);
                popupMenu.setOnMenuItemClickListener(popupMenuListener);
                popupMenu.inflate(R.menu.menu_ur_additional_options);
                popupMenu.show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        inflateTable(fragmentView, data.userRequests);
    }

    public void inflateTable(View view, ArrayList<UserRequest> userRequests){
        checked_count = 0;
        tableLayout.removeAllViews();
        checkBoxArrayList.clear();
        btAdditionalOptions.setClickable(false);
        btAdditionalOptions.setImageResource(android.R.color.transparent);
        for(final UserRequest userRequest : userRequests){
            tableRowLayout = (TableLayout) view.inflate(getActivity(), R.layout.table_row_user_request, null);
            tvDescription = tableRowLayout.findViewById(R.id.tvURDescription);
            tvCraftsman = tableRowLayout.findViewById(R.id.tvURCraftsman);
            tvDate = tableRowLayout.findViewById(R.id.tvURDate);
            tvStatus = tableRowLayout.findViewById(R.id.tvURStatus);
            cbSelected = tableRowLayout.findViewById(R.id.cbURSelected);
            checkBoxArrayList.add(cbSelected);

            if(even){
                tableRowLayout.setBackgroundResource(R.color.rowEven);
                even = false;
            }
            else{
                tableRowLayout.setBackgroundResource(R.color.rowOdd);
                even = true;
            }

            tvDescription.setText(userRequest.getDescription());
            tvCraftsman.setText(userRequest.getCraftsman().getNameAndSurname());
            tvDate.setText(userRequest.getDate());
            tvStatus.setText(userRequest.getStatus());

            cbSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        checked_count++;
                        btAdditionalOptions.setClickable(true);
                        btAdditionalOptions.setImageResource(android.R.drawable.ic_menu_more);
                    }
                    else{
                        checked_count--;
                        if(checked_count == 0){
                            btAdditionalOptions.setClickable(false);
                            btAdditionalOptions.setImageResource(android.R.color.transparent);
                        }
                    }
                }
            });
            TableRow tableRow = tableRowLayout.findViewById(R.id.trUR);
            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("UserRequest", userRequest);
                    MainFragmentController.setMainFragment(MainFragmentController.repairRequestFragment, bundle);
                }
            });

            tableLayout.addView(tableRowLayout);
        }
    }
}
