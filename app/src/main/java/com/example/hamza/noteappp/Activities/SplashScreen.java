package com.example.hamza.noteappp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.hamza.noteappp.R;
import com.example.hamza.noteappp.databinding.ActivitySpalshScreenBinding;

import static com.example.hamza.noteappp.R.transition.anim;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh_screen);

        ActivitySpalshScreenBinding binding  = DataBindingUtil.setContentView(this,R.layout.activity_spalsh_screen);



        @SuppressLint("ResourceType") Animation animation = AnimationUtils.loadAnimation(this, anim);
        binding.splash.startAnimation(animation);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1220);
    }
}