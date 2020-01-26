package com.theartofdev.edmodo.cropper;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.net.Uri;
import android.util.Pair;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

final class BitmapUtils {
    static final Rect EMPTY_RECT = new Rect();
    static final RectF EMPTY_RECT_F = new RectF();
    static final float[] POINTS = new float[6];
    static final float[] POINTS2 = new float[6];
    static final RectF RECT = new RectF();
    static int mMaxTextureSize;
    static Pair<String, WeakReference<Bitmap>> mStateBitmap;

    public static final class DecodeBitmapResult {
        public final Bitmap bitmap;
        public final int sampleSize;

        DecodeBitmapResult(Bitmap bitmap2, int sampleSize2) {
            this.sampleSize = sampleSize2;
            this.bitmap = bitmap2;
        }
    }

    public static final class RotateBitmapResult {
        public final Bitmap bitmap;
        public final int degrees;

        RotateBitmapResult(Bitmap bitmap2, int degrees2) {
            this.bitmap = bitmap2;
            this.degrees = degrees2;
        }
    }

    BitmapUtils() {
    }

    public static RotateBitmapResult rotateBitmapByExif(Bitmap bitmap, Context context, Uri uri) {
        try {
            File file = getFileFromUri(context, uri);
            if (file.exists()) {
                return rotateBitmapByExif(bitmap, new ExifInterface(file.getAbsolutePath()));
            }
        } catch (Exception e) {
        }
        return new RotateBitmapResult(bitmap, 0);
    }

    public static RotateBitmapResult rotateBitmapByExif(Bitmap bitmap, ExifInterface exif) {
        int degrees;
        switch (exif.getAttributeInt("Orientation", 1)) {
            case 3:
                degrees = 180;
                break;
            case 6:
                degrees = 90;
                break;
            case 8:
                degrees = 270;
                break;
            default:
                degrees = 0;
                break;
        }
        return new RotateBitmapResult(bitmap, degrees);
    }

    public static DecodeBitmapResult decodeSampledBitmap(Context context, Uri uri, int reqWidth, int reqHeight) {
        try {
            ContentResolver resolver = context.getContentResolver();
            Options options = decodeImageForOption(resolver, uri);
            options.inSampleSize = Math.max(calculateInSampleSizeByReqestedSize(options.outWidth, options.outHeight, reqWidth, reqHeight), calculateInSampleSizeByMaxTextureSize(options.outWidth, options.outHeight));
            return new DecodeBitmapResult(decodeImage(resolver, uri, options), options.inSampleSize);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load sampled bitmap: " + uri + "\r\n" + e.getMessage(), e);
        }
    }

    public static Bitmap cropBitmap(Bitmap bitmap, float[] points, int degreesRotated, boolean fixAspectRatio, int aspectRatioX, int aspectRatioY) {
        Rect rect = getRectFromPoints(points, bitmap.getWidth(), bitmap.getHeight(), fixAspectRatio, aspectRatioX, aspectRatioY);
        Matrix matrix = new Matrix();
        matrix.setRotate((float) degreesRotated, (float) (bitmap.getWidth() / 2), (float) (bitmap.getHeight() / 2));
        Bitmap result = Bitmap.createBitmap(bitmap, rect.left, rect.top, rect.width(), rect.height(), matrix, true);
        if (result == bitmap) {
            result = bitmap.copy(bitmap.getConfig(), false);
        }
        if (degreesRotated % 90 != 0) {
            return cropForRotatedImage(result, points, rect, degreesRotated, fixAspectRatio, aspectRatioX, aspectRatioY);
        }
        return result;
    }

