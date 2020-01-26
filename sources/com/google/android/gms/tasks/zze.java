package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zze<TResult> implements zzf<TResult> {
    private final Executor aEQ;
    /* access modifiers changed from: private */
    public OnSuccessListener<? super TResult> aMO;
    /* access modifiers changed from: private */
    public final Object zzako = new Object();

    public zze(@NonNull Executor executor, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        this.aEQ = executor;
        this.aMO = onSuccessListener;
    }

    public void cancel() {
        synchronized (this.zzako) {
            this.aMO = null;
        }
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        if (task.isSuccessful()) {
            synchronized (this.zzako) {
                if (this.aMO != null) {
                    this.aEQ.execute(new Runnable() {
                        public void run() {
                            synchronized (zze.this.zzako) {
                                if (zze.this.aMO != null) {
                                    zze.this.aMO.onSuccess(task.getResult());
                                }
                            }
                        }
                    });
                }
            }
        }
    }
}
