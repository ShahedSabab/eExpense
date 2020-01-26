package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class AuthAccountRequest extends AbstractSafeParcelable {
    public static final Creator<AuthAccountRequest> CREATOR = new zzd();

    /* renamed from: Df */
    final IBinder f238Df;

    /* renamed from: Dg */
    final Scope[] f239Dg;

    /* renamed from: Dh */
    Integer f240Dh;

    /* renamed from: Di */
    Integer f241Di;
    final int mVersionCode;

    AuthAccountRequest(int i, IBinder iBinder, Scope[] scopeArr, Integer num, Integer num2) {
        this.mVersionCode = i;
        this.f238Df = iBinder;
        this.f239Dg = scopeArr;
        this.f240Dh = num;
        this.f241Di = num2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzd.zza(this, parcel, i);
    }
}
