package com.googlecode.leptonica.android;

public class Enhance {
    public static final float DEFAULT_UNSHARP_FRACTION = 0.3f;
    public static final int DEFAULT_UNSHARP_HALFWIDTH = 1;

    private static native long nativeUnsharpMasking(long j, int i, float f);

    static {
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    public static Pix unsharpMasking(Pix pixs) {
        return unsharpMasking(pixs, 1, 0.3f);
    }

    public static Pix unsharpMasking(Pix pixs, int halfwidth, float fraction) {
        if (pixs == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        long nativePix = nativeUnsharpMasking(pixs.getNativePix(), halfwidth, fraction);
        if (nativePix != 0) {
            return new Pix(nativePix);
        }
        throw new OutOfMemoryError();
    }
}
