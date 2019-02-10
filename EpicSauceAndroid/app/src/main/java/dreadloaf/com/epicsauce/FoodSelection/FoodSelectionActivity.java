package dreadloaf.com.epicsauce.FoodSelection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import dreadloaf.com.epicsauce.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class FoodSelectionActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_selection);

        mRecyclerView = findViewById(R.id.options_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);


        String[] options = new String[] {"Pork", "Chicken", "Beef", "Rabbit", "Turtle"};
        mRecyclerView.setAdapter(new MyAdapter(options));



    }
}
