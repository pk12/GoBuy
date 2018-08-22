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

import com.example.nightc.gobuy.GoBuySDK.UserClasses.Expense;
import com.example.nightc.gobuy.GoBuySDK.UserClasses.Income;
import com.example.nightc.gobuy.R;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Oppai on 7/17/2017.
 */


//A custom RecyclerView Adapter for the Activity Cards
public class ActivityCardAdapter extends RecyclerView.Adapter<ActivityCardAdapter.ActivityViewHolder>{

    private ArrayList<Income> incomes;
    private ArrayList<Expense> expenses;
    private Context context;
    private boolean isIncome; //Check which list is not empty to return on the getItemCount

    public ActivityCardAdapter(ArrayList<Income> incomes, Context context) {
        this.incomes = incomes;
        this.context = context;
        isIncome = true;
    }

    public ActivityCardAdapter(Collection<Expense> expenses, Context context){
        //did this to be able to keep both constructors
        this.expenses = (ArrayList<Expense>) expenses;
        this.context = context;
        isIncome = false;
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
        if (isIncome){
            holder.CategoryText.setText(incomes.get(position).getName());
            holder.Amount.setText(String.valueOf(incomes.get(position).getAmount()));
        }
        else {
            holder.CategoryText.setText(expenses.get(position).getName());
            holder.Amount.setText(String.valueOf(expenses.get(position).getAmount()));
        }





    }

    //TODO: Add Delete button functinality


    @Override
    public int getItemCount() {
        if (isIncome)
            return incomes.size();
        else
            return expenses.size();
    }



    //implements the on click listener and sets it to each view so that each view can be handles separately
    //can use the getAdapterPosition for each view Position in the RecyclerView
    public class ActivityViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener{
        private ImageView CategoryIcon;
        private TextView CategoryText;
        private Drawable TransactionType;
        private TextView Amount;

        public ActivityViewHolder(View itemView) {
            super(itemView);
            CategoryIcon = (ImageView) itemView.findViewById(R.id.Category_ImageView);
            CategoryText = (TextView) itemView.findViewById(R.id.Category_NameView);
            Amount = (TextView) itemView.findViewById(R.id.Category_MoneyView);
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
