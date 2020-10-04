package com.example.jobbank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter_view_applicant extends FirebaseRecyclerAdapter <model_view_applicants, myadapter_view_applicant.applicantViewHolder> {

    private RecyclerViewClickListener listener;

    public myadapter_view_applicant(@NonNull FirebaseRecyclerOptions<model_view_applicants> options, RecyclerViewClickListener listener) {
        super(options);
        this.listener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull applicantViewHolder holder, int position, @NonNull model_view_applicants model) {
        holder.name.setText(model.getFull_name());
        holder.date.setText(model.getClosingDate());
        holder.status.setText(model.getStatus());
    }

    @NonNull
    @Override
    public applicantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlecard_view_applicant, parent, false);
        return new applicantViewHolder(view);
}

    public class applicantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView name, date,status;
        public applicantViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.applicant_name);
            date = (TextView) itemView.findViewById(R.id.applied_date);
            status = (TextView) itemView.findViewById(R.id.status_123);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());

        }

    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }
}
