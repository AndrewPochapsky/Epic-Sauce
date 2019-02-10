package dreadloaf.com.epicsauce.FoodSelection;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EpicSauceClient {

    @GET("/api/food/getFood.php")
    Call<List<List<String>>> getFoodInfo(@Query("data") String json, @Query("nextValue") String next, @Query("showOutput") boolean showOutput);

    @GET("/api/food/getFood.php")
    Call<List<List<FoodInfo>>> getMealInfo(@Query("data") String json, @Query("showOutput") boolean showOutput);
}
