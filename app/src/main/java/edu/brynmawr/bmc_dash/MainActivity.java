package edu.brynmawr.bmc_dash;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView spinner;
    Button truth_button;
    Button dare_button;
    Random r;
    boolean truthClicked = false;
    boolean dareClicked = false;

    final Context context = this;

    String[] truths = {"Do you agree with the honor code?", "Do you support our current school's administration?", "Would you rather be a Haverford or Swarthmore student?", "Erd or Haff?", "What was the best class you took?", "What's your favorite tradition(Parade Night, Lantern Night, Hell Week, May Day)?", "Favorite restaurant on the Main Line?", "Where have you cried on campus?", "Where have you fallen asleep on campus?"};
    String[] dares = {"Give an offering to Athena", "Go tunnelling at Haverford", "Ask a stranger to hell you", "Go skinny dipping in Taft or College Hall", "Get lost in Park", "Grab a goose", "Visit every dorm", "Run to Brecon", "Eat meat in Batten", "Go to a Haverford Party", "Take a trip to Swarthmore", "Propose to a Professor", "Take a 300 level CS class", "Get high"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = new Random();
        spinner = (ImageView)findViewById(R.id.spinner);
        truth_button = (Button)findViewById(R.id.truth_button);
        dare_button = (Button)findViewById(R.id.dare_button);
        truth_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                truthClicked = true;
                rotateSpinner();
            }
        });
        dare_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dareClicked = true;
                rotateSpinner();
            }
        });
    }

    private void rotateSpinner() {
        truth_button.setEnabled(false);
        dare_button.setEnabled(false);
        RotateAnimation rotate = new RotateAnimation(0, r.nextInt(3600) + 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setFillAfter(true);
        rotate.setDuration(2000);
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());

        spinner.startAnimation(rotate);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                showResultDialog();
                truth_button.setEnabled(true);
                dare_button.setEnabled(true);
                truthClicked = false;
                dareClicked = false;
            }
        }, 2000);
    }

    private void showResultDialog() {
        if (truthClicked) {
            Log.v("truth", "clicked");
            final AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Truth");
            alert.setMessage(truths[r.nextInt(truths.length)]);
            alert.setCancelable(true);
            alert.show();
        }

        if (dareClicked) {
            Log.v("dare", "clicked");
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Dare");
            alert.setMessage(dares[r.nextInt(dares.length)]);
            alert.setCancelable(true);
            alert.show();
        }
    }

}

