package com.ibrickedlabs.viewpager;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.Timer;
import java.util.TimerTask;

import java.util.logging.LogRecord;

import me.relex.circleindicator.CircleIndicator;


//TODO 1.Custom toolbar


public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    ViewPager viewPager;
    /**
     * handler->to communicate with maithread ui
     * timer to schedule a task
     * runnable ->to perform the action
     */
    Handler handler;
    Runnable runnable;
    Timer timer;
    CircleIndicator circleIndicator;//external dependancy

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        final SlideAdapter slideAdapter = new SlideAdapter(this);
        viewPager.setAdapter(slideAdapter);
        circleIndicator = (CircleIndicator) findViewById(R.id.circleIndicator);
        circleIndicator.setViewPager(viewPager);

        handler = new Handler();

        //Works on ui thread not background thread
        runnable = new Runnable() {
            @Override
            public void run() {
                int i = viewPager.getCurrentItem();
                if (i == slideAdapter.imageArray.length - 1) {
                    i = 0;
                    viewPager.setCurrentItem(i, true);
                } else {
                    i++;
                    viewPager.setCurrentItem(i, true);
                }


            }
        };
        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 4000, 4000);


    }
}
