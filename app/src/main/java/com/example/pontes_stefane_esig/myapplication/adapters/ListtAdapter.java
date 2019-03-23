package com.example.pontes_stefane_esig.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.activities.CardFormActivity;
import com.example.pontes_stefane_esig.myapplication.helpers.MyItemTouchHelperCallback;
import com.example.pontes_stefane_esig.myapplication.models.Listt;

import java.util.Collections;
import java.util.List;

public class ListtAdapter extends RecyclerView.Adapter<ListtAdapter.MyViewHolder> implements ItemTouchHelperAdapter {

    private Context context;
    private List<Listt> listts;

    class MyViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView tvName;
        RecyclerView lvCards;
        Button btNewCard;

        MyViewHolder(View view) {
            super(view);
            this.view = view;
            tvName = view.findViewById(R.id.tv_name);
            btNewCard = view.findViewById(R.id.bt_new_card);
            lvCards = view.findViewById(R.id.lv_cards);
            //TODO check what this method does
            lvCards.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            lvCards.setLayoutManager(layoutManager);
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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Listt listt = listts.get(position);
        holder.tvName.setText(listt.getName());

        CardAdapter adapter = new CardAdapter(listt.getCards());
        holder.lvCards.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new MyItemTouchHelperCallback(adapter, "vertical");
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(holder.lvCards);

        holder.btNewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CardFormActivity.class);
                intent.putExtra("listt", listt);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listts.size();
    }

    //Drag and Drop
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(listts, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(listts, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }
}