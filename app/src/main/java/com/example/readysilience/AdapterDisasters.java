package com.example.readysilience;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.List;


// UpdatesAdapter.java
public class AdapterDisasters extends RecyclerView.Adapter<AdapterDisasters.DisasterViewHolder> {
    private Context context;
    private List<DataDisasters> disastersDataList;

    public AdapterDisasters(Context context, List<DataDisasters> disastersDataList) {
        this.context = context;
        this.disastersDataList = disastersDataList;
    }

    @NonNull
    @Override
    public DisasterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_disasters, parent, false);
        return new DisasterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DisasterViewHolder holder, int position) {
        DataDisasters disasterData = disastersDataList.get(position);
        Glide.with(holder.itemView)
                .load(disasterData.getDisasterPic())
                .placeholder(R.drawable.image_icon)
                .into(holder.imageView);

        holder.disasterNameText.setText(disasterData.getDisasterName());
        holder.bitDescription.setText(disasterData.getBitDesc());
        holder.disasterTypeText.setText(disasterData.getDisasterType());
        holder.proneTypeText.setText(disasterData.getProneType());
        holder.activityTypeText.setText(disasterData.getActivityType());


        //BG COLORING

        if ("Earthquake".equals(disasterData.getDisasterName())) {
            Glide.with(holder.itemView)
                    .load(R.drawable.bg_disaster_cont_blue)
                    .placeholder(R.drawable.image_icon)
                    .into(holder.bg);
        } else if ("Typhoon".equals(disasterData.getDisasterName())) {
            Glide.with(holder.itemView)
                    .load(R.drawable.bg_disaster_cont_darkgreen)
                    .placeholder(R.drawable.image_icon)
                    .into(holder.bg);
        } else if ("Fire".equals(disasterData.getDisasterName())) {
            Glide.with(holder.itemView)
                    .load(R.drawable.bg_disaster_cont_gray)
                    .placeholder(R.drawable.image_icon)
                    .into(holder.bg);
        }else if ("Flood".equals(disasterData.getDisasterName())) {
            Glide.with(holder.itemView)
                    .load(R.drawable.bg_disaster_cont_yellow)
                    .placeholder(R.drawable.image_icon)
                    .into(holder.bg);
        }else if ("Deforestation".equals(disasterData.getDisasterName())) {
            Glide.with(holder.itemView)
                    .load(R.drawable.bg_disaster_cont_pink)
                    .placeholder(R.drawable.image_icon)
                    .into(holder.bg);
        }else if ("Volcanic Eruption".equals(disasterData.getDisasterName())) {
            Glide.with(holder.itemView)
                    .load(R.drawable.bg_disaster_cont_red)
                    .placeholder(R.drawable.image_icon)
                    .into(holder.bg);
        }






        //TEXT COLORING
        if ("Natural".equals(disasterData.getDisasterType())) {
            holder.disasterTypeText.setTextColor(context.getResources().getColor(R.color.green1)); // Set your green color
        } else if ("Man-made".equals(disasterData.getDisasterType())) {
            holder.disasterTypeText.setTextColor(context.getResources().getColor(R.color.yellow)); // Set your red color
        }else if ("Both".equals((disasterData.getDisasterType()))){
            holder.disasterTypeText.setTextColor(context.getResources().getColor(R.color.white));
        }

        if ("Prone".equals(disasterData.getProneType())) {
            holder.proneTypeText.setTextColor(context.getResources().getColor(R.color.red)); // Set your green color
        } else {
            holder.proneTypeText.setTextColor(context.getResources().getColor(R.color.green)); // Set your red color
        }



    }

    @Override
    public int getItemCount() {
        return disastersDataList.size();
    }

    static class DisasterViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView disasterNameText;
        TextView bitDescription;
        TextView disasterTypeText;
        TextView proneTypeText;
        TextView activityTypeText;
        ImageView bg;



        DisasterViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.disaster_pic);
            disasterNameText = itemView.findViewById(R.id.disaster_name);
            bitDescription = itemView.findViewById(R.id.disaster_desc);
            disasterTypeText = itemView.findViewById(R.id.disaster_type);
            proneTypeText = itemView.findViewById(R.id.prone_level);
            activityTypeText = itemView.findViewById(R.id.recent_act);
            bg = itemView.findViewById(R.id.bg);


        }
    }
}


