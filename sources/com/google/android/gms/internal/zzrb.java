package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.signin.internal.SignInResponse;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

public class zzrb implements zzre {
    /* access modifiers changed from: private */
    public final Context mContext;

    /* renamed from: xQ */
    private final com.google.android.gms.common.api.Api.zza<? extends zzxp, zzxq> f599xQ;
    /* access modifiers changed from: private */

    /* renamed from: zA */
    public final zzrf f600zA;

    /* renamed from: zD */
    private int f601zD;

    /* renamed from: zE */
    private int f602zE = 0;

    /* renamed from: zF */
    private int f603zF;

    /* renamed from: zG */
    private final Bundle f604zG = new Bundle();

    /* renamed from: zH */
    private final Set<com.google.android.gms.common.api.Api.zzc> f605zH = new HashSet();
    /* access modifiers changed from: private */

    /* renamed from: zI */
    public zzxp f606zI;

    /* renamed from: zJ */
    private int f607zJ;
    /* access modifiers changed from: private */

    /* renamed from: zK */
    public boolean f608zK;

    /* renamed from: zL */
    private boolean f609zL;
    /* access modifiers changed from: private */

    /* renamed from: zM */
    public zzp f610zM;

    /* renamed from: zN */
    private boolean f611zN;

    /* renamed from: zO */
    private boolean f612zO;

    /* renamed from: zP */
    private final com.google.android.gms.common.internal.zzf f613zP;

    /* renamed from: zQ */
    private ArrayList<Future<?>> f614zQ = new ArrayList<>();
    /* access modifiers changed from: private */

    /* renamed from: zg */
    public final Lock f615zg;

    /* renamed from: zk */
    private final Map<Api<?>, Integer> f616zk;
    /* access modifiers changed from: private */

    /* renamed from: zm */
    public final com.google.android.gms.common.zzc f617zm;

    /* renamed from: zq */
    private ConnectionResult f618zq;

    private static class zza implements com.google.android.gms.common.internal.zze.zzf {

        /* renamed from: vS */
        private final Api<?> f620vS;
        /* access modifiers changed from: private */

        /* renamed from: yU */
        public final int f621yU;

        /* renamed from: zS */
        private final WeakReference<zzrb> f622zS;

        public zza(zzrb zzrb, Api<?> api, int i) {
            this.f622zS = new WeakReference<>(zzrb);
            this.f620vS = api;
            this.f621yU = i;
        }

        public void zzg(@NonNull ConnectionResult connectionResult) {
            boolean z = false;
            zzrb zzrb = (zzrb) this.f622zS.get();
            if (zzrb != null) {
                if (Looper.myLooper() == zzrb.f600zA.f682yW.getLooper()) {
                    z = true;
                }
                zzaa.zza(z, (Object) "onReportServiceBinding must be called on the GoogleApiClient handler thread");
                zzrb.f615zg.lock();
                try {
                    if (zzrb.zzft(0)) {
                        if (!connectionResult.isSuccess()) {
                            zzrb.zzb(connectionResult, this.f620vS, this.f621yU);
                        }
                        if (zzrb.zzasp()) {
                            zzrb.zzasq();
                        }
                        zzrb.f615zg.unlock();
                    }
                } finally {
                    zzrb.f615zg.unlock();
                }
            }
        }
    }

    private class zzb extends zzf {

        /* renamed from: zT */
        private final Map<com.google.android.gms.common.api.Api.zze, zza> f624zT;

        public zzb(Map<com.google.android.gms.common.api.Api.zze, zza> map) {
            super();
            this.f624zT = map;
        }

