package com.example.test1;

public class GlobalSetting {
    private String aid;

    private static GlobalSetting instance;

    public static GlobalSetting getInstance() {
        if (instance == null) {
            instance = new GlobalSetting();
        }
        return instance;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }
}
