package com.example.milan.mojmajstor.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.milan.mojmajstor.LoginActivity;
import com.example.milan.mojmajstor.R;
import com.example.milan.mojmajstor.utils.Data;
import com.example.milan.mojmajstor.utils.RepairRequest;
import com.example.milan.mojmajstor.utils.RepairRequestsCraftsmanExpandableListViewAdapter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class RepairRequestsCraftsmanFragment extends Fragment {

    private Activity thisActivity;
    private ExpandableListView elv;
    private RepairRequestsCraftsmanExpandableListViewAdapter elvAdapter;
    private ArrayList<RepairRequest> repairRequests;
    private Data data;
    private ScrollView svFilter;
    private RelativeLayout rlFilterBorderTop, rlFilterBorderBottom;
    private TextView tvShowFilter, tvHeaderDescription, tvHeaderDate, tvHeaderSeverity, tvHeaderStatus;
    private boolean filterVisible;
    private EditText etFilterDescription, etFilterClient, etFilterDistrict, etFilterAddress, etFilterDate;
    private CheckBox cbSeverityLow, cbSeverityMedium, cbSeverityHigh, cbStatusOnHold, cbStatusOffered, cbStatusAccepted, cbStatusPaid, cbStatusRefused;
    private MapView mapView;
    private ImageButton btMapOrTable;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_repair_requests_craftsman, container, false);
        mapView = view.findViewById(R.id.mapView);
        Bundle mapViewBundle = null;
        if(savedInstanceState != null){
            mapViewBundle = savedInstanceState.getBundle("MapViewBundleKey");
        }
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        repairRequests = new ArrayList<>();
        data = Data.getInstance();
        data.getCurrentUserRepairRequests(repairRequests, LoginActivity.UserType.CRAFTSMAN);
        thisActivity = getActivity();

        svFilter = view.findViewById(R.id.svFilter);
        rlFilterBorderTop = view.findViewById(R.id.rlFilterBorderTop);
        rlFilterBorderBottom = view.findViewById(R.id.rlFilterBorderBottom);
        tvShowFilter = view.findViewById(R.id.tvShowFilter);
        tvHeaderDescription = view.findViewById(R.id.tvHeaderDescription);
        tvHeaderDate = view.findViewById(R.id.tvHeaderDate);
        tvHeaderSeverity = view.findViewById(R.id.tvHeaderSeverity);
        tvHeaderStatus = view.findViewById(R.id.tvHeaderStatus);
        etFilterDescription = view.findViewById(R.id.etDescription);
        etFilterClient = view.findViewById(R.id.etClient);
        etFilterDistrict = view.findViewById(R.id.etDistrict);
        etFilterAddress = view.findViewById(R.id.etAddress);
        etFilterDate = view.findViewById(R.id.etDate);
        cbSeverityLow = view.findViewById(R.id.cbSeverityLow);
        cbSeverityMedium = view.findViewById(R.id.cbSeverityMedium);
        cbSeverityHigh = view.findViewById(R.id.cbSeverityHigh);
        cbStatusOnHold = view.findViewById(R.id.cbStatusOnHold);
        cbStatusOffered = view.findViewById(R.id.cbStatusOffered);
        cbStatusAccepted = view.findViewById(R.id.cbStatusApproved);
        cbStatusPaid = view.findViewById(R.id.cbStatusPaid);
        cbStatusRefused = view.findViewById(R.id.cbStatusRefused);
        btMapOrTable = view.findViewById(R.id.btMapOrTable);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                data.findFilteredRepairRequests(repairRequests, data.currentUser, etFilterDescription, etFilterClient, etFilterDistrict,
                        etFilterAddress, etFilterDate, cbSeverityLow, cbSeverityMedium, cbSeverityHigh,
                        cbStatusOnHold, cbStatusOffered, cbStatusAccepted, cbStatusPaid, cbStatusRefused);
                elvAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                data.findFilteredRepairRequests(repairRequests, data.currentUser, etFilterDescription, etFilterClient, etFilterDistrict,
                        etFilterAddress, etFilterDate, cbSeverityLow, cbSeverityMedium, cbSeverityHigh,
                        cbStatusOnHold, cbStatusOffered, cbStatusAccepted, cbStatusPaid, cbStatusRefused);
                elvAdapter.notifyDataSetChanged();
            }
        };

        etFilterDescription.addTextChangedListener(textWatcher);
        etFilterClient.addTextChangedListener(textWatcher);
        etFilterDistrict.addTextChangedListener(textWatcher);
        etFilterAddress.addTextChangedListener(textWatcher);
        etFilterDate.addTextChangedListener(textWatcher);
        cbSeverityLow.setOnCheckedChangeListener(onCheckedChangeListener);
        cbSeverityMedium.setOnCheckedChangeListener(onCheckedChangeListener);
        cbSeverityHigh.setOnCheckedChangeListener(onCheckedChangeListener);
        cbStatusOnHold.setOnCheckedChangeListener(onCheckedChangeListener);
        cbStatusOffered.setOnCheckedChangeListener(onCheckedChangeListener);
        cbStatusAccepted.setOnCheckedChangeListener(onCheckedChangeListener);
        cbStatusPaid.setOnCheckedChangeListener(onCheckedChangeListener);
        cbStatusRefused.setOnCheckedChangeListener(onCheckedChangeListener);

        tvHeaderDescription.setOnClickListener(new HeaderOnClickListener(0));
        tvHeaderSeverity.setOnClickListener(new HeaderOnClickListener(2));
        tvHeaderStatus.setOnClickListener(new HeaderOnClickListener(3));

        filterVisible = false;
        svFilter.setVisibility(View.GONE);
        rlFilterBorderTop.setVisibility(View.GONE);
        rlFilterBorderBottom.setVisibility(View.GONE);
        tvShowFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(filterVisible){
                    svFilter.setVisibility(View.GONE);
                    rlFilterBorderTop.setVisibility(View.GONE);
                    rlFilterBorderBottom.setVisibility(View.GONE);
                    tvShowFilter.setText(thisActivity.getResources().getString(R.string.show_filter));
                }
                else{
                    svFilter.setVisibility(View.VISIBLE);
                    rlFilterBorderTop.setVisibility(View.VISIBLE);
                    rlFilterBorderBottom.setVisibility(View.VISIBLE);
                    tvShowFilter.setText(thisActivity.getResources().getString(R.string.hide_filter));
                }
                filterVisible = !filterVisible;
            }
        });

        elv = view.findViewById(R.id.elv);
        elvAdapter = new RepairRequestsCraftsmanExpandableListViewAdapter(thisActivity, repairRequests);
        elv.setAdapter(elvAdapter);

        btMapOrTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(elv.getVisibility() == View.INVISIBLE){
                    elv.setVisibility(View.VISIBLE);
                    mapView.setVisibility(View.INVISIBLE);
                    btMapOrTable.setBackgroundResource(R.drawable.ic_map);
                }
                else{
                    elv.setVisibility(View.INVISIBLE);
                    mapView.setVisibility(View.VISIBLE);
                    btMapOrTable.setBackgroundResource(R.drawable.ic_repair_requests_primary);
                }
            }
        });

    }

    private class HeaderOnClickListener implements View.OnClickListener {

        private int sortingOption;

        public HeaderOnClickListener(int sortingOption){
            this.sortingOption = sortingOption;
        }

        @Override
        public void onClick(View v) {
            data.sortRepairRequests(repairRequests, sortingOption);
            elvAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        etFilterDescription.setText("");
        etFilterClient.setText("");
        etFilterDistrict.setText("");
        etFilterAddress.setText("");
        etFilterDate.setText("");
        cbSeverityLow.setChecked(false);
        cbSeverityMedium.setChecked(false);
        cbSeverityHigh.setChecked(false);
        cbStatusOnHold.setChecked(false);
        cbStatusOffered.setChecked(false);
        cbStatusAccepted.setChecked(false);
        cbStatusPaid.setChecked(false);
        cbStatusRefused.setChecked(false);

        data.findFilteredRepairRequests(repairRequests, data.currentUser, etFilterDescription, etFilterClient, etFilterDistrict,
                etFilterAddress, etFilterDate, cbSeverityLow, cbSeverityMedium, cbSeverityHigh,
                cbStatusOnHold, cbStatusOffered, cbStatusAccepted, cbStatusPaid, cbStatusRefused);
        for(int i = 0; i < repairRequests.size(); i++){
            elv.collapseGroup(i);
        }
        if(repairRequests.size() > 0){
            if (repairRequests.get(0).getStatus().ordinal() >= repairRequests.get(repairRequests.size() - 1).getStatus().ordinal()) {
                data.sortRepairRequests(repairRequests, 3);
            }
        } // Hack :)
        mapView.onResume();
        mapView.setVisibility(View.INVISIBLE);
        elv.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle("MapViewBundleKey");
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle("MapViewBundleKey", mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }
}
