package com.example.mifans.recycleviewtest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<String> strings;

    public MyAdapter(Context context, List<String> strings) {
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(strings.get(i));
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_tv);
        }
    }
    public void addOne(String s){
        strings.add(s);
        notifyItemInserted(strings.size()-1);
    }
    public void addN(List<String> stringList){
        strings.addAll(stringList);
        notifyItemRangeInserted(strings.size()-1,stringList.size());
    }
    public void lessOne(){
        strings.remove(strings.size()-1);
        notifyItemRemoved(strings.size());
    }
    public void lessN(){
        for (int i = 0; i < 3; i++) {
            strings.remove(strings.size()-1);
        }
        notifyItemRangeRemoved(strings.size(),3);
    }


}
