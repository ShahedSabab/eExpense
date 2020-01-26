package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@Deprecated
public class ValidateAccountRequest extends AbstractSafeParcelable {
    public static final Creator<ValidateAccountRequest> CREATOR = new zzai();

    /* renamed from: Df */
    final IBinder f265Df;

    /* renamed from: Dg */
    private final Scope[] f266Dg;

    /* renamed from: ER */
    private final int f267ER;

    /* renamed from: ES */
    private final Bundle f268ES;

    /* renamed from: ET */
    private final String f269ET;
    final int mVersionCode;

    ValidateAccountRequest(int i, int i2, IBinder iBinder, Scope[] scopeArr, Bundle bundle, String str) {
        this.mVersionCode = i;
        this.f267ER = i2;
        this.f265Df = iBinder;
        this.f266Dg = scopeArr;
        this.f268ES = bundle;
        this.f269ET = str;
    }

    public String getCallingPackage() {
        return this.f269ET;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzai.zza(this, parcel, i);
    }

    public Scope[] zzaws() {
        return this.f266Dg;
    }

    public int zzawu() {
        return this.f267ER;
    }

    public Bundle zzawv() {
        return this.f268ES;
    }
}
