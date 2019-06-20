package com.example.ali.myapplication.aktivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import com.example.ali.myapplication.R;

import butterknife.BindView;

public class Pelapak extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelapak);


    }

    public void pindah(View view) {
        Intent intent = new Intent(Pelapak.this,Login.class);
        startActivity(intent);
    }
}