        @WorkerThread
        public void zzaso() {
            boolean z;
            boolean z2;
            boolean z3;
            boolean z4 = true;
            int i = 0;
            Iterator it = this.f624zT.keySet().iterator();
            boolean z5 = true;
            boolean z6 = false;
            while (true) {
                if (!it.hasNext()) {
                    z4 = z6;
                    z = false;
                    break;
                }
                com.google.android.gms.common.api.Api.zze zze = (com.google.android.gms.common.api.Api.zze) it.next();
                if (!zze.zzaqx()) {
                    z3 = false;
                    z2 = z6;
                } else if (((zza) this.f624zT.get(zze)).f621yU == 0) {
                    z = true;
                    break;
                } else {
                    z3 = z5;
                    z2 = true;
                }
                z6 = z2;
                z5 = z3;
            }
            if (z4) {
                i = zzrb.this.f617zm.isGooglePlayServicesAvailable(zzrb.this.mContext);
            }
            if (i == 0 || (!z && !z5)) {
                if (zzrb.this.f608zK) {
                    zzrb.this.f606zI.connect();
                }
                for (com.google.android.gms.common.api.Api.zze zze2 : this.f624zT.keySet()) {
                    final com.google.android.gms.common.internal.zze.zzf zzf = (com.google.android.gms.common.internal.zze.zzf) this.f624zT.get(zze2);
                    if (!zze2.zzaqx() || i == 0) {
                        zze2.zza(zzf);
                    } else {
                        zzrb.this.f600zA.zza((zza) new zza(zzrb.this) {
                            public void zzaso() {
                                zzf.zzg(new ConnectionResult(16, null));
                            }
                        });
                    }
                }
                return;
            }
            final ConnectionResult connectionResult = new ConnectionResult(i, null);
            zzrb.this.f600zA.zza((zza) new zza(zzrb.this) {
                public void zzaso() {
                    zzrb.this.zzf(connectionResult);
                }
            });
        }
    }

    private class zzc extends zzf {

        /* renamed from: zX */
        private final ArrayList<com.google.android.gms.common.api.Api.zze> f630zX;

        public zzc(ArrayList<com.google.android.gms.common.api.Api.zze> arrayList) {
            super();
            this.f630zX = arrayList;
        }

        @WorkerThread
        public void zzaso() {
            zzrb.this.f600zA.f682yW.f647Ak = zzrb.this.zzasv();
            Iterator it = this.f630zX.iterator();
            while (it.hasNext()) {
                ((com.google.android.gms.common.api.Api.zze) it.next()).zza(zzrb.this.f610zM, zzrb.this.f600zA.f682yW.f647Ak);
            }
        }
    }

    private static class zzd extends com.google.android.gms.signin.internal.zzb {

        /* renamed from: zS */
        private final WeakReference<zzrb> f631zS;

        zzd(zzrb zzrb) {
            this.f631zS = new WeakReference<>(zzrb);
        }

        @BinderThread
        public void zzb(final SignInResponse signInResponse) {
            final zzrb zzrb = (zzrb) this.f631zS.get();
            if (zzrb != null) {
                zzrb.f600zA.zza((zza) new zza(zzrb) {
                    public void zzaso() {
                        zzrb.zza(signInResponse);
                    }
                });
            }
        }
    }

    private class zze implements ConnectionCallbacks, OnConnectionFailedListener {
        private zze() {
        }

        public void onConnected(Bundle bundle) {
            zzrb.this.f606zI.zza(new zzd(zzrb.this));
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            zzrb.this.f615zg.lock();
            try {
                if (zzrb.this.zze(connectionResult)) {
                    zzrb.this.zzast();
                    zzrb.this.zzasq();
                } else {
                    zzrb.this.zzf(connectionResult);
                }
            } finally {
                zzrb.this.f615zg.unlock();
            }
        }

        public void onConnectionSuspended(int i) {
        }
    }

    private abstract class zzf implements Runnable {
        private zzf() {
        }

        @WorkerThread
        public void run() {
            zzrb.this.f615zg.lock();
            try {
                if (!Thread.interrupted()) {
                    zzaso();
                    zzrb.this.f615zg.unlock();
                }
            } catch (RuntimeException e) {
                zzrb.this.f600zA.zza(e);
            } finally {
                zzrb.this.f615zg.unlock();
            }
        }

        /* access modifiers changed from: protected */
        @WorkerThread
        public abstract void zzaso();
    }

    public zzrb(zzrf zzrf, com.google.android.gms.common.internal.zzf zzf2, Map<Api<?>, Integer> map, com.google.android.gms.common.zzc zzc2, com.google.android.gms.common.api.Api.zza<? extends zzxp, zzxq> zza2, Lock lock, Context context) {
        this.f600zA = zzrf;
        this.f613zP = zzf2;
        this.f616zk = map;
        this.f617zm = zzc2;
        this.f599xQ = zza2;
        this.f615zg = lock;
        this.mContext = context;
    }

