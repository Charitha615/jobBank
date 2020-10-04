package com.example.jobbank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter_company_interested extends FirebaseRecyclerAdapter <model_company_interested, myadapter_company_interested.appViewHolder> {
    public myadapter_company_interested(@NonNull FirebaseRecyclerOptions<model_company_interested> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull appViewHolder holder, int position, @NonNull model_company_interested model) {
        holder.username.setText(model.getUsername());
        holder.email.setText(model.getEmail());
    }

    @NonNull
    @Override
    public appViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlecard_company_interested, parent, false);
        return new appViewHolder(view);
    }

    class appViewHolder extends RecyclerView.ViewHolder
    {
        TextView username, email;
        public appViewHolder(@NonNull View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.int_name);
            email = (TextView) itemView.findViewById(R.id.int_post);
        }
    }
}
