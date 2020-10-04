package com.example.jobbank;

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
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class myApplicationFragment extends Fragment {

    RecyclerView recViewApps;
    TextView noOfApps;
    adapterApplications adapter;
    adapterApplications.RecyclerViewClickListener listener;
    FirebaseRecyclerOptions<modelApplications> options;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public myApplicationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment myApplicationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static myApplicationFragment newInstance(String param1, String param2) {
        myApplicationFragment fragment = new myApplicationFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_application, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setOnClickListener();

        recViewApps = (RecyclerView)getView().findViewById(R.id.appRecView);
        noOfApps = (TextView) getView().findViewById(R.id.numberOfApps);

        LinearLayoutManager linearLayoutManager = (new LinearLayoutManager(getActivity().getApplicationContext()));
        recViewApps.setLayoutManager(linearLayoutManager);

        options =
                new FirebaseRecyclerOptions.Builder<modelApplications>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("User_Req_Job"), modelApplications.class)
                        .build();

        adapter = new adapterApplications(options,listener);
        recViewApps.setAdapter(adapter);
        noOfApps.setText(String.valueOf(recViewApps.getAdapter().getItemCount()));
    }

    private void setOnClickListener() {
        listener = new adapterApplications.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {

                Intent intent = new Intent(getActivity().getApplicationContext(), view_applications.class);

                intent.putExtra("vName", options.getSnapshots().get(position).getPosition());
                intent.putExtra("cName", options.getSnapshots().get(position).getCompany());
                intent.putExtra("cDate", options.getSnapshots().get(position).getClosingDate());
                intent.putExtra("vStatus", options.getSnapshots().get(position).getStatus());

                intent.putExtra("fName", options.getSnapshots().get(position).getFull_name());
                intent.putExtra("nic", options.getSnapshots().get(position).getNic_number());
                intent.putExtra("emailVal", options.getSnapshots().get(position).getEmail());
                intent.putExtra("phoneVal", options.getSnapshots().get(position).getPhone());

                intent.putExtra("exVal", options.getSnapshots().get(position).getExperience());
                intent.putExtra("ageVal", options.getSnapshots().get(position).getAge());
                intent.putExtra("desVal", options.getSnapshots().get(position).getDescription());
                intent.putExtra("quaVal", options.getSnapshots().get(position).getQualifications());

                startActivity(intent);
            }
        };


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
}