package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zzqq;
import com.google.android.gms.internal.zzru;
import com.google.android.gms.internal.zzsc;

public final class PendingResults {

    private static final class zza<R extends Result> extends zzqq<R> {

        /* renamed from: xU */
        private final R f154xU;

        public zza(R r) {
            super(Looper.getMainLooper());
            this.f154xU = r;
        }

        /* access modifiers changed from: protected */
        public R zzc(Status status) {
            if (status.getStatusCode() == this.f154xU.getStatus().getStatusCode()) {
                return this.f154xU;
            }
            throw new UnsupportedOperationException("Creating failed results is not supported");
        }
    }

    private static final class zzb<R extends Result> extends zzqq<R> {

        /* renamed from: xV */
        private final R f155xV;

        public zzb(GoogleApiClient googleApiClient, R r) {
            super(googleApiClient);
            this.f155xV = r;
        }

        /* access modifiers changed from: protected */
        public R zzc(Status status) {
            return this.f155xV;
        }
    }

    private static final class zzc<R extends Result> extends zzqq<R> {
        public zzc(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* access modifiers changed from: protected */
        public R zzc(Status status) {
            throw new UnsupportedOperationException("Creating failed results is not supported");
        }
    }

    private PendingResults() {
    }

    public static PendingResult<Status> canceledPendingResult() {
        zzsc zzsc = new zzsc(Looper.getMainLooper());
        zzsc.cancel();
        return zzsc;
    }

    public static <R extends Result> PendingResult<R> canceledPendingResult(R r) {
        zzaa.zzb(r, (Object) "Result must not be null");
        zzaa.zzb(r.getStatus().getStatusCode() == 16, (Object) "Status code must be CommonStatusCodes.CANCELED");
        zza zza2 = new zza(r);
        zza2.cancel();
        return zza2;
    }

    public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(R r) {
        zzaa.zzb(r, (Object) "Result must not be null");
        zzc zzc2 = new zzc(null);
        zzc2.zzc(r);
        return new zzru(zzc2);
    }

    public static PendingResult<Status> immediatePendingResult(Status status) {
        zzaa.zzb(status, (Object) "Result must not be null");
        zzsc zzsc = new zzsc(Looper.getMainLooper());
        zzsc.zzc(status);
        return zzsc;
    }

    public static <R extends Result> PendingResult<R> zza(R r, GoogleApiClient googleApiClient) {
        zzaa.zzb(r, (Object) "Result must not be null");
        zzaa.zzb(!r.getStatus().isSuccess(), (Object) "Status code must not be SUCCESS");
        zzb zzb2 = new zzb(googleApiClient, r);
        zzb2.zzc(r);
        return zzb2;
    }

    public static PendingResult<Status> zza(Status status, GoogleApiClient googleApiClient) {
        zzaa.zzb(status, (Object) "Result must not be null");
        zzsc zzsc = new zzsc(googleApiClient);
        zzsc.zzc(status);
        return zzsc;
    }

    public static <R extends Result> OptionalPendingResult<R> zzb(R r, GoogleApiClient googleApiClient) {
        zzaa.zzb(r, (Object) "Result must not be null");
        zzc zzc2 = new zzc(googleApiClient);
        zzc2.zzc(r);
        return new zzru(zzc2);
    }
}
