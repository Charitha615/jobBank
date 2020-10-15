package com.example.jobbank;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_User_Dashbord#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_User_Dashbord extends Fragment {
    Intent Uprofile;
    Intent bkmrk;
    Intent inter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_User_Dashbord() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_User_Dashbord.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_User_Dashbord newInstance(String param1, String param2) {
        Fragment_User_Dashbord fragment = new Fragment_User_Dashbord();
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
        //return inflater.inflate(R.layout.fragment__user__dashbord, container, false);
        //bkmrk = (Button) getView().findViewById(R.id.bookmark_btn);

        View rootView= inflater.inflate(R.layout.fragment__user__dashbord, container, false);

        final Button profile = (Button) rootView.findViewById(R.id.user_profile);
        Uprofile = new Intent(getActivity(), user_account.class);

        final Button bookmark = (Button) rootView.findViewById(R.id.bookmark_btn);
        bkmrk = new Intent(getActivity(), user_bookmarks.class);

        final Button interest = (Button) rootView.findViewById(R.id.Interest);
        inter = new Intent(getActivity(), User_Interested_list.class);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Uprofile);
            }
        });

        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(bkmrk);
            }
        });
        
        interest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(inter);

            }
        });

        return rootView;
    }
}