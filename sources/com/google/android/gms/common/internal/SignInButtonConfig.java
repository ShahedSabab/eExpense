package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class SignInButtonConfig extends AbstractSafeParcelable {
    public static final Creator<SignInButtonConfig> CREATOR = new zzad();
    @Deprecated

    /* renamed from: Dg */
    private final Scope[] f262Dg;

    /* renamed from: EL */
    private final int f263EL;

    /* renamed from: EM */
    private final int f264EM;
    final int mVersionCode;

    SignInButtonConfig(int i, int i2, int i3, Scope[] scopeArr) {
        this.mVersionCode = i;
        this.f263EL = i2;
        this.f264EM = i3;
        this.f262Dg = scopeArr;
    }

    public SignInButtonConfig(int i, int i2, Scope[] scopeArr) {
        this(1, i, i2, null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzad.zza(this, parcel, i);
    }

    public int zzawq() {
        return this.f263EL;
    }

    public int zzawr() {
        return this.f264EM;
    }

    @Deprecated
    public Scope[] zzaws() {
        return this.f262Dg;
    }
}
