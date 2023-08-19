package edu.wgu.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.wgu.capstone.view.vacation.VacationActivity;

/**
 * The home activity.
 */
public class HomeActivity extends AppCompatActivity {

    /**
     * Gets the saved instance state.
     * @param savedInstanceState the context
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    /**
     * Navigate to the VacationActivity
     * @param view the view
     */
    public void navigateToVacationActivity(View view) {
        Intent intent = new Intent(this, VacationActivity.class);
        startActivity(intent);
    }
}