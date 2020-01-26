package com.google.android.gms.internal;

import com.google.android.gms.internal.zzaqj.zza;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

final class zzaqm<T> extends zzapk<T> {
    private final zzapk<T> bol;
    private final zzaos bqh;
    private final Type bqi;

    zzaqm(zzaos zzaos, zzapk<T> zzapk, Type type) {
        this.bqh = zzaos;
        this.bol = zzapk;
        this.bqi = type;
    }

    private Type zzb(Type type, Object obj) {
        return obj != null ? (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) ? obj.getClass() : type : type;
    }

    public void zza(zzaqr zzaqr, T t) throws IOException {
        zzapk<T> zzapk = this.bol;
        Type zzb = zzb(this.bqi, t);
        if (zzb != this.bqi) {
            zzapk = this.bqh.zza(zzaqo.zzl(zzb));
            if ((zzapk instanceof zza) && !(this.bol instanceof zza)) {
                zzapk = this.bol;
            }
        }
        zzapk.zza(zzaqr, t);
    }

    public T zzb(zzaqp zzaqp) throws IOException {
        return this.bol.zzb(zzaqp);
    }
}
