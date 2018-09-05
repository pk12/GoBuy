package com.example.nightc.gobuy.CustomAdapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nightc.gobuy.Activities.Bottom_Tabs_Activity;
import com.example.nightc.gobuy.GoBuySDK.UserClasses.Expense;
import com.example.nightc.gobuy.GoBuySDK.UserClasses.Income;
import com.example.nightc.gobuy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

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
    public void onBindViewHolder(ActivityCardAdapter.ActivityViewHolder holder, final int position) {
        //pass the goals Spontaneous Incomes and Expenses and show them in a CardView inside the RecyclerView
        if (isIncome){
            holder.CategoryText.setText(incomes.get(position).getName());
            holder.Amount.setText(String.valueOf(incomes.get(position).getAmount()));
        }
        else {
            holder.CategoryText.setText(expenses.get(position).getName());
            holder.Amount.setText(String.valueOf(expenses.get(position).getAmount()));
        }

        //Add delete functionality to the cards
        final Button button = holder.optionsMenu;
        holder.optionsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, button);
                popupMenu.inflate(R.menu.recycler_view_context_menu);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.Delete:
                                if (isIncome){
                                    //remove from firebase
                                    FirebaseDatabase.getInstance().getReference("Days/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/" +
                                            Bottom_Tabs_Activity.goalHandler.getDay().getDate().toString() + "/" + "Incomes/" + incomes.get(position).getName() + "/").removeValue();

                                    //Remove from GoalHandler
                                    Bottom_Tabs_Activity.goalHandler.getDay().getSpontaneousIncomes().remove(incomes.get(position).getName());

                                    //Remove from list
                                    incomes.remove(position);

                                    notifyItemRemoved(position);
                                    return true;
                                }
                                else if (!isIncome){
                                    //remove from firebase
                                    FirebaseDatabase.getInstance().getReference("Days/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/" +
                                            Bottom_Tabs_Activity.goalHandler.getDay().getDate().toString() + "/" + "Expenses/" + expenses.get(position).getName() + "/").removeValue();

                                    //Remove from GoalHandler
                                    Bottom_Tabs_Activity.goalHandler.getDay().getSpontaneousExpenses().remove(expenses.get(position).getName());
                                    //Remove from list
                                    expenses.remove(position);

                                    notifyItemRemoved(position);
                                    return true;
                                }
                        }

                        return false;
                    }
                });

                popupMenu.show();
            }
        });



    }



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
        private TextView CategoryText;
        private Drawable TransactionType;
        private Button optionsMenu;
        private TextView Amount;

        public ActivityViewHolder(View itemView) {
            super(itemView);
            CategoryText = (TextView) itemView.findViewById(R.id.Category_NameView);
            Amount = (TextView) itemView.findViewById(R.id.Category_MoneyView);
            optionsMenu = (Button) itemView.findViewById(R.id.OptionsButton);
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
