package com.example.nightc.gobuy.CustomAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nightc.gobuy.R;

/**
 * Created by Oppai on 7/22/2017.
 */

public class CategoryCardAdapter extends RecyclerView.Adapter<CategoryCardAdapter.CategoryViewHolder> {
    //creates the viewholder which holds the contents of the cardview layout
    //private ArrayList<ItemTypes> types//Item Types to be created

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_category,parent,false);
        return new CategoryViewHolder(itemView);

    }

    //populate the Viewholder with each goal Instance
    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
//        Type type = types.get(position);
        //holder.DateWanted.setText(goal.getDates().getDateWanted().toString());
        //holder.TypeName.setText(goal.getGoalItems().get(0).getName());

    }


    @Override
    public int getItemCount() {
        //return goals.size();
        return 0;
    }









    //A viewholder Subclass of the adapter to show the content of the view(CardView Views)
    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private ImageView TypeIcon;
        private TextView TypeName;
        private TextView Description; //May add one too

        public CategoryViewHolder(View itemView) {
            super(itemView);
            TypeIcon = (ImageView) itemView.findViewById(R.id.GoalImageView);
            TypeName = (TextView) itemView.findViewById(R.id.GoalNameText);
            //Description = (TextView) itemView.findViewById(R.id.DateWantedText);
        }
    }
}
