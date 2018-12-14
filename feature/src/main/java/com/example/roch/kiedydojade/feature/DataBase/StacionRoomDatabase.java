package com.example.roch.kiedydojade.feature.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {StacionModels.class}, version = 1)
public abstract class StacionRoomDatabase extends RoomDatabase{

    public abstract StationModelsDAO stacionDao();

    private static volatile StacionRoomDatabase INSTANCE;

    static StacionRoomDatabase getDatabase(final Context context)
    {
        if(INSTANCE == null){
            synchronized (StacionRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StacionRoomDatabase.class, "")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
