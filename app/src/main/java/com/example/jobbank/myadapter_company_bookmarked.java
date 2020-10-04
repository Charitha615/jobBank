package com.example.jobbank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter_company_bookmarked extends FirebaseRecyclerAdapter <model_company_bookmarked, myadapter_company_bookmarked.appViewHolder> {
    public myadapter_company_bookmarked(@NonNull FirebaseRecyclerOptions<model_company_bookmarked> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull appViewHolder holder, int position, @NonNull model_company_bookmarked model) {
        holder.username.setText(model.getFull_name());
        holder.position.setText(model.getPosition());
    }

    @NonNull
    @Override
    public appViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlecard_company_bookmarked, parent, false);
        return new appViewHolder(view);
    }

    class appViewHolder extends RecyclerView.ViewHolder
    {
        TextView username, position;
        public appViewHolder(@NonNull View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.nameBookmarked);
            position = (TextView) itemView.findViewById(R.id.positionBookmarked);
        }
    }
}
