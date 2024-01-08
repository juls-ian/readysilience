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
                .load(disasterData.getImagePic())
                .placeholder(R.drawable.image_icon)
                .into(holder.imageView);

        holder.disasterNameText.setText(disasterData.getDisasterName());
        holder.bitDescription.setText(disasterData.getBitDesc());
        holder.disasterTypeText.setText(disasterData.getDisasterType());
        holder.proneTypeText.setText(disasterData.getProneType());
        holder.activityTypeText.setText(disasterData.getActivityType());


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



        DisasterViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.disaster_pic);
            disasterNameText = itemView.findViewById(R.id.disaster_name);
            bitDescription = itemView.findViewById(R.id.disaster_desc);
            disasterTypeText = itemView.findViewById(R.id.disaster_type);
            proneTypeText = itemView.findViewById(R.id.prone_level);
            activityTypeText = itemView.findViewById(R.id.recent_act);

        }
    }
}


