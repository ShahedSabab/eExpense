package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.signin.internal.zzg;

public final class zzxo {
    public static final Api<zzxq> API = new Api<>("SignIn.API", f830hh, f829hg);

    /* renamed from: Jb */
    public static final Api<zza> f828Jb = new Api<>("SignIn.INTERNAL_API", aDj, aDi);
    public static final zzf<zzg> aDi = new zzf<>();
    static final com.google.android.gms.common.api.Api.zza<zzg, zza> aDj = new com.google.android.gms.common.api.Api.zza<zzg, zza>() {
        public zzg zza(Context context, Looper looper, com.google.android.gms.common.internal.zzf zzf, zza zza, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzg(context, looper, false, zzf, zza.zzcdb(), connectionCallbacks, onConnectionFailedListener);
        }
    };

    /* renamed from: hg */
    public static final zzf<zzg> f829hg = new zzf<>();

    /* renamed from: hh */
    public static final com.google.android.gms.common.api.Api.zza<zzg, zzxq> f830hh = new com.google.android.gms.common.api.Api.zza<zzg, zzxq>() {
        public zzg zza(Context context, Looper looper, com.google.android.gms.common.internal.zzf zzf, zzxq zzxq, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzg(context, looper, true, zzf, zzxq == null ? zzxq.aDl : zzxq, connectionCallbacks, onConnectionFailedListener);
        }
    };

    /* renamed from: jn */
    public static final Scope f831jn = new Scope(Scopes.PROFILE);

    /* renamed from: jo */
    public static final Scope f832jo = new Scope("email");

    public static class zza implements HasOptions {
        private final Bundle aDk;

        public Bundle zzcdb() {
            return this.aDk;
        }
    }
}
