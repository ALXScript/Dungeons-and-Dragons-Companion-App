package com.example.victor.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

public class createChar extends AppCompatActivity
{
    private static final String TAG = "createChar";
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createchar);
        textView=findViewById(R.id.textView);
        BusProvider.getInstance().register(this);
    }

    /*

    Subscription is the complement to event publishing—it lets you receive notification
     that an event has occurred. To subscribe to an event, annotate a method with
     @Subscribe. The method should take only a single parameter,
     the type of which will be the event you wish to subscribe to.
     You DONT need to call the function, the Otto Bus API will do so
     In order to receive an event you need to register with the Bus
     */
    @Subscribe
    public void getCharacter(String s)
    {
        if (textView!=null) textView.setText(s);
    }
}
