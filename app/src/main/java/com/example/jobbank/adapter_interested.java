package com.example.jobbank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class adapter_interested extends FirebaseRecyclerAdapter<Interested_Model, com.example.jobbank.adapter_interested.appViewHolder> {

        public adapter_interested(@NonNull FirebaseRecyclerOptions<Interested_Model> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull com.example.jobbank.adapter_interested.appViewHolder holder, int position, @NonNull Interested_Model model) {
            holder.cName.setText(model.getCompanyName());
            holder.cName.setText(model.getJobTitle());
        }

        @NonNull
        @Override
        public com.example.jobbank.adapter_interested.appViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singalcard_interested, parent, false);
            return new com.example.jobbank.adapter_interested.appViewHolder(view);
        }


    class appViewHolder extends RecyclerView.ViewHolder
        {
            TextView cName,job;
            public appViewHolder(@NonNull View itemView) {
                super(itemView);
                cName = (TextView) itemView.findViewById(R.id.companyNameCard_intersted);
                job = (TextView) itemView.findViewById(R.id.job_titleCard_intersted);
            }
        }
    }


