package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzf;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

final class zzqt implements zzrm {
    private final Context mContext;

    /* renamed from: yW */
    private final zzrd f556yW;
    /* access modifiers changed from: private */

    /* renamed from: yX */
    public final zzrf f557yX;
    /* access modifiers changed from: private */

    /* renamed from: yY */
    public final zzrf f558yY;

    /* renamed from: yZ */
    private final Map<zzc<?>, zzrf> f559yZ;

    /* renamed from: za */
    private final Set<zzsa> f560za = Collections.newSetFromMap(new WeakHashMap());

    /* renamed from: zb */
    private final zze f561zb;

    /* renamed from: zc */
    private Bundle f562zc;
    /* access modifiers changed from: private */

    /* renamed from: zd */
    public ConnectionResult f563zd = null;
    /* access modifiers changed from: private */

    /* renamed from: ze */
    public ConnectionResult f564ze = null;
    /* access modifiers changed from: private */

    /* renamed from: zf */
    public boolean f565zf = false;
    /* access modifiers changed from: private */

    /* renamed from: zg */
    public final Lock f566zg;

    /* renamed from: zh */
    private int f567zh = 0;
    private final Looper zzajy;

    private class zza implements com.google.android.gms.internal.zzrm.zza {
        private zza() {
        }

        public void zzc(int i, boolean z) {
            zzqt.this.f566zg.lock();
            try {
                if (zzqt.this.f565zf || zzqt.this.f564ze == null || !zzqt.this.f564ze.isSuccess()) {
                    zzqt.this.f565zf = false;
                    zzqt.this.zzb(i, z);
                    return;
                }
                zzqt.this.f565zf = true;
                zzqt.this.f558yY.onConnectionSuspended(i);
                zzqt.this.f566zg.unlock();
            } finally {
                zzqt.this.f566zg.unlock();
            }
        }

        public void zzc(@NonNull ConnectionResult connectionResult) {
            zzqt.this.f566zg.lock();
            try {
                zzqt.this.f563zd = connectionResult;
                zzqt.this.zzasc();
            } finally {
                zzqt.this.f566zg.unlock();
            }
        }

        public void zzn(@Nullable Bundle bundle) {
            zzqt.this.f566zg.lock();
            try {
                zzqt.this.zzm(bundle);
                zzqt.this.f563zd = ConnectionResult.f110wO;
                zzqt.this.zzasc();
            } finally {
                zzqt.this.f566zg.unlock();
            }
        }
    }

    private class zzb implements com.google.android.gms.internal.zzrm.zza {
        private zzb() {
        }

        public void zzc(int i, boolean z) {
            zzqt.this.f566zg.lock();
            try {
                if (zzqt.this.f565zf) {
                    zzqt.this.f565zf = false;
                    zzqt.this.zzb(i, z);
                    return;
                }
                zzqt.this.f565zf = true;
                zzqt.this.f557yX.onConnectionSuspended(i);
                zzqt.this.f566zg.unlock();
            } finally {
                zzqt.this.f566zg.unlock();
            }
        }

        public void zzc(@NonNull ConnectionResult connectionResult) {
            zzqt.this.f566zg.lock();
            try {
                zzqt.this.f564ze = connectionResult;
                zzqt.this.zzasc();
            } finally {
                zzqt.this.f566zg.unlock();
            }
        }

        public void zzn(@Nullable Bundle bundle) {
            zzqt.this.f566zg.lock();
            try {
                zzqt.this.f564ze = ConnectionResult.f110wO;
                zzqt.this.zzasc();
            } finally {
                zzqt.this.f566zg.unlock();
            }
        }
    }

    private zzqt(Context context, zzrd zzrd, Lock lock, Looper looper, com.google.android.gms.common.zzc zzc, Map<zzc<?>, zze> map, Map<zzc<?>, zze> map2, zzf zzf, com.google.android.gms.common.api.Api.zza<? extends zzxp, zzxq> zza2, zze zze, ArrayList<zzqr> arrayList, ArrayList<zzqr> arrayList2, Map<Api<?>, Integer> map3, Map<Api<?>, Integer> map4) {
        this.mContext = context;
        this.f556yW = zzrd;
        this.f566zg = lock;
        this.zzajy = looper;
        this.f561zb = zze;
        this.f557yX = new zzrf(context, this.f556yW, lock, looper, zzc, map2, null, map4, null, arrayList2, new zza());
        this.f558yY = new zzrf(context, this.f556yW, lock, looper, zzc, map, zzf, map3, zza2, arrayList, new zzb());
        ArrayMap arrayMap = new ArrayMap();
        for (zzc put : map2.keySet()) {
            arrayMap.put(put, this.f557yX);
        }
        for (zzc put2 : map.keySet()) {
            arrayMap.put(put2, this.f558yY);
        }
        this.f559yZ = Collections.unmodifiableMap(arrayMap);
    }

