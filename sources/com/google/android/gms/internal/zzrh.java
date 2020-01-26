package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzc;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzag;
import com.google.android.gms.common.internal.zze.zzf;
import com.google.android.gms.internal.zzqj.zzd;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class zzrh implements Callback {

    /* renamed from: AG */
    public static final Status f690AG = new Status(4, "Sign-out occurred while this API call was in progress.");
    /* access modifiers changed from: private */

    /* renamed from: AH */
    public static final Status f691AH = new Status(4, "The user must be signed in to make this API call.");

    /* renamed from: AJ */
    private static zzrh f692AJ;
    /* access modifiers changed from: private */
    public static final Object zzaox = new Object();
    /* access modifiers changed from: private */

    /* renamed from: AI */
    public long f693AI;
    /* access modifiers changed from: private */

    /* renamed from: AK */
    public int f694AK;

    /* renamed from: AL */
    private final AtomicInteger f695AL;

    /* renamed from: AM */
    private final AtomicInteger f696AM;
    /* access modifiers changed from: private */

    /* renamed from: AN */
    public zzqw f697AN;
    /* access modifiers changed from: private */

    /* renamed from: AO */
    public final Set<zzql<?>> f698AO;

    /* renamed from: AP */
    private final Set<zzql<?>> f699AP;
    /* access modifiers changed from: private */

    /* renamed from: Af */
    public long f700Af;
    /* access modifiers changed from: private */

    /* renamed from: Ag */
    public long f701Ag;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final Handler mHandler;
    /* access modifiers changed from: private */

    /* renamed from: xP */
    public final GoogleApiAvailability f702xP;
    /* access modifiers changed from: private */

    /* renamed from: zj */
    public final Map<zzql<?>, zza<?>> f703zj;

    class zza<O extends ApiOptions> implements ConnectionCallbacks, OnConnectionFailedListener, zzqs {

        /* renamed from: AQ */
        private final Queue<zzqj> f704AQ = new LinkedList();

        /* renamed from: AR */
        private final com.google.android.gms.common.api.Api.zzb f705AR;

        /* renamed from: AS */
        private final zzqv f706AS;

        /* renamed from: AT */
        private final Set<zzqn> f707AT = new HashSet();

        /* renamed from: AU */
        private final Map<com.google.android.gms.internal.zzrr.zzb<?>, zzrx> f708AU = new HashMap();

        /* renamed from: AV */
        private final int f709AV;

        /* renamed from: AW */
        private ConnectionResult f710AW = null;

        /* renamed from: Ae */
        private boolean f712Ae;

        /* renamed from: xB */
        private final zze f713xB;

        /* renamed from: xx */
        private final zzql<O> f714xx;

        @WorkerThread
        public zza(zzc<O> zzc) {
            if (zzc.isConnectionlessGoogleApiClient()) {
                this.f713xB = zzc.getClient();
                zzc.getClientCallbacks().zza(this);
            } else {
                this.f713xB = zzc.buildApiClient(zzrh.this.mHandler.getLooper(), this, this);
            }
            if (this.f713xB instanceof zzag) {
                this.f705AR = ((zzag) this.f713xB).zzawt();
            } else {
                this.f705AR = this.f713xB;
            }
            this.f714xx = zzc.getApiKey();
            this.f706AS = new zzqv();
            this.f709AV = zzc.getInstanceId();
        }

        /* access modifiers changed from: private */
        @WorkerThread
        public void connect() {
            if (!this.f713xB.isConnected() && !this.f713xB.isConnecting()) {
                if (this.f713xB.zzaqx() && zzrh.this.f694AK != 0) {
                    zzrh.this.f694AK = zzrh.this.f702xP.isGooglePlayServicesAvailable(zzrh.this.mContext);
                    if (zzrh.this.f694AK != 0) {
                        onConnectionFailed(new ConnectionResult(zzrh.this.f694AK, null));
                        return;
                    }
                }
                if (this.f713xB.zzain()) {
                }
                this.f713xB.zza(new zzb(this.f713xB, this.f714xx));
            }
        }

        /* access modifiers changed from: private */
        @WorkerThread
        public void resume() {
            if (this.f712Ae) {
                connect();
            }
        }

        /* access modifiers changed from: private */
        @WorkerThread
        public void zzac(Status status) {
            for (zzqj zzy : this.f704AQ) {
                zzy.zzy(status);
            }
            this.f704AQ.clear();
        }

        /* access modifiers changed from: private */
        @WorkerThread
        public void zzasx() {
            if (this.f712Ae) {
                zzatq();
                zzac(zzrh.this.f702xP.isGooglePlayServicesAvailable(zzrh.this.mContext) == 18 ? new Status(8, "Connection timed out while waiting for Google Play services update to complete.") : new Status(8, "API failed to connect while resuming due to an unknown error."));
                this.f713xB.disconnect();
            }
        }

        @WorkerThread
        private void zzatq() {
            if (this.f712Ae) {
                zzrh.this.mHandler.removeMessages(9, this.f714xx);
                zzrh.this.mHandler.removeMessages(7, this.f714xx);
                this.f712Ae = false;
            }
        }

        private void zzatr() {
            zzrh.this.mHandler.removeMessages(10, this.f714xx);
            zzrh.this.mHandler.sendMessageDelayed(zzrh.this.mHandler.obtainMessage(10, this.f714xx), zzrh.this.f693AI);
        }

        /* access modifiers changed from: private */
        public void zzats() {
            if (this.f713xB.isConnected() && this.f708AU.size() == 0) {
                if (this.f706AS.zzasi()) {
                    zzatr();
                } else {
                    this.f713xB.disconnect();
                }
            }
        }

        @WorkerThread
        private void zzb(zzqj zzqj) {
            zzqj.zza(this.f706AS, zzain());
            try {
                zzqj.zza(this);
            } catch (DeadObjectException e) {
                onConnectionSuspended(1);
                this.f713xB.disconnect();
            }
        }

        @WorkerThread
        private void zzi(ConnectionResult connectionResult) {
            for (zzqn zza : this.f707AT) {
                zza.zza(this.f714xx, connectionResult);
            }
            this.f707AT.clear();
        }

        public zze getClient() {
            return this.f713xB;
        }

        public int getInstanceId() {
            return this.f709AV;
        }

        /* access modifiers changed from: 0000 */
        public boolean isConnected() {
            return this.f713xB.isConnected();
        }

        @WorkerThread
        public void onConnected(@Nullable Bundle bundle) {
            zzato();
            zzi(ConnectionResult.f110wO);
            zzatq();
            for (zzrx zzrx : this.f708AU.values()) {
                try {
                    zzrx.f742yi.zza(this.f705AR, new TaskCompletionSource());
                } catch (DeadObjectException e) {
                    onConnectionSuspended(1);
                    this.f713xB.disconnect();
                }
            }
            zzatm();
            zzatr();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:22:0x005a, code lost:
            if (r5.f711AX.zzc(r6, r5.f709AV) != false) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0062, code lost:
            if (r6.getErrorCode() != 18) goto L_0x0067;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0064, code lost:
            r5.f712Ae = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0069, code lost:
            if (r5.f712Ae == false) goto L_0x0088;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x006b, code lost:
            com.google.android.gms.internal.zzrh.zza(r5.f711AX).sendMessageDelayed(android.os.Message.obtain(com.google.android.gms.internal.zzrh.zza(r5.f711AX), 7, r5.f714xx), com.google.android.gms.internal.zzrh.zzb(r5.f711AX));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0088, code lost:
            r2 = java.lang.String.valueOf(r5.f714xx.zzarl());
            zzac(new com.google.android.gms.common.api.Status(17, new java.lang.StringBuilder(java.lang.String.valueOf(r2).length() + 38).append("API: ").append(r2).append(" is not available on this device.").toString()));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
            return;
         */
        @android.support.annotation.WorkerThread
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onConnectionFailed(@android.support.annotation.NonNull com.google.android.gms.common.ConnectionResult r6) {
            /*
                r5 = this;
                r5.zzato()
                com.google.android.gms.internal.zzrh r0 = com.google.android.gms.internal.zzrh.this
                r1 = -1
                r0.f694AK = r1
                r5.zzi(r6)
                int r0 = r6.getErrorCode()
                r1 = 4
                if (r0 != r1) goto L_0x001b
                com.google.android.gms.common.api.Status r0 = com.google.android.gms.internal.zzrh.f691AH
                r5.zzac(r0)
            L_0x001a:
                return
            L_0x001b:
                java.util.Queue<com.google.android.gms.internal.zzqj> r0 = r5.f704AQ
                boolean r0 = r0.isEmpty()
                if (r0 == 0) goto L_0x0026
                r5.f710AW = r6
                goto L_0x001a
            L_0x0026:
                java.lang.Object r1 = com.google.android.gms.internal.zzrh.zzaox
                monitor-enter(r1)
                com.google.android.gms.internal.zzrh r0 = com.google.android.gms.internal.zzrh.this     // Catch:{ all -> 0x004e }
                com.google.android.gms.internal.zzqw r0 = r0.f697AN     // Catch:{ all -> 0x004e }
                if (r0 == 0) goto L_0x0051
                com.google.android.gms.internal.zzrh r0 = com.google.android.gms.internal.zzrh.this     // Catch:{ all -> 0x004e }
                java.util.Set r0 = r0.f698AO     // Catch:{ all -> 0x004e }
                com.google.android.gms.internal.zzql<O> r2 = r5.f714xx     // Catch:{ all -> 0x004e }
                boolean r0 = r0.contains(r2)     // Catch:{ all -> 0x004e }
                if (r0 == 0) goto L_0x0051
                com.google.android.gms.internal.zzrh r0 = com.google.android.gms.internal.zzrh.this     // Catch:{ all -> 0x004e }
                com.google.android.gms.internal.zzqw r0 = r0.f697AN     // Catch:{ all -> 0x004e }
                int r2 = r5.f709AV     // Catch:{ all -> 0x004e }
                r0.zzb(r6, r2)     // Catch:{ all -> 0x004e }
                monitor-exit(r1)     // Catch:{ all -> 0x004e }
                goto L_0x001a
            L_0x004e:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x004e }
                throw r0
            L_0x0051:
                monitor-exit(r1)     // Catch:{ all -> 0x004e }
                com.google.android.gms.internal.zzrh r0 = com.google.android.gms.internal.zzrh.this
                int r1 = r5.f709AV
                boolean r0 = r0.zzc(r6, r1)
                if (r0 != 0) goto L_0x001a
                int r0 = r6.getErrorCode()
                r1 = 18
                if (r0 != r1) goto L_0x0067
                r0 = 1
                r5.f712Ae = r0
            L_0x0067:
                boolean r0 = r5.f712Ae
                if (r0 == 0) goto L_0x0088
                com.google.android.gms.internal.zzrh r0 = com.google.android.gms.internal.zzrh.this
                android.os.Handler r0 = r0.mHandler
                com.google.android.gms.internal.zzrh r1 = com.google.android.gms.internal.zzrh.this
                android.os.Handler r1 = r1.mHandler
                r2 = 7
                com.google.android.gms.internal.zzql<O> r3 = r5.f714xx
                android.os.Message r1 = android.os.Message.obtain(r1, r2, r3)
                com.google.android.gms.internal.zzrh r2 = com.google.android.gms.internal.zzrh.this
                long r2 = r2.f701Ag
                r0.sendMessageDelayed(r1, r2)
                goto L_0x001a
            L_0x0088:
                com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status
                r1 = 17
                com.google.android.gms.internal.zzql<O> r2 = r5.f714xx
                java.lang.String r2 = r2.zzarl()
                java.lang.String r2 = java.lang.String.valueOf(r2)
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                java.lang.String r4 = java.lang.String.valueOf(r2)
                int r4 = r4.length()
                int r4 = r4 + 38
                r3.<init>(r4)
                java.lang.String r4 = "API: "
                java.lang.StringBuilder r3 = r3.append(r4)
                java.lang.StringBuilder r2 = r3.append(r2)
                java.lang.String r3 = " is not available on this device."
                java.lang.StringBuilder r2 = r2.append(r3)
                java.lang.String r2 = r2.toString()
                r0.<init>(r1, r2)
                r5.zzac(r0)
                goto L_0x001a
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzrh.zza.onConnectionFailed(com.google.android.gms.common.ConnectionResult):void");
        }

        @WorkerThread
        public void onConnectionSuspended(int i) {
            zzato();
            this.f712Ae = true;
            this.f706AS.zzask();
            zzrh.this.mHandler.sendMessageDelayed(Message.obtain(zzrh.this.mHandler, 7, this.f714xx), zzrh.this.f701Ag);
            zzrh.this.mHandler.sendMessageDelayed(Message.obtain(zzrh.this.mHandler, 9, this.f714xx), zzrh.this.f700Af);
            zzrh.this.f694AK = -1;
        }

        @WorkerThread
        public void signOut() {
            zzac(zzrh.f690AG);
            this.f706AS.zzasj();
            for (com.google.android.gms.internal.zzrr.zzb zze : this.f708AU.keySet()) {
                zza(new zzqj.zze(zze, new TaskCompletionSource()));
            }
            this.f713xB.disconnect();
        }

        public void zza(ConnectionResult connectionResult, Api<?> api, int i) {
            onConnectionFailed(connectionResult);
        }

        @WorkerThread
        public void zza(zzqj zzqj) {
            if (this.f713xB.isConnected()) {
                zzb(zzqj);
                zzatr();
                return;
            }
            this.f704AQ.add(zzqj);
            if (this.f710AW == null || !this.f710AW.hasResolution()) {
                connect();
            } else {
                onConnectionFailed(this.f710AW);
            }
        }

        public boolean zzain() {
            return this.f713xB.zzain();
        }

        @WorkerThread
        public void zzatm() {
            while (this.f713xB.isConnected() && !this.f704AQ.isEmpty()) {
                zzb((zzqj) this.f704AQ.remove());
            }
        }

        public Map<com.google.android.gms.internal.zzrr.zzb<?>, zzrx> zzatn() {
            return this.f708AU;
        }

        @WorkerThread
        public void zzato() {
            this.f710AW = null;
        }

        /* access modifiers changed from: 0000 */
        public ConnectionResult zzatp() {
            return this.f710AW;
        }

        @WorkerThread
        public void zzb(zzqn zzqn) {
            this.f707AT.add(zzqn);
        }
    }

    private class zzb implements zzf {

        /* renamed from: xB */
        private final zze f716xB;

        /* renamed from: xx */
        private final zzql<?> f717xx;

        public zzb(zze zze, zzql<?> zzql) {
            this.f716xB = zze;
            this.f717xx = zzql;
        }

        @WorkerThread
        public void zzg(@NonNull ConnectionResult connectionResult) {
            if (!connectionResult.isSuccess()) {
                ((zza) zzrh.this.f703zj.get(this.f717xx)).onConnectionFailed(connectionResult);
            } else if (!this.f716xB.zzain()) {
                this.f716xB.zza(null, Collections.emptySet());
            }
        }
    }

    private zzrh(Context context) {
        this(context, GoogleApiAvailability.getInstance());
    }

    private zzrh(Context context, GoogleApiAvailability googleApiAvailability) {
        this.f701Ag = 5000;
        this.f700Af = 120000;
        this.f693AI = 10000;
        this.f694AK = -1;
        this.f695AL = new AtomicInteger(1);
        this.f696AM = new AtomicInteger(0);
        this.f703zj = new ConcurrentHashMap(5, 0.75f, 1);
        this.f697AN = null;
        this.f698AO = new com.google.android.gms.common.util.zza();
        this.f699AP = new com.google.android.gms.common.util.zza();
        this.mContext = context;
        HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper(), this);
        this.f702xP = googleApiAvailability;
    }

    @WorkerThread
    private void zza(int i, ConnectionResult connectionResult) {
        zza zza2;
        Iterator it = this.f703zj.values().iterator();
        while (true) {
            if (!it.hasNext()) {
                zza2 = null;
                break;
            }
            zza2 = (zza) it.next();
            if (zza2.getInstanceId() == i) {
                break;
            }
        }
        if (zza2 != null) {
            String valueOf = String.valueOf(this.f702xP.getErrorString(connectionResult.getErrorCode()));
            String valueOf2 = String.valueOf(connectionResult.getErrorMessage());
            zza2.zzac(new Status(17, new StringBuilder(String.valueOf(valueOf).length() + 69 + String.valueOf(valueOf2).length()).append("Error resolution was canceled by the user, original error message: ").append(valueOf).append(": ").append(valueOf2).toString()));
            return;
        }
        Log.wtf("GoogleApiManager", "Could not find API instance " + i + " while trying to fail enqueued calls.", new Exception());
    }

    @WorkerThread
    private void zza(zzrv zzrv) {
        zza zza2 = (zza) this.f703zj.get(zzrv.f740Bs.getApiKey());
        if (zza2 == null) {
            zzb(zzrv.f740Bs);
            zza2 = (zza) this.f703zj.get(zzrv.f740Bs.getApiKey());
        }
        if (!zza2.zzain() || this.f696AM.get() == zzrv.f739Br) {
            zza2.zza(zzrv.f738Bq);
            return;
        }
        zzrv.f738Bq.zzy(f690AG);
        zza2.signOut();
    }

    public static zzrh zzatg() {
        zzrh zzrh;
        synchronized (zzaox) {
            zzaa.zzb(f692AJ, (Object) "Must guarantee manager is non-null before using getInstance");
            zzrh = f692AJ;
        }
        return zzrh;
    }

    @WorkerThread
    private void zzati() {
        for (zza zza2 : this.f703zj.values()) {
            zza2.zzato();
            zza2.connect();
        }
    }

    @WorkerThread
    private void zzb(zzc<?> zzc) {
        zzql apiKey = zzc.getApiKey();
        if (!this.f703zj.containsKey(apiKey)) {
            this.f703zj.put(apiKey, new zza(zzc));
        }
        zza zza2 = (zza) this.f703zj.get(apiKey);
        if (zza2.zzain()) {
            this.f699AP.add(apiKey);
        }
        zza2.connect();
    }

    public static zzrh zzbx(Context context) {
        zzrh zzrh;
        synchronized (zzaox) {
            if (f692AJ == null) {
                f692AJ = new zzrh(context.getApplicationContext());
            }
            zzrh = f692AJ;
        }
        return zzrh;
    }

    @WorkerThread
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                zza((zzqn) message.obj);
                break;
            case 2:
                zzati();
                break;
            case 3:
            case 6:
            case 11:
                zza((zzrv) message.obj);
                break;
            case 4:
                zza(message.arg1, (ConnectionResult) message.obj);
                break;
            case 5:
                zzb((zzc) message.obj);
                break;
            case 7:
                if (this.f703zj.containsKey(message.obj)) {
                    ((zza) this.f703zj.get(message.obj)).resume();
                    break;
                }
                break;
            case 8:
                zzatj();
                break;
            case 9:
                if (this.f703zj.containsKey(message.obj)) {
                    ((zza) this.f703zj.get(message.obj)).zzasx();
                    break;
                }
                break;
            case 10:
                if (this.f703zj.containsKey(message.obj)) {
                    ((zza) this.f703zj.get(message.obj)).zzats();
                    break;
                }
                break;
            default:
                Log.w("GoogleApiManager", "Unknown message id: " + message.what);
                return false;
        }
        return true;
    }

    public <O extends ApiOptions> Task<Void> zza(@NonNull zzc<O> zzc, @NonNull com.google.android.gms.internal.zzrr.zzb<?> zzb2) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.mHandler.sendMessage(this.mHandler.obtainMessage(11, new zzrv(new zzqj.zze(zzb2, taskCompletionSource), this.f696AM.get(), zzc)));
        return taskCompletionSource.getTask();
    }

    public <O extends ApiOptions> Task<Void> zza(@NonNull zzc<O> zzc, @NonNull zzrw<com.google.android.gms.common.api.Api.zzb> zzrw, @NonNull zzsh<com.google.android.gms.common.api.Api.zzb> zzsh) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.mHandler.sendMessage(this.mHandler.obtainMessage(6, new zzrv(new zzqj.zzc(new zzrx(zzrw, zzsh), taskCompletionSource), this.f696AM.get(), zzc)));
        return taskCompletionSource.getTask();
    }

    public Task<Void> zza(Iterable<zzc<?>> iterable) {
        zzqn zzqn = new zzqn(iterable);
        for (zzc apiKey : iterable) {
            zza zza2 = (zza) this.f703zj.get(apiKey.getApiKey());
            if (zza2 != null) {
                if (!zza2.isConnected()) {
                }
            }
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, zzqn));
            return zzqn.getTask();
        }
        zzqn.zzarp();
        return zzqn.getTask();
    }

    public void zza(ConnectionResult connectionResult, int i) {
        if (!zzc(connectionResult, i)) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(4, i, 0, connectionResult));
        }
    }

    public void zza(zzc<?> zzc) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(5, zzc));
    }

    public <O extends ApiOptions> void zza(zzc<O> zzc, int i, com.google.android.gms.internal.zzqo.zza<? extends Result, com.google.android.gms.common.api.Api.zzb> zza2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, new zzrv(new com.google.android.gms.internal.zzqj.zzb(i, zza2), this.f696AM.get(), zzc)));
    }

    public <O extends ApiOptions, TResult> void zza(zzc<O> zzc, int i, zzse<com.google.android.gms.common.api.Api.zzb, TResult> zzse, TaskCompletionSource<TResult> taskCompletionSource, zzsb zzsb) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, new zzrv(new zzd(i, zzse, taskCompletionSource, zzsb), this.f696AM.get(), zzc)));
    }

    @WorkerThread
    public void zza(zzqn zzqn) {
        for (zzql zzql : zzqn.zzaro()) {
            zza zza2 = (zza) this.f703zj.get(zzql);
            if (zza2 == null) {
                zzqn.zza(zzql, new ConnectionResult(13));
                return;
            } else if (zza2.isConnected()) {
                zzqn.zza(zzql, ConnectionResult.f110wO);
            } else if (zza2.zzatp() != null) {
                zzqn.zza(zzql, zza2.zzatp());
            } else {
                zza2.zzb(zzqn);
            }
        }
    }

    public void zza(@NonNull zzqw zzqw) {
        synchronized (zzaox) {
            if (this.f697AN != zzqw) {
                this.f697AN = zzqw;
                this.f698AO.clear();
                this.f698AO.addAll(zzqw.zzasl());
            }
        }
    }

    public void zzarm() {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(2));
    }

    public int zzath() {
        return this.f695AL.getAndIncrement();
    }

    @WorkerThread
    public void zzatj() {
        for (zzql remove : this.f699AP) {
            ((zza) this.f703zj.remove(remove)).signOut();
        }
        this.f699AP.clear();
    }

    /* access modifiers changed from: 0000 */
    public void zzb(@NonNull zzqw zzqw) {
        synchronized (zzaox) {
            if (this.f697AN == zzqw) {
                this.f697AN = null;
                this.f698AO.clear();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean zzc(ConnectionResult connectionResult, int i) {
        if (!connectionResult.hasResolution() && !this.f702xP.isUserResolvableError(connectionResult.getErrorCode())) {
            return false;
        }
        this.f702xP.zza(this.mContext, connectionResult, i);
        return true;
    }
}
