package edu.wgu.capstone.controller.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.wgu.capstone.model.Excursion;
import edu.wgu.capstone.model.Vacation;

/**
 * Data Access Object for Excursion.
 */
@Dao
public interface ExcursionDao {
    /**
     * Gets an Excursion by id.
     * @param id The id of the Excursion.
     * @return The Excursion.
     */
    @Query("SELECT * FROM Excursion WHERE id = :id")
    LiveData<Excursion> getExcursion(long id);

    /**
     * Gets all Excursions for a Vacation.
     * @param vacationId The id of the Vacation.
     * @return The Excursions.
     */
    @Query("SELECT * FROM Excursion WHERE vacation_id = :vacationId ORDER BY id")
    LiveData<List<Excursion>> getExcursions(long vacationId);

    /**
     * Adds an Excursion.
     * @param excursion The Excursion to add.
     * @return The id of the Excursion.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addExcursion(Excursion excursion);

    /**
     * Updates an Excursion.
     * @param excursion The Excursion to update.
     */
    @Update
    void updateExcursion(Excursion excursion);

    /**
     * Deletes an Excursion.
     * @param excursion The Excursion to delete.
     */
    @Delete
    void deleteExcursion(Excursion excursion);

    /**
     * Gets a Vacation by id.
     * @param vacationId The id of the Vacation.
     * @return The Vacation.
     */
    @Query("SELECT * FROM Vacation WHERE id = :vacationId")
    LiveData<Vacation> getVacation(long vacationId);
}

