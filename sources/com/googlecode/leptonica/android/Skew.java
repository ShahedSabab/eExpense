package com.googlecode.leptonica.android;

public class Skew {
    public static final float SEARCH_MIN_DELTA = 0.01f;
    public static final int SEARCH_REDUCTION = 4;
    public static final float SWEEP_DELTA = 5.0f;
    public static final float SWEEP_RANGE = 30.0f;
    public static final int SWEEP_REDUCTION = 8;

    private static native float nativeFindSkew(long j, float f, float f2, int i, int i2, float f3);

    static {
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    public static float findSkew(Pix pixs) {
        return findSkew(pixs, 30.0f, 5.0f, 8, 4, 0.01f);
    }

    public static float findSkew(Pix pixs, float sweepRange, float sweepDelta, int sweepReduction, int searchReduction, float searchMinDelta) {
        if (pixs != null) {
            return nativeFindSkew(pixs.getNativePix(), sweepRange, sweepDelta, sweepReduction, searchReduction, searchMinDelta);
        }
        throw new IllegalArgumentException("Source pix must be non-null");
    }
}
