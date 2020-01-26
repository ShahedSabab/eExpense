package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.p000v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzk;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;

public final class zzrd extends GoogleApiClient implements com.google.android.gms.internal.zzrm.zza {

    /* renamed from: Ab */
    private final zzk f638Ab;

    /* renamed from: Ac */
    private zzrm f639Ac = null;

    /* renamed from: Ad */
    final Queue<com.google.android.gms.internal.zzqo.zza<?, ?>> f640Ad = new LinkedList();

    /* renamed from: Ae */
    private volatile boolean f641Ae;

    /* renamed from: Af */
    private long f642Af = 120000;

    /* renamed from: Ag */
    private long f643Ag = 5000;

    /* renamed from: Ah */
    private final zza f644Ah;

    /* renamed from: Ai */
    zzrj f645Ai;

    /* renamed from: Aj */
    final Map<zzc<?>, zze> f646Aj;

    /* renamed from: Ak */
    Set<Scope> f647Ak = new HashSet();

    /* renamed from: Al */
    private final zzrs f648Al = new zzrs();

    /* renamed from: Am */
    private final ArrayList<zzqr> f649Am;

    /* renamed from: An */
    private Integer f650An = null;

    /* renamed from: Ao */
    Set<zzsf> f651Ao = null;

    /* renamed from: Ap */
    final zzsg f652Ap;

    /* renamed from: Aq */
    private final com.google.android.gms.common.internal.zzk.zza f653Aq = new com.google.android.gms.common.internal.zzk.zza() {
        public boolean isConnected() {
            return zzrd.this.isConnected();
        }

        public Bundle zzapn() {
            return null;
        }
    };
    /* access modifiers changed from: private */
    public final Context mContext;

    /* renamed from: xN */
    private final int f654xN;

    /* renamed from: xP */
    private final GoogleApiAvailability f655xP;

    /* renamed from: xQ */
    final com.google.android.gms.common.api.Api.zza<? extends zzxp, zzxq> f656xQ;

    /* renamed from: xT */
    private boolean f657xT;

    /* renamed from: zP */
    final zzf f658zP;

    /* renamed from: zg */
    private final Lock f659zg;

    /* renamed from: zk */
    final Map<Api<?>, Integer> f660zk;
    private final Looper zzajy;

