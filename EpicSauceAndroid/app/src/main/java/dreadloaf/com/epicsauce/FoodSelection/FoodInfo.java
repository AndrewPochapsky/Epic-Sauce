package dreadloaf.com.epicsauce.FoodSelection;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodInfo {
    @SerializedName("Name")
    public String name;

    @SerializedName("Time")
    public String time;

    @SerializedName("Ingredients")
    public List<String> ingredients;

    @SerializedName("Directions")
    public List<String>  directions;

    @SerializedName("Image")
    public String imageUrl;
}
