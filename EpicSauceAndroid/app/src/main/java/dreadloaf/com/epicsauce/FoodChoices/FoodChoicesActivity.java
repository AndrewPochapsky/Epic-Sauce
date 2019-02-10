package dreadloaf.com.epicsauce.FoodChoices;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import dreadloaf.com.epicsauce.FoodSelection.EpicSauceClient;
import dreadloaf.com.epicsauce.FoodSelection.FoodInfo;
import dreadloaf.com.epicsauce.FoodSelection.FoodSelectionActivity;
import dreadloaf.com.epicsauce.R;
import dreadloaf.com.epicsauce.SelectedFood.SelectedFoodActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodChoicesActivity extends AppCompatActivity implements FoodAdapter.OnFoodItemClickedListener {

    String mJson;
    List<FoodInfo> mList;
    RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_choices);

        mRecyclerView = findViewById(R.id.foods_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);


        if(getIntent().hasExtra("json")){
            mJson = getIntent().getStringExtra("json");
        }

        makeCall();

    }

    private void makeCall(){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://178.128.224.49").addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.build();
        EpicSauceClient client =  retrofit.create(EpicSauceClient.class);
        Call<List<List<FoodInfo>>> call = client.getMealInfo("{" + mJson + "}", true);

        call.enqueue(new Callback<List<List<FoodInfo>>>() {
            @Override
            public void onResponse(Call<List<List<FoodInfo>>> call, Response<List<List<FoodInfo>>> response) {
                List<FoodInfo> foods = response.body().get(0);
                mList = foods;
                Log.e("sd;lfja;dkjf", call.request().url().toString());
                Log.e("NEW JSON", response.body().toString());

                mRecyclerView.setAdapter(new FoodAdapter(foods, FoodChoicesActivity.this));
            }

            @Override
            public void onFailure(Call<List<List<FoodInfo>>> call, Throwable t) {
                Log.e("food", "Fail");
                Log.e("sd;lfja;dkjf", call.request().url().toString());
                Log.e("FOOD SELECTION", t.getMessage());
            }
        });
    }

    @Override
    public void onClick(int index) {

        String[] directionsArray = new String[mList.get(index).directions.size()];
        String[] ingredientsArray = new String[mList.get(index).ingredients.size()];

        for(int i =0; i < directionsArray.length; i++){
            directionsArray[i] = mList.get(index).directions.get(i);
        }

        for(int i =0; i < ingredientsArray.length; i++){
            ingredientsArray[i] = mList.get(index).ingredients.get(i);
        }


        Intent intent = new Intent(FoodChoicesActivity.this, SelectedFoodActivity.class);
        intent.putExtra("name", mList.get(index).name);
        intent.putExtra("imageUrl", mList.get(index).imageUrl);
        intent.putExtra("time", mList.get(index).time);
        intent.putExtra("ingredients", ingredientsArray);
        intent.putExtra("directions", directionsArray);

        startActivity(intent);

    }
}
