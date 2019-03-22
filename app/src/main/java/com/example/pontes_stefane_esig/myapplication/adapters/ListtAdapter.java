package com.example.pontes_stefane_esig.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.model.Card;
import com.example.pontes_stefane_esig.myapplication.model.Listt;

import java.util.List;

public class ListtAdapter extends RecyclerView.Adapter<ListtAdapter.MyViewHolder> {

    private Context context;
    private List<Listt> listts;

    class MyViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView tvName;
        ListView lvCards;

        MyViewHolder(View view) {
            super(view);
            this.view = view;
            tvName = view.findViewById(R.id.tv_name);
            lvCards = view.findViewById(R.id.lv_cards);
        }
    }

    public ListtAdapter(Context context, List<Listt> listts) {
        this.context = context;
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
        holder.tvName.setText(listt.getName());

        ArrayAdapter<Card> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, listt.getCards());
        holder.lvCards.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return listts.size();
    }
}
