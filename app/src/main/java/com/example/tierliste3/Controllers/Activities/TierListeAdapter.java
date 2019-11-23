package com.example.tierliste3.Controllers.Activities;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tierliste3.Models.Film;
import com.example.tierliste3.Models.TiersListe;
import com.example.tierliste3.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TierListeAdapter extends RecyclerView.Adapter<TierListeAdapter.TierListeViewHolder> {
    private ArrayList<TiersListe> mDataset;

    private TierListeAdapter.OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener {
        void vers_tierListe(TiersListe liste, int position);
    }
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class TierListeViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public final View view;


        @BindView(R.id.tierliste_titre)
        TextView textView;

        public TierListeViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            view = v;


        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TierListeAdapter(ArrayList<TiersListe> myDataset, OnItemClickListener itemClickListener) {
        this.mOnItemClickListener = itemClickListener;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TierListeAdapter.TierListeViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tierliste_item, parent, false);
        //.....
        //....
        TierListeViewHolder vh = new TierListeViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TierListeViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TiersListe tierListe = mDataset.get(position);
        holder.textView.setText(tierListe.getTitre());
        if(position%2==0){
            holder.textView.setBackgroundColor(Color.rgb(194,205,209));
        }

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.vers_tierListe(tierListe,holder.getAdapterPosition());
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

