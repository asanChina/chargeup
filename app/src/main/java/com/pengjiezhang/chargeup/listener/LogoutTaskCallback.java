package com.pengjiezhang.chargeup.listener;

/**
 * Created by Pengjie on 8/31/2015.
 * This interface is the bridge between asynchronous "logout task" and component which runs in UI thread, the component who is going
 * to initiate "logout task" should implements this interface
 */
public interface LogoutTaskCallback {
    void logoutTaskFinished(boolean successful);
}
