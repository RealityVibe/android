package com.example.yyyyz.winnerblog;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.widget.TabHost;

/**
 * Created by Yyyyz on 2018/1/24.
 */

public class Tab {
    private int image;
    private String text;
    private Class fragment;

    public Tab(int image, String text, Class fragment){
        this.image = image;
        this.text = text;
        this.fragment = fragment;
    }
/*    public void Tab(int image, String text, Class fragment){
        this.image = image;
        this.text = text;
        this.fragment = fragment;
    }*/
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        fragment = fragment;
    }
}
