package com.imsadman.fnf_info;
import android.app.Activity;
import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class FnfRepository {

    private static final String TAG = Activity.class.getSimpleName();

    private FnfDao mFnfDao;
    private LiveData<List<FnfEntity>> mAllFnf;

    public FnfRepository(Application application) {
        FnfDatabase fnfDatabase = FnfDatabase.getInstance(application);
        mFnfDao = fnfDatabase.fnfDao();
        mAllFnf = mFnfDao.getAllOrderByFname();
    }

    /*TODO: Check only getData
    * Then post/get data from API
    * */

    public LiveData<List<FnfEntity>> getAllFnf() {
        return mAllFnf;
    }

    public void insert(FnfEntity fnfEntity) {
        addFriend();
    }

    private void addFriend() {
    }
}
