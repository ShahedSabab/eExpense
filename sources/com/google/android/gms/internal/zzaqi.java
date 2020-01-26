package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;

public final class zzaqi extends zzapk<Object> {
    public static final zzapl bpG = new zzapl() {
        public <T> zzapk<T> zza(zzaos zzaos, zzaqo<T> zzaqo) {
            if (zzaqo.mo10723bB() == Object.class) {
                return new zzaqi(zzaos);
            }
            return null;
        }
    };
    private final zzaos boC;

    private zzaqi(zzaos zzaos) {
        this.boC = zzaos;
    }

    public void zza(zzaqr zzaqr, Object obj) throws IOException {
        if (obj == null) {
            zzaqr.mo10641bA();
            return;
        }
        zzapk zzk = this.boC.zzk(obj.getClass());
        if (zzk instanceof zzaqi) {
            zzaqr.mo10645by();
            zzaqr.mo10646bz();
            return;
        }
        zzk.zza(zzaqr, obj);
    }

    public Object zzb(zzaqp zzaqp) throws IOException {
        switch (zzaqp.mo10624bq()) {
            case BEGIN_ARRAY:
                ArrayList arrayList = new ArrayList();
                zzaqp.beginArray();
                while (zzaqp.hasNext()) {
                    arrayList.add(zzb(zzaqp));
                }
                zzaqp.endArray();
                return arrayList;
            case BEGIN_OBJECT:
                zzapw zzapw = new zzapw();
                zzaqp.beginObject();
                while (zzaqp.hasNext()) {
                    zzapw.put(zzaqp.nextName(), zzb(zzaqp));
                }
                zzaqp.endObject();
                return zzapw;
            case STRING:
                return zzaqp.nextString();
            case NUMBER:
                return Double.valueOf(zzaqp.nextDouble());
            case BOOLEAN:
                return Boolean.valueOf(zzaqp.nextBoolean());
            case NULL:
                zzaqp.nextNull();
                return null;
            default:
                throw new IllegalStateException();
        }
    }
}
