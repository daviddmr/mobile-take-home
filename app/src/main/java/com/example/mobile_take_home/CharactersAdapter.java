package com.example.mobile_take_home;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mobile_take_home.http.request.ImageRequest;
import com.example.mobile_take_home.model.Character;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Character> characterList;
    private OnItemClickInterface onItemClickInterface;

    public CharactersAdapter(Context context, List<Character> characterList, OnItemClickInterface onItemClickInterface) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.characterList = characterList;
        this.onItemClickInterface = onItemClickInterface;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.character_list_item, parent, false);

        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, final int position) {
        Character character = characterList.get(position);

        Bitmap bitmap = EpisodeDetailActivity.getBitmapFromMemoryCache(character.getId());
        if (bitmap != null) {
            holder.ivPhoto.setImageBitmap(bitmap);
            holder.pbPhoto.setVisibility(View.GONE);
        } else {
            ImageRequest request = new ImageRequest(holder.ivPhoto, holder.pbPhoto);
            request.execute(character.getImage(), String.valueOf(character.getId()));
        }

        holder.mainLayout.setOnClickListener(onClickListener(position));
        holder.mainLayout.setId(position);
        holder.tvName.setText(character.getName());
        holder.tvStatus.setText(character.getStatus());

        if (character.getStatus().equalsIgnoreCase("Dead")) {
            holder.ivStatus.setVisibility(View.VISIBLE);
        } else {
            holder.ivStatus.setVisibility(View.GONE);
            holder.mainLayout.setOnLongClickListener(onLongClickListener(position));
        }
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickInterface.onClick(position);
            }
        };
    }

    private View.OnLongClickListener onLongClickListener(final int position) {
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onItemClickInterface.onLongClick(position);

                return true;
            }
        };
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class CharacterViewHolder extends RecyclerView.ViewHolder {
        CardView mainLayout;
        ImageView ivPhoto;
        ProgressBar pbPhoto;
        TextView tvName;
        TextView tvStatus;
        ImageView ivStatus;

        CharacterViewHolder(@NonNull View itemView) {
            super(itemView);

            mainLayout = itemView.findViewById(R.id.main_layout);
            ivPhoto = itemView.findViewById(R.id.iv_photo);
            pbPhoto = itemView.findViewById(R.id.pb_photo);
            tvName = itemView.findViewById(R.id.tv_name);
            tvStatus = itemView.findViewById(R.id.tv_status);
            ivStatus = itemView.findViewById(R.id.iv_status);
        }
    }

}
