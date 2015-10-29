package com.facefont.songhang.emojitext;

import android.app.Application;

/**
 * Created by songhang on 15/10/29.
 */
public class BaseApplication extends Application{
    static BaseApplication baseApplication;
    public static BaseApplication getInstance() {
        return baseApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
    }
}
