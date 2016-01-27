package com.d_codepages.sarathkumar.VectrayExample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.d_codepages.sarathkumar.vectray.IconDrawable;
import com.d_codepages.sarathkumar.vectray.VectRay;
import com.d_codepages.sarathkumar.vectray.VectrayDrawable;


public class MainActivity extends AppCompatActivity {

     @VectrayDrawable(Icon = "{fa} {fa_heart}",Color = Color.RED,Dp = 150)
    public IconDrawable CustomDrawable;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         Log.v("VT",getPackageName());
        VectRay Vr = new VectRay(this,getPackageName());
        Vr.initIcons((ViewGroup) findViewById(R.id.root));
         ImageView im = (ImageView) findViewById(R.id.imageView);
         im.setImageDrawable(CustomDrawable);
     }
 }
