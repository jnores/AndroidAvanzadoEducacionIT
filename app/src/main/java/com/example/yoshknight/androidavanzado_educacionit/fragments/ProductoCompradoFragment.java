package com.example.yoshknight.androidavanzado_educacionit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoshknight.androidavanzado_educacionit.R;

/**
 * Created by EducaciónIT on 15/12/2018.
 */

public class ProductoCompradoFragment extends Fragment{


    public static ProductoCompradoFragment newInstance(){
        ProductoCompradoFragment fragment = new ProductoCompradoFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_compra_exitosa,container, false);
        return rootView;
    }


}
