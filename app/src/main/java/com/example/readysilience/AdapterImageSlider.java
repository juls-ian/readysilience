package com.example.readysilience;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.readysilience.ExpandedViews.AnnouncementExpandedActivity;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class AdapterImageSlider extends SliderViewAdapter<AdapterImageSlider.SliderAdapterVH> {

    private Context context;

    private String[] bannerUrl = {"https://depeddasma.edu.ph/wp-content/uploads/2023/06/Web-Banner.png",
            "https://www.deped.gov.ph/wp-content/uploads/2021/08/be2021-web-banner-04.png",
            "https://connect-assets.prosple.com/cdn/ff/9mTtj6yfOvnjiHzh2tPaA5Qv8WTh7BSzaMNqi7CR-HM/1633490570/public/2021-10/banner-department-of-health-philippines-1786x642-2021.png"};


    public AdapterImageSlider(Context context) {
        this.context = context;
    }

    @Override
    public SliderAdapterVH  onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_image_slider, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(AdapterImageSlider.SliderAdapterVH viewHolder, int position) {

        Glide.with(context)
                .load(bannerUrl[position])
                .into(viewHolder.bannerPic);

        viewHolder.bannerPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass the clicked image URL to the expanded view activity
                String clickedImageUrl = bannerUrl[position];
                Intent intent = new Intent(context, AnnouncementExpandedActivity.class);
                intent.putExtra("imageUrl", clickedImageUrl);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getCount() {
        return bannerUrl.length;
    }

    static class SliderAdapterVH extends SliderViewAdapter.ViewHolder {
        ImageView bannerPic;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            bannerPic = itemView.findViewById(R.id.image_slider_item);
        }
    }

    private void showExpandedView(Context context, String imageUrl) {
        Intent intent = new Intent(context, AnnouncementExpandedActivity.class);
        intent.putExtra("bannerImage", imageUrl);
        context.startActivity(intent);


    }
}
