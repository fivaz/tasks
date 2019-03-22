package com.example.pontes_stefane_esig.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.model.Listt;

import java.util.List;

public class ListtAdapter extends BaseAdapter {
    private List<Listt> listts;
    private Context context;

    public ListtAdapter(Context context, List<Listt> listts) {
        this.context = context;
        this.listts = listts;
    }

    @Override
    public int getCount() {
        return listts.size();
    }

    @Override
    public Object getItem(int i) {
        return listts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listts.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Listt listt = listts.get(i);
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.listt_item, null);
    }
}
