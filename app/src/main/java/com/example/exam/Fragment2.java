package com.example.exam;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {

    private static final String ARG_NAME = "arg_name";

    public interface OnFragment2ContinueListener {
        void onContinueFromFragment2(String userName);
    }

    private OnFragment2ContinueListener listener;
    private String userName = "";

    public static Fragment2 newInstance(String name) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragment2ContinueListener) {
            listener = (OnFragment2ContinueListener) context;
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
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        TextView tvWelcome2 = view.findViewById(R.id.tvWelcome2);
        EditText etEmail = view.findViewById(R.id.etEmail);
        EditText etUniversity = view.findViewById(R.id.etUniversity);
        RadioButton rbFemale = view.findViewById(R.id.rbFemale);
        Button btnContinue2 = view.findViewById(R.id.btnContinue2);

        if (!userName.isEmpty()) {
            tvWelcome2.setText("Step 2: Details for " + userName);
        }

        btnContinue2.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String uni = etUniversity.getText().toString().trim();
            String gender = rbFemale.isChecked() ? "Female" : "Male";
            if (listener != null) {
                listener.onContinueFromFragment2(userName);
            }
        });

        return view;
    }
}
