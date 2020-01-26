package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class zzaqj implements zzapl {
    private final zzaps bod;
    private final zzapt bom;
    private final zzaor boo;

    public static final class zza<T> extends zzapk<T> {
        private final zzapx<T> bpK;
        private final Map<String, zzb> bqd;

        private zza(zzapx<T> zzapx, Map<String, zzb> map) {
            this.bpK = zzapx;
            this.bqd = map;
        }

        public void zza(zzaqr zzaqr, T t) throws IOException {
            if (t == null) {
                zzaqr.mo10641bA();
                return;
            }
            zzaqr.mo10645by();
            try {
                for (zzb zzb : this.bqd.values()) {
                    if (zzb.zzcs(t)) {
                        zzaqr.zzus(zzb.name);
                        zzb.zza(zzaqr, (Object) t);
                    }
                }
                zzaqr.mo10646bz();
            } catch (IllegalAccessException e) {
                throw new AssertionError();
            }
        }

        public T zzb(zzaqp zzaqp) throws IOException {
            if (zzaqp.mo10624bq() == zzaqq.NULL) {
                zzaqp.nextNull();
                return null;
            }
            Object bj = this.bpK.mo10557bj();
            try {
                zzaqp.beginObject();
                while (zzaqp.hasNext()) {
                    zzb zzb = (zzb) this.bqd.get(zzaqp.nextName());
                    if (zzb == null || !zzb.bqf) {
                        zzaqp.skipValue();
                    } else {
                        zzb.zza(zzaqp, bj);
                    }
                }
                zzaqp.endObject();
                return bj;
            } catch (IllegalStateException e) {
                throw new zzaph((Throwable) e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    static abstract class zzb {
        final boolean bqe;
        final boolean bqf;
        final String name;

        protected zzb(String str, boolean z, boolean z2) {
            this.name = str;
            this.bqe = z;
            this.bqf = z2;
        }

        /* access modifiers changed from: 0000 */
        public abstract void zza(zzaqp zzaqp, Object obj) throws IOException, IllegalAccessException;

        /* access modifiers changed from: 0000 */
        public abstract void zza(zzaqr zzaqr, Object obj) throws IOException, IllegalAccessException;

        /* access modifiers changed from: 0000 */
        public abstract boolean zzcs(Object obj) throws IOException, IllegalAccessException;
    }

    public zzaqj(zzaps zzaps, zzaor zzaor, zzapt zzapt) {
        this.bod = zzaps;
        this.boo = zzaor;
        this.bom = zzapt;
    }

    /* access modifiers changed from: private */
    public zzapk<?> zza(zzaos zzaos, Field field, zzaqo<?> zzaqo) {
        zzapm zzapm = (zzapm) field.getAnnotation(zzapm.class);
        if (zzapm != null) {
            zzapk<?> zza2 = zzaqe.zza(this.bod, zzaos, zzaqo, zzapm);
            if (zza2 != null) {
                return zza2;
            }
        }
        return zzaos.zza(zzaqo);
    }

    private zzb zza(zzaos zzaos, Field field, String str, zzaqo<?> zzaqo, boolean z, boolean z2) {
        final boolean zzk = zzapy.zzk(zzaqo.mo10723bB());
        final zzaos zzaos2 = zzaos;
        final Field field2 = field;
        final zzaqo<?> zzaqo2 = zzaqo;
        return new zzb(str, z, z2) {
            final zzapk<?> bpX = zzaqj.this.zza(zzaos2, field2, zzaqo2);

            /* access modifiers changed from: 0000 */
            public void zza(zzaqp zzaqp, Object obj) throws IOException, IllegalAccessException {
                Object zzb = this.bpX.zzb(zzaqp);
                if (zzb != null || !zzk) {
                    field2.set(obj, zzb);
                }
            }

            /* access modifiers changed from: 0000 */
            public void zza(zzaqr zzaqr, Object obj) throws IOException, IllegalAccessException {
                new zzaqm(zzaos2, this.bpX, zzaqo2.mo10724bC()).zza(zzaqr, field2.get(obj));
            }

            public boolean zzcs(Object obj) throws IOException, IllegalAccessException {
                return this.bqe && field2.get(obj) != obj;
            }
        };
    }

    static List<String> zza(zzaor zzaor, Field field) {
        zzapn zzapn = (zzapn) field.getAnnotation(zzapn.class);
        LinkedList linkedList = new LinkedList();
        if (zzapn == null) {
            linkedList.add(zzaor.zzc(field));
        } else {
            linkedList.add(zzapn.value());
            for (String add : zzapn.mo10536bh()) {
                linkedList.add(add);
            }
        }
        return linkedList;
    }

    private Map<String, zzb> zza(zzaos zzaos, zzaqo<?> zzaqo, Class<?> cls) {
        Field[] declaredFields;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type bC = zzaqo.mo10724bC();
        while (cls != Object.class) {
            for (Field field : cls.getDeclaredFields()) {
                boolean zza2 = zza(field, true);
                boolean zza3 = zza(field, false);
                if (zza2 || zza3) {
                    field.setAccessible(true);
                    Type zza4 = zzapr.zza(zzaqo.mo10724bC(), cls, field.getGenericType());
                    List zzd = zzd(field);
                    zzb zzb2 = null;
                    int i = 0;
                    while (i < zzd.size()) {
                        String str = (String) zzd.get(i);
                        if (i != 0) {
                            zza2 = false;
                        }
                        zzb zzb3 = (zzb) linkedHashMap.put(str, zza(zzaos, field, str, zzaqo.zzl(zza4), zza2, zza3));
                        if (zzb2 != null) {
                            zzb3 = zzb2;
                        }
                        i++;
                        zzb2 = zzb3;
                    }
                    if (zzb2 != null) {
                        String valueOf = String.valueOf(bC);
                        String str2 = zzb2.name;
                        throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 37 + String.valueOf(str2).length()).append(valueOf).append(" declares multiple JSON fields named ").append(str2).toString());
                    }
                }
            }
            zzaqo = zzaqo.zzl(zzapr.zza(zzaqo.mo10724bC(), cls, cls.getGenericSuperclass()));
            cls = zzaqo.mo10723bB();
        }
        return linkedHashMap;
    }

    static boolean zza(Field field, boolean z, zzapt zzapt) {
        return !zzapt.zza(field.getType(), z) && !zzapt.zza(field, z);
    }

    private List<String> zzd(Field field) {
        return zza(this.boo, field);
    }

    public <T> zzapk<T> zza(zzaos zzaos, zzaqo<T> zzaqo) {
        Class bB = zzaqo.mo10723bB();
        if (!Object.class.isAssignableFrom(bB)) {
            return null;
        }
        return new zza(this.bod.zzb(zzaqo), zza(zzaos, zzaqo, bB));
    }

    public boolean zza(Field field, boolean z) {
        return zza(field, z, this.bom);
    }
}
