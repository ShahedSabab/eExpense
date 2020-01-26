package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class zzarx implements Cloneable {
    private zzarv<?, ?> btM;
    private List<zzasc> btN = new ArrayList();
    private Object value;

    zzarx() {
    }

    private byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzx()];
        zza(zzart.zzbe(bArr));
        return bArr;
    }

    /* renamed from: cq */
    public final zzarx clone() {
        int i = 0;
        zzarx zzarx = new zzarx();
        try {
            zzarx.btM = this.btM;
            if (this.btN == null) {
                zzarx.btN = null;
            } else {
                zzarx.btN.addAll(this.btN);
            }
            if (this.value != null) {
                if (this.value instanceof zzasa) {
                    zzarx.value = (zzasa) ((zzasa) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    zzarx.value = ((byte[]) this.value).clone();
                } else if (this.value instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) this.value;
                    byte[][] bArr2 = new byte[bArr.length][];
                    zzarx.value = bArr2;
                    for (int i2 = 0; i2 < bArr.length; i2++) {
                        bArr2[i2] = (byte[]) bArr[i2].clone();
                    }
                } else if (this.value instanceof boolean[]) {
                    zzarx.value = ((boolean[]) this.value).clone();
                } else if (this.value instanceof int[]) {
                    zzarx.value = ((int[]) this.value).clone();
                } else if (this.value instanceof long[]) {
                    zzarx.value = ((long[]) this.value).clone();
                } else if (this.value instanceof float[]) {
                    zzarx.value = ((float[]) this.value).clone();
                } else if (this.value instanceof double[]) {
                    zzarx.value = ((double[]) this.value).clone();
                } else if (this.value instanceof zzasa[]) {
                    zzasa[] zzasaArr = (zzasa[]) this.value;
                    zzasa[] zzasaArr2 = new zzasa[zzasaArr.length];
                    zzarx.value = zzasaArr2;
                    while (true) {
                        int i3 = i;
                        if (i3 >= zzasaArr.length) {
                            break;
                        }
                        zzasaArr2[i3] = (zzasa) zzasaArr[i3].clone();
                        i = i3 + 1;
                    }
                }
            }
            return zzarx;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzarx)) {
            return false;
        }
        zzarx zzarx = (zzarx) obj;
        if (this.value == null || zzarx.value == null) {
            if (this.btN != null && zzarx.btN != null) {
                return this.btN.equals(zzarx.btN);
            }
            try {
                return Arrays.equals(toByteArray(), zzarx.toByteArray());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else if (this.btM == zzarx.btM) {
            return !this.btM.bkp.isArray() ? this.value.equals(zzarx.value) : this.value instanceof byte[] ? Arrays.equals((byte[]) this.value, (byte[]) zzarx.value) : this.value instanceof int[] ? Arrays.equals((int[]) this.value, (int[]) zzarx.value) : this.value instanceof long[] ? Arrays.equals((long[]) this.value, (long[]) zzarx.value) : this.value instanceof float[] ? Arrays.equals((float[]) this.value, (float[]) zzarx.value) : this.value instanceof double[] ? Arrays.equals((double[]) this.value, (double[]) zzarx.value) : this.value instanceof boolean[] ? Arrays.equals((boolean[]) this.value, (boolean[]) zzarx.value) : Arrays.deepEquals((Object[]) this.value, (Object[]) zzarx.value);
        } else {
            return false;
        }
    }

    public int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: 0000 */
    public void zza(zzart zzart) throws IOException {
        if (this.value != null) {
            this.btM.zza(this.value, zzart);
            return;
        }
        for (zzasc zza : this.btN) {
            zza.zza(zzart);
        }
    }

    /* access modifiers changed from: 0000 */
    public void zza(zzasc zzasc) {
        this.btN.add(zzasc);
    }

    /* access modifiers changed from: 0000 */
    public <T> T zzb(zzarv<?, T> zzarv) {
        if (this.value == null) {
            this.btM = zzarv;
            this.value = zzarv.zzay(this.btN);
            this.btN = null;
        } else if (!this.btM.equals(zzarv)) {
            throw new IllegalStateException("Tried to getExtension with a different Extension.");
        }
        return this.value;
    }

    /* access modifiers changed from: 0000 */
    public int zzx() {
        int i = 0;
        if (this.value != null) {
            return this.btM.zzct(this.value);
        }
        Iterator it = this.btN.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            i = ((zzasc) it.next()).zzx() + i2;
        }
    }
}
