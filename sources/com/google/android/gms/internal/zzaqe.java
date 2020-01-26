package com.google.android.gms.internal;

public final class zzaqe implements zzapl {
    private final zzaps bod;

    public zzaqe(zzaps zzaps) {
        this.bod = zzaps;
    }

    static zzapk<?> zza(zzaps zzaps, zzaos zzaos, zzaqo<?> zzaqo, zzapm zzapm) {
        Class value = zzapm.value();
        if (zzapk.class.isAssignableFrom(value)) {
            return (zzapk) zzaps.zzb(zzaqo.zzr(value)).mo10557bj();
        }
        if (zzapl.class.isAssignableFrom(value)) {
            return ((zzapl) zzaps.zzb(zzaqo.zzr(value)).mo10557bj()).zza(zzaos, zzaqo);
        }
        throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter or TypeAdapterFactory reference.");
    }

    public <T> zzapk<T> zza(zzaos zzaos, zzaqo<T> zzaqo) {
        zzapm zzapm = (zzapm) zzaqo.mo10723bB().getAnnotation(zzapm.class);
        if (zzapm == null) {
            return null;
        }
        return zza(this.bod, zzaos, zzaqo, zzapm);
    }
}
