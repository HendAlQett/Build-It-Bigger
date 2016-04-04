package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by hend on 4/3/16.
 */
public class Utility {

    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
