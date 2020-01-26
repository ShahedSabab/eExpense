package com.googlecode.leptonica.android;

public class AdaptiveMap {
    public static final int DEFAULT_MIN_COUNT = 40;
    public static final int DEFAULT_TILE_HEIGHT = 15;
    public static final int DEFAULT_TILE_WIDTH = 10;
    public static final int DEFAULT_X_SMOOTH_SIZE = 2;
    public static final int DEFAULT_Y_SMOOTH_SIZE = 1;
    private static final int NORM_BG_VALUE = 200;
    private static final int NORM_REDUCTION = 16;
    private static final int NORM_SIZE = 3;

    private static native long nativeBackgroundNormMorph(long j, int i, int i2, int i3);

    private static native long nativePixContrastNorm(long j, int i, int i2, int i3, int i4, int i5);

    static {
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    public static Pix backgroundNormMorph(Pix pixs) {
        return backgroundNormMorph(pixs, 16, 3, 200);
    }

    public static Pix backgroundNormMorph(Pix pixs, int normReduction, int normSize, int normBgValue) {
        if (pixs == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        long nativePix = nativeBackgroundNormMorph(pixs.getNativePix(), normReduction, normSize, normBgValue);
        if (nativePix != 0) {
            return new Pix(nativePix);
        }
        throw new RuntimeException("Failed to normalize image background");
    }

    public static Pix pixContrastNorm(Pix pixs) {
        return pixContrastNorm(pixs, 10, 15, 40, 2, 1);
    }

    public static Pix pixContrastNorm(Pix pixs, int sizeX, int sizeY, int minDiff, int smoothX, int smoothY) {
        if (pixs == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        long nativePix = nativePixContrastNorm(pixs.getNativePix(), sizeX, sizeY, minDiff, smoothX, smoothY);
        if (nativePix != 0) {
            return new Pix(nativePix);
        }
        throw new RuntimeException("Failed to normalize image contrast");
    }
}
