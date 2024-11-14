package ievsieieva.oleksandra.nure.data.model;

import androidx.annotation.DrawableRes;

import java.util.Date;

public class Note {
    private Integer id;
    private String title;
    private String desc;
    private Type type;
    private Date date;
    @DrawableRes private Integer icon;

    public Note(
            Integer id,
            String title,
            String desc,
            Type type,
            Date date,
            @DrawableRes Integer icon
    ) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.type = type;
        this.date = date;
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public Type getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public Integer getIcon() {
        return icon;
    }

    public enum Type {
        LOW, NORMAL, HIGH
    }
}
