package com.example.mobile_take_home;

import android.content.Context;
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
    private View.OnClickListener listener;

    public CharactersAdapter(Context context, List<Character> characterList, View.OnClickListener listener) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listener = listener;
        this.characterList = characterList;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.character_list_item, parent, false);

        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Character character = characterList.get(position);

        ImageRequest request = new ImageRequest(holder.ivPhoto, holder.pbPhoto);
        request.execute(character.getImage());

        holder.mainLayout.setOnClickListener(listener);
        holder.mainLayout.setId(position);
        holder.tvName.setText(character.getName());
        holder.tvStatus.setText(character.getStatus());

        if (character.getStatus().equalsIgnoreCase("Dead")) {
            holder.ivStatus.setVisibility(View.VISIBLE);
        } else {
            holder.ivStatus.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return characterList.size();
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
