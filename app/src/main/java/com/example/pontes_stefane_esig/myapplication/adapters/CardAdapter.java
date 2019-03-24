package com.example.pontes_stefane_esig.myapplication.adapters;

import android.content.ClipData;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.models.Card;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder>
        implements View.OnTouchListener {

    private List<Card> cards;

    CardAdapter(List<Card> cards) {
        this.cards = cards;
    }

    List<Card> getCards() {
        return cards;
    }

    void setCards(List<Card> cards) {
        this.cards = cards;
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
        holder.tvName.setText(card.getName() + " - " + card.getPoints());
        holder.flCard.setTag(position);
        holder.flCard.setOnTouchListener(this);
        holder.flCard.setOnDragListener(new DragListener());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    //Drag and Drop
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    view.startDragAndDrop(data, shadowBuilder, view, 0);
                } else {
                    view.startDrag(data, shadowBuilder, view, 0);
                }
                return true;
        }
        return false;
    }

    //ViewHolder
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        FrameLayout flCard;

        MyViewHolder(View view) {
            super(view);
            tvName = itemView.findViewById(R.id.tv_card_name);
            flCard = itemView.findViewById(R.id.fl_card);
        }
    }
}
