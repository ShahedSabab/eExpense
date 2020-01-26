package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza.C0735zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza implements Creator<Configuration> {
    static void zza(Configuration configuration, Parcel parcel, int i) {
        int zzcs = zzb.zzcs(parcel);
        zzb.zzc(parcel, 1, configuration.mVersionCode);
        zzb.zzc(parcel, 2, configuration.aAs);
        zzb.zza(parcel, 3, (T[]) configuration.aAt, i, false);
        zzb.zza(parcel, 4, configuration.aAu, false);
        zzb.zzaj(parcel, zzcs);
    }

    /* renamed from: zzrf */
    public Configuration createFromParcel(Parcel parcel) {
        String[] zzac;
        Flag[] flagArr;
        int i;
        int i2;
        String[] strArr = null;
        int i3 = 0;
        int zzcr = com.google.android.gms.common.internal.safeparcel.zza.zzcr(parcel);
        Flag[] flagArr2 = null;
        int i4 = 0;
        while (parcel.dataPosition() < zzcr) {
            int zzcq = com.google.android.gms.common.internal.safeparcel.zza.zzcq(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzgu(zzcq)) {
                case 1:
                    String[] strArr2 = strArr;
                    flagArr = flagArr2;
                    i = i3;
                    i2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcq);
                    zzac = strArr2;
                    break;
                case 2:
                    i2 = i4;
                    Flag[] flagArr3 = flagArr2;
                    i = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcq);
                    zzac = strArr;
                    flagArr = flagArr3;
                    break;
                case 3:
                    i = i3;
                    i2 = i4;
                    String[] strArr3 = strArr;
                    flagArr = (Flag[]) com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzcq, Flag.CREATOR);
                    zzac = strArr3;
                    break;
                case 4:
                    zzac = com.google.android.gms.common.internal.safeparcel.zza.zzac(parcel, zzcq);
                    flagArr = flagArr2;
                    i = i3;
                    i2 = i4;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzcq);
                    zzac = strArr;
                    flagArr = flagArr2;
                    i = i3;
                    i2 = i4;
                    break;
            }
            i4 = i2;
            i3 = i;
            flagArr2 = flagArr;
            strArr = zzac;
        }
        if (parcel.dataPosition() == zzcr) {
            return new Configuration(i4, i3, flagArr2, strArr);
        }
        throw new C0735zza("Overread allowed size end=" + zzcr, parcel);
    }

    /* renamed from: zzyx */
    public Configuration[] newArray(int i) {
        return new Configuration[i];
    }
}
