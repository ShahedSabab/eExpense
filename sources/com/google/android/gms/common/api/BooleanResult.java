package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzaa;

public class BooleanResult implements Result {

    /* renamed from: hv */
    private final Status f133hv;

    /* renamed from: xv */
    private final boolean f134xv;

    public BooleanResult(Status status, boolean z) {
        this.f133hv = (Status) zzaa.zzb(status, (Object) "Status must not be null");
        this.f134xv = z;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BooleanResult)) {
            return false;
        }
        BooleanResult booleanResult = (BooleanResult) obj;
        return this.f133hv.equals(booleanResult.f133hv) && this.f134xv == booleanResult.f134xv;
    }

    public Status getStatus() {
        return this.f133hv;
    }

    public boolean getValue() {
        return this.f134xv;
    }

    public final int hashCode() {
        return (this.f134xv ? 1 : 0) + ((this.f133hv.hashCode() + 527) * 31);
    }
}
