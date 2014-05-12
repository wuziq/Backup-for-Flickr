package com.wuziq.flickrsync;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.REST;
import com.googlecode.flickrjandroid.RequestContext;
import com.googlecode.flickrjandroid.oauth.OAuth;
import com.googlecode.flickrjandroid.oauth.OAuthToken;
import com.googlecode.flickrjandroid.photos.PhotosInterface;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by wuziq on 5/11/2014.
 */
public final class FlickrHelper
{
    private static FlickrHelper instance = null;
    private static final String API_KEY = "c802346cf9580a3117d14ae0bcb94ef8"; //$NON-NLS-1$
    private static final String API_SEC = "2b32cc1ccbbcb1b5"; //$NON-NLS-1$

    private FlickrHelper()
    {

    }

    public static FlickrHelper getInstance()
    {
        if ( instance == null )
        {
            instance = new FlickrHelper();
        }

        return instance;
    }

    public Flickr getFlickr()
    {
        try
        {
            Flickr f = new Flickr( API_KEY,
                                   API_SEC,
                                   new REST() );
            return f;
        }
        catch ( ParserConfigurationException e )
        {
            return null;
        }
    }

    public Flickr getFlickrAuthed( String token,
                                   String secret )
    {
        Flickr f = getFlickr();
        RequestContext requestContext = RequestContext.getRequestContext();
        OAuth auth = new OAuth();
        auth.setToken( new OAuthToken( token,
                                       secret ) );
        requestContext.setOAuth( auth );
        return f;
    }

    public PhotosInterface getPhotosInterface()
    {
        Flickr f = getFlickr();
        if ( f != null )
        {
            return f.getPhotosInterface();
        }
        else
        {
            return null;
        }
    }

}