    final class zza extends Handler {
        zza(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    zzrd.this.zzasx();
                    return;
                case 2:
                    zzrd.this.resume();
                    return;
                default:
                    Log.w("GoogleApiClientImpl", "Unknown message id: " + message.what);
                    return;
            }
        }
    }

    static class zzb extends com.google.android.gms.internal.zzrj.zza {

        /* renamed from: Av */
        private WeakReference<zzrd> f672Av;

        zzb(zzrd zzrd) {
            this.f672Av = new WeakReference<>(zzrd);
        }

        public void zzarr() {
            zzrd zzrd = (zzrd) this.f672Av.get();
            if (zzrd != null) {
                zzrd.resume();
            }
        }
    }

    public zzrd(Context context, Lock lock, Looper looper, zzf zzf, GoogleApiAvailability googleApiAvailability, com.google.android.gms.common.api.Api.zza<? extends zzxp, zzxq> zza2, Map<Api<?>, Integer> map, List<ConnectionCallbacks> list, List<OnConnectionFailedListener> list2, Map<zzc<?>, zze> map2, int i, int i2, ArrayList<zzqr> arrayList, boolean z) {
        this.mContext = context;
        this.f659zg = lock;
        this.f657xT = z;
        this.f638Ab = new zzk(looper, this.f653Aq);
        this.zzajy = looper;
        this.f644Ah = new zza(looper);
        this.f655xP = googleApiAvailability;
        this.f654xN = i;
        if (this.f654xN >= 0) {
            this.f650An = Integer.valueOf(i2);
        }
        this.f660zk = map;
        this.f646Aj = map2;
        this.f649Am = arrayList;
        this.f652Ap = new zzsg(this.f646Aj);
        for (ConnectionCallbacks registerConnectionCallbacks : list) {
            this.f638Ab.registerConnectionCallbacks(registerConnectionCallbacks);
        }
        for (OnConnectionFailedListener registerConnectionFailedListener : list2) {
            this.f638Ab.registerConnectionFailedListener(registerConnectionFailedListener);
        }
        this.f658zP = zzf;
        this.f656xQ = zza2;
    }

    /* access modifiers changed from: private */
    public void resume() {
        this.f659zg.lock();
        try {
            if (isResuming()) {
                zzasw();
            }
        } finally {
            this.f659zg.unlock();
        }
    }

    public static int zza(Iterable<zze> iterable, boolean z) {
        boolean z2 = false;
        boolean z3 = false;
        for (zze zze : iterable) {
            if (zze.zzain()) {
                z3 = true;
            }
            z2 = zze.zzajc() ? true : z2;
        }
        if (z3) {
            return (!z2 || !z) ? 1 : 2;
        }
        return 3;
    }

    /* access modifiers changed from: private */
    public void zza(final GoogleApiClient googleApiClient, final zzsc zzsc, final boolean z) {
        zzsn.f796EU.zzg(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            /* renamed from: zzp */
            public void onResult(@NonNull Status status) {
                com.google.android.gms.auth.api.signin.internal.zzk.zzba(zzrd.this.mContext).zzajo();
                if (status.isSuccess() && zzrd.this.isConnected()) {
                    zzrd.this.reconnect();
                }
                zzsc.zzc(status);
                if (z) {
                    googleApiClient.disconnect();
                }
            }
        });
    }

    private void zzasw() {
        this.f638Ab.zzawd();
        this.f639Ac.connect();
    }

    /* access modifiers changed from: private */
    public void zzasx() {
        this.f659zg.lock();
        try {
            if (zzasz()) {
                zzasw();
            }
        } finally {
            this.f659zg.unlock();
        }
    }

    private void zzb(@NonNull zzrn zzrn) {
        if (this.f654xN >= 0) {
            zzqm.zza(zzrn).zzfs(this.f654xN);
            return;
        }
        throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
    }

    private void zzfv(int i) {
        if (this.f650An == null) {
            this.f650An = Integer.valueOf(i);
        } else if (this.f650An.intValue() != i) {
            String valueOf = String.valueOf(zzfw(i));
            String valueOf2 = String.valueOf(zzfw(this.f650An.intValue()));
            throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 51 + String.valueOf(valueOf2).length()).append("Cannot use sign-in mode: ").append(valueOf).append(". Mode was already set to ").append(valueOf2).toString());
        }
        if (this.f639Ac == null) {
            boolean z = false;
            boolean z2 = false;
            for (zze zze : this.f646Aj.values()) {
                if (zze.zzain()) {
                    z2 = true;
                }
                z = zze.zzajc() ? true : z;
            }
            switch (this.f650An.intValue()) {
                case 1:
                    if (!z2) {
                        throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
                    } else if (z) {
                        throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
                    }
                    break;
                case 2:
                    if (z2) {
                        this.f639Ac = zzqt.zza(this.mContext, this, this.f659zg, this.zzajy, this.f655xP, this.f646Aj, this.f658zP, this.f660zk, this.f656xQ, this.f649Am);
                        return;
                    }
                    break;
            }
            if (!this.f657xT || z2 || z) {
                this.f639Ac = new zzrf(this.mContext, this, this.f659zg, this.zzajy, this.f655xP, this.f646Aj, this.f658zP, this.f660zk, this.f656xQ, this.f649Am, this);
            } else {
                this.f639Ac = new zzqu(this.mContext, this.f659zg, this.zzajy, this.f655xP, this.f646Aj, this.f660zk, this.f649Am, this);
            }
        }
    }

    static String zzfw(int i) {
        switch (i) {
            case 1:
                return "SIGN_IN_MODE_REQUIRED";
            case 2:
                return "SIGN_IN_MODE_OPTIONAL";
            case 3:
                return "SIGN_IN_MODE_NONE";
            default:
                return "UNKNOWN";
        }
    }

    public ConnectionResult blockingConnect() {
        boolean z = true;
        zzaa.zza(Looper.myLooper() != Looper.getMainLooper(), (Object) "blockingConnect must not be called on the UI thread");
        this.f659zg.lock();
        try {
            if (this.f654xN >= 0) {
                if (this.f650An == null) {
                    z = false;
                }
                zzaa.zza(z, (Object) "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.f650An == null) {
                this.f650An = Integer.valueOf(zza(this.f646Aj.values(), false));
            } else if (this.f650An.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zzfv(this.f650An.intValue());
            this.f638Ab.zzawd();
            return this.f639Ac.blockingConnect();
        } finally {
            this.f659zg.unlock();
        }
    }

    public ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit) {
        boolean z = false;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            z = true;
        }
        zzaa.zza(z, (Object) "blockingConnect must not be called on the UI thread");
        zzaa.zzb(timeUnit, (Object) "TimeUnit must not be null");
        this.f659zg.lock();
        try {
            if (this.f650An == null) {
                this.f650An = Integer.valueOf(zza(this.f646Aj.values(), false));
            } else if (this.f650An.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zzfv(this.f650An.intValue());
            this.f638Ab.zzawd();
            return this.f639Ac.blockingConnect(j, timeUnit);
        } finally {
            this.f659zg.unlock();
        }
    }

    public PendingResult<Status> clearDefaultAccountAndReconnect() {
        zzaa.zza(isConnected(), (Object) "GoogleApiClient is not connected yet.");
        zzaa.zza(this.f650An.intValue() != 2, (Object) "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
        final zzsc zzsc = new zzsc((GoogleApiClient) this);
        if (this.f646Aj.containsKey(zzsn.f797hg)) {
            zza(this, zzsc, false);
        } else {
            final AtomicReference atomicReference = new AtomicReference();
            GoogleApiClient build = new Builder(this.mContext).addApi(zzsn.API).addConnectionCallbacks(new ConnectionCallbacks() {
                public void onConnected(Bundle bundle) {
                    zzrd.this.zza((GoogleApiClient) atomicReference.get(), zzsc, true);
                }

                public void onConnectionSuspended(int i) {
                }
            }).addOnConnectionFailedListener(new OnConnectionFailedListener() {
                public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                    zzsc.zzc(new Status(8));
                }
            }).setHandler(this.f644Ah).build();
            atomicReference.set(build);
            build.connect();
        }
        return zzsc;
    }

    public void connect() {
        boolean z = false;
        this.f659zg.lock();
        try {
            if (this.f654xN >= 0) {
                if (this.f650An != null) {
                    z = true;
                }
                zzaa.zza(z, (Object) "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.f650An == null) {
                this.f650An = Integer.valueOf(zza(this.f646Aj.values(), false));
            } else if (this.f650An.intValue() == 2) {
                throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            connect(this.f650An.intValue());
        } finally {
            this.f659zg.unlock();
        }
    }

    public void connect(int i) {
        boolean z = true;
        this.f659zg.lock();
        if (!(i == 3 || i == 1 || i == 2)) {
            z = false;
        }
        try {
            zzaa.zzb(z, (Object) "Illegal sign-in mode: " + i);
            zzfv(i);
            zzasw();
        } finally {
            this.f659zg.unlock();
        }
    }

    public void disconnect() {
        this.f659zg.lock();
        try {
            this.f652Ap.release();
            if (this.f639Ac != null) {
                this.f639Ac.disconnect();
            }
            this.f648Al.release();
            for (com.google.android.gms.internal.zzqo.zza zza2 : this.f640Ad) {
                zza2.zza((zzb) null);
                zza2.cancel();
            }
            this.f640Ad.clear();
            if (this.f639Ac != null) {
                zzasz();
                this.f638Ab.zzawc();
                this.f659zg.unlock();
            }
        } finally {
            this.f659zg.unlock();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append(str).append("mContext=").println(this.mContext);
        printWriter.append(str).append("mResuming=").print(this.f641Ae);
        printWriter.append(" mWorkQueue.size()=").print(this.f640Ad.size());
        this.f652Ap.dump(printWriter);
        if (this.f639Ac != null) {
            this.f639Ac.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    @NonNull
    public ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        this.f659zg.lock();
        try {
            if (!isConnected() && !isResuming()) {
                throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
            } else if (this.f646Aj.containsKey(api.zzaqv())) {
                ConnectionResult connectionResult = this.f639Ac.getConnectionResult(api);
                if (connectionResult != null) {
                    this.f659zg.unlock();
                } else if (isResuming()) {
                    connectionResult = ConnectionResult.f110wO;
                } else {
                    Log.w("GoogleApiClientImpl", zzatb());
                    Log.wtf("GoogleApiClientImpl", String.valueOf(api.getName()).concat(" requested in getConnectionResult is not connected but is not present in the failed  connections map"), new Exception());
                    connectionResult = new ConnectionResult(8, null);
                    this.f659zg.unlock();
                }
                return connectionResult;
            } else {
                throw new IllegalArgumentException(String.valueOf(api.getName()).concat(" was never registered with GoogleApiClient"));
            }
        } finally {
            this.f659zg.unlock();
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    public Looper getLooper() {
        return this.zzajy;
    }

    public int getSessionId() {
        return System.identityHashCode(this);
    }

    public boolean hasConnectedApi(@NonNull Api<?> api) {
        if (!isConnected()) {
            return false;
        }
        zze zze = (zze) this.f646Aj.get(api.zzaqv());
        return zze != null && zze.isConnected();
    }

    public boolean isConnected() {
        return this.f639Ac != null && this.f639Ac.isConnected();
    }

    public boolean isConnecting() {
        return this.f639Ac != null && this.f639Ac.isConnecting();
    }

    public boolean isConnectionCallbacksRegistered(@NonNull ConnectionCallbacks connectionCallbacks) {
        return this.f638Ab.isConnectionCallbacksRegistered(connectionCallbacks);
    }

    public boolean isConnectionFailedListenerRegistered(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
        return this.f638Ab.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }

    /* access modifiers changed from: 0000 */
    public boolean isResuming() {
        return this.f641Ae;
    }

    public void reconnect() {
        disconnect();
        connect();
    }

    public void registerConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks) {
        this.f638Ab.registerConnectionCallbacks(connectionCallbacks);
    }

    public void registerConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
        this.f638Ab.registerConnectionFailedListener(onConnectionFailedListener);
    }

    public void stopAutoManage(@NonNull FragmentActivity fragmentActivity) {
        zzb(new zzrn(fragmentActivity));
    }

    public void unregisterConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks) {
        this.f638Ab.unregisterConnectionCallbacks(connectionCallbacks);
    }

    public void unregisterConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
        this.f638Ab.unregisterConnectionFailedListener(onConnectionFailedListener);
    }

    @NonNull
    public <C extends zze> C zza(@NonNull zzc<C> zzc) {
        C c = (zze) this.f646Aj.get(zzc);
        zzaa.zzb(c, (Object) "Appropriate Api was not requested.");
        return c;
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, R extends Result, T extends com.google.android.gms.internal.zzqo.zza<R, A>> T zza(@NonNull T t) {
        zzaa.zzb(t.zzaqv() != null, (Object) "This task can not be enqueued (it's probably a Batch or malformed)");
        boolean containsKey = this.f646Aj.containsKey(t.zzaqv());
        String str = t.getApi() != null ? t.getApi().getName() : "the API";
        zzaa.zzb(containsKey, (Object) new StringBuilder(String.valueOf(str).length() + 65).append("GoogleApiClient is not configured to use ").append(str).append(" required for this call.").toString());
        this.f659zg.lock();
        try {
            if (this.f639Ac == null) {
                this.f640Ad.add(t);
            } else {
                t = this.f639Ac.zza(t);
                this.f659zg.unlock();
            }
            return t;
        } finally {
            this.f659zg.unlock();
        }
    }

    public void zza(zzsf zzsf) {
        this.f659zg.lock();
        try {
            if (this.f651Ao == null) {
                this.f651Ao = new HashSet();
            }
            this.f651Ao.add(zzsf);
        } finally {
            this.f659zg.unlock();
        }
    }

    public boolean zza(@NonNull Api<?> api) {
        return this.f646Aj.containsKey(api.zzaqv());
    }

    public boolean zza(zzsa zzsa) {
        return this.f639Ac != null && this.f639Ac.zza(zzsa);
    }

    public void zzard() {
        if (this.f639Ac != null) {
            this.f639Ac.zzard();
        }
    }

    /* access modifiers changed from: 0000 */
    public void zzasy() {
        if (!isResuming()) {
            this.f641Ae = true;
            if (this.f645Ai == null) {
                this.f645Ai = this.f655xP.zza(this.mContext.getApplicationContext(), (com.google.android.gms.internal.zzrj.zza) new zzb(this));
            }
            this.f644Ah.sendMessageDelayed(this.f644Ah.obtainMessage(1), this.f642Af);
            this.f644Ah.sendMessageDelayed(this.f644Ah.obtainMessage(2), this.f643Ag);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean zzasz() {
        if (!isResuming()) {
            return false;
        }
        this.f641Ae = false;
        this.f644Ah.removeMessages(2);
        this.f644Ah.removeMessages(1);
        if (this.f645Ai != null) {
            this.f645Ai.unregister();
            this.f645Ai = null;
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public boolean zzata() {
        boolean z = false;
        this.f659zg.lock();
        try {
            if (this.f651Ao != null) {
                if (!this.f651Ao.isEmpty()) {
                    z = true;
                }
                this.f659zg.unlock();
            }
            return z;
        } finally {
            this.f659zg.unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    public String zzatb() {
        StringWriter stringWriter = new StringWriter();
        dump("", null, new PrintWriter(stringWriter), null);
        return stringWriter.toString();
    }

    /* access modifiers changed from: 0000 */
    public <C extends zze> C zzb(zzc<?> zzc) {
        C c = (zze) this.f646Aj.get(zzc);
        zzaa.zzb(c, (Object) "Appropriate Api was not requested.");
        return c;
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, T extends com.google.android.gms.internal.zzqo.zza<? extends Result, A>> T zzb(@NonNull T t) {
        zzaa.zzb(t.zzaqv() != null, (Object) "This task can not be executed (it's probably a Batch or malformed)");
        boolean containsKey = this.f646Aj.containsKey(t.zzaqv());
        String str = t.getApi() != null ? t.getApi().getName() : "the API";
        zzaa.zzb(containsKey, (Object) new StringBuilder(String.valueOf(str).length() + 65).append("GoogleApiClient is not configured to use ").append(str).append(" required for this call.").toString());
        this.f659zg.lock();
        try {
            if (this.f639Ac == null) {
                throw new IllegalStateException("GoogleApiClient is not connected yet.");
            }
            if (isResuming()) {
                this.f640Ad.add(t);
                while (!this.f640Ad.isEmpty()) {
                    com.google.android.gms.internal.zzqo.zza zza2 = (com.google.android.gms.internal.zzqo.zza) this.f640Ad.remove();
                    this.f652Ap.zzb(zza2);
                    zza2.zzaa(Status.f160yb);
                }
            } else {
                t = this.f639Ac.zzb(t);
                this.f659zg.unlock();
            }
            return t;
        } finally {
            this.f659zg.unlock();
        }
    }

    public void zzb(zzsf zzsf) {
        this.f659zg.lock();
        try {
            if (this.f651Ao == null) {
                Log.wtf("GoogleApiClientImpl", "Attempted to remove pending transform when no transforms are registered.", new Exception());
            } else if (!this.f651Ao.remove(zzsf)) {
                Log.wtf("GoogleApiClientImpl", "Failed to remove pending transform - this may lead to memory leaks!", new Exception());
            } else if (!zzata()) {
                this.f639Ac.zzarz();
            }
        } finally {
            this.f659zg.unlock();
        }
    }

    public void zzc(int i, boolean z) {
        if (i == 1 && !z) {
            zzasy();
        }
        this.f652Ap.zzauf();
        this.f638Ab.zzgn(i);
        this.f638Ab.zzawc();
        if (i == 2) {
            zzasw();
        }
    }

    public void zzc(ConnectionResult connectionResult) {
        if (!this.f655xP.zzd(this.mContext, connectionResult.getErrorCode())) {
            zzasz();
        }
        if (!isResuming()) {
            this.f638Ab.zzn(connectionResult);
            this.f638Ab.zzawc();
        }
    }

    public void zzn(Bundle bundle) {
        while (!this.f640Ad.isEmpty()) {
            zzb((T) (com.google.android.gms.internal.zzqo.zza) this.f640Ad.remove());
        }
        this.f638Ab.zzp(bundle);
    }

    public <L> zzrr<L> zzs(@NonNull L l) {
        this.f659zg.lock();
        try {
            return this.f648Al.zzb(l, this.zzajy);
        } finally {
            this.f659zg.unlock();
        }
    }
}
