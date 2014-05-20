package com.wuziq.flickrsync;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.R;
import com.google.inject.Inject;
import com.wuziq.flickrsync.authentication.IAuthenticationChecker;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class MainActivity extends RoboActivity
{
    private static final int REQUESTCODE_LOGIN_ACTIVITY_EXITED = 1;

    @InjectView( R.id.button_takephotos )
    private Button m_buttonTakePhotos;

    @InjectView( R.id.button_sync )
    private Button m_buttonSync;

    @InjectView( R.id.button_viewuploaded )
    private Button m_buttonViewUploaded;

    @Inject
    private IAuthenticationChecker m_authChecker;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main,
                                   menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if ( id == R.id.action_settings )
        {
            return true;
        }
        return super.onOptionsItemSelected( item );
    }

    public void onButtonClick( View view )
    {
        switch ( view.getId() )
        {
        case R.id.button_takephotos:
            break;
        case R.id.button_sync:
            Intent intent = new Intent( this,
                                        LoginActivity.class );
            startActivity( intent );
            break;
        case R.id.button_viewuploaded:
            break;
        default:
            // TODO:  logger
        }
    }

    @Override
    protected void onActivityResult( int requestCode,
                                     int resultCode,
                                     Intent data )
    {
        super.onActivityResult( requestCode,
                                resultCode,
                                data );

        Toast.makeText( this,
                        "Login activity exited with resultCode " + resultCode,
                        Toast.LENGTH_LONG )
             .show();
    }
}
