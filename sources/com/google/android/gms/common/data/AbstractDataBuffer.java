package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Iterator;

public abstract class AbstractDataBuffer<T> implements DataBuffer<T> {

    /* renamed from: zy */
    protected final DataHolder f178zy;

    protected AbstractDataBuffer(DataHolder dataHolder) {
        this.f178zy = dataHolder;
        if (this.f178zy != null) {
        }
    }

    @Deprecated
    public final void close() {
        release();
    }

    public abstract T get(int i);

    public int getCount() {
        if (this.f178zy == null) {
            return 0;
        }
        return this.f178zy.getCount();
    }

    @Deprecated
    public boolean isClosed() {
        return this.f178zy == null || this.f178zy.isClosed();
    }

    public Iterator<T> iterator() {
        return new zzb(this);
    }

    public void release() {
        if (this.f178zy != null) {
            this.f178zy.close();
        }
    }

    public Iterator<T> singleRefIterator() {
        return new zzg(this);
    }

    public Bundle zzaui() {
        return this.f178zy.zzaui();
    }
}
