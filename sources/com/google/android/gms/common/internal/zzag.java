package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api.zzg;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

public class zzag<T extends IInterface> extends zzj<T> {

    /* renamed from: EO */
    private final zzg<T> f272EO;

    public zzag(Context context, Looper looper, int i, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, zzf zzf, zzg<T> zzg) {
        super(context, looper, i, zzf, connectionCallbacks, onConnectionFailedListener);
        this.f272EO = zzg;
    }

    public zzg<T> zzawt() {
        return this.f272EO;
    }

    /* access modifiers changed from: protected */
    public void zzc(int i, T t) {
        this.f272EO.zza(i, t);
    }

    /* access modifiers changed from: protected */
    public T zzh(IBinder iBinder) {
        return this.f272EO.zzh(iBinder);
    }

    /* access modifiers changed from: protected */
    public String zzjx() {
        return this.f272EO.zzjx();
    }

    /* access modifiers changed from: protected */
    public String zzjy() {
        return this.f272EO.zzjy();
    }
}
