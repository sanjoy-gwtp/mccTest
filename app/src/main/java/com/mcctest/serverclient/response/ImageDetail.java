package com.mcctest.serverclient.response;

import java.io.Serializable;

/**
 * Created by sanjoy on 8/29/18.
 */

public class ImageDetail implements Serializable {
    String Id;
    String Title;
    String Details;
    String IMG;
    String IMGW;
    String AUDIO;
    String VIDEO;
    String FILE;
    String YT;
    String Entry;
    String LastUpdated;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getIMG() {
        return IMG;
    }

    public void setIMG(String IMG) {
        this.IMG = IMG;
    }

    public String getIMGW() {
        return IMGW;
    }

    public void setIMGW(String IMGW) {
        this.IMGW = IMGW;
    }

    public String getAUDIO() {
        return AUDIO;
    }

    public void setAUDIO(String AUDIO) {
        this.AUDIO = AUDIO;
    }

    public String getVIDEO() {
        return VIDEO;
    }

    public void setVIDEO(String VIDEO) {
        this.VIDEO = VIDEO;
    }

    public String getFILE() {
        return FILE;
    }

    public void setFILE(String FILE) {
        this.FILE = FILE;
    }

    public String getYT() {
        return YT;
    }

    public void setYT(String YT) {
        this.YT = YT;
    }

    public String getEntry() {
        return Entry;
    }

    public void setEntry(String entry) {
        Entry = entry;
    }

    public String getLastUpdated() {
        return LastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        LastUpdated = lastUpdated;
    }
}
