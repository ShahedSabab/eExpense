package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzqo.zzb;

public final class zzsp implements zzso {

    private static class zza extends zzsm {

        /* renamed from: EW */
        private final zzb<Status> f800EW;

        public zza(zzb<Status> zzb) {
            this.f800EW = zzb;
        }

        public void zzgv(int i) throws RemoteException {
            this.f800EW.setResult(new Status(i));
        }
    }

    public PendingResult<Status> zzg(GoogleApiClient googleApiClient) {
        return googleApiClient.zzb(new zza(googleApiClient) {
            /* access modifiers changed from: protected */
            public void zza(zzsr zzsr) throws RemoteException {
                ((zzst) zzsr.zzavg()).zza(new zza(this));
            }
        });
    }
}
