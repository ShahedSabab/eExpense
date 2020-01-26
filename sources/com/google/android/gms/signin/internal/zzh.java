package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.C0735zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzh implements Creator<SignInRequest> {
    static void zza(SignInRequest signInRequest, Parcel parcel, int i) {
        int zzcs = zzb.zzcs(parcel);
        zzb.zzc(parcel, 1, signInRequest.mVersionCode);
        zzb.zza(parcel, 2, (Parcelable) signInRequest.zzcdm(), i, false);
        zzb.zzaj(parcel, zzcs);
    }

    /* renamed from: zzsc */
    public SignInRequest createFromParcel(Parcel parcel) {
        int zzcr = zza.zzcr(parcel);
        int i = 0;
        ResolveAccountRequest resolveAccountRequest = null;
        while (parcel.dataPosition() < zzcr) {
            int zzcq = zza.zzcq(parcel);
            switch (zza.zzgu(zzcq)) {
                case 1:
                    i = zza.zzg(parcel, zzcq);
                    break;
                case 2:
                    resolveAccountRequest = (ResolveAccountRequest) zza.zza(parcel, zzcq, ResolveAccountRequest.CREATOR);
                    break;
                default:
                    zza.zzb(parcel, zzcq);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcr) {
            return new SignInRequest(i, resolveAccountRequest);
        }
        throw new C0735zza("Overread allowed size end=" + zzcr, parcel);
    }

    /* renamed from: zzzx */
    public SignInRequest[] newArray(int i) {
        return new SignInRequest[i];
    }
}
