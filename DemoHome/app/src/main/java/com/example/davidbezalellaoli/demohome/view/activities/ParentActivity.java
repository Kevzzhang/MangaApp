package com.example.davidbezalellaoli.demohome.view.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by David Bezalel Laoli on 10/30/2016.
 */
public class ParentActivity extends AppCompatActivity{

    /*
    * this function allows you to move to another Activity
    * @param Context context
    * @param Activity activity.class
     */
    public static void doChangeActivity (Context context, Class activityClass) {
        Intent _intent = new Intent(context, activityClass);
        _intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(_intent);
    }

}
