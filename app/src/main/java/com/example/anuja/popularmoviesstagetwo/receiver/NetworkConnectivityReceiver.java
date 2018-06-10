package com.example.anuja.popularmoviesstagetwo.receiver;

import android.arch.lifecycle.LiveData;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.anuja.popularmoviesstagetwo.common.ConnectionStatus;
import com.example.anuja.popularmoviesstagetwo.model.ConnectionModel;

/**
 * This class handles the connectivity
 */
public class NetworkConnectivityReceiver extends LiveData<ConnectionModel> {

    /**
     * Context
     */
    private Context context;

    public NetworkConnectivityReceiver(Context context) {
        this.context = context;
    }

    @Override
    protected void onActive() {
        super.onActive();

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(connectivityBroadcastReceiver, filter);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        context.unregisterReceiver(connectivityBroadcastReceiver);
    }

    /**
     * broadcast receiver
     */
    private BroadcastReceiver connectivityBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getExtras() != null) {
                checkConnectivity(context);
            }
        }
    };

    /**
     * Ref:- https://developer.android.com/training/monitoring-device-state/connectivity-monitoring
     */
    public void checkConnectivity(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            postValue(new ConnectionModel(ConnectionStatus.CONNECTED));
        }
        else {
            postValue(new ConnectionModel(ConnectionStatus.NOT_CONNECTED));
        }
    }
}
