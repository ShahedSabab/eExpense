package com.googlecode.leptonica.android;

public class Clip {
    private static native int nativeClipRectangle(long j, long j2);

    static {
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    public static Pix clipRectangle(Pix source, Box box) {
        int result = nativeClipRectangle(source.getNativePix(), box.getNativeBox());
        if (result != 0) {
            return new Pix((long) result);
        }
        return null;
    }
}
