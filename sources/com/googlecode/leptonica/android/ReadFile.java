package com.googlecode.leptonica.android;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;
import java.io.File;

public class ReadFile {
    private static final String LOG_TAG = ReadFile.class.getSimpleName();

    private static native long nativeReadBitmap(Bitmap bitmap);

    private static native long nativeReadBytes8(byte[] bArr, int i, int i2);

    private static native long nativeReadFile(String str);

    private static native long nativeReadMem(byte[] bArr, int i);

    private static native boolean nativeReplaceBytes8(long j, byte[] bArr, int i, int i2);

    static {
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    public static Pix readMem(byte[] encodedData) {
        if (encodedData == null) {
            Log.e(LOG_TAG, "Image data byte array must be non-null");
            return null;
        }
        Options opts = new Options();
        opts.inPreferredConfig = Config.ARGB_8888;
        Bitmap bmp = BitmapFactory.decodeByteArray(encodedData, 0, encodedData.length, opts);
        Pix readBitmap = readBitmap(bmp);
        bmp.recycle();
        return readBitmap;
    }

    public static Pix readBytes8(byte[] pixelData, int width, int height) {
        if (pixelData == null) {
            throw new IllegalArgumentException("Byte array must be non-null");
        } else if (width <= 0) {
            throw new IllegalArgumentException("Image width must be greater than 0");
        } else if (height <= 0) {
            throw new IllegalArgumentException("Image height must be greater than 0");
        } else if (pixelData.length < width * height) {
            throw new IllegalArgumentException("Array length does not match dimensions");
        } else {
            long nativePix = nativeReadBytes8(pixelData, width, height);
            if (nativePix != 0) {
                return new Pix(nativePix);
            }
            throw new RuntimeException("Failed to read pix from memory");
        }
    }

    public static boolean replaceBytes8(Pix pixs, byte[] pixelData, int width, int height) {
        if (pixs == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        } else if (pixelData == null) {
            throw new IllegalArgumentException("Byte array must be non-null");
        } else if (width <= 0) {
            throw new IllegalArgumentException("Image width must be greater than 0");
        } else if (height <= 0) {
            throw new IllegalArgumentException("Image height must be greater than 0");
        } else if (pixelData.length < width * height) {
            throw new IllegalArgumentException("Array length does not match dimensions");
        } else if (pixs.getWidth() != width) {
            throw new IllegalArgumentException("Source pix width does not match image width");
        } else if (pixs.getHeight() == height) {
            return nativeReplaceBytes8(pixs.getNativePix(), pixelData, width, height);
        } else {
            throw new IllegalArgumentException("Source pix height does not match image height");
        }
    }

    public static Pix readFile(File file) {
        if (file == null) {
            Log.e(LOG_TAG, "File must be non-null");
            return null;
        } else if (!file.exists()) {
            Log.e(LOG_TAG, "File does not exist");
            return null;
        } else if (!file.canRead()) {
            Log.e(LOG_TAG, "Cannot read file");
            return null;
        } else {
            long nativePix = nativeReadFile(file.getAbsolutePath());
            if (nativePix != 0) {
                return new Pix(nativePix);
            }
            Options opts = new Options();
            opts.inPreferredConfig = Config.ARGB_8888;
            Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath(), opts);
            if (bmp == null) {
                Log.e(LOG_TAG, "Cannot decode bitmap");
                return null;
            }
            Pix readBitmap = readBitmap(bmp);
            bmp.recycle();
            return readBitmap;
        }
    }

    public static Pix readBitmap(Bitmap bmp) {
        if (bmp == null) {
            Log.e(LOG_TAG, "Bitmap must be non-null");
            return null;
        } else if (bmp.getConfig() != Config.ARGB_8888) {
            Log.e(LOG_TAG, "Bitmap config must be ARGB_8888");
            return null;
        } else {
            long nativePix = nativeReadBitmap(bmp);
            if (nativePix != 0) {
                return new Pix(nativePix);
            }
            Log.e(LOG_TAG, "Failed to read pix from bitmap");
            return null;
        }
    }
}
