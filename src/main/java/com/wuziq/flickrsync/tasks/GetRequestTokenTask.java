package com.wuziq.flickrsync.tasks;

/**
 * Created by wuziq on 5/11/2014.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;
import com.example.R;
import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.auth.Permission;
import com.googlecode.flickrjandroid.oauth.OAuthToken;
import com.wuziq.flickrsync.FlickrHelper;
import com.wuziq.flickrsync.Preferences;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class GetRequestTokenTask extends AsyncTask<Void, Integer, String>
{

    private static final Logger logger = LoggerFactory.getLogger( GetRequestTokenTask.class );

    /**
     * The context.
     */
    private Context m_ctx;

    /**
     * The progress dialog before going to the browser.
     */
    private ProgressDialog mProgressDialog;

    /**
     * Constructor.
     *
     * @param context
     */
    public GetRequestTokenTask( Context context )
    {
        super();
        this.m_ctx = context;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        mProgressDialog = ProgressDialog.show( m_ctx,
                                               "",
                                               "Generating the authorization request..." ); //$NON-NLS-1$ //$NON-NLS-2$
        mProgressDialog.setCanceledOnTouchOutside( true );
        mProgressDialog.setCancelable( true );
        mProgressDialog.setOnCancelListener( new OnCancelListener()
                                             {
                                                 @Override
                                                 public void onCancel( DialogInterface dlg )
                                                 {
                                                     GetRequestTokenTask.this.cancel( true );
                                                 }
                                             }
        );
    }

    /*
     * (non-Javadoc)
     *
     * @see android.os.AsyncTask#doInBackground(Params[])
     */
    @Override
    protected String doInBackground( Void... params )
    {
        // TODO:  use non-generic Exception
        try
        {
            Flickr f = FlickrHelper.getInstance()
                                   .getFlickr();
            OAuthToken oauthToken = f.getOAuthInterface()
                                     .getRequestToken( Uri.parse( m_ctx.getString( R.string.scheme ) + "://oauth" )
                                                          .toString() );
            saveTokenSecret( oauthToken.getOauthTokenSecret() );
            URL oauthUrl = f.getOAuthInterface()
                            .buildAuthenticationUrl( Permission.WRITE,
                                                     oauthToken );
            return oauthUrl.toString();
        }
        catch ( Exception e )
        {
            logger.error( "Error to oauth",
                          e ); //$NON-NLS-1$
            return "error:" + e.getMessage(); //$NON-NLS-1$
        }
    }

    /**
     * Saves the oauth token secrent.
     *
     * @param tokenSecret
     */
    private void saveTokenSecret( String tokenSecret )
    {
        logger.debug( "request token: " + tokenSecret );
        Preferences prefs = new Preferences();
        prefs.saveOAuthTokenData( null,
                                  tokenSecret,
                                  null,
                                  null );
        logger.debug( "oauth token secret saved: {}",
                      tokenSecret ); //$NON-NLS-1$
    }

    @Override
    protected void onPostExecute( String result )
    {
        if ( mProgressDialog != null )
        {
            mProgressDialog.dismiss();
        }
        if ( result != null && ! result.startsWith( "error" ) )
        {
            Intent intent = new Intent( Intent.ACTION_VIEW,
                                        Uri.parse( result ) );
            intent.setFlags( Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS );
            m_ctx.startActivity( intent );
        }
        else
        {
            Toast.makeText( m_ctx,
                            result,
                            Toast.LENGTH_LONG )
                 .show();
        }
    }

}
