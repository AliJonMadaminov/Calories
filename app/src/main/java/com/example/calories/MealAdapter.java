package com.example.calories;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {

    List<Meal> meals;
    MealOnClickListener listener;

    public MealAdapter(List<Meal> meals, MealOnClickListener listener) {
        this.meals = meals;
        this.listener = listener;
    }
    public MealAdapter(MealOnClickListener listener) {
        meals = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meal, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(meals.get(position).imgUrl).into(holder.mealImage);
        holder.mealName.setText(meals.get(position).name);
        holder.mealCalory.setText(meals.get(position).caloriesPerPortion + " Kcal");
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(meals.get(position));
            }
        });
        holder.isVavouriteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.isVavouriteImg.setImageResource(R.drawable.ic_favorite_pressed);

            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public void addMeal(Meal meal) {
        meals.add(meal);
        notifyDataSetChanged();
    }

    public void removeMeal(Meal meal) {
        meals.remove(meal);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mealImage;
        TextView mealName;
        TextView mealCalory;
        CardView root;
        ImageView isVavouriteImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            isVavouriteImg = itemView.findViewById(R.id.img_is_favourite);
            mealImage = itemView.findViewById(R.id.img_meal);
            mealName = itemView.findViewById(R.id.txt_meal_name);
            mealCalory = itemView.findViewById(R.id.txt_meal_calory);
            root = itemView.findViewById(R.id.card_item_root);
        }
    }
}
