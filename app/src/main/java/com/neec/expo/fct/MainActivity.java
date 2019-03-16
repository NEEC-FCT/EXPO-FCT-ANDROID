package com.neec.expo.fct;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.neec.expo.fct.fragments.Cursos;
import com.neec.expo.fct.fragments.EasterEgg;
import com.neec.expo.fct.fragments.Horario;
import com.neec.expo.fct.fragments.Information;
import com.neec.expo.fct.fragments.Map;
import com.neec.expo.fct.fragments.Razoes;

public class MainActivity extends AppCompatActivity {


    private int numberOfTaps = 0;
    private long lastTapTimeMs = 0;
    private long touchDownMs = 0;
    private Handler handler;
    private MotionEvent event;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_horario:
                    changeFragment(new Horario());
                    return true;
                case R.id.navigation_info:
                    changeFragment(new Information());
                    return true;
                case R.id.navigation_mapa:
                    changeFragment(new Map());
                    return true;
                case R.id.navigation_razoes:
                    changeFragment(new Razoes());
                    return true;
                case R.id.navigation_cursos:
                    changeFragment(new Cursos());
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_mapa);

        int FTOPEN = getIntent().getIntExtra("FragmentToOpen", 1);
        switch (FTOPEN) {
            case 1:
                changeFragment(new Map());
                navigation.getMenu().getItem(2);
                break;// map=1;info=2;camera=3;razoes=4;cursos=5;
            case 2:
                changeFragment(new Information());
                navigation.getMenu().getItem(1).setChecked(true);
                break;
            //  case 3:  abre a camara no outro lado
            case 4:
                changeFragment(new Razoes());
                navigation.getMenu().getItem(3).setChecked(true);
                break;
            case 5:
                changeFragment(new Cursos());
                navigation.getMenu().getItem(4).setChecked(true);
                break;
            case 6:
                changeFragment(new Horario());
                navigation.getMenu().getItem(0).setChecked(true);
                break;

        }

    }


    public void scrollToTop(View v) {

        Animation shake;
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);

        ImageView image;
        image = (ImageView) v.findViewById(R.id.LogoNEEC);

        image.startAnimation(shake); // starts animation

        image.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchDownMs = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_UP:
                        handler.removeCallbacksAndMessages(null);

                        if ((System.currentTimeMillis() - touchDownMs) > ViewConfiguration.getTapTimeout()) {
                            //it was not a tap

                            numberOfTaps = 0;
                            lastTapTimeMs = 0;
                            break;
                        }

                        if (numberOfTaps > 0
                                && (System.currentTimeMillis() - lastTapTimeMs) < ViewConfiguration.getDoubleTapTimeout()) {
                            numberOfTaps += 1;
                        } else {
                            numberOfTaps = 1;
                        }

                        lastTapTimeMs = System.currentTimeMillis();

                        if (numberOfTaps == 4) {
                            Toast.makeText(getApplicationContext(), "Abrir o Easter Egg", Toast.LENGTH_SHORT).show();
                            changeFragment(new EasterEgg());

                            //handle triple tap
                        } else if (numberOfTaps == 2) {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //handle double tap
                                    //    Toast.makeText(getApplicationContext(), "double", Toast.LENGTH_SHORT).show();
                                }
                            }, ViewConfiguration.getDoubleTapTimeout());
                        }
                }

                return true;
            }
        });


    }

    private void changeFragment(Fragment fm) {
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fm);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }

}
