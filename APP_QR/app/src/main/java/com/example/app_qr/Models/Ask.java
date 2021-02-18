package com.example.app_qr.Models;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.app_qr.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ask {


    public Ask( ) {

    }

    String ask;
    String req0;
    String req1;
    String req2;
    String req3;
    String reqCorrect;
    int imgReq;



    public Ask(String ask, String req0, String req1, String req2,String req3, String reqCorrect, int imgReq) {
        this.ask = ask;
        this.req0 = req0;
        this.req1 = req1;
        this.req2 = req2;
        this.req3 = req3;
        this.reqCorrect = reqCorrect;
        this.imgReq = imgReq;
    }


    public Ask(String ask,String req0, String req1, String req2, String req3, String reqCorrect) {
        this.ask = ask;
        this.req0 = req0;
        this.req1 = req1;
        this.req2 = req2;
        this.req3 = req3;
        this.reqCorrect = reqCorrect;
    }



    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getReq0() {
        return req0;
    }

    public void setReq0(String req0) {
        this.req0 = req0;
    }

    public String getReq1() {
        return req1;
    }

    public void setReq1(String req1) {
        this.req1 = req1;
    }

    public String getReq2() {
        return req2;
    }

    public void setReq2(String req2) {
        this.req2 = req2;
    }

    public String getReq3() {
        return req3;
    }

    public void setReq3(String req3) {
        this.req3 = req3;
    }

    public String getReqCorrect() {
        return reqCorrect;
    }

    public void setReqCorrect(String reqCorrect) {
        this.reqCorrect = reqCorrect;
    }

    public int getImgReq() {
        return imgReq;
    }

    public void setImgReq(int imgReq) {
        this.imgReq = imgReq;
    }




    @Override
    public String toString() {
        return "Ask{" +
                "ask='" + ask + '\'' +
                ", req0='" + req0 + '\'' +
                ", req1='" + req1 + '\'' +
                ", req2='" + req2 + '\'' +
                ", req3='" + req3 + '\'' +
                ", reqCorrect='" + reqCorrect + '\'' +
                ", imgReq=" + imgReq +
                '}';
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ask ask1 = (Ask) o;
        return imgReq == ask1.imgReq &&
                Objects.equals(ask, ask1.ask) &&
                Objects.equals(req0, ask1.req0) &&
                Objects.equals(req1, ask1.req1) &&
                Objects.equals(req2, ask1.req2) &&
                Objects.equals(req3, ask1.req3) &&
                Objects.equals(reqCorrect, ask1.reqCorrect);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(ask, req0, req1, req2, req3, reqCorrect, imgReq);
    }
}
