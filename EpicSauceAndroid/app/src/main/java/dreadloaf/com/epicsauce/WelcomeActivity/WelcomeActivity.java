package dreadloaf.com.epicsauce.WelcomeActivity;

import android.gesture.Gesture;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import android.view.View;
import android.widget.TextView;


import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import dreadloaf.com.epicsauce.R;

public class WelcomeActivity extends AppCompatActivity {
//    private SlidingUpPanelLayout mLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
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
    }



}

