package com.google.android.gms.internal;

import android.os.DeadObjectException;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzsh<A extends zzb> {

    /* renamed from: Bm */
    private final zzrr.zzb<?> f772Bm;

    public zzrr.zzb<?> zzatz() {
        return this.f772Bm;
    }

    /* access modifiers changed from: protected */
    public abstract void zzc(A a, TaskCompletionSource<Void> taskCompletionSource) throws DeadObjectException;
}
