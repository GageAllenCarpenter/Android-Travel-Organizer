package edu.wgu.capstone.view.excursion.listener;

import edu.wgu.capstone.model.Excursion;

/**
 * Listener for when an excursion is clicked.
 */
public interface ExcursionItemClickListener {
    void onDeleteButtonClick(Excursion excursion);
    void onDetailsButtonClick(Excursion excursion);
}
