package com.google.android.gms.internal;

import com.google.android.gms.internal.zzaru;
import java.io.IOException;

public abstract class zzaru<M extends zzaru<M>> extends zzasa {
    protected zzarw btG;

    /* renamed from: cn */
    public M clone() throws CloneNotSupportedException {
        M m = (zzaru) super.clone();
        zzary.zza(this, (zzaru) m);
        return m;
    }

    /* renamed from: co */
    public /* synthetic */ zzasa mo10806co() throws CloneNotSupportedException {
        return (zzaru) clone();
    }

    public final <T> T zza(zzarv<M, T> zzarv) {
        if (this.btG == null) {
            return null;
        }
        zzarx zzahh = this.btG.zzahh(zzasd.zzahl(zzarv.tag));
        if (zzahh != null) {
            return zzahh.zzb(zzarv);
        }
        return null;
    }

    public void zza(zzart zzart) throws IOException {
        if (this.btG != null) {
            for (int i = 0; i < this.btG.size(); i++) {
                this.btG.zzahi(i).zza(zzart);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzars zzars, int i) throws IOException {
        int position = zzars.getPosition();
        if (!zzars.zzagr(i)) {
            return false;
        }
        int zzahl = zzasd.zzahl(i);
        zzasc zzasc = new zzasc(i, zzars.zzae(position, zzars.getPosition() - position));
        zzarx zzarx = null;
        if (this.btG == null) {
            this.btG = new zzarw();
        } else {
            zzarx = this.btG.zzahh(zzahl);
        }
        if (zzarx == null) {
            zzarx = new zzarx();
            this.btG.zza(zzahl, zzarx);
        }
        zzarx.zza(zzasc);
        return true;
    }

    /* access modifiers changed from: protected */
    public int zzx() {
        if (this.btG == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.btG.size(); i2++) {
            i += this.btG.zzahi(i2).zzx();
        }
        return i;
    }
}
