package com.imsadman.fnf_info.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FnfDao {

    @Query("SELECT * from fnf ORDER BY name DESC")
    LiveData<List<FnfEntity>> getAllOrderByName();

    @Insert
    void insert(FnfEntity fnfEntity);

    @Update
    void update(FnfEntity fnfEntity);

    @Delete
    void delete(FnfEntity fnfEntity);

    @Query("DELETE FROM fnf")
    void deleteAll();

}
