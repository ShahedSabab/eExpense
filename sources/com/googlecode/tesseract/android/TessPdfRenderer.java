package com.googlecode.tesseract.android;

public class TessPdfRenderer {
    private final long mNativePdfRenderer;
    private boolean mRecycled = false;

    private static native long nativeCreate(TessBaseAPI tessBaseAPI, String str);

    private static native void nativeRecycle(long j);

    static {
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
        System.loadLibrary("tess");
    }

    public TessPdfRenderer(TessBaseAPI baseApi, String outputPath) {
        this.mNativePdfRenderer = nativeCreate(baseApi, outputPath);
    }

    public long getNativePdfRenderer() {
        if (!this.mRecycled) {
            return this.mNativePdfRenderer;
        }
        throw new IllegalStateException();
    }

    public void recycle() {
        nativeRecycle(this.mNativePdfRenderer);
        this.mRecycled = true;
    }
}
