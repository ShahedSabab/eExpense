package com.google.android.gms.auth.api.signin.internal;

public class zze {

    /* renamed from: jI */
    static int f104jI = 31;

    /* renamed from: jJ */
    private int f105jJ = 1;

    public int zzajf() {
        return this.f105jJ;
    }

    public zze zzbe(boolean z) {
        this.f105jJ = (z ? 1 : 0) + (this.f105jJ * f104jI);
        return this;
    }

    public zze zzq(Object obj) {
        this.f105jJ = (obj == null ? 0 : obj.hashCode()) + (this.f105jJ * f104jI);
        return this;
    }
}
