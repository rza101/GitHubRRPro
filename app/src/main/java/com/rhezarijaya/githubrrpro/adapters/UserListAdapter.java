package com.rhezarijaya.githubrrpro.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rhezarijaya.githubrrpro.databinding.ItemUserBinding;
import com.rhezarijaya.githubrrpro.models.UserDetail;
import com.rhezarijaya.githubrrpro.utils.OnItemClick;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private List<UserDetail> userDetails = new ArrayList<>();
    private OnItemClick<UserDetail> onItemClick;

    public UserListAdapter(OnItemClick<UserDetail> onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setData(List<UserDetail> data) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return userDetails.size();
            }

            @Override
            public int getNewListSize() {
                return data.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return userDetails.get(oldItemPosition).getId() == data.get(newItemPosition).getId();
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return userDetails.get(oldItemPosition).getId() == data.get(newItemPosition).getId();
            }
        });

        this.userDetails.clear();
        this.userDetails.addAll(data);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserBinding itemUserBinding = ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int pos = holder.getAdapterPosition();

        Glide.with(holder.itemView.getContext())
                .load(userDetails.get(pos).getAvatarUrl())
                .into(holder.itemUserBinding.itemUserCivAvatar);
        holder.itemUserBinding.itemUserTvUsername.setText("@" + userDetails.get(pos).getLogin());
        holder.itemUserBinding.itemUserTvId.setText("ID : " + String.valueOf(userDetails.get(pos).getId()));

        holder.itemView.setOnClickListener(v -> onItemClick.onClick(userDetails.get(pos)));
    }

    @Override
    public int getItemCount() {
        return userDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemUserBinding itemUserBinding;

        public ViewHolder(ItemUserBinding itemUserBinding) {
            super(itemUserBinding.getRoot());
            this.itemUserBinding = itemUserBinding;
        }
    }
}
