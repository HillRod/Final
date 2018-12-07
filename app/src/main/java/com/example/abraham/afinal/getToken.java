package com.example.abraham.afinal;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static android.support.constraint.Constraints.TAG;

public class getToken extends FirebaseInstanceIdService{
    public String toc;

    public getToken() {
        this.toc = FirebaseInstanceId.getInstance().getToken();
    }

    @Override
    public void onTokenRefresh(){
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        toc = refreshedToken;
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.}



    }
    public String get(){
        return FirebaseInstanceId.getInstance().getToken();
    }
}
