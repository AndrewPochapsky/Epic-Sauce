package dreadloaf.com.epicsauce.WelcomeActivity;

import android.content.Intent;
import android.graphics.ImageDecoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import dreadloaf.com.epicsauce.FoodSelection.FoodSelectionActivity;
import dreadloaf.com.epicsauce.FoodSelection.FoodSelectionActivity;
import dreadloaf.com.epicsauce.R;
import dreadloaf.com.epicsauce.SauceOfDay.SauceOfDayActivity;

public class WelcomeActivity extends AppCompatActivity {
    CardView sodButton;
    CardView foodSelectionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        sodButton = findViewById(R.id.sauce_button);
        foodSelectionButton = findViewById(R.id.food_selection_button);

        sodButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(WelcomeActivity.this, SauceOfDayActivity.class));
            }
        });

        foodSelectionButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(WelcomeActivity.this, FoodSelectionActivity.class);
                String[] options = new String[] {"Vegetarian", "Non-Vegetarian"};
                intent.putExtra("options", options);
                intent.putExtra("nextValue", "Cuisine");
                intent.putExtra("json", "");
                startActivity(intent);
            }
        });

    }
}
