package com.google.android.gms.internal;

import android.os.DeadObjectException;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzrw<A extends zzb> {

    /* renamed from: Bt */
    private final zzrr<?> f741Bt;

    /* access modifiers changed from: protected */
    public abstract void zza(A a, TaskCompletionSource<Void> taskCompletionSource) throws DeadObjectException;

    public zzrr.zzb<?> zzatz() {
        return this.f741Bt.zzatz();
    }

    public void zzaua() {
        this.f741Bt.clear();
    }
}
