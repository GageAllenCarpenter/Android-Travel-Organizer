package edu.wgu.capstone.view.vacation.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.Date;

import edu.wgu.capstone.R;
import edu.wgu.capstone.model.Vacation;
import edu.wgu.capstone.view.vacation.listener.VacationDialogListener;

/**
 * Dialog for editing a vacation
 * @param <T> The listener for the dialog.
 */
public class EditVacationDialogFragment<T extends VacationDialogListener> extends VacationBaseDialogFragment {

    private final Vacation vacation;
    private final T listener;

    /**
     * Constructor for the dialog.
     * @param context The context of the dialog.
     * @param vacation The vacation to be edited.
     * @param listener The listener for the dialog.
     */
    public EditVacationDialogFragment(Context context, Vacation vacation, T listener) {
        super(context);
        this.vacation = vacation;
        this.listener = listener;
    }

    /**
     * Creates a dialog for editing a vacation.
     * @param savedInstanceState The last saved instance state of the Fragment,
     * or null if this is a freshly created Fragment.
     *
     * @return A new Dialog instance to be displayed by the Fragment.
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        getVacationTitleEditText().setText(vacation.getTitle());
        getVacationHotelEditText().setText(vacation.getHotel());
        getVacationDescriptionEditText().setText(vacation.getDescription());
        getVacationStartDateTextView().setText(vacation.getStartDate().toString());
        getVacationEndDateTextView().setText(vacation.getEndDate().toString());

        // Set click listeners for the date pickers
        getVacationStartDateTextView().setOnClickListener(v -> getDialogDate().showDatePickerDialog(getVacationStartDateTextView()));
        getVacationEndDateTextView().setOnClickListener(v -> getDialogDate().showDatePickerDialog(getVacationEndDateTextView()));

        return new AlertDialog.Builder(requireActivity())
                .setTitle("Update Vacation")
                .setView(getDialogView())
                .setPositiveButton("Update", (dialog, which) -> {
                    String vacationTitle = getVacationTitleEditText().getText().toString();
                    String vacationHotel = getVacationHotelEditText().getText().toString();
                    String vacationDescription = getVacationDescriptionEditText().getText().toString();
                    Date startDate = getDialogDate().parseDateString(getVacationStartDateTextView().getText().toString());
                    Date endDate = getDialogDate().parseDateString(getVacationEndDateTextView().getText().toString());
                    //validate dates
                    if (startDate != null && endDate != null) {
                        if (endDate.before(startDate)) {
                            Toast.makeText(requireContext(), R.string.invalid_end_date, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        vacation.setTitle(vacationTitle);
                        vacation.setHotel(vacationHotel);
                        vacation.setDescription(vacationDescription);
                        vacation.setStartDate(startDate);
                        vacation.setEndDate(endDate);
                        listener.addVacation(vacation);
                        if (getVacationAlertsCheckBox().isChecked()) scheduleStartNotification(vacationTitle, startDate, endDate);
                    } else {
                        Toast.makeText(requireContext(), R.string.invalid_date, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
    }
}
