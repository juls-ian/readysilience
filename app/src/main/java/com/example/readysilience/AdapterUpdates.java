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
public class AdapterUpdates extends RecyclerView.Adapter<AdapterUpdates.UpdateViewHolder> {
    private Context context;
    private List<DataUpdates> updateDataList;

    public AdapterUpdates(Context context, List<DataUpdates> updateDataList) {
        this.context = context;
        this.updateDataList = updateDataList;
    }

    @NonNull
    @Override
    public UpdateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_updates, parent, false);
        return new UpdateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpdateViewHolder holder, int position) {
        DataUpdates updateData = updateDataList.get(position);
        Glide.with(holder.itemView)
                .load(updateData.getNewsImage())
                .placeholder(R.drawable.image_icon)
                .into(holder.imageView);

        holder.titleTextView.setText(updateData.getTitle());

    }

    @Override
    public int getItemCount() {
        return updateDataList.size();
    }

    static class UpdateViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView titleTextView;


        UpdateViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.updatesImage);
            titleTextView = itemView.findViewById(R.id.updatesTitle);

        }
    }
}


