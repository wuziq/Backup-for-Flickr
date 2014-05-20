package com.wuziq.flickrsync.authentication;

/**
 * Created by wuziq on 5/17/2014.
 */
public class AuthenticationChecker implements IAuthenticationChecker
{
    @Override
    public boolean accessTokenIsPresent()
    {
        return false;
    }

    @Override
    public boolean accessTokenIsValid()
    {
        return false;
    }

    @Override
    public boolean requestTokenIsPresent()
    {
        return false;
    }

    @Override
    public boolean requestTokenIsValid()
    {
        return false;
    }
}
