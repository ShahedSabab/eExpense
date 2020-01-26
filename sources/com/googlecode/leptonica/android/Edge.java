package com.googlecode.leptonica.android;

public class Edge {
    public static final int L_ALL_EDGES = 2;
    public static final int L_HORIZONTAL_EDGES = 0;
    public static final int L_VERTICAL_EDGES = 1;

    private static native long nativePixSobelEdgeFilter(long j, int i);

    static {
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    public static Pix pixSobelEdgeFilter(Pix pixs, int orientFlag) {
        if (pixs == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        } else if (pixs.getDepth() != 8) {
            throw new IllegalArgumentException("Source pix depth must be 8bpp");
        } else if (orientFlag < 0 || orientFlag > 2) {
            throw new IllegalArgumentException("Invalid orientation flag");
        } else {
            long nativePix = nativePixSobelEdgeFilter(pixs.getNativePix(), orientFlag);
            if (nativePix != 0) {
                return new Pix(nativePix);
            }
            throw new RuntimeException("Failed to perform Sobel edge filter on image");
        }
    }
}
