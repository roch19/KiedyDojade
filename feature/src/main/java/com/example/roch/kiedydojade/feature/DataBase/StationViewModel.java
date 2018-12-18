package com.example.roch.kiedydojade.feature.DataBase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.widget.ListView;

import java.util.List;

public class StationViewModel extends AndroidViewModel {
    private StationRepository mRepository;
    private LiveData<List<StacionModels>> mAllStations;

    public StationViewModel (Application application){
        super(application);
        mRepository = new StationRepository(application);
        mAllStations = mRepository.getMyAllStation();
    }

    public LiveData<List<StacionModels>> getAllStations(){ return mAllStations; }

    public void insert(StacionModels stacionModels){mRepository.insert(stacionModels);}


    public void delete(StacionModels stacionModels) {mRepository.delete(stacionModels);}
    public void update(StacionModels stacionModels) {mRepository.update(stacionModels);}

    public void deleteDocument(ListView lv, int position) {
        StationListAdapter adapter = (StationListAdapter) lv.getAdapter();
        for( int j = 0; j < adapter.getHolders().size(); j++ ) {
            if(adapter.getHolders().get(j).position == position) {
                this.delete(adapter.getHolders().get(j).stacionModels);
            }
        }
    }
}
