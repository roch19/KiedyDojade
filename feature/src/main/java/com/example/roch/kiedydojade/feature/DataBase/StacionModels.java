package com.example.roch.kiedydojade.feature.DataBase;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class StacionModels {
    @PrimaryKey public  int ID;
    public  String Data;
    public  String DestinationName;
    public  Float latitude;
    public  Float longtitude;

    public StacionModels(@NonNull int ID, String Data, String DestinationName, Float latitude, Float longtitude){
        this.ID = ID;
        this.Data = Data;
        this.DestinationName = DestinationName;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }
}
