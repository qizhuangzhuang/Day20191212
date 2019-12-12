package com.bawei.day20191212.app;

import android.app.Application;
import android.content.Context;

/*
 *@Auther:祁壮壮
 *@Date: 2019/12/12
 *@Time:9:09
 *@Description功能: * */

public class App extends Application {

    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
