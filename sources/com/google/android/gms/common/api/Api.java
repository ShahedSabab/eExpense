package com.google.android.gms.common.api;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzp;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class Api<O extends ApiOptions> {
    private final String mName;

    /* renamed from: xk */
    private final zza<?, O> f120xk;

    /* renamed from: xl */
    private final zzh<?, O> f121xl = null;

    /* renamed from: xm */
    private final zzf<?> f122xm;

    /* renamed from: xn */
    private final zzi<?> f123xn;

    public interface ApiOptions {

        public interface HasOptions extends ApiOptions {
        }

        public static final class NoOptions implements NotRequiredOptions {
            private NoOptions() {
            }
        }

        public interface NotRequiredOptions extends ApiOptions {
        }

        public interface Optional extends HasOptions, NotRequiredOptions {
        }
    }

    public static abstract class zza<T extends zze, O> extends zzd<T, O> {
        public abstract T zza(Context context, Looper looper, com.google.android.gms.common.internal.zzf zzf, O o, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener);
    }

    public interface zzb {
    }

    public static class zzc<C extends zzb> {
    }

    public static abstract class zzd<T extends zzb, O> {
        public int getPriority() {
            return Integer.MAX_VALUE;
        }

        public List<Scope> zzp(O o) {
            return Collections.emptyList();
        }
    }

    public interface zze extends zzb {
        void disconnect();

        void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

        boolean isConnected();

        boolean isConnecting();

        void zza(com.google.android.gms.common.internal.zze.zzf zzf);

        void zza(zzp zzp, Set<Scope> set);

        boolean zzain();

        boolean zzajc();

        Intent zzajd();

        boolean zzaqx();

        @Nullable
        IBinder zzaqy();
    }

    public static final class zzf<C extends zze> extends zzc<C> {
    }

    public interface zzg<T extends IInterface> extends zzb {
        void zza(int i, T t);

        T zzh(IBinder iBinder);

        String zzjx();

        String zzjy();
    }

    public static abstract class zzh<T extends zzg, O> extends zzd<T, O> {
        public abstract int zzaqz();

        public abstract T zzr(O o);
    }

    public static final class zzi<C extends zzg> extends zzc<C> {
    }

    public <C extends zze> Api(String str, zza<C, O> zza2, zzf<C> zzf2) {
        zzaa.zzb(zza2, (Object) "Cannot construct an Api with a null ClientBuilder");
        zzaa.zzb(zzf2, (Object) "Cannot construct an Api with a null ClientKey");
        this.mName = str;
        this.f120xk = zza2;
        this.f122xm = zzf2;
        this.f123xn = null;
    }

    public String getName() {
        return this.mName;
    }

    public zzd<?, O> zzaqs() {
        if (zzaqw()) {
            return null;
        }
        return this.f120xk;
    }

    public zza<?, O> zzaqt() {
        zzaa.zza(this.f120xk != null, (Object) "This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder");
        return this.f120xk;
    }

    public zzh<?, O> zzaqu() {
        zzaa.zza(false, (Object) "This API was constructed with a ClientBuilder. Use getClientBuilder");
        return null;
    }

    public zzc<?> zzaqv() {
        if (this.f122xm != null) {
            return this.f122xm;
        }
        throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
    }

    public boolean zzaqw() {
        return false;
    }
}
