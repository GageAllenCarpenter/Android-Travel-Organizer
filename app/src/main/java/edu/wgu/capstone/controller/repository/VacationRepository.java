package edu.wgu.capstone.controller.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.wgu.capstone.controller.AppDatabase;
import edu.wgu.capstone.controller.DatabaseContext;
import edu.wgu.capstone.controller.dao.VacationDao;
import edu.wgu.capstone.controller.thread.AppExecutors;
import edu.wgu.capstone.model.Vacation;

/**
 * Repository for Vacation.
 */
public class VacationRepository {
    private final VacationDao vacationDao;
    private final AppExecutors appExecutors;

    /**
     * Instantiates a new Vacation repository.
     * @param application The application.
     */
    public VacationRepository(Application application) {
        AppDatabase database = DatabaseContext.getDatabase(application);
        vacationDao = database.vacationDao();
        appExecutors = AppExecutors.getInstance();
    }

    /**
     * Adds a vacation.
     * @param vacation The vacation.
     */
    public void addVacation(Vacation vacation) {
        appExecutors.diskIO().execute(() -> {
            long vacationId = vacationDao.addVacation(vacation);
            vacation.setId(vacationId);
        });
    }

    /**
     * Gets a vacation by id.
     * @param vacationId The vacation id.
     * @return The vacation.
     */
    public LiveData<Vacation> getVacation(long vacationId) {
        return vacationDao.getVacation(vacationId);
    }

    /**
     * Gets all vacations.
     * @return The vacations.
     */
    public LiveData<List<Vacation>> getVacations() {
        return vacationDao.getVacations();
    }

    /**
     * Deletes a vacation.
     * @param vacation The vacation.
     */
    public void deleteVacation(Vacation vacation) {
        appExecutors.diskIO().execute(() -> vacationDao.deleteVacation(vacation));
    }

    /**
     * Updates a vacation.
     * @param vacation The vacation.
     */
    public void updateVacation(Vacation vacation) {
        appExecutors.diskIO().execute(() -> vacationDao.updateVacation(vacation));
    }
}
