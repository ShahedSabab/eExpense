package com.googlecode.leptonica.android;

public class Rotate {
    public static final boolean ROTATE_QUALITY = true;

    private static native long nativeRotate(long j, float f, boolean z, boolean z2);

    private static native int nativeRotateOrth(long j, int i);

    static {
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    public static Pix rotate(Pix pixs, float degrees) {
        return rotate(pixs, degrees, false);
    }

    public static Pix rotate(Pix pixs, float degrees, boolean quality) {
        return rotate(pixs, degrees, quality, true);
    }

    public static Pix rotate(Pix pixs, float degrees, boolean quality, boolean resize) {
        if (pixs == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        long nativePix = nativeRotate(pixs.getNativePix(), degrees, quality, resize);
        if (nativePix == 0) {
            return null;
        }
        return new Pix(nativePix);
    }

    public static Pix rotateOrth(Pix pixs, int quads) {
        if (pixs == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        } else if (quads < 0 || quads > 3) {
            throw new IllegalArgumentException("quads not in {0,1,2,3}");
        } else {
            int nativePix = nativeRotateOrth(pixs.getNativePix(), quads);
            if (nativePix == 0) {
                return null;
            }
            return new Pix((long) nativePix);
        }
    }
}
