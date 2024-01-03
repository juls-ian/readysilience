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
public class AdapterFeatured extends RecyclerView.Adapter<AdapterFeatured.FeaturedViewHolder> {
    private Context context;
    private List<DataFeatured> featuredDataList;

    public AdapterFeatured(Context context, List<DataFeatured> featuredDataList) {
        this.context = context;
        this.featuredDataList = featuredDataList;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_featured_articles, parent, false);
        return new FeaturedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        DataFeatured featuredData = featuredDataList.get(position);
        Glide.with(holder.itemView)
                .load(featuredData.getArticleImage())
                .placeholder(R.drawable.image_icon)
                .into(holder.imageView);

        holder.titleTextView.setText(featuredData.getHeadline());

    }

    @Override
    public int getItemCount() {
        return featuredDataList.size();
    }

    static class FeaturedViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView titleTextView;


        FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.articleImage);
            titleTextView = itemView.findViewById(R.id.featuredTitle);

        }
    }
}


