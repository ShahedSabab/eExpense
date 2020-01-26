package com.theartofdev.edmodo.cropper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.AsyncTask;
import java.lang.ref.WeakReference;

final class BitmapCroppingWorkerTask extends AsyncTask<Void, Void, Result> {
    private final int mAspectRatioX;
    private final int mAspectRatioY;
    private final Bitmap mBitmap;
    private final Context mContext;
    private final WeakReference<CropImageView> mCropImageViewReference;
    private final float[] mCropPoints;
    private final int mDegreesRotated;
    private final boolean mFixAspectRatio;
    private final int mOrgHeight;
    private final int mOrgWidth;
    private final int mReqHeight;
    private final int mReqWidth;
    private final CompressFormat mSaveCompressFormat;
    private final int mSaveCompressQuality;
    private final Uri mSaveUri;
    private final Uri mUri;

    public static final class Result {
        public final Bitmap bitmap;
        public final Exception error;
        public final boolean isSave;
        public final Uri uri;

        Result(Bitmap bitmap2) {
            this.bitmap = bitmap2;
            this.uri = null;
            this.error = null;
            this.isSave = false;
        }

        Result(Uri uri2) {
            this.bitmap = null;
            this.uri = uri2;
            this.error = null;
            this.isSave = true;
        }

        Result(Exception error2, boolean isSave2) {
            this.bitmap = null;
            this.uri = null;
            this.error = error2;
            this.isSave = isSave2;
        }
    }

    public BitmapCroppingWorkerTask(CropImageView cropImageView, Bitmap bitmap, float[] cropPoints, int degreesRotated, boolean fixAspectRatio, int aspectRatioX, int aspectRatioY, Uri saveUri, CompressFormat saveCompressFormat, int saveCompressQuality) {
        this.mCropImageViewReference = new WeakReference<>(cropImageView);
        this.mContext = cropImageView.getContext();
        this.mBitmap = bitmap;
        this.mCropPoints = cropPoints;
        this.mUri = null;
        this.mDegreesRotated = degreesRotated;
        this.mFixAspectRatio = fixAspectRatio;
        this.mAspectRatioX = aspectRatioX;
        this.mAspectRatioY = aspectRatioY;
        this.mSaveUri = saveUri;
        this.mSaveCompressFormat = saveCompressFormat;
        this.mSaveCompressQuality = saveCompressQuality;
        this.mOrgWidth = 0;
        this.mOrgHeight = 0;
        this.mReqWidth = 0;
        this.mReqHeight = 0;
    }

    public BitmapCroppingWorkerTask(CropImageView cropImageView, Uri uri, float[] cropPoints, int degreesRotated, int orgWidth, int orgHeight, boolean fixAspectRatio, int aspectRatioX, int aspectRatioY, int reqWidth, int reqHeight, Uri saveUri, CompressFormat saveCompressFormat, int saveCompressQuality) {
        this.mCropImageViewReference = new WeakReference<>(cropImageView);
        this.mContext = cropImageView.getContext();
        this.mUri = uri;
        this.mCropPoints = cropPoints;
        this.mDegreesRotated = degreesRotated;
        this.mFixAspectRatio = fixAspectRatio;
        this.mAspectRatioX = aspectRatioX;
        this.mAspectRatioY = aspectRatioY;
        this.mOrgWidth = orgWidth;
        this.mOrgHeight = orgHeight;
        this.mReqWidth = reqWidth;
        this.mReqHeight = reqHeight;
        this.mSaveUri = saveUri;
        this.mSaveCompressFormat = saveCompressFormat;
        this.mSaveCompressQuality = saveCompressQuality;
        this.mBitmap = null;
    }

    public Uri getUri() {
        return this.mUri;
    }

    /* access modifiers changed from: protected */
    public Result doInBackground(Void... params) {
        try {
            if (isCancelled()) {
                return null;
            }
            Bitmap bitmap = null;
            if (this.mUri != null) {
                bitmap = BitmapUtils.cropBitmap(this.mContext, this.mUri, this.mCropPoints, this.mDegreesRotated, this.mOrgWidth, this.mOrgHeight, this.mFixAspectRatio, this.mAspectRatioX, this.mAspectRatioY, this.mReqWidth, this.mReqHeight);
            } else if (this.mBitmap != null) {
                bitmap = BitmapUtils.cropBitmap(this.mBitmap, this.mCropPoints, this.mDegreesRotated, this.mFixAspectRatio, this.mAspectRatioX, this.mAspectRatioY);
            }
            if (this.mSaveUri == null) {
                return new Result(bitmap);
            }
            BitmapUtils.writeBitmapToUri(this.mContext, bitmap, this.mSaveUri, this.mSaveCompressFormat, this.mSaveCompressQuality);
            bitmap.recycle();
            return new Result(this.mSaveUri);
        } catch (Exception e) {
            return new Result(e, this.mSaveUri != null);
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Result result) {
        if (result != null) {
            boolean completeCalled = false;
            if (!isCancelled()) {
                CropImageView cropImageView = (CropImageView) this.mCropImageViewReference.get();
                if (cropImageView != null) {
                    completeCalled = true;
                    cropImageView.onImageCroppingAsyncComplete(result);
                }
            }
            if (!completeCalled && result.bitmap != null) {
                result.bitmap.recycle();
            }
        }
    }
}
