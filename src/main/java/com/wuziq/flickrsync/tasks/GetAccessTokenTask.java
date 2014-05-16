package com.wuziq.flickrsync.tasks;

import android.os.AsyncTask;
import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.oauth.OAuth;
import com.googlecode.flickrjandroid.oauth.OAuthInterface;
import com.wuziq.flickrsync.FlickrHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wuziq on 5/11/2014.
 */
public class GetAccessTokenTask extends AsyncTask<String, Integer, OAuth>
{
    private static final Logger logger = LoggerFactory.getLogger( GetAccessTokenTask.class );

    private IGetAccessTokenCallback m_callback;

    public GetAccessTokenTask( IGetAccessTokenCallback callback )
    {
        super();
        this.m_callback = callback;
    }

    @Override
    protected OAuth doInBackground( String... params )
    {
        String oauthToken = params[0];
        String oauthTokenSecret = params[1];
        String verifier = params[2];

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
            logger.error( e.getLocalizedMessage(),
                          e );
            return null;
        }

    }

    @Override
    protected void onPostExecute( OAuth result )
    {
        if ( m_callback != null )
        {
            m_callback.onGotAccessToken( result );
        }
    }

}
