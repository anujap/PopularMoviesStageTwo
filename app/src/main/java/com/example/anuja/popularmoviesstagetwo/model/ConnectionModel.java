package com.example.anuja.popularmoviesstagetwo.model;

import com.example.anuja.popularmoviesstagetwo.common.ConnectionStatus;

public class ConnectionModel {

    /**
     * the signal strength
     */
    private ConnectionStatus connectionStatus;

    public ConnectionModel(ConnectionStatus connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public ConnectionStatus getConnectionStatus() {
        return connectionStatus;
    }
}
