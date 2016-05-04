package com.laquysoft.acceleratioagera;

import android.support.annotation.NonNull;

import com.google.android.agera.BaseObservable;
import com.google.android.agera.Supplier;
import com.google.android.agera.Updatable;

/**
 * Created by joaobiriba on 04/05/16.
 */
public class AccelerometerRepository extends BaseObservable
        implements Supplier<float[]>, Updatable, AccelerometerListenable.AccelerometerValuesCallback {

    private float[] values;

    private final AccelerometerListenable accelerometerFetcher;

    public AccelerometerRepository(AccelerometerListenable accelerometerFetcher) {
        super();
        this.accelerometerFetcher = accelerometerFetcher;
        values = new float[3];
    }

    @Override
    public void setAccelerometerValues(float[] values) {
        System.arraycopy(values, 0, this.values, 0, values.length);
        dispatchUpdate();
    }

    @Override
    protected void observableActivated() {
        // Now that this is activated, we trigger an update to ensure the repository contains up to
        // date data.
        update();
    }
    @NonNull
    @Override
    public float[] get() {
        return values;
    }

    @Override
    public void update() {
        accelerometerFetcher.getAccelerometerValues(this);
    }
}
