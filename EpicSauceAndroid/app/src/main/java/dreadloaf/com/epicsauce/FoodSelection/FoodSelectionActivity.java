package dreadloaf.com.epicsauce.FoodSelection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import dreadloaf.com.epicsauce.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class FoodSelectionActivity extends AppCompatActivity implements MyAdapter.OnOptionClickedListener{

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_selection);

        mRecyclerView = findViewById(R.id.options_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        String[] options = null;
        if(getIntent().hasExtra("options")){
            options = getIntent().getStringArrayExtra("options");
        }

        mRecyclerView.setAdapter(new MyAdapter(options, this));



    }

    @Override
    public void onClick(int index) {
        Intent intent = new Intent(FoodSelectionActivity.this, FoodSelectionActivity.class);
        //API call is made here
        String[] options = new String[] {"Pork", "Chicken", "Beef", "Rabbit"};
        intent.putExtra("options", options);
        startActivity(intent);
    }
}
