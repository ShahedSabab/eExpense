package com.google.android.gms.internal;

import java.io.IOException;

final class zzapj<T> extends zzapk<T> {
    private final zzapg<T> boA;
    private final zzaox<T> boB;
    private final zzaos boC;
    private final zzaqo<T> boD;
    private final zzapl boE;
    private zzapk<T> bol;

    private static class zza implements zzapl {
        private final zzapg<?> boA;
        private final zzaox<?> boB;
        private final zzaqo<?> boF;
        private final boolean boG;
        private final Class<?> boH;

        private zza(Object obj, zzaqo<?> zzaqo, boolean z, Class<?> cls) {
            this.boA = obj instanceof zzapg ? (zzapg) obj : null;
            this.boB = obj instanceof zzaox ? (zzaox) obj : null;
            zzapq.zzbt((this.boA == null && this.boB == null) ? false : true);
            this.boF = zzaqo;
            this.boG = z;
            this.boH = cls;
        }

        public <T> zzapk<T> zza(zzaos zzaos, zzaqo<T> zzaqo) {
            boolean isAssignableFrom = this.boF != null ? this.boF.equals(zzaqo) || (this.boG && this.boF.mo10724bC() == zzaqo.mo10723bB()) : this.boH.isAssignableFrom(zzaqo.mo10723bB());
            if (isAssignableFrom) {
                return new zzapj(this.boA, this.boB, zzaos, zzaqo, this);
            }
            return null;
        }
    }

    private zzapj(zzapg<T> zzapg, zzaox<T> zzaox, zzaos zzaos, zzaqo<T> zzaqo, zzapl zzapl) {
        this.boA = zzapg;
        this.boB = zzaox;
        this.boC = zzaos;
        this.boD = zzaqo;
        this.boE = zzapl;
    }

    /* renamed from: bg */
    private zzapk<T> m50bg() {
        zzapk<T> zzapk = this.bol;
        if (zzapk != null) {
            return zzapk;
        }
        zzapk<T> zza2 = this.boC.zza(this.boE, this.boD);
        this.bol = zza2;
        return zza2;
    }

    public static zzapl zza(zzaqo<?> zzaqo, Object obj) {
        return new zza(obj, zzaqo, false, null);
    }

    public static zzapl zzb(zzaqo<?> zzaqo, Object obj) {
        return new zza(obj, zzaqo, zzaqo.mo10724bC() == zzaqo.mo10723bB(), null);
    }

    public void zza(zzaqr zzaqr, T t) throws IOException {
        if (this.boA == null) {
            m50bg().zza(zzaqr, t);
        } else if (t == null) {
            zzaqr.mo10641bA();
        } else {
            zzapz.zzb(this.boA.zza(t, this.boD.mo10724bC(), this.boC.boj), zzaqr);
        }
    }

    public T zzb(zzaqp zzaqp) throws IOException {
        if (this.boB == null) {
            return m50bg().zzb(zzaqp);
        }
        zzaoy zzh = zzapz.zzh(zzaqp);
        if (zzh.mo10507aY()) {
            return null;
        }
        try {
            return this.boB.zzb(zzh, this.boD.mo10724bC(), this.boC.boi);
        } catch (zzapc e) {
            throw e;
        } catch (Exception e2) {
            throw new zzapc((Throwable) e2);
        }
    }
}
