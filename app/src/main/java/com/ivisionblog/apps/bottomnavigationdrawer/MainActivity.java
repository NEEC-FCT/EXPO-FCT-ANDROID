package com.ivisionblog.apps.bottomnavigationdrawer;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.ivisionblog.apps.bottomnavigationdrawer.fragments.Cursos;
import com.ivisionblog.apps.bottomnavigationdrawer.fragments.Horario;
import com.ivisionblog.apps.bottomnavigationdrawer.fragments.Information;
import com.ivisionblog.apps.bottomnavigationdrawer.fragments.Map;
import com.ivisionblog.apps.bottomnavigationdrawer.fragments.Razoes;

public class MainActivity extends AppCompatActivity {


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
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        changeFragment(new Horario());
    }

    private void changeFragment(Fragment fm) {
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fm);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }

}
