package com.example.pontes_stefane_esig.myapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.models.Card;

import java.util.List;

public class DragListener implements View.OnDragListener {

    private boolean isDropped = false;

    @Override
    public boolean onDrag(View view, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DROP:
                isDropped = true;
                int positionTarget = -1;

                View viewSource = (View) event.getLocalState();
                int viewId = view.getId();
                final int flItem = R.id.frame_layout_item;
                final int rvTop = R.id.rvTop;
                final int rvBottom = R.id.rvBottom;

                switch (viewId) {
                    case flItem:
                    case rvTop:
                    case rvBottom:

                        RecyclerView target;
                        switch (viewId) {
                            case rvTop:
                                target = view.getRootView().findViewById(rvTop);
                                break;
                            case rvBottom:
                                target = view.getRootView().findViewById(rvBottom);
                                break;
                            default:
                                Log.i("DragListener on Drag", "view: " + view.toString());
                                target = (RecyclerView) view.getParent();
                                positionTarget = (int) view.getTag();
                        }

                        if (viewSource != null) {
                            RecyclerView source = (RecyclerView) viewSource.getParent();

                            CardAdapter adapterSource = (CardAdapter) source.getAdapter();
                            int positionSource = (int) viewSource.getTag();
//                            int sourceId = source.getId();

                            Card card = adapterSource.getList().get(positionSource);
                            List<Card> listSource = adapterSource.getList();

                            listSource.remove(positionSource);
                            adapterSource.updateList(listSource);
                            adapterSource.notifyDataSetChanged();

                            CardAdapter adapterTarget = (CardAdapter) target.getAdapter();
                            List<Card> customListTarget = adapterTarget.getList();
                            if (positionTarget >= 0) {
                                customListTarget.add(positionTarget, card);
                            } else {
                                customListTarget.add(card);
                            }
                            adapterTarget.updateList(customListTarget);
                            adapterTarget.notifyDataSetChanged();
                        }
                        break;
                }
                break;
        }

        if (!isDropped && event.getLocalState() != null) {
            ((View) event.getLocalState()).setVisibility(View.VISIBLE);
        }
        return true;
    }
}