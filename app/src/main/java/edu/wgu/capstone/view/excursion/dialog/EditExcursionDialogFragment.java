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
 * Dialog Fragment to edit an existing excursion. This dialog also provides details
 * on the existing excursion.
 */
public class EditExcursionDialogFragment<T extends ExcursionDialogListener> extends ExcursionBaseDialogFragment {

    private final Vacation vacation;
    private final Excursion excursion;
    private final T listener;

    /**
     * Constructor for EditExcursionDialogFragment.
     * @param context Context
     * @param vacation Vacation
     * @param excursion Excursion
     * @param listener DialogListener
     */
    public EditExcursionDialogFragment(Context context, Vacation vacation, Excursion excursion, T listener){
        super(context);
        this.vacation = vacation;
        this.excursion = excursion;
        this.listener = listener;
    }

    /**
     * Creates a new Dialog instance to edit an existing excursion.
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

        getExcursionTitleEditText().setText(excursion.getTitle());
        getExcursionDescriptionEditText().setText(excursion.getDescription());
        getExcursionStartDateTextView().setText(excursion.getStartDate().toString());
        getExcursionEndDateTextView().setText(excursion.getEndDate().toString());


        return new AlertDialog.Builder(requireActivity())
                .setTitle("Update Excursion")
                .setView(getDialogView())
                .setPositiveButton("Update", (dialog, which) -> {
                    String excursionTitle = getExcursionTitleEditText().getText().toString();
                    String excursionDescription = getExcursionDescriptionEditText().getText().toString();
                    Date startDate = getDialogDate().parseDateString(getExcursionStartDateTextView().getText().toString());
                    Date endDate = getDialogDate().parseDateString(getExcursionEndDateTextView().getText().toString());
                    //validate dates
                    if(startDate != null && endDate != null){
                        if(endDate.before(startDate)){
                            Toast.makeText(requireContext(), R.string.invalid_end_date, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if (startDate.before(vacation.getStartDate()) || endDate.after(vacation.getEndDate())) {
                            Toast.makeText(requireContext(), R.string.excursion_out_of_date_range, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        excursion.setTitle(excursionTitle);
                        excursion.setDescription(excursionDescription);
                        excursion.setStartDate(startDate);
                        excursion.setEndDate(endDate);
                        listener.editExcursion(excursion);
                        if(getExcursionAlertsCheckBox().isChecked()) scheduleStartNotification(excursionTitle, startDate, endDate);
                    }
                })
                .setNegativeButton(R.string.cancel,null)
                .create();
    }
}
