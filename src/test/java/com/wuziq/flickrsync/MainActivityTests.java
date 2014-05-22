package com.wuziq.flickrsync;

import android.app.Activity;
import android.widget.Button;
import com.example.R;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

/**
 * Created by wuziq on 5/17/2014.
 */
@RunWith( RobolectricTestRunner.class )
public class MainActivityTests
{
    @Mock
    IUploader m_mockUploader;

    @Test
    public void onClick_upload_performsDoUpload()
    {
        Activity activity = getNewMainActivity();
        Button buttonSync = (Button)activity.findViewById( R.id.button_sync );

        buttonSync.performClick();

        Mockito.verify( m_mockUploader ).doUpload();
    }

    private static Activity getNewMainActivity()
    {
        return Robolectric.buildActivity(MainActivity.class).create().visible().get();
    }
}
