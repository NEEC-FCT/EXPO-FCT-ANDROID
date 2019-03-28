package com.neec.expo19.fct.fragments;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.clans.fab.FloatingActionButton;
import com.neec.expo.fct.R;
import com.neec.expo19.fct.semNet;

public class Map extends Fragment {

    public static final int MY_CAMERA_PERMISSION_CODE = 2;
    String currentURL = "https://www.google.com/maps/d/viewer?mid=1LC7hMeFmnZ8cj4XGiMAEBam7WHZZIn3k&ll=38.66073135349911%2C-9.205763350000097&z=17";
    String Nucleos = "https://www.google.com/maps/d/u/0/viewer?mid=1yiEFH_jHV-7Ea4S6VlxUhG_WPkFdopPv&ll=38.661187223123896%2C-9.206034335656113&z=17";
    String RestFCT = "https://www.google.com/maps/d/u/0/viewer?mid=1EBVZIb43YRKtlhJZFm1m_TxBao4xNeOy&ll=38.66095008260262%2C-9.20509986148852&z=17&fbclid=IwAR1jgH9w90v-_sFNz_0wGMxnJqB75jSdrVa9RCkJbgWJdfN0dPFVtdHYKU0";
    String FCTNormal = "https://www.google.com/maps/d/u/0/viewer?mid=1LC7hMeFmnZ8cj4XGiMAEBam7WHZZIn3k&ll=38.660549041189526%2C-9.204203388254882&z=18";
    String ALL = "https://www.google.com/maps/d/viewer?mid=1LC7hMeFmnZ8cj4XGiMAEBam7WHZZIn3k&ll=38.66073135349911%2C-9.205763350000097&z=17";
    public Map() {
        // Required empty public constructor
        //currentURL = ALL;
    }




    public boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;

    }

    public  void setURL(int i){

        if(i == 1){
            currentURL = RestFCT;
        }
        else if( i == 2 ){
            currentURL = Nucleos;
        }
        else {
            currentURL = ALL;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (!isInternetAvailable()) {
            Intent intent = new Intent(getContext(), semNet.class);
            startActivity(intent);
        }

        View view = inflater.inflate(R.layout.fragment_map, container, false);




        final WebView webView = (WebView) view.findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
                return false;
            }
        });

        if (Build.VERSION.SDK_INT >= 19) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

        webView.loadUrl(currentURL);


        FloatingActionButton allFAB = (FloatingActionButton) view.findViewById(R.id.all);
        FloatingActionButton atividades = (FloatingActionButton) view.findViewById(R.id.atividades);
        FloatingActionButton nucleos = (FloatingActionButton) view.findViewById(R.id.nucleos);

        allFAB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                webView.loadUrl( ALL);


            }
        });
        atividades.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                webView.loadUrl(RestFCT);

            }
        });

        nucleos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                webView.loadUrl(Nucleos);


            }
        });

     /*   team.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d("FAB", "Team");
                changeFragment(new Team());


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
        });*/


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