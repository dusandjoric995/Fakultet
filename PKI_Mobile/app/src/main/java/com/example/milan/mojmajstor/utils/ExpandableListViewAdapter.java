package com.example.milan.mojmajstor.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.milan.mojmajstor.R;

import java.util.ArrayList;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private ArrayList<UserRequest> userRequests;

    @Override
    public int getGroupCount() {
        return userRequests.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return userRequests.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView tvDescription = parent.findViewById(R.id.tvURDescription);
        TextView tvCraftsman = parent.findViewById(R.id.tvURCraftsman);
        TextView tvDate = parent.findViewById(R.id.tvURDate);
        TextView tvStatus = parent.findViewById(R.id.tvURStatus);
        TextView cbSelected = parent.findViewById(R.id.cbURSelected);
        TextView checkBoxArrayList.add(cbSelected);

        tvDescription.setText(userRequest.getDescription());
        tvCraftsman.setText(userRequest.getCraftsman().getNameAndSurname());
        tvDate.setText(userRequest.getDate());
        tvStatus.setText(userRequest.getStatus());

        cbSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    checked_count++;
                    btAdditionalOptions.setClickable(true);
                    btAdditionalOptions.setImageResource(android.R.drawable.ic_menu_more);
                } else {
                    checked_count--;
                    if (checked_count == 0) {
                        btAdditionalOptions.setClickable(false);
                        btAdditionalOptions.setImageResource(android.R.color.transparent);
                    }
                }
            }
        });
        TableRow tableRow = tableRowLayout.findViewById(R.id.trUR);
        if (even) {
            tableRow.setBackgroundResource(R.drawable.table_row_even);
            even = false;
        } else {
            tableRow.setBackgroundResource(R.drawable.table_row_odd);
            even = true;
        }
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
