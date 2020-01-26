package com.googlecode.leptonica.android;

import android.graphics.Rect;
import android.util.Log;

public class Boxa {
    private static final String TAG = Boxa.class.getSimpleName();
    private final long mNativeBoxa;
    private boolean mRecycled = false;

    private static native void nativeDestroy(long j);

    private static native int nativeGetCount(long j);

    private static native boolean nativeGetGeometry(long j, int i, int[] iArr);

    static {
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    public Boxa(long nativeBoxa) {
        this.mNativeBoxa = nativeBoxa;
        this.mRecycled = false;
    }

    public long getNativeBoxa() {
        if (!this.mRecycled) {
            return this.mNativeBoxa;
        }
        throw new IllegalStateException();
    }

    public int getCount() {
        if (!this.mRecycled) {
            return nativeGetCount(this.mNativeBoxa);
        }
        throw new IllegalStateException();
    }

    public Rect getRect(int index) {
        int[] geometry = getGeometry(index);
        int left = geometry[0];
        int top = geometry[1];
        return new Rect(left, top, left + geometry[2], top + geometry[3]);
    }

    public int[] getGeometry(int index) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        int[] geometry = new int[4];
        if (getGeometry(index, geometry)) {
            return geometry;
        }
        return null;
    }

    public boolean getGeometry(int index, int[] geometry) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        } else if (geometry.length >= 4) {
            return nativeGetGeometry(this.mNativeBoxa, index, geometry);
        } else {
            throw new IllegalArgumentException("Geometry array must be at least 4 elements long");
        }
    }

    public synchronized void recycle() {
        if (!this.mRecycled) {
            nativeDestroy(this.mNativeBoxa);
            this.mRecycled = true;
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            if (!this.mRecycled) {
                Log.w(TAG, "Boxa was not terminated using recycle()");
                recycle();
            }
        } finally {
            super.finalize();
        }
    }
}
