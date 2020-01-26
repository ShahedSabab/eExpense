package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IInterface;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zze.zzb;
import com.google.android.gms.common.internal.zze.zzc;
import com.google.android.gms.common.internal.zzk.zza;
import java.util.Set;

public abstract class zzj<T extends IInterface> extends zze<T> implements zze, zza {

    /* renamed from: gj */
    private final Account f322gj;

    /* renamed from: jw */
    private final Set<Scope> f323jw;

    /* renamed from: zP */
    private final zzf f324zP;

    protected zzj(Context context, Looper looper, int i, zzf zzf, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, zzl.zzcc(context), GoogleApiAvailability.getInstance(), i, zzf, (ConnectionCallbacks) zzaa.zzy(connectionCallbacks), (OnConnectionFailedListener) zzaa.zzy(onConnectionFailedListener));
    }

    protected zzj(Context context, Looper looper, zzl zzl, GoogleApiAvailability googleApiAvailability, int i, zzf zzf, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, zzl, googleApiAvailability, i, zza(connectionCallbacks), zza(onConnectionFailedListener), zzf.zzavt());
        this.f324zP = zzf;
        this.f322gj = zzf.getAccount();
        this.f323jw = zzb(zzf.zzavq());
    }

    @Nullable
    private static zzb zza(final ConnectionCallbacks connectionCallbacks) {
        if (connectionCallbacks == null) {
            return null;
        }
        return new zzb() {
            public void onConnected(@Nullable Bundle bundle) {
                ConnectionCallbacks.this.onConnected(bundle);
            }

            public void onConnectionSuspended(int i) {
                ConnectionCallbacks.this.onConnectionSuspended(i);
            }
        };
    }

    @Nullable
    private static zzc zza(final OnConnectionFailedListener onConnectionFailedListener) {
        if (onConnectionFailedListener == null) {
            return null;
        }
        return new zzc() {
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                OnConnectionFailedListener.this.onConnectionFailed(connectionResult);
            }
        };
    }

    private Set<Scope> zzb(@NonNull Set<Scope> set) {
        Set<Scope> zzc = zzc(set);
        for (Scope contains : zzc) {
            if (!set.contains(contains)) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        return zzc;
    }

    public final Account getAccount() {
        return this.f322gj;
    }

    /* access modifiers changed from: protected */
    public final Set<Scope> zzavi() {
        return this.f323jw;
    }

    /* access modifiers changed from: protected */
    public final zzf zzawb() {
        return this.f324zP;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public Set<Scope> zzc(@NonNull Set<Scope> set) {
        return set;
    }
}
