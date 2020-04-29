package com.example.zigzag.src.main.zzim;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zigzag.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ZzimFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ZzimFragment extends Fragment {


    public ZzimFragment() {
        // Required empty public constructor
    }


    public static ZzimFragment newInstance(String param1, String param2) {
        ZzimFragment fragment = new ZzimFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_zzim, container, false);
    }
}
