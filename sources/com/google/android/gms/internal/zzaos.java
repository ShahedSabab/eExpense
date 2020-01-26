package com.google.android.gms.internal;

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzaos {
    private final ThreadLocal<Map<zzaqo<?>, zza<?>>> boa;
    private final Map<zzaqo<?>, zzapk<?>> bob;
    private final List<zzapl> boc;
    private final zzaps bod;
    private final boolean boe;
    private final boolean bof;
    private final boolean bog;
    private final boolean boh;
    final zzaow boi;
    final zzapf boj;

    static class zza<T> extends zzapk<T> {
        private zzapk<T> bol;

        zza() {
        }

        public void zza(zzapk<T> zzapk) {
            if (this.bol != null) {
                throw new AssertionError();
            }
            this.bol = zzapk;
        }

        public void zza(zzaqr zzaqr, T t) throws IOException {
            if (this.bol == null) {
                throw new IllegalStateException();
            }
            this.bol.zza(zzaqr, t);
        }

        public T zzb(zzaqp zzaqp) throws IOException {
            if (this.bol != null) {
                return this.bol.zzb(zzaqp);
            }
            throw new IllegalStateException();
        }
    }

    public zzaos() {
        this(zzapt.boW, zzaoq.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, zzapi.DEFAULT, Collections.emptyList());
    }

    zzaos(zzapt zzapt, zzaor zzaor, Map<Type, zzaou<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, zzapi zzapi, List<zzapl> list) {
        this.boa = new ThreadLocal<>();
        this.bob = Collections.synchronizedMap(new HashMap());
        this.boi = new zzaow() {
        };
        this.boj = new zzapf() {
        };
        this.bod = new zzaps(map);
        this.boe = z;
        this.bog = z3;
        this.bof = z4;
        this.boh = z5;
        ArrayList arrayList = new ArrayList();
        arrayList.add(zzaqn.bqZ);
        arrayList.add(zzaqi.bpG);
        arrayList.add(zzapt);
        arrayList.addAll(list);
        arrayList.add(zzaqn.bqG);
        arrayList.add(zzaqn.bqv);
        arrayList.add(zzaqn.bqp);
        arrayList.add(zzaqn.bqr);
        arrayList.add(zzaqn.bqt);
        arrayList.add(zzaqn.zza(Long.TYPE, Long.class, zza(zzapi)));
        arrayList.add(zzaqn.zza(Double.TYPE, Double.class, zzdf(z6)));
        arrayList.add(zzaqn.zza(Float.TYPE, Float.class, zzdg(z6)));
        arrayList.add(zzaqn.bqA);
        arrayList.add(zzaqn.bqC);
        arrayList.add(zzaqn.bqI);
        arrayList.add(zzaqn.bqK);
        arrayList.add(zzaqn.zza(BigDecimal.class, zzaqn.bqE));
        arrayList.add(zzaqn.zza(BigInteger.class, zzaqn.bqF));
        arrayList.add(zzaqn.bqM);
        arrayList.add(zzaqn.bqO);
        arrayList.add(zzaqn.bqS);
        arrayList.add(zzaqn.bqX);
        arrayList.add(zzaqn.bqQ);
        arrayList.add(zzaqn.bqm);
        arrayList.add(zzaqd.bpG);
        arrayList.add(zzaqn.bqV);
        arrayList.add(zzaql.bpG);
        arrayList.add(zzaqk.bpG);
        arrayList.add(zzaqn.bqT);
        arrayList.add(zzaqb.bpG);
        arrayList.add(zzaqn.bqk);
        arrayList.add(new zzaqc(this.bod));
        arrayList.add(new zzaqh(this.bod, z2));
        arrayList.add(new zzaqe(this.bod));
        arrayList.add(zzaqn.bra);
        arrayList.add(new zzaqj(this.bod, zzaor, zzapt));
        this.boc = Collections.unmodifiableList(arrayList);
    }

    private zzapk<Number> zza(zzapi zzapi) {
        return zzapi == zzapi.DEFAULT ? zzaqn.bqw : new zzapk<Number>() {
            public void zza(zzaqr zzaqr, Number number) throws IOException {
                if (number == null) {
                    zzaqr.mo10641bA();
                } else {
                    zzaqr.zzut(number.toString());
                }
            }

            /* renamed from: zzg */
            public Number zzb(zzaqp zzaqp) throws IOException {
                if (zzaqp.mo10624bq() != zzaqq.NULL) {
                    return Long.valueOf(zzaqp.nextLong());
                }
                zzaqp.nextNull();
                return null;
            }
        };
    }

    private static void zza(Object obj, zzaqp zzaqp) {
        if (obj != null) {
            try {
                if (zzaqp.mo10624bq() != zzaqq.END_DOCUMENT) {
                    throw new zzaoz("JSON document was not fully consumed.");
                }
            } catch (zzaqs e) {
                throw new zzaph((Throwable) e);
            } catch (IOException e2) {
                throw new zzaoz((Throwable) e2);
            }
        }
    }

    private zzapk<Number> zzdf(boolean z) {
        return z ? zzaqn.bqy : new zzapk<Number>() {
            public void zza(zzaqr zzaqr, Number number) throws IOException {
                if (number == null) {
                    zzaqr.mo10641bA();
                    return;
                }
                zzaos.this.zzm(number.doubleValue());
                zzaqr.zza(number);
            }

            /* renamed from: zze */
            public Double zzb(zzaqp zzaqp) throws IOException {
                if (zzaqp.mo10624bq() != zzaqq.NULL) {
                    return Double.valueOf(zzaqp.nextDouble());
                }
                zzaqp.nextNull();
                return null;
            }
        };
    }

    private zzapk<Number> zzdg(boolean z) {
        return z ? zzaqn.bqx : new zzapk<Number>() {
            public void zza(zzaqr zzaqr, Number number) throws IOException {
                if (number == null) {
                    zzaqr.mo10641bA();
                    return;
                }
                zzaos.this.zzm((double) number.floatValue());
                zzaqr.zza(number);
            }

            /* renamed from: zzf */
            public Float zzb(zzaqp zzaqp) throws IOException {
                if (zzaqp.mo10624bq() != zzaqq.NULL) {
                    return Float.valueOf((float) zzaqp.nextDouble());
                }
                zzaqp.nextNull();
                return null;
            }
        };
    }

    /* access modifiers changed from: private */
    public void zzm(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this" + " behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    public String toString() {
        return "{serializeNulls:" + this.boe + "factories:" + this.boc + ",instanceCreators:" + this.bod + "}";
    }

    public <T> zzapk<T> zza(zzapl zzapl, zzaqo<T> zzaqo) {
        boolean z = false;
        if (!this.boc.contains(zzapl)) {
            z = true;
        }
        boolean z2 = z;
        for (zzapl zzapl2 : this.boc) {
            if (z2) {
                zzapk<T> zza2 = zzapl2.zza(this, zzaqo);
                if (zza2 != null) {
                    return zza2;
                }
            } else if (zzapl2 == zzapl) {
                z2 = true;
            }
        }
        String valueOf = String.valueOf(zzaqo);
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 22).append("GSON cannot serialize ").append(valueOf).toString());
    }

    public <T> zzapk<T> zza(zzaqo<T> zzaqo) {
        Map map;
        zzapk<T> zzapk = (zzapk) this.bob.get(zzaqo);
        if (zzapk == null) {
            Map map2 = (Map) this.boa.get();
            boolean z = false;
            if (map2 == null) {
                HashMap hashMap = new HashMap();
                this.boa.set(hashMap);
                map = hashMap;
                z = true;
            } else {
                map = map2;
            }
            zzapk = (zza) map.get(zzaqo);
            if (zzapk == null) {
                try {
                    zza zza2 = new zza();
                    map.put(zzaqo, zza2);
                    for (zzapl zza3 : this.boc) {
                        zzapk = zza3.zza(this, zzaqo);
                        if (zzapk != null) {
                            zza2.zza(zzapk);
                            this.bob.put(zzaqo, zzapk);
                            map.remove(zzaqo);
                            if (z) {
                                this.boa.remove();
                            }
                        }
                    }
                    String valueOf = String.valueOf(zzaqo);
                    throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 19).append("GSON cannot handle ").append(valueOf).toString());
                } catch (Throwable th) {
                    map.remove(zzaqo);
                    if (z) {
                        this.boa.remove();
                    }
                    throw th;
                }
            }
        }
        return zzapk;
    }

    public zzaqr zza(Writer writer) throws IOException {
        if (this.bog) {
            writer.write(")]}'\n");
        }
        zzaqr zzaqr = new zzaqr(writer);
        if (this.boh) {
            zzaqr.setIndent("  ");
        }
        zzaqr.zzdk(this.boe);
        return zzaqr;
    }

    public <T> T zza(zzaoy zzaoy, Class<T> cls) throws zzaph {
        return zzapy.zzp(cls).cast(zza(zzaoy, (Type) cls));
    }

    public <T> T zza(zzaoy zzaoy, Type type) throws zzaph {
        if (zzaoy == null) {
            return null;
        }
        return zza((zzaqp) new zzaqf(zzaoy), type);
    }

    public <T> T zza(zzaqp zzaqp, Type type) throws zzaoz, zzaph {
        boolean z = true;
        boolean isLenient = zzaqp.isLenient();
        zzaqp.setLenient(true);
        try {
            zzaqp.mo10624bq();
            z = false;
            T zzb = zza(zzaqo.zzl(type)).zzb(zzaqp);
            zzaqp.setLenient(isLenient);
            return zzb;
        } catch (EOFException e) {
            if (z) {
                zzaqp.setLenient(isLenient);
                return null;
            }
            throw new zzaph((Throwable) e);
        } catch (IllegalStateException e2) {
            throw new zzaph((Throwable) e2);
        } catch (IOException e3) {
            throw new zzaph((Throwable) e3);
        } catch (Throwable th) {
            zzaqp.setLenient(isLenient);
            throw th;
        }
    }

    public <T> T zza(Reader reader, Type type) throws zzaoz, zzaph {
        zzaqp zzaqp = new zzaqp(reader);
        T zza2 = zza(zzaqp, type);
        zza((Object) zza2, zzaqp);
        return zza2;
    }

    public <T> T zza(String str, Type type) throws zzaph {
        if (str == null) {
            return null;
        }
        return zza((Reader) new StringReader(str), type);
    }

    public void zza(zzaoy zzaoy, zzaqr zzaqr) throws zzaoz {
        boolean isLenient = zzaqr.isLenient();
        zzaqr.setLenient(true);
        boolean bM = zzaqr.mo10731bM();
        zzaqr.zzdj(this.bof);
        boolean bN = zzaqr.mo10732bN();
        zzaqr.zzdk(this.boe);
        try {
            zzapz.zzb(zzaoy, zzaqr);
            zzaqr.setLenient(isLenient);
            zzaqr.zzdj(bM);
            zzaqr.zzdk(bN);
        } catch (IOException e) {
            throw new zzaoz((Throwable) e);
        } catch (Throwable th) {
            zzaqr.setLenient(isLenient);
            zzaqr.zzdj(bM);
            zzaqr.zzdk(bN);
            throw th;
        }
    }

    public void zza(zzaoy zzaoy, Appendable appendable) throws zzaoz {
        try {
            zza(zzaoy, zza(zzapz.zza(appendable)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void zza(Object obj, Type type, zzaqr zzaqr) throws zzaoz {
        zzapk zza2 = zza(zzaqo.zzl(type));
        boolean isLenient = zzaqr.isLenient();
        zzaqr.setLenient(true);
        boolean bM = zzaqr.mo10731bM();
        zzaqr.zzdj(this.bof);
        boolean bN = zzaqr.mo10732bN();
        zzaqr.zzdk(this.boe);
        try {
            zza2.zza(zzaqr, obj);
            zzaqr.setLenient(isLenient);
            zzaqr.zzdj(bM);
            zzaqr.zzdk(bN);
        } catch (IOException e) {
            throw new zzaoz((Throwable) e);
        } catch (Throwable th) {
            zzaqr.setLenient(isLenient);
            zzaqr.zzdj(bM);
            zzaqr.zzdk(bN);
            throw th;
        }
    }

    public void zza(Object obj, Type type, Appendable appendable) throws zzaoz {
        try {
            zza(obj, type, zza(zzapz.zza(appendable)));
        } catch (IOException e) {
            throw new zzaoz((Throwable) e);
        }
    }

    public String zzb(zzaoy zzaoy) {
        StringWriter stringWriter = new StringWriter();
        zza(zzaoy, (Appendable) stringWriter);
        return stringWriter.toString();
    }

    public String zzc(Object obj, Type type) {
        StringWriter stringWriter = new StringWriter();
        zza(obj, type, (Appendable) stringWriter);
        return stringWriter.toString();
    }

    public String zzck(Object obj) {
        return obj == null ? zzb(zzapa.bou) : zzc(obj, obj.getClass());
    }

    public <T> T zzf(String str, Class<T> cls) throws zzaph {
        return zzapy.zzp(cls).cast(zza(str, (Type) cls));
    }

    public <T> zzapk<T> zzk(Class<T> cls) {
        return zza(zzaqo.zzr(cls));
    }
}
