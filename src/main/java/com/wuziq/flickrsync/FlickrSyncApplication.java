package com.wuziq.flickrsync;

import android.app.Application;
import android.content.Context;

/**
 * Created by wuziq on 5/12/2014.
 *
 * http://stackoverflow.com/questions/987072/using-application-context-everywhere?lq=1
 */
public class FlickrSyncApplication extends Application
{
    private static FlickrSyncApplication instance;

    public FlickrSyncApplication()
    {
        instance = this;
    }

    public static Context getContext()
    {
        return instance;
    }
}
