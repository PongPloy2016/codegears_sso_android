package com.tow.sso.codegears_sso_android.http;

import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Skeleton base class for RequestListeners, providing default error handling.
 * Applications should handle these error conditions.
 */
public abstract class HttpRQListener implements RQListener {

    @Override
    public void onFileNotFoundException(FileNotFoundException e, final Object state) {
        Log.e("Facebook", e.getMessage());
        e.printStackTrace();
    }

    @Override
    public void onIOException(IOException e, final Object state) {
        Log.e("Facebook", e.getMessage());
        e.printStackTrace();
    }

    @Override
    public void onMalformedURLException(MalformedURLException e, final Object state) {
        Log.e("Facebook", e.getMessage());
        e.printStackTrace();
    }

}
