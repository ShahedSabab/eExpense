package com.google.android.gms.internal;

import android.support.p000v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.zzb;
import com.google.android.gms.common.api.zzc;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Set;

public final class zzqn {

    /* renamed from: xo */
    private final ArrayMap<zzql<?>, ConnectionResult> f524xo = new ArrayMap<>();

    /* renamed from: yv */
    private final TaskCompletionSource<Void> f525yv = new TaskCompletionSource<>();

    /* renamed from: yw */
    private int f526yw;

    /* renamed from: yx */
    private boolean f527yx = false;

    public zzqn(Iterable<zzc<? extends ApiOptions>> iterable) {
        for (zzc apiKey : iterable) {
            this.f524xo.put(apiKey.getApiKey(), null);
        }
        this.f526yw = this.f524xo.keySet().size();
    }

    public Task<Void> getTask() {
        return this.f525yv.getTask();
    }

    public void zza(zzql<?> zzql, ConnectionResult connectionResult) {
        this.f524xo.put(zzql, connectionResult);
        this.f526yw--;
        if (!connectionResult.isSuccess()) {
            this.f527yx = true;
        }
        if (this.f526yw != 0) {
            return;
        }
        if (this.f527yx) {
            this.f525yv.setException(new zzb(this.f524xo));
            return;
        }
        this.f525yv.setResult(null);
    }

    public Set<zzql<?>> zzaro() {
        return this.f524xo.keySet();
    }

    public void zzarp() {
        this.f525yv.setResult(null);
    }
}
