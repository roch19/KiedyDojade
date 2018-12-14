package com.example.roch.kiedydojade.feature.DataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface
StationModelsDAO {
    @Insert
    void insert (StacionModels sm);

    @Query("DELETE FROM StacionModels")
    void deleteAll();

    @Query("SELECT * from StacionModels ORDER BY ID ASC")
    LiveData<List<StacionModels>> getAllStacionModels();
}
