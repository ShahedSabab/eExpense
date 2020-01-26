package com.google.android.gms.internal;

public final class zzvu {

    /* renamed from: WD */
    private static zzvu f825WD;

    /* renamed from: WE */
    private final zzvr f826WE = new zzvr();

    /* renamed from: WF */
    private final zzvs f827WF = new zzvs();

    static {
        zza(new zzvu());
    }

    private zzvu() {
    }

    protected static void zza(zzvu zzvu) {
        synchronized (zzvu.class) {
            f825WD = zzvu;
        }
    }

    private static zzvu zzbhd() {
        zzvu zzvu;
        synchronized (zzvu.class) {
            zzvu = f825WD;
        }
        return zzvu;
    }

    public static zzvr zzbhe() {
        return zzbhd().f826WE;
    }

    public static zzvs zzbhf() {
        return zzbhd().f827WF;
    }
}
