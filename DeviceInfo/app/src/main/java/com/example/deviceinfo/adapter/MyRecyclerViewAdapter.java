package com.example.deviceinfo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deviceinfo.R;
import com.example.deviceinfo.entity.Bean;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private List<Bean> data;
    private Context context;

    public MyRecyclerViewAdapter(List<Bean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.recyclerview_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_key.setText(data.get(position).getKey());
        holder.tv_value.setText(data.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public interface OnRecyclerItemClickListener {
        void onRecyclerItemClick(int position);
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    // 点击监听
    private OnRecyclerItemClickListener onRecyclerItemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_key, tv_value;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_key = itemView.findViewById(R.id.tv_key);
            tv_value = itemView.findViewById(R.id.tv_value);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onRecyclerItemClickListener != null)
                        onRecyclerItemClickListener.onRecyclerItemClick(getAdapterPosition());
                }
            });
        }
    }


}
