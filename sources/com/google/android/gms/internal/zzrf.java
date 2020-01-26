package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzf;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class zzrf implements zzqs, zzrm {

    /* renamed from: AA */
    private ConnectionResult f673AA = null;

    /* renamed from: AB */
    int f674AB;

    /* renamed from: AC */
    final com.google.android.gms.internal.zzrm.zza f675AC;

    /* renamed from: Aj */
    final Map<zzc<?>, zze> f676Aj;

    /* renamed from: Aw */
    private final Condition f677Aw;

    /* renamed from: Ax */
    private final zzb f678Ax;

    /* renamed from: Ay */
    final Map<zzc<?>, ConnectionResult> f679Ay = new HashMap();
    /* access modifiers changed from: private */

    /* renamed from: Az */
    public volatile zzre f680Az;
    private final Context mContext;

    /* renamed from: xQ */
    final com.google.android.gms.common.api.Api.zza<? extends zzxp, zzxq> f681xQ;

    /* renamed from: yW */
    final zzrd f682yW;

    /* renamed from: zP */
    final zzf f683zP;
    /* access modifiers changed from: private */

    /* renamed from: zg */
    public final Lock f684zg;

    /* renamed from: zk */
    final Map<Api<?>, Integer> f685zk;

    /* renamed from: zm */
    private final com.google.android.gms.common.zzc f686zm;

    static abstract class zza {

        /* renamed from: AD */
        private final zzre f687AD;

        protected zza(zzre zzre) {
            this.f687AD = zzre;
        }

        /* access modifiers changed from: protected */
        public abstract void zzaso();

        public final void zzc(zzrf zzrf) {
            zzrf.f684zg.lock();
            try {
                if (zzrf.f680Az == this.f687AD) {
                    zzaso();
                    zzrf.f684zg.unlock();
                }
            } finally {
                zzrf.f684zg.unlock();
            }
        }
    }

    final class zzb extends Handler {
        zzb(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    ((zza) message.obj).zzc(zzrf.this);
                    return;
                case 2:
                    throw ((RuntimeException) message.obj);
                default:
                    Log.w("GACStateManager", "Unknown message id: " + message.what);
                    return;
            }
        }
    }

    public zzrf(Context context, zzrd zzrd, Lock lock, Looper looper, com.google.android.gms.common.zzc zzc, Map<zzc<?>, zze> map, zzf zzf, Map<Api<?>, Integer> map2, com.google.android.gms.common.api.Api.zza<? extends zzxp, zzxq> zza2, ArrayList<zzqr> arrayList, com.google.android.gms.internal.zzrm.zza zza3) {
        this.mContext = context;
        this.f684zg = lock;
        this.f686zm = zzc;
        this.f676Aj = map;
        this.f683zP = zzf;
        this.f685zk = map2;
        this.f681xQ = zza2;
        this.f682yW = zzrd;
        this.f675AC = zza3;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((zzqr) it.next()).zza(this);
        }
        this.f678Ax = new zzb(looper);
        this.f677Aw = lock.newCondition();
        this.f680Az = new zzrc(this);
    }

    public ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.f677Aw.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        return isConnected() ? ConnectionResult.f110wO : this.f673AA != null ? this.f673AA : new ConnectionResult(13, null);
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
                nanos = this.f677Aw.awaitNanos(nanos);
            }
        }
        return isConnected() ? ConnectionResult.f110wO : this.f673AA != null ? this.f673AA : new ConnectionResult(13, null);
    }

    public void connect() {
        this.f680Az.connect();
    }

    public void disconnect() {
        if (this.f680Az.disconnect()) {
            this.f679Ay.clear();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String concat = String.valueOf(str).concat("  ");
        printWriter.append(str).append("mState=").println(this.f680Az);
        for (Api api : this.f685zk.keySet()) {
            printWriter.append(str).append(api.getName()).println(":");
            ((zze) this.f676Aj.get(api.zzaqv())).dump(concat, fileDescriptor, printWriter, strArr);
        }
    }

    @Nullable
    public ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        zzc zzaqv = api.zzaqv();
        if (this.f676Aj.containsKey(zzaqv)) {
            if (((zze) this.f676Aj.get(zzaqv)).isConnected()) {
                return ConnectionResult.f110wO;
            }
            if (this.f679Ay.containsKey(zzaqv)) {
                return (ConnectionResult) this.f679Ay.get(zzaqv);
            }
        }
        return null;
    }

    public boolean isConnected() {
        return this.f680Az instanceof zzra;
    }

    public boolean isConnecting() {
        return this.f680Az instanceof zzrb;
    }

    public void onConnected(@Nullable Bundle bundle) {
        this.f684zg.lock();
        try {
            this.f680Az.onConnected(bundle);
        } finally {
            this.f684zg.unlock();
        }
    }

    public void onConnectionSuspended(int i) {
        this.f684zg.lock();
        try {
            this.f680Az.onConnectionSuspended(i);
        } finally {
            this.f684zg.unlock();
        }
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, R extends Result, T extends com.google.android.gms.internal.zzqo.zza<R, A>> T zza(@NonNull T t) {
        t.zzarv();
        return this.f680Az.zza(t);
    }

    public void zza(@NonNull ConnectionResult connectionResult, @NonNull Api<?> api, int i) {
        this.f684zg.lock();
        try {
            this.f680Az.zza(connectionResult, api, i);
        } finally {
            this.f684zg.unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    public void zza(zza zza2) {
        this.f678Ax.sendMessage(this.f678Ax.obtainMessage(1, zza2));
    }

    /* access modifiers changed from: 0000 */
    public void zza(RuntimeException runtimeException) {
        this.f678Ax.sendMessage(this.f678Ax.obtainMessage(2, runtimeException));
    }

    public boolean zza(zzsa zzsa) {
        return false;
    }

    public void zzard() {
    }

    public void zzarz() {
        if (isConnected()) {
            ((zzra) this.f680Az).zzasn();
        }
    }

    /* access modifiers changed from: 0000 */
    public void zzatc() {
        this.f684zg.lock();
        try {
            this.f680Az = new zzrb(this, this.f683zP, this.f685zk, this.f686zm, this.f681xQ, this.f684zg, this.mContext);
            this.f680Az.begin();
            this.f677Aw.signalAll();
        } finally {
            this.f684zg.unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    public void zzatd() {
        this.f684zg.lock();
        try {
            this.f682yW.zzasz();
            this.f680Az = new zzra(this);
            this.f680Az.begin();
            this.f677Aw.signalAll();
        } finally {
            this.f684zg.unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    public void zzate() {
        for (zze disconnect : this.f676Aj.values()) {
            disconnect.disconnect();
        }
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, T extends com.google.android.gms.internal.zzqo.zza<? extends Result, A>> T zzb(@NonNull T t) {
        t.zzarv();
        return this.f680Az.zzb(t);
    }

    /* access modifiers changed from: 0000 */
    public void zzh(ConnectionResult connectionResult) {
        this.f684zg.lock();
        try {
            this.f673AA = connectionResult;
            this.f680Az = new zzrc(this);
            this.f680Az.begin();
            this.f677Aw.signalAll();
        } finally {
            this.f684zg.unlock();
        }
    }
}
