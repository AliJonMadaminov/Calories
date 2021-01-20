package com.example.calories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MealHistoryAdapter extends RecyclerView.Adapter<MealHistoryAdapter.HistoryViewHolder> {

    List<ChosenMeal> meals;

    public MealHistoryAdapter() {
        meals = new ArrayList<>();
    }

    public MealHistoryAdapter(List<ChosenMeal> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meal_history, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.nameTxt.setText(meals.get(position).name);
        holder.kcalsAmountTxt.setText(meals.get(position).totalCalories + "");
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }


    public void addChosenMeal(ChosenMeal meal) {
        meals.add(meal);
        notifyDataSetChanged();
    }

    public void removeChosenMeal(ChosenMeal meal) {
        meals.remove(meal);
        notifyDataSetChanged();
    }


    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView nameTxt;
        TextView kcalsAmountTxt;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.txt_meal_name_history);
            kcalsAmountTxt = itemView.findViewById(R.id.txt_kcals_amount_history);
        }
    }
}
