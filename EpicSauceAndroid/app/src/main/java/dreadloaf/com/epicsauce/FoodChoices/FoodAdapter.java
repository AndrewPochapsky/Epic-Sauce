package dreadloaf.com.epicsauce.FoodChoices;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dreadloaf.com.epicsauce.FoodSelection.FoodInfo;
import dreadloaf.com.epicsauce.FoodSelection.MyAdapter;
import dreadloaf.com.epicsauce.R;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder>{

    public interface OnFoodItemClickedListener{
        void onClick(int index);
    }

    List<FoodInfo> foods;

    OnFoodItemClickedListener listener;

    public FoodAdapter(List<FoodInfo> foods, OnFoodItemClickedListener onFoodItemClickedListener){
        this.foods = foods;
        this.listener = onFoodItemClickedListener;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.foods_view_holder, viewGroup, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder foodViewHolder, int i) {
        foodViewHolder.bind(foods.get(i).name, foods.get(i).imageUrl, i);
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }


    class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mTitleText;
        ImageView mImageView;
        int mIndex;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitleText = itemView.findViewById(R.id.food_list_title);
            mImageView = itemView.findViewById(R.id.food_list_image);

            itemView.setOnClickListener(this);
        }

        public void bind(String title, String imageUrl, int index){
            mIndex = index;
            mTitleText.setText(title);
            if(imageUrl != null && !imageUrl.isEmpty()){
                new DownloadImageTask(mImageView)
                        .execute(imageUrl);
            }

        }

        @Override
        public void onClick(View view) {
            listener.onClick(mIndex);
        }
    }
}
