package com.google.android.gms.common.stats;

import android.support.p000v4.util.SimpleArrayMap;

public class zzd {

    /* renamed from: FX */
    private final long f427FX;

    /* renamed from: FY */
    private final int f428FY;

    /* renamed from: FZ */
    private final SimpleArrayMap<String, Long> f429FZ;

    public zzd() {
        this.f427FX = 60000;
        this.f428FY = 10;
        this.f429FZ = new SimpleArrayMap<>(10);
    }

    public zzd(int i, long j) {
        this.f427FX = j;
        this.f428FY = i;
        this.f429FZ = new SimpleArrayMap<>();
    }
}
