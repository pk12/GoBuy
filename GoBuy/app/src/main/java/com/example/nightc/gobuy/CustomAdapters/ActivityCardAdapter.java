package com.example.nightc.gobuy.CustomAdapters;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nightc.gobuy.GoBuySDK.UserClasses.SpontaneousIncome;
import com.example.nightc.gobuy.R;

import java.util.ArrayList;

/**
 * Created by Oppai on 7/17/2017.
 */


//A custom RecyclerView Adapter for the Activity Cards
public class ActivityCardAdapter extends RecyclerView.Adapter<ActivityCardAdapter.ActivityViewHolder> {

    private ArrayList<SpontaneousIncome> incomes;

    public ActivityCardAdapter(ArrayList<SpontaneousIncome> incomes) {
        this.incomes = incomes;
    }

    //populate the view holder with the layout inside the RecyclerView
    //and return a new instance of it
    @Override
    public ActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_daily_activity,parent,false);
        return new ActivityViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ActivityCardAdapter.ActivityViewHolder holder, int position) {
        //pass the goals Spontaneous Incomes and Expenses and show them in a CardView inside the RecyclerView
        holder.CategoryText.setText(incomes.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return incomes.size();
    }




    public class ActivityViewHolder extends RecyclerView.ViewHolder {
        private ImageView CategoryIcon;
        private TextView CategoryText;
        private Drawable TransactionType;

        public ActivityViewHolder(View itemView) {
            super(itemView);
            CategoryIcon = (ImageView) itemView.findViewById(R.id.Category_ImageView);
            CategoryText = (TextView) itemView.findViewById(R.id.Category_NameView);
            TransactionType = null;
        }
    }
}
