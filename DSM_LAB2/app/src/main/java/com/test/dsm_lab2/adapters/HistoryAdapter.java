package com.test.dsm_lab2.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.dsm_lab2.R;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    final ArrayList<Double> bought;

    public HistoryAdapter(ArrayList<Double> bought) {
        this.bought = bought;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.history_item, parent, false);
        return new HistoryAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Double bought = this.bought.get(position);
        holder.getHistoryValue().setText("Compra hecha: $" + bought);
    }

    @Override
    public int getItemCount() {
        return bought.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView historyValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            historyValue = itemView.findViewById(R.id.historyValue);
        }

        public TextView getHistoryValue() {
            return historyValue;
        }
    }
}
