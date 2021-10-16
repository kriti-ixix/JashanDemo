package com.kriti.jashandemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Fading extends AppCompatActivity {

    ImageView inImageView, outImageView;
    Animation animFade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fading);
        inImageView = findViewById(R.id.inImageView);
        outImageView = findViewById(R.id.outImageView);
        animFade = AnimationUtils.loadAnimation(this, R.anim.fading_anim);

        inImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("TAG", "Image is clicked");
//                inImageView.animate().alpha(0).setDuration(3000);
//                outImageView.animate().alpha(1).setDuration(3000);

                inImageView.startAnimation(animFade);
            }
        });
    }
}