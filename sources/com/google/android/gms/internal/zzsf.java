package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzaa;
import java.lang.ref.WeakReference;

public class zzsf<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
    /* access modifiers changed from: private */

    /* renamed from: BA */
    public final zza f751BA;

    /* renamed from: BB */
    private boolean f752BB = false;
    /* access modifiers changed from: private */

    /* renamed from: Bv */
    public ResultTransform<? super R, ? extends Result> f753Bv = null;
    /* access modifiers changed from: private */

    /* renamed from: Bw */
    public zzsf<? extends Result> f754Bw = null;

    /* renamed from: Bx */
    private volatile ResultCallbacks<? super R> f755Bx = null;

    /* renamed from: By */
    private PendingResult<R> f756By = null;

    /* renamed from: Bz */
    private Status f757Bz = null;
    /* access modifiers changed from: private */

    /* renamed from: yH */
    public final Object f758yH = new Object();
    /* access modifiers changed from: private */

    /* renamed from: yJ */
    public final WeakReference<GoogleApiClient> f759yJ;

    private final class zza extends Handler {
        public zza(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    PendingResult pendingResult = (PendingResult) message.obj;
                    synchronized (zzsf.this.f758yH) {
                        if (pendingResult == null) {
                            zzsf.this.f754Bw.zzad(new Status(13, "Transform returned null"));
                        } else if (pendingResult instanceof zzrz) {
                            zzsf.this.f754Bw.zzad(((zzrz) pendingResult).getStatus());
                        } else {
                            zzsf.this.f754Bw.zza(pendingResult);
                        }
                    }
                    return;
                case 1:
                    RuntimeException runtimeException = (RuntimeException) message.obj;
                    String str = "TransformedResultImpl";
                    String str2 = "Runtime exception on the transformation worker thread: ";
                    String valueOf = String.valueOf(runtimeException.getMessage());
                    Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                    throw runtimeException;
                default:
                    Log.e("TransformedResultImpl", "TransformationResultHandler received unknown message type: " + message.what);
                    return;
            }
        }
    }

    public zzsf(WeakReference<GoogleApiClient> weakReference) {
        zzaa.zzb(weakReference, (Object) "GoogleApiClient reference must not be null");
        this.f759yJ = weakReference;
        GoogleApiClient googleApiClient = (GoogleApiClient) this.f759yJ.get();
        this.f751BA = new zza(googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
    }

    /* access modifiers changed from: private */
    public void zzad(Status status) {
        synchronized (this.f758yH) {
            this.f757Bz = status;
            zzae(this.f757Bz);
        }
    }

    private void zzae(Status status) {
        synchronized (this.f758yH) {
            if (this.f753Bv != null) {
                Status onFailure = this.f753Bv.onFailure(status);
                zzaa.zzb(onFailure, (Object) "onFailure must not return null");
                this.f754Bw.zzad(onFailure);
            } else if (zzaue()) {
                this.f755Bx.onFailure(status);
            }
        }
    }

    private void zzauc() {
        if (this.f753Bv != null || this.f755Bx != null) {
            GoogleApiClient googleApiClient = (GoogleApiClient) this.f759yJ.get();
            if (!(this.f752BB || this.f753Bv == null || googleApiClient == null)) {
                googleApiClient.zza(this);
                this.f752BB = true;
            }
            if (this.f757Bz != null) {
                zzae(this.f757Bz);
            } else if (this.f756By != null) {
                this.f756By.setResultCallback(this);
            }
        }
    }

    private boolean zzaue() {
        return (this.f755Bx == null || ((GoogleApiClient) this.f759yJ.get()) == null) ? false : true;
    }

    /* access modifiers changed from: private */
    public void zze(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                String valueOf = String.valueOf(result);
                Log.w("TransformedResultImpl", new StringBuilder(String.valueOf(valueOf).length() + 18).append("Unable to release ").append(valueOf).toString(), e);
            }
        }
    }

    public void andFinally(@NonNull ResultCallbacks<? super R> resultCallbacks) {
        boolean z = true;
        synchronized (this.f758yH) {
            zzaa.zza(this.f755Bx == null, (Object) "Cannot call andFinally() twice.");
            if (this.f753Bv != null) {
                z = false;
            }
            zzaa.zza(z, (Object) "Cannot call then() and andFinally() on the same TransformedResult.");
            this.f755Bx = resultCallbacks;
            zzauc();
        }
    }

    public void onResult(final R r) {
        synchronized (this.f758yH) {
            if (!r.getStatus().isSuccess()) {
                zzad(r.getStatus());
                zze((Result) r);
            } else if (this.f753Bv != null) {
                zzry.zzatf().submit(new Runnable() {
                    @WorkerThread
                    public void run() {
                        try {
                            zzqq.f538yG.set(Boolean.valueOf(true));
                            zzsf.this.f751BA.sendMessage(zzsf.this.f751BA.obtainMessage(0, zzsf.this.f753Bv.onSuccess(r)));
                            zzqq.f538yG.set(Boolean.valueOf(false));
                            zzsf.this.zze(r);
                            GoogleApiClient googleApiClient = (GoogleApiClient) zzsf.this.f759yJ.get();
                            if (googleApiClient != null) {
                                googleApiClient.zzb(zzsf.this);
                            }
                        } catch (RuntimeException e) {
                            zzsf.this.f751BA.sendMessage(zzsf.this.f751BA.obtainMessage(1, e));
                            zzqq.f538yG.set(Boolean.valueOf(false));
                            zzsf.this.zze(r);
                            GoogleApiClient googleApiClient2 = (GoogleApiClient) zzsf.this.f759yJ.get();
                            if (googleApiClient2 != null) {
                                googleApiClient2.zzb(zzsf.this);
                            }
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            zzqq.f538yG.set(Boolean.valueOf(false));
                            zzsf.this.zze(r);
                            GoogleApiClient googleApiClient3 = (GoogleApiClient) zzsf.this.f759yJ.get();
                            if (googleApiClient3 != null) {
                                googleApiClient3.zzb(zzsf.this);
                            }
                            throw th2;
                        }
                    }
                });
            } else if (zzaue()) {
                this.f755Bx.onSuccess(r);
            }
        }
    }

    @NonNull
    public <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> resultTransform) {
        zzsf<? extends Result> zzsf;
        boolean z = true;
        synchronized (this.f758yH) {
            zzaa.zza(this.f753Bv == null, (Object) "Cannot call then() twice.");
            if (this.f755Bx != null) {
                z = false;
            }
            zzaa.zza(z, (Object) "Cannot call then() and andFinally() on the same TransformedResult.");
            this.f753Bv = resultTransform;
            zzsf = new zzsf<>(this.f759yJ);
            this.f754Bw = zzsf;
            zzauc();
        }
        return zzsf;
    }

    public void zza(PendingResult<?> pendingResult) {
        synchronized (this.f758yH) {
            this.f756By = pendingResult;
            zzauc();
        }
    }

    /* access modifiers changed from: 0000 */
    public void zzaud() {
        this.f755Bx = null;
    }
}
