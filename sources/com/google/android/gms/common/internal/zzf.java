package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.view.View;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzxq;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzf {

    /* renamed from: DJ */
    private final Set<Scope> f308DJ;

    /* renamed from: DK */
    private final Map<Api<?>, zza> f309DK;

    /* renamed from: DL */
    private final zzxq f310DL;

    /* renamed from: DM */
    private Integer f311DM;

    /* renamed from: gj */
    private final Account f312gj;

    /* renamed from: hu */
    private final String f313hu;

    /* renamed from: xF */
    private final Set<Scope> f314xF;

    /* renamed from: xH */
    private final int f315xH;

    /* renamed from: xI */
    private final View f316xI;

    /* renamed from: xJ */
    private final String f317xJ;

    public static final class zza {

        /* renamed from: DN */
        public final boolean f318DN;

        /* renamed from: jw */
        public final Set<Scope> f319jw;

        public zza(Set<Scope> set, boolean z) {
            zzaa.zzy(set);
            this.f319jw = Collections.unmodifiableSet(set);
            this.f318DN = z;
        }
    }

    public zzf(Account account, Set<Scope> set, Map<Api<?>, zza> map, int i, View view, String str, String str2, zzxq zzxq) {
        this.f312gj = account;
        this.f314xF = set == null ? Collections.EMPTY_SET : Collections.unmodifiableSet(set);
        if (map == null) {
            map = Collections.EMPTY_MAP;
        }
        this.f309DK = map;
        this.f316xI = view;
        this.f315xH = i;
        this.f313hu = str;
        this.f317xJ = str2;
        this.f310DL = zzxq;
        HashSet hashSet = new HashSet(this.f314xF);
        for (zza zza2 : this.f309DK.values()) {
            hashSet.addAll(zza2.f319jw);
        }
        this.f308DJ = Collections.unmodifiableSet(hashSet);
    }

    public static zzf zzca(Context context) {
        return new Builder(context).zzarf();
    }

    public Account getAccount() {
        return this.f312gj;
    }

    @Deprecated
    public String getAccountName() {
        if (this.f312gj != null) {
            return this.f312gj.name;
        }
        return null;
    }

    public Account zzave() {
        return this.f312gj != null ? this.f312gj : new Account("<<default account>>", "com.google");
    }

    public int zzavo() {
        return this.f315xH;
    }

    public Set<Scope> zzavp() {
        return this.f314xF;
    }

    public Set<Scope> zzavq() {
        return this.f308DJ;
    }

    public Map<Api<?>, zza> zzavr() {
        return this.f309DK;
    }

    public String zzavs() {
        return this.f313hu;
    }

    public String zzavt() {
        return this.f317xJ;
    }

    public View zzavu() {
        return this.f316xI;
    }

    public zzxq zzavv() {
        return this.f310DL;
    }

    public Integer zzavw() {
        return this.f311DM;
    }

    public Set<Scope> zzc(Api<?> api) {
        zza zza2 = (zza) this.f309DK.get(api);
        if (zza2 == null || zza2.f319jw.isEmpty()) {
            return this.f314xF;
        }
        HashSet hashSet = new HashSet(this.f314xF);
        hashSet.addAll(zza2.f319jw);
        return hashSet;
    }

    public void zzc(Integer num) {
        this.f311DM = num;
    }
}
