package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.zzaa;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class zzb<T> implements Iterator<T> {

    /* renamed from: BR */
    protected final DataBuffer<T> f199BR;

    /* renamed from: BS */
    protected int f200BS = -1;

    public zzb(DataBuffer<T> dataBuffer) {
        this.f199BR = (DataBuffer) zzaa.zzy(dataBuffer);
    }

    public boolean hasNext() {
        return this.f200BS < this.f199BR.getCount() + -1;
    }

    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Cannot advance the iterator beyond " + this.f200BS);
        }
        DataBuffer<T> dataBuffer = this.f199BR;
        int i = this.f200BS + 1;
        this.f200BS = i;
        return dataBuffer.get(i);
    }

    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
