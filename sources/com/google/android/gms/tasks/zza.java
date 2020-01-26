package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zza<TResult, TContinuationResult> implements zzf<TResult> {
    private final Executor aEQ;
    /* access modifiers changed from: private */
    public final Continuation<TResult, TContinuationResult> aMF;
    /* access modifiers changed from: private */
    public final zzh<TContinuationResult> aMG;

    public zza(@NonNull Executor executor, @NonNull Continuation<TResult, TContinuationResult> continuation, @NonNull zzh<TContinuationResult> zzh) {
        this.aEQ = executor;
        this.aMF = continuation;
        this.aMG = zzh;
    }

    public void cancel() {
        throw new UnsupportedOperationException();
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        this.aEQ.execute(new Runnable() {
            public void run() {
                try {
                    zza.this.aMG.setResult(zza.this.aMF.then(task));
                } catch (RuntimeExecutionException e) {
                    if (e.getCause() instanceof Exception) {
                        zza.this.aMG.setException((Exception) e.getCause());
                    } else {
                        zza.this.aMG.setException(e);
                    }
                } catch (Exception e2) {
                    zza.this.aMG.setException(e2);
                }
            }
        });
    }
}
