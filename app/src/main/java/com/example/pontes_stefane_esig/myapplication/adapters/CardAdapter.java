package com.example.pontes_stefane_esig.myapplication.adapters;

import android.content.ClipData;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.models.Card;
import com.example.pontes_stefane_esig.myapplication.models.Listt;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder>
        implements View.OnLongClickListener {

    private final ListtAdapter listtAdapter;
    private final int listtPosition;
    private Listt listt;

    public CardAdapter(Listt listt, ListtAdapter listtAdapter, int listtPosition) {
        this.listt = listt;
        this.listtAdapter = listtAdapter;
        this.listtPosition = listtPosition;
    }

    List<Card> getCards() {
        return listt.getCards();
    }

    void setCards(List<Card> cards) {
        listt.setCards(cards);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Card card = listt.getCards().get(position);
        holder.tvName.setText(card.getName() + " - " + card.getPoints());
        holder.flCard.setTag(position);
        holder.flCard.setOnLongClickListener(this);
//        holder.flCard.setOnTouchListener(this);
//        holder.flCard.setOnDragListener(this);
        holder.flCard.setOnDragListener(new DragListener(listtAdapter, listtPosition));
    }

    @Override
    public int getItemCount() {
        return listt.getCards().size();
    }

    //Drag and Drop
    @Override
    public boolean onLongClick(View view) {
        ClipData data = ClipData.newPlainText("", "");
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            view.startDragAndDrop(data, shadowBuilder, view, 0);
        } else {
            view.startDrag(data, shadowBuilder, view, 0);
        }
        return true;
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
