package com.example.readysilience;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterGridItems extends BaseAdapter {

    private Context context;
    private ArrayList<Integer> itemPics;
    private ArrayList<String> itemNames;
    private ArrayList<String> itemDescs;
    private LayoutInflater layoutInflater;

    public AdapterGridItems(Context context, ArrayList<Integer> itemPics, ArrayList<String> itemNames) {
        this.context = context;
        this.itemPics = itemPics;
        this.itemNames = itemNames;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount(){
        return itemNames.size();
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
            convertView = layoutInflater.inflate(R.layout.layout_grid_items_firstaid, null);
        }

        ImageView imageView = convertView.findViewById(R.id.item_pic);
        TextView textViewName = convertView.findViewById(R.id.item_name);

        imageView.setImageResource(itemPics.get(position));
        textViewName.setText(itemNames.get(position));


        return convertView;
    }
}
