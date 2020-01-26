package com.googlecode.leptonica.android;

import android.graphics.Rect;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class Pixa implements Iterable<Pix> {
    private static final String TAG = Pixa.class.getSimpleName();
    final int mHeight;
    private final long mNativePixa;
    private boolean mRecycled = false;
    final int mWidth;

    private class PixIterator implements Iterator<Pix> {
        private int mIndex;

        private PixIterator() {
            this.mIndex = 0;
        }

        public boolean hasNext() {
            int size = Pixa.this.size();
            return size > 0 && this.mIndex < size;
        }

        public Pix next() {
            Pixa pixa = Pixa.this;
            int i = this.mIndex;
            this.mIndex = i + 1;
            return pixa.getPix(i);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private static native void nativeAdd(long j, long j2, long j3, int i);

    private static native void nativeAddBox(long j, long j2, int i);

    private static native void nativeAddPix(long j, long j2, int i);

    private static native int nativeCopy(long j);

    private static native int nativeCreate(int i);

    private static native void nativeDestroy(long j);

    private static native long nativeGetBox(long j, int i);

    private static native boolean nativeGetBoxGeometry(long j, int i, int[] iArr);

    private static native int nativeGetCount(long j);

    private static native int nativeGetPix(long j, int i);

    private static native boolean nativeJoin(long j, long j2);

    private static native void nativeMergeAndReplacePix(long j, int i, int i2);

    private static native void nativeReplacePix(long j, int i, long j2, long j3);

    private static native int nativeSort(long j, int i, int i2);

    private static native boolean nativeWriteToFileRandomCmap(long j, String str, int i, int i2);

    static {
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    public static Pixa createPixa(int size) {
        return createPixa(size, 0, 0);
    }

    public static Pixa createPixa(int size, int width, int height) {
        long nativePixa = (long) nativeCreate(size);
        if (nativePixa != 0) {
            return new Pixa(nativePixa, width, height);
        }
        throw new OutOfMemoryError();
    }

    public Pixa(long nativePixa, int width, int height) {
        this.mNativePixa = nativePixa;
        this.mWidth = width;
        this.mHeight = height;
    }

    public long getNativePixa() {
        if (!this.mRecycled) {
            return this.mNativePixa;
        }
        throw new IllegalStateException();
    }

    public Pixa copy() {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        int nativePixa = nativeCopy(this.mNativePixa);
        if (nativePixa != 0) {
            return new Pixa((long) nativePixa, this.mWidth, this.mHeight);
        }
        throw new OutOfMemoryError();
    }

    public Pixa sort(int field, int order) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        int nativePixa = nativeSort(this.mNativePixa, field, order);
        if (nativePixa != 0) {
            return new Pixa((long) nativePixa, this.mWidth, this.mHeight);
        }
        throw new OutOfMemoryError();
    }

    public int size() {
        if (!this.mRecycled) {
            return nativeGetCount(this.mNativePixa);
        }
        throw new IllegalStateException();
    }

    public synchronized void recycle() {
        if (!this.mRecycled) {
            nativeDestroy(this.mNativePixa);
            this.mRecycled = true;
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            if (!this.mRecycled) {
                Log.w(TAG, "Pixa was not terminated using recycle()");
                recycle();
            }
        } finally {
            super.finalize();
        }
    }

    public boolean join(Pixa otherPixa) {
        if (!this.mRecycled) {
            return nativeJoin(this.mNativePixa, otherPixa.mNativePixa);
        }
        throw new IllegalStateException();
    }

    public void addPix(Pix pix, int mode) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        nativeAddPix(this.mNativePixa, pix.getNativePix(), mode);
    }

    public void addBox(Box box, int mode) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        nativeAddBox(this.mNativePixa, box.getNativeBox(), mode);
    }

    public void add(Pix pix, Box box, int mode) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        nativeAdd(this.mNativePixa, pix.getNativePix(), box.getNativeBox(), mode);
    }

    public Box getBox(int index) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        long nativeBox = nativeGetBox(this.mNativePixa, index);
        if (nativeBox == 0) {
            return null;
        }
        return new Box(nativeBox);
    }

    public Pix getPix(int index) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        int nativePix = nativeGetPix(this.mNativePixa, index);
        if (nativePix == 0) {
            return null;
        }
        return new Pix((long) nativePix);
    }

    public int getWidth() {
        if (!this.mRecycled) {
            return this.mWidth;
        }
        throw new IllegalStateException();
    }

    public int getHeight() {
        if (!this.mRecycled) {
            return this.mHeight;
        }
        throw new IllegalStateException();
    }

    public Rect getRect() {
        if (!this.mRecycled) {
            return new Rect(0, 0, this.mWidth, this.mHeight);
        }
        throw new IllegalStateException();
    }

    public int[] getBoxGeometry(int index) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        int[] dimensions = new int[4];
        if (getBoxGeometry(index, dimensions)) {
            return dimensions;
        }
        return null;
    }

    public boolean getBoxGeometry(int index, int[] dimensions) {
        if (!this.mRecycled) {
            return nativeGetBoxGeometry(this.mNativePixa, index, dimensions);
        }
        throw new IllegalStateException();
    }

    public Rect getBoxRect(int index) {
        int[] dimensions = getBoxGeometry(index);
        if (dimensions == null) {
            return null;
        }
        int x = dimensions[0];
        int y = dimensions[1];
        return new Rect(x, y, x + dimensions[2], y + dimensions[3]);
    }

    public ArrayList<Rect> getBoxRects() {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        int pixaCount = nativeGetCount(this.mNativePixa);
        int[] buffer = new int[4];
        ArrayList<Rect> rects = new ArrayList<>(pixaCount);
        for (int i = 0; i < pixaCount; i++) {
            getBoxGeometry(i, buffer);
            int x = buffer[0];
            int y = buffer[1];
            rects.add(new Rect(x, y, buffer[2] + x, buffer[3] + y));
        }
        return rects;
    }

    public void replacePix(int index, Pix pix, Box box) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        nativeReplacePix(this.mNativePixa, index, pix.getNativePix(), box.getNativeBox());
    }

    public void mergeAndReplacePix(int indexA, int indexB) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        nativeMergeAndReplacePix(this.mNativePixa, indexA, indexB);
    }

    public boolean writeToFileRandomCmap(File file) {
        if (!this.mRecycled) {
            return nativeWriteToFileRandomCmap(this.mNativePixa, file.getAbsolutePath(), this.mWidth, this.mHeight);
        }
        throw new IllegalStateException();
    }

    public Iterator<Pix> iterator() {
        return new PixIterator();
    }
}
