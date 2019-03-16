package com.neec.expo.fct;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import static com.neec.expo.fct.fragments.Map.MY_CAMERA_PERMISSION_CODE;

public class FirstPageActivity extends AppCompatActivity {
    ImageButton map;
    ImageButton info;
    ImageButton cam;
    ImageButton razoes;
    ImageButton curso;
    ImageButton horario;


    public boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        if (!isInternetAvailable()) {
            Intent intent = new Intent(getApplication(), semNet.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_first_page);
        map = findViewById(R.id.mapa);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.setImageResource(R.drawable.map_pressed);
                Intent intent = new Intent(FirstPageActivity.this, MainActivity.class);
                intent.putExtra("FragmentToOpen", 1);// map=1;info=2;camera=3;razoes=4;cursos=5;
                startActivity(intent);
                finish();
            }
        });

        info = findViewById(R.id.Info);
        info.setImageResource(R.drawable.inf0);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setImageResource(R.drawable.info_pressed);
                Intent intent = new Intent(FirstPageActivity.this, MainActivity.class);
                intent.putExtra("FragmentToOpen", 2);// map=1;info=2;camera=3;razoes=4;cursos=5;
                startActivity(intent);
                finish();
            }
        });

        cam = findViewById(R.id.Cam);
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(android.Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{android.Manifest.permission.CAMERA},
                                MY_CAMERA_PERMISSION_CODE);
                        Toast.makeText(FirstPageActivity.this, "Obrigado tente novamente", Toast.LENGTH_LONG).show();
                    } else {

                        Intent myIntent = new Intent(FirstPageActivity.this, QrCodeScanner.class);
                        startActivity(myIntent);
                    }
                } else {

                    Intent myIntent = new Intent(FirstPageActivity.this, QrCodeScanner.class);
                    startActivity(myIntent);
                }
            }
        });

        razoes = findViewById(R.id.Razoes);
        razoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                razoes.setImageResource(R.drawable.reasons_pressed);
                Intent intent = new Intent(FirstPageActivity.this, MainActivity.class);
                intent.putExtra("FragmentToOpen", 4);// map=1;info=2;camera=3;razoes=4;cursos=5;
                startActivity(intent);
                finish();
            }
        });
        curso = findViewById(R.id.cursos);
        curso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curso.setImageResource(R.drawable.cursos_pressed);
                Intent intent = new Intent(FirstPageActivity.this, MainActivity.class);
                intent.putExtra("FragmentToOpen", 5);// map=1;info=2;camera=3;razoes=4;cursos=5;
                startActivity(intent);
                finish();
            }
        });
        horario = findViewById(R.id.horario);
        horario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                horario.setImageResource(R.drawable.schedule_pressed);
                Intent intent = new Intent(FirstPageActivity.this, MainActivity.class);
                intent.putExtra("FragmentToOpen", 6);// map=1;info=2;camera=3;razoes=4;cursos=5;horario=6
                startActivity(intent);
                finish();
            }
        });


    }
}
