package com.example.pontes_stefane_esig.myapplication.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.activities.CardFormActivity;
import com.example.pontes_stefane_esig.myapplication.activities.ListtFormActivity;
import com.example.pontes_stefane_esig.myapplication.daos.ListtDAO;
import com.example.pontes_stefane_esig.myapplication.models.Listt;

import java.util.Collections;
import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.MyViewHolder> implements ItemTouchHelperAdapter {

    private Context context;
    private List<Listt> listts;

    class MyViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView tvName;
        TextView tvTotal;
        RecyclerView rvCards;
        Button btNewCard;

        MyViewHolder(View view) {
            super(view);
            this.view = view;
            tvName = view.findViewById(R.id.tv_name);
            tvTotal = view.findViewById(R.id.tv_total);
            btNewCard = view.findViewById(R.id.bt_new_card);
            rvCards = view.findViewById(R.id.rv_cards);
            //TODO check what this method does
            rvCards.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            rvCards.setLayoutManager(layoutManager);
        }
    }

    public ProjectAdapter(Context context, List<Listt> listts) {
        this.context = context;
        this.listts = listts;
    }

    @Override
    public ProjectAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listt_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Listt listt = listts.get(position);
        holder.tvName.setText(listt.getName());
        holder.tvTotal.setText(String.valueOf(listt.getTotal()));
        holder.view.setTag(position);

        ListtAdapter adapter = new ListtAdapter(listt, this, position, context);
        holder.rvCards.setAdapter(adapter);
        holder.rvCards.setOnDragListener(new DragListener(this, position));

        holder.btNewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String option1 = context.getString(R.string.card_new);
                String option2 = context.getString(R.string.listt_edit);
                String option3 = context.getString(R.string.listt_delete);
                final CharSequence[] options = {option1, option2, option3};

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                newCardForm(listt);
                                break;
                            case 1:
                                updateListtForm(listt.getId());
                                break;
                            case 2:
                                deleteListt(listt, position);
                                break;
                        }
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    private void newCardForm(Listt listt) {
        Intent intent = new Intent(context, CardFormActivity.class);
        intent.putExtra("listt_id", listt.getId());
        intent.putExtra("position", listt.getCards().size());
        context.startActivity(intent);
    }

    private void updateListtForm(long listtId) {
        Intent intent = new Intent(context, ListtFormActivity.class);
        intent.putExtra("listt_id", listtId);
        context.startActivity(intent);
    }

    private void deleteListt(Listt listt, int position) {
        ListtDAO dao = new ListtDAO(context);
        dao.delete(listt);
        dao.close();
        listts.remove(listt);
        ProjectAdapter.this.notifyItemRemoved(position);
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