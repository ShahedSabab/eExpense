package com.googlecode.leptonica.android;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import java.io.File;

public class WriteFile {
    private static native boolean nativeWriteBitmap(long j, Bitmap bitmap);

    private static native int nativeWriteBytes8(long j, byte[] bArr);

    private static native boolean nativeWriteImpliedFormat(long j, String str);

    static {
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    public static byte[] writeBytes8(Pix pixs) {
        if (pixs == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        byte[] data = new byte[(pixs.getWidth() * pixs.getHeight())];
        if (pixs.getDepth() != 8) {
            Pix pix8 = Convert.convertTo8(pixs);
            writeBytes8(pix8, data);
            pix8.recycle();
        } else {
            writeBytes8(pixs, data);
        }
        return data;
    }

    public static int writeBytes8(Pix pixs, byte[] data) {
        if (pixs == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        if (data.length >= pixs.getWidth() * pixs.getHeight()) {
            return nativeWriteBytes8(pixs.getNativePix(), data);
        }
        throw new IllegalArgumentException("Data array must be large enough to hold image bytes");
    }

    public static boolean writeImpliedFormat(Pix pixs, File file) {
        if (pixs == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        } else if (file != null) {
            return nativeWriteImpliedFormat(pixs.getNativePix(), file.getAbsolutePath());
        } else {
            throw new IllegalArgumentException("File must be non-null");
        }
    }

    public static Bitmap writeBitmap(Pix pixs) {
        if (pixs == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        int[] dimensions = pixs.getDimensions();
        Bitmap bitmap = Bitmap.createBitmap(dimensions[0], dimensions[1], Config.ARGB_8888);
        if (nativeWriteBitmap(pixs.getNativePix(), bitmap)) {
            return bitmap;
        }
        bitmap.recycle();
        return null;
    }
}
