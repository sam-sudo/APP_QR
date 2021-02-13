package com.example.app_qr;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.app_qr.Models.Ask;
import com.example.app_qr.R;

public class Ask_activity extends AppCompatActivity {

    RadioButton req0;
    RadioButton req1;
    RadioButton req2;
    ImageView imgReq;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment2);

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.req0:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.req1:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.req2:
                if (checked)
                    // Ninjas rule
                    break;
        }

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

        return new Ask("¿Que provincia es?","x","xx","xxx","Cádiz","Cádiz");
    }
}
