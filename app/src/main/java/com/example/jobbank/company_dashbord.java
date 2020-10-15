package com.example.jobbank;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link company_dashbord#newInstance} factory method to
 * create an instance of this fragment.
 */
public class company_dashbord extends Fragment {
    Intent save_intent,add_vacancy_intent,vacancy_intent,profile_intent;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public company_dashbord() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment company_dashbord.
     */
    // TODO: Rename and change types and number of parameters
    public static company_dashbord newInstance(String param1, String param2) {
        company_dashbord fragment = new company_dashbord();
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
        View rootView= inflater.inflate(R.layout.fragment_company_dashbord, container, false);

        //View rootView = inflater.inflate(R.layout.about, container, false);
        profile_intent = new Intent(getActivity(), Company_Profile.class);
        save_intent = new Intent(getActivity(), company_bookmarked_list.class);
        add_vacancy_intent = new Intent(getActivity(), publish_a_vacancy.class);
        vacancy_intent = new Intent(getActivity(), view_published_vacancies.class);

        //////////////////////////////////////////////////////////////////////////
        final Button profile = (Button) rootView.findViewById(R.id.user_profile9);
        final Button save = (Button) rootView.findViewById(R.id.bookmark_btn9);
        final Button add = (Button) rootView.findViewById(R.id.addVacancy_btn);
        final Button vacancy = (Button) rootView.findViewById(R.id.my_applica9);

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(save_intent);
            }
        });



        //////////////////////////////////////////////////////////////////////////
        vacancy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(vacancy_intent);

            }
        });

        //////////////////////////////////////////////////////////////////////////
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(profile_intent);

            }
        });


        //////////////////////////////////////////////////////////////////////////


        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(add_vacancy_intent);
            }
        });
        //////////////////////////////////////////////////////////////////////////

        return rootView;
    }
}