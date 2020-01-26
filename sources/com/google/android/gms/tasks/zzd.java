package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzd<TResult> implements zzf<TResult> {
    private final Executor aEQ;
    /* access modifiers changed from: private */
    public OnFailureListener aMM;
    /* access modifiers changed from: private */
    public final Object zzako = new Object();

    public zzd(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
        this.aEQ = executor;
        this.aMM = onFailureListener;
    }

    public void cancel() {
        synchronized (this.zzako) {
            this.aMM = null;
        }
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        if (!task.isSuccessful()) {
            synchronized (this.zzako) {
                if (this.aMM != null) {
                    this.aEQ.execute(new Runnable() {
                        public void run() {
                            synchronized (zzd.this.zzako) {
                                if (zzd.this.aMM != null) {
                                    zzd.this.aMM.onFailure(task.getException());
                                }
                            }
                        }
                    });
                }
            }
        }
    }
}
