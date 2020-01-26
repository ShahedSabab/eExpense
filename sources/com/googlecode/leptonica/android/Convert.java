package com.googlecode.leptonica.android;

public class Convert {
    private static native long nativeConvertTo8(long j);

    static {
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    public static Pix convertTo8(Pix pixs) {
        if (pixs == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        long nativePix = nativeConvertTo8(pixs.getNativePix());
        if (nativePix != 0) {
            return new Pix(nativePix);
        }
        throw new RuntimeException("Failed to natively convert pix");
    }
}
