package com.google.android.gms.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzaa;

public class zzqo {

    public static abstract class zza<R extends Result, A extends com.google.android.gms.common.api.Api.zzb> extends zzqq<R> implements zzb<R> {

        /* renamed from: vS */
        private final Api<?> f528vS;

        /* renamed from: yy */
        private final zzc<A> f529yy;

        @Deprecated
        protected zza(zzc<A> zzc, GoogleApiClient googleApiClient) {
            super((GoogleApiClient) zzaa.zzb(googleApiClient, (Object) "GoogleApiClient must not be null"));
            this.f529yy = (zzc) zzaa.zzy(zzc);
            this.f528vS = null;
        }

        protected zza(Api<?> api, GoogleApiClient googleApiClient) {
            super((GoogleApiClient) zzaa.zzb(googleApiClient, (Object) "GoogleApiClient must not be null"));
            this.f529yy = api.zzaqv();
            this.f528vS = api;
        }

        private void zza(RemoteException remoteException) {
            zzaa(new Status(8, remoteException.getLocalizedMessage(), null));
        }

        public final Api<?> getApi() {
            return this.f528vS;
        }

        public /* synthetic */ void setResult(Object obj) {
            super.zzc((Result) obj);
        }

        /* access modifiers changed from: protected */
        public abstract void zza(A a) throws RemoteException;

        public final void zzaa(Status status) {
            zzaa.zzb(!status.isSuccess(), (Object) "Failed result must not be success");
            Result zzc = zzc(status);
            zzc(zzc);
            zzb((R) zzc);
        }

        public final zzc<A> zzaqv() {
            return this.f529yy;
        }

        public final void zzb(A a) throws DeadObjectException {
            try {
                zza(a);
            } catch (DeadObjectException e) {
                zza((RemoteException) e);
                throw e;
            } catch (RemoteException e2) {
                zza(e2);
            }
        }

        /* access modifiers changed from: protected */
        public void zzb(R r) {
        }
    }

    public interface zzb<R> {
        void setResult(R r);

        void zzaa(Status status);
    }
}
