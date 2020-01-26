package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public final class zzaqh implements zzapl {
    private final zzaps bod;
    /* access modifiers changed from: private */
    public final boolean bpS;

    private final class zza<K, V> extends zzapk<Map<K, V>> {
        private final zzapx<? extends Map<K, V>> bpK;
        private final zzapk<K> bpT;
        private final zzapk<V> bpU;

        public zza(zzaos zzaos, Type type, zzapk<K> zzapk, Type type2, zzapk<V> zzapk2, zzapx<? extends Map<K, V>> zzapx) {
            this.bpT = new zzaqm(zzaos, zzapk, type);
            this.bpU = new zzaqm(zzaos, zzapk2, type2);
            this.bpK = zzapx;
        }

        private String zze(zzaoy zzaoy) {
            if (zzaoy.mo10506aX()) {
                zzape bb = zzaoy.mo10510bb();
                if (bb.mo10528be()) {
                    return String.valueOf(bb.mo10492aT());
                }
                if (bb.mo10527bd()) {
                    return Boolean.toString(bb.getAsBoolean());
                }
                if (bb.mo10529bf()) {
                    return bb.mo10493aU();
                }
                throw new AssertionError();
            } else if (zzaoy.mo10507aY()) {
                return "null";
            } else {
                throw new AssertionError();
            }
        }

        public void zza(zzaqr zzaqr, Map<K, V> map) throws IOException {
            int i = 0;
            if (map == null) {
                zzaqr.mo10641bA();
            } else if (!zzaqh.this.bpS) {
                zzaqr.mo10645by();
                for (Entry entry : map.entrySet()) {
                    zzaqr.zzus(String.valueOf(entry.getKey()));
                    this.bpU.zza(zzaqr, entry.getValue());
                }
                zzaqr.mo10646bz();
            } else {
                ArrayList arrayList = new ArrayList(map.size());
                ArrayList arrayList2 = new ArrayList(map.size());
                boolean z = false;
                for (Entry entry2 : map.entrySet()) {
                    zzaoy zzcn = this.bpT.zzcn(entry2.getKey());
                    arrayList.add(zzcn);
                    arrayList2.add(entry2.getValue());
                    z = (zzcn.mo10504aV() || zzcn.mo10505aW()) | z;
                }
                if (z) {
                    zzaqr.mo10643bw();
                    while (i < arrayList.size()) {
                        zzaqr.mo10643bw();
                        zzapz.zzb((zzaoy) arrayList.get(i), zzaqr);
                        this.bpU.zza(zzaqr, arrayList2.get(i));
                        zzaqr.mo10644bx();
                        i++;
                    }
                    zzaqr.mo10644bx();
                    return;
                }
                zzaqr.mo10645by();
                while (i < arrayList.size()) {
                    zzaqr.zzus(zze((zzaoy) arrayList.get(i)));
                    this.bpU.zza(zzaqr, arrayList2.get(i));
                    i++;
                }
                zzaqr.mo10646bz();
            }
        }

        /* renamed from: zzl */
        public Map<K, V> zzb(zzaqp zzaqp) throws IOException {
            zzaqq bq = zzaqp.mo10624bq();
            if (bq == zzaqq.NULL) {
                zzaqp.nextNull();
                return null;
            }
            Map<K, V> map = (Map) this.bpK.mo10557bj();
            if (bq == zzaqq.BEGIN_ARRAY) {
                zzaqp.beginArray();
                while (zzaqp.hasNext()) {
                    zzaqp.beginArray();
                    Object zzb = this.bpT.zzb(zzaqp);
                    if (map.put(zzb, this.bpU.zzb(zzaqp)) != null) {
                        String valueOf = String.valueOf(zzb);
                        throw new zzaph(new StringBuilder(String.valueOf(valueOf).length() + 15).append("duplicate key: ").append(valueOf).toString());
                    }
                    zzaqp.endArray();
                }
                zzaqp.endArray();
                return map;
            }
            zzaqp.beginObject();
            while (zzaqp.hasNext()) {
                zzapu.bph.zzi(zzaqp);
                Object zzb2 = this.bpT.zzb(zzaqp);
                if (map.put(zzb2, this.bpU.zzb(zzaqp)) != null) {
                    String valueOf2 = String.valueOf(zzb2);
                    throw new zzaph(new StringBuilder(String.valueOf(valueOf2).length() + 15).append("duplicate key: ").append(valueOf2).toString());
                }
            }
            zzaqp.endObject();
            return map;
        }
    }

    public zzaqh(zzaps zzaps, boolean z) {
        this.bod = zzaps;
        this.bpS = z;
    }

    private zzapk<?> zza(zzaos zzaos, Type type) {
        return (type == Boolean.TYPE || type == Boolean.class) ? zzaqn.bqo : zzaos.zza(zzaqo.zzl(type));
    }

    public <T> zzapk<T> zza(zzaos zzaos, zzaqo<T> zzaqo) {
        Type bC = zzaqo.mo10724bC();
        if (!Map.class.isAssignableFrom(zzaqo.mo10723bB())) {
            return null;
        }
        Type[] zzb = zzapr.zzb(bC, zzapr.zzf(bC));
        zzapk zza2 = zza(zzaos, zzb[0]);
        zzapk zza3 = zzaos.zza(zzaqo.zzl(zzb[1]));
        zzapx zzb2 = this.bod.zzb(zzaqo);
        return new zza(zzaos, zzb[0], zza2, zzb[1], zza3, zzb2);
    }
}
