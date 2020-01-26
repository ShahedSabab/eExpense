package com.google.android.gms.internal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class zzrg {

    /* renamed from: AF */
    private static final ExecutorService f689AF = Executors.newFixedThreadPool(2, new zzsw("GAC_Executor"));

    public static ExecutorService zzatf() {
        return f689AF;
    }
}
