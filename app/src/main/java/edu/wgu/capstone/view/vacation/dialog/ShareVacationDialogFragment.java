package edu.wgu.capstone.view.vacation.dialog;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import edu.wgu.capstone.R;
import edu.wgu.capstone.model.Excursion;
import edu.wgu.capstone.model.Vacation;
import edu.wgu.capstone.view.vacation.VacationListViewModel;
import edu.wgu.capstone.view.vacation.listener.VacationDialogListener;

/**
 * This class is used to create a dialog for sharing a vacation.
 * @param <T> The listener for the dialog.
 */
public class ShareVacationDialogFragment<T extends VacationDialogListener> extends VacationBaseDialogFragment {

    private final Vacation vacation;
    private final VacationListViewModel vacationListViewModel;
    private final T listener;

    /**
     * Constructor for the dialog.
     * @param context The context of the dialog.
     * @param vacation The vacation to be shared.
     * @param vacationListViewModel The view model for the vacation list.
     * @param listener The listener for the dialog.
     */
    public ShareVacationDialogFragment(Context context, Vacation vacation, VacationListViewModel vacationListViewModel, T listener) {
        super(context);
        this.vacation = vacation;
        this.vacationListViewModel = vacationListViewModel;
        this.listener = listener;
    }

    /**
     * Creates a dialog for sharing a vacation.
     *
     * @param savedInstanceState The last saved instance state of the Fragment,
     *                           or null if this is a freshly created Fragment.
     * @return A new Dialog instance to be displayed by the Fragment.
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        View dialogView = LayoutInflater.from(requireActivity()).inflate(R.layout.dialog_sms, null);

        final EditText phoneNumberEditText = dialogView.findViewById(R.id.phone_number);
        final EditText vacationTitleEditText = dialogView.findViewById(R.id.vacation_title_edit_text);
        final EditText vacationHotelEditText = dialogView.findViewById(R.id.vacation_hotel_edit_text);
        final EditText vacationDescriptionEditText = dialogView.findViewById(R.id.vacation_description_edit_text);
        final TextView vacationStartDateTextView = dialogView.findViewById(R.id.vacation_start_date_edit_text);
        final TextView vacationEndDateTextView = dialogView.findViewById(R.id.vacation_end_date_edit_text);
        final EditText messageEditText = dialogView.findViewById(R.id.message);

        vacationTitleEditText.setText(vacation.getTitle());
        vacationHotelEditText.setText(vacation.getHotel());
        vacationDescriptionEditText.setText(vacation.getDescription());
        vacationStartDateTextView.setText(vacation.getStartDate().toString());
        vacationEndDateTextView.setText(vacation.getEndDate().toString());

        return new AlertDialog.Builder(requireActivity())
                .setTitle("Share Vacation")
                .setView(dialogView)
                .setPositiveButton(R.string.create, (dialog,whichButton)-> shareVacation(vacation, phoneNumberEditText, messageEditText))
                .setNegativeButton("Cancel", null)
                .create();
    }

    /**
     * Shares a vacation with another user.
     * @param vacation The vacation to be shared.
     * @param phoneNumberEditText The phone number of the user to share the vacation with.
     * @param messageEditText The message to be sent with the vacation.
     */
    private void shareVacation(Vacation vacation, EditText phoneNumberEditText, EditText messageEditText) {
        StringBuilder message = new StringBuilder();
        message.append("Vacation: \n\n");
        message.append("Vacation Title: ").append(vacation.getTitle()).append("\n");
        message.append("Vacation Hotel: ").append(vacation.getHotel()).append("\n");
        message.append("Vacation Description: ").append(vacation.getDescription()).append("\n");
        message.append("Vacation Start Date: ").append(vacation.getStartDate()).append("\n");
        message.append("Vacation End Date: ").append(vacation.getEndDate()).append("\n\n");

        vacationListViewModel.getExcursions(vacation.getId()).observe(this, excursions -> {
            if (excursions != null && !excursions.isEmpty()) {
                message.append("Excursions: \n\n");
                for (Excursion excursion : excursions) {
                    message.append("Excursion Title: ").append(excursion.getTitle()).append("\n");
                    message.append("Excursion Description: ").append(excursion.getDescription()).append("\n");
                    message.append("Excursion Start Date: ").append(excursion.getStartDate()).append("\n");
                    message.append("Excursion End Date: ").append(excursion.getEndDate()).append("\n\n");
                }
            }

            // Append the custom message entered by the user
            String customMessage = messageEditText.getText().toString();
            message.append(customMessage);

            // Send the MMS with the complete message (text-only)
            if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
                sendMMS(phoneNumberEditText.getText().toString(), message.toString());
            else
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.SEND_SMS}, 100);
        });
    }

    /**
     * Checks if the user has granted permission to send SMS messages.
     * @param requestCode The request code passed in {@link #requestPermissions(String[], int)}.
     * @param permissions The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *     which is either {@link android.content.pm.PackageManager#PERMISSION_GRANTED}
     *     or {@link android.content.pm.PackageManager#PERMISSION_DENIED}. Never null.
     *
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            Toast.makeText(requireActivity(), "Permission Granted", Toast.LENGTH_SHORT).show();
        else Toast.makeText(requireActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
    }

    /**
     * This method sends an MMS to the specified phone number with the specified message.
     *
     * @param phoneNumber The phone number.
     * @param message     The message.
     */
    public void sendMMS(String phoneNumber, String message) {
        Intent mmsIntent = new Intent(Intent.ACTION_SEND);
        mmsIntent.putExtra("address", phoneNumber);
        mmsIntent.putExtra("sms_body", message);
        mmsIntent.setType("text/plain");
        startActivity(Intent.createChooser(mmsIntent, "Send MMS"));
    }
}

