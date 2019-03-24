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
//        implements ItemTouchHelperAdapter
        implements View.OnTouchListener
{

    private List<Card> cards;

    CardAdapter(List<Card> cards) {
        this.cards = cards;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        FrameLayout frameLayout;

        MyViewHolder(View view) {
            super(view);

            textView = itemView.findViewById(R.id.text);
            frameLayout = itemView.findViewById(R.id.frame_layout_item);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Card card = cards.get(position);
        holder.textView.setText(card.getName() + " - " + card.getPoints());
        holder.frameLayout.setTag(position);
        holder.frameLayout.setOnTouchListener(this);
        holder.frameLayout.setOnDragListener(new DragListener());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

//    //Drag and Drop
//    @Override
//    public boolean onItemMove(int fromPosition, int toPosition) {
//        if (fromPosition < toPosition) {
//            for (int i = fromPosition; i < toPosition; i++) {
//                Collections.swap(cards, i, i + 1);
//            }
//        } else {
//            for (int i = fromPosition; i > toPosition; i--) {
//                Collections.swap(cards, i, i - 1);
//            }
//        }
//        notifyItemMoved(fromPosition, toPosition);
//        return true;
//    }

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

    public DragListener getDragInstance() {
        return new DragListener();
    }

    List<Card> getList() {
        return cards;
    }

    void updateList(List<Card> cards) {
        this.cards = cards;
    }
}
