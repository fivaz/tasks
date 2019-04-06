package com.example.pontes_stefane_esig.myapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.View;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.models.Card;

import java.util.List;

public class DragListener implements View.OnDragListener {

    private final ProjectAdapter targetProjectAdapter;
    private int targetListtPosition;

    public DragListener(ProjectAdapter targetProjectAdapter, int targetListtPosition) {
        this.targetProjectAdapter = targetProjectAdapter;
        this.targetListtPosition = targetListtPosition;
    }

    //TODO change this method to receive some of its elements (maybe source ones) from constructor
    @Override
    public boolean onDrag(View view, DragEvent event) {
        View viewSource = (View) event.getLocalState();
        if (viewSource != null) {
            if (event.getAction() == DragEvent.ACTION_DROP) {
                if (view.getId() == R.id.fl_card || view.getId() == R.id.rv_cards) {
                    //source
                    RecyclerView source = (RecyclerView) viewSource.getParent();
                    ListtAdapter adapterSource = (ListtAdapter) source.getAdapter();
                    int positionSource = (int) viewSource.getTag();

                    Card card = adapterSource.getCards().get(positionSource);
                    List<Card> listSource = adapterSource.getCards();

                    //remove the card from its current list
                    listSource.remove(positionSource);
                    adapterSource.setCards(listSource);
                    adapterSource.notifyDataSetChanged();

                    //target
                    int positionTarget;
                    RecyclerView target;
                    //check if card was dropped on another card
                    if (view.getId() == R.id.fl_card) {
                        target = (RecyclerView) view.getParent();
                        positionTarget = (int) view.getTag();
                    }
                    //otherwise it was dropped on empty area of the recyclerView
                    else {
                        target = (RecyclerView) view;
                        positionTarget = -1;
                    }

                    ListtAdapter adapterTarget = (ListtAdapter) target.getAdapter();
                    List<Card> customListTarget = adapterTarget.getCards();

                    //add the card to its new list
                    if (positionTarget >= 0)
                        customListTarget.add(positionTarget, card);
                    else
                        customListTarget.add(card);

                    adapterTarget.setCards(customListTarget);
                    adapterTarget.notifyDataSetChanged();

                    //update totals
                    View rvItemListt = (View) source.getParent();
                    RecyclerView rvListt = (RecyclerView) rvItemListt.getParent();
                    ProjectAdapter sourceProjectAdapter = (ProjectAdapter) rvListt.getAdapter();
                    int sourceListtPosition = (int) rvItemListt.getTag();
                    sourceProjectAdapter.notifyItemChanged(sourceListtPosition);

                    this.targetProjectAdapter.notifyItemChanged(targetListtPosition);
                }
            } else {
                viewSource.setVisibility(View.VISIBLE);
            }
        }
        return true;
    }
}