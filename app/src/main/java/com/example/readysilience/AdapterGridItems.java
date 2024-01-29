package com.example.readysilience;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.readysilience.ExpandedViews.FASuppDetailsActivity;
import com.example.readysilience.R;

import java.util.ArrayList;

public class AdapterGridItems extends BaseAdapter {

    private Context context;
    private ArrayList<Integer> itemPics;
    private ArrayList<String> itemNames;
    private ArrayList<String> itemDescs;
    private LayoutInflater layoutInflater;

    public AdapterGridItems(Context context, ArrayList<Integer> itemPics, ArrayList<String> itemNames, ArrayList<String>itemDescs) {
        this.context = context;
        this.itemPics = itemPics;
        this.itemNames = itemNames;
        this.itemDescs = itemDescs;
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


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle item click, launch expanded view activity
                Intent intent = new Intent(context, FASuppDetailsActivity.class);
                intent.putExtra("item_pic", itemPics.get(position));
                intent.putExtra("item_name", itemNames.get(position));
                intent.putExtra("item_desc", itemDescs.get(position));
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