    /* access modifiers changed from: private */
    public void zza(SignInResponse signInResponse) {
        if (zzft(0)) {
            ConnectionResult zzawn = signInResponse.zzawn();
            if (zzawn.isSuccess()) {
                ResolveAccountResponse zzcdn = signInResponse.zzcdn();
                ConnectionResult zzawn2 = zzcdn.zzawn();
                if (!zzawn2.isSuccess()) {
                    String valueOf = String.valueOf(zzawn2);
                    Log.wtf("GoogleApiClientConnecting", new StringBuilder(String.valueOf(valueOf).length() + 48).append("Sign-in succeeded with resolve account failure: ").append(valueOf).toString(), new Exception());
                    zzf(zzawn2);
                    return;
                }
                this.f609zL = true;
                this.f610zM = zzcdn.zzawm();
                this.f611zN = zzcdn.zzawo();
                this.f612zO = zzcdn.zzawp();
                zzasq();
            } else if (zze(zzawn)) {
                zzast();
                zzasq();
            } else {
                zzf(zzawn);
            }
        }
    }

    private boolean zza(int i, int i2, ConnectionResult connectionResult) {
        if (i2 != 1 || zzd(connectionResult)) {
            return this.f618zq == null || i < this.f601zD;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean zzasp() {
        this.f603zF--;
        if (this.f603zF > 0) {
            return false;
        }
        if (this.f603zF < 0) {
            Log.w("GoogleApiClientConnecting", this.f600zA.f682yW.zzatb());
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            zzf(new ConnectionResult(8, null));
            return false;
        } else if (this.f618zq == null) {
            return true;
        } else {
            this.f600zA.f674AB = this.f601zD;
            zzf(this.f618zq);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void zzasq() {
        if (this.f603zF == 0) {
            if (!this.f608zK || this.f609zL) {
                zzasr();
            }
        }
    }

    private void zzasr() {
        ArrayList arrayList = new ArrayList();
        this.f602zE = 1;
        this.f603zF = this.f600zA.f676Aj.size();
        for (com.google.android.gms.common.api.Api.zzc zzc2 : this.f600zA.f676Aj.keySet()) {
            if (!this.f600zA.f679Ay.containsKey(zzc2)) {
                arrayList.add((com.google.android.gms.common.api.Api.zze) this.f600zA.f676Aj.get(zzc2));
            } else if (zzasp()) {
                zzass();
            }
        }
        if (!arrayList.isEmpty()) {
            this.f614zQ.add(zzrg.zzatf().submit(new zzc(arrayList)));
        }
    }

    private void zzass() {
        this.f600zA.zzatd();
        zzrg.zzatf().execute(new Runnable() {
            public void run() {
                zzrb.this.f617zm.zzbn(zzrb.this.mContext);
            }
        });
        if (this.f606zI != null) {
            if (this.f611zN) {
                this.f606zI.zza(this.f610zM, this.f612zO);
            }
            zzbr(false);
        }
        for (com.google.android.gms.common.api.Api.zzc zzc2 : this.f600zA.f679Ay.keySet()) {
            ((com.google.android.gms.common.api.Api.zze) this.f600zA.f676Aj.get(zzc2)).disconnect();
        }
        this.f600zA.f675AC.zzn(this.f604zG.isEmpty() ? null : this.f604zG);
    }

    /* access modifiers changed from: private */
    public void zzast() {
        this.f608zK = false;
        this.f600zA.f682yW.f647Ak = Collections.emptySet();
        for (com.google.android.gms.common.api.Api.zzc zzc2 : this.f605zH) {
            if (!this.f600zA.f679Ay.containsKey(zzc2)) {
                this.f600zA.f679Ay.put(zzc2, new ConnectionResult(17, null));
            }
        }
    }

    private void zzasu() {
        Iterator it = this.f614zQ.iterator();
        while (it.hasNext()) {
            ((Future) it.next()).cancel(true);
        }
        this.f614zQ.clear();
    }

    /* access modifiers changed from: private */
    public Set<Scope> zzasv() {
        if (this.f613zP == null) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet(this.f613zP.zzavp());
        Map zzavr = this.f613zP.zzavr();
        for (Api api : zzavr.keySet()) {
            if (!this.f600zA.f679Ay.containsKey(api.zzaqv())) {
                hashSet.addAll(((com.google.android.gms.common.internal.zzf.zza) zzavr.get(api)).f319jw);
            }
        }
        return hashSet;
    }

    /* access modifiers changed from: private */
    public void zzb(ConnectionResult connectionResult, Api<?> api, int i) {
        if (i != 2) {
            int priority = api.zzaqs().getPriority();
            if (zza(priority, i, connectionResult)) {
                this.f618zq = connectionResult;
                this.f601zD = priority;
            }
        }
        this.f600zA.f679Ay.put(api.zzaqv(), connectionResult);
    }

    private void zzbr(boolean z) {
        if (this.f606zI != null) {
            if (this.f606zI.isConnected() && z) {
                this.f606zI.zzcdc();
            }
            this.f606zI.disconnect();
            this.f610zM = null;
        }
    }

    private boolean zzd(ConnectionResult connectionResult) {
        return connectionResult.hasResolution() || this.f617zm.zzfp(connectionResult.getErrorCode()) != null;
    }

    /* access modifiers changed from: private */
    public boolean zze(ConnectionResult connectionResult) {
        if (this.f607zJ != 2) {
            return this.f607zJ == 1 && !connectionResult.hasResolution();
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void zzf(ConnectionResult connectionResult) {
        zzasu();
        zzbr(!connectionResult.hasResolution());
        this.f600zA.zzh(connectionResult);
        this.f600zA.f675AC.zzc(connectionResult);
    }

    /* access modifiers changed from: private */
    public boolean zzft(int i) {
        if (this.f602zE == i) {
            return true;
        }
        Log.w("GoogleApiClientConnecting", this.f600zA.f682yW.zzatb());
        String valueOf = String.valueOf(this);
        Log.w("GoogleApiClientConnecting", new StringBuilder(String.valueOf(valueOf).length() + 23).append("Unexpected callback in ").append(valueOf).toString());
        Log.w("GoogleApiClientConnecting", "mRemainingConnections=" + this.f603zF);
        String valueOf2 = String.valueOf(zzfu(this.f602zE));
        String valueOf3 = String.valueOf(zzfu(i));
        Log.wtf("GoogleApiClientConnecting", new StringBuilder(String.valueOf(valueOf2).length() + 70 + String.valueOf(valueOf3).length()).append("GoogleApiClient connecting is in step ").append(valueOf2).append(" but received callback for step ").append(valueOf3).toString(), new Exception());
        zzf(new ConnectionResult(8, null));
        return false;
    }

    private String zzfu(int i) {
        switch (i) {
            case 0:
                return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
            case 1:
                return "STEP_GETTING_REMOTE_SERVICE";
            default:
                return "UNKNOWN";
        }
    }

    public void begin() {
        this.f600zA.f679Ay.clear();
        this.f608zK = false;
        this.f618zq = null;
        this.f602zE = 0;
        this.f607zJ = 2;
        this.f609zL = false;
        this.f611zN = false;
        HashMap hashMap = new HashMap();
        boolean z = false;
        for (Api api : this.f616zk.keySet()) {
            com.google.android.gms.common.api.Api.zze zze2 = (com.google.android.gms.common.api.Api.zze) this.f600zA.f676Aj.get(api.zzaqv());
            int intValue = ((Integer) this.f616zk.get(api)).intValue();
            boolean z2 = (api.zzaqs().getPriority() == 1) | z;
            if (zze2.zzain()) {
                this.f608zK = true;
                if (intValue < this.f607zJ) {
                    this.f607zJ = intValue;
                }
                if (intValue != 0) {
                    this.f605zH.add(api.zzaqv());
                }
            }
            hashMap.put(zze2, new zza(this, api, intValue));
            z = z2;
        }
        if (z) {
            this.f608zK = false;
        }
        if (this.f608zK) {
            this.f613zP.zzc(Integer.valueOf(this.f600zA.f682yW.getSessionId()));
            zze zze3 = new zze();
            this.f606zI = (zzxp) this.f599xQ.zza(this.mContext, this.f600zA.f682yW.getLooper(), this.f613zP, this.f613zP.zzavv(), zze3, zze3);
        }
        this.f603zF = this.f600zA.f676Aj.size();
        this.f614zQ.add(zzrg.zzatf().submit(new zzb(hashMap)));
    }

    public void connect() {
    }

    public boolean disconnect() {
        zzasu();
        zzbr(true);
        this.f600zA.zzh(null);
        return true;
    }

    public void onConnected(Bundle bundle) {
        if (zzft(1)) {
            if (bundle != null) {
                this.f604zG.putAll(bundle);
            }
            if (zzasp()) {
                zzass();
            }
        }
    }

    public void onConnectionSuspended(int i) {
        zzf(new ConnectionResult(8, null));
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, R extends Result, T extends com.google.android.gms.internal.zzqo.zza<R, A>> T zza(T t) {
        this.f600zA.f682yW.f640Ad.add(t);
        return t;
    }

    public void zza(ConnectionResult connectionResult, Api<?> api, int i) {
        if (zzft(1)) {
            zzb(connectionResult, api, i);
            if (zzasp()) {
                zzass();
            }
        }
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, T extends com.google.android.gms.internal.zzqo.zza<? extends Result, A>> T zzb(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
