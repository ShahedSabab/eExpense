package com.googlecode.leptonica.android;

public class Binarize {
    public static final float OTSU_SCORE_FRACTION = 0.1f;
    public static final int OTSU_SIZE_X = 32;
    public static final int OTSU_SIZE_Y = 32;
    public static final int OTSU_SMOOTH_X = 2;
    public static final int OTSU_SMOOTH_Y = 2;
    public static final int SAUVOLA_DEFAULT_NUM_TILES_X = 1;
    public static final int SAUVOLA_DEFAULT_NUM_TILES_Y = 1;
    public static final float SAUVOLA_DEFAULT_REDUCTION_FACTOR = 0.35f;
    public static final int SAUVOLA_DEFAULT_WINDOW_HALFWIDTH = 8;

    private static native long nativeOtsuAdaptiveThreshold(long j, int i, int i2, int i3, int i4, float f);

    private static native long nativeSauvolaBinarizeTiled(long j, int i, float f, int i2, int i3);

    static {
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    public static Pix otsuAdaptiveThreshold(Pix pixs) {
        return otsuAdaptiveThreshold(pixs, 32, 32, 2, 2, 0.1f);
    }

    public static Pix otsuAdaptiveThreshold(Pix pixs, int sizeX, int sizeY, int smoothX, int smoothY, float scoreFraction) {
        if (pixs == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        } else if (pixs.getDepth() != 8) {
            throw new IllegalArgumentException("Source pix depth must be 8bpp");
        } else {
            long nativePix = nativeOtsuAdaptiveThreshold(pixs.getNativePix(), sizeX, sizeY, smoothX, smoothY, scoreFraction);
            if (nativePix != 0) {
                return new Pix(nativePix);
            }
            throw new RuntimeException("Failed to perform Otsu adaptive threshold on image");
        }
    }

    public static Pix sauvolaBinarizeTiled(Pix pixs) {
        return sauvolaBinarizeTiled(pixs, 8, 0.35f, 1, 1);
    }

    public static Pix sauvolaBinarizeTiled(Pix pixs, int whsize, float factor, int nx, int ny) {
        if (pixs == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        } else if (pixs.getDepth() != 8) {
            throw new IllegalArgumentException("Source pix depth must be 8bpp");
        } else {
            long nativePix = nativeSauvolaBinarizeTiled(pixs.getNativePix(), whsize, factor, nx, ny);
            if (nativePix != 0) {
                return new Pix(nativePix);
            }
            throw new RuntimeException("Failed to perform Sauvola binarization on image");
        }
    }
}
