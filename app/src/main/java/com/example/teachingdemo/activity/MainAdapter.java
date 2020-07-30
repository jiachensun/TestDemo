package com.example.teachingdemo.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teachingdemo.R;

import java.util.ArrayList;

/**
 * @Author sjc
 * @Date 2020/6/16
 * Description：
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    public interface IMainOnItemClickListener {

        void onClick(String name);
    }

    private ArrayList<String> buttons;
    private IMainOnItemClickListener onItemClickListener;

    public MainAdapter(IMainOnItemClickListener listener) {

        this.onItemClickListener = listener;
    }

    public void setData(ArrayList<String> buttons) {
        this.buttons = buttons;
    }

    @NonNull
    @Override
    public MainAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mainitem, parent, false);
        return new MainAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MyViewHolder holder, int position) {

        holder.nameTextView.setText(buttons.get(position));
    }

    @Override
    public int getItemCount() {

        return null != buttons ? buttons.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.nameTextView = itemView.findViewById(R.id.tv_name);
            nameTextView.setOnClickListener(view -> {
                onItemClickListener.onClick(buttons.get(getAdapterPosition()));
            });
        }
    }
}
