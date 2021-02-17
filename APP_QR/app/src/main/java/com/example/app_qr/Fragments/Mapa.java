package com.example.app_qr.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app_qr.R;

import uk.co.senab.photoview.PhotoViewAttacher;

public class Mapa extends Fragment{
    PhotoViewAttacher photoViewAttacher;
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mapa, container ,false);
        imageView = (ImageView)view.findViewById(R.id.mapa);
        photoViewAttacher = new PhotoViewAttacher(imageView);
        return view;
    }


}