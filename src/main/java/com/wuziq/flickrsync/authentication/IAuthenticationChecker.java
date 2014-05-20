package com.wuziq.flickrsync.authentication;

/**
 * Created by wuziq on 5/17/2014.
 */
public interface IAuthenticationChecker
{
    boolean accessTokenIsPresent();
    boolean accessTokenIsValid();
    boolean requestTokenIsPresent();
    boolean requestTokenIsValid();
}
