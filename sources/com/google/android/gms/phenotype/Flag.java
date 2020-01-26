package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Comparator;

public class Flag extends AbstractSafeParcelable implements Comparable<Flag> {
    public static final Creator<Flag> CREATOR = new zzb();
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final zza aAA = new zza();

    /* renamed from: Fe */
    final String f840Fe;
    final long aAw;
    final byte[] aAx;
    public final int aAy;
    public final int aAz;
    final boolean ahI;
    final double ahK;
    final int mVersionCode;
    public final String name;

    public static class zza implements Comparator<Flag> {
        /* renamed from: zza */
        public int compare(Flag flag, Flag flag2) {
            return flag.aAz == flag2.aAz ? flag.name.compareTo(flag2.name) : flag.aAz - flag2.aAz;
        }
    }

    Flag(int i, String str, long j, boolean z, double d, String str2, byte[] bArr, int i2, int i3) {
        this.mVersionCode = i;
        this.name = str;
        this.aAw = j;
        this.ahI = z;
        this.ahK = d;
        this.f840Fe = str2;
        this.aAx = bArr;
        this.aAy = i2;
        this.aAz = i3;
    }

    private static int compare(byte b, byte b2) {
        return b - b2;
    }

    private static int compare(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    private static int compare(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        return j == j2 ? 0 : 1;
    }

    private static int compare(String str, String str2) {
        if (str == str2) {
            return 0;
        }
        if (str == null) {
            return -1;
        }
        if (str2 == null) {
            return 1;
        }
        return str.compareTo(str2);
    }

    private static int compare(boolean z, boolean z2) {
        if (z == z2) {
            return 0;
        }
        return z ? 1 : -1;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Flag)) {
            return false;
        }
        Flag flag = (Flag) obj;
        if (this.mVersionCode != flag.mVersionCode || !zzz.equal(this.name, flag.name) || this.aAy != flag.aAy || this.aAz != flag.aAz) {
            return false;
        }
        switch (this.aAy) {
            case 1:
                return this.aAw == flag.aAw;
            case 2:
                return this.ahI == flag.ahI;
            case 3:
                return this.ahK == flag.ahK;
            case 4:
                return zzz.equal(this.f840Fe, flag.f840Fe);
            case 5:
                return Arrays.equals(this.aAx, flag.aAx);
            default:
                throw new AssertionError("Invalid enum value: " + this.aAy);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        zza(sb);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    /* renamed from: zza */
    public int compareTo(Flag flag) {
        int compareTo = this.name.compareTo(flag.name);
        if (compareTo != 0) {
            return compareTo;
        }
        int compare = compare(this.aAy, flag.aAy);
        if (compare != 0) {
            return compare;
        }
        switch (this.aAy) {
            case 1:
                return compare(this.aAw, flag.aAw);
            case 2:
                return compare(this.ahI, flag.ahI);
            case 3:
                return Double.compare(this.ahK, flag.ahK);
            case 4:
                return compare(this.f840Fe, flag.f840Fe);
            case 5:
                if (this.aAx == flag.aAx) {
                    return 0;
                }
                if (this.aAx == null) {
                    return -1;
                }
                if (flag.aAx == null) {
                    return 1;
                }
                for (int i = 0; i < Math.min(this.aAx.length, flag.aAx.length); i++) {
                    int compare2 = compare(this.aAx[i], flag.aAx[i]);
                    if (compare2 != 0) {
                        return compare2;
                    }
                }
                return compare(this.aAx.length, flag.aAx.length);
            default:
                throw new AssertionError("Invalid enum value: " + this.aAy);
        }
    }

    public String zza(StringBuilder sb) {
        sb.append("Flag(");
        sb.append(this.mVersionCode);
        sb.append(", ");
        sb.append(this.name);
        sb.append(", ");
        switch (this.aAy) {
            case 1:
                sb.append(this.aAw);
                break;
            case 2:
                sb.append(this.ahI);
                break;
            case 3:
                sb.append(this.ahK);
                break;
            case 4:
                sb.append("'");
                sb.append(this.f840Fe);
                sb.append("'");
                break;
            case 5:
                if (this.aAx != null) {
                    sb.append("'");
                    sb.append(new String(this.aAx, UTF_8));
                    sb.append("'");
                    break;
                } else {
                    sb.append("null");
                    break;
                }
            default:
                String str = this.name;
                throw new AssertionError(new StringBuilder(String.valueOf(str).length() + 27).append("Invalid type: ").append(str).append(", ").append(this.aAy).toString());
        }
        sb.append(", ");
        sb.append(this.aAy);
        sb.append(", ");
        sb.append(this.aAz);
        sb.append(")");
        return sb.toString();
    }
}
