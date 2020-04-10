package com.imsadman.fnf_info;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.imsadman.fnf_info.database.FnfEntity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = Activity.class.getSimpleName();

    private FnfViewModel mFnfViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFnfViewModel = ViewModelProviders.of(this).get(FnfViewModel.class);
        subscribeObserver();
    }

    private void subscribeObserver() {
        mFnfViewModel.getAllFnf().observe(this, new Observer<List<FnfEntity>>() {
            @Override
            public void onChanged(@Nullable List<FnfEntity> fnfEntityList) {
                initFnfView(fnfEntityList);
                for (FnfEntity fnfEntity : fnfEntityList) {
                    Log.d(TAG, "onChanged: " + fnfEntity.getName());
                }
            }
        });
    }

    private void initFnfView(List<FnfEntity> fnfEntityList) {
        LinearLayoutManager FnfLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView FnfRecyclerView = findViewById(R.id.recycler_fnf);
        FnfRecyclerView.setLayoutManager(FnfLayoutManager);
        FnfAdapter fnfAdapter = new FnfAdapter(this, fnfEntityList);
        FnfRecyclerView.setAdapter(fnfAdapter);
    }
}
