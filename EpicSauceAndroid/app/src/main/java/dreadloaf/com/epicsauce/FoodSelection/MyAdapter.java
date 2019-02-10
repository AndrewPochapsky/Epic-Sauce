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

    private String[] options;

    public MyAdapter(String[] options){
        this.options = options;
    }

    @NonNull
    @Override
    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.options_viewholder, viewGroup, false);
        return new OptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionViewHolder optionViewHolder, int i) {
        optionViewHolder.onBind(options[i]);
    }

    @Override
    public int getItemCount() {
        return options.length;
    }


    class OptionViewHolder extends RecyclerView.ViewHolder{

        TextView mOptionText;

        public OptionViewHolder(@NonNull View itemView) {
            super(itemView);
            mOptionText = itemView.findViewById(R.id.option_text);
        }

        public void onBind(String option){
            mOptionText.setText(option);
        }
    }
}
