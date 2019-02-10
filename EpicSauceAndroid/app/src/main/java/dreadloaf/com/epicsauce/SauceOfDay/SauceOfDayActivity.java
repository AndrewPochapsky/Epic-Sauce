package dreadloaf.com.epicsauce.SauceOfDay;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import dreadloaf.com.epicsauce.R;

public class SauceOfDayActivity extends AppCompatActivity  {
    //implements GestureDetector.OnGestureListener
//    public static final int  = 100;
//    public static final int SWIPE_THRESHOLD = 100;
//    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    //private GestureDetectorCompat detector;

    Typeface typeface;
    TextView mytv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sauce_of_day);
        //detector = new GestureDetectorCompat(this, this);
        typeface = getResources().getFont(fonts/Pac)

    }
    }

//    @Override
//    public boolean onDown(MotionEvent e) {
//        return false;
//    }
//
//    @Override
//    public void onShowPress(MotionEvent e) {
//
//    }
//
//    @Override
//    public boolean onSingleTapUp(MotionEvent e) {
//        return false;
//    }
//
//    @Override
//    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//        return false;
//    }
//
//    @Override
//    public void onLongPress(MotionEvent e) {
//
//    }
//
//    @Override
//    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY) {
//        boolean result = false;
//        float diffY = moveEvent.getY() - downEvent.getY();
//        float diffX = moveEvent.getX() - downEvent.getX();
//
//        if(Math.abs(diffY) > Math.abs(diffX)) {
//            if(Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD)
//                if (diffY > 0 ){
//                    onSwipeUp();
//                    result = true;
//                }
//        }
//
//        return result;
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        detector.onTouchEvent(event);
//        return super.onTouchEvent(event);
//    }
//
//    private void onSwipeUp() {
//
//    }

