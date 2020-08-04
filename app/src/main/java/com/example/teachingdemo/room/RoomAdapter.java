package com.example.teachingdemo.room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teachingdemo.R;

import java.util.List;
import java.util.Locale;

/**
 * @Author sjc
 * @Date 2020/7/31
 * Description：
 */
public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private List<UserDatabase> infos;

    public void setInfos(List<UserDatabase> infos1) {
        this.infos = infos1;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_item, parent, false);
        return new RoomViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        TextView infoTextView = holder.infoTextView;
        UserDatabase info = infos.get(position);
        infoTextView.setText(String.format(Locale.getDefault(), "%d，%s，%s", info.getUser_id(), info.getUser_name(), info.getUser_sex()));
    }

    @Override
    public int getItemCount() {
        return infos.size();
    }

    static class RoomViewHolder extends RecyclerView.ViewHolder {

        private TextView infoTextView;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            infoTextView = itemView.findViewById(R.id.info_room);
        }
    }
}
