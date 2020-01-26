package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.internal.zzqo.zza;
import java.util.Collections;

public class zzrc implements zzre {

    /* renamed from: zA */
    private final zzrf f637zA;

    public zzrc(zzrf zzrf) {
        this.f637zA = zzrf;
    }

    public void begin() {
        this.f637zA.zzate();
        this.f637zA.f682yW.f647Ak = Collections.emptySet();
    }

    public void connect() {
        this.f637zA.zzatc();
    }

    public boolean disconnect() {
        return true;
    }

    public void onConnected(Bundle bundle) {
    }

    public void onConnectionSuspended(int i) {
    }

    public <A extends zzb, R extends Result, T extends zza<R, A>> T zza(T t) {
        this.f637zA.f682yW.f640Ad.add(t);
        return t;
    }

    public void zza(ConnectionResult connectionResult, Api<?> api, int i) {
    }

    public <A extends zzb, T extends zza<? extends Result, A>> T zzb(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
