package edu.wgu.capstone.view.vacation;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import edu.wgu.capstone.R;
import edu.wgu.capstone.model.Vacation;
import edu.wgu.capstone.view.vacation.listener.VacationItemClickListener;

/**
 * This class is the view holder for the vacation recycler view.
 */
public class VacationHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Vacation vacation;
    private final TextView vacationTextView;
    private final VacationItemClickListener itemClickListener;

    /**
     * Instantiates a new Vacation holder.
     * @param itemView The item view.
     */
    VacationHolder(View itemView, VacationItemClickListener itemClickListener) {
        super(itemView);
        itemView.setOnClickListener(this);
        vacationTextView = itemView.findViewById(R.id.text_view);
        Button deleteButton = itemView.findViewById(R.id.delete_button);
        Button detailsButton = itemView.findViewById(R.id.details_button);
        Button shareButton = itemView.findViewById(R.id.share_button);
        this.itemClickListener = itemClickListener;
        deleteButton.setOnClickListener(this);
        detailsButton.setOnClickListener(this);
        shareButton.setOnClickListener(this);
    }

    /**
     * Binds the vacation to the view holder.
     * @param vacation The vacation.
     */
    void bind(Vacation vacation) {
        this.vacation = vacation;
        vacationTextView.setText(vacation.getTitle());
        vacationTextView.setBackgroundColor(0);
    }

    /**
     * Called when a view has been clicked.
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.delete_button) itemClickListener.onDeleteButtonClick(vacation);
        else if (view.getId() == R.id.details_button) itemClickListener.onDetailsButtonClick(vacation);
        else if (view.getId() == R.id.share_button) itemClickListener.onShareButtonClick(vacation);
        else itemClickListener.onVacationClick(vacation);
    }
}
