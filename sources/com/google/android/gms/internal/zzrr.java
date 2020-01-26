package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzaa;

public final class zzrr<L> {

    /* renamed from: Bl */
    private final zza f731Bl;

    /* renamed from: Bm */
    private final zzb<L> f732Bm;
    private volatile L mListener;

    private final class zza extends Handler {
        public zza(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            boolean z = true;
            if (message.what != 1) {
                z = false;
            }
            zzaa.zzbt(z);
            zzrr.this.zzb((zzc) message.obj);
        }
    }

    public static final class zzb<L> {

        /* renamed from: Bo */
        private final String f734Bo;
        private final L mListener;

        private zzb(L l, String str) {
            this.mListener = l;
            this.f734Bo = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) obj;
            return this.mListener == zzb.mListener && this.f734Bo.equals(zzb.f734Bo);
        }

        public int hashCode() {
            return (System.identityHashCode(this.mListener) * 31) + this.f734Bo.hashCode();
        }
    }

    public interface zzc<L> {
        void zzasm();

        void zzt(L l);
    }

    zzrr(@NonNull Looper looper, @NonNull L l, @NonNull String str) {
        this.f731Bl = new zza(looper);
        this.mListener = zzaa.zzb(l, (Object) "Listener must not be null");
        this.f732Bm = new zzb<>(l, zzaa.zzib(str));
    }

    public void clear() {
        this.mListener = null;
    }

    public void zza(zzc<? super L> zzc2) {
        zzaa.zzb(zzc2, (Object) "Notifier must not be null");
        this.f731Bl.sendMessage(this.f731Bl.obtainMessage(1, zzc2));
    }

    @NonNull
    public zzb<L> zzatz() {
        return this.f732Bm;
    }

    /* access modifiers changed from: 0000 */
    public void zzb(zzc<? super L> zzc2) {
        L l = this.mListener;
        if (l == null) {
            zzc2.zzasm();
            return;
        }
        try {
            zzc2.zzt(l);
        } catch (RuntimeException e) {
            zzc2.zzasm();
            throw e;
        }
    }
}
