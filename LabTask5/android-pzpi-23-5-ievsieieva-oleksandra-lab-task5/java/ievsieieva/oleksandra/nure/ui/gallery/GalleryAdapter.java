package ievsieieva.oleksandra.nure.ui.gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ievsieieva.oleksandra.nure.R;
import ievsieieva.oleksandra.nure.databinding.ItemGalleryBinding;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {
    List<Integer> list = new ArrayList<>();
    GalleryCallback callback;

    public GalleryAdapter(List<Integer> list, GalleryCallback callback) {
        this.list = list;
        this.callback = callback;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGalleryBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_gallery,
                parent,
                false
        );
        return new GalleryViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        holder.binding.image.setImageResource(list.get(position));
        holder.binding.image.setOnClickListener(v -> {
            callback.onItemClick(list.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GalleryViewHolder extends RecyclerView.ViewHolder {
        final ItemGalleryBinding binding;

        public GalleryViewHolder(@NonNull View itemView, ItemGalleryBinding binding) {
            super(itemView);
            this.binding = binding;
        }
    }
}
