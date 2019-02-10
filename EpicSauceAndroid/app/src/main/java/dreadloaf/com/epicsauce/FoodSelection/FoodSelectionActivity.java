package dreadloaf.com.epicsauce.FoodSelection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.util.List;

import dreadloaf.com.epicsauce.R;
import dreadloaf.com.epicsauce.SelectedFood.SelectedFoodActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class FoodSelectionActivity extends AppCompatActivity implements MyAdapter.OnOptionClickedListener{

    RecyclerView mRecyclerView;

    String[] mOptions;
    String mJson;
    String mNextValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_selection);

        mRecyclerView = findViewById(R.id.options_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        if(getIntent().hasExtra("options")){
            mOptions = getIntent().getStringArrayExtra("options");
        }
        if(getIntent().hasExtra("json")){
            mJson = getIntent().getStringExtra("json");
        }
        if(getIntent().hasExtra("nextValue")){
            mNextValue = getIntent().getStringExtra("nextValue");
        }

        mRecyclerView.setAdapter(new MyAdapter(mOptions, this));



    }

    @Override
    public void onClick(int index) {
        final Intent intent = new Intent(FoodSelectionActivity.this, FoodSelectionActivity.class);
        //API call is made here

        String userChoice = mOptions[index];
        Log.e("USER", "user selected" + userChoice);

        //Vegetable
        if(mJson.equals("")){
            int value = conversion(userChoice, mNextValue);
            mJson = "\"isVegetarian\": " + value;
        }else{
            switch (mNextValue){
                case "Meats":
                    mJson += ",\"cuisine\":" + "\"" + userChoice + "\"";
                    break;
                case "isSpicy":
                    mJson += ",\"meat\":" +  "\"" + userChoice + "\"";
                    break;
                case "Vegetables":
                    //int spicyValue = conversion(userChoice, mNextValue);
                    mJson += ",\"isSpicy\":" + userChoice;
                    break;
                case "Time":
                    mJson += ",\"vegetables\":" + "\"" + userChoice + "\"";
                    break;



            }
        }

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://178.128.224.49").addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.build();
        EpicSauceClient client =  retrofit.create(EpicSauceClient.class);
        Call<List<List<String>>> call = client.getFoodInfo("{" + mJson + "}", mNextValue, false);
        Log.e("JSON","{" + mJson + "}" );

        if(!mNextValue.equals("done")){
            call.enqueue(new Callback<List<List<String>>>() {
                @Override
                public void onResponse(Call<List<List<String>>> call, Response<List<List<String>>> response) {
                    //Log.e("FOOD SElection", "success");
                    //Log.e("FOODSLECTION", call.request().url().toString());
                    Log.e("FOODSLECTION", response.body().toString());

                    List<String> listOfOptions = response.body().get(0);
                    mOptions = new String[listOfOptions.size()];
                    for(int i = 0; i < listOfOptions.size(); i++){
                        mOptions[i] = listOfOptions.get(i);
                    }



                    intent.putExtra("options", mOptions);
                    intent.putExtra("json", mJson);
                    getNextValue();


                    intent.putExtra("nextValue", mNextValue);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<List<List<String>>> call, Throwable t) {
                    Log.e("food", "Fail");
                    Log.e("FOOD SELECTION", t.getMessage());
                }
            });
        }else{
            //End of choices
            //user is choosing time
            mJson += ",\"time\":" + "\"" + userChoice + "\"";

            Intent finalIntent = new Intent(FoodSelectionActivity.this, SelectedFoodActivity.class);
            finalIntent.putExtra("json", mJson);
            startActivity(finalIntent);
        }
    }


    private int conversion(String choice, String nextValue){
        //choice is regarding veg
        if(nextValue.equals("Cuisine")){
            if(choice.equals("Vegetarian")){
                return 1;
            }else{
                return 0;
            }
        }
        
        return -1;
    }

    private void getNextValue(){
        String newValue = "";
        switch (mNextValue){
            case "Cuisine":
                newValue = "Meats";
                break;
            case "Meats":
                newValue = "isSpicy";
                break;
            case "isSpicy":
                newValue = "Vegetables";
                break;
            case "Vegetables":
                newValue = "Time";
                break;
            default:
                newValue = "done";
        }
        mNextValue = newValue;
    }
}
