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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
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
        getAccess();
    }

    private void getAccess() {
        Call<ResponseBody> authCall = HelperService.getFnfAPI().auth("s4dman", "baalamar");
        authCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response != null) {
                    try {
                        JSONObject authObject = new JSONObject(response.body().string());
                        authToken = authObject.getString("access");
                        getFnf(authToken);
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure Auth: " + t.toString());
            }
        });
    }

    private void subscribeObserver() {
        mFnfViewModel.getAllFnf().observe(this, new Observer<List<FnfEntity>>() {
            @Override
            public void onChanged(@Nullable List<FnfEntity> fnfEntityList) {
                mFnfEntityList = fnfEntityList;
            }
        });
    }

    private void getFnf(String authToken) {
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
