package com.amarpreetsinghprojects.pokemonapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by kulvi on 07/05/17.
 */

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.PokeViewHolder> {

    ArrayList<Types> pokeListArrayList;
    Context c;

    public TypeAdapter(ArrayList<Types> pokeListArrayList) {
        this.pokeListArrayList = pokeListArrayList;
    }

    @Override
    public PokeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (LayoutInflater.from(parent.getContext())).inflate(R.layout.type_single_item,parent,false);
        c = parent.getContext();
        return new PokeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PokeViewHolder holder, int position) {
        Types type = pokeListArrayList.get(position);
        holder.type.setText(type.getType().getName());


    }

    @Override
    public int getItemCount() {
        return pokeListArrayList.size();
    }


    public class PokeViewHolder extends RecyclerView.ViewHolder {
        TextView type;
        public PokeViewHolder(View itemView) {
            super(itemView);
            type = (TextView)itemView.findViewById(R.id.typeTV);

        }
    }
}
