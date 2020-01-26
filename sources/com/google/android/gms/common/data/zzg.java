package com.google.android.gms.common.data;

import java.util.NoSuchElementException;

public class zzg<T> extends zzb<T> {

    /* renamed from: Cn */
    private T f208Cn;

    public zzg(DataBuffer<T> dataBuffer) {
        super(dataBuffer);
    }

    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Cannot advance the iterator beyond " + this.f200BS);
        }
        this.f200BS++;
        if (this.f200BS == 0) {
            this.f208Cn = this.f199BR.get(0);
            if (!(this.f208Cn instanceof zzc)) {
                String valueOf = String.valueOf(this.f208Cn.getClass());
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 44).append("DataBuffer reference of type ").append(valueOf).append(" is not movable").toString());
            }
        } else {
            ((zzc) this.f208Cn).zzfy(this.f200BS);
        }
        return this.f208Cn;
    }
}
