package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzp.zza;
import com.google.android.gms.common.zzc;
import java.util.Collection;

public class GetServiceRequest extends AbstractSafeParcelable {
    public static final Creator<GetServiceRequest> CREATOR = new zzi();

    /* renamed from: DU */
    final int f247DU;

    /* renamed from: DV */
    int f248DV;

    /* renamed from: DW */
    String f249DW;

    /* renamed from: DX */
    IBinder f250DX;

    /* renamed from: DY */
    Scope[] f251DY;

    /* renamed from: DZ */
    Bundle f252DZ;

    /* renamed from: Ea */
    Account f253Ea;

    /* renamed from: Eb */
    long f254Eb;
    final int version;

    public GetServiceRequest(int i) {
        this.version = 3;
        this.f248DV = zzc.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        this.f247DU = i;
    }

    GetServiceRequest(int i, int i2, int i3, String str, IBinder iBinder, Scope[] scopeArr, Bundle bundle, Account account, long j) {
        this.version = i;
        this.f247DU = i2;
        this.f248DV = i3;
        if ("com.google.android.gms".equals(str)) {
            this.f249DW = "com.google.android.gms";
        } else {
            this.f249DW = str;
        }
        if (i < 2) {
            this.f253Ea = zzdq(iBinder);
        } else {
            this.f250DX = iBinder;
            this.f253Ea = account;
        }
        this.f251DY = scopeArr;
        this.f252DZ = bundle;
        this.f254Eb = j;
    }

    private Account zzdq(IBinder iBinder) {
        if (iBinder != null) {
            return zza.zza(zza.zzdr(iBinder));
        }
        return null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzi.zza(this, parcel, i);
    }

    public GetServiceRequest zzb(zzp zzp) {
        if (zzp != null) {
            this.f250DX = zzp.asBinder();
        }
        return this;
    }

    public GetServiceRequest zze(Account account) {
        this.f253Ea = account;
        return this;
    }

    public GetServiceRequest zzf(Collection<Scope> collection) {
        this.f251DY = (Scope[]) collection.toArray(new Scope[collection.size()]);
        return this;
    }

    public GetServiceRequest zzhv(String str) {
        this.f249DW = str;
        return this;
    }

    public GetServiceRequest zzo(Bundle bundle) {
        this.f252DZ = bundle;
        return this;
    }
}
