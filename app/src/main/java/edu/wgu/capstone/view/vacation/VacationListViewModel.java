package edu.wgu.capstone.view.vacation;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.wgu.capstone.controller.repository.ExcursionRepository;
import edu.wgu.capstone.controller.repository.VacationRepository;
import edu.wgu.capstone.model.Excursion;
import edu.wgu.capstone.model.Vacation;

/**
 * View model for VacationListActivity.
 */
public class VacationListViewModel extends AndroidViewModel {

    private final VacationRepository vacationRepository;
    private final LiveData<List<Vacation>> vacations;
    private final ExcursionRepository excursionRepository;
    private LiveData<List<Excursion>> excursions;

    /**
     * Instantiates a new Vacation list view model.
     * @param application The application.
     */
    public VacationListViewModel(Application application){
        super(application);
        vacationRepository = new VacationRepository(application);
        vacations = vacationRepository.getVacations();
        excursionRepository = new ExcursionRepository(application);
    }

    /**
     * Gets a vacation by id.
     * @return The vacation.
     */
    public LiveData<List<Vacation>> getVacations() {
        return vacations;
    }

    /**
     * Gets a vacation by id.
     * @param vacationId The vacation id.
     * @return The vacation.
     */
    public LiveData<Vacation> getVacation(long vacationId) {
        return vacationRepository.getVacation(vacationId);
    }

    /**
     * Adds a vacation.
     * @param vacation The vacation.
     */
    public void addVacation(Vacation vacation) {
        vacationRepository.addVacation(vacation);
    }

    /**
     * Deletes a vacation.
     * @param vacation The vacation.
     */
    public void deleteVacation(Vacation vacation) {
        vacationRepository.deleteVacation(vacation);
    }

    /**
     * Updates a vacation.
     * @param vacation The vacation.
     */
    public void updateVacation(Vacation vacation) {
        vacationRepository.updateVacation(vacation);
    }

    /**
     * Gets all excursions by a vacation id.
     * @param vacationId The vacation id.
     * @return The excursion.
     */
    public LiveData<List<Excursion>> getExcursions(long vacationId) {
        if (excursions == null) excursions = excursionRepository.getExcursions(vacationId);
        return excursions;
    }
}

