package edu.wgu.capstone.view.vacation.dialog;

import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import java.util.Date;
import java.util.Map;

import edu.wgu.capstone.R;
import edu.wgu.capstone.view.NotificationReceiver;
import edu.wgu.capstone.view.RequestCodeCounter;
import edu.wgu.capstone.view.dialog.DialogDate;

/**
 * Base dialog fragment for vacation dialogs.
 */
public class VacationBaseDialogFragment extends DialogFragment {
    private final Context context;
    private final View dialogView;
    private final EditText vacationTitleEditText;
    private final EditText vacationHotelEditText;
    private final EditText vacationDescriptionEditText;
    private final TextView vacationStartDateTextView;
    private final TextView vacationEndDateTextView;
    private final CheckBox vacationAlertsCheckBox;
    private final DialogDate dialogDate;
    NotificationManager notificationManager;
    ActivityResultLauncher<String[]> rpl;
    private final String[] REQUIRED_PERMISSIONS = new String[]{Manifest.permission.POST_NOTIFICATIONS};

    /**
     * Constructor for the vacation base dialog fragment.
     * @param context The context of the application.
     */
    public VacationBaseDialogFragment(Context context) {
        this.context = context;
        dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_vacation, null);
        vacationTitleEditText = dialogView.findViewById(R.id.vacation_title_edit_text);
        vacationHotelEditText = dialogView.findViewById(R.id.vacation_hotel_edit_text);
        vacationDescriptionEditText = dialogView.findViewById(R.id.vacation_description_edit_text);
        vacationStartDateTextView = dialogView.findViewById(R.id.vacation_start_date_edit_text);
        vacationEndDateTextView = dialogView.findViewById(R.id.vacation_end_date_edit_text);
        vacationAlertsCheckBox = dialogView.findViewById(R.id.vacation_alerts_check_box);
        dialogDate = new DialogDate(context);
    }

    /**
     * Checks if all permissions are granted and creates the notification channel
     * on attach of the fragment.
     * @param context The context of the application.
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        rpl = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(),
                isGranted -> {
                    for (Map.Entry<String, Boolean> x : isGranted.entrySet()) {
                        x.getValue();
                    }
                }
        );

        createNotificationChannel();

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (!allPermissionsGranted()) {
                rpl.launch(REQUIRED_PERMISSIONS);
            }
        }
    }

    /**
     * Creates a notification channel.
     */
    private void createNotificationChannel() {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        CharSequence name = getString(R.string.channel_name);
        String description = getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        String channelId = "test_channel_01";
        NotificationChannel channel = new NotificationChannel(channelId, name, importance);
        channel.setDescription(description);
        channel.enableLights(true);
        channel.setLightColor(Color.RED);
        channel.enableVibration(true);
        channel.setShowBadge(true);
        channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        notificationManager.createNotificationChannel(channel);
    }

    /**
     * Schedules a notification at the start of the vacation.
     * @param message The message to display in the notification.
     */
    public void scheduleStartNotification(String message, Date startDate, Date endDate) {
        AlarmManager startAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        long startTimeMillis = startDate.getTime();
        Intent startNotificationIntent = new Intent(context, NotificationReceiver.class);
        startNotificationIntent.putExtra("start", "Your vacation at " + message + " is starting!");
        PendingIntent startPendingIntent = PendingIntent.getBroadcast(context, RequestCodeCounter.getInstance().getRequestCode(), startNotificationIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        startAlarmManager.set(AlarmManager.RTC_WAKEUP, startTimeMillis, startPendingIntent);

        AlarmManager endAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        long endTimeMillis = endDate.getTime();
        Intent endNotificationIntent = new Intent(context, NotificationReceiver.class);
        endNotificationIntent.putExtra("end", "Your vacation at " + message + " is ending!");
        PendingIntent endPendingIntent = PendingIntent.getBroadcast(context, RequestCodeCounter.getInstance().getRequestCode(), endNotificationIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        endAlarmManager.set(AlarmManager.RTC_WAKEUP, endTimeMillis, endPendingIntent);
    }


    /**
     * Checks if all permissions are granted.
     * @return True if all permissions are granted, false otherwise.
     */
    private boolean allPermissionsGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public View getDialogView() {
        return dialogView;
    }

    public EditText getVacationTitleEditText() {
        return vacationTitleEditText;
    }

    public EditText getVacationHotelEditText() {
        return vacationHotelEditText;
    }

    public EditText getVacationDescriptionEditText() {
        return vacationDescriptionEditText;
    }

    public TextView getVacationStartDateTextView() {
        return vacationStartDateTextView;
    }

    public TextView getVacationEndDateTextView() {
        return vacationEndDateTextView;
    }

    public CheckBox getVacationAlertsCheckBox() {
        return vacationAlertsCheckBox;
    }

    public DialogDate getDialogDate() {
        return dialogDate;
    }
}
