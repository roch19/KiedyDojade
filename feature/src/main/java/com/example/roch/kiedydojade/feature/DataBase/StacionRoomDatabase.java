package com.example.roch.kiedydojade.feature.DataBase;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

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
                            StacionRoomDatabase.class, "stacion_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final StationModelsDAO mDao;

        PopulateDbAsync(StacionRoomDatabase db) {
            mDao = db.stacionDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            StacionModels word = new StacionModels("Destination 1");
            mDao.insert(word);
            word = new StacionModels("Destination 2");
            mDao.insert(word);
            return null;
        }
    }
}
