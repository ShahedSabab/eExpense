package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.C0735zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzd implements Creator<AuthAccountRequest> {
    static void zza(AuthAccountRequest authAccountRequest, Parcel parcel, int i) {
        int zzcs = zzb.zzcs(parcel);
        zzb.zzc(parcel, 1, authAccountRequest.mVersionCode);
        zzb.zza(parcel, 2, authAccountRequest.f238Df, false);
        zzb.zza(parcel, 3, (T[]) authAccountRequest.f239Dg, i, false);
        zzb.zza(parcel, 4, authAccountRequest.f240Dh, false);
        zzb.zza(parcel, 5, authAccountRequest.f241Di, false);
        zzb.zzaj(parcel, zzcs);
    }

    /* renamed from: zzcj */
    public AuthAccountRequest createFromParcel(Parcel parcel) {
        Integer num = null;
        int zzcr = zza.zzcr(parcel);
        int i = 0;
        Integer num2 = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < zzcr) {
            int zzcq = zza.zzcq(parcel);
            switch (zza.zzgu(zzcq)) {
                case 1:
                    i = zza.zzg(parcel, zzcq);
                    break;
                case 2:
                    iBinder = zza.zzr(parcel, zzcq);
                    break;
                case 3:
                    scopeArr = (Scope[]) zza.zzb(parcel, zzcq, Scope.CREATOR);
                    break;
                case 4:
                    num2 = zza.zzh(parcel, zzcq);
                    break;
                case 5:
                    num = zza.zzh(parcel, zzcq);
                    break;
                default:
                    zza.zzb(parcel, zzcq);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcr) {
            return new AuthAccountRequest(i, iBinder, scopeArr, num2, num);
        }
        throw new C0735zza("Overread allowed size end=" + zzcr, parcel);
    }

    /* renamed from: zzgj */
    public AuthAccountRequest[] newArray(int i) {
        return new AuthAccountRequest[i];
    }
}
