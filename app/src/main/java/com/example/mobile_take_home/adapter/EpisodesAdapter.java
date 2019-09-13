package com.example.mobile_take_home.adapter;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobile_take_home.R;
import com.example.mobile_take_home.model.Episode;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class EpisodesAdapter extends RecyclerView.Adapter<EpisodesAdapter.EpisodeViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Episode> episodeList;
    private View.OnClickListener listener;

    public EpisodesAdapter(Context context, List<Episode> episodeList, View.OnClickListener listener) {
        this.context = context;
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

        final Episode episode = episodeList.get(position);

        holder.tvName.setText(String.format(context.getString(R.string.name_episode_label), episode.getName()));
        holder.tvEpisode.setText(String.format(context.getString(R.string.number_episode_label), episode.getEpisode().substring(1, 3), episode.getEpisode().substring(4, 6)));
        holder.tvAirDate.setText(String.format(context.getString(R.string.air_date_episode_label), episode.getAirDate()));
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
