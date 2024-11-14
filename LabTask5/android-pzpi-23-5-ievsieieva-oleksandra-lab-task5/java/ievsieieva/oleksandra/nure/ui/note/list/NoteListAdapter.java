package ievsieieva.oleksandra.nure.ui.note.list;

import android.util.Log;
import android.util.TypedValue;
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
import ievsieieva.oleksandra.nure.data.source.NoteDataSource;
import ievsieieva.oleksandra.nure.databinding.ItemNoteBinding;


public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {
    private String TAG = NoteListAdapter.class.getSimpleName();
    ArrayList<Note> list = new ArrayList<>();
    OnItemClickCallback callback;
    NoteDataSource noteDataSource = NoteDataSource.getInstance();

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
        Integer textSize = noteDataSource.getTextSize();
        if (textSize == 0) {
            holder.binding.title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            holder.binding.date.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
        } else if (textSize == 1) {
            holder.binding.title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            holder.binding.date.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        } else {
            holder.binding.title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            holder.binding.date.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        }

        holder.binding.itemRoot.setOnClickListener(view -> {
            callback.onItemClick(list.get(position).getId());
        });
        holder.binding.itemRoot.setOnLongClickListener(view -> {
            PopupMenu menu = new PopupMenu(holder.binding.itemRoot.getContext(),holder.binding.itemRoot);
            menu.getMenu().add(Menu.NONE, 0, 0, R.string.delete);
            menu.getMenu().add(Menu.NONE, 1,1, R.string.edit);
            menu.setOnMenuItemClickListener(v -> {
                switch (v.getItemId()) {
                    case 0:
                        callback.onDelete(list.get(position).getId());
                        break;
                    case 1:
                        callback.onEdit(list.get(position).getId());
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
