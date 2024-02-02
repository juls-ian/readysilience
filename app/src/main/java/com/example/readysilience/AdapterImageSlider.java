package com.example.readysilience;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.readysilience.ExpandedViews.AnnouncementExpandedActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdapterImageSlider extends SliderViewAdapter<AdapterImageSlider.SliderAdapterVH> {

    private Context context;
    private DatabaseReference databaseReference;
    private List<AnnouncementData> dataList;

    public AdapterImageSlider(Context context, DatabaseReference databaseReference) {
        this.context = context;
        this.databaseReference = databaseReference;
        this.dataList = new ArrayList<>();
        fetchAnnouncements();
    }

    private void fetchAnnouncements() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    AnnouncementData data = dataSnapshot.getValue(AnnouncementData.class);
                    dataList.add(data);
                }
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @NonNull
    @Override
    public SliderAdapterVH onCreateViewHolder(@NonNull ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_image_slider, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(AdapterImageSlider.SliderAdapterVH viewHolder, int position) {
        AnnouncementData data = dataList.get(position);

        Glide.with(context)
                .load(data.getImageUrl())
                .into(viewHolder.bannerPic);

        viewHolder.bannerPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AnnouncementExpandedActivity.class);
                intent.putExtra("date", data.getDate());
                intent.putExtra("title", data.getTitle());
                intent.putExtra("description", data.getDescription());
                intent.putExtra("imageUrl", data.getImageUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    static class SliderAdapterVH extends SliderViewAdapter.ViewHolder {
        ImageView bannerPic;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            bannerPic = itemView.findViewById(R.id.image_slider_item);
        }
    }
}
