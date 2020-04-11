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
import com.imsadman.fnf_info.network.HelperService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = Activity.class.getSimpleName();

    private String authToken;
    private List<FnfEntity> mFnfEntityList;
    private FnfViewModel mFnfViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFnfViewModel = ViewModelProviders.of(this).get(FnfViewModel.class);
        subscribeObserver();
        getFnf();
    }

    private void subscribeObserver() {
        mFnfViewModel.getAllFnf().observe(this, new Observer<List<FnfEntity>>() {
            @Override
            public void onChanged(@Nullable List<FnfEntity> fnfEntityList) {
                mFnfEntityList = fnfEntityList;
            }
        });
    }

    private void getFnf() {
        authToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNTg2NTc0OTg2LCJqdGkiOiIyNGU1ZDJiMDMxYTM0MTY2OTc5OGFkOGFjYjFkYmFkOCIsInVzZXJfaWQiOjJ9.Qe9DyRGgQsfPIHCU-mHNUOR81_hpZPuUdgSCs7V48yc";

        Call<List<FnfModel>> fnfCall = HelperService.getFnfAPI().getFriends("Bearer " + authToken);

        fnfCall.enqueue(new Callback<List<FnfModel>>() {
            @Override
            public void onResponse(Call<List<FnfModel>> call, Response<List<FnfModel>> response) {

                if (response.isSuccessful() && response != null) {
                    List<FnfModel> fnfModelList = response.body();
                    initFnfView(fnfModelList);

                    for (int i = 0; i < fnfModelList.size(); i++) {
                        FnfModel position = fnfModelList.get(i);
                        String name = position.getName();
                        String dob = position.getDob();
                        String phoneNumber = position.getPhone_number();
                        String email = position.getEmail();
                        String facebook = position.getFacebook();
                        String instagram = position.getInstagram();
                        String address = position.getAddress();
                        String postalCode = position.getPostalCode();
                        String city = position.getCity();

                        FnfEntity fnfEntity = new FnfEntity(name, dob, email, phoneNumber, facebook, instagram, address, postalCode, city);
                        mFnfViewModel.insert(fnfEntity);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<FnfModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

    private void initFnfView(List<FnfModel> fnfModelList) {
        LinearLayoutManager FnfLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView FnfRecyclerView = findViewById(R.id.recycler_fnf);
        FnfRecyclerView.setLayoutManager(FnfLayoutManager);
        FnfAdapter fnfAdapter = new FnfAdapter(this, fnfModelList);
        FnfRecyclerView.setAdapter(fnfAdapter);
    }
}
