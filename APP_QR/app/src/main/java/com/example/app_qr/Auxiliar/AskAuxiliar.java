package com.example.app_qr.Auxiliar;

import android.util.Log;

import com.example.app_qr.Models.Ask;

import java.util.ArrayList;
import java.util.List;

public class AskAuxiliar {

    public static ArrayList<Ask> askDoneList = new ArrayList();
    public static List<Integer> randomList = new ArrayList<Integer>();
    public static ArrayList<String> datos = new ArrayList<String>();
    public static int endToken = 0;
    public static int contador = 0;

    public static void randomCode(){
        randomList.clear();
        randomList.add((int) (Math.random()*9));
        randomList.add((int) (Math.random()*9));
        randomList.add((int) (Math.random()*9));
        randomList.add((int) (Math.random()*9));
        randomList.add((int) (Math.random()*9));
        randomList.add((int) (Math.random()*9));
        Log.d("randomList", "" + randomList.toString());

    }

    public static int getNumberCode(){

            return randomList.get(contador);
    }



}
