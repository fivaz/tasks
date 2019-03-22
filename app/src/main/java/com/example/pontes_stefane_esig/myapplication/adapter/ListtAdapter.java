package com.example.pontes_stefane_esig.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.model.Listt;

import java.util.List;

public class ListtAdapter extends RecyclerView.Adapter<ListtAdapter.MyViewHolder> {

    private List<Listt> listts;

    class MyViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView inputName;

        MyViewHolder(View view) {
            super(view);
            this.view = view;
            inputName = view.findViewById(R.id.listt_item_name);
        }
    }

    public ListtAdapter(List<Listt> listts) {
        this.listts = listts;
    }

    @Override
    public ListtAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listt_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Listt listt = listts.get(position);
        holder.inputName.setText(listt.getName());
    }

    @Override
    public int getItemCount() {
        return listts.size();
    }
}
