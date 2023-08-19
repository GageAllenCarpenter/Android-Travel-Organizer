package edu.wgu.capstone.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * Represents an excursion.
 */
@Entity(foreignKeys = @ForeignKey(entity = Vacation.class, parentColumns = "id", childColumns = "vacation_id", onDelete = ForeignKey.CASCADE))
public class Excursion {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Long id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "start_date")
    private Date startDate;
    @ColumnInfo(name = "end_date")
    private Date endDate;
    @ColumnInfo(name = "vacation_id")
    private long vacationId;

    public Excursion(@NotNull String title, @NotNull String description, @NotNull Date startDate, @NotNull Date endDate, long vacationId) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.vacationId = vacationId;
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

    public long getVacationId() {
        return vacationId;
    }

    public void setVacationId(long vacationId) {
        this.vacationId = vacationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

