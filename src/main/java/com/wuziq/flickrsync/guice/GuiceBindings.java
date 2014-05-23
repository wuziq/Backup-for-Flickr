package com.wuziq.flickrsync.guice;

import com.google.inject.AbstractModule;
import com.wuziq.flickrsync.*;

/**
 * Created by wuziq on 5/17/2014.
 */
public class GuiceBindings extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind( IAuthenticator.class ).to( Authenticator.class );
        bind( IPreferences.class ).to( Preferences.class );
        bind( IUploader.class ).to( Uploader.class );
    }
}
