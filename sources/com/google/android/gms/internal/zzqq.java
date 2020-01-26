package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzq;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zzqq<R extends Result> extends PendingResult<R> {

    /* renamed from: yG */
    static final ThreadLocal<Boolean> f538yG = new ThreadLocal<Boolean>() {
        /* access modifiers changed from: protected */
        /* renamed from: zzarw */
        public Boolean initialValue() {
            return Boolean.valueOf(false);
        }
    };
    /* access modifiers changed from: private */

    /* renamed from: xV */
    public R f539xV;

    /* renamed from: yH */
    private final Object f540yH;

    /* renamed from: yI */
    protected final zza<R> f541yI;

    /* renamed from: yJ */
    protected final WeakReference<GoogleApiClient> f542yJ;

    /* renamed from: yK */
    private final ArrayList<com.google.android.gms.common.api.PendingResult.zza> f543yK;

    /* renamed from: yL */
    private ResultCallback<? super R> f544yL;

    /* renamed from: yM */
    private final AtomicReference<zzb> f545yM;

    /* renamed from: yN */
    private zzb f546yN;

    /* renamed from: yO */
    private volatile boolean f547yO;

    /* renamed from: yP */
    private boolean f548yP;

    /* renamed from: yQ */
    private zzq f549yQ;

    /* renamed from: yR */
    private volatile zzsf<R> f550yR;

    /* renamed from: yS */
    private boolean f551yS;
    private boolean zzak;
    private final CountDownLatch zzank;

    public static class zza<R extends Result> extends Handler {
        public zza() {
            this(Looper.getMainLooper());
        }

        public zza(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Pair pair = (Pair) message.obj;
                    zzb((ResultCallback) pair.first, (Result) pair.second);
                    return;
                case 2:
                    ((zzqq) message.obj).zzab(Status.f161yc);
                    return;
                default:
                    Log.wtf("BasePendingResult", "Don't know how to handle message: " + message.what, new Exception());
                    return;
            }
        }

        public void zza(ResultCallback<? super R> resultCallback, R r) {
            sendMessage(obtainMessage(1, new Pair(resultCallback, r)));
        }

        public void zza(zzqq<R> zzqq, long j) {
            sendMessageDelayed(obtainMessage(2, zzqq), j);
        }

        public void zzarx() {
            removeMessages(2);
        }

        /* access modifiers changed from: protected */
        public void zzb(ResultCallback<? super R> resultCallback, R r) {
            try {
                resultCallback.onResult(r);
            } catch (RuntimeException e) {
                zzqq.zze(r);
                throw e;
            }
        }
    }

    private final class zzb {
        private zzb() {
        }

        /* access modifiers changed from: protected */
        public void finalize() throws Throwable {
            zzqq.zze(zzqq.this.f539xV);
            super.finalize();
        }
    }

    @Deprecated
    zzqq() {
        this.f540yH = new Object();
        this.zzank = new CountDownLatch(1);
        this.f543yK = new ArrayList<>();
        this.f545yM = new AtomicReference<>();
        this.f551yS = false;
        this.f541yI = new zza<>(Looper.getMainLooper());
        this.f542yJ = new WeakReference<>(null);
    }

    @Deprecated
    protected zzqq(Looper looper) {
        this.f540yH = new Object();
        this.zzank = new CountDownLatch(1);
        this.f543yK = new ArrayList<>();
        this.f545yM = new AtomicReference<>();
        this.f551yS = false;
        this.f541yI = new zza<>(looper);
        this.f542yJ = new WeakReference<>(null);
    }

    protected zzqq(GoogleApiClient googleApiClient) {
        this.f540yH = new Object();
        this.zzank = new CountDownLatch(1);
        this.f543yK = new ArrayList<>();
        this.f545yM = new AtomicReference<>();
        this.f551yS = false;
        this.f541yI = new zza<>(googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
        this.f542yJ = new WeakReference<>(googleApiClient);
    }

    private R get() {
        R r;
        boolean z = true;
        synchronized (this.f540yH) {
            if (this.f547yO) {
                z = false;
            }
            zzaa.zza(z, (Object) "Result has already been consumed.");
            zzaa.zza(isReady(), (Object) "Result is not ready.");
            r = this.f539xV;
            this.f539xV = null;
            this.f544yL = null;
            this.f547yO = true;
        }
        zzart();
        return r;
    }

    private void zzart() {
        zzb zzb2 = (zzb) this.f545yM.getAndSet(null);
        if (zzb2 != null) {
            zzb2.zzc(this);
        }
    }

    private void zzd(R r) {
        this.f539xV = r;
        this.f549yQ = null;
        this.zzank.countDown();
        Status status = this.f539xV.getStatus();
        if (this.zzak) {
            this.f544yL = null;
        } else if (this.f544yL != null) {
            this.f541yI.zzarx();
            this.f541yI.zza(this.f544yL, get());
        } else if (this.f539xV instanceof Releasable) {
            this.f546yN = new zzb();
        }
        Iterator it = this.f543yK.iterator();
        while (it.hasNext()) {
            ((com.google.android.gms.common.api.PendingResult.zza) it.next()).zzx(status);
        }
        this.f543yK.clear();
    }

    public static void zze(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                String valueOf = String.valueOf(result);
                Log.w("BasePendingResult", new StringBuilder(String.valueOf(valueOf).length() + 18).append("Unable to release ").append(valueOf).toString(), e);
            }
        }
    }

    public final R await() {
        boolean z = true;
        zzaa.zza(Looper.myLooper() != Looper.getMainLooper(), (Object) "await must not be called on the UI thread");
        zzaa.zza(!this.f547yO, (Object) "Result has already been consumed");
        if (this.f550yR != null) {
            z = false;
        }
        zzaa.zza(z, (Object) "Cannot await if then() has been called.");
        try {
            this.zzank.await();
        } catch (InterruptedException e) {
            zzab(Status.f159ya);
        }
        zzaa.zza(isReady(), (Object) "Result is not ready.");
        return get();
    }

    public final R await(long j, TimeUnit timeUnit) {
        boolean z = true;
        zzaa.zza(j <= 0 || Looper.myLooper() != Looper.getMainLooper(), (Object) "await must not be called on the UI thread when time is greater than zero.");
        zzaa.zza(!this.f547yO, (Object) "Result has already been consumed.");
        if (this.f550yR != null) {
            z = false;
        }
        zzaa.zza(z, (Object) "Cannot await if then() has been called.");
        try {
            if (!this.zzank.await(j, timeUnit)) {
                zzab(Status.f161yc);
            }
        } catch (InterruptedException e) {
            zzab(Status.f159ya);
        }
        zzaa.zza(isReady(), (Object) "Result is not ready.");
        return get();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void cancel() {
        /*
            r2 = this;
            java.lang.Object r1 = r2.f540yH
            monitor-enter(r1)
            boolean r0 = r2.zzak     // Catch:{ all -> 0x0029 }
            if (r0 != 0) goto L_0x000b
            boolean r0 = r2.f547yO     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x000d
        L_0x000b:
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
        L_0x000c:
            return
        L_0x000d:
            com.google.android.gms.common.internal.zzq r0 = r2.f549yQ     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x0016
            com.google.android.gms.common.internal.zzq r0 = r2.f549yQ     // Catch:{ RemoteException -> 0x002c }
            r0.cancel()     // Catch:{ RemoteException -> 0x002c }
        L_0x0016:
            R r0 = r2.f539xV     // Catch:{ all -> 0x0029 }
            zze(r0)     // Catch:{ all -> 0x0029 }
            r0 = 1
            r2.zzak = r0     // Catch:{ all -> 0x0029 }
            com.google.android.gms.common.api.Status r0 = com.google.android.gms.common.api.Status.f162yd     // Catch:{ all -> 0x0029 }
            com.google.android.gms.common.api.Result r0 = r2.zzc(r0)     // Catch:{ all -> 0x0029 }
            r2.zzd(r0)     // Catch:{ all -> 0x0029 }
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
            goto L_0x000c
        L_0x0029:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
            throw r0
        L_0x002c:
            r0 = move-exception
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzqq.cancel():void");
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this.f540yH) {
            z = this.zzak;
        }
        return z;
    }

    public final boolean isReady() {
        return this.zzank.getCount() == 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<? super R> r6) {
        /*
            r5 = this;
            r0 = 1
            r1 = 0
            java.lang.Object r3 = r5.f540yH
            monitor-enter(r3)
            if (r6 != 0) goto L_0x000c
            r0 = 0
            r5.f544yL = r0     // Catch:{ all -> 0x0027 }
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
        L_0x000b:
            return
        L_0x000c:
            boolean r2 = r5.f547yO     // Catch:{ all -> 0x0027 }
            if (r2 != 0) goto L_0x002a
            r2 = r0
        L_0x0011:
            java.lang.String r4 = "Result has already been consumed."
            com.google.android.gms.common.internal.zzaa.zza(r2, r4)     // Catch:{ all -> 0x0027 }
            com.google.android.gms.internal.zzsf<R> r2 = r5.f550yR     // Catch:{ all -> 0x0027 }
            if (r2 != 0) goto L_0x002c
        L_0x001a:
            java.lang.String r1 = "Cannot set callbacks if then() has been called."
            com.google.android.gms.common.internal.zzaa.zza(r0, r1)     // Catch:{ all -> 0x0027 }
            boolean r0 = r5.isCanceled()     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x002e
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            goto L_0x000b
        L_0x0027:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            throw r0
        L_0x002a:
            r2 = r1
            goto L_0x0011
        L_0x002c:
            r0 = r1
            goto L_0x001a
        L_0x002e:
            boolean r0 = r5.isReady()     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x003f
            com.google.android.gms.internal.zzqq$zza<R> r0 = r5.f541yI     // Catch:{ all -> 0x0027 }
            com.google.android.gms.common.api.Result r1 = r5.get()     // Catch:{ all -> 0x0027 }
            r0.zza(r6, r1)     // Catch:{ all -> 0x0027 }
        L_0x003d:
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            goto L_0x000b
        L_0x003f:
            r5.f544yL = r6     // Catch:{ all -> 0x0027 }
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzqq.setResultCallback(com.google.android.gms.common.api.ResultCallback):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<? super R> r7, long r8, java.util.concurrent.TimeUnit r10) {
        /*
            r6 = this;
            r0 = 1
            r1 = 0
            java.lang.Object r3 = r6.f540yH
            monitor-enter(r3)
            if (r7 != 0) goto L_0x000c
            r0 = 0
            r6.f544yL = r0     // Catch:{ all -> 0x0027 }
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
        L_0x000b:
            return
        L_0x000c:
            boolean r2 = r6.f547yO     // Catch:{ all -> 0x0027 }
            if (r2 != 0) goto L_0x002a
            r2 = r0
        L_0x0011:
            java.lang.String r4 = "Result has already been consumed."
            com.google.android.gms.common.internal.zzaa.zza(r2, r4)     // Catch:{ all -> 0x0027 }
            com.google.android.gms.internal.zzsf<R> r2 = r6.f550yR     // Catch:{ all -> 0x0027 }
            if (r2 != 0) goto L_0x002c
        L_0x001a:
            java.lang.String r1 = "Cannot set callbacks if then() has been called."
            com.google.android.gms.common.internal.zzaa.zza(r0, r1)     // Catch:{ all -> 0x0027 }
            boolean r0 = r6.isCanceled()     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x002e
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            goto L_0x000b
        L_0x0027:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            throw r0
        L_0x002a:
            r2 = r1
            goto L_0x0011
        L_0x002c:
            r0 = r1
            goto L_0x001a
        L_0x002e:
            boolean r0 = r6.isReady()     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x003f
            com.google.android.gms.internal.zzqq$zza<R> r0 = r6.f541yI     // Catch:{ all -> 0x0027 }
            com.google.android.gms.common.api.Result r1 = r6.get()     // Catch:{ all -> 0x0027 }
            r0.zza(r7, r1)     // Catch:{ all -> 0x0027 }
        L_0x003d:
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            goto L_0x000b
        L_0x003f:
            r6.f544yL = r7     // Catch:{ all -> 0x0027 }
            com.google.android.gms.internal.zzqq$zza<R> r0 = r6.f541yI     // Catch:{ all -> 0x0027 }
            long r4 = r10.toMillis(r8)     // Catch:{ all -> 0x0027 }
            r0.zza(r6, r4)     // Catch:{ all -> 0x0027 }
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzqq.setResultCallback(com.google.android.gms.common.api.ResultCallback, long, java.util.concurrent.TimeUnit):void");
    }

    public <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> resultTransform) {
        TransformedResult<S> then;
        boolean z = true;
        zzaa.zza(!this.f547yO, (Object) "Result has already been consumed.");
        synchronized (this.f540yH) {
            zzaa.zza(this.f550yR == null, (Object) "Cannot call then() twice.");
            if (this.f544yL != null) {
                z = false;
            }
            zzaa.zza(z, (Object) "Cannot call then() if callbacks are set.");
            this.f551yS = true;
            this.f550yR = new zzsf<>(this.f542yJ);
            then = this.f550yR.then(resultTransform);
            if (isReady()) {
                this.f541yI.zza((ResultCallback<? super R>) this.f550yR, get());
            } else {
                this.f544yL = this.f550yR;
            }
        }
        return then;
    }

    public final void zza(com.google.android.gms.common.api.PendingResult.zza zza2) {
        boolean z = true;
        zzaa.zza(!this.f547yO, (Object) "Result has already been consumed.");
        if (zza2 == null) {
            z = false;
        }
        zzaa.zzb(z, (Object) "Callback cannot be null.");
        synchronized (this.f540yH) {
            if (isReady()) {
                zza2.zzx(this.f539xV.getStatus());
            } else {
                this.f543yK.add(zza2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(zzq zzq) {
        synchronized (this.f540yH) {
            this.f549yQ = zzq;
        }
    }

    public void zza(zzb zzb2) {
        this.f545yM.set(zzb2);
    }

    public final void zzab(Status status) {
        synchronized (this.f540yH) {
            if (!isReady()) {
                zzc((R) zzc(status));
                this.f548yP = true;
            }
        }
    }

    public Integer zzarh() {
        return null;
    }

    public boolean zzars() {
        boolean isCanceled;
        synchronized (this.f540yH) {
            if (((GoogleApiClient) this.f542yJ.get()) == null || !this.f551yS) {
                cancel();
            }
            isCanceled = isCanceled();
        }
        return isCanceled;
    }

    public void zzaru() {
        setResultCallback(null);
    }

    public void zzarv() {
        this.f551yS = this.f551yS || ((Boolean) f538yG.get()).booleanValue();
    }

    /* access modifiers changed from: protected */
    public abstract R zzc(Status status);

    public final void zzc(R r) {
        boolean z = true;
        synchronized (this.f540yH) {
            if (this.f548yP || this.zzak) {
                zze(r);
                return;
            }
            if (isReady()) {
            }
            zzaa.zza(!isReady(), (Object) "Results have already been set");
            if (this.f547yO) {
                z = false;
            }
            zzaa.zza(z, (Object) "Result has already been consumed");
            zzd(r);
        }
    }
}
