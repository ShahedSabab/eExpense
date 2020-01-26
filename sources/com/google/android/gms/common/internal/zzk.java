package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzk implements Callback {

    /* renamed from: Ee */
    private final zza f327Ee;

    /* renamed from: Ef */
    private final ArrayList<ConnectionCallbacks> f328Ef = new ArrayList<>();

    /* renamed from: Eg */
    final ArrayList<ConnectionCallbacks> f329Eg = new ArrayList<>();

    /* renamed from: Eh */
    private final ArrayList<OnConnectionFailedListener> f330Eh = new ArrayList<>();

    /* renamed from: Ei */
    private volatile boolean f331Ei = false;

    /* renamed from: Ej */
    private final AtomicInteger f332Ej = new AtomicInteger(0);

    /* renamed from: Ek */
    private boolean f333Ek = false;
    private final Handler mHandler;
    private final Object zzako = new Object();

    public interface zza {
        boolean isConnected();

        Bundle zzapn();
    }

    public zzk(Looper looper, zza zza2) {
        this.f327Ee = zza2;
        this.mHandler = new Handler(looper, this);
    }

    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) message.obj;
            synchronized (this.zzako) {
                if (this.f331Ei && this.f327Ee.isConnected() && this.f328Ef.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(this.f327Ee.zzapn());
                }
            }
            return true;
        }
        Log.wtf("GmsClientEvents", "Don't know how to handle message: " + message.what, new Exception());
        return false;
    }

    public boolean isConnectionCallbacksRegistered(ConnectionCallbacks connectionCallbacks) {
        boolean contains;
        zzaa.zzy(connectionCallbacks);
        synchronized (this.zzako) {
            contains = this.f328Ef.contains(connectionCallbacks);
        }
        return contains;
    }

    public boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener onConnectionFailedListener) {
        boolean contains;
        zzaa.zzy(onConnectionFailedListener);
        synchronized (this.zzako) {
            contains = this.f330Eh.contains(onConnectionFailedListener);
        }
        return contains;
    }

    public void registerConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        zzaa.zzy(connectionCallbacks);
        synchronized (this.zzako) {
            if (this.f328Ef.contains(connectionCallbacks)) {
                String valueOf = String.valueOf(connectionCallbacks);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 62).append("registerConnectionCallbacks(): listener ").append(valueOf).append(" is already registered").toString());
            } else {
                this.f328Ef.add(connectionCallbacks);
            }
        }
        if (this.f327Ee.isConnected()) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, connectionCallbacks));
        }
    }

    public void registerConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        zzaa.zzy(onConnectionFailedListener);
        synchronized (this.zzako) {
            if (this.f330Eh.contains(onConnectionFailedListener)) {
                String valueOf = String.valueOf(onConnectionFailedListener);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 67).append("registerConnectionFailedListener(): listener ").append(valueOf).append(" is already registered").toString());
            } else {
                this.f330Eh.add(onConnectionFailedListener);
            }
        }
    }

    public void unregisterConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        zzaa.zzy(connectionCallbacks);
        synchronized (this.zzako) {
            if (!this.f328Ef.remove(connectionCallbacks)) {
                String valueOf = String.valueOf(connectionCallbacks);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 52).append("unregisterConnectionCallbacks(): listener ").append(valueOf).append(" not found").toString());
            } else if (this.f333Ek) {
                this.f329Eg.add(connectionCallbacks);
            }
        }
    }

    public void unregisterConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        zzaa.zzy(onConnectionFailedListener);
        synchronized (this.zzako) {
            if (!this.f330Eh.remove(onConnectionFailedListener)) {
                String valueOf = String.valueOf(onConnectionFailedListener);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 57).append("unregisterConnectionFailedListener(): listener ").append(valueOf).append(" not found").toString());
            }
        }
    }

    public void zzawc() {
        this.f331Ei = false;
        this.f332Ej.incrementAndGet();
    }

    public void zzawd() {
        this.f331Ei = true;
    }

    public void zzgn(int i) {
        boolean z = false;
        if (Looper.myLooper() == this.mHandler.getLooper()) {
            z = true;
        }
        zzaa.zza(z, (Object) "onUnintentionalDisconnection must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.zzako) {
            this.f333Ek = true;
            ArrayList arrayList = new ArrayList(this.f328Ef);
            int i2 = this.f332Ej.get();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) it.next();
                if (!this.f331Ei || this.f332Ej.get() != i2) {
                    break;
                } else if (this.f328Ef.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnectionSuspended(i);
                }
            }
            this.f329Eg.clear();
            this.f333Ek = false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzn(com.google.android.gms.common.ConnectionResult r6) {
        /*
            r5 = this;
            r1 = 1
            android.os.Looper r0 = android.os.Looper.myLooper()
            android.os.Handler r2 = r5.mHandler
            android.os.Looper r2 = r2.getLooper()
            if (r0 != r2) goto L_0x0046
            r0 = r1
        L_0x000e:
            java.lang.String r2 = "onConnectionFailure must only be called on the Handler thread"
            com.google.android.gms.common.internal.zzaa.zza(r0, r2)
            android.os.Handler r0 = r5.mHandler
            r0.removeMessages(r1)
            java.lang.Object r1 = r5.zzako
            monitor-enter(r1)
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0054 }
            java.util.ArrayList<com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener> r2 = r5.f330Eh     // Catch:{ all -> 0x0054 }
            r0.<init>(r2)     // Catch:{ all -> 0x0054 }
            java.util.concurrent.atomic.AtomicInteger r2 = r5.f332Ej     // Catch:{ all -> 0x0054 }
            int r2 = r2.get()     // Catch:{ all -> 0x0054 }
            java.util.Iterator r3 = r0.iterator()     // Catch:{ all -> 0x0054 }
        L_0x002c:
            boolean r0 = r3.hasNext()     // Catch:{ all -> 0x0054 }
            if (r0 == 0) goto L_0x0057
            java.lang.Object r0 = r3.next()     // Catch:{ all -> 0x0054 }
            com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener r0 = (com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener) r0     // Catch:{ all -> 0x0054 }
            boolean r4 = r5.f331Ei     // Catch:{ all -> 0x0054 }
            if (r4 == 0) goto L_0x0044
            java.util.concurrent.atomic.AtomicInteger r4 = r5.f332Ej     // Catch:{ all -> 0x0054 }
            int r4 = r4.get()     // Catch:{ all -> 0x0054 }
            if (r4 == r2) goto L_0x0048
        L_0x0044:
            monitor-exit(r1)     // Catch:{ all -> 0x0054 }
        L_0x0045:
            return
        L_0x0046:
            r0 = 0
            goto L_0x000e
        L_0x0048:
            java.util.ArrayList<com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener> r4 = r5.f330Eh     // Catch:{ all -> 0x0054 }
            boolean r4 = r4.contains(r0)     // Catch:{ all -> 0x0054 }
            if (r4 == 0) goto L_0x002c
            r0.onConnectionFailed(r6)     // Catch:{ all -> 0x0054 }
            goto L_0x002c
        L_0x0054:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0054 }
            throw r0
        L_0x0057:
            monitor-exit(r1)     // Catch:{ all -> 0x0054 }
            goto L_0x0045
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzk.zzn(com.google.android.gms.common.ConnectionResult):void");
    }

    public void zzp(Bundle bundle) {
        boolean z = true;
        zzaa.zza(Looper.myLooper() == this.mHandler.getLooper(), (Object) "onConnectionSuccess must only be called on the Handler thread");
        synchronized (this.zzako) {
            zzaa.zzbs(!this.f333Ek);
            this.mHandler.removeMessages(1);
            this.f333Ek = true;
            if (this.f329Eg.size() != 0) {
                z = false;
            }
            zzaa.zzbs(z);
            ArrayList arrayList = new ArrayList(this.f328Ef);
            int i = this.f332Ej.get();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) it.next();
                if (!this.f331Ei || !this.f327Ee.isConnected() || this.f332Ej.get() != i) {
                    break;
                } else if (!this.f329Eg.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(bundle);
                }
            }
            this.f329Eg.clear();
            this.f333Ek = false;
        }
    }
}
