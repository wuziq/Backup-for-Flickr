package com.wuziq.flickrsync.exceptions;

/**
 * Created by wuziq on 5/24/2014.
 */
public class AuthenticationException extends Exception
{
    private Type m_type;

    public static enum Type
    {
        NEEDS_USER_AUTHORIZATION
    }

    public AuthenticationException( Type type )
    {
        super( type.name() );
        m_type = type;
    }

    public Type getType()
    {
        return m_type;
    }
}
