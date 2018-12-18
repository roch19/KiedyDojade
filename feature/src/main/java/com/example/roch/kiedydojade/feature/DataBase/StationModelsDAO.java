package com.example.roch.kiedydojade.feature.DataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface
StationModelsDAO {

    @Update
    void update(StacionModels stations);

    @Delete
    void delete(StacionModels stations);

    @Insert
    void insert (StacionModels stations);

    @Query("SELECT * from stations")
    LiveData<List<StacionModels>> getAll();

    @Query("SELECT * FROM stations ")
    LiveData<List<StacionModels>> getAllStations();

    @Query("DELETE FROM stations")
    void deleteAll();


}
