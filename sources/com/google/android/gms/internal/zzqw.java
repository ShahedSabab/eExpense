package com.google.android.gms.internal;

import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.util.zza;

public class zzqw extends zzqp {

    /* renamed from: xy */
    private zzrh f589xy;

    /* renamed from: zx */
    private final zza<zzql<?>> f590zx = new zza<>();

    private zzqw(zzrp zzrp) {
        super(zzrp);
        this.f725Bf.zza("ConnectionlessLifecycleHelper", (zzro) this);
    }

    public static void zza(Activity activity, zzrh zzrh, zzql<?> zzql) {
        zzrp zzs = zzs(activity);
        zzqw zzqw = (zzqw) zzs.zza("ConnectionlessLifecycleHelper", zzqw.class);
        if (zzqw == null) {
            zzqw = new zzqw(zzs);
        }
        zzqw.f589xy = zzrh;
        zzqw.zza(zzql);
        zzrh.zza(zzqw);
    }

    private void zza(zzql<?> zzql) {
        zzaa.zzb(zzql, (Object) "ApiKey cannot be null");
        this.f590zx.add(zzql);
    }

    public void onStart() {
        super.onStart();
        if (!this.f590zx.isEmpty()) {
            this.f589xy.zza(this);
        }
    }

    public void onStop() {
        super.onStop();
        this.f589xy.zzb(this);
    }

    /* access modifiers changed from: protected */
    public void zza(ConnectionResult connectionResult, int i) {
        this.f589xy.zza(connectionResult, i);
    }

    /* access modifiers changed from: protected */
    public void zzarm() {
        this.f589xy.zzarm();
    }

    /* access modifiers changed from: 0000 */
    public zza<zzql<?>> zzasl() {
        return this.f590zx;
    }
}
