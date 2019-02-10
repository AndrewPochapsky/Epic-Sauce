package dreadloaf.com.epicsauce.WelcomeActivity;

<<<<<<< HEAD
import android.gesture.Gesture;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import android.view.View;
import android.widget.TextView;


import com.sothree.slidinguppanel.SlidingUpPanelLayout;

=======
import android.content.Intent;
import android.graphics.ImageDecoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import dreadloaf.com.epicsauce.FoodSelection.FoodSelectionActivity;
import dreadloaf.com.epicsauce.FoodSelection.FoodSelectionActivity;
>>>>>>> 94b9a5d1325217ff1336027d824ffae30e18ee83
import dreadloaf.com.epicsauce.R;
import dreadloaf.com.epicsauce.SauceOfDay.SauceOfDayActivity;

public class WelcomeActivity extends AppCompatActivity {
<<<<<<< HEAD
//    private SlidingUpPanelLayout mLayout;
=======
    ImageButton sodButton;
    ImageButton foodSelectionButton;
>>>>>>> 94b9a5d1325217ff1336027d824ffae30e18ee83
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
<<<<<<< HEAD
//        mLayout = findViewById(R.id.sliding_layout);
//        if(mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.HIDDEN){
//            Log.d("GAYBITCH", "HIIII");
//        }

//        SlidingUpPanelLayout l = new SlidingUpPanelLayout(this);
//
//        l.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
//            @Override
//            public void onPanelSlide(View panel, float slideOffset) {
//
//            }
//
//            @Override
//            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
//                Log.e("Listener: ", "hi");
//            }
//        });
=======

        sodButton = findViewById(R.id.sod_button);
        foodSelectionButton = findViewById(R.id.food_selection_button);

        sodButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(WelcomeActivity.this, SauceOfDayActivity.class));
            }
        });

        foodSelectionButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(WelcomeActivity.this, FoodSelectionActivity.class));
            }
        });

>>>>>>> 94b9a5d1325217ff1336027d824ffae30e18ee83
    }



}

