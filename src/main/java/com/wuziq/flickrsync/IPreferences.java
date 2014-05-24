package com.wuziq.flickrsync;

/**
 * Created by wuziq on 5/22/2014.
 */
public interface IPreferences
{
    public static final String KEY_REQUEST_TOKEN = "prefs-key-requestToken";
    public static final String KEY_REQUEST_TOKEN_SECRET = "prefs-key-requestTokenSecret";
    public static final String KEY_OAUTH_VERIFIER = "prefs-key-oauthVerifier";
    public static final String KEY_ACCESS_TOKEN = "prefs-key-accessToken";
    public static final String KEY_ACCESS_TOKEN_SECRET = "prefs-key-accessTokenSecret";

    String getRequestToken();

    String getRequestTokenSecret();

    String getOAuthVerifier();

    String getAccessToken();

    String getAccessTokenSecret();

    void setAccessToken( String oauthToken );

    void setAccessTokenSecret( String oauthTokenSecret );
}
