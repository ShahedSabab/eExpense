package com.google.android.gms.tasks;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zzro;
import com.google.android.gms.internal.zzrp;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

final class zzh<TResult> extends Task<TResult> {
    private final zzg<TResult> aMU = new zzg<>();
    private boolean aMV;
    private TResult aMW;
    private Exception aMX;
    private final Object zzako = new Object();

    private static class zza extends zzro {
        private final List<WeakReference<zzf<?>>> mListeners = new ArrayList();

        private zza(zzrp zzrp) {
            super(zzrp);
            this.f725Bf.zza("TaskOnStopCallback", (zzro) this);
        }

        public static zza zzw(Activity activity) {
            zzrp zzs = zzs(activity);
            zza zza = (zza) zzs.zza("TaskOnStopCallback", zza.class);
            return zza == null ? new zza(zzs) : zza;
        }

        @MainThread
        public void onStop() {
            synchronized (this.mListeners) {
                for (WeakReference weakReference : this.mListeners) {
                    zzf zzf = (zzf) weakReference.get();
                    if (zzf != null) {
                        zzf.cancel();
                    }
                }
                this.mListeners.clear();
            }
        }

        public <T> void zzb(zzf<T> zzf) {
            synchronized (this.mListeners) {
                this.mListeners.add(new WeakReference(zzf));
            }
        }
    }

    zzh() {
    }

    private void zzclg() {
        zzaa.zza(this.aMV, (Object) "Task is not yet complete");
    }

    private void zzclh() {
        zzaa.zza(!this.aMV, (Object) "Task is already complete");
    }

    private void zzcli() {
        synchronized (this.zzako) {
            if (this.aMV) {
                this.aMU.zza((Task<TResult>) this);
            }
        }
    }

    @NonNull
    public Task<TResult> addOnCompleteListener(@NonNull Activity activity, @NonNull OnCompleteListener<TResult> onCompleteListener) {
        zzc zzc = new zzc(TaskExecutors.MAIN_THREAD, onCompleteListener);
        this.aMU.zza((zzf<TResult>) zzc);
        zza.zzw(activity).zzb(zzc);
        zzcli();
        return this;
    }

    @NonNull
    public Task<TResult> addOnCompleteListener(@NonNull OnCompleteListener<TResult> onCompleteListener) {
        return addOnCompleteListener(TaskExecutors.MAIN_THREAD, onCompleteListener);
    }

    @NonNull
    public Task<TResult> addOnCompleteListener(@NonNull Executor executor, @NonNull OnCompleteListener<TResult> onCompleteListener) {
        this.aMU.zza((zzf<TResult>) new zzc<TResult>(executor, onCompleteListener));
        zzcli();
        return this;
    }

    @NonNull
    public Task<TResult> addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener) {
        zzd zzd = new zzd(TaskExecutors.MAIN_THREAD, onFailureListener);
        this.aMU.zza((zzf<TResult>) zzd);
        zza.zzw(activity).zzb(zzd);
        zzcli();
        return this;
    }

    @NonNull
    public Task<TResult> addOnFailureListener(@NonNull OnFailureListener onFailureListener) {
        return addOnFailureListener(TaskExecutors.MAIN_THREAD, onFailureListener);
    }

    @NonNull
    public Task<TResult> addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
        this.aMU.zza((zzf<TResult>) new zzd<TResult>(executor, onFailureListener));
        zzcli();
        return this;
    }

    @NonNull
    public Task<TResult> addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        zze zze = new zze(TaskExecutors.MAIN_THREAD, onSuccessListener);
        this.aMU.zza((zzf<TResult>) zze);
        zza.zzw(activity).zzb(zze);
        zzcli();
        return this;
    }

    @NonNull
    public Task<TResult> addOnSuccessListener(@NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        return addOnSuccessListener(TaskExecutors.MAIN_THREAD, onSuccessListener);
    }

    @NonNull
    public Task<TResult> addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        this.aMU.zza((zzf<TResult>) new zze<TResult>(executor, onSuccessListener));
        zzcli();
        return this;
    }

    @NonNull
    public <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Continuation<TResult, TContinuationResult> continuation) {
        return continueWith(TaskExecutors.MAIN_THREAD, continuation);
    }

    @NonNull
    public <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Executor executor, @NonNull Continuation<TResult, TContinuationResult> continuation) {
        zzh zzh = new zzh();
        this.aMU.zza((zzf<TResult>) new zza<TResult>(executor, continuation, zzh));
        zzcli();
        return zzh;
    }

    @NonNull
    public <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Continuation<TResult, Task<TContinuationResult>> continuation) {
        return continueWithTask(TaskExecutors.MAIN_THREAD, continuation);
    }

    @NonNull
    public <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Executor executor, @NonNull Continuation<TResult, Task<TContinuationResult>> continuation) {
        zzh zzh = new zzh();
        this.aMU.zza((zzf<TResult>) new zzb<TResult>(executor, continuation, zzh));
        zzcli();
        return zzh;
    }

    @Nullable
    public Exception getException() {
        Exception exc;
        synchronized (this.zzako) {
            exc = this.aMX;
        }
        return exc;
    }

    public TResult getResult() {
        TResult tresult;
        synchronized (this.zzako) {
            zzclg();
            if (this.aMX != null) {
                throw new RuntimeExecutionException(this.aMX);
            }
            tresult = this.aMW;
        }
        return tresult;
    }

    public <X extends Throwable> TResult getResult(@NonNull Class<X> cls) throws Throwable {
        TResult tresult;
        synchronized (this.zzako) {
            zzclg();
            if (cls.isInstance(this.aMX)) {
                throw ((Throwable) cls.cast(this.aMX));
            } else if (this.aMX != null) {
                throw new RuntimeExecutionException(this.aMX);
            } else {
                tresult = this.aMW;
            }
        }
        return tresult;
    }

    public boolean isComplete() {
        boolean z;
        synchronized (this.zzako) {
            z = this.aMV;
        }
        return z;
    }

    public boolean isSuccessful() {
        boolean z;
        synchronized (this.zzako) {
            z = this.aMV && this.aMX == null;
        }
        return z;
    }

    public void setException(@NonNull Exception exc) {
        zzaa.zzb(exc, (Object) "Exception must not be null");
        synchronized (this.zzako) {
            zzclh();
            this.aMV = true;
            this.aMX = exc;
        }
        this.aMU.zza((Task<TResult>) this);
    }

    public void setResult(TResult tresult) {
        synchronized (this.zzako) {
            zzclh();
            this.aMV = true;
            this.aMW = tresult;
        }
        this.aMU.zza((Task<TResult>) this);
    }

    public boolean trySetException(@NonNull Exception exc) {
        boolean z = true;
        zzaa.zzb(exc, (Object) "Exception must not be null");
        synchronized (this.zzako) {
            if (this.aMV) {
                z = false;
            } else {
                this.aMV = true;
                this.aMX = exc;
                this.aMU.zza((Task<TResult>) this);
            }
        }
        return z;
    }

    public boolean trySetResult(TResult tresult) {
        boolean z = true;
        synchronized (this.zzako) {
            if (this.aMV) {
                z = false;
            } else {
                this.aMV = true;
                this.aMW = tresult;
                this.aMU.zza((Task<TResult>) this);
            }
        }
        return z;
    }
}
