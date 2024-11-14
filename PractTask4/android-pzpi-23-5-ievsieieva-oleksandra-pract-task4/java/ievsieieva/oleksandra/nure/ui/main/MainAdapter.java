package ievsieieva.oleksandra.nure.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ievsieieva.oleksandra.nure.R;
import ievsieieva.oleksandra.nure.data.model.User;
import ievsieieva.oleksandra.nure.databinding.ItemUserBinding;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{
    ArrayList<User> list = new ArrayList<>();

    void submitList(List<User> list) {
        if (list != null && list.size() > 0) {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user, parent, false);
        return new MainViewHolder( binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.binding.setNameString(list.get(position).getName());
        holder.binding.setAgeString(list.get(position).getAge().toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        final ItemUserBinding binding;

        public MainViewHolder(@NonNull View itemView, ItemUserBinding binding) {
            super(itemView);
            this.binding = binding;
        }
    }
}
