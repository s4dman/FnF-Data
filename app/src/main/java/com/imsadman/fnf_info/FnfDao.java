package com.imsadman.fnf_info;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface FnfDao {

    @Insert
    void insert(FnfEntity fnfEntity);

    @Update
    void update(FnfEntity fnfEntity);

    @Delete
    void delete(FnfEntity fnfEntity);

    @Query("DELETE FROM fnf")
    void deleteAll();

    @Query("SELECT * from fnf ORDER BY first_name DESC")
    LiveData<List<FnfEntity>> getAllOrderByFname();
}
