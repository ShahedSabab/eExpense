package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.p000v4.p002os.EnvironmentCompat;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.common.internal.zzz.zza;

public class PlaceReport extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<PlaceReport> CREATOR = new zzi();
    private final String alV;

    /* renamed from: bQ */
    private final String f839bQ;
    private final String mTag;
    final int mVersionCode;

    PlaceReport(int i, String str, String str2, String str3) {
        this.mVersionCode = i;
        this.alV = str;
        this.mTag = str2;
        this.f839bQ = str3;
    }

    public static PlaceReport create(String str, String str2) {
        return zzj(str, str2, EnvironmentCompat.MEDIA_UNKNOWN);
    }

    public static PlaceReport zzj(String str, String str2, String str3) {
        zzaa.zzy(str);
        zzaa.zzib(str2);
        zzaa.zzib(str3);
        zzaa.zzb(zzla(str3), (Object) "Invalid source");
        return new PlaceReport(1, str, str2, str3);
    }

    private static boolean zzla(String str) {
        char c = 65535;
        switch (str.hashCode()) {
            case -1436706272:
                if (str.equals("inferredGeofencing")) {
                    c = 2;
                    break;
                }
                break;
            case -1194968642:
                if (str.equals("userReported")) {
                    c = 1;
                    break;
                }
                break;
            case -284840886:
                if (str.equals(EnvironmentCompat.MEDIA_UNKNOWN)) {
                    c = 0;
                    break;
                }
                break;
            case -262743844:
                if (str.equals("inferredReverseGeocoding")) {
                    c = 4;
                    break;
                }
                break;
            case 1164924125:
                if (str.equals("inferredSnappedToRoad")) {
                    c = 5;
                    break;
                }
                break;
            case 1287171955:
                if (str.equals("inferredRadioSignals")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return true;
            default:
                return false;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlaceReport)) {
            return false;
        }
        PlaceReport placeReport = (PlaceReport) obj;
        return zzz.equal(this.alV, placeReport.alV) && zzz.equal(this.mTag, placeReport.mTag) && zzz.equal(this.f839bQ, placeReport.f839bQ);
    }

    public String getPlaceId() {
        return this.alV;
    }

    public String getSource() {
        return this.f839bQ;
    }

    public String getTag() {
        return this.mTag;
    }

    public int hashCode() {
        return zzz.hashCode(this.alV, this.mTag, this.f839bQ);
    }

    public String toString() {
        zza zzx = zzz.zzx(this);
        zzx.zzg("placeId", this.alV);
        zzx.zzg("tag", this.mTag);
        if (!EnvironmentCompat.MEDIA_UNKNOWN.equals(this.f839bQ)) {
            zzx.zzg("source", this.f839bQ);
        }
        return zzx.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzi.zza(this, parcel, i);
    }
}
