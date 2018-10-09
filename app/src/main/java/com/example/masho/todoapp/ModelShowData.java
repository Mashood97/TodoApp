package com.example.masho.todoapp;

/**
 * Created by Masho on 10/9/2018.
 */

public class ModelShowData {

    private String Activity;

    public ModelShowData(String activity) {
        Activity = activity;
    }
    public ModelShowData(){}
    public String getActivity() {
        return Activity;
    }

    public void setActivity(String activity) {
        Activity = activity;
    }

}
