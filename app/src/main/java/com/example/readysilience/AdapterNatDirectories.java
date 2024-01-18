package com.example.readysilience;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


// UpdatesAdapter.java

public class AdapterNatDirectories extends RecyclerView.Adapter<AdapterNatDirectories.DirectoryViewHolder> {
    private Context context;
    private List<DataNatDirectories> natDirectoryDataList;

    public AdapterNatDirectories(Context context, List<DataNatDirectories> natDirectoryDataList) {
        this.context = context;
        this.natDirectoryDataList = natDirectoryDataList;
    }

    @NonNull
    @Override
    public DirectoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_national_directories, parent, false);
        return new DirectoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DirectoryViewHolder holder, int position) {
        DataNatDirectories directoriesData = natDirectoryDataList.get(position);
        Glide.with(holder.itemView)
                .load(directoriesData.getNatIcon())
                .placeholder(R.drawable.image_icon)
                .into(holder.imageView);

        holder.titleTextView.setText(directoriesData.getOfficeName());
        holder.phone1.setText(directoriesData.getPhone1());
        holder.phone2.setText(directoriesData.getPhone2());

    }

    @Override
    public int getItemCount() {
        return natDirectoryDataList.size();
    }

    static class DirectoryViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imageView;
        TextView titleTextView, phone1, phone2;


        DirectoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.nat_office_icon);
            titleTextView = itemView.findViewById(R.id.nat_office_name);
            phone1 = itemView.findViewById(R.id.nat_office_phone1);
            phone2 = itemView.findViewById(R.id.nat_office_phone2);

        }
    }
}


