package com.sevixoo.goposdemo.service.sync;

/**
 * Created by Seweryn on 2016-07-07.
 */
public interface ISyncHelper {

    void requestSync();
    boolean isSyncPending();
    boolean isSyncActive();

}
