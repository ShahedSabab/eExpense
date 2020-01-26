package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.util.ArrayMap;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.Api.ApiOptions.NotRequiredOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Api.zzg;
import com.google.android.gms.common.api.Api.zzh;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzag;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzf.zza;
import com.google.android.gms.internal.zzqm;
import com.google.android.gms.internal.zzqo;
import com.google.android.gms.internal.zzqr;
import com.google.android.gms.internal.zzrd;
import com.google.android.gms.internal.zzrn;
import com.google.android.gms.internal.zzrr;
import com.google.android.gms.internal.zzsa;
import com.google.android.gms.internal.zzsf;
import com.google.android.gms.internal.zzxo;
import com.google.android.gms.internal.zzxp;
import com.google.android.gms.internal.zzxq;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public abstract class GoogleApiClient {
    public static final int SIGN_IN_MODE_OPTIONAL = 2;
    public static final int SIGN_IN_MODE_REQUIRED = 1;
    /* access modifiers changed from: private */

    /* renamed from: xE */
    public static final Set<GoogleApiClient> f136xE = Collections.newSetFromMap(new WeakHashMap());

    public static final class Builder {

        /* renamed from: gj */
        private Account f137gj;

        /* renamed from: hu */
        private String f138hu;
        private final Context mContext;

        /* renamed from: xF */
        private final Set<Scope> f139xF;

        /* renamed from: xG */
        private final Set<Scope> f140xG;

        /* renamed from: xH */
        private int f141xH;

        /* renamed from: xI */
        private View f142xI;

        /* renamed from: xJ */
        private String f143xJ;

        /* renamed from: xK */
        private final Map<Api<?>, zza> f144xK;

        /* renamed from: xL */
        private final Map<Api<?>, ApiOptions> f145xL;

        /* renamed from: xM */
        private zzrn f146xM;

        /* renamed from: xN */
        private int f147xN;

        /* renamed from: xO */
        private OnConnectionFailedListener f148xO;

        /* renamed from: xP */
        private GoogleApiAvailability f149xP;

        /* renamed from: xQ */
        private Api.zza<? extends zzxp, zzxq> f150xQ;

        /* renamed from: xR */
        private final ArrayList<ConnectionCallbacks> f151xR;

        /* renamed from: xS */
        private final ArrayList<OnConnectionFailedListener> f152xS;

        /* renamed from: xT */
        private boolean f153xT;
        private Looper zzajy;

        public Builder(@NonNull Context context) {
            this.f139xF = new HashSet();
            this.f140xG = new HashSet();
            this.f144xK = new ArrayMap();
            this.f145xL = new ArrayMap();
            this.f147xN = -1;
            this.f149xP = GoogleApiAvailability.getInstance();
            this.f150xQ = zzxo.f830hh;
            this.f151xR = new ArrayList<>();
            this.f152xS = new ArrayList<>();
            this.f153xT = false;
            this.mContext = context;
            this.zzajy = context.getMainLooper();
            this.f138hu = context.getPackageName();
            this.f143xJ = context.getClass().getName();
        }

        public Builder(@NonNull Context context, @NonNull ConnectionCallbacks connectionCallbacks, @NonNull OnConnectionFailedListener onConnectionFailedListener) {
            this(context);
            zzaa.zzb(connectionCallbacks, (Object) "Must provide a connected listener");
            this.f151xR.add(connectionCallbacks);
            zzaa.zzb(onConnectionFailedListener, (Object) "Must provide a connection failed listener");
            this.f152xS.add(onConnectionFailedListener);
        }

        private static <C extends zze, O> C zza(Api.zza<C, O> zza, Object obj, Context context, Looper looper, zzf zzf, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return zza.zza(context, looper, zzf, obj, connectionCallbacks, onConnectionFailedListener);
        }

        private Builder zza(@NonNull zzrn zzrn, int i, @Nullable OnConnectionFailedListener onConnectionFailedListener) {
            zzaa.zzb(i >= 0, (Object) "clientId must be non-negative");
            this.f147xN = i;
            this.f148xO = onConnectionFailedListener;
            this.f146xM = zzrn;
            return this;
        }

        private static <C extends zzg, O> zzag zza(zzh<C, O> zzh, Object obj, Context context, Looper looper, zzf zzf, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzag(context, looper, zzh.zzaqz(), connectionCallbacks, onConnectionFailedListener, zzf, zzh.zzr(obj));
        }

        private <O extends ApiOptions> void zza(Api<O> api, O o, int i, Scope... scopeArr) {
            boolean z = true;
            if (i != 1) {
                if (i == 2) {
                    z = false;
                } else {
                    throw new IllegalArgumentException("Invalid resolution mode: '" + i + "', use a constant from GoogleApiClient.ResolutionMode");
                }
            }
            HashSet hashSet = new HashSet(api.zzaqs().zzp(o));
            for (Scope add : scopeArr) {
                hashSet.add(add);
            }
            this.f144xK.put(api, new zza(hashSet, z));
        }

        private GoogleApiClient zzarg() {
            zze zza;
            Api api;
            zzf zzarf = zzarf();
            Api api2 = null;
            Map zzavr = zzarf.zzavr();
            ArrayMap arrayMap = new ArrayMap();
            ArrayMap arrayMap2 = new ArrayMap();
            ArrayList arrayList = new ArrayList();
            Api api3 = null;
            for (Api api4 : this.f145xL.keySet()) {
                Object obj = this.f145xL.get(api4);
                int i = 0;
                if (zzavr.get(api4) != null) {
                    i = ((zza) zzavr.get(api4)).f318DN ? 1 : 2;
                }
                arrayMap.put(api4, Integer.valueOf(i));
                zzqr zzqr = new zzqr(api4, i);
                arrayList.add(zzqr);
                if (api4.zzaqw()) {
                    zzh zzaqu = api4.zzaqu();
                    Api api5 = zzaqu.getPriority() == 1 ? api4 : api3;
                    zza = zza(zzaqu, obj, this.mContext, this.zzajy, zzarf, (ConnectionCallbacks) zzqr, (OnConnectionFailedListener) zzqr);
                    api = api5;
                } else {
                    Api.zza zzaqt = api4.zzaqt();
                    Api api6 = zzaqt.getPriority() == 1 ? api4 : api3;
                    zza = zza(zzaqt, obj, this.mContext, this.zzajy, zzarf, (ConnectionCallbacks) zzqr, (OnConnectionFailedListener) zzqr);
                    api = api6;
                }
                arrayMap2.put(api4.zzaqv(), zza);
                if (!zza.zzajc()) {
                    api4 = api2;
                } else if (api2 != null) {
                    String valueOf = String.valueOf(api4.getName());
                    String valueOf2 = String.valueOf(api2.getName());
                    throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 21 + String.valueOf(valueOf2).length()).append(valueOf).append(" cannot be used with ").append(valueOf2).toString());
                }
                api3 = api;
                api2 = api4;
            }
            if (api2 != null) {
                if (api3 != null) {
                    String valueOf3 = String.valueOf(api2.getName());
                    String valueOf4 = String.valueOf(api3.getName());
                    throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf3).length() + 21 + String.valueOf(valueOf4).length()).append(valueOf3).append(" cannot be used with ").append(valueOf4).toString());
                }
                zzaa.zza(this.f137gj == null, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", api2.getName());
                zzaa.zza(this.f139xF.equals(this.f140xG), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", api2.getName());
            }
            return new zzrd(this.mContext, new ReentrantLock(), this.zzajy, zzarf, this.f149xP, this.f150xQ, arrayMap, this.f151xR, this.f152xS, arrayMap2, this.f147xN, zzrd.zza(arrayMap2.values(), true), arrayList, false);
        }

        private void zzf(GoogleApiClient googleApiClient) {
            zzqm.zza(this.f146xM).zza(this.f147xN, googleApiClient, this.f148xO);
        }

        public Builder addApi(@NonNull Api<? extends NotRequiredOptions> api) {
            zzaa.zzb(api, (Object) "Api must not be null");
            this.f145xL.put(api, null);
            List zzp = api.zzaqs().zzp(null);
            this.f140xG.addAll(zzp);
            this.f139xF.addAll(zzp);
            return this;
        }

        public <O extends HasOptions> Builder addApi(@NonNull Api<O> api, @NonNull O o) {
            zzaa.zzb(api, (Object) "Api must not be null");
            zzaa.zzb(o, (Object) "Null options are not permitted for this Api");
            this.f145xL.put(api, o);
            List zzp = api.zzaqs().zzp(o);
            this.f140xG.addAll(zzp);
            this.f139xF.addAll(zzp);
            return this;
        }

        public <O extends HasOptions> Builder addApiIfAvailable(@NonNull Api<O> api, @NonNull O o, Scope... scopeArr) {
            zzaa.zzb(api, (Object) "Api must not be null");
            zzaa.zzb(o, (Object) "Null options are not permitted for this Api");
            this.f145xL.put(api, o);
            zza(api, o, 1, scopeArr);
            return this;
        }

        public Builder addApiIfAvailable(@NonNull Api<? extends NotRequiredOptions> api, Scope... scopeArr) {
            zzaa.zzb(api, (Object) "Api must not be null");
            this.f145xL.put(api, null);
            zza(api, null, 1, scopeArr);
            return this;
        }

        public Builder addConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks) {
            zzaa.zzb(connectionCallbacks, (Object) "Listener must not be null");
            this.f151xR.add(connectionCallbacks);
            return this;
        }

        public Builder addOnConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
            zzaa.zzb(onConnectionFailedListener, (Object) "Listener must not be null");
            this.f152xS.add(onConnectionFailedListener);
            return this;
        }

        public Builder addScope(@NonNull Scope scope) {
            zzaa.zzb(scope, (Object) "Scope must not be null");
            this.f139xF.add(scope);
            return this;
        }

        public GoogleApiClient build() {
            zzaa.zzb(!this.f145xL.isEmpty(), (Object) "must call addApi() to add at least one API");
            GoogleApiClient zzarg = zzarg();
            synchronized (GoogleApiClient.f136xE) {
                GoogleApiClient.f136xE.add(zzarg);
            }
            if (this.f147xN >= 0) {
                zzf(zzarg);
            }
            return zzarg;
        }

        public Builder enableAutoManage(@NonNull FragmentActivity fragmentActivity, int i, @Nullable OnConnectionFailedListener onConnectionFailedListener) {
            return zza(new zzrn(fragmentActivity), i, onConnectionFailedListener);
        }

        public Builder enableAutoManage(@NonNull FragmentActivity fragmentActivity, @Nullable OnConnectionFailedListener onConnectionFailedListener) {
            return enableAutoManage(fragmentActivity, 0, onConnectionFailedListener);
        }

        public Builder setAccountName(String str) {
            this.f137gj = str == null ? null : new Account(str, "com.google");
            return this;
        }

        public Builder setGravityForPopups(int i) {
            this.f141xH = i;
            return this;
        }

        public Builder setHandler(@NonNull Handler handler) {
            zzaa.zzb(handler, (Object) "Handler must not be null");
            this.zzajy = handler.getLooper();
            return this;
        }

        public Builder setViewForPopups(@NonNull View view) {
            zzaa.zzb(view, (Object) "View must not be null");
            this.f142xI = view;
            return this;
        }

        public Builder useDefaultAccount() {
            return setAccountName("<<default account>>");
        }

        public zzf zzarf() {
            zzxq zzxq = zzxq.aDl;
            if (this.f145xL.containsKey(zzxo.API)) {
                zzxq = (zzxq) this.f145xL.get(zzxo.API);
            }
            return new zzf(this.f137gj, this.f139xF, this.f144xK, this.f141xH, this.f142xI, this.f138hu, this.f143xJ, zzxq);
        }
    }

    public interface ConnectionCallbacks {
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;

        void onConnected(@Nullable Bundle bundle);

        void onConnectionSuspended(int i);
    }

    public interface OnConnectionFailedListener {
        void onConnectionFailed(@NonNull ConnectionResult connectionResult);
    }

    public static void dumpAll(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        synchronized (f136xE) {
            String concat = String.valueOf(str).concat("  ");
            int i = 0;
            for (GoogleApiClient googleApiClient : f136xE) {
                int i2 = i + 1;
                printWriter.append(str).append("GoogleApiClient#").println(i);
                googleApiClient.dump(concat, fileDescriptor, printWriter, strArr);
                i = i2;
            }
        }
    }

    public static Set<GoogleApiClient> zzarc() {
        Set<GoogleApiClient> set;
        synchronized (f136xE) {
            set = f136xE;
        }
        return set;
    }

    public abstract ConnectionResult blockingConnect();

    public abstract ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit);

    public abstract PendingResult<Status> clearDefaultAccountAndReconnect();

    public abstract void connect();

    public void connect(int i) {
        throw new UnsupportedOperationException();
    }

    public abstract void disconnect();

    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    @NonNull
    public abstract ConnectionResult getConnectionResult(@NonNull Api<?> api);

    public Context getContext() {
        throw new UnsupportedOperationException();
    }

    public Looper getLooper() {
        throw new UnsupportedOperationException();
    }

    public abstract boolean hasConnectedApi(@NonNull Api<?> api);

    public abstract boolean isConnected();

    public abstract boolean isConnecting();

    public abstract boolean isConnectionCallbacksRegistered(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract boolean isConnectionFailedListenerRegistered(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    public abstract void reconnect();

    public abstract void registerConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract void registerConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    public abstract void stopAutoManage(@NonNull FragmentActivity fragmentActivity);

    public abstract void unregisterConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract void unregisterConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    @NonNull
    public <C extends zze> C zza(@NonNull zzc<C> zzc) {
        throw new UnsupportedOperationException();
    }

    public <A extends zzb, R extends Result, T extends zzqo.zza<R, A>> T zza(@NonNull T t) {
        throw new UnsupportedOperationException();
    }

    public void zza(zzsf zzsf) {
        throw new UnsupportedOperationException();
    }

    public boolean zza(@NonNull Api<?> api) {
        throw new UnsupportedOperationException();
    }

    public boolean zza(zzsa zzsa) {
        throw new UnsupportedOperationException();
    }

    public void zzard() {
        throw new UnsupportedOperationException();
    }

    public <A extends zzb, T extends zzqo.zza<? extends Result, A>> T zzb(@NonNull T t) {
        throw new UnsupportedOperationException();
    }

    public void zzb(zzsf zzsf) {
        throw new UnsupportedOperationException();
    }

    public <L> zzrr<L> zzs(@NonNull L l) {
        throw new UnsupportedOperationException();
    }
}
