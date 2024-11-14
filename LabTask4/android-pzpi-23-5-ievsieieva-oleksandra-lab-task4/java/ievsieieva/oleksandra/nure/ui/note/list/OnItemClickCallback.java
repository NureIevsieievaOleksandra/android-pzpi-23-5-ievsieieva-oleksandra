package ievsieieva.oleksandra.nure.ui.note.list;

public interface OnItemClickCallback {
    void onItemClick(String title);
    void onDelete(String title);
    void onEdit(String title);
}
