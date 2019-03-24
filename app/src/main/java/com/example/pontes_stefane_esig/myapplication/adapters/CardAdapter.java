package com.example.pontes_stefane_esig.myapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.models.Card;

import java.util.Collections;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder>
        implements ItemTouchHelperAdapter
{

    private List<Card> cards;

    CardAdapter(List<Card> cards) {
        this.cards = cards;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        MyViewHolder(View view) {
            super(view);
            this.textView = (TextView) view;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Card card = cards.get(position);
        holder.textView.setText(card.getName() + " - " + card.getPoints());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    //Drag and Drop
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(cards, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(cards, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }
}
