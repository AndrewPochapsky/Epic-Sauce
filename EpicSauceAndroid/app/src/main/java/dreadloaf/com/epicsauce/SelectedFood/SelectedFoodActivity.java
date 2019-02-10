package dreadloaf.com.epicsauce.SelectedFood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import dreadloaf.com.epicsauce.FoodChoices.DownloadImageTask;
import dreadloaf.com.epicsauce.R;


public class SelectedFoodActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_food);

        TextView foodNameText = findViewById(R.id.food_name);
        ImageView image = findViewById(R.id.food_image);
        TextView ingredientsText = findViewById(R.id.food_ingreients);
        TextView directionsText = findViewById(R.id.food_directions);

        Intent p = getIntent();

        foodNameText.setText(p.getStringExtra("name"));
        if(!p.getStringExtra("imageUrl").equals("")){
            new DownloadImageTask(image)
                    .execute(p.getStringExtra("imageUrl"));
        }


        String[] ingredients = p.getStringArrayExtra("ingredients");
        String[] directions = p.getStringArrayExtra("directions");

        StringBuilder ingredientsString = new StringBuilder();
        StringBuilder directionsString = new StringBuilder();

        for(String s : ingredients){
            ingredientsString.append("- ").append(s).append("\n").append("\n");
        }
        int num = 1;
        for(String s : directions){
            directionsString.append(num).append(". ").append(s).append("\n").append("\n");
            num++;
        }

        ingredientsText.setText(ingredientsString.toString());
        directionsText.setText(directionsString.toString());



        
    }
}
