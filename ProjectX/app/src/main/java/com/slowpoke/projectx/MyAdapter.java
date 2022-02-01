package com.slowpoke.projectx;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyyVH> {

    private ArrayList<Formula_Text> formula_list;
    private RecyclerViewOnClick listener;

    public MyAdapter(ArrayList<Formula_Text> formula_list, RecyclerViewOnClick listener)
    {
        this.formula_list = formula_list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_items_list_link, parent, false);
        return new MyyVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyyVH holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);

        String formula = formula_list.get(position).getFormula_context();
        holder.textView.setText(formula);

        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return formula_list.size();
    }


    public class MyyVH extends  RecyclerView.ViewHolder implements View.OnClickListener{


        TextView textView;

        public MyyVH(final View view)
        {
            super(view);
            textView = itemView.findViewById(R.id.text_of_list);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            listener.onClick(v, getAdapterPosition());
        }
    }

    public interface RecyclerViewOnClick
    {
        void onClick(View v, int position);
    }
}
