package edu.wgu.capstone.view.dialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Base class for all dialogs that require a date and time.
 */
public class DialogDate implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private Calendar selectedStartDate;
    private TextView selectedTextView;
    private final Context context;

    /**
     * Constructor for the DialogDate class.
     * @param context The context of the dialog.
     */
    public DialogDate(Context context) {
        this.context = context;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        selectedStartDate = Calendar.getInstance();
        selectedStartDate.set(Calendar.YEAR, year);
        selectedStartDate.set(Calendar.MONTH, month);
        selectedStartDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        showTimePickerDialog();
    }

    /**
     * Sets the date and time for the selected text view.
     * @param view The time picker view.
     * @param hourOfDay The hour of the day.
     * @param minute  The minute of the hour.
     */
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (selectedStartDate != null && selectedTextView != null) {
            selectedStartDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
            selectedStartDate.set(Calendar.MINUTE, minute);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
            selectedTextView.setText(dateFormat.format(selectedStartDate.getTime()));
        }
    }

    /**
     * Gets the date and time for the selected text view.
     * @param textView The text view to get the date and time for.
     */
    public void showDatePickerDialog(TextView textView) {
        selectedTextView = textView;
        if (!textView.isTextSelectable()) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
            }
        }
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context, this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    /**
     * Shows the time picker dialog.
     */
    public void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                context, this,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false
        );
        timePickerDialog.show();
    }

    /**
     * Parses the date string.
     * @param dateString The date string to parse.
     * @return The parsed date.
     */
    public Date parseDateString(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            // If the date does not match the expected format, try parsing with another format
            try {
                SimpleDateFormat altDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.getDefault());
                return altDateFormat.parse(dateString);
            } catch (ParseException ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }
}
