package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.support.p000v4.app.FragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class zzqz extends GoogleApiClient {

    /* renamed from: zz */
    private final UnsupportedOperationException f594zz;

    public zzqz(String str) {
        this.f594zz = new UnsupportedOperationException(str);
    }

    public ConnectionResult blockingConnect() {
        throw this.f594zz;
    }

    public ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit) {
        throw this.f594zz;
    }

    public PendingResult<Status> clearDefaultAccountAndReconnect() {
        throw this.f594zz;
    }

    public void connect() {
        throw this.f594zz;
    }

    public void disconnect() {
        throw this.f594zz;
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        throw this.f594zz;
    }

    @NonNull
    public ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        throw this.f594zz;
    }

    public boolean hasConnectedApi(@NonNull Api<?> api) {
        throw this.f594zz;
    }

    public boolean isConnected() {
        throw this.f594zz;
    }

    public boolean isConnecting() {
        throw this.f594zz;
    }

    public boolean isConnectionCallbacksRegistered(@NonNull ConnectionCallbacks connectionCallbacks) {
        throw this.f594zz;
    }

    public boolean isConnectionFailedListenerRegistered(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
        throw this.f594zz;
    }

    public void reconnect() {
        throw this.f594zz;
    }

    public void registerConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks) {
        throw this.f594zz;
    }

    public void registerConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
        throw this.f594zz;
    }

    public void stopAutoManage(@NonNull FragmentActivity fragmentActivity) {
        throw this.f594zz;
    }

    public void unregisterConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks) {
        throw this.f594zz;
    }

    public void unregisterConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
        throw this.f594zz;
    }
}
