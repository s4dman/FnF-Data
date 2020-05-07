package com.imsadman.fnf_info;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imsadman.fnf_info.database.FnfEntity;
import com.imsadman.fnf_info.network.HelperService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = Activity.class.getSimpleName();

    private String authToken;
    private FnfViewModel mFnfViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFnfViewModel = new ViewModelProvider(this).get(FnfViewModel.class);

        subscribeObserver();
        getAccess();
    }

    private void getAccess() {
        Call<ResponseBody> authCall = HelperService.getFnfAPI().auth("guest", "hidjango");
        authCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        try {
                            JSONObject authObject = new JSONObject(response.body().string());
                            authToken = authObject.getString("access");
                            getFnf(authToken);
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "getAccess() onFailure: " + t.toString());
            }
        });
    }

    private void subscribeObserver() {
        mFnfViewModel.getAllFnf().observe(this, fnfEntityList ->
                initFnfView(fnfEntityList)
        );
    }

    private void getFnf(String authToken) {

        Call<List<FnfEntity>> fnfCall = HelperService.getFnfAPI().getFriends("Bearer " + authToken);
        fnfCall.enqueue(new Callback<List<FnfEntity>>() {
            @Override
            public void onResponse(Call<List<FnfEntity>> call, Response<List<FnfEntity>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<FnfEntity> fnfEntityList = response.body();
                        for (FnfEntity fnfEntity : fnfEntityList) {
                            mFnfViewModel.insert(fnfEntity);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<FnfEntity>> call, Throwable t) {

            }
        });
    }

    private void initFnfView(List<FnfEntity> fnfModelList) {
        LinearLayoutManager FnfLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView FnfRecyclerView = findViewById(R.id.recycler_fnf);
        FnfRecyclerView.setLayoutManager(FnfLayoutManager);
        FnfAdapter fnfAdapter = new FnfAdapter(this, fnfModelList);
        FnfRecyclerView.setAdapter(fnfAdapter);
    }
}
