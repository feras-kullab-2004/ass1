package com.example.exam;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {

    public interface OnNameEnteredListener {
        void onNameEntered(String name);
    }

    private OnNameEnteredListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnNameEnteredListener) {
            listener = (OnNameEnteredListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        EditText etName = view.findViewById(R.id.etName);
        Button btnNext1 = view.findViewById(R.id.btnNext1);

        btnNext1.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            if (name.isEmpty()) {
                name = "Rana";
            }
            if (listener != null) {
                listener.onNameEntered(name);
            }
        });
        return view;
    }
}
