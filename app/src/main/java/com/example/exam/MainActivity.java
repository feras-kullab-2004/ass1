package com.example.exam;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements
        Fragment1.OnNameEnteredListener,
        Fragment2.OnFragment2ContinueListener,
        Fragment3.OnAgreementChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            openFragment(new Fragment1(), false);
        }
    }

    @Override
    public void onNameEntered(String name) {
        openFragment(Fragment2.newInstance(name), true);
    }

    @Override
    public void onContinueFromFragment2(String userName) {
        openFragment(Fragment3.newInstance(userName), true);
    }

    @Override
    public void onAgreementChanged(boolean isChecked, Button actionButton) {
        actionButton.setEnabled(isChecked);
        actionButton.setText(isChecked ? "Finish" : "Continue");
    }

    private void openFragment(Fragment fragment, boolean addToBackStack) {
        androidx.fragment.app.FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
