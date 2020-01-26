package com.google.android.gms.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzaa;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

public class zzrs {

    /* renamed from: rK */
    private final Set<zzrr<?>> f735rK = Collections.newSetFromMap(new WeakHashMap());

    public static <L> zzrr<L> zzb(@NonNull L l, @NonNull Looper looper, @NonNull String str) {
        zzaa.zzb(l, (Object) "Listener must not be null");
        zzaa.zzb(looper, (Object) "Looper must not be null");
        zzaa.zzb(str, (Object) "Listener type must not be null");
        return new zzrr<>(looper, l, str);
    }

    public void release() {
        for (zzrr clear : this.f735rK) {
            clear.clear();
        }
        this.f735rK.clear();
    }

    public <L> zzrr<L> zza(@NonNull L l, @NonNull Looper looper, @NonNull String str) {
        zzrr<L> zzb = zzb(l, looper, str);
        this.f735rK.add(zzb);
        return zzb;
    }

    public <L> zzrr<L> zzb(@NonNull L l, Looper looper) {
        return zza(l, looper, "NO_TYPE");
    }
}
