package com.google.android.gms.internal;

import java.io.IOException;

public final class zzars {
    private int btA;
    private int btB = Integer.MAX_VALUE;
    private int btC;
    private int btD = 64;
    private int btE = 67108864;
    private int btw;
    private int btx;
    private int bty;
    private int btz;
    private final byte[] buffer;

    private zzars(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.btw = i;
        this.btx = i + i2;
        this.btz = i;
    }

    /* renamed from: ch */
    private void m108ch() {
        this.btx += this.bty;
        int i = this.btx;
        if (i > this.btB) {
            this.bty = i - this.btB;
            this.btx -= this.bty;
            return;
        }
        this.bty = 0;
    }

    public static int zzags(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    public static zzars zzb(byte[] bArr, int i, int i2) {
        return new zzars(bArr, i, i2);
    }

    public static zzars zzbd(byte[] bArr) {
        return zzb(bArr, 0, bArr.length);
    }

    public static long zzct(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    /* renamed from: bU */
    public int mo10738bU() throws IOException {
        if (mo10752cj()) {
            this.btA = 0;
            return 0;
        }
        this.btA = mo10747cd();
        if (this.btA != 0) {
            return this.btA;
        }
        throw zzarz.m134cu();
    }

    /* renamed from: bV */
    public void mo10739bV() throws IOException {
        int bU;
        do {
            bU = mo10738bU();
            if (bU == 0) {
                return;
            }
        } while (zzagr(bU));
    }

    /* renamed from: bW */
    public long mo10740bW() throws IOException {
        return mo10748ce();
    }

    /* renamed from: bX */
    public long mo10741bX() throws IOException {
        return mo10748ce();
    }

    /* renamed from: bY */
    public int mo10742bY() throws IOException {
        return mo10747cd();
    }

    /* renamed from: bZ */
    public long mo10743bZ() throws IOException {
        return mo10750cg();
    }

    /* renamed from: ca */
    public boolean mo10744ca() throws IOException {
        return mo10747cd() != 0;
    }

    /* renamed from: cb */
    public int mo10745cb() throws IOException {
        return zzags(mo10747cd());
    }

    /* renamed from: cc */
    public long mo10746cc() throws IOException {
        return zzct(mo10748ce());
    }

    /* renamed from: cd */
    public int mo10747cd() throws IOException {
        byte ck = mo10753ck();
        if (ck >= 0) {
            return ck;
        }
        byte b = ck & Byte.MAX_VALUE;
        byte ck2 = mo10753ck();
        if (ck2 >= 0) {
            return b | (ck2 << 7);
        }
        byte b2 = b | ((ck2 & Byte.MAX_VALUE) << 7);
        byte ck3 = mo10753ck();
        if (ck3 >= 0) {
            return b2 | (ck3 << 14);
        }
        byte b3 = b2 | ((ck3 & Byte.MAX_VALUE) << 14);
        byte ck4 = mo10753ck();
        if (ck4 >= 0) {
            return b3 | (ck4 << 21);
        }
        byte b4 = b3 | ((ck4 & Byte.MAX_VALUE) << 21);
        byte ck5 = mo10753ck();
        byte b5 = b4 | (ck5 << 28);
        if (ck5 >= 0) {
            return b5;
        }
        for (int i = 0; i < 5; i++) {
            if (mo10753ck() >= 0) {
                return b5;
            }
        }
        throw zzarz.m133ct();
    }

    /* renamed from: ce */
    public long mo10748ce() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte ck = mo10753ck();
            j |= ((long) (ck & Byte.MAX_VALUE)) << i;
            if ((ck & 128) == 0) {
                return j;
            }
        }
        throw zzarz.m133ct();
    }

    /* renamed from: cf */
    public int mo10749cf() throws IOException {
        return (mo10753ck() & 255) | ((mo10753ck() & 255) << 8) | ((mo10753ck() & 255) << 16) | ((mo10753ck() & 255) << 24);
    }

    /* renamed from: cg */
    public long mo10750cg() throws IOException {
        byte ck = mo10753ck();
        byte ck2 = mo10753ck();
        return ((((long) ck2) & 255) << 8) | (((long) ck) & 255) | ((((long) mo10753ck()) & 255) << 16) | ((((long) mo10753ck()) & 255) << 24) | ((((long) mo10753ck()) & 255) << 32) | ((((long) mo10753ck()) & 255) << 40) | ((((long) mo10753ck()) & 255) << 48) | ((((long) mo10753ck()) & 255) << 56);
    }

