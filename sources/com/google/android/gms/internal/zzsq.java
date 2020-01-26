package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class zzsq<R extends Result> extends com.google.android.gms.internal.zzqo.zza<R, zzsr> {

    static abstract class zza extends zzsq<Status> {
        public zza(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* renamed from: zzb */
        public Status zzc(Status status) {
            return status;
        }
    }

    public zzsq(GoogleApiClient googleApiClient) {
        super(zzsn.API, googleApiClient);
    }
}
