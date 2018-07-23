package com.example.anuja.popularmoviesstagetwo.app.activity;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.example.anuja.popularmoviesstagetwo.common.ConnectionStatus;
import com.example.anuja.popularmoviesstagetwo.model.ConnectionModel;
import com.example.anuja.popularmoviesstagetwo.receiver.NetworkConnectivityReceiver;

/**
 * This is the Base class for all the activities
 */
public abstract class BaseActivity extends AppCompatActivity {

    // connection is available
    protected abstract void onConnected();

    // connection is not available
    protected abstract void onDisconnected();

    // snackbar
    private Snackbar snackbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleConnectivity();
    }

    /**
     * function called to handle the connectivity
     */
    private void handleConnectivity() {
        NetworkConnectivityReceiver connectivityReceiver = new NetworkConnectivityReceiver(getApplicationContext());
        connectivityReceiver.observe(this, new Observer<ConnectionModel>() {
            @Override
            public void onChanged(@Nullable ConnectionModel connectionModel) {
                if(connectionModel.getConnectionStatus() == ConnectionStatus.CONNECTED)
                    onConnected();
                else if(connectionModel.getConnectionStatus() == ConnectionStatus.NOT_CONNECTED)
                    onDisconnected();
            }
        });
    }

    /**
     * function called to show snack bar
     * @param resId - res id to display the message
     */
    protected void showSnackBar(CoordinatorLayout coordinatorLayout, int resId) {
        if(snackbar == null) {
            snackbar = Snackbar.make(coordinatorLayout, resId, Snackbar.LENGTH_LONG);
            snackbar.show();
            snackbar = null;
        }
    }
}
