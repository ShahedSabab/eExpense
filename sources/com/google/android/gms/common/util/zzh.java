package com.google.android.gms.common.util;

import android.os.SystemClock;

public class zzh implements zze {

    /* renamed from: Gq */
    private static zzh f433Gq = new zzh();

    private zzh() {
    }

    public static zze zzayl() {
        return f433Gq;
    }

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public long elapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }

    public long nanoTime() {
        return System.nanoTime();
    }
}
