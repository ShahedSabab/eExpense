package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

final class zzasc {
    final byte[] btQ;
    final int tag;

    zzasc(int i, byte[] bArr) {
        this.tag = i;
        this.btQ = bArr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzasc)) {
            return false;
        }
        zzasc zzasc = (zzasc) obj;
        return this.tag == zzasc.tag && Arrays.equals(this.btQ, zzasc.btQ);
    }

    public int hashCode() {
        return ((this.tag + 527) * 31) + Arrays.hashCode(this.btQ);
    }

    /* access modifiers changed from: 0000 */
    public void zza(zzart zzart) throws IOException {
        zzart.zzahd(this.tag);
        zzart.zzbh(this.btQ);
    }

    /* access modifiers changed from: 0000 */
    public int zzx() {
        return zzart.zzahe(this.tag) + 0 + this.btQ.length;
    }
}
