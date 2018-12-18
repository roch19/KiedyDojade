package com.example.roch.kiedydojade.feature.DataBase;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class StationRepository {

    private StationModelsDAO smDao;
    private LiveData<List<StacionModels>> myAllStation;

    StationRepository(Application application){
        StacionRoomDatabase db = StacionRoomDatabase.getDatabase(application);
        smDao = db.stacionDao();
        myAllStation = smDao.getAll();
    }

    LiveData<List<StacionModels>> getMyAllStation(){
        return myAllStation;
    }

    public void insert (StacionModels sm){
         new insertAsyncTask(smDao).execute(sm);
    }

    public void delete (StacionModels sm) {new deleteAsyncTask(smDao).execute(sm);}

    public void update (StacionModels sm) {new updateAsyncTask(smDao).execute(sm);}








    private static class insertAsyncTask extends AsyncTask<StacionModels, Void, Void>{

        private StationModelsDAO mAsyncTaskDao;

        insertAsyncTask(StationModelsDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final StacionModels... params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<StacionModels, Void, Void>{

        private StationModelsDAO mAsyncTaskDao;

        updateAsyncTask(StationModelsDAO dao) {mAsyncTaskDao = dao;}

        @Override
        protected Void doInBackground(final StacionModels... params){
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private class deleteAsyncTask extends AsyncTask<StacionModels, Void,Void>{

        private StationModelsDAO mAsyncTaskDao;

        deleteAsyncTask(StationModelsDAO dao) {mAsyncTaskDao = dao;}
        @Override
        protected Void doInBackground(final StacionModels... params){
            mAsyncTaskDao.delete(params[0]);
            return null;
        }

    }

}
