package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzz;

public abstract class zzc {

    /* renamed from: BU */
    protected int f201BU;

    /* renamed from: BV */
    private int f202BV;

    /* renamed from: zy */
    protected final DataHolder f203zy;

    public zzc(DataHolder dataHolder, int i) {
        this.f203zy = (DataHolder) zzaa.zzy(dataHolder);
        zzfy(i);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zzc)) {
            return false;
        }
        zzc zzc = (zzc) obj;
        return zzz.equal(Integer.valueOf(zzc.f201BU), Integer.valueOf(this.f201BU)) && zzz.equal(Integer.valueOf(zzc.f202BV), Integer.valueOf(this.f202BV)) && zzc.f203zy == this.f203zy;
    }

    /* access modifiers changed from: protected */
    public boolean getBoolean(String str) {
        return this.f203zy.zze(str, this.f201BU, this.f202BV);
    }

    /* access modifiers changed from: protected */
    public byte[] getByteArray(String str) {
        return this.f203zy.zzg(str, this.f201BU, this.f202BV);
    }

    /* access modifiers changed from: protected */
    public float getFloat(String str) {
        return this.f203zy.zzf(str, this.f201BU, this.f202BV);
    }

    /* access modifiers changed from: protected */
    public int getInteger(String str) {
        return this.f203zy.zzc(str, this.f201BU, this.f202BV);
    }

    /* access modifiers changed from: protected */
    public long getLong(String str) {
        return this.f203zy.zzb(str, this.f201BU, this.f202BV);
    }

    /* access modifiers changed from: protected */
    public String getString(String str) {
        return this.f203zy.zzd(str, this.f201BU, this.f202BV);
    }

    public int hashCode() {
        return zzz.hashCode(Integer.valueOf(this.f201BU), Integer.valueOf(this.f202BV), this.f203zy);
    }

    public boolean isDataValid() {
        return !this.f203zy.isClosed();
    }

    /* access modifiers changed from: protected */
    public void zza(String str, CharArrayBuffer charArrayBuffer) {
        this.f203zy.zza(str, this.f201BU, this.f202BV, charArrayBuffer);
    }

    /* access modifiers changed from: protected */
    public int zzaul() {
        return this.f201BU;
    }

    /* access modifiers changed from: protected */
    public void zzfy(int i) {
        zzaa.zzbs(i >= 0 && i < this.f203zy.getCount());
        this.f201BU = i;
        this.f202BV = this.f203zy.zzga(this.f201BU);
    }

    public boolean zzho(String str) {
        return this.f203zy.zzho(str);
    }

    /* access modifiers changed from: protected */
    public Uri zzhp(String str) {
        return this.f203zy.zzh(str, this.f201BU, this.f202BV);
    }

    /* access modifiers changed from: protected */
    public boolean zzhq(String str) {
        return this.f203zy.zzi(str, this.f201BU, this.f202BV);
    }
}
