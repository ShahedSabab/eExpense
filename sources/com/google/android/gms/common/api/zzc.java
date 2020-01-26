package com.google.android.gms.common.api;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Api.zzh;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzag;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.internal.zzqk;
import com.google.android.gms.internal.zzql;
import com.google.android.gms.internal.zzqo.zza;
import com.google.android.gms.internal.zzqr;
import com.google.android.gms.internal.zzqw;
import com.google.android.gms.internal.zzrh;
import com.google.android.gms.internal.zzri;
import com.google.android.gms.internal.zzrr;
import com.google.android.gms.internal.zzrs;
import com.google.android.gms.internal.zzrw;
import com.google.android.gms.internal.zzsb;
import com.google.android.gms.internal.zzse;
import com.google.android.gms.internal.zzsh;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzc<O extends ApiOptions> {
    private final Context mContext;
    private final int mId;

    /* renamed from: vS */
    private final Api<O> f169vS;

    /* renamed from: xA */
    private final zzsb f170xA;

    /* renamed from: xB */
    private final zze f171xB;

    /* renamed from: xC */
    private final zzqr f172xC;

    /* renamed from: xw */
    private final O f173xw;

    /* renamed from: xx */
    private final zzql<O> f174xx;

    /* renamed from: xy */
    private final zzrh f175xy;

    /* renamed from: xz */
    private final GoogleApiClient f176xz;
    private final Looper zzajy;

    @MainThread
    public zzc(@NonNull Activity activity, Api<O> api, O o, Looper looper, zzsb zzsb) {
        zzaa.zzb(activity, (Object) "Null activity is not permitted.");
        zzaa.zzb(api, (Object) "Api must not be null.");
        zzaa.zzb(looper, (Object) "Looper must not be null.");
        this.mContext = activity.getApplicationContext();
        this.f169vS = api;
        this.f173xw = o;
        this.zzajy = looper;
        this.f174xx = zzql.zza(this.f169vS, this.f173xw);
        this.f176xz = new zzri(this);
        this.f175xy = zzrh.zzbx(this.mContext);
        this.mId = this.f175xy.zzath();
        this.f170xA = zzsb;
        this.f171xB = null;
        this.f172xC = null;
        zzqw.zza(activity, this.f175xy, this.f174xx);
        this.f175xy.zza(this);
    }

    public zzc(@NonNull Activity activity, Api<O> api, O o, zzsb zzsb) {
        this(activity, api, o, activity.getMainLooper(), zzsb);
    }

    protected zzc(@NonNull Context context, Api<O> api, Looper looper, zze zze, zzqr zzqr) {
        zzaa.zzb(context, (Object) "Null context is not permitted.");
        zzaa.zzb(api, (Object) "Api must not be null.");
        zzaa.zzb(looper, (Object) "Looper must not be null.");
        this.mContext = context.getApplicationContext();
        this.f169vS = api;
        this.f173xw = null;
        this.zzajy = looper;
        this.f174xx = zzql.zzb(api);
        this.f176xz = new zzri(this);
        this.f175xy = zzrh.zzbx(this.mContext);
        this.mId = this.f175xy.zzath();
        this.f170xA = new zzqk();
        this.f171xB = zze;
        this.f172xC = zzqr;
        this.f175xy.zza(this);
    }

    public zzc(@NonNull Context context, Api<O> api, O o, Looper looper, zzsb zzsb) {
        zzaa.zzb(context, (Object) "Null context is not permitted.");
        zzaa.zzb(api, (Object) "Api must not be null.");
        zzaa.zzb(looper, (Object) "Looper must not be null.");
        this.mContext = context.getApplicationContext();
        this.f169vS = api;
        this.f173xw = o;
        this.zzajy = looper;
        this.f174xx = zzql.zza(this.f169vS, this.f173xw);
        this.f176xz = new zzri(this);
        this.f175xy = zzrh.zzbx(this.mContext);
        this.mId = this.f175xy.zzath();
        this.f170xA = zzsb;
        this.f171xB = null;
        this.f172xC = null;
        this.f175xy.zza(this);
    }

    public zzc(@NonNull Context context, Api<O> api, O o, zzsb zzsb) {
        this(context, api, o, Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper(), zzsb);
    }

    private <A extends zzb, T extends zza<? extends Result, A>> T zza(int i, @NonNull T t) {
        t.zzarv();
        this.f175xy.zza(this, i, (zza<? extends Result, zzb>) t);
        return t;
    }

    private <TResult, A extends zzb> Task<TResult> zza(int i, @NonNull zzse<A, TResult> zzse) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.f175xy.zza(this, i, zzse, taskCompletionSource, this.f170xA);
        return taskCompletionSource.getTask();
    }

    public GoogleApiClient asGoogleApiClient() {
        return this.f176xz;
    }

    @WorkerThread
    public zze buildApiClient(Looper looper, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        zzaa.zza(this.f171xB == null, (Object) "Client is already built, use getClient(). getClientCallbacks() should also be provided with a helper.");
        if (this.f169vS.zzaqw()) {
            zzh zzaqu = this.f169vS.zzaqu();
            return new zzag(this.mContext, looper, zzaqu.zzaqz(), connectionCallbacks, onConnectionFailedListener, zzf.zzca(this.mContext), zzaqu.zzr(this.f173xw));
        }
        return this.f169vS.zzaqt().zza(this.mContext, looper, zzf.zzca(this.mContext), this.f173xw, connectionCallbacks, onConnectionFailedListener);
    }

    public <A extends zzb, T extends zza<? extends Result, A>> T doBestEffortWrite(@NonNull T t) {
        return zza(2, t);
    }

    public <TResult, A extends zzb> Task<TResult> doBestEffortWrite(zzse<A, TResult> zzse) {
        return zza(2, zzse);
    }

    public <A extends zzb, T extends zza<? extends Result, A>> T doRead(@NonNull T t) {
        return zza(0, t);
    }

    public <TResult, A extends zzb> Task<TResult> doRead(zzse<A, TResult> zzse) {
        return zza(0, zzse);
    }

    public <A extends zzb, T extends zzrw<A>, U extends zzsh<A>> Task<Void> doRegisterEventListener(@NonNull T t, U u) {
        zzaa.zzy(t);
        zzaa.zzy(u);
        zzaa.zzb(t.zzatz(), (Object) "Listener has already been released.");
        zzaa.zzb(u.zzatz(), (Object) "Listener has already been released.");
        zzaa.zzb(t.zzatz().equals(u.zzatz()), (Object) "Listener registration and unregistration methods must be constructed with the same ListenerHolder.");
        return this.f175xy.zza(this, (zzrw<zzb>) t, (zzsh<zzb>) u);
    }

    public Task<Void> doUnregisterEventListener(@NonNull zzrr.zzb<?> zzb) {
        zzaa.zzb(zzb, (Object) "Listener key cannot be null.");
        return this.f175xy.zza(this, zzb);
    }

    public <A extends zzb, T extends zza<? extends Result, A>> T doWrite(@NonNull T t) {
        return zza(1, t);
    }

    public <TResult, A extends zzb> Task<TResult> doWrite(zzse<A, TResult> zzse) {
        return zza(1, zzse);
    }

    public Api<O> getApi() {
        return this.f169vS;
    }

    public zzql<O> getApiKey() {
        return this.f174xx;
    }

    public O getApiOptions() {
        return this.f173xw;
    }

    public Context getApplicationContext() {
        return this.mContext;
    }

    public zze getClient() {
        return (zze) zzaa.zzb(this.f171xB, (Object) "Client is null, buildApiClient() should be used.");
    }

    public zzqr getClientCallbacks() {
        return (zzqr) zzaa.zzb(this.f172xC, (Object) "ClientCallbacks is null.");
    }

    public int getInstanceId() {
        return this.mId;
    }

    public Looper getLooper() {
        return this.zzajy;
    }

    public boolean isConnectionlessGoogleApiClient() {
        return (this.f171xB == null || this.f172xC == null) ? false : true;
    }

    public <L> zzrr<L> registerListener(@NonNull L l, String str) {
        return zzrs.zzb(l, this.zzajy, str);
    }
}
