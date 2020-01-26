package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzapk<T> {
    public abstract void zza(zzaqr zzaqr, T t) throws IOException;

    public abstract T zzb(zzaqp zzaqp) throws IOException;

    public final zzaoy zzcn(T t) {
        try {
            zzaqg zzaqg = new zzaqg();
            zza(zzaqg, t);
            return zzaqg.mo10642bu();
        } catch (IOException e) {
            throw new zzaoz((Throwable) e);
        }
    }
}
