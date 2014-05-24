package com.wuziq.flickrsync;

import com.googlecode.flickrjandroid.oauth.OAuth;
import com.googlecode.flickrjandroid.oauth.OAuthToken;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

/**
 * Created by wuziq on 5/22/2014.
 */
@RunWith( RobolectricTestRunner.class )
public class UploaderTest
{
    private volatile boolean m_wasCalledAsynchronously;

    private class FakeAuthenticator implements IAuthenticator
    {
        @Override
        public OAuthToken getAccessToken()
        {
            OAuthToken accessToken = new OAuthToken();
            accessToken.setOauthToken( "test token" );
            accessToken.setOauthTokenSecret( "test secret" );

            m_wasCalledAsynchronously = true;
            return accessToken;
        }

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

    @Before
    public void setUp()
    {
        m_wasCalledAsynchronously = false;
    }

    @Test
    public void uploader_prepareUpload_callsGetAccessTokenOnAuthenticatorAsynchronously() throws InterruptedException
    {
        int i = 0;
        IAuthenticator fakeAuthenticator = new FakeAuthenticator();
        IUploader uploader = new Uploader( fakeAuthenticator );
        uploader.prepareUpload();
        while ( ! m_wasCalledAsynchronously )
        {
            if ( i < 5 )
            {
                Thread.sleep( 1000 );
            }
            else
            {
                break;
            }
            ++ i;
        }

        Assert.assertTrue( m_wasCalledAsynchronously );
    }
}
