package edu.wgu.capstone.view.vacation;

import static edu.wgu.capstone.view.excursion.ExcursionActivity.EXTRA_VACATION_ID;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.wgu.capstone.R;
import edu.wgu.capstone.model.Vacation;
import edu.wgu.capstone.view.dialog.DialogFactory;
import edu.wgu.capstone.view.dialog.DialogFragmentFactory;
import edu.wgu.capstone.view.excursion.ExcursionActivity;
import edu.wgu.capstone.view.vacation.listener.VacationDialogListener;

/**
 * This activity displays a list of Vacations.
 */
public class VacationActivity extends AppCompatActivity implements VacationDialogListener {
    private RecyclerView recyclerView;
    private VacationAdapter vacationAdapter;
    private List<Vacation> originalVacationList;
    private VacationListViewModel vacationListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation);
        setupViews();
        setupRecyclerView();
        observeVacations();
    }

    /**
     * This method sets up the views.
     */
    private void setupViews() {
        findViewById(R.id.add_vacation_button).setOnClickListener(view -> addVacationClick());

        EditText searchEditText = findViewById(R.id.searchEditText);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not needed for this example
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Called when the text changes
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Called after the text has changed
                String searchText = editable.toString();
                filterVacationList(searchText);
            }
        });
    }

    private void filterVacationList(String searchText) {
        List<Vacation> filteredList = new ArrayList<>();
        for (Vacation vacation : originalVacationList) {
            if (vacation.getTitle().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(vacation);
            }
        }
        vacationAdapter.setData(filteredList);
    }

    /**
     * This method sets up the recycler view.
     */
    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.vacation_recycler_view);
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    /**
     * This method observes the vacations.
     */
    private void observeVacations() {
        vacationListViewModel = new ViewModelProvider(this).get(VacationListViewModel.class);
        vacationListViewModel.getVacations().observe(this, this::updateUI);
    }

    /**
     * This method updates the UI.
     * @param vacationList The list of vacations.
     */
    private void updateUI(List<Vacation> vacationList) {
        originalVacationList = new ArrayList<>(vacationList);
        vacationAdapter = new VacationAdapter(vacationList);
        recyclerView.setAdapter(vacationAdapter);
    }

    /**
     * This method is called when the user enters a vacation.
     * @param vacation The vacation.
     */
    @Override
    public void addVacation(@NonNull Vacation vacation) {
        vacationListViewModel.addVacation(vacation);
        Toast.makeText(this, "Added " + vacation.getTitle(), Toast.LENGTH_SHORT).show();
    }

    /**
     * This method is called when the user clicks the Add Vacation button.
     */
    private void addVacationClick() {
        DialogFactory<VacationDialogListener> vacationFactory = new DialogFragmentFactory<>(this, true);
        vacationFactory.createDialogFragment(this).show(getSupportFragmentManager(), "addVacationDialog");
    }

    /**
     * This method is called when the user updates a vacation.
     * @param vacation The vacation.
     */
    @Override
    public void editVacation(@NonNull Vacation vacation){
        vacationListViewModel.updateVacation(vacation);
        Toast.makeText(this, "Updated " + vacation.getTitle(), Toast.LENGTH_SHORT).show();
    }

    /**
     * This method is called when the user clicks the Details button.
     * @param vacation The vacation.
     */
    public void onDetailsClicked(@NonNull Vacation vacation) {
        DialogFactory<VacationDialogListener> vacationFactory = new DialogFragmentFactory<>(this, true, vacation);
        vacationFactory.editDialogFragment(this).show(getSupportFragmentManager(), "addVacationDialog");
    }

    /**
     * This method is called when the user clicks a vacation.
     * @param vacation The vacation.
     */
    public void onVacationClicked(@NonNull Vacation vacation) {
        Intent intent = new Intent(this, ExcursionActivity.class);
        intent.putExtra(EXTRA_VACATION_ID, vacation.getId());
        startActivity(intent);
    }

    /**
     * This method is called when the user clicks the Delete button.
     * The vacation will only delete if no excursions are associated with it.
     * @param vacation The vacation.
     */
    public void onDeleteClicked(@NonNull Vacation vacation) {
        vacationListViewModel.getExcursions(vacation.getId()).observe(this, excursions -> {
            if(excursions != null && !excursions.isEmpty()) Toast.makeText(this, "Cannot delete vacation. Excursions are associated with it.", Toast.LENGTH_SHORT).show();
            else {
                vacationListViewModel.deleteVacation(vacation);
                Toast.makeText(this, "Deleted " + vacation.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * This method is called when the user clicks the Share button.
     * @param vacation The vacation.
     */
    public void onShareClicked(@NonNull Vacation vacation){
        DialogFactory<VacationDialogListener> vacationFactory = new DialogFragmentFactory<>(this, true, vacation, vacationListViewModel);
        vacationFactory.shareDialogFragment(this).show(getSupportFragmentManager(), "addVacationDialog");
    }
}
