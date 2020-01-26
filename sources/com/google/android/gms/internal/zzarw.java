package com.google.android.gms.internal;

public final class zzarw implements Cloneable {
    private static final zzarx btI = new zzarx();
    private boolean btJ;
    private int[] btK;
    private zzarx[] btL;
    private int mSize;

    zzarw() {
        this(10);
    }

    zzarw(int i) {
        this.btJ = false;
        int idealIntArraySize = idealIntArraySize(i);
        this.btK = new int[idealIntArraySize];
        this.btL = new zzarx[idealIntArraySize];
        this.mSize = 0;
    }

    private int idealByteArraySize(int i) {
        for (int i2 = 4; i2 < 32; i2++) {
            if (i <= (1 << i2) - 12) {
                return (1 << i2) - 12;
            }
        }
        return i;
    }

    private int idealIntArraySize(int i) {
        return idealByteArraySize(i * 4) / 4;
    }

    private boolean zza(int[] iArr, int[] iArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    private boolean zza(zzarx[] zzarxArr, zzarx[] zzarxArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (!zzarxArr[i2].equals(zzarxArr2[i2])) {
                return false;
            }
        }
        return true;
    }

    private int zzahj(int i) {
        int i2 = 0;
        int i3 = this.mSize - 1;
        while (i2 <= i3) {
            int i4 = (i2 + i3) >>> 1;
            int i5 = this.btK[i4];
            if (i5 < i) {
                i2 = i4 + 1;
            } else if (i5 <= i) {
                return i4;
            } else {
                i3 = i4 - 1;
            }
        }
        return i2 ^ -1;
    }

    /* renamed from: cp */
    public final zzarw clone() {
        int size = size();
        zzarw zzarw = new zzarw(size);
        System.arraycopy(this.btK, 0, zzarw.btK, 0, size);
        for (int i = 0; i < size; i++) {
            if (this.btL[i] != null) {
                zzarw.btL[i] = (zzarx) this.btL[i].clone();
            }
        }
        zzarw.mSize = size;
        return zzarw;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzarw)) {
            return false;
        }
        zzarw zzarw = (zzarw) obj;
        if (size() != zzarw.size()) {
            return false;
        }
        return zza(this.btK, zzarw.btK, this.mSize) && zza(this.btL, zzarw.btL, this.mSize);
    }

    public int hashCode() {
        int i = 17;
        for (int i2 = 0; i2 < this.mSize; i2++) {
            i = (((i * 31) + this.btK[i2]) * 31) + this.btL[i2].hashCode();
        }
        return i;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /* access modifiers changed from: 0000 */
    public int size() {
        return this.mSize;
    }

    /* access modifiers changed from: 0000 */
    public void zza(int i, zzarx zzarx) {
        int zzahj = zzahj(i);
        if (zzahj >= 0) {
            this.btL[zzahj] = zzarx;
            return;
        }
        int i2 = zzahj ^ -1;
        if (i2 >= this.mSize || this.btL[i2] != btI) {
            if (this.mSize >= this.btK.length) {
                int idealIntArraySize = idealIntArraySize(this.mSize + 1);
                int[] iArr = new int[idealIntArraySize];
                zzarx[] zzarxArr = new zzarx[idealIntArraySize];
                System.arraycopy(this.btK, 0, iArr, 0, this.btK.length);
                System.arraycopy(this.btL, 0, zzarxArr, 0, this.btL.length);
                this.btK = iArr;
                this.btL = zzarxArr;
            }
            if (this.mSize - i2 != 0) {
                System.arraycopy(this.btK, i2, this.btK, i2 + 1, this.mSize - i2);
                System.arraycopy(this.btL, i2, this.btL, i2 + 1, this.mSize - i2);
            }
            this.btK[i2] = i;
            this.btL[i2] = zzarx;
            this.mSize++;
            return;
        }
        this.btK[i2] = i;
        this.btL[i2] = zzarx;
    }

    /* access modifiers changed from: 0000 */
    public zzarx zzahh(int i) {
        int zzahj = zzahj(i);
        if (zzahj < 0 || this.btL[zzahj] == btI) {
            return null;
        }
        return this.btL[zzahj];
    }

    /* access modifiers changed from: 0000 */
    public zzarx zzahi(int i) {
        return this.btL[i];
    }
}
