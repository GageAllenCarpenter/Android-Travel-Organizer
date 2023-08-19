package edu.wgu.capstone.view.dialog;

import android.content.Context;

import androidx.fragment.app.DialogFragment;

import edu.wgu.capstone.model.Excursion;
import edu.wgu.capstone.model.Vacation;
import edu.wgu.capstone.view.excursion.dialog.AddExcursionDialogFragment;
import edu.wgu.capstone.view.excursion.dialog.EditExcursionDialogFragment;
import edu.wgu.capstone.view.excursion.listener.ExcursionDialogListener;
import edu.wgu.capstone.view.vacation.VacationListViewModel;
import edu.wgu.capstone.view.vacation.dialog.AddVacationDialogFragment;
import edu.wgu.capstone.view.vacation.dialog.EditVacationDialogFragment;
import edu.wgu.capstone.view.vacation.dialog.ShareVacationDialogFragment;
import edu.wgu.capstone.view.vacation.listener.VacationDialogListener;

/**
 * Factory for creating DialogFragments. This is used to create the correct DialogFragment
 * based on the type of CRUD operation being performed and the type of object being operated on.
 */
public class DialogFragmentFactory<T extends DialogListener> implements DialogFactory<T> {
    private final boolean isVacation;
    private final Context context;
    private Vacation vacation;
    private Excursion excursion;
    private VacationListViewModel viewModel;

    /**
     * Constructor for DialogFragmentFactory. Takes a boolean to determine if the DialogFragment
     * being created is for a Vacation or an Excursion.
     * @param isVacation boolean
     */
    public DialogFragmentFactory(Context context, boolean isVacation) {
        this.context = context;
        this.isVacation = isVacation;
    }

    /**
     * Constructor for DialogFragmentFactory. Takes a boolean to determine if the DialogFragment
     * being created is for a Vacation or an Excursion, and a Vacation object.
     * @param isVacation boolean
     * @param vacation Vacation
     */
    public DialogFragmentFactory(Context context, boolean isVacation, Vacation vacation){
        this.context = context;
        this.isVacation = isVacation;
        this.vacation = vacation;
    }

    /**
     * Constructor for DialogFragmentFactory. Takes a boolean to determine if the DialogFragment
     * being created is for a Vacation or an Excursion, a Vacation object, and an Excursion object.
     * @param isVacation boolean
     * @param vacation Vacation
     * @param excursion Excursion
     */
    public DialogFragmentFactory(Context context, boolean isVacation, Vacation vacation, Excursion excursion){
        this.context = context;
        this.isVacation = isVacation;
        this.vacation = vacation;
        this.excursion = excursion;
    }

    /**
     * Constructor for DialogFragmentFactory. Takes a boolean to determine if the DialogFragment
     * being created is for a Vacation or an Excursion, a Vacation object, and a VacationListViewModel.
     * @param context Context
     * @param isVacation boolean
     * @param vacation Vacation
     * @param viewModel VacationListViewModel
     */
    public DialogFragmentFactory(Context context, boolean isVacation, Vacation vacation, VacationListViewModel viewModel){
        this.context = context;
        this.isVacation = isVacation;
        this.vacation = vacation;
        this.viewModel = viewModel;
    }

    /**
     * Creates a DialogFragment that is used to create a new object.
     * @return DialogFragment
     */
    @Override
    public DialogFragment createDialogFragment(T listener) {
        if(isVacation){
            VacationDialogListener vacationDialogListener = (VacationDialogListener) listener;
            return new AddVacationDialogFragment(context, vacationDialogListener);
        } else {
            ExcursionDialogListener excursionDialogListener = (ExcursionDialogListener) listener;
            return new AddExcursionDialogFragment(context, vacation, excursionDialogListener);
        }
    }

    /**
     * Creates a DialogFragment that is used to edit an existing object.
     * @return DialogFragment
     */
    @Override
    public DialogFragment editDialogFragment(T listener) {
        if(isVacation){
            VacationDialogListener vacationDialogListener = (VacationDialogListener) listener;
            return new EditVacationDialogFragment(context,vacation, vacationDialogListener);
        } else {
            ExcursionDialogListener excursionDialogListener = (ExcursionDialogListener) listener;
            return new EditExcursionDialogFragment(context, vacation, excursion, excursionDialogListener);
        }
    }

    /**
     * Creates a DialogFragment that is used to share an existing object.
     * @return DialogFragment
     */
    @Override
    public DialogFragment shareDialogFragment(T listener) {
        if(isVacation){
            VacationDialogListener vacationDialogListener = (VacationDialogListener) listener;
            return new ShareVacationDialogFragment(context, vacation, viewModel, vacationDialogListener);
        } else {
            return null; //NOTE: Excursions cannot be shared
        }
    }
}

