package com.example.emptyactivity;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment1 extends Fragment {


    public TabFragment1() {
        // Required empty public constructor
    }

    //layout inflater converts XML file into corresponding viewgroups and widgets
    //attachToRoot set to true/false, meaning XML inflated elems attach to ViewGroup and its
    //properties, true used to do it automatically, false means it's unattached but can
    //later be manually added
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tab_fragment1, container, false);
    }

}
