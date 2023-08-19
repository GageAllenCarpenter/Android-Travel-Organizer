package edu.wgu.capstone.view.excursion;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.wgu.capstone.controller.repository.ExcursionRepository;
import edu.wgu.capstone.model.Excursion;
import edu.wgu.capstone.model.Vacation;

/**
 * View model for ExcursionListActivity.
 */
public class ExcursionListViewModel extends AndroidViewModel {
    private final ExcursionRepository excursionRepository;
    private LiveData<List<Excursion>> excursions;


    /**
     * Instantiates a new Excursion list view model.
     * @param application The application.
     */
    public ExcursionListViewModel(@NonNull Application application) {
        super(application);
        excursionRepository = new ExcursionRepository(application);
    }

    /**
     * Gets an excursion by id.
     * @param vacationId The vacation id.
     * @return The excursion.
     */
    public LiveData<List<Excursion>> getExcursions(long vacationId) {
        if (excursions == null) {
            excursions = excursionRepository.getExcursions(vacationId);
        }
        return excursions;
    }

    /**
     * Adds an excursion.
     * @param excursion The excursion.
     */
    public void addExcursion(Excursion excursion) {
        excursionRepository.addExcursion(excursion);
    }

    /**
     * Deletes an excursion.
     * @param excursion The excursion.
     */
    public void deleteExcursion(Excursion excursion) {
        excursionRepository.deleteExcursion(excursion);
    }

    /**
     * Updates an excursion.
     * @param excursion The excursion.
     */
    public void updateExcursion(Excursion excursion) {
        excursionRepository.updateExcursion(excursion);
    }

    /**
     * Gets a vacation.
     * @param vacationId The vacation id.
     * @return The vacation.
     */
    public LiveData<Vacation> getVacation(long vacationId) {
        return excursionRepository.getVacation(vacationId);
    }
}

