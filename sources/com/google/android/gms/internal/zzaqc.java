package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

public final class zzaqc implements zzapl {
    private final zzaps bod;

    private static final class zza<E> extends zzapk<Collection<E>> {
        private final zzapk<E> bpJ;
        private final zzapx<? extends Collection<E>> bpK;

        public zza(zzaos zzaos, Type type, zzapk<E> zzapk, zzapx<? extends Collection<E>> zzapx) {
            this.bpJ = new zzaqm(zzaos, zzapk, type);
            this.bpK = zzapx;
        }

        public void zza(zzaqr zzaqr, Collection<E> collection) throws IOException {
            if (collection == null) {
                zzaqr.mo10641bA();
                return;
            }
            zzaqr.mo10643bw();
            for (E zza : collection) {
                this.bpJ.zza(zzaqr, zza);
            }
            zzaqr.mo10644bx();
        }

        /* renamed from: zzj */
        public Collection<E> zzb(zzaqp zzaqp) throws IOException {
            if (zzaqp.mo10624bq() == zzaqq.NULL) {
                zzaqp.nextNull();
                return null;
            }
            Collection<E> collection = (Collection) this.bpK.mo10557bj();
            zzaqp.beginArray();
            while (zzaqp.hasNext()) {
                collection.add(this.bpJ.zzb(zzaqp));
            }
            zzaqp.endArray();
            return collection;
        }
    }

    public zzaqc(zzaps zzaps) {
        this.bod = zzaps;
    }

    public <T> zzapk<T> zza(zzaos zzaos, zzaqo<T> zzaqo) {
        Type bC = zzaqo.mo10724bC();
        Class bB = zzaqo.mo10723bB();
        if (!Collection.class.isAssignableFrom(bB)) {
            return null;
        }
        Type zza2 = zzapr.zza(bC, bB);
        return new zza(zzaos, zza2, zzaos.zza(zzaqo.zzl(zza2)), this.bod.zzb(zzaqo));
    }
}
