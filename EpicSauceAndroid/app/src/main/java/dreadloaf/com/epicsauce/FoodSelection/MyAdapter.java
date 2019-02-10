package dreadloaf.com.epicsauce.FoodSelection;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import dreadloaf.com.epicsauce.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.OptionViewHolder>{


    public interface OnOptionClickedListener{
        void onClick(int index);
    }

    private String[] options;

    OnOptionClickedListener mListener;

    public MyAdapter(String[] options, OnOptionClickedListener listener){
        this.options = options;
        mListener = listener;

    }

    @NonNull
    @Override
    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.options_viewholder, viewGroup, false);
        return new OptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionViewHolder optionViewHolder, int i) {
        optionViewHolder.onBind(options[i], i);
    }

    @Override
    public int getItemCount() {
        return options.length;
    }


    class OptionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView mOptionText;
        int mIndex;

        public OptionViewHolder(@NonNull View itemView) {
            super(itemView);
            mOptionText = itemView.findViewById(R.id.option_text);
            itemView.setOnClickListener(this);
        }

        public void onBind(String option, int index){
            if(option.equals("0")){
                option = "Not Spicy";
            }else if(option.equals("1")){
                option = "Spicy";
            }

            mOptionText.setText(option);
            mIndex = index;
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(mIndex);
        }
    }
}