    public static zzqt zza(Context context, zzrd zzrd, Lock lock, Looper looper, com.google.android.gms.common.zzc zzc, Map<zzc<?>, zze> map, zzf zzf, Map<Api<?>, Integer> map2, com.google.android.gms.common.api.Api.zza<? extends zzxp, zzxq> zza2, ArrayList<zzqr> arrayList) {
        zze zze = null;
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        for (Entry entry : map.entrySet()) {
            zze zze2 = (zze) entry.getValue();
            if (zze2.zzajc()) {
                zze = zze2;
            }
            if (zze2.zzain()) {
                arrayMap.put((zzc) entry.getKey(), zze2);
            } else {
                arrayMap2.put((zzc) entry.getKey(), zze2);
            }
        }
        zzaa.zza(!arrayMap.isEmpty(), (Object) "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        ArrayMap arrayMap3 = new ArrayMap();
        ArrayMap arrayMap4 = new ArrayMap();
        for (Api api : map2.keySet()) {
            zzc zzaqv = api.zzaqv();
            if (arrayMap.containsKey(zzaqv)) {
                arrayMap3.put(api, (Integer) map2.get(api));
            } else if (arrayMap2.containsKey(zzaqv)) {
                arrayMap4.put(api, (Integer) map2.get(api));
            } else {
                throw new IllegalStateException("Each API in the apiTypeMap must have a corresponding client in the clients map.");
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            zzqr zzqr = (zzqr) it.next();
            if (arrayMap3.containsKey(zzqr.f553vS)) {
                arrayList2.add(zzqr);
            } else if (arrayMap4.containsKey(zzqr.f553vS)) {
                arrayList3.add(zzqr);
            } else {
                throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the apiTypeMap");
            }
        }
        return new zzqt(context, zzrd, lock, looper, zzc, arrayMap, arrayMap2, zzf, zza2, zze, arrayList2, arrayList3, arrayMap3, arrayMap4);
    }

    private void zza(ConnectionResult connectionResult) {
        switch (this.f567zh) {
            case 1:
                break;
            case 2:
                this.f556yW.zzc(connectionResult);
                break;
            default:
                Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
                break;
        }
        zzase();
        this.f567zh = 0;
    }

    private void zzasb() {
        this.f564ze = null;
        this.f563zd = null;
        this.f557yX.connect();
        this.f558yY.connect();
    }

    /* access modifiers changed from: private */
    public void zzasc() {
        if (zzb(this.f563zd)) {
            if (zzb(this.f564ze) || zzasf()) {
                zzasd();
            } else if (this.f564ze == null) {
            } else {
                if (this.f567zh == 1) {
                    zzase();
                    return;
                }
                zza(this.f564ze);
                this.f557yX.disconnect();
            }
        } else if (this.f563zd != null && zzb(this.f564ze)) {
            this.f558yY.disconnect();
            zza(this.f563zd);
        } else if (this.f563zd != null && this.f564ze != null) {
            ConnectionResult connectionResult = this.f563zd;
            if (this.f558yY.f674AB < this.f557yX.f674AB) {
                connectionResult = this.f564ze;
            }
            zza(connectionResult);
        }
    }

    private void zzasd() {
        switch (this.f567zh) {
            case 1:
                break;
            case 2:
                this.f556yW.zzn(this.f562zc);
                break;
            default:
                Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
                break;
        }
        zzase();
        this.f567zh = 0;
    }

    private void zzase() {
        for (zzsa zzajb : this.f560za) {
            zzajb.zzajb();
        }
        this.f560za.clear();
    }

    private boolean zzasf() {
        return this.f564ze != null && this.f564ze.getErrorCode() == 4;
    }

    @Nullable
    private PendingIntent zzasg() {
        if (this.f561zb == null) {
            return null;
        }
        return PendingIntent.getActivity(this.mContext, this.f556yW.getSessionId(), this.f561zb.zzajd(), 134217728);
    }

    /* access modifiers changed from: private */
    public void zzb(int i, boolean z) {
        this.f556yW.zzc(i, z);
        this.f564ze = null;
        this.f563zd = null;
    }

    private static boolean zzb(ConnectionResult connectionResult) {
        return connectionResult != null && connectionResult.isSuccess();
    }

    private boolean zzc(com.google.android.gms.internal.zzqo.zza<? extends Result, ? extends com.google.android.gms.common.api.Api.zzb> zza2) {
        zzc zzaqv = zza2.zzaqv();
        zzaa.zzb(this.f559yZ.containsKey(zzaqv), (Object) "GoogleApiClient is not configured to use the API required for this call.");
        return ((zzrf) this.f559yZ.get(zzaqv)).equals(this.f558yY);
    }

    /* access modifiers changed from: private */
    public void zzm(Bundle bundle) {
        if (this.f562zc == null) {
            this.f562zc = bundle;
        } else if (bundle != null) {
            this.f562zc.putAll(bundle);
        }
    }

    public ConnectionResult blockingConnect() {
        throw new UnsupportedOperationException();
    }

    public ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public void connect() {
        this.f567zh = 2;
        this.f565zf = false;
        zzasb();
    }

    public void disconnect() {
        this.f564ze = null;
        this.f563zd = null;
        this.f567zh = 0;
        this.f557yX.disconnect();
        this.f558yY.disconnect();
        zzase();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append(str).append("authClient").println(":");
        this.f558yY.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        printWriter.append(str).append("anonClient").println(":");
        this.f557yX.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
    }

    @Nullable
    public ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        return ((zzrf) this.f559yZ.get(api.zzaqv())).equals(this.f558yY) ? zzasf() ? new ConnectionResult(4, zzasg()) : this.f558yY.getConnectionResult(api) : this.f557yX.getConnectionResult(api);
    }

    public boolean isConnected() {
        boolean z = true;
        this.f566zg.lock();
        try {
            if (!this.f557yX.isConnected() || (!zzasa() && !zzasf() && this.f567zh != 1)) {
                z = false;
            }
            return z;
        } finally {
            this.f566zg.unlock();
        }
    }

    public boolean isConnecting() {
        this.f566zg.lock();
        try {
            return this.f567zh == 2;
        } finally {
            this.f566zg.unlock();
        }
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, R extends Result, T extends com.google.android.gms.internal.zzqo.zza<R, A>> T zza(@NonNull T t) {
        if (!zzc((com.google.android.gms.internal.zzqo.zza<? extends Result, ? extends com.google.android.gms.common.api.Api.zzb>) t)) {
            return this.f557yX.zza(t);
        }
        if (!zzasf()) {
            return this.f558yY.zza(t);
        }
        t.zzaa(new Status(4, null, zzasg()));
        return t;
    }

    public boolean zza(zzsa zzsa) {
        this.f566zg.lock();
        try {
            if ((isConnecting() || isConnected()) && !zzasa()) {
                this.f560za.add(zzsa);
                if (this.f567zh == 0) {
                    this.f567zh = 1;
                }
                this.f564ze = null;
                this.f558yY.connect();
                return true;
            }
            this.f566zg.unlock();
            return false;
        } finally {
            this.f566zg.unlock();
        }
    }

    public void zzard() {
        this.f566zg.lock();
        try {
            boolean isConnecting = isConnecting();
            this.f558yY.disconnect();
            this.f564ze = new ConnectionResult(4);
            if (isConnecting) {
                new Handler(this.zzajy).post(new Runnable() {
                    public void run() {
                        zzqt.this.f566zg.lock();
                        try {
                            zzqt.this.zzasc();
                        } finally {
                            zzqt.this.f566zg.unlock();
                        }
                    }
                });
            } else {
                zzase();
            }
        } finally {
            this.f566zg.unlock();
        }
    }

    public void zzarz() {
        this.f557yX.zzarz();
        this.f558yY.zzarz();
    }

    public boolean zzasa() {
        return this.f558yY.isConnected();
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, T extends com.google.android.gms.internal.zzqo.zza<? extends Result, A>> T zzb(@NonNull T t) {
        if (!zzc((com.google.android.gms.internal.zzqo.zza<? extends Result, ? extends com.google.android.gms.common.api.Api.zzb>) t)) {
            return this.f557yX.zzb(t);
        }
        if (!zzasf()) {
            return this.f558yY.zzb(t);
        }
        t.zzaa(new Status(4, null, zzasg()));
        return t;
    }
}
