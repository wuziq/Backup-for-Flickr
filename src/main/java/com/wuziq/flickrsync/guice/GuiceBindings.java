package com.wuziq.flickrsync.guice;

import com.google.inject.AbstractModule;
import com.wuziq.flickrsync.authentication.AuthenticationChecker;
import com.wuziq.flickrsync.authentication.IAuthenticationChecker;

/**
 * Created by wuziq on 5/17/2014.
 */
public class GuiceBindings extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind( IAuthenticationChecker.class ).to( AuthenticationChecker.class );
    }
}
