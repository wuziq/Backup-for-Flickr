package com.wuziq.flickrsync;

import android.content.Context;
import android.content.SharedPreferences;
import com.googlecode.flickrjandroid.oauth.OAuth;
import com.googlecode.flickrjandroid.oauth.OAuthToken;
import com.googlecode.flickrjandroid.people.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wuziq on 5/11/2014.
 */
public class Preferences
{
    public static final String PREFS_NAME = "flickrsync-prefs-name";
    public static final String KEY_OAUTH_TOKEN = "flickrsync-prefs-key-oauthToken";
    public static final String KEY_TOKEN_SECRET = "flickrsync-prefs-key-oauthTokenSecret";
    public static final String KEY_USER_NAME = "flickrsync-prefs-key-userName";
    public static final String KEY_USER_ID = "flickrsync-prefs-key-userId";

    private static final Logger logger = LoggerFactory.getLogger( Preferences.class );

    public void saveOAuthTokenData( String token,
                                    String tokenSecret,
                                    String userId,
                                    String userName )
    {
        Context ctx = FlickrSyncApplication.getContext();
        SharedPreferences sp = ctx.getSharedPreferences( PREFS_NAME,
                                                         Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sp.edit();
        editor.putString( KEY_OAUTH_TOKEN,
                          token );
        editor.putString( KEY_TOKEN_SECRET,
                          tokenSecret );
        editor.putString( KEY_USER_NAME,
                          userName );
        editor.putString( KEY_USER_ID,
                          userId );
        editor.commit();
    }

    // TODO:  determine all scenarios for saving of this oauth data
    public OAuth getOAuthTokenData()
    {
        Context ctx = FlickrSyncApplication.getContext();
        SharedPreferences sp = ctx.getSharedPreferences( PREFS_NAME,
                                                         Context.MODE_PRIVATE );
        String oauthTokenString = sp.getString( KEY_OAUTH_TOKEN,
                                                null );
        String tokenSecret = sp.getString( KEY_TOKEN_SECRET,
                                           null );
        String userName = sp.getString( KEY_USER_NAME,
                                        null );
        String userId = sp.getString( KEY_USER_ID,
                                      null );

        OAuthToken oauthToken = null;
        if (    null == oauthTokenString
             || null == tokenSecret )
        {
            logger.warn( "No OAuth token was found." );
        }
        else
        {
            oauthToken = new OAuthToken();
            oauthToken.setOauthToken( oauthTokenString );
            oauthToken.setOauthTokenSecret( tokenSecret );
        }

        User user = null;

        if (    null == userName
             || null == userId )
        {
            logger.warn( "No user was found." );
        }
        else
        {
            user = new User();
            user.setUsername( userName );
            user.setId( userId );
        }

        OAuth oauth = new OAuth();
        oauth.setUser( user );
        oauth.setToken( oauthToken );

        logger.debug( "Retrieved token from preference store: oauth token={}, and token secret={}",
                      oauthTokenString,
                      tokenSecret );

        return oauth;
    }
}
