//package com.example.jobbank;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//
//public class myadapter extends FirebaseRecyclerAdapter <pubVacancy,myadapter.myviewholder> {
//
//    private RecyclerViewClickListener listener;
//
//    public myadapter(@NonNull FirebaseRecyclerOptions<pubVacancy> options, RecyclerViewClickListener listener) {
//        super(options);
//        this.listener = listener;
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull pubVacancy model) {
//
//        holder.jobtitle.setText(model.getJobTitle());
////        holder.publishdate.setText(model.getPublishedDate());
////        holder.closingdate.setText(model.getClosingDate());
////
//    }
//
//    @NonNull
//    @Override
//    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_card_publish_vacancy, parent, false);
//        return new myviewholder(view);
//    }
//
//
//    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
//
//
//        TextView jobtitle,publishdate,closingdate;
//
//        public myviewholder(@NonNull View itemView) {
//            super(itemView);
//
//            jobtitle = (TextView) itemView.findViewById(R.id.jobtitleTxt);
////            publishdate = (TextView) itemView.findViewById(R.id.publishedDateTxt);
////            closingdate = (TextView) itemView.findViewById(R.id.closingDateTxt);
//
//            itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View view) {
//            listener.onClick(view, getAdapterPosition());
//
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return super.getItemCount();
//    }
//
//    public interface RecyclerViewClickListener {
//        void onClick(View v, int position);
//    }
//}
package com.example.jobbank;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.jobbank.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
public class myadapter extends FirebaseRecyclerAdapter<pubVacancy,myadapter.myviewholder> {
    private RecyclerViewClickListener listener;

    public myadapter(@NonNull FirebaseRecyclerOptions<pubVacancy> options,RecyclerViewClickListener listener) {
        super(options);
        this.listener = listener;
    }
    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull pubVacancy ModelP) {

        holder.jobTitle.setText(ModelP.getJobTitle());
        holder.closingD.setText(ModelP.getClosingDate());
        holder.publishedD.setText(ModelP.getPublishedDate());
    }
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_card_publish_vacancy,parent,false);
        return new myviewholder(view);
    }
    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView jobTitle, closingD, publishedD;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            jobTitle = (TextView) itemView.findViewById(R.id.jobtitleTxt);
            closingD = (TextView)itemView.findViewById(R.id.closingDateTxt);
            publishedD = (TextView)itemView.findViewById(R.id.publishedDateTxt);

            itemView.setOnClickListener(this);
        }
        @Override

        public void onClick(View view){
            listener.onClick(view, getAdapterPosition());
        }

    }

    public interface RecyclerViewClickListener{
        void onClick(View v,int position);
    }
}
