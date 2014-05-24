package com.wuziq.flickrsync;

import android.os.AsyncTask;
import com.google.inject.Inject;
import com.googlecode.flickrjandroid.oauth.OAuth;
import com.googlecode.flickrjandroid.oauth.OAuthToken;

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
            return m_authenticator.getAccessToken();
        }

        @Override
        protected void onPostExecute( OAuthToken oAuthToken )
        {
            // TODO:  implement
        }
    }
}
