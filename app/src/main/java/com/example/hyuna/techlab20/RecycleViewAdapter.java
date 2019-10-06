package com.example.hyuna.techlab20;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private ArrayList<Dictionary> mItems;

    public RecycleViewAdapter(ArrayList<Dictionary> items) {
        mItems = items;
    }

    @Override
    public int getItemCount() {
        return (null != mItems ? mItems.size() : 0);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dictionary item = mItems.get(position);
        holder.bind(item);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private View v;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            v=itemView;
        }

        @Override
        public String toString() {
            return super.toString();
        }

        public void bind(Dictionary listItem) {
            TextView tv_date = v.findViewById(R.id.date);
            TextView tv_purpose = v.findViewById(R.id.purpose);
            TextView tv_amount = v.findViewById(R.id.amount);
            TextView tv_result = v.findViewById(R.id.result);
            tv_date.setText(listItem.getDate());
            tv_purpose.setText(listItem.getPurpose());
            tv_amount.setText(listItem.getAmount());
            tv_result.setText(listItem.getResult());

        }
    }

}
