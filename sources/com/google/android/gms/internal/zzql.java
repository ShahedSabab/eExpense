package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.internal.zzz;

public final class zzql<O extends ApiOptions> {

    /* renamed from: vS */
    private final Api<O> f515vS;

    /* renamed from: xw */
    private final O f516xw;

    /* renamed from: yo */
    private final boolean f517yo = true;

    /* renamed from: yp */
    private final int f518yp;

    private zzql(Api<O> api) {
        this.f515vS = api;
        this.f516xw = null;
        this.f518yp = System.identityHashCode(this);
    }

    private zzql(Api<O> api, O o) {
        this.f515vS = api;
        this.f516xw = o;
        this.f518yp = zzz.hashCode(this.f515vS, this.f516xw);
    }

    public static <O extends ApiOptions> zzql<O> zza(Api<O> api, O o) {
        return new zzql<>(api, o);
    }

    public static <O extends ApiOptions> zzql<O> zzb(Api<O> api) {
        return new zzql<>(api);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzql)) {
            return false;
        }
        zzql zzql = (zzql) obj;
        return !this.f517yo && !zzql.f517yo && zzz.equal(this.f515vS, zzql.f515vS) && zzz.equal(this.f516xw, zzql.f516xw);
    }

    public int hashCode() {
        return this.f518yp;
    }

    public String zzarl() {
        return this.f515vS.getName();
    }
}
