package com.wuziq.flickrsync;

import com.googlecode.flickrjandroid.oauth.OAuth;

/**
 * Created by wuziq on 5/22/2014.
 */
public interface IAuthenticator
{
    OAuth getAccessToken();

    OAuth getRequestToken();

    OAuth renewAccessToken();
}
