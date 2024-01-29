package com.example.readysilience;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.readysilience.ExpandedViews.FeaturedDetailsActivity;

import org.w3c.dom.Text;

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
                .load(featuredData.getArticleImageUrl())
                .placeholder(R.drawable.image_icon)
                .into(holder.imageView);

        holder.titleTV.setText(featuredData.getHeadline());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle item click
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    DataFeatured featuredData = featuredDataList.get(adapterPosition);
                    Intent intent = new Intent(context, FeaturedDetailsActivity.class);
                    //CONNECTED TO FEATUREDDETAILSACTIVITY.JAVA
                    intent.putExtra("articlePic", featuredData.getArticleImageUrl());
                    intent.putExtra("headline", featuredData.getHeadline());
                    intent.putExtra("introduction", featuredData.getIntro());

                    intent.putExtra("parentSubhead1", featuredData.getParentSubheading1());

                    intent.putExtra("childSubhead1", featuredData.getChildSubheading1());
                    intent.putExtra("childSubheadDetails1", featuredData.getChildSubheading1Details1());
                    intent.putExtra("childSubhead1Pic", featuredData.getChildSubheading1Pic());
                    intent.putExtra("childSubheadDetails2", featuredData.getChildSubheading1Details2());


                    intent.putExtra("childSubhead2", featuredData.getChildSubheading2());
                    intent.putExtra("childSubhead2Details", featuredData.getChildSubheading2Detail());

                    intent.putExtra("parentSubhead2", featuredData.getParentSubheading2());
                    intent.putExtra("parentSubhead2Pic", featuredData.getParentSubheading2Pic());
                    intent.putExtra("parentSubhead2Detail", featuredData.getParentSubheading2Detail());

                    intent.putExtra("parentSubhead3", featuredData.getParentSubheading3());
                    intent.putExtra("parentSubhead3Pic", featuredData.getParentSubheading3Pic());
                    intent.putExtra("parentSubhead3Detail", featuredData.getParentSubheading3Details());

                    intent.putExtra("parentSubhead4", featuredData.getParentSubheading4());
                    intent.putExtra("subhead4ChildSubhead1", featuredData.getSubhead4ChildSubhead1());
                    intent.putExtra("subhead4ChildSubhead1Detail", featuredData.getSubhead4ChildSubhead1Details());

                    intent.putExtra("subhead4ChildSubhead2", featuredData.getSubhead4ChildSubhead2());
                    intent.putExtra("subhead4ChildSubhead2Detail", featuredData.getSubhead4ChildSubhead2Details());

                    intent.putExtra("subhead4ChildSubhead3", featuredData.getSubhead4ChildSubhead3());
                    intent.putExtra("subhead4ChildSubhead3Detail", featuredData.getSubhead4ChildSubhead3Details());

                    intent.putExtra("subhead4ChildSubhead4", featuredData.getSubhead4ChildSubhead4());
                    intent.putExtra("subhead4ChildSubhead4Detail", featuredData.getSubhead4ChildSubhead4Details());

                    context.startActivity(intent);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return featuredDataList.size();
    }

    static class FeaturedViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView titleTV;


        FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.featured_pic);
            titleTV = itemView.findViewById(R.id.featured_headline);





        }
    }
}


