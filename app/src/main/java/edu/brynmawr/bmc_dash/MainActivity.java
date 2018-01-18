package edu.brynmawr.bmc_dash;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView spinner;
    Random r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = new Random();
        spinner = (ImageView)findViewById(R.id.spinner);
        spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RotateAnimation rotate = new RotateAnimation(0, r.nextInt(3600) + 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                rotate.setFillAfter(true);
                rotate.setDuration(2000);
                rotate.setInterpolator(new AccelerateDecelerateInterpolator());

                spinner.startAnimation(rotate);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "fuck u", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }
        });
    }
}
