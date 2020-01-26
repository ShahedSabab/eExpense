package com.googlecode.leptonica.android;

public class Scale {

    public enum ScaleType {
        FILL,
        FIT,
        FIT_SHRINK
    }

    private static native long nativeScale(long j, float f, float f2);

    private static native long nativeScaleGeneral(long j, float f, float f2, float f3, int i);

    static {
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    public static Pix scaleToSize(Pix pixs, int width, int height, ScaleType type) {
        if (pixs == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        float scaleX = ((float) width) / ((float) pixs.getWidth());
        float scaleY = ((float) height) / ((float) pixs.getHeight());
        switch (type) {
            case FIT:
                scaleX = Math.min(scaleX, scaleY);
                scaleY = scaleX;
                break;
            case FIT_SHRINK:
                scaleX = Math.min(1.0f, Math.min(scaleX, scaleY));
                scaleY = scaleX;
                break;
        }
        return scale(pixs, scaleX, scaleY);
    }

    public static Pix scale(Pix pixs, float scale) {
        return scale(pixs, scale, scale);
    }

    public static Pix scaleWithoutSharpening(Pix pixs, float scale) {
        if (pixs == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        } else if (scale > 0.0f) {
            return new Pix(nativeScaleGeneral(pixs.getNativePix(), scale, scale, 0.0f, 0));
        } else {
            throw new IllegalArgumentException("Scaling factor must be positive");
        }
    }

    public static Pix scale(Pix pixs, float scaleX, float scaleY) {
        if (pixs == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        } else if (scaleX <= 0.0f) {
            throw new IllegalArgumentException("X scaling factor must be positive");
        } else if (scaleY <= 0.0f) {
            throw new IllegalArgumentException("Y scaling factor must be positive");
        } else {
            long nativePix = nativeScale(pixs.getNativePix(), scaleX, scaleY);
            if (nativePix != 0) {
                return new Pix(nativePix);
            }
            throw new RuntimeException("Failed to natively scale pix");
        }
    }
}
