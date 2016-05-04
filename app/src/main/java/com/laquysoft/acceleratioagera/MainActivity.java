package com.laquysoft.acceleratioagera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.agera.Updatable;


public class MainActivity extends AppCompatActivity implements Updatable {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    AccelerometerListenable observable;

    AccelerometerRepository accelerometerRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        observable = new AccelerometerListenable(this);
        accelerometerRepository = new AccelerometerRepository(observable);

    }


    @Override
    public void update() {
        float[] values = accelerometerRepository.get();
        Log.d(LOG_TAG, "update: " + values[0] + " " + values[1] + " " + values[2]);
    }

    @Override
    protected void onResume() {
        super.onResume();
        observable.addUpdatable(accelerometerRepository);
        accelerometerRepository.addUpdatable(this);
        update();
    }

    @Override
    protected void onPause() {
        super.onPause();
        observable.removeUpdatable(accelerometerRepository);
        accelerometerRepository.removeUpdatable(this);
    }
}
