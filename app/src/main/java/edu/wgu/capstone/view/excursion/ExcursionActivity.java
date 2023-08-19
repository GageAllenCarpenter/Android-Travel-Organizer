package edu.wgu.capstone.view.excursion;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.wgu.capstone.R;
import edu.wgu.capstone.model.Excursion;
import edu.wgu.capstone.model.Vacation;
import edu.wgu.capstone.view.dialog.DialogFactory;
import edu.wgu.capstone.view.dialog.DialogFragmentFactory;
import edu.wgu.capstone.view.excursion.listener.ExcursionDialogListener;

/**
 * Activity for viewing a list of excursions.
 */
public class ExcursionActivity extends AppCompatActivity implements ExcursionDialogListener {
    private RecyclerView recyclerView;
    private ExcursionAdapter excursionAdapter;
    private List<Excursion> originalExcursionList;
    private ExcursionListViewModel excursionListViewModel;
    public static final String EXTRA_VACATION_ID = "extra_vacation_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excursion);
        setupViews();
        setupRecyclerView();
        observeExcursions(getIntent().getLongExtra(EXTRA_VACATION_ID, 0));
    }

    /**
     * This method sets up the views.
     */
    private void setupViews(){
        findViewById(R.id.add_excursion_button).setOnClickListener(view -> addExcursionClick());

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
                filterExcursionList(searchText);
            }
        });
    }

    private void filterExcursionList(String searchText) {
        List<Excursion> filteredList = new ArrayList<>();
        for (Excursion excursion : originalExcursionList) {
            if (excursion.getTitle().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(excursion);
            }
        }
        excursionAdapter.setData(filteredList);
    }

    /**
     * This method sets up the recycler view.
     */
    private void setupRecyclerView(){
        recyclerView = findViewById(R.id.excursion_recycler_view);
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    /**
     * This method observes the excursions.
     *
     * @param id the id of the vacation
     */
    private void observeExcursions(long id){
        excursionListViewModel = new ViewModelProvider(this).get(ExcursionListViewModel.class);
        excursionListViewModel.getExcursions(id).observe(this, this::updateUI);
    }

    /**
     * This method updates the UI.
     *
     * @param excursionList the list of excursions
     */
    private void updateUI(List<Excursion> excursionList){
        originalExcursionList = new ArrayList<>(excursionList);
        excursionAdapter = new ExcursionAdapter(excursionList);
        recyclerView.setAdapter(excursionAdapter);
    }

    /**
     * This method handles the click of the add button.
     * @param excursion the excursion to be added
     */
    @Override
    public void addExcursion(Excursion excursion){
        excursionListViewModel.addExcursion(excursion);
        Toast.makeText(this, "Added " + excursion.getTitle(), Toast.LENGTH_SHORT).show();
    }

    /**
     * This method handles the click of the add button.
     */
    private void addExcursionClick() {
        long vacationId = getIntent().getLongExtra(EXTRA_VACATION_ID, 0);
        LiveData<Vacation> vacationLiveData = excursionListViewModel.getVacation(vacationId);
        vacationLiveData.observe(this, new Observer<Vacation>() {
            @Override
            public void onChanged(Vacation vacation) {
                vacationLiveData.removeObserver(this);
                if (vacation != null) {
                    DialogFactory<ExcursionDialogListener> excursionFactory = new DialogFragmentFactory<>(ExcursionActivity.this, false, vacation);
                    excursionFactory.createDialogFragment(ExcursionActivity.this).show(getSupportFragmentManager(), "addExcursionDialog");
                } else {
                    Toast.makeText(ExcursionActivity.this, "Vacation not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * This method handles the click of the edit button.
     * @param excursion the excursion to be edited
     */
    @Override
    public void editExcursion(Excursion excursion){
        excursionListViewModel.updateExcursion(excursion);
        Toast.makeText(this, "Updated " + excursion.getTitle(), Toast.LENGTH_SHORT).show();
    }

    /**
     * This method handles the click of the delete button.
     * @param excursion the excursion to be deleted
     */
    public void onDetailsClicked(@NonNull Excursion excursion) {
        long vacationId = getIntent().getLongExtra(EXTRA_VACATION_ID, 0);
        LiveData<Vacation> vacationLiveData = excursionListViewModel.getVacation(vacationId);
        vacationLiveData.observe(this, new Observer<Vacation>() {
            @Override
            public void onChanged(Vacation vacation) {
                vacationLiveData.removeObserver(this);
                if (vacation != null) {
                    DialogFactory<ExcursionDialogListener> excursionFactory = new DialogFragmentFactory<>(ExcursionActivity.this, false, vacation, excursion);
                    excursionFactory.editDialogFragment(ExcursionActivity.this).show(getSupportFragmentManager(), "editExcursionDialog");
                } else {
                    Toast.makeText(ExcursionActivity.this, "Vacation not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * This method deletes an excursion.
     * @param excursion the excursion to be deleted
     */
    public void onDeleteClicked(@NonNull Excursion excursion){
        excursionListViewModel.deleteExcursion(excursion);
        Toast.makeText(this, "Deleted " + excursion.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
