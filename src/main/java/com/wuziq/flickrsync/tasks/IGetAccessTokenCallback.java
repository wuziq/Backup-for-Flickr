package com.wuziq.flickrsync.tasks;

import com.googlecode.flickrjandroid.oauth.OAuth;

/**
 * Created by wuziq on 5/13/2014.
 */
public interface IGetAccessTokenCallback
{
    void onGotAccessToken( OAuth result );
}
