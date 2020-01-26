package com.google.android.gms.internal;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public final class zzapw<K, V> extends AbstractMap<K, V> implements Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = (!zzapw.class.desiredAssertionStatus());
    private static final Comparator<Comparable> bpi = new Comparator<Comparable>() {
        /* renamed from: zza */
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    };
    Comparator<? super K> bab;
    zzd<K, V> bpj;
    final zzd<K, V> bpk;
    private zza bpl;
    private zzb bpm;
    int modCount;
    int size;

    class zza extends AbstractSet<Entry<K, V>> {
        zza() {
        }

        public void clear() {
            zzapw.this.clear();
        }

        public boolean contains(Object obj) {
            return (obj instanceof Entry) && zzapw.this.zzc((Entry) obj) != null;
        }

        public Iterator<Entry<K, V>> iterator() {
            return new zzc<Entry<K, V>>() {
                {
                    zzapw zzapw = zzapw.this;
                }

                public Entry<K, V> next() {
                    return mo10599bl();
                }
            };
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            zzd zzc = zzapw.this.zzc((Entry) obj);
            if (zzc == null) {
                return false;
            }
            zzapw.this.zza(zzc, true);
            return true;
        }

        public int size() {
            return zzapw.this.size;
        }
    }

    final class zzb extends AbstractSet<K> {
        zzb() {
        }

        public void clear() {
            zzapw.this.clear();
        }

        public boolean contains(Object obj) {
            return zzapw.this.containsKey(obj);
        }

        public Iterator<K> iterator() {
            return new zzc<K>() {
                {
                    zzapw zzapw = zzapw.this;
                }

                public K next() {
                    return mo10599bl().bap;
                }
            };
        }

        public boolean remove(Object obj) {
            return zzapw.this.zzcr(obj) != null;
        }

        public int size() {
            return zzapw.this.size;
        }
    }

    private abstract class zzc<T> implements Iterator<T> {
        zzd<K, V> bpq;
        zzd<K, V> bpr;
        int bps;

        private zzc() {
            this.bpq = zzapw.this.bpk.bpq;
            this.bpr = null;
            this.bps = zzapw.this.modCount;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: bl */
        public final zzd<K, V> mo10599bl() {
            zzd<K, V> zzd = this.bpq;
            if (zzd == zzapw.this.bpk) {
                throw new NoSuchElementException();
            } else if (zzapw.this.modCount != this.bps) {
                throw new ConcurrentModificationException();
            } else {
                this.bpq = zzd.bpq;
                this.bpr = zzd;
                return zzd;
            }
        }

        public final boolean hasNext() {
            return this.bpq != zzapw.this.bpk;
        }

        public final void remove() {
            if (this.bpr == null) {
                throw new IllegalStateException();
            }
            zzapw.this.zza(this.bpr, true);
            this.bpr = null;
            this.bps = zzapw.this.modCount;
        }
    }

    static final class zzd<K, V> implements Entry<K, V> {
        final K bap;
        zzd<K, V> bpq;
        zzd<K, V> bpt;
        zzd<K, V> bpu;
        zzd<K, V> bpv;
        zzd<K, V> bpw;
        int height;
        V value;

        zzd() {
            this.bap = null;
            this.bpw = this;
            this.bpq = this;
        }

        zzd(zzd<K, V> zzd, K k, zzd<K, V> zzd2, zzd<K, V> zzd3) {
            this.bpt = zzd;
            this.bap = k;
            this.height = 1;
            this.bpq = zzd2;
            this.bpw = zzd3;
            zzd3.bpq = this;
            zzd2.bpw = this;
        }

        /* renamed from: bm */
        public zzd<K, V> mo10602bm() {
            for (zzd<K, V> zzd = this.bpu; zzd != null; zzd = zzd.bpu) {
                this = zzd;
            }
            return this;
        }

        /* renamed from: bn */
        public zzd<K, V> mo10603bn() {
            for (zzd<K, V> zzd = this.bpv; zzd != null; zzd = zzd.bpv) {
                this = zzd;
            }
            return this;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            if (this.bap == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!this.bap.equals(entry.getKey())) {
                return false;
            }
            if (this.value == null) {
                if (entry.getValue() != null) {
                    return false;
                }
            } else if (!this.value.equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        public K getKey() {
            return this.bap;
        }

        public V getValue() {
            return this.value;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = this.bap == null ? 0 : this.bap.hashCode();
            if (this.value != null) {
                i = this.value.hashCode();
            }
            return hashCode ^ i;
        }

        public V setValue(V v) {
            V v2 = this.value;
            this.value = v;
            return v2;
        }

        public String toString() {
            String valueOf = String.valueOf(this.bap);
            String valueOf2 = String.valueOf(this.value);
            return new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(valueOf2).length()).append(valueOf).append("=").append(valueOf2).toString();
        }
    }

    public zzapw() {
        this(bpi);
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Comparator<? super K>, code=java.util.Comparator, for r2v0, types: [java.util.Comparator<? super K>, java.util.Comparator] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzapw(java.util.Comparator r2) {
        /*
            r1 = this;
            r0 = 0
            r1.<init>()
            r1.size = r0
            r1.modCount = r0
            com.google.android.gms.internal.zzapw$zzd r0 = new com.google.android.gms.internal.zzapw$zzd
            r0.<init>()
            r1.bpk = r0
            if (r2 == 0) goto L_0x0014
        L_0x0011:
            r1.bab = r2
            return
        L_0x0014:
            java.util.Comparator<java.lang.Comparable> r2 = bpi
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzapw.<init>(java.util.Comparator):void");
    }

    private boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private void zza(zzd<K, V> zzd2) {
        int i = 0;
        zzd<K, V> zzd3 = zzd2.bpu;
        zzd<K, V> zzd4 = zzd2.bpv;
        zzd<K, V> zzd5 = zzd4.bpu;
        zzd<K, V> zzd6 = zzd4.bpv;
        zzd2.bpv = zzd5;
        if (zzd5 != null) {
            zzd5.bpt = zzd2;
        }
        zza(zzd2, zzd4);
        zzd4.bpu = zzd2;
        zzd2.bpt = zzd4;
        zzd2.height = Math.max(zzd3 != null ? zzd3.height : 0, zzd5 != null ? zzd5.height : 0) + 1;
        int i2 = zzd2.height;
        if (zzd6 != null) {
            i = zzd6.height;
        }
        zzd4.height = Math.max(i2, i) + 1;
    }

    private void zza(zzd<K, V> zzd2, zzd<K, V> zzd3) {
        zzd<K, V> zzd4 = zzd2.bpt;
        zzd2.bpt = null;
        if (zzd3 != null) {
            zzd3.bpt = zzd4;
        }
        if (zzd4 == null) {
            this.bpj = zzd3;
        } else if (zzd4.bpu == zzd2) {
            zzd4.bpu = zzd3;
        } else if ($assertionsDisabled || zzd4.bpv == zzd2) {
            zzd4.bpv = zzd3;
        } else {
            throw new AssertionError();
        }
    }

    private void zzb(zzd<K, V> zzd2) {
        int i = 0;
        zzd<K, V> zzd3 = zzd2.bpu;
        zzd<K, V> zzd4 = zzd2.bpv;
        zzd<K, V> zzd5 = zzd3.bpu;
        zzd<K, V> zzd6 = zzd3.bpv;
        zzd2.bpu = zzd6;
        if (zzd6 != null) {
            zzd6.bpt = zzd2;
        }
        zza(zzd2, zzd3);
        zzd3.bpv = zzd2;
        zzd2.bpt = zzd3;
        zzd2.height = Math.max(zzd4 != null ? zzd4.height : 0, zzd6 != null ? zzd6.height : 0) + 1;
        int i2 = zzd2.height;
        if (zzd5 != null) {
            i = zzd5.height;
        }
        zzd3.height = Math.max(i2, i) + 1;
    }

    private void zzb(zzd<K, V> zzd2, boolean z) {
        while (zzd2 != null) {
            zzd<K, V> zzd3 = zzd2.bpu;
            zzd<K, V> zzd4 = zzd2.bpv;
            int i = zzd3 != null ? zzd3.height : 0;
            int i2 = zzd4 != null ? zzd4.height : 0;
            int i3 = i - i2;
            if (i3 == -2) {
                zzd<K, V> zzd5 = zzd4.bpu;
                zzd<K, V> zzd6 = zzd4.bpv;
                int i4 = (zzd5 != null ? zzd5.height : 0) - (zzd6 != null ? zzd6.height : 0);
                if (i4 == -1 || (i4 == 0 && !z)) {
                    zza(zzd2);
                } else if ($assertionsDisabled || i4 == 1) {
                    zzb(zzd4);
                    zza(zzd2);
                } else {
                    throw new AssertionError();
                }
                if (z) {
                    return;
                }
            } else if (i3 == 2) {
                zzd<K, V> zzd7 = zzd3.bpu;
                zzd<K, V> zzd8 = zzd3.bpv;
                int i5 = (zzd7 != null ? zzd7.height : 0) - (zzd8 != null ? zzd8.height : 0);
                if (i5 == 1 || (i5 == 0 && !z)) {
                    zzb(zzd2);
                } else if ($assertionsDisabled || i5 == -1) {
                    zza(zzd3);
                    zzb(zzd2);
                } else {
                    throw new AssertionError();
                }
                if (z) {
                    return;
                }
            } else if (i3 == 0) {
                zzd2.height = i + 1;
                if (z) {
                    return;
                }
            } else if ($assertionsDisabled || i3 == -1 || i3 == 1) {
                zzd2.height = Math.max(i, i2) + 1;
                if (!z) {
                    return;
                }
            } else {
                throw new AssertionError();
            }
            zzd2 = zzd2.bpt;
        }
    }

    public void clear() {
        this.bpj = null;
        this.size = 0;
        this.modCount++;
        zzd<K, V> zzd2 = this.bpk;
        zzd2.bpw = zzd2;
        zzd2.bpq = zzd2;
    }

    public boolean containsKey(Object obj) {
        return zzcq(obj) != null;
    }

    public Set<Entry<K, V>> entrySet() {
        zza zza2 = this.bpl;
        if (zza2 != null) {
            return zza2;
        }
        zza zza3 = new zza();
        this.bpl = zza3;
        return zza3;
    }

    public V get(Object obj) {
        zzd zzcq = zzcq(obj);
        if (zzcq != null) {
            return zzcq.value;
        }
        return null;
    }

    public Set<K> keySet() {
        zzb zzb2 = this.bpm;
        if (zzb2 != null) {
            return zzb2;
        }
        zzb zzb3 = new zzb();
        this.bpm = zzb3;
        return zzb3;
    }

    public V put(K k, V v) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        zzd zza2 = zza(k, true);
        V v2 = zza2.value;
        zza2.value = v;
        return v2;
    }

    public V remove(Object obj) {
        zzd zzcr = zzcr(obj);
        if (zzcr != null) {
            return zzcr.value;
        }
        return null;
    }

    public int size() {
        return this.size;
    }

    /* access modifiers changed from: 0000 */
    public zzd<K, V> zza(K k, boolean z) {
        zzd<K, V> zzd2;
        int i;
        zzd<K, V> zzd3;
        Comparator<? super K> comparator = this.bab;
        zzd<K, V> zzd4 = this.bpj;
        if (zzd4 != null) {
            Comparable comparable = comparator == bpi ? (Comparable) k : null;
            while (true) {
                int compare = comparable != null ? comparable.compareTo(zzd4.bap) : comparator.compare(k, zzd4.bap);
                if (compare == 0) {
                    return zzd4;
                }
                zzd<K, V> zzd5 = compare < 0 ? zzd4.bpu : zzd4.bpv;
                if (zzd5 == null) {
                    int i2 = compare;
                    zzd2 = zzd4;
                    i = i2;
                    break;
                }
                zzd4 = zzd5;
            }
        } else {
            zzd2 = zzd4;
            i = 0;
        }
        if (!z) {
            return null;
        }
        zzd<K, V> zzd6 = this.bpk;
        if (zzd2 != null) {
            zzd3 = new zzd<>(zzd2, k, zzd6, zzd6.bpw);
            if (i < 0) {
                zzd2.bpu = zzd3;
            } else {
                zzd2.bpv = zzd3;
            }
            zzb(zzd2, true);
        } else if (comparator != bpi || (k instanceof Comparable)) {
            zzd3 = new zzd<>(zzd2, k, zzd6, zzd6.bpw);
            this.bpj = zzd3;
        } else {
            throw new ClassCastException(String.valueOf(k.getClass().getName()).concat(" is not Comparable"));
        }
        this.size++;
        this.modCount++;
        return zzd3;
    }

    /* access modifiers changed from: 0000 */
    public void zza(zzd<K, V> zzd2, boolean z) {
        int i;
        int i2 = 0;
        if (z) {
            zzd2.bpw.bpq = zzd2.bpq;
            zzd2.bpq.bpw = zzd2.bpw;
        }
        zzd<K, V> zzd3 = zzd2.bpu;
        zzd<K, V> zzd4 = zzd2.bpv;
        zzd<K, V> zzd5 = zzd2.bpt;
        if (zzd3 == null || zzd4 == null) {
            if (zzd3 != null) {
                zza(zzd2, zzd3);
                zzd2.bpu = null;
            } else if (zzd4 != null) {
                zza(zzd2, zzd4);
                zzd2.bpv = null;
            } else {
                zza(zzd2, null);
            }
            zzb(zzd5, false);
            this.size--;
            this.modCount++;
            return;
        }
        zzd<K, V> bm = zzd3.height > zzd4.height ? zzd3.mo10603bn() : zzd4.mo10602bm();
        zza(bm, false);
        zzd<K, V> zzd6 = zzd2.bpu;
        if (zzd6 != null) {
            i = zzd6.height;
            bm.bpu = zzd6;
            zzd6.bpt = bm;
            zzd2.bpu = null;
        } else {
            i = 0;
        }
        zzd<K, V> zzd7 = zzd2.bpv;
        if (zzd7 != null) {
            i2 = zzd7.height;
            bm.bpv = zzd7;
            zzd7.bpt = bm;
            zzd2.bpv = null;
        }
        bm.height = Math.max(i, i2) + 1;
        zza(zzd2, bm);
    }

    /* access modifiers changed from: 0000 */
    public zzd<K, V> zzc(Entry<?, ?> entry) {
        zzd<K, V> zzcq = zzcq(entry.getKey());
        if (zzcq != null && equal(zzcq.value, entry.getValue())) {
            return zzcq;
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public zzd<K, V> zzcq(Object obj) {
        zzd<K, V> zzd2 = null;
        if (obj == null) {
            return zzd2;
        }
        try {
            return zza((K) obj, false);
        } catch (ClassCastException e) {
            return zzd2;
        }
    }

    /* access modifiers changed from: 0000 */
    public zzd<K, V> zzcr(Object obj) {
        zzd<K, V> zzcq = zzcq(obj);
        if (zzcq != null) {
            zza(zzcq, true);
        }
        return zzcq;
    }
}
