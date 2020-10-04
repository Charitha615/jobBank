package com.example.jobbank;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class my_adapter_home extends FirebaseRecyclerAdapter<Model_Home,my_adapter_home.myViewHolderHome> {
    private RecyclerViewClickListener listener;

    public my_adapter_home(@NonNull FirebaseRecyclerOptions<Model_Home> options, RecyclerViewClickListener listener) {
        super(options);
        this.listener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolderHome holder, int position, @NonNull Model_Home model) {
        holder.job_Title.setText(model.getJobTitle());
        holder.com_name_.setText(model.getCompanyName());
        Glide.with(holder.logo.getContext()).load(model.getProfileImage()).into(holder.logo);

    }

    @NonNull
    @Override
    public myViewHolderHome onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singalcard_home, parent, false);
        return new myViewHolderHome(view);
    }

    class myViewHolderHome extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        ImageView logo;
        TextView com_name_,job_Title;

        public myViewHolderHome(@NonNull View itemView) {
            super(itemView);

            logo =(ImageView) itemView.findViewById(R.id.com_logo_in);
            com_name_ = (TextView) itemView . findViewById(R.id.com_name);
            job_Title = (TextView) itemView . findViewById(R.id.job_Title_);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
         //   startActivity(new Intent(getActivity().getApplicationContext(),user_add_cv.class));

        }
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }

}
