package com.imsadman.fnf_info;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.imsadman.fnf_info.database.FnfEntity;
import com.imsadman.fnf_info.database.FnfRepository;

import java.util.List;

public class FnfViewModel extends AndroidViewModel {

    private static final String TAG = Activity.class.getSimpleName();

    private FnfRepository mFnfRepository;
    private LiveData<List<FnfEntity>> mAllFnf;

    public FnfViewModel(@NonNull Application application) {
        super(application);
        mFnfRepository = new FnfRepository(application);
        mAllFnf = mFnfRepository.getAllFnf();
    }

    public LiveData<List<FnfEntity>> getAllFnf() {
        return mAllFnf;
    }

    public void insert(FnfEntity fnfEntity) {
        mFnfRepository.insert(fnfEntity);
    }


}
