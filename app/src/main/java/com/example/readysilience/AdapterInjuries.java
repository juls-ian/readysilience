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

public class AdapterInjuries extends RecyclerView.Adapter<AdapterInjuries.InjuryViewHolder> {
    private Context context;
    private List<DataInjuries> injuriesList;
    private OnItemClickListener onItemClickListener;

    public AdapterInjuries(Context context, List<DataInjuries> injuriesList) {
        this.context = context;
        this.injuriesList = injuriesList;
    }

    @NonNull
    @Override
    public InjuryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_firstaid_injuries, parent, false);
        return new InjuryViewHolder(view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(DataInjuries injuriesData);
    }

    @Override
    public void onBindViewHolder(@NonNull InjuryViewHolder holder, int position) {
        DataInjuries injuriesData = injuriesList.get(position);
        Glide.with(holder.itemView)
                .load(injuriesData.getInjuryPic())
                .placeholder(R.drawable.image_icon)
                .into(holder.injuryPhoto);

        holder.injuryName.setText(injuriesData.getInjuryName());
        holder.injuryDesc.setText(injuriesData.getInjuryDesc());
        holder.injuryType.setText(injuriesData.getInjuryType());

        int typeIcon = getTypeIcon(injuriesData.getInjuryType());
        holder.injuryTypeIcon.setImageResource(typeIcon);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(injuriesData);
                }
            }
        });
    }

    private int getTypeIcon(String injuryType) {
        switch (injuryType) {
            case "Medical Emergency":
                return R.drawable.injury_medemergency_type;
            case "Burn":
                return R.drawable.injury_burn_type;
            case "Traumatic":
                return R.drawable.injury_traumatic_type;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return injuriesList.size();
    }

    static class InjuryViewHolder extends RecyclerView.ViewHolder {

        ImageView injuryPhoto, injuryTypeIcon;
        TextView injuryName, injuryDesc, injuryType ;


        InjuryViewHolder(@NonNull View itemView) {
            super(itemView);
            injuryPhoto = itemView.findViewById(R.id.injury_pic);
            injuryName = itemView.findViewById(R.id.injury_name);
            injuryDesc = itemView.findViewById(R.id.injury_desc);
            injuryType = itemView.findViewById(R.id.injury_type_text);
            injuryTypeIcon = itemView.findViewById(R.id.injury_type_icon);

        }
    }
}


