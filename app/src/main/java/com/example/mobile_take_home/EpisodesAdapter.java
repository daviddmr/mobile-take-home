package com.example.mobile_take_home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobile_take_home.model.Episode;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class EpisodesAdapter extends RecyclerView.Adapter<EpisodesAdapter.EpisodeViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Episode> episodeList;
    private View.OnClickListener listener;

    public EpisodesAdapter(Context context, List<Episode> episodeList, View.OnClickListener listener) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listener = listener;
        this.episodeList = episodeList;
    }

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.episode_list_item, parent, false);

        return new EpisodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {
        holder.mainLayout.setOnClickListener(listener);
        holder.mainLayout.setId(position);

        holder.tvName.setText(episodeList.get(position).getName());
        holder.tvEpisode.setText(episodeList.get(position).getEpisode());
        holder.tvAirDate.setText(episodeList.get(position).getAirDate());
    }

    @Override
    public int getItemCount() {
        return episodeList.size();
    }

    class EpisodeViewHolder extends RecyclerView.ViewHolder {
        CardView mainLayout;
        TextView tvName;
        TextView tvEpisode;
        TextView tvAirDate;

        EpisodeViewHolder(@NonNull View itemView) {
            super(itemView);

            mainLayout = itemView.findViewById(R.id.main_layout);
            tvName = itemView.findViewById(R.id.tv_name);
            tvEpisode = itemView.findViewById(R.id.tv_episode);
            tvAirDate = itemView.findViewById(R.id.tv_air_date);
        }
    }

}
