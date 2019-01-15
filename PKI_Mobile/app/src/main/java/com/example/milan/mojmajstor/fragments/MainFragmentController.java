package com.example.milan.mojmajstor.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.milan.mojmajstor.R;

public class MainFragmentController {

    private static FragmentManager fragmentManager;
    private static BackButtonFragment backButtonFragment;
    public static RepairRequestsFragment userRequestFragment;
    public static CraftsmanRequestFragment craftsmanRequestFragment;
    public static RepairRequestFragmentLegacy repairRequestFragment;

    public static void initialize(FragmentManager fm){
        fragmentManager = fm;
        userRequestFragment = new RepairRequestsFragment();
        craftsmanRequestFragment = new CraftsmanRequestFragment();
        repairRequestFragment = new RepairRequestFragmentLegacy();
        backButtonFragment = new BackButtonFragment();
    }

    public static void setMainFragment(Fragment fragment, Bundle bundle){
        if(bundle != null){
            fragment.setArguments(bundle);
        }
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
    }

    public static void showLastMainFragment(){
        fragmentManager.popBackStack();
    }

    public static void setBackButton(){
        fragmentManager.beginTransaction().replace(R.id.rlBackButton, backButtonFragment).commit();
    }

    public static void removeBackButton(){
        fragmentManager.beginTransaction().remove(backButtonFragment).commit();
    }

    public static boolean fragmentBackStackEmpty() {
        return fragmentManager.getBackStackEntryCount() == 1;
    }

    public static void clearBackStack(){
        for(int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
            fragmentManager.popBackStack();
        }
    }
}
