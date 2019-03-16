package com.neec.expo.fct.fragments;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.github.clans.fab.FloatingActionButton;
import com.neec.expo.fct.QrCodeScanner;
import com.neec.expo.fct.R;
import com.neec.expo.fct.qrcode.QRCodeEncoder;
import com.neec.expo.fct.semNet;

public class Map extends Fragment {

    public static final int MY_CAMERA_PERMISSION_CODE = 2;

    public Map() {
        // Required empty public constructor
    }


    public boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(  !isInternetAvailable() ){
            Intent intent = new Intent( getContext() ,  semNet.class);
            startActivity(intent);
        }

        View view = inflater.inflate(R.layout.fragment_map, container, false);


        WebView webView = (WebView) view.findViewById(R.id.webView);

        if (Build.VERSION.SDK_INT >= 19) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        else {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (getContext().checkSelfPermission(android.Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{android.Manifest.permission.CAMERA},
                                MY_CAMERA_PERMISSION_CODE);
                    } else {

                        Intent myIntent = new Intent(getActivity(), QrCodeScanner.class);
                        getActivity().startActivity(myIntent);
                    }
                } else {

                    Intent myIntent = new Intent(getActivity(), QrCodeScanner.class);
                    getActivity().startActivity(myIntent);
                }


            }
        });


        return view;
    }


    private void changeFragment(Fragment fm) {
        android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content, fm);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }

}