package dreadloaf.com.epicsauce.SauceOfDay;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import dreadloaf.com.epicsauce.FoodChoices.DownloadImageTask;
import dreadloaf.com.epicsauce.FoodChoices.FoodAdapter;
import dreadloaf.com.epicsauce.FoodChoices.FoodChoicesActivity;
import dreadloaf.com.epicsauce.FoodSelection.EpicSauceClient;
import dreadloaf.com.epicsauce.FoodSelection.FoodInfo;
import dreadloaf.com.epicsauce.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SauceOfDayActivity extends AppCompatActivity  {

    TextView mDescription;
    TextView mName;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sauce_of_day);
        mDescription = findViewById(R.id.sauce_desciption);
        mName = findViewById(R.id.sauce_name);
        mImageView = findViewById(R.id.sauce_image);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://178.128.224.49").addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.build();
        EpicSauceClient client =  retrofit.create(EpicSauceClient.class);
        Call<SauceInfo> call = client.getSauceInfo();

        call.enqueue(new Callback<SauceInfo>() {
            @Override
            public void onResponse(Call<SauceInfo> call, Response<SauceInfo> response) {
                SauceInfo sauceInfo = response.body();
                mDescription.setText(sauceInfo.description);
                mName.setText(sauceInfo.name);
                new DownloadImageTask(mImageView).execute(sauceInfo.imageUrl);

            }

            @Override
            public void onFailure(Call<SauceInfo> call, Throwable t) {
                Log.e("YOU SUCK", "ERRORO ERROR");
            }
        });
    }

}
