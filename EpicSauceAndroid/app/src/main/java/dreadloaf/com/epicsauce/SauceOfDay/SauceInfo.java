package dreadloaf.com.epicsauce.SauceOfDay;

import com.google.gson.annotations.SerializedName;

public class SauceInfo {

    @SerializedName("Name")
    public String name;

    @SerializedName("Image")
    public String imageUrl;

    @SerializedName("Description")
    public String description;

}
