package edu.wgu.capstone.view.excursion.listener;

import edu.wgu.capstone.model.Excursion;
import edu.wgu.capstone.view.dialog.DialogListener;

/**
 * Base interface for all excursion dialog listeners.
 */
public interface ExcursionDialogListener extends DialogListener {
    void addExcursion(Excursion excursion);
    void editExcursion(Excursion excursion);
}
