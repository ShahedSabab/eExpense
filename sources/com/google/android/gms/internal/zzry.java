package com.google.android.gms.internal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public abstract class zzry {

    /* renamed from: AF */
    private static final ExecutorService f744AF = new ThreadPoolExecutor(0, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new zzsw("GAC_Transform"));

    public static ExecutorService zzatf() {
        return f744AF;
    }
}
