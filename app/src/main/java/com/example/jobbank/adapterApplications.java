package com.example.jobbank;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;

import java.util.List;

public class adapterApplications extends FirebaseRecyclerAdapter <modelApplications, adapterApplications.appViewHolder> {

    private RecyclerViewClickListener listener;

    public adapterApplications(@NonNull FirebaseRecyclerOptions<modelApplications> options, RecyclerViewClickListener listener) {
        super(options);
        this.listener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull appViewHolder holder, int position, @NonNull modelApplications model) {
        holder.position.setText(model.getPosition());
        holder.company.setText(model.getCompany());
    }

    public class appViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView position, company;
        public appViewHolder(@NonNull View itemView) {
            super(itemView);
            position = (TextView) itemView.findViewById(R.id.positionCard);
            company = (TextView) itemView.findViewById(R.id.companyCard);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener { void onClick(View v, int position);}

    @NonNull
    @Override
    public appViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_app_card, parent, false);
        return new appViewHolder(view);
    }

    @Override
    public int getItemCount() {

        return super.getItemCount();
    }


}
