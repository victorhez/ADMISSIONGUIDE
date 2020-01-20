package com.example.guide;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseViewHolder extends RecyclerView.ViewHolder {
public TextView title, descrip;

    public FirebaseViewHolder(@NonNull View itemView) {
        super(itemView);
        title= itemView.findViewById(R.id.list_title);
        descrip= itemView.findViewById(R.id.list_desc);
    }
}
