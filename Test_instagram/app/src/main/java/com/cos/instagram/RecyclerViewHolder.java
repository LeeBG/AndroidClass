package com.cos.instagram;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public TextView mUsername;
    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        mUsername = (TextView) itemView.findViewById(R.id.tv_username);
    }
}
