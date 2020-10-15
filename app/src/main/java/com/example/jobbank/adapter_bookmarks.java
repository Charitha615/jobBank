package com.example.jobbank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class adapter_bookmarks extends FirebaseRecyclerAdapter<modelBookmarks, adapter_bookmarks.appViewHolder> {

    public adapter_bookmarks(@NonNull FirebaseRecyclerOptions<modelBookmarks> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull appViewHolder holder, int position, @NonNull modelBookmarks model) {
        holder.cName.setText(model.getCompanyName());
    }

    @NonNull
    @Override
    public appViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlecard_bookmarks, parent, false);
        return new appViewHolder(view);
    }

    class appViewHolder extends RecyclerView.ViewHolder
    {
        TextView cName;
        public appViewHolder(@NonNull View itemView) {
            super(itemView);
            cName = (TextView) itemView.findViewById(R.id.companyNameCard);
        }
    }
}

