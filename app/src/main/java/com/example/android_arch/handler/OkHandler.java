package com.example.android_arch.handler;

import android.os.Handler;
import android.os.Message;

/**
 * 统一handler
 */
public class OkHandler extends Handler implements IHandler{

    private OkHandler() {
    }

    private static OkHandler okHandler;

    public static OkHandler getInstance() {
        if (okHandler == null) {
            synchronized (OkHandler.class) {
                if (okHandler == null) {
                    okHandler = new OkHandler();
                }
            }
        }
        return okHandler;
    }

    @Override
    public void handleMessage(Message message) {

    }

    @Override
    public void handleError() {

    }

    @Override
    public void handleSuccess() {

    }
}
