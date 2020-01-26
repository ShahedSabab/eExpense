package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class zzapt implements zzapl, Cloneable {
    public static final zzapt boW = new zzapt();
    private double boX = -1.0d;
    private int boY = 136;
    private boolean boZ = true;
    private List<zzaoo> bpa = Collections.emptyList();
    private List<zzaoo> bpb = Collections.emptyList();

    private boolean zza(zzapo zzapo) {
        return zzapo == null || zzapo.mo10538bi() <= this.boX;
    }

    private boolean zza(zzapo zzapo, zzapp zzapp) {
        return zza(zzapo) && zza(zzapp);
    }

    private boolean zza(zzapp zzapp) {
        return zzapp == null || zzapp.mo10539bi() > this.boX;
    }

    private boolean zzm(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    private boolean zzn(Class<?> cls) {
        return cls.isMemberClass() && !zzo(cls);
    }

    private boolean zzo(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: bk */
    public zzapt clone() {
        try {
            return (zzapt) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public <T> zzapk<T> zza(zzaos zzaos, zzaqo<T> zzaqo) {
        Class bB = zzaqo.mo10723bB();
        final boolean zza = zza(bB, true);
        final boolean zza2 = zza(bB, false);
        if (!zza && !zza2) {
            return null;
        }
        final zzaos zzaos2 = zzaos;
        final zzaqo<T> zzaqo2 = zzaqo;
        return new zzapk<T>() {
            private zzapk<T> bol;

            /* renamed from: bg */
            private zzapk<T> m67bg() {
                zzapk<T> zzapk = this.bol;
                if (zzapk != null) {
                    return zzapk;
                }
                zzapk<T> zza = zzaos2.zza((zzapl) zzapt.this, zzaqo2);
                this.bol = zza;
                return zza;
            }

            public void zza(zzaqr zzaqr, T t) throws IOException {
                if (zza) {
                    zzaqr.mo10641bA();
                } else {
                    m67bg().zza(zzaqr, t);
                }
            }

            public T zzb(zzaqp zzaqp) throws IOException {
                if (!zza2) {
                    return m67bg().zzb(zzaqp);
                }
                zzaqp.skipValue();
                return null;
            }
        };
    }

    public zzapt zza(zzaoo zzaoo, boolean z, boolean z2) {
        zzapt bk = clone();
        if (z) {
            bk.bpa = new ArrayList(this.bpa);
            bk.bpa.add(zzaoo);
        }
        if (z2) {
            bk.bpb = new ArrayList(this.bpb);
            bk.bpb.add(zzaoo);
        }
        return bk;
    }

    public boolean zza(Class<?> cls, boolean z) {
        if (this.boX != -1.0d && !zza((zzapo) cls.getAnnotation(zzapo.class), (zzapp) cls.getAnnotation(zzapp.class))) {
            return true;
        }
        if (!this.boZ && zzn(cls)) {
            return true;
        }
        if (zzm(cls)) {
            return true;
        }
        for (zzaoo zzh : z ? this.bpa : this.bpb) {
            if (zzh.zzh(cls)) {
                return true;
            }
        }
        return false;
    }

    public boolean zza(Field field, boolean z) {
        if ((this.boY & field.getModifiers()) != 0) {
            return true;
        }
        if (this.boX != -1.0d && !zza((zzapo) field.getAnnotation(zzapo.class), (zzapp) field.getAnnotation(zzapp.class))) {
            return true;
        }
        if (field.isSynthetic()) {
            return true;
        }
        if (!this.boZ && zzn(field.getType())) {
            return true;
        }
        if (zzm(field.getType())) {
            return true;
        }
        List<zzaoo> list = z ? this.bpa : this.bpb;
        if (!list.isEmpty()) {
            zzaop zzaop = new zzaop(field);
            for (zzaoo zza : list) {
                if (zza.zza(zzaop)) {
                    return true;
                }
            }
        }
        return false;
    }

    public zzapt zzg(int... iArr) {
        zzapt bk = clone();
        bk.boY = 0;
        for (int i : iArr) {
            bk.boY = i | bk.boY;
        }
        return bk;
    }
}
