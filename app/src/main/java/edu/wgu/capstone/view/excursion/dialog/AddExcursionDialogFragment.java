package edu.wgu.capstone.view.excursion.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.Date;

import edu.wgu.capstone.R;
import edu.wgu.capstone.model.Excursion;
import edu.wgu.capstone.model.Vacation;
import edu.wgu.capstone.view.excursion.listener.ExcursionDialogListener;

/**
 * Dialog Fragment to add a new excursion.
 */
public class AddExcursionDialogFragment<T extends ExcursionDialogListener> extends ExcursionBaseDialogFragment {

    private final Vacation vacation;
    private final T listener;


    /**
     * Constructor for AddExcursionDialogFragment.
     * @param vacation Vacation
     */
    public AddExcursionDialogFragment(Context context, Vacation vacation, T listener) {
        super(context);
        this.vacation = vacation;
        this.listener = listener;
    }

    /**
     * Creates a new Dialog instance to add a new excursion.
     * @param savedInstanceState The last saved instance state of the Fragment,
     * or null if this is a freshly created Fragment.
     *
     * @return Dialog
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        getExcursionStartDateTextView().setOnClickListener(v -> getDialogDate().showDatePickerDialog(getExcursionStartDateTextView()));
        getExcursionEndDateTextView().setOnClickListener(v -> getDialogDate().showDatePickerDialog(getExcursionEndDateTextView()));

        return new AlertDialog.Builder(requireActivity())
                .setTitle(R.string.excursion)
                .setView(getDialogView())
                .setPositiveButton(R.string.create, (dialog, whichButton) -> {
                    String excursionTitle = getExcursionTitleEditText().getText().toString();
                    String excursionDescription = getExcursionDescriptionEditText().getText().toString();
                    Date startDate = getDialogDate().parseDateString(getExcursionStartDateTextView().getText().toString());
                    Date endDate = getDialogDate().parseDateString(getExcursionEndDateTextView().getText().toString());

                    if(startDate != null && endDate != null){
                        if(endDate.before(startDate)){
                            Toast.makeText(requireContext(), R.string.invalid_end_date, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if (startDate.before(vacation.getStartDate()) || endDate.after(vacation.getEndDate())) {
                            Toast.makeText(requireContext(), R.string.excursion_out_of_date_range, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        listener.addExcursion(new Excursion(excursionTitle, excursionDescription, startDate, endDate,vacation.getId()));
                        if(getExcursionAlertsCheckBox().isChecked()) scheduleStartNotification(excursionTitle, startDate, endDate);
                    }
                })
                .setNegativeButton(R.string.cancel,null)
                .create();
    }
}
