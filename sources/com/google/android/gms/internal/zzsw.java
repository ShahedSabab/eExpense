package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzaa;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class zzsw implements ThreadFactory {

    /* renamed from: GM */
    private final String f804GM;

    /* renamed from: GN */
    private final AtomicInteger f805GN;

    /* renamed from: GO */
    private final ThreadFactory f806GO;
    private final int mPriority;

    public zzsw(String str) {
        this(str, 0);
    }

    public zzsw(String str, int i) {
        this.f805GN = new AtomicInteger();
        this.f806GO = Executors.defaultThreadFactory();
        this.f804GM = (String) zzaa.zzb(str, (Object) "Name must not be null");
        this.mPriority = i;
    }

    public Thread newThread(Runnable runnable) {
        Thread newThread = this.f806GO.newThread(new zzsx(runnable, this.mPriority));
        String str = this.f804GM;
        newThread.setName(new StringBuilder(String.valueOf(str).length() + 13).append(str).append("[").append(this.f805GN.getAndIncrement()).append("]").toString());
        return newThread;
    }
}
