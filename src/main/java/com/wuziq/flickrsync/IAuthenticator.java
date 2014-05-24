package com.wuziq.flickrsync;

import com.googlecode.flickrjandroid.FlickrException;
import com.googlecode.flickrjandroid.oauth.OAuth;
import com.googlecode.flickrjandroid.oauth.OAuthToken;
import com.wuziq.flickrsync.exceptions.AuthenticationException;

import java.io.IOException;

/**
 * Created by wuziq on 5/22/2014.
 */
public interface IAuthenticator
{
    OAuthToken getAccessToken() throws IOException, FlickrException, AuthenticationException;

    OAuth getRequestToken();

    OAuth renewAccessToken();
}
