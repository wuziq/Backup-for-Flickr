package com.example;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by wuziq on 5/6/2014.
 */
public class StringCalculatorTests
{
    @Test
    public void Add_EmptyString_ReturnsZero()
    {
        StringCalculator sc = makeNewStringCalculator();

        int result = sc.add( "" );

        Assert.assertEquals( 0, result );
    }

    @Test
    public void Add_SingleNumber_ReturnsThatNumber()
    {
        StringCalculator sc = makeNewStringCalculator();

        int result = sc.add( "1" );

        Assert.assertEquals( 1, result );
    }

    private static StringCalculator makeNewStringCalculator()
    {
        return new StringCalculator();
    }
}
