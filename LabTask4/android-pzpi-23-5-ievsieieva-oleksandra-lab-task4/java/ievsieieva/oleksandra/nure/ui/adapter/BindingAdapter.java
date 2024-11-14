package ievsieieva.oleksandra.nure.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ievsieieva.oleksandra.nure.R;
import ievsieieva.oleksandra.nure.data.model.Note;

public abstract class BindingAdapter {
    @androidx.databinding.BindingAdapter({"formatDate"})
    public static void formatDate(TextView view, Date date) {
        if (view != null && date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm dd.MM.yyyy");
            view.setText(simpleDateFormat.format(date));
        }
    }

    @androidx.databinding.BindingAdapter({"priority"})
    public static void setPriority(ImageView view, Note.Type type) {
        if (view != null && type != null) {
            switch (type) {
                case LOW:
                    view.setImageResource(R.drawable.low);
                    break;
                case NORMAL:
                    view.setImageResource(R.drawable.normal);
                    break;
                case HIGH:
                    view.setImageResource(R.drawable.high);
                    break;
            }
        }
    }

    @androidx.databinding.BindingAdapter({"imageByRes"})
    public static void setImageByRes(ImageView view, @DrawableRes Integer image) {
        if (view != null && image != null) {
            view.setImageResource(image);
        }
    }
}
