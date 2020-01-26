package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.C0735zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzi implements Creator<GetServiceRequest> {
    static void zza(GetServiceRequest getServiceRequest, Parcel parcel, int i) {
        int zzcs = zzb.zzcs(parcel);
        zzb.zzc(parcel, 1, getServiceRequest.version);
        zzb.zzc(parcel, 2, getServiceRequest.f247DU);
        zzb.zzc(parcel, 3, getServiceRequest.f248DV);
        zzb.zza(parcel, 4, getServiceRequest.f249DW, false);
        zzb.zza(parcel, 5, getServiceRequest.f250DX, false);
        zzb.zza(parcel, 6, (T[]) getServiceRequest.f251DY, i, false);
        zzb.zza(parcel, 7, getServiceRequest.f252DZ, false);
        zzb.zza(parcel, 8, (Parcelable) getServiceRequest.f253Ea, i, false);
        zzb.zza(parcel, 9, getServiceRequest.f254Eb);
        zzb.zzaj(parcel, zzcs);
    }

    /* renamed from: zzcl */
    public GetServiceRequest createFromParcel(Parcel parcel) {
        int i = 0;
        Account account = null;
        int zzcr = zza.zzcr(parcel);
        long j = 0;
        Bundle bundle = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        String str = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzcr) {
            int zzcq = zza.zzcq(parcel);
            switch (zza.zzgu(zzcq)) {
                case 1:
                    i3 = zza.zzg(parcel, zzcq);
                    break;
                case 2:
                    i2 = zza.zzg(parcel, zzcq);
                    break;
                case 3:
                    i = zza.zzg(parcel, zzcq);
                    break;
                case 4:
                    str = zza.zzq(parcel, zzcq);
                    break;
                case 5:
                    iBinder = zza.zzr(parcel, zzcq);
                    break;
                case 6:
                    scopeArr = (Scope[]) zza.zzb(parcel, zzcq, Scope.CREATOR);
                    break;
                case 7:
                    bundle = zza.zzs(parcel, zzcq);
                    break;
                case 8:
                    account = (Account) zza.zza(parcel, zzcq, Account.CREATOR);
                    break;
                case 9:
                    j = zza.zzi(parcel, zzcq);
                    break;
                default:
                    zza.zzb(parcel, zzcq);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcr) {
            return new GetServiceRequest(i3, i2, i, str, iBinder, scopeArr, bundle, account, j);
        }
        throw new C0735zza("Overread allowed size end=" + zzcr, parcel);
    }

    /* renamed from: zzgm */
    public GetServiceRequest[] newArray(int i) {
        return new GetServiceRequest[i];
    }
}
