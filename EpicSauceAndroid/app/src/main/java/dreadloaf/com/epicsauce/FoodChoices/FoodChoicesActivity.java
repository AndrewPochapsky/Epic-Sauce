package dreadloaf.com.epicsauce.FoodChoices;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import dreadloaf.com.epicsauce.R;

public class FoodChoicesActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_choices);

        if(getIntent().hasExtra("json")){
            Log.e("FINAL", getIntent().getStringExtra("json"));
        }
    }
}
