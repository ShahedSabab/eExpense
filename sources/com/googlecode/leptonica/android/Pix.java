package com.googlecode.leptonica.android;

import android.graphics.Rect;

public class Pix {
    public static final int INDEX_D = 2;
    public static final int INDEX_H = 1;
    public static final int INDEX_W = 0;
    private final long mNativePix;
    private boolean mRecycled;

    private static native long nativeClone(long j);

    private static native long nativeCopy(long j);

    private static native long nativeCreateFromData(byte[] bArr, int i, int i2, int i3);

    private static native long nativeCreatePix(int i, int i2, int i3);

    private static native void nativeDestroy(long j);

    private static native byte[] nativeGetData(long j);

    private static native int nativeGetDepth(long j);

    private static native boolean nativeGetDimensions(long j, int[] iArr);

    private static native int nativeGetHeight(long j);

    private static native int nativeGetPixel(long j, int i, int i2);

    private static native int nativeGetRefCount(long j);

    private static native int nativeGetWidth(long j);

    private static native boolean nativeInvert(long j);

    private static native void nativeSetPixel(long j, int i, int i2, int i3);

    static {
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    public Pix(long nativePix) {
        this.mNativePix = nativePix;
        this.mRecycled = false;
    }

    public Pix(int width, int height, int depth) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Pix width and height must be > 0");
        } else if (depth == 1 || depth == 2 || depth == 4 || depth == 8 || depth == 16 || depth == 24 || depth == 32) {
            this.mNativePix = nativeCreatePix(width, height, depth);
            this.mRecycled = false;
        } else {
            throw new IllegalArgumentException("Depth must be one of 1, 2, 4, 8, 16, or 32");
        }
    }

    public long getNativePix() {
        if (!this.mRecycled) {
            return this.mNativePix;
        }
        throw new IllegalStateException();
    }

    public byte[] getData() {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        byte[] buffer = nativeGetData(this.mNativePix);
        if (buffer != null) {
            return buffer;
        }
        throw new RuntimeException("native getData failed");
    }

    public int[] getDimensions() {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        int[] dimensions = new int[3];
        if (getDimensions(dimensions)) {
            return dimensions;
        }
        return null;
    }

    public boolean getDimensions(int[] dimensions) {
        if (!this.mRecycled) {
            return nativeGetDimensions(this.mNativePix, dimensions);
        }
        throw new IllegalStateException();
    }

    public Pix clone() {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        long nativePix = nativeClone(this.mNativePix);
        if (nativePix != 0) {
            return new Pix(nativePix);
        }
        throw new OutOfMemoryError();
    }

    public Pix copy() {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        long nativePix = nativeCopy(this.mNativePix);
        if (nativePix != 0) {
            return new Pix(nativePix);
        }
        throw new OutOfMemoryError();
    }

    public boolean invert() {
        if (!this.mRecycled) {
            return nativeInvert(this.mNativePix);
        }
        throw new IllegalStateException();
    }

    public void recycle() {
        if (!this.mRecycled) {
            nativeDestroy(this.mNativePix);
            this.mRecycled = true;
        }
    }

    public static Pix createFromPix(byte[] pixData, int width, int height, int depth) {
        long nativePix = nativeCreateFromData(pixData, width, height, depth);
        if (nativePix != 0) {
            return new Pix(nativePix);
        }
        throw new OutOfMemoryError();
    }

    public Rect getRect() {
        return new Rect(0, 0, getWidth(), getHeight());
    }

    public int getWidth() {
        if (!this.mRecycled) {
            return nativeGetWidth(this.mNativePix);
        }
        throw new IllegalStateException();
    }

    public int getHeight() {
        if (!this.mRecycled) {
            return nativeGetHeight(this.mNativePix);
        }
        throw new IllegalStateException();
    }

    public int getDepth() {
        if (!this.mRecycled) {
            return nativeGetDepth(this.mNativePix);
        }
        throw new IllegalStateException();
    }

    public int getRefCount() {
        return nativeGetRefCount(this.mNativePix);
    }

    public int getPixel(int x, int y) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        } else if (x < 0 || x >= getWidth()) {
            throw new IllegalArgumentException("Supplied x coordinate exceeds image bounds");
        } else if (y >= 0 && y < getHeight()) {
            return nativeGetPixel(this.mNativePix, x, y);
        } else {
            throw new IllegalArgumentException("Supplied y coordinate exceeds image bounds");
        }
    }

    public void setPixel(int x, int y, int color) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        } else if (x < 0 || x >= getWidth()) {
            throw new IllegalArgumentException("Supplied x coordinate exceeds image bounds");
        } else if (y < 0 || y >= getHeight()) {
            throw new IllegalArgumentException("Supplied y coordinate exceeds image bounds");
        } else {
            nativeSetPixel(this.mNativePix, x, y, color);
        }
    }
}
