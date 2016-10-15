package com.example.kousalya.swiggy.utilis;

import android.app.Activity;
import android.support.design.widget.Snackbar;

/**
 * Created by kousalya on 15/10/16.
 */

public class CommonUtilis {

    public static void showMassege(Activity activity, String msg){
        if (activity != null && msg != null) {
            Snackbar.make(activity.findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG).show();
        }
    }
}