    public static Bitmap cropBitmap(Context context, Uri loadedImageUri, float[] points, int degreesRotated, int orgWidth, int orgHeight, boolean fixAspectRatio, int aspectRatioX, int aspectRatioY, int reqWidth, int reqHeight) {
        Rect rect = getRectFromPoints(points, orgWidth, orgHeight, fixAspectRatio, aspectRatioX, aspectRatioY);
        Bitmap result = null;
        try {
            result = decodeSampledBitmapRegion(context, loadedImageUri, rect, reqWidth > 0 ? reqWidth : rect.width(), reqHeight > 0 ? reqHeight : rect.height());
        } catch (Exception e) {
        }
        if (result != null) {
            Bitmap result2 = rotateBitmapInt(result, degreesRotated);
            if (degreesRotated % 90 != 0) {
                return cropForRotatedImage(result2, points, rect, degreesRotated, fixAspectRatio, aspectRatioX, aspectRatioY);
            }
            return result2;
        }
        try {
            Options options = new Options();
            options.inSampleSize = calculateInSampleSizeByReqestedSize(rect.width(), rect.height(), reqWidth, reqHeight);
            Bitmap fullBitmap = decodeImage(context.getContentResolver(), loadedImageUri, options);
            if (fullBitmap == null) {
                return result;
            }
            Bitmap result3 = cropBitmap(fullBitmap, points, degreesRotated, fixAspectRatio, aspectRatioX, aspectRatioY);
            fullBitmap.recycle();
            return result3;
        } catch (Exception e2) {
            throw new RuntimeException("Failed to load sampled bitmap: " + loadedImageUri + "\r\n" + e2.getMessage(), e2);
        }
    }

    public static float getRectLeft(float[] points) {
        return Math.min(Math.min(Math.min(points[0], points[2]), points[4]), points[6]);
    }

    public static float getRectTop(float[] points) {
        return Math.min(Math.min(Math.min(points[1], points[3]), points[5]), points[7]);
    }

    public static float getRectRight(float[] points) {
        return Math.max(Math.max(Math.max(points[0], points[2]), points[4]), points[6]);
    }

    public static float getRectBottom(float[] points) {
        return Math.max(Math.max(Math.max(points[1], points[3]), points[5]), points[7]);
    }

    public static float getRectWidth(float[] points) {
        return getRectRight(points) - getRectLeft(points);
    }

    public static float getRectHeight(float[] points) {
        return getRectBottom(points) - getRectTop(points);
    }

    public static float getRectCenterX(float[] points) {
        return (getRectRight(points) + getRectLeft(points)) / 2.0f;
    }

    public static float getRectCenterY(float[] points) {
        return (getRectBottom(points) + getRectTop(points)) / 2.0f;
    }

    public static Rect getRectFromPoints(float[] points, int imageWidth, int imageHeight, boolean fixAspectRatio, int aspectRatioX, int aspectRatioY) {
        Rect rect = new Rect(Math.round(Math.max(0.0f, getRectLeft(points))), Math.round(Math.max(0.0f, getRectTop(points))), Math.round(Math.min((float) imageWidth, getRectRight(points))), Math.round(Math.min((float) imageHeight, getRectBottom(points))));
        if (fixAspectRatio) {
            fixRectForAspectRatio(rect, aspectRatioX, aspectRatioY);
        }
        return rect;
    }

    public static void fixRectForAspectRatio(Rect rect, int aspectRatioX, int aspectRatioY) {
        if (aspectRatioX == aspectRatioY && rect.width() != rect.height()) {
            if (rect.height() > rect.width()) {
                rect.bottom -= rect.height() - rect.width();
            } else {
                rect.right -= rect.width() - rect.height();
            }
        }
    }

    public static void writeBitmapToUri(Context context, Bitmap bitmap, Uri uri, CompressFormat compressFormat, int compressQuality) throws FileNotFoundException {
        OutputStream outputStream = null;
        try {
            outputStream = context.getContentResolver().openOutputStream(uri);
            bitmap.compress(compressFormat, compressQuality, outputStream);
        } finally {
            closeSafe(outputStream);
        }
    }

    private static Options decodeImageForOption(ContentResolver resolver, Uri uri) throws FileNotFoundException {
        InputStream stream = null;
        try {
            stream = resolver.openInputStream(uri);
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(stream, EMPTY_RECT, options);
            options.inJustDecodeBounds = false;
            return options;
        } finally {
            closeSafe(stream);
        }
    }

