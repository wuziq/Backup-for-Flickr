package com.wuziq.flickrsync;

import android.os.AsyncTask;
import com.google.inject.Inject;
import com.googlecode.flickrjandroid.FlickrException;
import com.googlecode.flickrjandroid.oauth.OAuth;
import com.googlecode.flickrjandroid.oauth.OAuthToken;
import com.wuziq.flickrsync.exceptions.AuthenticationException;

import java.io.IOException;

/**
 * Created by wuziq on 5/21/2014.
 */
public class Uploader implements IUploader
{
    @Inject
    IAuthenticator m_authenticator;

    @Inject
    public Uploader( IAuthenticator authenticator )
    {
        m_authenticator = authenticator;
    }

    @Override
    public void prepareUpload()
    {
        GetAccessTokenAsyncTask getAccessTokenAsyncTask = new GetAccessTokenAsyncTask();
        getAccessTokenAsyncTask.execute();
    }

    @Override
    public void doUpload()
    {
        // TODO:  implement
    }

    private class GetAccessTokenAsyncTask extends AsyncTask<Void, Void, OAuthToken>
    {
        @Override
        protected OAuthToken doInBackground( Void... voids )
        {
            try
            {
                return m_authenticator.getAccessToken();
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
            catch ( FlickrException e )
            {
                e.printStackTrace();
            }
            catch ( AuthenticationException e )
            {
                e.printStackTrace();
            }
            finally
            {
                return null;
            }
        }

        @Override
        protected void onPostExecute( OAuthToken oAuthToken )
        {
            // TODO:  implement
        }
    }
}
