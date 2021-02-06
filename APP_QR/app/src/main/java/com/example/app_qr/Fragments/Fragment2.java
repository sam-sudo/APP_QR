package com.example.app_qr.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app_qr.Models.Ask;
import com.example.app_qr.R;

public class Fragment2 extends Fragment {

    RadioButton req0;
    RadioButton req1;
    RadioButton req2;
    ImageView imgReq;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2,container,false);

        req0 = view.findViewById(R.id.req0);
        req1 = view.findViewById(R.id.req1);
        req2 = view.findViewById(R.id.req2);
        imgReq = view.findViewById(R.id.imgReq);


        Ask ask = getAsk();
        generateAsk(ask);




        return view;

    }

    private void generateAsk(Ask ask) {
        String tex0 = ask.getReq0();
        String tex1 = ask.getReq1();
        String tex2 = ask.getReq2();
        int img = ask.getImgReq();

        req0.setText(tex0);
        req1.setText(tex1);
        req2.setText(tex2);
        imgReq.setImageResource(img);

    }

    private Ask getAsk() {
        Log.d("samy", "generateAsk: aaaask");

        return new Ask("eee","hola","que tal");
    }
}
