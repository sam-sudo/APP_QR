package com.example.app_qr.Fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.app_qr.R;

public class Fragment1 extends Fragment {
    NumberPicker numberPicker1, numberPicker2, numberPicker3, numberPicker4, numberPicker5, numberPicker6;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,container,false);

        numberPicker1 = (NumberPicker)view.findViewById(R.id.numberPicker1);
        numberPicker2 = (NumberPicker)view.findViewById(R.id.numberPicker2);
        numberPicker3 = (NumberPicker)view.findViewById(R.id.numberPicker3);
        numberPicker4 = (NumberPicker)view.findViewById(R.id.numberPicker4);
        numberPicker5 = (NumberPicker)view.findViewById(R.id.numberPicker5);
        numberPicker6 = (NumberPicker)view.findViewById(R.id.numberPicker6);

        numberPicker1.setMaxValue(9);
        numberPicker2.setMaxValue(9);
        numberPicker3.setMaxValue(9);
        numberPicker4.setMaxValue(9);
        numberPicker5.setMaxValue(9);
        numberPicker6.setMaxValue(9);

        return view;
    }


}
