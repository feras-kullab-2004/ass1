package com.example.exam;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment3 extends Fragment {

    private static final String ARG_NAME = "arg_name";

    public interface OnAgreementChangeListener {
        void onAgreementChanged(boolean isChecked, Button actionButton);
    }

    private OnAgreementChangeListener agreementListener;
    private String userName = "";

    public static Fragment3 newInstance(String name) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnAgreementChangeListener) {
            agreementListener = (OnAgreementChangeListener) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userName = getArguments().getString(ARG_NAME, "");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);
        TextView tvUserName3 = view.findViewById(R.id.tvUserName3);
        CheckBox cbAgree = view.findViewById(R.id.cbAgree);
        Button btnAction3 = view.findViewById(R.id.btnAction3);

        tvUserName3.setText("User: " + userName);
        btnAction3.setEnabled(false);
        btnAction3.setText("Continue");

        cbAgree.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (agreementListener != null) {
                agreementListener.onAgreementChanged(isChecked, btnAction3);
            }
        });

        btnAction3.setOnClickListener(v ->
            Toast.makeText(getContext(), "Onboarding finished for " + userName, Toast.LENGTH_SHORT).show()
        );

        return view;
    }
}
