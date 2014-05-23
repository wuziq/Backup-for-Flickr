package com.wuziq.flickrsync;

import com.googlecode.flickrjandroid.oauth.OAuth;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;

/**
 * Created by wuziq on 5/22/2014.
 */
@RunWith( RobolectricTestRunner.class )
public class AuthenticatorTest
{
    @Mock
    private IPreferences m_mockPreferences;

    private static IAuthenticator getNewAuthenticator()
    {
        return new Authenticator();
    }

    @Test
    public void authenticator_accessTokenExists_getAccessTokenReturnsValidOAuthToken()
    {
        Mockito.when( m_mockPreferences.getAccessToken() )
               .thenReturn( "test access token" );
        Mockito.when( m_mockPreferences.getAccessTokenSecret() )
               .thenReturn( "test access token secret" );

        IAuthenticator authenticator = getNewAuthenticator();
        OAuth oAuth = null;
        oAuth = authenticator.getAccessToken();

        Assert.assertEquals( "test access token",
                             oAuth.getToken()
                                  .getOauthToken()
        );
        Assert.assertEquals( "test access token secret",
                             oAuth.getToken()
                                  .getOauthTokenSecret() );
    }
}
