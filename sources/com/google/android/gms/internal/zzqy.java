package com.google.android.gms.internal;

import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

public abstract class zzqy implements Releasable, Result {

    /* renamed from: hv */
    protected final Status f592hv;

    /* renamed from: zy */
    protected final DataHolder f593zy;

    protected zzqy(DataHolder dataHolder, Status status) {
        this.f592hv = status;
        this.f593zy = dataHolder;
    }

    public Status getStatus() {
        return this.f592hv;
    }

    public void release() {
        if (this.f593zy != null) {
            this.f593zy.close();
        }
    }
}
