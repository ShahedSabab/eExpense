package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.common.util.zzv;
import com.google.android.gms.common.util.zzy;

public class zzxr {
    private static boolean DEBUG = false;
    private static String TAG = "WakeLock";
    private static String aDy = "*gcore*:";

    /* renamed from: Gc */
    private final String f837Gc;

    /* renamed from: Ge */
    private final String f838Ge;
    private final int aDA;
    private final String aDB;
    private boolean aDC;
    private int aDD;
    private int aDE;
    private final WakeLock aDz;
    private WorkSource ajz;
    private final Context mContext;

    public zzxr(Context context, int i, String str) {
        this(context, i, str, null, context == null ? null : context.getPackageName());
    }

    @SuppressLint({"UnwrappedWakeLock"})
    public zzxr(Context context, int i, String str, String str2, String str3) {
        this(context, i, str, str2, str3, null);
    }

    @SuppressLint({"UnwrappedWakeLock"})
    public zzxr(Context context, int i, String str, String str2, String str3, String str4) {
        this.aDC = true;
        zzaa.zzh(str, "Wake lock name can NOT be empty");
        this.aDA = i;
        this.aDB = str2;
        this.f838Ge = str4;
        this.mContext = context.getApplicationContext();
        if (!"com.google.android.gms".equals(context.getPackageName())) {
            String valueOf = String.valueOf(aDy);
            String valueOf2 = String.valueOf(str);
            this.f837Gc = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        } else {
            this.f837Gc = str;
        }
        this.aDz = ((PowerManager) context.getSystemService("power")).newWakeLock(i, str);
        if (zzy.zzcm(this.mContext)) {
            if (zzv.zzij(str3)) {
                str3 = context.getPackageName();
            }
            this.ajz = zzy.zzy(context, str3);
            zzc(this.ajz);
        }
    }

