package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import java.util.List;

public final class WakeLockEvent extends StatsEvent {
    public static final Creator<WakeLockEvent> CREATOR = new zzf();

    /* renamed from: Ga */
    private final long f387Ga;

    /* renamed from: Gb */
    private int f388Gb;

    /* renamed from: Gc */
    private final String f389Gc;

    /* renamed from: Gd */
    private final String f390Gd;

    /* renamed from: Ge */
    private final String f391Ge;

    /* renamed from: Gf */
    private final int f392Gf;

    /* renamed from: Gg */
    private final List<String> f393Gg;

    /* renamed from: Gh */
    private final String f394Gh;

    /* renamed from: Gi */
    private final long f395Gi;

    /* renamed from: Gj */
    private int f396Gj;

    /* renamed from: Gk */
    private final String f397Gk;

    /* renamed from: Gl */
    private final float f398Gl;

    /* renamed from: Gm */
    private long f399Gm;
    private final long mTimeout;
    final int mVersionCode;

    WakeLockEvent(int i, long j, int i2, String str, int i3, List<String> list, String str2, long j2, int i4, String str3, String str4, float f, long j3, String str5) {
        this.mVersionCode = i;
        this.f387Ga = j;
        this.f388Gb = i2;
        this.f389Gc = str;
        this.f390Gd = str3;
        this.f391Ge = str5;
        this.f392Gf = i3;
        this.f399Gm = -1;
        this.f393Gg = list;
        this.f394Gh = str2;
        this.f395Gi = j2;
        this.f396Gj = i4;
        this.f397Gk = str4;
        this.f398Gl = f;
        this.mTimeout = j3;
    }

    public WakeLockEvent(long j, int i, String str, int i2, List<String> list, String str2, long j2, int i3, String str3, String str4, float f, long j3, String str5) {
        this(2, j, i, str, i2, list, str2, j2, i3, str3, str4, f, j3, str5);
    }

    public int getEventType() {
        return this.f388Gb;
    }

    public long getTimeMillis() {
        return this.f387Ga;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzf.zza(this, parcel, i);
    }

    public long zzaxt() {
        return this.f399Gm;
    }

    public String zzaxu() {
        String valueOf = String.valueOf("\t");
        String valueOf2 = String.valueOf(zzaxv());
        String valueOf3 = String.valueOf("\t");
        int zzaxy = zzaxy();
        String valueOf4 = String.valueOf("\t");
        String join = zzaxz() == null ? "" : TextUtils.join(",", zzaxz());
        String valueOf5 = String.valueOf("\t");
        int zzayc = zzayc();
        String valueOf6 = String.valueOf("\t");
        String zzaxw = zzaxw() == null ? "" : zzaxw();
        String valueOf7 = String.valueOf("\t");
        String zzayd = zzayd() == null ? "" : zzayd();
        String valueOf8 = String.valueOf("\t");
        float zzaye = zzaye();
        String valueOf9 = String.valueOf("\t");
        String zzaxx = zzaxx() == null ? "" : zzaxx();
        return new StringBuilder(String.valueOf(valueOf).length() + 37 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length() + String.valueOf(join).length() + String.valueOf(valueOf5).length() + String.valueOf(valueOf6).length() + String.valueOf(zzaxw).length() + String.valueOf(valueOf7).length() + String.valueOf(zzayd).length() + String.valueOf(valueOf8).length() + String.valueOf(valueOf9).length() + String.valueOf(zzaxx).length()).append(valueOf).append(valueOf2).append(valueOf3).append(zzaxy).append(valueOf4).append(join).append(valueOf5).append(zzayc).append(valueOf6).append(zzaxw).append(valueOf7).append(zzayd).append(valueOf8).append(zzaye).append(valueOf9).append(zzaxx).toString();
    }

    public String zzaxv() {
        return this.f389Gc;
    }

    public String zzaxw() {
        return this.f390Gd;
    }

    public String zzaxx() {
        return this.f391Ge;
    }

    public int zzaxy() {
        return this.f392Gf;
    }

    public List<String> zzaxz() {
        return this.f393Gg;
    }

    public String zzaya() {
        return this.f394Gh;
    }

    public long zzayb() {
        return this.f395Gi;
    }

    public int zzayc() {
        return this.f396Gj;
    }

    public String zzayd() {
        return this.f397Gk;
    }

    public float zzaye() {
        return this.f398Gl;
    }

    public long zzayf() {
        return this.mTimeout;
    }
}
