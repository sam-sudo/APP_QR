package com.example.app_qr.Models;


import com.example.app_qr.R;

public class Ask {

    String req0;
    String req1;
    String req2;
    int imgReq;

    public Ask(String req0, String req1, String req2, int imgReq) {
        this.req0 = req0;
        this.req1 = req1;
        this.req2 = req2;
        this.imgReq = imgReq;
    }

    public Ask(String req0, String req1, String req2) {
        this.req0 = req0;
        this.req1 = req1;
        this.req2 = req2;
        this.imgReq = R.drawable.index;
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

    public int getImgReq() {
        return imgReq;
    }

    public void setImgReq(int imgReq) {
        this.imgReq = imgReq;
    }
}
