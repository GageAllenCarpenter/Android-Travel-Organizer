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
 * Dialog Fragment to add a new vacation.
 * @param <T> DialogListener
 */
public class AddVacationDialogFragment<T extends VacationDialogListener> extends VacationBaseDialogFragment {
    private final T listener;

    /**
     * Constructor for AddVacationDialogFragment.
     * @param context Context
     * @param listener DialogListener
     */
    public AddVacationDialogFragment(Context context, T listener) {
        super(context);
        this.listener = listener;
    }

    /**
     * Creates a new Dialog instance to add a new vacation.
     * @param savedInstanceState The last saved instance state of the Fragment,
     * or null if this is a freshly created Fragment.
     *
     * @return Dialog
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        getVacationStartDateTextView().setOnClickListener(v -> getDialogDate().showDatePickerDialog(getVacationStartDateTextView()));
        getVacationEndDateTextView().setOnClickListener(v -> getDialogDate().showDatePickerDialog(getVacationEndDateTextView()));

        return new AlertDialog.Builder(requireActivity())
                .setTitle(R.string.vacation)
                .setView(getDialogView())
                .setPositiveButton(R.string.create, (dialog, whichButton) -> {
                    String vacation = getVacationTitleEditText().getText().toString();
                    String hotel = getVacationHotelEditText().getText().toString();
                    String description = getVacationDescriptionEditText().getText().toString();
                    Date startDate = getDialogDate().parseDateString(getVacationStartDateTextView().getText().toString());
                    Date endDate = getDialogDate().parseDateString(getVacationEndDateTextView().getText().toString());
                    //validate dates
                    if (startDate != null && endDate != null) {
                        if (endDate.before(startDate)) {
                            Toast.makeText(requireContext(), R.string.invalid_end_date, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        listener.addVacation(new Vacation(vacation, hotel, description, startDate, endDate));
                        if (getVacationAlertsCheckBox().isChecked())scheduleStartNotification(vacation, startDate, endDate);
                    } else {
                        Toast.makeText(requireContext(), R.string.invalid_date, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .create();
    }
}
