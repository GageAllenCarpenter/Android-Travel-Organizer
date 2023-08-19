package edu.wgu.capstone.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

/**
 * Represents a vacation.
 */
@Entity
public class Vacation {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Long id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "hotel")
    private String hotel;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "start_date")
    private Date startDate;
    @ColumnInfo(name = "end_date")
    private Date endDate;

    public Vacation(@NonNull String title, @NonNull String hotel, @NonNull String description, @NonNull Date startDate, @NonNull Date endDate) {
        this.title = title;
        this.hotel = hotel;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
