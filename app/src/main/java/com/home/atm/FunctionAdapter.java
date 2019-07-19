package com.home.atm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FunctionAdapter extends RecyclerView.Adapter<FunctionAdapter.functionViewHolder> {
    private final String[] function;
    Context context;
    public FunctionAdapter(Context context){
        this.context = context;
        function = context.getResources().getStringArray(R.array.functions);
    }

    @NonNull
    @Override
    public functionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(
                android.R.layout.simple_list_item_1, viewGroup, false);
        return new functionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull functionViewHolder functionViewHolder, int i) {
        functionViewHolder.nameText.setText(function[i]);
    }

    @Override
    public int getItemCount() {
        return function.length;
    }

    public class functionViewHolder extends RecyclerView.ViewHolder{
        TextView nameText;

        public functionViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(android.R.id.text1);
        }
    }
}
