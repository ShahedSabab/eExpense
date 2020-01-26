package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

public final class zzsn {
    public static final Api<NoOptions> API = new Api<>("Common.API", f798hh, f797hg);

    /* renamed from: EU */
    public static final zzso f796EU = new zzsp();

    /* renamed from: hg */
    public static final zzf<zzsr> f797hg = new zzf<>();

    /* renamed from: hh */
    private static final zza<zzsr, NoOptions> f798hh = new zza<zzsr, NoOptions>() {
        /* renamed from: zzf */
        public zzsr zza(Context context, Looper looper, com.google.android.gms.common.internal.zzf zzf, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzsr(context, looper, zzf, connectionCallbacks, onConnectionFailedListener);
        }
    };
}
