package com.example.esppad.calc2.Main.Tools;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.esppad.calc2.Main.Tools.passwordHelper.PasswordActivity;
import com.example.esppad.calc2.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToolsFragment extends Fragment {
    ImageButton PasswordActivityBtn;


    public ToolsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tools, container, false);
        PasswordActivityBtn = (ImageButton) view.findViewById(R.id.passwordImageBtn);
        PasswordActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PasswordActivity.class);
                getActivity().startActivity(intent);
            }
        });


        return view;
    }

}
