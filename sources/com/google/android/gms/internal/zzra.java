package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.internal.zzqo.zza;

public class zzra implements zzre {
    /* access modifiers changed from: private */

    /* renamed from: zA */
    public final zzrf f595zA;

    /* renamed from: zB */
    private boolean f596zB = false;

    public zzra(zzrf zzrf) {
        this.f595zA = zzrf;
    }

    /* JADX WARNING: type inference failed for: r0v8, types: [com.google.android.gms.common.api.Api$zzg] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <A extends com.google.android.gms.common.api.Api.zzb> void zzd(com.google.android.gms.internal.zzqo.zza<? extends com.google.android.gms.common.api.Result, A> r4) throws android.os.DeadObjectException {
        /*
            r3 = this;
            com.google.android.gms.internal.zzrf r0 = r3.f595zA
            com.google.android.gms.internal.zzrd r0 = r0.f682yW
            com.google.android.gms.internal.zzsg r0 = r0.f652Ap
            r0.zzb(r4)
            com.google.android.gms.internal.zzrf r0 = r3.f595zA
            com.google.android.gms.internal.zzrd r0 = r0.f682yW
            com.google.android.gms.common.api.Api$zzc r1 = r4.zzaqv()
            com.google.android.gms.common.api.Api$zze r0 = r0.zzb(r1)
            boolean r1 = r0.isConnected()
            if (r1 != 0) goto L_0x0034
            com.google.android.gms.internal.zzrf r1 = r3.f595zA
            java.util.Map<com.google.android.gms.common.api.Api$zzc<?>, com.google.android.gms.common.ConnectionResult> r1 = r1.f679Ay
            com.google.android.gms.common.api.Api$zzc r2 = r4.zzaqv()
            boolean r1 = r1.containsKey(r2)
            if (r1 == 0) goto L_0x0034
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status
            r1 = 17
            r0.<init>(r1)
            r4.zzaa(r0)
        L_0x0033:
            return
        L_0x0034:
            boolean r1 = r0 instanceof com.google.android.gms.common.internal.zzag
            if (r1 == 0) goto L_0x003e
            com.google.android.gms.common.internal.zzag r0 = (com.google.android.gms.common.internal.zzag) r0
            com.google.android.gms.common.api.Api$zzg r0 = r0.zzawt()
        L_0x003e:
            r4.zzb(r0)
            goto L_0x0033
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzra.zzd(com.google.android.gms.internal.zzqo$zza):void");
    }

    public void begin() {
    }

    public void connect() {
        if (this.f596zB) {
            this.f596zB = false;
            this.f595zA.zza((zza) new zza(this) {
                public void zzaso() {
                    zzra.this.f595zA.f675AC.zzn(null);
                }
            });
        }
    }

    public boolean disconnect() {
        if (this.f596zB) {
            return false;
        }
        if (this.f595zA.f682yW.zzata()) {
            this.f596zB = true;
            for (zzsf zzaud : this.f595zA.f682yW.f651Ao) {
                zzaud.zzaud();
            }
            return false;
        }
        this.f595zA.zzh(null);
        return true;
    }

    public void onConnected(Bundle bundle) {
    }

    public void onConnectionSuspended(int i) {
        this.f595zA.zzh(null);
        this.f595zA.f675AC.zzc(i, this.f596zB);
    }

    public <A extends zzb, R extends Result, T extends zza<R, A>> T zza(T t) {
        return zzb(t);
    }

    public void zza(ConnectionResult connectionResult, Api<?> api, int i) {
    }

    /* access modifiers changed from: 0000 */
    public void zzasn() {
        if (this.f596zB) {
            this.f596zB = false;
            this.f595zA.f682yW.f652Ap.release();
            disconnect();
        }
    }

    public <A extends zzb, T extends zza<? extends Result, A>> T zzb(T t) {
        try {
            zzd(t);
        } catch (DeadObjectException e) {
            this.f595zA.zza((zza) new zza(this) {
                public void zzaso() {
                    zzra.this.onConnectionSuspended(1);
                }
            });
        }
        return t;
    }
}
