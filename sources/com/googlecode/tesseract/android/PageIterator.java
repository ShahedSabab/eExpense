package com.googlecode.tesseract.android;

import android.graphics.Rect;

public class PageIterator {
    private final long mNativePageIterator;

    private static native void nativeBegin(long j);

    private static native int[] nativeBoundingBox(long j, int i);

    private static native boolean nativeNext(long j, int i);

    static {
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
        System.loadLibrary("tess");
    }

    PageIterator(long nativePageIterator) {
        this.mNativePageIterator = nativePageIterator;
    }

    public void begin() {
        nativeBegin(this.mNativePageIterator);
    }

    public boolean next(int level) {
        return nativeNext(this.mNativePageIterator, level);
    }

    public int[] getBoundingBox(int level) {
        return nativeBoundingBox(this.mNativePageIterator, level);
    }

    public Rect getBoundingRect(int level) {
        int[] box = getBoundingBox(level);
        return new Rect(box[0], box[1], box[2], box[3]);
    }
}
