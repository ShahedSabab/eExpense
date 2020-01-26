package com.google.android.gms.common.api;

import com.google.android.gms.common.api.PendingResult.zza;
import com.google.android.gms.internal.zzqq;
import java.util.ArrayList;
import java.util.List;

public final class Batch extends zzqq<BatchResult> {
    /* access modifiers changed from: private */

    /* renamed from: xp */
    public int f124xp;
    /* access modifiers changed from: private */

    /* renamed from: xq */
    public boolean f125xq;
    /* access modifiers changed from: private */

    /* renamed from: xr */
    public boolean f126xr;
    /* access modifiers changed from: private */

    /* renamed from: xs */
    public final PendingResult<?>[] f127xs;
    /* access modifiers changed from: private */
    public final Object zzako;

    public static final class Builder {

        /* renamed from: mD */
        private GoogleApiClient f129mD;

        /* renamed from: xu */
        private List<PendingResult<?>> f130xu = new ArrayList();

        public Builder(GoogleApiClient googleApiClient) {
            this.f129mD = googleApiClient;
        }

        public <R extends Result> BatchResultToken<R> add(PendingResult<R> pendingResult) {
            BatchResultToken<R> batchResultToken = new BatchResultToken<>(this.f130xu.size());
            this.f130xu.add(pendingResult);
            return batchResultToken;
        }

        public Batch build() {
            return new Batch(this.f130xu, this.f129mD);
        }
    }

    private Batch(List<PendingResult<?>> list, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzako = new Object();
        this.f124xp = list.size();
        this.f127xs = new PendingResult[this.f124xp];
        if (list.isEmpty()) {
            zzc(new BatchResult(Status.f158xZ, this.f127xs));
            return;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < list.size()) {
                PendingResult<?> pendingResult = (PendingResult) list.get(i2);
                this.f127xs[i2] = pendingResult;
                pendingResult.zza(new zza() {
                    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
                        return;
                     */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void zzx(com.google.android.gms.common.api.Status r6) {
                        /*
                            r5 = this;
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this
                            java.lang.Object r1 = r0.zzako
                            monitor-enter(r1)
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            boolean r0 = r0.isCanceled()     // Catch:{ all -> 0x0039 }
                            if (r0 == 0) goto L_0x0011
                            monitor-exit(r1)     // Catch:{ all -> 0x0039 }
                        L_0x0010:
                            return
                        L_0x0011:
                            boolean r0 = r6.isCanceled()     // Catch:{ all -> 0x0039 }
                            if (r0 == 0) goto L_0x003c
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            r2 = 1
                            r0.f126xr = r2     // Catch:{ all -> 0x0039 }
                        L_0x001d:
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            r0.f124xp = r0.f124xp - 1     // Catch:{ all -> 0x0039 }
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            int r0 = r0.f124xp     // Catch:{ all -> 0x0039 }
                            if (r0 != 0) goto L_0x0037
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            boolean r0 = r0.f126xr     // Catch:{ all -> 0x0039 }
                            if (r0 == 0) goto L_0x0049
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            com.google.android.gms.common.api.Batch.super.cancel()     // Catch:{ all -> 0x0039 }
                        L_0x0037:
                            monitor-exit(r1)     // Catch:{ all -> 0x0039 }
                            goto L_0x0010
                        L_0x0039:
                            r0 = move-exception
                            monitor-exit(r1)     // Catch:{ all -> 0x0039 }
                            throw r0
                        L_0x003c:
                            boolean r0 = r6.isSuccess()     // Catch:{ all -> 0x0039 }
                            if (r0 != 0) goto L_0x001d
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            r2 = 1
                            r0.f125xq = r2     // Catch:{ all -> 0x0039 }
                            goto L_0x001d
                        L_0x0049:
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            boolean r0 = r0.f125xq     // Catch:{ all -> 0x0039 }
                            if (r0 == 0) goto L_0x0069
                            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x0039 }
                            r2 = 13
                            r0.<init>(r2)     // Catch:{ all -> 0x0039 }
                        L_0x0058:
                            com.google.android.gms.common.api.Batch r2 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            com.google.android.gms.common.api.BatchResult r3 = new com.google.android.gms.common.api.BatchResult     // Catch:{ all -> 0x0039 }
                            com.google.android.gms.common.api.Batch r4 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            com.google.android.gms.common.api.PendingResult[] r4 = r4.f127xs     // Catch:{ all -> 0x0039 }
                            r3.<init>(r0, r4)     // Catch:{ all -> 0x0039 }
                            r2.zzc(r3)     // Catch:{ all -> 0x0039 }
                            goto L_0x0037
                        L_0x0069:
                            com.google.android.gms.common.api.Status r0 = com.google.android.gms.common.api.Status.f158xZ     // Catch:{ all -> 0x0039 }
                            goto L_0x0058
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.Batch.C04661.zzx(com.google.android.gms.common.api.Status):void");
                    }
                });
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    public void cancel() {
        super.cancel();
        for (PendingResult<?> cancel : this.f127xs) {
            cancel.cancel();
        }
    }

    /* renamed from: createFailedResult */
    public BatchResult zzc(Status status) {
        return new BatchResult(status, this.f127xs);
    }
}
