package com.example.pontes_stefane_esig.myapplication.helpers;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.pontes_stefane_esig.myapplication.adapters.ItemTouchHelperAdapter;

public class MyItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private ItemTouchHelperAdapter adapter;
    private String orientation;

    public MyItemTouchHelperCallback(ItemTouchHelperAdapter adapter, String orientation) {
        this.adapter = adapter;
        this.orientation = orientation;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int directions;
        switch (orientation) {
            case "horizontal":
                directions = ItemTouchHelper.START | ItemTouchHelper.END;
                break;
            case "vertical":
                directions = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                break;
            default:
                directions = ItemTouchHelper.START | ItemTouchHelper.END | ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        }
        return makeFlag(ItemTouchHelper.ACTION_STATE_DRAG, directions);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        adapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }
}