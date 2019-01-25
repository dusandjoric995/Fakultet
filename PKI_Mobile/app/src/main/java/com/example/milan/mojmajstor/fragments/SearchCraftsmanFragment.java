package com.example.milan.mojmajstor.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TableLayout;

import com.example.milan.mojmajstor.R;
import com.example.milan.mojmajstor.utils.Data;
import com.example.milan.mojmajstor.utils.SearchCraftsmanListViewAdapter;
import com.example.milan.mojmajstor.utils.User;

import java.util.ArrayList;

public class SearchCraftsmanFragment extends Fragment {

    private TableLayout tl;
    private ListView lv;
    private SearchCraftsmanListViewAdapter lvAdapter;
    private Activity thisActivity;
    private Data data;
    private EditText etFirstOrLastName;
    private EditText etProfession;
    private RadioButton rbAllWorkers;
    private ArrayList<User> selectedCraftsmen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_search_carftsman, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        data = Data.getInstance();
        thisActivity = getActivity();
        tl = view.findViewById(R.id.tlSC);
        lv = tl.findViewById(R.id.lvSC);
        etFirstOrLastName = view.findViewById(R.id.etSCFirstOrLastName);
        etProfession = view.findViewById(R.id.etSCProfession);
        rbAllWorkers = view.findViewById(R.id.rbSCAllWorkers);
        selectedCraftsmen = new ArrayList<>();

        data.findSelectedCraftsmen(selectedCraftsmen, "", "", null);
        lvAdapter = new SearchCraftsmanListViewAdapter(thisActivity, selectedCraftsmen);
        lv.setAdapter(lvAdapter);

        rbAllWorkers.setChecked(true);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                data.findSelectedCraftsmen(selectedCraftsmen, etFirstOrLastName.getText().toString(), etProfession.getText().toString(), rbAllWorkers.isChecked() ? null : data.currentUser);
                lvAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        etFirstOrLastName.addTextChangedListener(textWatcher);
        etProfession.addTextChangedListener(textWatcher);
        rbAllWorkers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                data.findSelectedCraftsmen(selectedCraftsmen, etFirstOrLastName.getText().toString(), etProfession.getText().toString(), isChecked ? null : data.currentUser);
                lvAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        etFirstOrLastName.setText("");
        etProfession.setText("");
        data.findSelectedCraftsmen(selectedCraftsmen, "", "", null);
        rbAllWorkers.setChecked(true);
        lvAdapter.notifyDataSetChanged();

    }
}
