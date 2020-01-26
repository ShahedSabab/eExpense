package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public final class zzaqb<E> extends zzapk<Object> {
    public static final zzapl bpG = new zzapl() {
        public <T> zzapk<T> zza(zzaos zzaos, zzaqo<T> zzaqo) {
            Type bC = zzaqo.mo10724bC();
            if (!(bC instanceof GenericArrayType) && (!(bC instanceof Class) || !((Class) bC).isArray())) {
                return null;
            }
            Type zzh = zzapr.zzh(bC);
            return new zzaqb(zzaos, zzaos.zza(zzaqo.zzl(zzh)), zzapr.zzf(zzh));
        }
    };
    private final Class<E> bpH;
    private final zzapk<E> bpI;

    public zzaqb(zzaos zzaos, zzapk<E> zzapk, Class<E> cls) {
        this.bpI = new zzaqm(zzaos, zzapk, cls);
        this.bpH = cls;
    }

    public void zza(zzaqr zzaqr, Object obj) throws IOException {
        if (obj == null) {
            zzaqr.mo10641bA();
            return;
        }
        zzaqr.mo10643bw();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.bpI.zza(zzaqr, Array.get(obj, i));
        }
        zzaqr.mo10644bx();
    }

    public Object zzb(zzaqp zzaqp) throws IOException {
        if (zzaqp.mo10624bq() == zzaqq.NULL) {
            zzaqp.nextNull();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        zzaqp.beginArray();
        while (zzaqp.hasNext()) {
            arrayList.add(this.bpI.zzb(zzaqp));
        }
        zzaqp.endArray();
        Object newInstance = Array.newInstance(this.bpH, arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }
}
