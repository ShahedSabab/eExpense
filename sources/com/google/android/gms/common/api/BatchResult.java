package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzaa;
import java.util.concurrent.TimeUnit;

public final class BatchResult implements Result {

    /* renamed from: hv */
    private final Status f131hv;

    /* renamed from: xs */
    private final PendingResult<?>[] f132xs;

    BatchResult(Status status, PendingResult<?>[] pendingResultArr) {
        this.f131hv = status;
        this.f132xs = pendingResultArr;
    }

    public Status getStatus() {
        return this.f131hv;
    }

    public <R extends Result> R take(BatchResultToken<R> batchResultToken) {
        zzaa.zzb(batchResultToken.mId < this.f132xs.length, (Object) "The result token does not belong to this batch");
        return this.f132xs[batchResultToken.mId].await(0, TimeUnit.MILLISECONDS);
    }
}
