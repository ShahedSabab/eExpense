package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.zzrr.zzc;

public abstract class zzqx<L> implements zzc<L> {

    /* renamed from: zy */
    private final DataHolder f591zy;

    protected zzqx(DataHolder dataHolder) {
        this.f591zy = dataHolder;
    }

    /* access modifiers changed from: protected */
    public abstract void zza(L l, DataHolder dataHolder);

    public void zzasm() {
        if (this.f591zy != null) {
            this.f591zy.close();
        }
    }

    public final void zzt(L l) {
        zza(l, this.f591zy);
    }
}
