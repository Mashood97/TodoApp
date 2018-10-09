package com.example.masho.todoapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Masho on 10/9/2018.
 */

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.MyViewHolder> {

    //in this we are creating reciept adapter so that we can generate reciept.
    Context context;
    ArrayList<ModelShowData> profiles;

    public ModelAdapter(Context c , ArrayList<ModelShowData> p)
    {
        context = c;
        profiles = p;
    }


    // inflate the custom layout of reciept list item to show on the activity context we pass. reciept_list_item is custom layout.++
    @Override
    public ModelAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_myposts,parent,false));
    }

    //same as food item adpter
    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        holder.Activity.setText(profiles.get(position).getActivity());


    }


    @Override
    public int getItemCount() {
        return profiles.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView Activity;

        public MyViewHolder(View itemView) {
            super(itemView);
            Activity = itemView.findViewById(R.id.Activity);

        }
    }
}
