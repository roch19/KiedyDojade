package com.example.roch.kiedydojade.feature.DataBase;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "stations")
public class StacionModels {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id")
    public  int ID;
    public  String Data;
    @ColumnInfo(name = "destinaction")
    public  String DestinationName;
    public  Float latitude;
    public  Float longtitude;

//    public StacionModels(@NonNull int ID, String Data, String DestinationName, Float latitude, Float longtitude){
//        this.ID = ID;
//        this.Data = Data;
//        this.DestinationName = DestinationName;
//        this.latitude = latitude;
//        this.longtitude = longtitude;
//    }
    public StacionModels(String DestinationName){this.DestinationName = DestinationName;}

    @NonNull
    public String getDestinationName(){return DestinationName;}

    public int getID(){return ID;}

    public String getData(){return  Data;}

    @NonNull
    public  Float getLatitude(){return latitude ;}

    @NonNull
    public  Float getLongtitude(){return longtitude;}

    public  void setDestinationName(String destinationName) {this.DestinationName =DestinationName;}
    public  void setData(String Data) {this.Data =Data;}
    public  void setLatitude(Float latitude) {this.latitude =latitude;}
    public  void setLongtitude(Float longtitude) {this.longtitude =longtitude;}
}
