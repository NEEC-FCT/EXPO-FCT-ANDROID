package com.neec.expo.fct.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.github.clans.fab.FloatingActionButton;
import com.neec.expo.fct.R;

public class Map extends Fragment {


    public Map() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);


        WebView webView = (WebView) view.findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl("https://www.google.com/maps/d/viewer?mid=1LC7hMeFmnZ8cj4XGiMAEBam7WHZZIn3k&ll=38.66073135349911%2C-9.205763350000097&z=17");

        FloatingActionButton team = (FloatingActionButton) view.findViewById(R.id.team);
        FloatingActionButton qrcode = (FloatingActionButton) view.findViewById(R.id.qrcode);



        team.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d("FAB", "Team");


            }
        });



        qrcode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d("FAB", "QRCODE");


            }
        });


        return view;
    }

}