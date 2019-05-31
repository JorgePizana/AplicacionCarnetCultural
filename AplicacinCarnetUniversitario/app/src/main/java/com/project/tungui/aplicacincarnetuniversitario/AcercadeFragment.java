package com.project.tungui.aplicacincarnetuniversitario;

import android.icu.util.ValueIterator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.project.tungui.aplicacincarnetuniversitario.RecyclerView.RecyclerViewAdapter;


public class AcercadeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_acercade, container, false);

        ImageView imageViewIcon = (ImageView) view.findViewById(R.id.imgv_info);
        imageViewIcon.setColorFilter(getContext().getResources().getColor(R.color.myWhite));

        return view;
    }
}
