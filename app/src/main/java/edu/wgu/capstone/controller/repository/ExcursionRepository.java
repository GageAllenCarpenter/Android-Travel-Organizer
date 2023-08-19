package edu.wgu.capstone.controller.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.wgu.capstone.controller.AppDatabase;
import edu.wgu.capstone.controller.DatabaseContext;
import edu.wgu.capstone.controller.dao.ExcursionDao;
import edu.wgu.capstone.controller.thread.AppExecutors;
import edu.wgu.capstone.model.Excursion;
import edu.wgu.capstone.model.Vacation;

/**
 * Repository for Excursion.
 */
public class ExcursionRepository {
    private final ExcursionDao excursionDao;
    private final AppExecutors appExecutors;

    /**
     * Instantiates a new Excursion repository.
     * @param application The application.
     */
    public ExcursionRepository(Application application) {
        AppDatabase database = DatabaseContext.getDatabase(application);
        excursionDao = database.excursionDao();
        appExecutors = AppExecutors.getInstance();
    }

    /**
     * Gets an excursion by id.
     * @param excursionId The excursion id.
     * @return The excursion.
     */
    public LiveData<Excursion> getExcursion(long excursionId){
        return excursionDao.getExcursion(excursionId);
    }

    /**
     * Gets all excursions for a vacation.
     * @param vacationId The vacation id.
     * @return The excursions.
     */
    public LiveData<List<Excursion>> getExcursions(long vacationId){
        return excursionDao.getExcursions(vacationId);
    }

    /**
     * Adds an excursion.
     * @param excursion The excursion.
     */
    public void addExcursion(Excursion excursion){
        appExecutors.diskIO().execute(() -> {
            long excursionId = excursionDao.addExcursion(excursion);
            excursion.setId(excursionId);
        });
    }

    /**
     * Updates an excursion.
     * @param excursion The excursion.
     */
    public void updateExcursion(Excursion excursion){
        appExecutors.diskIO().execute(() -> excursionDao.updateExcursion(excursion));
    }

    /**
     * Deletes an excursion.
     * @param excursion The excursion.
     */
    public void deleteExcursion(Excursion excursion){
        appExecutors.diskIO().execute(() -> excursionDao.deleteExcursion(excursion));
    }

    /**
     * Gets a vacation by id.
     * @param vacationId The vacation id.
     * @return The vacation.
     */
    public LiveData<Vacation> getVacation(long vacationId) {
        return excursionDao.getVacation(vacationId);
    }
}
