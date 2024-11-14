package ievsieieva.oleksandra.nure.ui.note.list;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ievsieieva.oleksandra.nure.R;
import ievsieieva.oleksandra.nure.data.model.Note;
import ievsieieva.oleksandra.nure.databinding.ItemNoteBinding;


public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {
    private String TAG = NoteListAdapter.class.getSimpleName();
    ArrayList<Note> list = new ArrayList<>();
    OnItemClickCallback callback;

    void submitList(List<Note> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public NoteListAdapter(OnItemClickCallback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNoteBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_note, parent, false);
        return new NoteViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.binding.setNote(list.get(position));
        holder.binding.itemRoot.setOnClickListener(view -> {
            callback.onItemClick(list.get(position).getTitle());
        });
        holder.binding.itemRoot.setOnLongClickListener(view -> {
            PopupMenu menu = new PopupMenu(holder.binding.itemRoot.getContext(),holder.binding.itemRoot);
            menu.getMenu().add(Menu.NONE, 0, 0, R.string.delete);
            menu.getMenu().add(Menu.NONE, 1,1, R.string.edit);
            menu.setOnMenuItemClickListener(v -> {
                switch (v.getItemId()) {
                    case 0:
                        callback.onDelete(list.get(position).getTitle());
                        break;
                    case 1:
                        callback.onEdit(list.get(position).getTitle());
                        break;
                }
                return false;
            });
                    menu.show();
            return false;
            });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        final ItemNoteBinding binding;

        public NoteViewHolder(@NonNull View itemView, ItemNoteBinding binding) {
            super(itemView);
            this.binding = binding;
        }
    }
}
