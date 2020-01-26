package com.google.android.gms.internal;

import java.io.IOException;

public final class zzasd {
    public static final int[] btR = new int[0];
    public static final long[] btS = new long[0];
    public static final float[] btT = new float[0];
    public static final double[] btU = new double[0];
    public static final boolean[] btV = new boolean[0];
    public static final String[] btW = new String[0];
    public static final byte[][] btX = new byte[0][];
    public static final byte[] btY = new byte[0];

    static int zzahk(int i) {
        return i & 7;
    }

    public static int zzahl(int i) {
        return i >>> 3;
    }

    public static int zzak(int i, int i2) {
        return (i << 3) | i2;
    }

    public static boolean zzb(zzars zzars, int i) throws IOException {
        return zzars.zzagr(i);
    }

    public static final int zzc(zzars zzars, int i) throws IOException {
        int i2 = 1;
        int position = zzars.getPosition();
        zzars.zzagr(i);
        while (zzars.mo10738bU() == i) {
            zzars.zzagr(i);
            i2++;
        }
        zzars.zzagv(position);
        return i2;
    }
}
