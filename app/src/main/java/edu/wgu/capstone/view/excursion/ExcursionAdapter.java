package edu.wgu.capstone.view.excursion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.wgu.capstone.R;
import edu.wgu.capstone.model.Excursion;
import edu.wgu.capstone.model.Vacation;
import edu.wgu.capstone.view.excursion.listener.ExcursionItemClickListener;

/**
 * Adapter for excursion recycler view.
 */
public class ExcursionAdapter extends RecyclerView.Adapter<ExcursionHolder>{
    private List<Excursion> excursions;

    /**
     * Instantiates a new Excursion adapter.
     * @param excursions The excursions.
     */
    public ExcursionAdapter(List<Excursion> excursions) {
        this.excursions = excursions;
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
    public ExcursionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_items_excursion, parent, false);

        return new ExcursionHolder(view, new ExcursionItemClickListener() {
            @Override
            public void onDeleteButtonClick(Excursion excursion) {
                ExcursionActivity activity = (ExcursionActivity) view.getContext();
                activity.onDeleteClicked(excursion);
            }
            @Override
            public void onDetailsButtonClick(Excursion excursion) {
                ExcursionActivity activity = (ExcursionActivity) view.getContext();
                activity.onDetailsClicked(excursion);
            }
        });
    }

    public void setData(List<Excursion> data) {
        excursions = data;
        notifyDataSetChanged();
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ExcursionHolder holder, int position) {
        holder.bind(excursions.get(position));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return excursions.size();
    }
}