    /* renamed from: ci */
    public int mo10751ci() {
        if (this.btB == Integer.MAX_VALUE) {
            return -1;
        }
        return this.btB - this.btz;
    }

    /* renamed from: cj */
    public boolean mo10752cj() {
        return this.btz == this.btx;
    }

    /* renamed from: ck */
    public byte mo10753ck() throws IOException {
        if (this.btz == this.btx) {
            throw zzarz.m131cr();
        }
        byte[] bArr = this.buffer;
        int i = this.btz;
        this.btz = i + 1;
        return bArr[i];
    }

    public int getPosition() {
        return this.btz - this.btw;
    }

    public byte[] readBytes() throws IOException {
        int cd = mo10747cd();
        if (cd < 0) {
            throw zzarz.m132cs();
        } else if (cd == 0) {
            return zzasd.btY;
        } else {
            if (cd > this.btx - this.btz) {
                throw zzarz.m131cr();
            }
            byte[] bArr = new byte[cd];
            System.arraycopy(this.buffer, this.btz, bArr, 0, cd);
            this.btz = cd + this.btz;
            return bArr;
        }
    }

    public double readDouble() throws IOException {
        return Double.longBitsToDouble(mo10750cg());
    }

    public float readFloat() throws IOException {
        return Float.intBitsToFloat(mo10749cf());
    }

    public String readString() throws IOException {
        int cd = mo10747cd();
        if (cd < 0) {
            throw zzarz.m132cs();
        } else if (cd > this.btx - this.btz) {
            throw zzarz.m131cr();
        } else {
            String str = new String(this.buffer, this.btz, cd, zzary.UTF_8);
            this.btz = cd + this.btz;
            return str;
        }
    }

    public void zza(zzasa zzasa) throws IOException {
        int cd = mo10747cd();
        if (this.btC >= this.btD) {
            throw zzarz.m137cx();
        }
        int zzagt = zzagt(cd);
        this.btC++;
        zzasa.zzb(this);
        zzagq(0);
        this.btC--;
        zzagu(zzagt);
    }

    public void zza(zzasa zzasa, int i) throws IOException {
        if (this.btC >= this.btD) {
            throw zzarz.m137cx();
        }
        this.btC++;
        zzasa.zzb(this);
        zzagq(zzasd.zzak(i, 4));
        this.btC--;
    }

    public byte[] zzae(int i, int i2) {
        if (i2 == 0) {
            return zzasd.btY;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.buffer, this.btw + i, bArr, 0, i2);
        return bArr;
    }

    public void zzagq(int i) throws zzarz {
        if (this.btA != i) {
            throw zzarz.m135cv();
        }
    }

    public boolean zzagr(int i) throws IOException {
        switch (zzasd.zzahk(i)) {
            case 0:
                mo10742bY();
                return true;
            case 1:
                mo10750cg();
                return true;
            case 2:
                zzagw(mo10747cd());
                return true;
            case 3:
                mo10739bV();
                zzagq(zzasd.zzak(zzasd.zzahl(i), 4));
                return true;
            case 4:
                return false;
            case 5:
                mo10749cf();
                return true;
            default:
                throw zzarz.m136cw();
        }
    }

    public int zzagt(int i) throws zzarz {
        if (i < 0) {
            throw zzarz.m132cs();
        }
        int i2 = this.btz + i;
        int i3 = this.btB;
        if (i2 > i3) {
            throw zzarz.m131cr();
        }
        this.btB = i2;
        m108ch();
        return i3;
    }

    public void zzagu(int i) {
        this.btB = i;
        m108ch();
    }

    public void zzagv(int i) {
        if (i > this.btz - this.btw) {
            throw new IllegalArgumentException("Position " + i + " is beyond current " + (this.btz - this.btw));
        } else if (i < 0) {
            throw new IllegalArgumentException("Bad position " + i);
        } else {
            this.btz = this.btw + i;
        }
    }

    public void zzagw(int i) throws IOException {
        if (i < 0) {
            throw zzarz.m132cs();
        } else if (this.btz + i > this.btB) {
            zzagw(this.btB - this.btz);
            throw zzarz.m131cr();
        } else if (i <= this.btx - this.btz) {
            this.btz += i;
        } else {
            throw zzarz.m131cr();
        }
    }
}
