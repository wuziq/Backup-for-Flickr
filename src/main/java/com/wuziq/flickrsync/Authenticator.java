package com.wuziq.flickrsync;

import com.google.inject.Inject;
import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.oauth.OAuth;
import com.googlecode.flickrjandroid.oauth.OAuthInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wuziq on 5/22/2014.
 */
public class Authenticator implements IAuthenticator
{
    private static final Logger m_logger = LoggerFactory.getLogger( Authenticator.class );

    @Inject
    private IPreferences m_preferences;

    @Override
    public OAuth getAccessToken()
    {
        String oauthToken = m_preferences.getRequestToken();
        String oauthTokenSecret = m_preferences.getRequestTokenSecret();
        String verifier = m_preferences.getOAuthVerifier();

        Flickr f = FlickrHelper.getInstance()
                               .getFlickr();
        OAuthInterface oauthApi = f.getOAuthInterface();
        try
        {
            return oauthApi.getAccessToken( oauthToken,
                                            oauthTokenSecret,
                                            verifier );
        }
        catch ( Exception e )
        {
            m_logger.error( e.getLocalizedMessage(),
                            e );
            return null;
        }
    }

    // this should be called only after request token has been written into preferences
    @Override
    public OAuth getRequestToken()
    {
        return null;
    }

    @Override
    public OAuth renewAccessToken()
    {
        return null;
    }
}
