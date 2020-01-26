package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzc<TResult> implements zzf<TResult> {
    private final Executor aEQ;
    /* access modifiers changed from: private */
    public OnCompleteListener<TResult> aMK;
    /* access modifiers changed from: private */
    public final Object zzako = new Object();

    public zzc(@NonNull Executor executor, @NonNull OnCompleteListener<TResult> onCompleteListener) {
        this.aEQ = executor;
        this.aMK = onCompleteListener;
    }

    public void cancel() {
        synchronized (this.zzako) {
            this.aMK = null;
        }
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        synchronized (this.zzako) {
            if (this.aMK != null) {
                this.aEQ.execute(new Runnable() {
                    public void run() {
                        synchronized (zzc.this.zzako) {
                            if (zzc.this.aMK != null) {
                                zzc.this.aMK.onComplete(task);
                            }
                        }
                    }
                });
            }
        }
    }
}
