package com.google.android.gms.internal;

import com.google.android.gms.internal.zzaru;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class zzarv<M extends zzaru<M>, T> {
    protected final Class<T> bkp;
    protected final boolean btH;
    public final int tag;
    protected final int type;

    private zzarv(int i, Class<T> cls, int i2, boolean z) {
        this.type = i;
        this.bkp = cls;
        this.tag = i2;
        this.btH = z;
    }

    public static <M extends zzaru<M>, T extends zzasa> zzarv<M, T> zza(int i, Class<T> cls, long j) {
        return new zzarv<>(i, cls, (int) j, false);
    }

    private T zzaz(List<zzasc> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            zzasc zzasc = (zzasc) list.get(i);
            if (zzasc.btQ.length != 0) {
                zza(zzasc, (List<Object>) arrayList);
            }
        }
        int size = arrayList.size();
        if (size == 0) {
            return null;
        }
        T cast = this.bkp.cast(Array.newInstance(this.bkp.getComponentType(), size));
        for (int i2 = 0; i2 < size; i2++) {
            Array.set(cast, i2, arrayList.get(i2));
        }
        return cast;
    }

    private T zzba(List<zzasc> list) {
        if (list.isEmpty()) {
            return null;
        }
        return this.bkp.cast(zzcm(zzars.zzbd(((zzasc) list.get(list.size() - 1)).btQ)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzarv)) {
            return false;
        }
        zzarv zzarv = (zzarv) obj;
        return this.type == zzarv.type && this.bkp == zzarv.bkp && this.tag == zzarv.tag && this.btH == zzarv.btH;
    }

    public int hashCode() {
        return (this.btH ? 1 : 0) + ((((((this.type + 1147) * 31) + this.bkp.hashCode()) * 31) + this.tag) * 31);
    }

    /* access modifiers changed from: protected */
    public void zza(zzasc zzasc, List<Object> list) {
        list.add(zzcm(zzars.zzbd(zzasc.btQ)));
    }

    /* access modifiers changed from: 0000 */
    public void zza(Object obj, zzart zzart) throws IOException {
        if (this.btH) {
            zzc(obj, zzart);
        } else {
            zzb(obj, zzart);
        }
    }

    /* access modifiers changed from: 0000 */
    public final T zzay(List<zzasc> list) {
        if (list == null) {
            return null;
        }
        return this.btH ? zzaz(list) : zzba(list);
    }

    /* access modifiers changed from: protected */
    public void zzb(Object obj, zzart zzart) {
        try {
            zzart.zzahd(this.tag);
            switch (this.type) {
                case 10:
                    zzasa zzasa = (zzasa) obj;
                    int zzahl = zzasd.zzahl(this.tag);
                    zzart.zzb(zzasa);
                    zzart.zzaj(zzahl, 4);
                    return;
                case 11:
                    zzart.zzc((zzasa) obj);
                    return;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.type);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        throw new IllegalStateException(e);
    }

    /* access modifiers changed from: protected */
    public void zzc(Object obj, zzart zzart) {
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            Object obj2 = Array.get(obj, i);
            if (obj2 != null) {
                zzb(obj2, zzart);
            }
        }
    }

    /* access modifiers changed from: protected */
    public Object zzcm(zzars zzars) {
        Class<T> cls = this.btH ? this.bkp.getComponentType() : this.bkp;
        try {
            switch (this.type) {
                case 10:
                    zzasa zzasa = (zzasa) cls.newInstance();
                    zzars.zza(zzasa, zzasd.zzahl(this.tag));
                    return zzasa;
                case 11:
                    zzasa zzasa2 = (zzasa) cls.newInstance();
                    zzars.zza(zzasa2);
                    return zzasa2;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.type);
            }
        } catch (InstantiationException e) {
            String valueOf = String.valueOf(cls);
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 33).append("Error creating instance of class ").append(valueOf).toString(), e);
        } catch (IllegalAccessException e2) {
            String valueOf2 = String.valueOf(cls);
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf2).length() + 33).append("Error creating instance of class ").append(valueOf2).toString(), e2);
        } catch (IOException e3) {
            throw new IllegalArgumentException("Error reading extension field", e3);
        }
    }

    /* access modifiers changed from: 0000 */
    public int zzct(Object obj) {
        return this.btH ? zzcu(obj) : zzcv(obj);
    }

    /* access modifiers changed from: protected */
    public int zzcu(Object obj) {
        int i = 0;
        int length = Array.getLength(obj);
        for (int i2 = 0; i2 < length; i2++) {
            if (Array.get(obj, i2) != null) {
                i += zzcv(Array.get(obj, i2));
            }
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public int zzcv(Object obj) {
        int zzahl = zzasd.zzahl(this.tag);
        switch (this.type) {
            case 10:
                return zzart.zzb(zzahl, (zzasa) obj);
            case 11:
                return zzart.zzc(zzahl, (zzasa) obj);
            default:
                throw new IllegalArgumentException("Unknown type " + this.type);
        }
    }
}
