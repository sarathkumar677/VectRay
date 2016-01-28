package com.d_codepages.sarathkumar.VectrayExample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
         Log.v("VT", getPackageName());
        VectRay Vr = new VectRay(this,getPackageName());
          Vr.initIcons((ViewGroup) findViewById(R.id.root));
         final ImageView im = (ImageView) findViewById(R.id.imageView);
         final ImageView im2 = (ImageView) findViewById(R.id.imageView2);

         IconDrawable iconDrawable = new IconDrawable(this,Vr.get_icon("{fa} {fa_bolt}"));
                iconDrawable.color(Color.CYAN)
                        .sizeDp(100)
                         .setShadow(5f,4,1,Color.GRAY);
         im.setImageDrawable(CustomDrawable);
         im2.setImageDrawable(iconDrawable);
         im.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 CustomDrawable.colorRes(Color.CYAN);
                 im.setImageDrawable(CustomDrawable);
             }
         });

//                 LinearGradient linearGradient = new LinearGradient(0, 0, width, height,
//                         new int[] {
//                                 Color.parseColor("#ffff00"),
//                                 Color.parseColor("#00ffff"),
//                         }, //substitute the correct colors for these
//                         new float[] {
//                                 10, 0.40f },
//                         Shader.TileMode.REPEAT);

     }
 }
