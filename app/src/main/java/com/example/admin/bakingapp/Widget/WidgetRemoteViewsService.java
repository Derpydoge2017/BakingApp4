package com.example.admin.bakingapp.Widget;

import android.content.Intent;
import android.widget.RemoteViewsService;


public class WidgetRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetRemoteViewFactory(this.getApplicationContext());
    }
}
