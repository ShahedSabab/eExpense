package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.zzb;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class zzqu implements zzrm {

    /* renamed from: xy */
    private final zzrh f571xy;
    /* access modifiers changed from: private */

    /* renamed from: zg */
    public final Lock f572zg;
    /* access modifiers changed from: private */

    /* renamed from: zj */
    public final Map<zzc<?>, com.google.android.gms.common.api.zzc<?>> f573zj = new HashMap();
    /* access modifiers changed from: private */

    /* renamed from: zk */
    public final Map<Api<?>, Integer> f574zk;
    /* access modifiers changed from: private */

    /* renamed from: zl */
    public final zzrd f575zl;
    /* access modifiers changed from: private */

    /* renamed from: zm */
    public final com.google.android.gms.common.zzc f576zm;
    /* access modifiers changed from: private */

    /* renamed from: zn */
    public final Condition f577zn;
    /* access modifiers changed from: private */

    /* renamed from: zo */
    public boolean f578zo;
    /* access modifiers changed from: private */

    /* renamed from: zp */
    public Map<zzql<?>, ConnectionResult> f579zp;
    /* access modifiers changed from: private */

    /* renamed from: zq */
    public ConnectionResult f580zq;
    private final Looper zzajy;

    private class zza implements OnFailureListener, OnSuccessListener<Void> {
        private zza() {
        }

        @Nullable
        private ConnectionResult zzash() {
            ConnectionResult connectionResult = null;
            int i = 0;
            for (Api api : zzqu.this.f574zk.keySet()) {
                ConnectionResult connectionResult2 = (ConnectionResult) zzqu.this.f579zp.get(((com.google.android.gms.common.api.zzc) zzqu.this.f573zj.get(api.zzaqv())).getApiKey());
                if (!connectionResult2.isSuccess()) {
                    int intValue = ((Integer) zzqu.this.f574zk.get(api)).intValue();
                    if (intValue != 2 && (intValue != 1 || connectionResult2.hasResolution() || zzqu.this.f576zm.isUserResolvableError(connectionResult2.getErrorCode()))) {
                        int priority = api.zzaqs().getPriority();
                        if (connectionResult != null && i <= priority) {
                            priority = i;
                            connectionResult2 = connectionResult;
                        }
                        i = priority;
                        connectionResult = connectionResult2;
                    }
                }
            }
            return connectionResult;
        }

        public void onFailure(@NonNull Exception exc) {
            zzb zzb = (zzb) exc;
            zzqu.this.f572zg.lock();
            try {
                zzqu.this.f579zp = zzb.zzara();
                zzqu.this.f580zq = zzash();
                if (zzqu.this.f580zq == null) {
                    zzqu.this.f575zl.zzn(null);
                } else {
                    zzqu.this.f578zo = false;
                    zzqu.this.f575zl.zzc(zzqu.this.f580zq);
                }
                zzqu.this.f577zn.signalAll();
            } finally {
                zzqu.this.f572zg.unlock();
            }
        }

        /* renamed from: zza */
        public void onSuccess(Void voidR) {
            zzqu.this.f572zg.lock();
            try {
                zzqu.this.f579zp = new ArrayMap(zzqu.this.f573zj.size());
                for (zzc zzc : zzqu.this.f573zj.keySet()) {
                    zzqu.this.f579zp.put(((com.google.android.gms.common.api.zzc) zzqu.this.f573zj.get(zzc)).getApiKey(), ConnectionResult.f110wO);
                }
                zzqu.this.f575zl.zzn(null);
                zzqu.this.f577zn.signalAll();
            } finally {
                zzqu.this.f572zg.unlock();
            }
        }
    }

    public zzqu(Context context, Lock lock, Looper looper, com.google.android.gms.common.zzc zzc, Map<zzc<?>, zze> map, Map<Api<?>, Integer> map2, ArrayList<zzqr> arrayList, zzrd zzrd) {
        this.f572zg = lock;
        this.zzajy = looper;
        this.f577zn = lock.newCondition();
        this.f576zm = zzc;
        this.f575zl = zzrd;
        this.f574zk = map2;
        HashMap hashMap = new HashMap();
        for (Api api : map2.keySet()) {
            hashMap.put(api.zzaqv(), api);
        }
        HashMap hashMap2 = new HashMap();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            zzqr zzqr = (zzqr) it.next();
            hashMap2.put(zzqr.f553vS, zzqr);
        }
        for (Entry entry : map.entrySet()) {
            Api api2 = (Api) hashMap.get(entry.getKey());
            this.f573zj.put((zzc) entry.getKey(), new com.google.android.gms.common.api.zzc(context, api2, looper, (zze) entry.getValue(), (zzqr) hashMap2.get(api2)) {
            });
        }
        this.f571xy = zzrh.zzatg();
    }

    public ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.f577zn.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        return isConnected() ? ConnectionResult.f110wO : this.f580zq != null ? this.f580zq : new ConnectionResult(13, null);
    }

    public ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        connect();
        long nanos = timeUnit.toNanos(j);
        while (isConnecting()) {
            if (nanos <= 0) {
                try {
                    disconnect();
                    return new ConnectionResult(14, null);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return new ConnectionResult(15, null);
                }
            } else {
                nanos = this.f577zn.awaitNanos(nanos);
            }
        }
        return isConnected() ? ConnectionResult.f110wO : this.f580zq != null ? this.f580zq : new ConnectionResult(13, null);
    }

    public void connect() {
        this.f572zg.lock();
        try {
            if (!this.f578zo) {
                this.f578zo = true;
                this.f579zp = null;
                this.f580zq = null;
                zza zza2 = new zza();
                zzsv zzsv = new zzsv(this.zzajy);
                this.f571xy.zza((Iterable<com.google.android.gms.common.api.zzc<?>>) this.f573zj.values()).addOnSuccessListener((Executor) zzsv, (OnSuccessListener<? super TResult>) zza2).addOnFailureListener((Executor) zzsv, (OnFailureListener) zza2);
                this.f572zg.unlock();
            }
        } finally {
            this.f572zg.unlock();
        }
    }

    public void disconnect() {
        this.f572zg.lock();
        try {
            this.f578zo = false;
            this.f579zp = null;
            this.f580zq = null;
            this.f577zn.signalAll();
        } finally {
            this.f572zg.unlock();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Nullable
    public ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        this.f572zg.lock();
        try {
            if (((com.google.android.gms.common.api.zzc) this.f573zj.get(api.zzaqv())).getClient().isConnected()) {
                return ConnectionResult.f110wO;
            }
            if (this.f579zp != null) {
                ConnectionResult connectionResult = (ConnectionResult) this.f579zp.get(((com.google.android.gms.common.api.zzc) this.f573zj.get(api.zzaqv())).getApiKey());
                this.f572zg.unlock();
                return connectionResult;
            }
            this.f572zg.unlock();
            return null;
        } finally {
            this.f572zg.unlock();
        }
    }

    public boolean isConnected() {
        this.f572zg.lock();
        try {
            return this.f579zp != null && this.f580zq == null;
        } finally {
            this.f572zg.unlock();
        }
    }

    public boolean isConnecting() {
        this.f572zg.lock();
        try {
            return this.f579zp == null && this.f578zo;
        } finally {
            this.f572zg.unlock();
        }
    }

    public <A extends Api.zzb, R extends Result, T extends com.google.android.gms.internal.zzqo.zza<R, A>> T zza(@NonNull T t) {
        this.f575zl.f652Ap.zzb(t);
        return ((com.google.android.gms.common.api.zzc) this.f573zj.get(t.zzaqv())).doRead(t);
    }

    public boolean zza(zzsa zzsa) {
        throw new UnsupportedOperationException();
    }

    public void zzard() {
        throw new UnsupportedOperationException();
    }

    public void zzarz() {
    }

    public <A extends Api.zzb, T extends com.google.android.gms.internal.zzqo.zza<? extends Result, A>> T zzb(@NonNull T t) {
        this.f575zl.f652Ap.zzb(t);
        return ((com.google.android.gms.common.api.zzc) this.f573zj.get(t.zzaqv())).doWrite(t);
    }
}
