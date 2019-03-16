package com.neec.expo.fct;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class FirstPageActivity extends AppCompatActivity {
    ImageButton map;
    ImageButton info;
    ImageButton cam;
    ImageButton razoes;
    ImageButton curso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_first_page);
        map = findViewById(R.id.mapa);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.setImageResource(R.drawable.map_pressed);
                Intent intent = new Intent(FirstPageActivity.this, MainActivity.class);
                intent.putExtra("FragmentToOpen", 1);// map=1;info=2;camera=3;razoes=4;cursos=5;
                startActivity(intent);
            }
        });

        info = findViewById(R.id.Info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setImageResource(R.drawable.info_pressed);
                Intent intent = new Intent(FirstPageActivity.this, MainActivity.class);
                intent.putExtra("FragmentToOpen", 2);// map=1;info=2;camera=3;razoes=4;cursos=5;
                startActivity(intent);
            }
        });

        cam = findViewById(R.id.Cam);
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cam.setImageResource(R.drawable.camera_pressed);
                Intent intent = new Intent(FirstPageActivity.this, MainActivity.class);
                intent.putExtra("FragmentToOpen", 3);// map=1;info=2;camera=3;razoes=4;cursos=5;
                startActivity(intent);
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
            }
        });





    }
}
