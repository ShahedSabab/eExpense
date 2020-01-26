package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zza;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public class zzqv {
    /* access modifiers changed from: private */

    /* renamed from: zs */
    public final Map<zzqq<?>, Boolean> f583zs = Collections.synchronizedMap(new WeakHashMap());
    /* access modifiers changed from: private */

    /* renamed from: zt */
    public final Map<TaskCompletionSource<?>, Boolean> f584zt = Collections.synchronizedMap(new WeakHashMap());

    private void zza(boolean z, Status status) {
        HashMap hashMap;
        HashMap hashMap2;
        synchronized (this.f583zs) {
            hashMap = new HashMap(this.f583zs);
        }
        synchronized (this.f584zt) {
            hashMap2 = new HashMap(this.f584zt);
        }
        for (Entry entry : hashMap.entrySet()) {
            if (z || ((Boolean) entry.getValue()).booleanValue()) {
                ((zzqq) entry.getKey()).zzab(status);
            }
        }
        for (Entry entry2 : hashMap2.entrySet()) {
            if (z || ((Boolean) entry2.getValue()).booleanValue()) {
                ((TaskCompletionSource) entry2.getKey()).trySetException(new zza(status));
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void zza(final zzqq<? extends Result> zzqq, boolean z) {
        this.f583zs.put(zzqq, Boolean.valueOf(z));
        zzqq.zza((PendingResult.zza) new PendingResult.zza() {
            public void zzx(Status status) {
                zzqv.this.f583zs.remove(zzqq);
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public <TResult> void zza(final TaskCompletionSource<TResult> taskCompletionSource, boolean z) {
        this.f584zt.put(taskCompletionSource, Boolean.valueOf(z));
        taskCompletionSource.getTask().addOnCompleteListener(new OnCompleteListener<TResult>() {
            public void onComplete(@NonNull Task<TResult> task) {
                zzqv.this.f584zt.remove(taskCompletionSource);
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public boolean zzasi() {
        return !this.f583zs.isEmpty() || !this.f584zt.isEmpty();
    }

    public void zzasj() {
        zza(false, zzrh.f690AG);
    }

    public void zzask() {
        zza(true, zzsg.f764ym);
    }
}
