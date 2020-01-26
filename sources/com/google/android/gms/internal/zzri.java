package com.google.android.gms.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.zzc;
import com.google.android.gms.internal.zzqo.zza;

public class zzri<O extends ApiOptions> extends zzqz {

    /* renamed from: AY */
    private final zzc<O> f718AY;

    public zzri(zzc<O> zzc) {
        super("Method is not supported by connectionless client. APIs supporting connectionless client must not call this method.");
        this.f718AY = zzc;
    }

    public Looper getLooper() {
        return this.f718AY.getLooper();
    }

    public <A extends zzb, R extends Result, T extends zza<R, A>> T zza(@NonNull T t) {
        return this.f718AY.doRead(t);
    }

    public void zza(zzsf zzsf) {
    }

    public <A extends zzb, T extends zza<? extends Result, A>> T zzb(@NonNull T t) {
        return this.f718AY.doWrite(t);
    }

    public void zzb(zzsf zzsf) {
    }
}
