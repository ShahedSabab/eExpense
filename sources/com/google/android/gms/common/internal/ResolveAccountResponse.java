package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzp.zza;

public class ResolveAccountResponse extends AbstractSafeParcelable {
    public static final Creator<ResolveAccountResponse> CREATOR = new zzac();

    /* renamed from: Df */
    IBinder f258Df;

    /* renamed from: EJ */
    private ConnectionResult f259EJ;

    /* renamed from: EK */
    private boolean f260EK;
    final int mVersionCode;

    /* renamed from: zN */
    private boolean f261zN;

    ResolveAccountResponse(int i, IBinder iBinder, ConnectionResult connectionResult, boolean z, boolean z2) {
        this.mVersionCode = i;
        this.f258Df = iBinder;
        this.f259EJ = connectionResult;
        this.f261zN = z;
        this.f260EK = z2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResolveAccountResponse)) {
            return false;
        }
        ResolveAccountResponse resolveAccountResponse = (ResolveAccountResponse) obj;
        return this.f259EJ.equals(resolveAccountResponse.f259EJ) && zzawm().equals(resolveAccountResponse.zzawm());
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzac.zza(this, parcel, i);
    }

    public zzp zzawm() {
        return zza.zzdr(this.f258Df);
    }

    public ConnectionResult zzawn() {
        return this.f259EJ;
    }

    public boolean zzawo() {
        return this.f261zN;
    }

    public boolean zzawp() {
        return this.f260EK;
    }
}
