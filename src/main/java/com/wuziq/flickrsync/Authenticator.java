package com.wuziq.flickrsync;

import com.google.inject.Inject;
import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.oauth.OAuth;
import com.googlecode.flickrjandroid.oauth.OAuthInterface;
import com.googlecode.flickrjandroid.oauth.OAuthToken;
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

    @Inject
    public Authenticator( IPreferences preferences )
    {
        m_preferences = preferences;
    }

    @Override
    public OAuthToken getAccessToken()
    {
        String accessToken = m_preferences.getAccessToken();
        String accessTokenSecret = m_preferences.getAccessTokenSecret();

        if ( null == accessToken )
        {
            String oauthToken = m_preferences.getRequestToken();
            String oauthTokenSecret = m_preferences.getRequestTokenSecret();
            String verifier = m_preferences.getOAuthVerifier();

            Flickr f = FlickrHelper.getInstance()
                                   .getFlickr();
            OAuthInterface oauthApi = f.getOAuthInterface();
            try
            {
                OAuth oAuth = oauthApi.getAccessToken( oauthToken,
                                                       oauthTokenSecret,
                                                       verifier );

                OAuthToken oAuthToken = oAuth.getToken();
                m_preferences.setAccessToken( oAuthToken.getOauthToken() );
                m_preferences.setAccessTokenSecret( oAuthToken.getOauthTokenSecret() );

                return oAuthToken;
            }
            catch ( Exception e )
            {
                m_logger.error( e.getLocalizedMessage(),
                                e );
                return null;
            }
        }

        OAuthToken oAuthToken = new OAuthToken();
        oAuthToken.setOauthToken( accessToken );
        oAuthToken.setOauthTokenSecret( accessTokenSecret );

        return oAuthToken;
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
