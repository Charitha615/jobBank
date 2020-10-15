package com.example.jobbank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import io.paperdb.Paper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Home extends Fragment {
    RecyclerView recyclerView;
    Context context;
    String firebaseChild ="PublishVacancy";
    my_adapter_home adapter;

    String header = "";

    my_adapter_home.RecyclerViewClickListener listener;
    FirebaseRecyclerOptions<Model_Home> options;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Home newInstance(String param1, String param2) {
        Fragment_Home fragment = new Fragment_Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        setOnClickListner();
        super.onViewCreated(view, savedInstanceState);
        recyclerView =(RecyclerView)getView().findViewById(R.id.home_recycler1);

        LinearLayoutManager linearLayoutManager = (new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setLayoutManager(linearLayoutManager);

        options =
                new FirebaseRecyclerOptions.Builder<Model_Home>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(firebaseChild), Model_Home.class)
                        .build();

        adapter = new my_adapter_home(options,listener);

        recyclerView.setAdapter(adapter);
    }



    @Override
    public void onStart() {
        super.onStart();
        Log.i("start","fragment started");
        adapter.startListening();
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.i("stop","fragment stopped");
        adapter.stopListening();
    }

    private void setOnClickListner() {
        listener = new my_adapter_home.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {


               Intent i = new Intent(getActivity().getApplicationContext(),user_add_cv.class);

               i.putExtra("JOB_TITLE",options.getSnapshots().get(position).getJobTitle());
               i.putExtra("COMPANY_NAME",options.getSnapshots().get(position).getCompanyName());
               i.putExtra("QUALIFICATION1",options.getSnapshots().get(position).getQualification());
               i.putExtra("AGE_LIMIT",options.getSnapshots().get(position).getAgeLimit());
               i.putExtra("DESCRIPTION1",options.getSnapshots().get(position).getDescription());
               i.putExtra("CLOSING_DATE",options.getSnapshots().get(position).getClosingDate());
               i.putExtra("JOB_TYPE",options.getSnapshots().get(position).getJobType());


               startActivity(i);



            }
        };



    }
}