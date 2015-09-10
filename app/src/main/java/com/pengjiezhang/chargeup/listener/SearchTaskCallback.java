package com.pengjiezhang.chargeup.listener;

import org.json.JSONObject;

/**
 * Created by Pengjie on 8/31/2015.
 * This interface is the bridge between asynchronous "search task" and component which runs in UI thread, the component who is going
 * to initiate "search task" should implements this interface
 */
public interface SearchTaskCallback {
    void onSearchTaskFinished(boolean exceptionHappened, boolean successful, JSONObject jsonObject);
}
