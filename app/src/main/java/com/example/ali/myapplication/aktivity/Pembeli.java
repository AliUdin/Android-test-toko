package com.example.ali.myapplication.aktivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ali.myapplication.R;

import java.util.HashMap;

public class Pembeli extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembeli);

       /* sliderShow = (SliderLayout) findViewById(R.id.slider);


        //-- HashMap image from web
        HashMap url_maps = new HashMap();
        url_maps.put("Bengong", "https://lh3.googleusercontent.com/rQPEV7eJZZNU_1clOKBnHIzZZMaD_rgKebi3OJEGVH6oURVodWnVrtXMMhidN5JvuJg=h310");
        url_maps.put("Melongo", "https://i0.wp.com/www.amazine.co/wp-content/uploads/2013/12/Kucing_1.jpg");
        url_maps.put("Apaaa", "http://islamidia.com/wp-content/uploads/2016/07/Kucing-dan-Kedudukannya-Dalam-Pandangan-Islam.jpg");
        url_maps.put("Bobo", "https://hellosehat.com/wp-content/uploads/2016/11/tidur-dengan-kucing.jpg");
    }

       for(String name : url_maps.keySet()){
        TextSliderView textSliderView = new TextSliderView(this);
        textSliderView
                .description(name)
                .image(url_maps.get(name))
                .setScaleType(BaseSliderView.ScaleType.Fit)
                .setOnSliderClickListener(this);

        textSliderView.bundle(new Bundle());
        textSliderView.getBundle()
                .putString("extra", name);

        sliderShow.addSlider(textSliderView);
        */
    }


}



