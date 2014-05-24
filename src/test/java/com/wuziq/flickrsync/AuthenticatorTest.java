package com.wuziq.flickrsync;

import com.googlecode.flickrjandroid.FlickrException;
import com.googlecode.flickrjandroid.Transport;
import com.googlecode.flickrjandroid.oauth.OAuth;
import com.googlecode.flickrjandroid.oauth.OAuthInterface;
import com.googlecode.flickrjandroid.oauth.OAuthToken;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * Created by wuziq on 5/22/2014.
 */
@RunWith( MockitoJUnitRunner.class )
public class AuthenticatorTest
{
    @Mock
    private IPreferences m_mockPreferences;

    @Mocked
    OAuthInterface m_mockOAuthInterface;

    private IAuthenticator getNewAuthenticator()
    {
        return new Authenticator( m_mockPreferences );
    }

    @Test
    public void authenticator_accessTokenExists_getAccessTokenReturnsAccessToken()
    {
        when( m_mockPreferences.getAccessToken() ).thenReturn( "test access token" );
        when( m_mockPreferences.getAccessTokenSecret() ).thenReturn( "test access token secret" );

        IAuthenticator authenticator = getNewAuthenticator();
        OAuthToken oAuthToken = null;
        oAuthToken = authenticator.getAccessToken();

        Assert.assertEquals( "test access token",
                             oAuthToken.getOauthToken() );
        Assert.assertEquals( "test access token secret",
                             oAuthToken.getOauthTokenSecret() );
    }

    @Test
    public void authenticator_accessTokenDoesNotExistButRequestTokenExists_accessTokenRequestedAndReturned() throws IOException, FlickrException
    {
        when( m_mockPreferences.getAccessToken() ).thenReturn( null );
        when( m_mockPreferences.getAccessTokenSecret() ).thenReturn( null );

        when( m_mockPreferences.getRequestToken() ).thenReturn( "test request token" );
        when( m_mockPreferences.getRequestTokenSecret() ).thenReturn( "test request token secret" );
        when( m_mockPreferences.getOAuthVerifier() ).thenReturn( "test oauth verifier" );

        new Expectations()
        {
            {
                new OAuthInterface( anyString,
                                    anyString,
                                    (Transport)any );
                result = m_mockOAuthInterface;

                OAuth fakeOAuth = new OAuth();
                OAuthToken fakeOAuthToken = new OAuthToken();
                fakeOAuthToken.setOauthToken( "test access token" );
                fakeOAuthToken.setOauthTokenSecret( "test access token secret" );
                fakeOAuth.setToken( fakeOAuthToken );
                m_mockOAuthInterface.getAccessToken( "test request token",
                                                     "test request token secret",
                                                     "test oauth verifier" );
                result = fakeOAuth;
            }
        };

        IAuthenticator authenticator = getNewAuthenticator();
        OAuthToken oAuthToken = null;
        oAuthToken = authenticator.getAccessToken();

        Assert.assertEquals( "test access token",
                             oAuthToken.getOauthToken() );
        Assert.assertEquals( "test access token secret",
                             oAuthToken.getOauthTokenSecret() );

        verify( m_mockPreferences ).getAccessToken();
        verify( m_mockPreferences ).getAccessTokenSecret();

        verify( m_mockPreferences ).getRequestToken();
        verify( m_mockPreferences ).getRequestTokenSecret();
        verify( m_mockPreferences ).getOAuthVerifier();
        verify( m_mockPreferences ).setAccessToken( "test access token" );
        verify( m_mockPreferences ).setAccessTokenSecret( "test access token secret" );
    }
}
