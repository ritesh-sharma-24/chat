package com.ritesh.chat.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ritesh.chat.Fragments.ChatFragment;
//import com.ritesh.chat.Fragments.StatusFragment;

public class FragmentsAdapter extends FragmentPagerAdapter {


    public FragmentsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new ChatFragment();
            //case 1: return new StatusFragment();
            default: return new ChatFragment();

        }

    }

    @Override
    public int getCount() {
        return 1;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String tittle = null;
        if (position == 0){

            tittle = "CHATS";

        }
//        if (position == 1){
//
//            tittle = "GROUP";
//
//        }


        return tittle;
    }
}
