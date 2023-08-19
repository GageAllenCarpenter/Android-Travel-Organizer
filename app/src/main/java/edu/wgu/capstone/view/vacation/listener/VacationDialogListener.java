package edu.wgu.capstone.view.vacation.listener;

import androidx.annotation.NonNull;

import edu.wgu.capstone.model.Vacation;
import edu.wgu.capstone.view.dialog.DialogListener;

/**
 * Base interface for all vacation dialog listeners.
 */
public interface VacationDialogListener extends DialogListener {
    void addVacation(@NonNull Vacation vacation);
    void editVacation(@NonNull Vacation vacation);
}
