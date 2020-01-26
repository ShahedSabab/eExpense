package com.google.android.gms.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzqj {

    /* renamed from: nV */
    public final int f505nV;

    private static abstract class zza extends zzqj {

        /* renamed from: yg */
        protected final TaskCompletionSource<Void> f506yg;

        public zza(int i, TaskCompletionSource<Void> taskCompletionSource) {
            super(i);
            this.f506yg = taskCompletionSource;
        }

        private void zza(RemoteException remoteException) {
            zzy(new Status(8, remoteException.getLocalizedMessage(), null));
        }

        public void zza(@NonNull zzqv zzqv, boolean z) {
        }

        public final void zza(zza<?> zza) throws DeadObjectException {
            try {
                zzb(zza);
            } catch (DeadObjectException e) {
                zza((RemoteException) e);
                throw e;
            } catch (RemoteException e2) {
                zza(e2);
            }
        }

        /* access modifiers changed from: protected */
        public abstract void zzb(zza<?> zza) throws RemoteException;

        public void zzy(@NonNull Status status) {
            this.f506yg.trySetException(new com.google.android.gms.common.api.zza(status));
        }
    }

    public static class zzb<A extends com.google.android.gms.internal.zzqo.zza<? extends Result, com.google.android.gms.common.api.Api.zzb>> extends zzqj {

        /* renamed from: yh */
        protected final A f507yh;

        public zzb(int i, A a) {
            super(i);
            this.f507yh = a;
        }

        public void zza(@NonNull zzqv zzqv, boolean z) {
            zzqv.zza((zzqq<? extends Result>) this.f507yh, z);
        }

        public void zza(zza<?> zza) throws DeadObjectException {
            this.f507yh.zzb(zza.getClient());
        }

        public void zzy(@NonNull Status status) {
            this.f507yh.zzaa(status);
        }
    }

    public static final class zzc extends zza {

        /* renamed from: yi */
        public final zzrw<com.google.android.gms.common.api.Api.zzb> f508yi;

        /* renamed from: yj */
        public final zzsh<com.google.android.gms.common.api.Api.zzb> f509yj;

        public zzc(zzrx zzrx, TaskCompletionSource<Void> taskCompletionSource) {
            super(3, taskCompletionSource);
            this.f508yi = zzrx.f742yi;
            this.f509yj = zzrx.f743yj;
        }

        public /* bridge */ /* synthetic */ void zza(@NonNull zzqv zzqv, boolean z) {
            super.zza(zzqv, z);
        }

        public void zzb(zza<?> zza) throws DeadObjectException {
            this.f508yi.zza(zza.getClient(), this.f506yg);
            if (this.f508yi.zzatz() != null) {
                zza.zzatn().put(this.f508yi.zzatz(), new zzrx(this.f508yi, this.f509yj));
            }
        }

        public /* bridge */ /* synthetic */ void zzy(@NonNull Status status) {
            super.zzy(status);
        }
    }

    public static final class zzd<TResult> extends zzqj {

        /* renamed from: ym */
        private static final Status f510ym = new Status(8, "Connection to Google Play services was lost while executing the API call.");

        /* renamed from: yg */
        private final TaskCompletionSource<TResult> f511yg;

        /* renamed from: yk */
        private final zzse<com.google.android.gms.common.api.Api.zzb, TResult> f512yk;

        /* renamed from: yl */
        private final zzsb f513yl;

        public zzd(int i, zzse<com.google.android.gms.common.api.Api.zzb, TResult> zzse, TaskCompletionSource<TResult> taskCompletionSource, zzsb zzsb) {
            super(i);
            this.f511yg = taskCompletionSource;
            this.f512yk = zzse;
            this.f513yl = zzsb;
        }

        public void zza(@NonNull zzqv zzqv, boolean z) {
            zzqv.zza(this.f511yg, z);
        }

        public void zza(zza<?> zza) throws DeadObjectException {
            try {
                this.f512yk.zzb(zza.getClient(), this.f511yg);
            } catch (DeadObjectException e) {
                throw e;
            } catch (RemoteException e2) {
                zzy(f510ym);
            }
        }

        public void zzy(@NonNull Status status) {
            this.f511yg.trySetException(this.f513yl.zzz(status));
        }
    }

    public static final class zze extends zza {

        /* renamed from: yn */
        public final com.google.android.gms.internal.zzrr.zzb<?> f514yn;

        public zze(com.google.android.gms.internal.zzrr.zzb<?> zzb, TaskCompletionSource<Void> taskCompletionSource) {
            super(4, taskCompletionSource);
            this.f514yn = zzb;
        }

        public /* bridge */ /* synthetic */ void zza(@NonNull zzqv zzqv, boolean z) {
            super.zza(zzqv, z);
        }

        public void zzb(zza<?> zza) throws DeadObjectException {
            zzrx zzrx = (zzrx) zza.zzatn().remove(this.f514yn);
            if (zzrx != null) {
                zzrx.f743yj.zzc(zza.getClient(), this.f506yg);
                zzrx.f742yi.zzaua();
                return;
            }
            Log.wtf("UnregisterListenerTask", "Received call to unregister a listener without a matching registration call.", new Exception());
            this.f506yg.trySetException(new com.google.android.gms.common.api.zza(Status.f160yb));
        }

        public /* bridge */ /* synthetic */ void zzy(@NonNull Status status) {
            super.zzy(status);
        }
    }

    public zzqj(int i) {
        this.f505nV = i;
    }

    public abstract void zza(@NonNull zzqv zzqv, boolean z);

    public abstract void zza(zza<?> zza2) throws DeadObjectException;

    public abstract void zzy(@NonNull Status status);
}
