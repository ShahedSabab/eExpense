package com.googlecode.leptonica.android;

public class MorphApp {
    public static final int DEFAULT_HEIGHT = 7;
    public static final int DEFAULT_WIDTH = 7;
    public static final int L_TOPHAT_BLACK = 1;
    public static final int L_TOPHAT_WHITE = 0;

    private static native long nativePixFastTophat(long j, int i, int i2, int i3);

    private static native long nativePixTophat(long j, int i, int i2, int i3);

    static {
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    public static Pix pixTophat(Pix pixs, int hsize, int vsize, int type) {
        if (pixs == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        } else if (pixs.getDepth() != 8) {
            throw new IllegalArgumentException("Source pix depth must be 8bpp");
        } else if (hsize < 1 || vsize < 1) {
            throw new IllegalArgumentException("hsize or vsize < 1");
        } else if (type < 0 || type > 1) {
            throw new IllegalArgumentException("Type must be L_TOPHAT_BLACK or L_TOPHAT_WHITE");
        } else {
            long nativePix = nativePixTophat(pixs.getNativePix(), hsize, vsize, type);
            if (nativePix != 0) {
                return new Pix(nativePix);
            }
            throw new RuntimeException("Failed to perform Tophat on image");
        }
    }

    public static Pix pixFastTophatBlack(Pix pixs) {
        return pixFastTophat(pixs, 7, 7, 1);
    }

    public static Pix pixFastTophatWhite(Pix pixs) {
        return pixFastTophat(pixs, 7, 7, 0);
    }

    public static Pix pixFastTophat(Pix pixs, int xsize, int ysize, int type) {
        if (pixs == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        } else if (pixs.getDepth() != 8) {
            throw new IllegalArgumentException("Source pix depth must be 8bpp");
        } else if (xsize < 1 || ysize < 1) {
            throw new IllegalArgumentException("size < 1");
        } else if (type < 0 || type > 1) {
            throw new IllegalArgumentException("Type must be L_TOPHAT_BLACK or L_TOPHAT_WHITE");
        } else {
            long nativePix = nativePixFastTophat(pixs.getNativePix(), xsize, ysize, type);
            if (nativePix != 0) {
                return new Pix(nativePix);
            }
            throw new RuntimeException("Failed to perform pixFastTophat on image");
        }
    }
}