    /* JADX INFO: finally extract failed */
    private static Bitmap decodeImage(ContentResolver resolver, Uri uri, Options options) throws FileNotFoundException {
        do {
            InputStream stream = null;
            try {
                stream = resolver.openInputStream(uri);
                Bitmap decodeStream = BitmapFactory.decodeStream(stream, EMPTY_RECT, options);
                closeSafe(stream);
                return decodeStream;
            } catch (OutOfMemoryError e) {
                options.inSampleSize *= 2;
                closeSafe(stream);
                if (options.inSampleSize > 512) {
                    throw new RuntimeException("Failed to decode image: " + uri);
                }
            } catch (Throwable th) {
                closeSafe(stream);
                throw th;
            }
        } while (options.inSampleSize > 512);
        throw new RuntimeException("Failed to decode image: " + uri);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0041  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Bitmap decodeSampledBitmapRegion(android.content.Context r7, android.net.Uri r8, android.graphics.Rect r9, int r10, int r11) {
        /*
            r3 = 0
            r0 = 0
            android.graphics.BitmapFactory$Options r2 = new android.graphics.BitmapFactory$Options     // Catch:{ Exception -> 0x0046 }
            r2.<init>()     // Catch:{ Exception -> 0x0046 }
            int r4 = r9.width()     // Catch:{ Exception -> 0x0046 }
            int r5 = r9.height()     // Catch:{ Exception -> 0x0046 }
            int r4 = calculateInSampleSizeByReqestedSize(r4, r5, r10, r11)     // Catch:{ Exception -> 0x0046 }
            r2.inSampleSize = r4     // Catch:{ Exception -> 0x0046 }
            android.content.ContentResolver r4 = r7.getContentResolver()     // Catch:{ Exception -> 0x0046 }
            java.io.InputStream r3 = r4.openInputStream(r8)     // Catch:{ Exception -> 0x0046 }
            r4 = 0
            android.graphics.BitmapRegionDecoder r0 = android.graphics.BitmapRegionDecoder.newInstance(r3, r4)     // Catch:{ Exception -> 0x0046 }
        L_0x0022:
            android.graphics.Bitmap r4 = r0.decodeRegion(r9, r2)     // Catch:{ OutOfMemoryError -> 0x002f }
            closeSafe(r3)
            if (r0 == 0) goto L_0x002e
            r0.recycle()
        L_0x002e:
            return r4
        L_0x002f:
            r1 = move-exception
            int r4 = r2.inSampleSize     // Catch:{ Exception -> 0x0046 }
            int r4 = r4 * 2
            r2.inSampleSize = r4     // Catch:{ Exception -> 0x0046 }
            int r4 = r2.inSampleSize     // Catch:{ Exception -> 0x0046 }
            r5 = 512(0x200, float:7.175E-43)
            if (r4 <= r5) goto L_0x0022
            closeSafe(r3)
            if (r0 == 0) goto L_0x0044
            r0.recycle()
        L_0x0044:
            r4 = 0
            goto L_0x002e
        L_0x0046:
            r1 = move-exception
            java.lang.RuntimeException r4 = new java.lang.RuntimeException     // Catch:{ all -> 0x006e }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x006e }
            r5.<init>()     // Catch:{ all -> 0x006e }
            java.lang.String r6 = "Failed to load sampled bitmap: "
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x006e }
            java.lang.StringBuilder r5 = r5.append(r8)     // Catch:{ all -> 0x006e }
            java.lang.String r6 = "\r\n"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x006e }
            java.lang.String r6 = r1.getMessage()     // Catch:{ all -> 0x006e }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x006e }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x006e }
            r4.<init>(r5, r1)     // Catch:{ all -> 0x006e }
            throw r4     // Catch:{ all -> 0x006e }
        L_0x006e:
            r4 = move-exception
            closeSafe(r3)
            if (r0 == 0) goto L_0x0077
            r0.recycle()
        L_0x0077:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.theartofdev.edmodo.cropper.BitmapUtils.decodeSampledBitmapRegion(android.content.Context, android.net.Uri, android.graphics.Rect, int, int):android.graphics.Bitmap");
    }

    private static Bitmap cropForRotatedImage(Bitmap bitmap, float[] points, Rect rect, int degreesRotated, boolean fixAspectRatio, int aspectRatioX, int aspectRatioY) {
        if (degreesRotated % 90 == 0) {
            return bitmap;
        }
        int adjLeft = 0;
        int adjTop = 0;
        int width = 0;
        int height = 0;
        double rads = Math.toRadians((double) degreesRotated);
        int compareTo = (degreesRotated < 90 || (degreesRotated > 180 && degreesRotated < 270)) ? rect.left : rect.right;
        int i = 0;
        while (true) {
            if (i < points.length) {
                if (points[i] >= ((float) (compareTo - 1)) && points[i] <= ((float) (compareTo + 1))) {
                    adjLeft = (int) Math.abs(Math.sin(rads) * ((double) (((float) rect.bottom) - points[i + 1])));
                    adjTop = (int) Math.abs(Math.cos(rads) * ((double) (points[i + 1] - ((float) rect.top))));
                    width = (int) Math.abs(((double) (points[i + 1] - ((float) rect.top))) / Math.sin(rads));
                    height = (int) Math.abs(((double) (((float) rect.bottom) - points[i + 1])) / Math.cos(rads));
                    break;
                }
                i += 2;
            } else {
                break;
            }
        }
        rect.set(adjLeft, adjTop, adjLeft + width, adjTop + height);
        if (fixAspectRatio) {
            fixRectForAspectRatio(rect, aspectRatioX, aspectRatioY);
        }
        Bitmap bitmapTmp = bitmap;
        Bitmap bitmap2 = Bitmap.createBitmap(bitmap, rect.left, rect.top, rect.width(), rect.height());
        bitmapTmp.recycle();
        return bitmap2;
    }

    private static int calculateInSampleSizeByReqestedSize(int width, int height, int reqWidth, int reqHeight) {
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            while ((height / 2) / inSampleSize > reqHeight && (width / 2) / inSampleSize > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    private static int calculateInSampleSizeByMaxTextureSize(int width, int height) {
        int inSampleSize = 1;
        if (mMaxTextureSize == 0) {
            mMaxTextureSize = getMaxTextureSize();
        }
        if (mMaxTextureSize > 0) {
            while (true) {
                if (height / inSampleSize <= mMaxTextureSize && width / inSampleSize <= mMaxTextureSize) {
                    break;
                }
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    private static File getFileFromUri(Context context, Uri uri) {
        File file = new File(uri.getPath());
        if (file.exists()) {
            return file;
        }
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow("_data");
            cursor.moveToFirst();
            File file2 = new File(cursor.getString(column_index));
            if (cursor != null) {
                cursor.close();
                file = file2;
            } else {
                file = file2;
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return file;
    }

    private static Bitmap rotateBitmapInt(Bitmap bitmap, int degrees) {
        if (degrees <= 0) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate((float) degrees);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
        if (newBitmap == bitmap) {
            return newBitmap;
        }
        bitmap.recycle();
        return newBitmap;
    }

    private static int getMaxTextureSize() {
        try {
            EGL10 egl = (EGL10) EGLContext.getEGL();
            EGLDisplay display = egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            egl.eglInitialize(display, new int[2]);
            int[] totalConfigurations = new int[1];
            egl.eglGetConfigs(display, null, 0, totalConfigurations);
            EGLConfig[] configurationsList = new EGLConfig[totalConfigurations[0]];
            egl.eglGetConfigs(display, configurationsList, totalConfigurations[0], totalConfigurations);
            int[] textureSize = new int[1];
            int maximumTextureSize = 0;
            for (int i = 0; i < totalConfigurations[0]; i++) {
                egl.eglGetConfigAttrib(display, configurationsList[i], 12332, textureSize);
                if (maximumTextureSize < textureSize[0]) {
                    maximumTextureSize = textureSize[0];
                }
            }
            egl.eglTerminate(display);
            return Math.max(maximumTextureSize, 2048);
        } catch (Exception e) {
            return 2048;
        }
    }

    private static void closeSafe(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }
}
