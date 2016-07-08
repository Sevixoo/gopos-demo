package com.sevixoo.goposdemo.service.sync;

/**
 * Created by Seweryn on 2016-07-08.
 */
public interface ISyncDispatcher {

    public void sendSyncStarted();
    public void sendSyncFinish();
    public void sendSyncError( String message );

}
