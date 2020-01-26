package com.google.android.gms.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzaa;

public class zzqr implements ConnectionCallbacks, OnConnectionFailedListener {

    /* renamed from: vS */
    public final Api<?> f553vS;

    /* renamed from: yU */
    private final int f554yU;

    /* renamed from: yV */
    private zzqs f555yV;

    public zzqr(Api<?> api, int i) {
        this.f553vS = api;
        this.f554yU = i;
    }

    private void zzary() {
        zzaa.zzb(this.f555yV, (Object) "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
    }

    public void onConnected(@Nullable Bundle bundle) {
        zzary();
        this.f555yV.onConnected(bundle);
    }

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        zzary();
        this.f555yV.zza(connectionResult, this.f553vS, this.f554yU);
    }

    public void onConnectionSuspended(int i) {
        zzary();
        this.f555yV.onConnectionSuspended(i);
    }

    public void zza(zzqs zzqs) {
        this.f555yV = zzqs;
    }
}
