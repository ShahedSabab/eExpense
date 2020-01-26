package com.google.android.gms.common.util;

import android.support.p000v4.util.ArrayMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;

public class zza<E> extends AbstractSet<E> {

    /* renamed from: Gp */
    private final ArrayMap<E, E> f432Gp;

    public zza() {
        this.f432Gp = new ArrayMap<>();
    }

    public zza(int i) {
        this.f432Gp = new ArrayMap<>(i);
    }

    public zza(Collection<E> collection) {
        this(collection.size());
        addAll(collection);
    }

    public boolean add(E e) {
        if (this.f432Gp.containsKey(e)) {
            return false;
        }
        this.f432Gp.put(e, e);
        return true;
    }

    public boolean addAll(Collection<? extends E> collection) {
        return collection instanceof zza ? zza((zza) collection) : super.addAll(collection);
    }

    public void clear() {
        this.f432Gp.clear();
    }

    public boolean contains(Object obj) {
        return this.f432Gp.containsKey(obj);
    }

    public Iterator<E> iterator() {
        return this.f432Gp.keySet().iterator();
    }

    public boolean remove(Object obj) {
        if (!this.f432Gp.containsKey(obj)) {
            return false;
        }
        this.f432Gp.remove(obj);
        return true;
    }

    public int size() {
        return this.f432Gp.size();
    }

    public boolean zza(zza<? extends E> zza) {
        int size = size();
        this.f432Gp.putAll(zza.f432Gp);
        return size() > size;
    }
}
