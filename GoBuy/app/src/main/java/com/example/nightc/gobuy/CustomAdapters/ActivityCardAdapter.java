package com.example.nightc.gobuy.CustomAdapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nightc.gobuy.GoBuySDK.UserClasses.SpontaneousIncome;
import com.example.nightc.gobuy.R;

import java.util.ArrayList;

/**
 * Created by Oppai on 7/17/2017.
 */


//A custom RecyclerView Adapter for the Activity Cards
public class ActivityCardAdapter extends RecyclerView.Adapter<ActivityCardAdapter.ActivityViewHolder>{

    private ArrayList<SpontaneousIncome> incomes;
    private Context context;

    public ActivityCardAdapter(ArrayList<SpontaneousIncome> incomes, Context context) {
        this.incomes = incomes;
        this.context = context;
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



    //implements the on click listener and sets it to each view so that each view can be handles separately
    //can use the getAdapterPosition for each view Position in the RecyclerView
    public class ActivityViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener{
        private ImageView CategoryIcon;
        private TextView CategoryText;
        private Drawable TransactionType;

        public ActivityViewHolder(View itemView) {
            super(itemView);
            CategoryIcon = (ImageView) itemView.findViewById(R.id.Category_ImageView);
            CategoryText = (TextView) itemView.findViewById(R.id.Category_NameView);
            TransactionType = null;
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            Toast toast = Toast.makeText(context,"clicked="+ getAdapterPosition(),Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
