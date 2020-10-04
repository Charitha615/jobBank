package com.example.jobbank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter_view_list extends FirebaseRecyclerAdapter <model_view_list, myadapter_view_list.applicantViewHolder> {

    private RecyclerViewClickListener listener;
    public String STATUS;

    public myadapter_view_list(@NonNull FirebaseRecyclerOptions<model_view_list> options, RecyclerViewClickListener listener) {
        super(options);
        this.listener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull applicantViewHolder holder, int position, @NonNull model_view_list model) {

        //STATUS = model.getStatus();
        //if(STATUS.equals("Pending")){
            holder.name.setText(model.getFull_name());
            holder.marks.setText(String.valueOf(model.getMarks()));
            holder.status.setText(model.getStatus());
        //}
    }

    @NonNull
    @Override
    public applicantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlecard_view_list, parent, false);
        return new applicantViewHolder(view);
    }

    public class applicantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView name, marks,status;
        public applicantViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.listView_name);
            marks = (TextView) itemView.findViewById(R.id.listView_marks);
            status = (TextView) itemView.findViewById(R.id.listView_status);

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
