package com.wuziq.flickrsync;

import com.googlecode.flickrjandroid.oauth.OAuth;
import com.googlecode.flickrjandroid.oauth.OAuthToken;

/**
 * Created by wuziq on 5/22/2014.
 */
public interface IAuthenticator
{
    OAuthToken getAccessToken();

    OAuth getRequestToken();

    OAuth renewAccessToken();
}
