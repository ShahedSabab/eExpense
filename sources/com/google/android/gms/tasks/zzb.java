package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzb<TResult, TContinuationResult> implements OnFailureListener, OnSuccessListener<TContinuationResult>, zzf<TResult> {
    private final Executor aEQ;
    /* access modifiers changed from: private */
    public final Continuation<TResult, Task<TContinuationResult>> aMF;
    /* access modifiers changed from: private */
    public final zzh<TContinuationResult> aMG;

    public zzb(@NonNull Executor executor, @NonNull Continuation<TResult, Task<TContinuationResult>> continuation, @NonNull zzh<TContinuationResult> zzh) {
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
                    Task task = (Task) zzb.this.aMF.then(task);
                    if (task == null) {
                        zzb.this.onFailure(new NullPointerException("Continuation returned null"));
                        return;
                    }
                    task.addOnSuccessListener(TaskExecutors.aMT, (OnSuccessListener<? super TResult>) zzb.this);
                    task.addOnFailureListener(TaskExecutors.aMT, (OnFailureListener) zzb.this);
                } catch (RuntimeExecutionException e) {
                    if (e.getCause() instanceof Exception) {
                        zzb.this.aMG.setException((Exception) e.getCause());
                    } else {
                        zzb.this.aMG.setException(e);
                    }
                } catch (Exception e2) {
                    zzb.this.aMG.setException(e2);
                }
            }
        });
    }

    public void onFailure(@NonNull Exception exc) {
        this.aMG.setException(exc);
    }

    public void onSuccess(TContinuationResult tcontinuationresult) {
        this.aMG.setResult(tcontinuationresult);
    }
}