    private void zzd(WorkSource workSource) {
        try {
            this.aDz.setWorkSource(workSource);
        } catch (IllegalArgumentException e) {
            Log.wtf(TAG, e.toString());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001d, code lost:
        if (r12.aDE == 0) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0015, code lost:
        if (r0 == false) goto L_0x0017;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzk(java.lang.String r13, long r14) {
        /*
            r12 = this;
            boolean r0 = r12.zzoo(r13)
            java.lang.String r6 = r12.zzp(r13, r0)
            monitor-enter(r12)
            boolean r1 = r12.aDC     // Catch:{ all -> 0x0044 }
            if (r1 == 0) goto L_0x0017
            int r1 = r12.aDD     // Catch:{ all -> 0x0044 }
            int r2 = r1 + 1
            r12.aDD = r2     // Catch:{ all -> 0x0044 }
            if (r1 == 0) goto L_0x001f
            if (r0 != 0) goto L_0x001f
        L_0x0017:
            boolean r0 = r12.aDC     // Catch:{ all -> 0x0044 }
            if (r0 != 0) goto L_0x0042
            int r0 = r12.aDE     // Catch:{ all -> 0x0044 }
            if (r0 != 0) goto L_0x0042
        L_0x001f:
            com.google.android.gms.common.stats.zzg r1 = com.google.android.gms.common.stats.zzg.zzayg()     // Catch:{ all -> 0x0044 }
            android.content.Context r2 = r12.mContext     // Catch:{ all -> 0x0044 }
            android.os.PowerManager$WakeLock r0 = r12.aDz     // Catch:{ all -> 0x0044 }
            java.lang.String r3 = com.google.android.gms.common.stats.zze.zza(r0, r6)     // Catch:{ all -> 0x0044 }
            r4 = 7
            java.lang.String r5 = r12.f837Gc     // Catch:{ all -> 0x0044 }
            java.lang.String r7 = r12.f838Ge     // Catch:{ all -> 0x0044 }
            int r8 = r12.aDA     // Catch:{ all -> 0x0044 }
            android.os.WorkSource r0 = r12.ajz     // Catch:{ all -> 0x0044 }
            java.util.List r9 = com.google.android.gms.common.util.zzy.zzb(r0)     // Catch:{ all -> 0x0044 }
            r10 = r14
            r1.zza(r2, r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x0044 }
            int r0 = r12.aDE     // Catch:{ all -> 0x0044 }
            int r0 = r0 + 1
            r12.aDE = r0     // Catch:{ all -> 0x0044 }
        L_0x0042:
            monitor-exit(r12)     // Catch:{ all -> 0x0044 }
            return
        L_0x0044:
            r0 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x0044 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzxr.zzk(java.lang.String, long):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        if (r9.aDE == 1) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0015, code lost:
        if (r0 == false) goto L_0x0017;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzon(java.lang.String r10) {
        /*
            r9 = this;
            boolean r0 = r9.zzoo(r10)
            java.lang.String r5 = r9.zzp(r10, r0)
            monitor-enter(r9)
            boolean r1 = r9.aDC     // Catch:{ all -> 0x0045 }
            if (r1 == 0) goto L_0x0017
            int r1 = r9.aDD     // Catch:{ all -> 0x0045 }
            int r1 = r1 + -1
            r9.aDD = r1     // Catch:{ all -> 0x0045 }
            if (r1 == 0) goto L_0x0020
            if (r0 != 0) goto L_0x0020
        L_0x0017:
            boolean r0 = r9.aDC     // Catch:{ all -> 0x0045 }
            if (r0 != 0) goto L_0x0043
            int r0 = r9.aDE     // Catch:{ all -> 0x0045 }
            r1 = 1
            if (r0 != r1) goto L_0x0043
        L_0x0020:
            com.google.android.gms.common.stats.zzg r0 = com.google.android.gms.common.stats.zzg.zzayg()     // Catch:{ all -> 0x0045 }
            android.content.Context r1 = r9.mContext     // Catch:{ all -> 0x0045 }
            android.os.PowerManager$WakeLock r2 = r9.aDz     // Catch:{ all -> 0x0045 }
            java.lang.String r2 = com.google.android.gms.common.stats.zze.zza(r2, r5)     // Catch:{ all -> 0x0045 }
            r3 = 8
            java.lang.String r4 = r9.f837Gc     // Catch:{ all -> 0x0045 }
            java.lang.String r6 = r9.f838Ge     // Catch:{ all -> 0x0045 }
            int r7 = r9.aDA     // Catch:{ all -> 0x0045 }
            android.os.WorkSource r8 = r9.ajz     // Catch:{ all -> 0x0045 }
            java.util.List r8 = com.google.android.gms.common.util.zzy.zzb(r8)     // Catch:{ all -> 0x0045 }
            r0.zza(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0045 }
            int r0 = r9.aDE     // Catch:{ all -> 0x0045 }
            int r0 = r0 + -1
            r9.aDE = r0     // Catch:{ all -> 0x0045 }
        L_0x0043:
            monitor-exit(r9)     // Catch:{ all -> 0x0045 }
            return
        L_0x0045:
            r0 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x0045 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzxr.zzon(java.lang.String):void");
    }

    private boolean zzoo(String str) {
        return !TextUtils.isEmpty(str) && !str.equals(this.aDB);
    }

    private String zzp(String str, boolean z) {
        return this.aDC ? z ? str : this.aDB : this.aDB;
    }

    public void acquire(long j) {
        if (!zzs.zzayq() && this.aDC) {
            String str = TAG;
            String str2 = "Do not acquire with timeout on reference counted WakeLocks before ICS. wakelock: ";
            String valueOf = String.valueOf(this.f837Gc);
            Log.wtf(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
        zzk(null, j);
        this.aDz.acquire(j);
    }

    public boolean isHeld() {
        return this.aDz.isHeld();
    }

    public void release() {
        zzon(null);
        this.aDz.release();
    }

    public void setReferenceCounted(boolean z) {
        this.aDz.setReferenceCounted(z);
        this.aDC = z;
    }

    public void zzc(WorkSource workSource) {
        if (workSource != null && zzy.zzcm(this.mContext)) {
            if (this.ajz != null) {
                this.ajz.add(workSource);
            } else {
                this.ajz = workSource;
            }
            zzd(this.ajz);
        }
    }
}
