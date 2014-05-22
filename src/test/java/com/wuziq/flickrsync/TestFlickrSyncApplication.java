package com.wuziq.flickrsync;

import com.google.inject.AbstractModule;
import com.google.inject.util.Modules;
import mockit.Mocked;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.TestLifecycleApplication;
import roboguice.RoboGuice;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuziq on 5/21/2014.
 */
public class TestFlickrSyncApplication extends FlickrSyncApplication implements
                                                                     TestLifecycleApplication
{
    @Override public void beforeTest( Method test )
    {}

    @Override public void prepareTest( Object test )
    {
        MockitoAnnotations.initMocks( test );
        try
        {
            setUpRoboguice( test, getListOfMocks( test ) );
        } catch ( IllegalAccessException e )
        {
            throw new RuntimeException( "Failed to get instance of object", e );
        }
    }

    @Override public void afterTest( Method method )
    {}

    private List<Object> getListOfMocks( Object test ) throws IllegalAccessException
    {
        final Field[] declaredFields = test.getClass().getDeclaredFields();
        List<Object> objects = new ArrayList<Object>();
        for ( Field field : declaredFields )
        {
            if (    field.getAnnotation( Mock.class ) != null
                 || field.getAnnotation( Mocked.class ) != null )
            {
                field.setAccessible( true );
                objects.add( field.get( test ) );
            }
        }
        return objects;
    }

    private void setUpRoboguice( Object test, List<Object> objects )
    {
        RoboGuice.setBaseApplicationInjector( Robolectric.application,
                                              RoboGuice.DEFAULT_STAGE,
                                              Modules.override( RoboGuice.newDefaultRoboModule( Robolectric.application ) ).with( new MyRoboModule( objects ) ) );

        RoboGuice.injectMembers( Robolectric.application, test );
    }

    public static class MyRoboModule extends AbstractModule
    {
        private List<Object> mocksToInject;

        public MyRoboModule( List<Object> mocksToInject )
        {
            this.mocksToInject = mocksToInject;
        }

        @Override
        protected void configure()
        {
            for ( final Object mock : mocksToInject )
            {
                Class clazz = mock.getClass();
                bind( clazz.getSuperclass() ).toInstance( mock );
            }
        }
    }
}
