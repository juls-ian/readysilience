package com.example.readysilience;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterGridSupply extends BaseAdapter {

    private Context context;
    private ArrayList<Integer> supplyPics;
    private ArrayList<String> supplyName;
    private LayoutInflater layoutInflater;

    public AdapterGridSupply(Context context, ArrayList<Integer> supplyPics, ArrayList<String> supplyName) {
        this.context = context;
        this.supplyPics = supplyPics;
        this.supplyName = supplyName;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount(){
        return supplyName.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (layoutInflater == null)
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_grid_supplies, null);
        }

        ImageView imageView = convertView.findViewById(R.id.supply_pic);
        TextView textViewName = convertView.findViewById(R.id.supply_name);

        imageView.setImageResource(supplyPics.get(position));
        textViewName.setText(supplyName.get(position));


        return convertView;
    }
}
