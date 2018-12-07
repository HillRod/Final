package com.example.abraham.afinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class SignedIn extends AppCompatActivity {

    UserSessionManager session;

    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_in);

        session = new UserSessionManager(getApplicationContext());

        // Button logout
        btnLogout = findViewById(R.id.btnLogout);

        if(session.checkLogin())
            finish();

        // get user data from session
        //HashMap<String, String> user = session.getUserDetails();

        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // Clear the User session data
                // and redirect user to LoginActivity
                session.logoutUser();
            }
        });

    }

}
