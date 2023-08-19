package edu.wgu.capstone.controller.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.wgu.capstone.model.Vacation;

/**
 * Data Access Object for Vacation.
 */
@Dao
public interface VacationDao {
    /**
     * Gets a Vacation by id.
     * @param id The id of the Vacation.
     * @return The Vacation.
     */
    @Query("SELECT * FROM Vacation WHERE id = :id")
    LiveData<Vacation> getVacation(long id);

    /**
     * Gets all Vacations.
     * @return The Vacations.
     */
    @Query("SELECT * FROM Vacation ORDER BY title COLLATE NOCASE")
    LiveData<List<Vacation>> getVacations();

    /**
     * Adds a Vacation.
     * @param vacation The Vacation to add.
     * @return The id of the Vacation.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addVacation(Vacation vacation);

    /**
     * Updates a Vacation.
     * @param vacation The Vacation to update.
     */
    @Update
    void updateVacation(Vacation vacation);

    /**
     * Deletes a Vacation.
     * @param vacation The Vacation to delete.
     */
    @Delete
    void deleteVacation(Vacation vacation);
}

