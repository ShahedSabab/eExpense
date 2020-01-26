package com.googlecode.leptonica.android;

public class GrayQuant {
    private static native long nativePixThresholdToBinary(long j, int i);

    static {
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    public static Pix pixThresholdToBinary(Pix pixs, int thresh) {
        if (pixs == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        int depth = pixs.getDepth();
        if (depth != 4 && depth != 8) {
            throw new IllegalArgumentException("Source pix depth must be 4 or 8 bpp");
        } else if (depth == 4 && thresh > 16) {
            throw new IllegalArgumentException("4 bpp thresh not in {0-16}");
        } else if (depth != 8 || thresh <= 256) {
            long nativePix = nativePixThresholdToBinary(pixs.getNativePix(), thresh);
            if (nativePix != 0) {
                return new Pix(nativePix);
            }
            throw new RuntimeException("Failed to perform binarization");
        } else {
            throw new IllegalArgumentException("8 bpp thresh not in {0-256}");
        }
    }
}
