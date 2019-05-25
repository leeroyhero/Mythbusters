package ru.bogdanov.mythbusters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.bogdanov.mythbusters.items.HistoryItem;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.MyViewHolder> {
    private List<HistoryItem> list;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textViewDateTime;
        public TextView textViewThreads;
        public TextView textViewSeconds;
        public MyViewHolder(View v) {
            super(v);
            textViewDateTime=v.findViewById(R.id.textViewTime);
            textViewThreads=v.findViewById(R.id.textViewThreads);
            textViewSeconds=v.findViewById(R.id.textViewSeconds);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public HistoryRecyclerAdapter() {
        list=new ArrayList<>();
    }

    public void addToList(HistoryItem historyItem){
        list.add(0,historyItem);
        notifyItemInserted(0);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public HistoryRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_item, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HistoryItem historyItem=list.get(position);
        holder.textViewDateTime.setText(historyItem.getDateTime());
        holder.textViewThreads.setText(String.format("Количество потоков: %d", historyItem.getThreadCount()));
        holder.textViewSeconds.setText(String.format("Затрачено секунд: %d", historyItem.getSecondsSpent()));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list.size();
    }
}
