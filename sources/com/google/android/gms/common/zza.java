package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.internal.zzaa;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zza implements ServiceConnection {

    /* renamed from: wM */
    boolean f455wM = false;

    /* renamed from: wN */
    private final BlockingQueue<IBinder> f456wN = new LinkedBlockingQueue();

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f456wN.add(iBinder);
    }

    public void onServiceDisconnected(ComponentName componentName) {
    }

    public IBinder zza(long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException {
        zzaa.zzht("BlockingServiceConnection.getServiceWithTimeout() called on main thread");
        if (this.f455wM) {
            throw new IllegalStateException("Cannot call get on this connection more than once");
        }
        this.f455wM = true;
        IBinder iBinder = (IBinder) this.f456wN.poll(j, timeUnit);
        if (iBinder != null) {
            return iBinder;
        }
        throw new TimeoutException("Timed out waiting for the service connection");
    }

    public IBinder zzaqk() throws InterruptedException {
        zzaa.zzht("BlockingServiceConnection.getService() called on main thread");
        if (this.f455wM) {
            throw new IllegalStateException("Cannot call get on this connection more than once");
        }
        this.f455wM = true;
        return (IBinder) this.f456wN.take();
    }
}
