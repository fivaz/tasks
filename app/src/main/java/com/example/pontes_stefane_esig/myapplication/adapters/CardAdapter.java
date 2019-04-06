package com.example.pontes_stefane_esig.myapplication.adapters;

import android.content.ClipData;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
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

    //ViewHolder
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, View.OnClickListener {

        TextView tvName;
        FrameLayout flCard;
        private Card card;

        MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_card_name);
            flCard = view.findViewById(R.id.fl_card);

            view.setOnCreateContextMenuListener(this);
            view.setOnClickListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View view,
                                        final ContextMenu.ContextMenuInfo menuInfo) {
//            menu.setHeaderTitle("Select");
//            menu.clear();
            MenuItem edit = menu.add(0, view.getId(), getAdapterPosition(), "Edit");
            MenuItem delete = menu.add(0, view.getId(), getAdapterPosition(), "Delete");

//            edit.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//                @Override
//                public boolean onMenuItemClick(MenuItem menuItem) {
//
//                    return false;
//                }
//            });
//
            delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Log.e("CA#onCreateContextMenu", "clické");
                    if(card != null){
                        Log.e("CA#onCreateContextMenu", card.toString());
                    }
//                    View.OnContextClickListener menuInfo1 = (RecyclerView.OnContextClickListener) menuInfo;
//                    Log.e("CA#onCreateContextMenu", "position du card: "+position);
                    return false;
                }
            });
        }

        @Override
        public void onClick(View view) {
            view.showContextMenu();
        }

        public void setCard(Card card) {
            this.card = card;
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
        Card card = listt.getCards().get(position);
        holder.setCard(card);
        //TODO perform these methods inside MyViewHolder class
        holder.tvName.setText(card.getName() + " - " + card.getPoints());
        holder.flCard.setTag(position);
        holder.flCard.setOnLongClickListener(this);
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
}
