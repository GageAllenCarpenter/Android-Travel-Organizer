package edu.wgu.capstone.view.excursion;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import edu.wgu.capstone.R;
import edu.wgu.capstone.model.Excursion;
import edu.wgu.capstone.view.excursion.listener.ExcursionItemClickListener;

/**
 * This class is the view holder for the excursion recycler view.
 */
public class ExcursionHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private Excursion excursion;
    private final TextView excursionTextView;
    private final ExcursionItemClickListener itemClickListener;

    /**
     * Instantiates a new Excursion holder.
     * @param itemView The item view.
     * @param itemClickListener The item click listener.
     */
    ExcursionHolder(View itemView, ExcursionItemClickListener itemClickListener) {
        super(itemView);
        itemView.setOnClickListener(this);
        excursionTextView = itemView.findViewById(R.id.text_view);
        Button deleteButton = itemView.findViewById(R.id.delete_button);
        Button detailsButton = itemView.findViewById(R.id.details_button);
        this.itemClickListener = itemClickListener;
        deleteButton.setOnClickListener(this);
        detailsButton.setOnClickListener(this);
    }

    /**
     * Binds the excursion to the view holder.
     * @param excursion The excursion.
     */
    void bind(Excursion excursion) {
        this.excursion = excursion;
        excursionTextView.setText(excursion.getTitle());
        excursionTextView.setBackgroundColor(0);
    }

    /**
     * Called when a view has been clicked.
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.delete_button) itemClickListener.onDeleteButtonClick(excursion);
        else if (view.getId() == R.id.details_button) itemClickListener.onDetailsButtonClick(excursion);
    }
}

