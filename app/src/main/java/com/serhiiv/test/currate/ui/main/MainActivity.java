package com.serhiiv.test.currate.ui.main;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.serhiiv.test.currate.R;
import com.serhiiv.test.currate.core.base.BaseActivity;
import com.serhiiv.test.currate.ui.main.viewmodel.MainViewModel;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    MainFragment injectedFragment;
    @Inject
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, injectedFragment)
                    .commit();
        }
    }

}
