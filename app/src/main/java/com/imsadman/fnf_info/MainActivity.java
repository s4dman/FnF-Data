package com.imsadman.fnf_info;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imsadman.fnf_info.database.FnfEntity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = Activity.class.getSimpleName();

    private FnfViewModel mFnfViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFnfViewModel = new ViewModelProvider(this).get(FnfViewModel.class);

        authRequest();
        subscribeObserver();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem addItem = menu.findItem(R.id.action_add);

        addItem.setOnMenuItemClickListener(menuItem -> {
            /*TODO -> Add new fnf*/
            Toast.makeText(MainActivity.this, "Add new", Toast.LENGTH_SHORT).show();
            return true;
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void authRequest() {
        mFnfViewModel.authRequest();
    }

    private void subscribeObserver() {
        mFnfViewModel.getFnfList().observe(this, fnfEntityList -> initFnfView(fnfEntityList));
    }

    private void initFnfView(List<FnfEntity> fnfEntityList) {
        LinearLayoutManager FnfLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView FnfRecyclerView = findViewById(R.id.recycler_fnf);
        FnfRecyclerView.setLayoutManager(FnfLayoutManager);
        FnfAdapter fnfAdapter = new FnfAdapter(this, fnfEntityList);
        FnfRecyclerView.setAdapter(fnfAdapter);
    }
}
