package com.example.app_qr.Auxiliar;

import android.util.Log;

import com.example.app_qr.Models.Ask;

import java.util.ArrayList;
import java.util.List;

public class AskAuxiliar {

    public static ArrayList<Ask> askDoneList = new ArrayList();
    public static List<Integer> randomList = new ArrayList<Integer>();

    static int contador = 0;

    public static void randomCode(){
        randomList.add((int) (Math.random()*9));
        randomList.add((int) (Math.random()*9));
        randomList.add((int) (Math.random()*9));
        randomList.add((int) (Math.random()*9));
        randomList.add((int) (Math.random()*9));
        randomList.add((int) (Math.random()*9));
        Log.d("randomList", "" + randomList.toString());

    }

    public static int getNumberCode(){
        int response = 0;
        switch (contador){
            case  0:
                response = randomList.get(0);
            case  1:
                response = randomList.get(1);
            case  2:
                response = randomList.get(2);
            case  3:
                response = randomList.get(3);
            case  4:
                response = randomList.get(4);
            case  5:
                response = randomList.get(5);

        }
            contador ++;
            return response;
    }

}
