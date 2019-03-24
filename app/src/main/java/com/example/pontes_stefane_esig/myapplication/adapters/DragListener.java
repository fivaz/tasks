package com.example.pontes_stefane_esig.myapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.models.Card;

import java.util.List;

public class DragListener implements View.OnDragListener {

//    private boolean isDropped = false;

    @Override
    public boolean onDrag(View view, DragEvent event) {
//        boolean isDropped = false;
        if (event.getAction() == DragEvent.ACTION_DROP) {
//            isDropped = true;
            int positionTarget;

            View viewSource = (View) event.getLocalState();
            int viewId = view.getId();
            final int flItem = R.id.fl_card;

            if (viewId == flItem) {

//            switch (viewId) {
//                case flItem:

                RecyclerView target;
//                switch (viewId) {
//                    default:
                Log.e("DragListener on Drag", "view: " + view.toString());
                target = (RecyclerView) view.getParent();
                positionTarget = (int) view.getTag();
//                }

                if (viewSource != null) {
                    RecyclerView source = (RecyclerView) viewSource.getParent();

                    CardAdapter adapterSource = (CardAdapter) source.getAdapter();
                    int positionSource = (int) viewSource.getTag();

                    Card card = adapterSource.getCards().get(positionSource);
                    List<Card> listSource = adapterSource.getCards();

                    listSource.remove(positionSource);
                    adapterSource.setCards(listSource);
                    adapterSource.notifyDataSetChanged();

                    CardAdapter adapterTarget = (CardAdapter) target.getAdapter();
                    List<Card> customListTarget = adapterTarget.getCards();
                    if (positionTarget >= 0) {
                        customListTarget.add(positionTarget, card);
                    } else {
                        customListTarget.add(card);
                    }
                    adapterTarget.setCards(customListTarget);
                    adapterTarget.notifyDataSetChanged();
                }
//                break;
            }
//                break;
        } else if (event.getLocalState() != null) {
//        if (!isDropped && event.getLocalState() != null) {
            ((View) event.getLocalState()).setVisibility(View.VISIBLE);
        }
        return true;
    }
}