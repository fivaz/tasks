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

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>
        implements View.OnTouchListener {

    private List<String> list;

    public ListAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_row, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        holder.text.setText(list.get(position));
        holder.frameLayout.setTag(position);
        holder.frameLayout.setOnTouchListener(this);
        holder.frameLayout.setOnDragListener(new DragListener());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    v.startDragAndDrop(data, shadowBuilder, v, 0);
                } else {
                    v.startDrag(data, shadowBuilder, v, 0);
                }
                return true;
        }
        return false;
    }

    List<String> getList() {
        return list;
    }

    void updateList(List<String> list) {
        this.list = list;
    }

    public DragListener getDragInstance() {
        return new DragListener();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        FrameLayout frameLayout;

        ListViewHolder(View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.text);
            frameLayout = itemView.findViewById(R.id.frame_layout_item);
        }
    }
}