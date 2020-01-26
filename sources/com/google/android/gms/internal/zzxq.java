package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;

public final class zzxq implements Optional {
    public static final zzxq aDl = new zza().zzcdh();
    private final boolean aDm;
    private final boolean aDn;
    private final Long aDo;
    private final Long aDp;

    /* renamed from: jr */
    private final boolean f833jr;

    /* renamed from: jt */
    private final boolean f834jt;

    /* renamed from: ju */
    private final String f835ju;

    /* renamed from: jv */
    private final String f836jv;

    public static final class zza {
        public zzxq zzcdh() {
            return new zzxq(false, false, null, false, null, false, null, null);
        }
    }

    private zzxq(boolean z, boolean z2, String str, boolean z3, String str2, boolean z4, Long l, Long l2) {
        this.aDm = z;
        this.f833jr = z2;
        this.f835ju = str;
        this.f834jt = z3;
        this.aDn = z4;
        this.f836jv = str2;
        this.aDo = l;
        this.aDp = l2;
    }

    public boolean zzaiu() {
        return this.f833jr;
    }

    public boolean zzaiw() {
        return this.f834jt;
    }

    public String zzaix() {
        return this.f835ju;
    }

    @Nullable
    public String zzaiy() {
        return this.f836jv;
    }

    public boolean zzcdd() {
        return this.aDm;
    }

    public boolean zzcde() {
        return this.aDn;
    }

    @Nullable
    public Long zzcdf() {
        return this.aDo;
    }

    @Nullable
    public Long zzcdg() {
        return this.aDp;
    }
}
