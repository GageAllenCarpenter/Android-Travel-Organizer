package edu.wgu.capstone.view.vacation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.wgu.capstone.R;
import edu.wgu.capstone.model.Vacation;
import edu.wgu.capstone.view.vacation.listener.VacationItemClickListener;

/**
 * This class is the adapter for the vacation recycler view.
 */
public class VacationAdapter extends RecyclerView.Adapter<VacationHolder> {

    private List<Vacation> vacations;

    /**
     * Instantiates a new Vacation adapter.
     * @param vacations The vacations.
     */
    public VacationAdapter(List<Vacation> vacations) {
        this.vacations = vacations;
    }

    /**
     * Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public VacationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_items_vacation, parent, false);
        return new VacationHolder(view, new VacationItemClickListener() {
            @Override
            public void onVacationClick(Vacation vacation) {
                VacationActivity activity = (VacationActivity) view.getContext();
                activity.onVacationClicked(vacation);
            }

            @Override
            public void onDeleteButtonClick(Vacation vacation) {
                VacationActivity activity = (VacationActivity) view.getContext();
                activity.onDeleteClicked(vacation);
            }

            @Override
            public void onDetailsButtonClick(Vacation vacation) {
                VacationActivity activity = (VacationActivity) view.getContext();
                activity.onDetailsClicked(vacation);
            }

            @Override
            public void onShareButtonClick(Vacation vacation) {
                VacationActivity activity = (VacationActivity) view.getContext();
                activity.onShareClicked(vacation);
            }
        });
    }

    public void setData(List<Vacation> data) {
        vacations = data;
        notifyDataSetChanged();
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull VacationHolder holder, int position) {
        holder.bind(vacations.get(position));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return vacations.size();
    }
}