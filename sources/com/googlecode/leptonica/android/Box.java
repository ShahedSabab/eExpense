package com.googlecode.leptonica.android;

import android.graphics.Rect;
import android.util.Log;

public class Box {
    public static final int INDEX_H = 3;
    public static final int INDEX_W = 2;
    public static final int INDEX_X = 0;
    public static final int INDEX_Y = 1;
    private static final String TAG = Box.class.getSimpleName();
    private final long mNativeBox;
    private boolean mRecycled = false;

    private static native long nativeCreate(int i, int i2, int i3, int i4);

    private static native void nativeDestroy(long j);

    private static native boolean nativeGetGeometry(long j, int[] iArr);

    private static native int nativeGetHeight(long j);

    private static native int nativeGetWidth(long j);

    private static native int nativeGetX(long j);

    private static native int nativeGetY(long j);

    static {
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    Box(long nativeBox) {
        this.mNativeBox = nativeBox;
        this.mRecycled = false;
    }

    public Box(int x, int y, int w, int h) {
        if (x < 0 || y < 0 || w < 0 || h < 0) {
            throw new IllegalArgumentException("All box dimensions must be non-negative");
        }
        long nativeBox = nativeCreate(x, y, w, h);
        if (nativeBox == 0) {
            throw new OutOfMemoryError();
        }
        this.mNativeBox = nativeBox;
        this.mRecycled = false;
    }

    public long getNativeBox() {
        if (!this.mRecycled) {
            return this.mNativeBox;
        }
        throw new IllegalStateException();
    }

    public int getX() {
        if (!this.mRecycled) {
            return nativeGetX(this.mNativeBox);
        }
        throw new IllegalStateException();
    }

    public int getY() {
        if (!this.mRecycled) {
            return nativeGetY(this.mNativeBox);
        }
        throw new IllegalStateException();
    }

    public int getWidth() {
        if (!this.mRecycled) {
            return nativeGetWidth(this.mNativeBox);
        }
        throw new IllegalStateException();
    }

    public int getHeight() {
        if (!this.mRecycled) {
            return nativeGetHeight(this.mNativeBox);
        }
        throw new IllegalStateException();
    }

    public Rect getRect() {
        int[] geometry = getGeometry();
        int left = geometry[0];
        int top = geometry[1];
        return new Rect(left, top, left + geometry[2], top + geometry[3]);
    }

    public int[] getGeometry() {
        int[] geometry = new int[4];
        if (getGeometry(geometry)) {
            return geometry;
        }
        return null;
    }

    public boolean getGeometry(int[] geometry) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        } else if (geometry.length >= 4) {
            return nativeGetGeometry(this.mNativeBox, geometry);
        } else {
            throw new IllegalArgumentException("Geometry array must be at least 4 elements long");
        }
    }

    public void recycle() {
        if (!this.mRecycled) {
            nativeDestroy(this.mNativeBox);
            this.mRecycled = true;
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            if (!this.mRecycled) {
                Log.w(TAG, "Box was not terminated using recycle()");
                recycle();
            }
        } finally {
            super.finalize();
        }
    }
}
