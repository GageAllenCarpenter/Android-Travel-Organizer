package edu.wgu.capstone.view.vacation.listener;


import edu.wgu.capstone.model.Vacation;

/**
 * This interface is used to handle the click events on the vacation recycler view.
 */
public interface VacationItemClickListener {
    void onVacationClick(Vacation vacation);
    void onDeleteButtonClick(Vacation vacation);
    void onDetailsButtonClick(Vacation vacation);
    void onShareButtonClick(Vacation vacation);
}

