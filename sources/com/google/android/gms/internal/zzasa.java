package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzasa {
    protected volatile int btP = -1;

    public static final <T extends zzasa> T zza(T t, byte[] bArr) throws zzarz {
        return zzb(t, bArr, 0, bArr.length);
    }

    public static final void zza(zzasa zzasa, byte[] bArr, int i, int i2) {
        try {
            zzart zzc = zzart.zzc(bArr, i, i2);
            zzasa.zza(zzc);
            zzc.mo10769cm();
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public static final <T extends zzasa> T zzb(T t, byte[] bArr, int i, int i2) throws zzarz {
        try {
            zzars zzb = zzars.zzb(bArr, i, i2);
            t.zzb(zzb);
            zzb.zzagq(0);
            return t;
        } catch (zzarz e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }

    public static final byte[] zzf(zzasa zzasa) {
        byte[] bArr = new byte[zzasa.mo10838cz()];
        zza(zzasa, bArr, 0, bArr.length);
        return bArr;
    }

    /* renamed from: co */
    public zzasa clone() throws CloneNotSupportedException {
        return (zzasa) super.clone();
    }

    /* renamed from: cy */
    public int mo10837cy() {
        if (this.btP < 0) {
            mo10838cz();
        }
        return this.btP;
    }

    /* renamed from: cz */
    public int mo10838cz() {
        int zzx = zzx();
        this.btP = zzx;
        return zzx;
    }

    public String toString() {
        return zzasb.zzg(this);
    }

    public void zza(zzart zzart) throws IOException {
    }

    public abstract zzasa zzb(zzars zzars) throws IOException;

    /* access modifiers changed from: protected */
    public int zzx() {
        return 0;
    }
}
