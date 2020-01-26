package com.theartofdev.edmodo.cropper;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.app.Fragment;
import com.theartofdev.edmodo.cropper.CropImageView.CropShape;
import com.theartofdev.edmodo.cropper.CropImageView.Guidelines;
import com.theartofdev.edmodo.cropper.CropImageView.ScaleType;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class CropImage {
    public static final int CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE = 2011;
    public static final int CROP_IMAGE_ACTIVITY_REQUEST_CODE = 203;
    public static final int CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE = 204;
    public static final String CROP_IMAGE_EXTRA_OPTIONS = "CROP_IMAGE_EXTRA_OPTIONS";
    public static final String CROP_IMAGE_EXTRA_RESULT = "CROP_IMAGE_EXTRA_RESULT";
    public static final String CROP_IMAGE_EXTRA_SOURCE = "CROP_IMAGE_EXTRA_SOURCE";
    public static final int PICK_IMAGE_CHOOSER_REQUEST_CODE = 200;
    public static final int PICK_IMAGE_PERMISSIONS_REQUEST_CODE = 201;

    public static final class ActivityBuilder {
        private final CropImageOptions mOptions;
        private final Uri mSource;

        private ActivityBuilder(@NonNull Uri source) {
            this.mSource = source;
            this.mOptions = new CropImageOptions();
        }

        public Intent getIntent(@NonNull Context context) {
            return getIntent(context, CropImageActivity.class);
        }

        public Intent getIntent(@NonNull Context context, @Nullable Class<?> cls) {
            this.mOptions.validate();
            Intent intent = new Intent();
            intent.setClass(context, cls);
            intent.putExtra(CropImage.CROP_IMAGE_EXTRA_SOURCE, this.mSource);
            intent.putExtra(CropImage.CROP_IMAGE_EXTRA_OPTIONS, this.mOptions);
            return intent;
        }

        public void start(@NonNull Activity activity) {
            this.mOptions.validate();
            activity.startActivityForResult(getIntent(activity), CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);
        }

        public void start(@NonNull Activity activity, @Nullable Class<?> cls) {
            this.mOptions.validate();
            activity.startActivityForResult(getIntent(activity, cls), CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);
        }

        public void start(@NonNull Context context, @NonNull Fragment fragment) {
            fragment.startActivityForResult(getIntent(context), CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);
        }

        public void start(@NonNull Context context, @NonNull Fragment fragment, @Nullable Class<?> cls) {
            fragment.startActivityForResult(getIntent(context, cls), CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);
        }

        public ActivityBuilder setCropShape(@NonNull CropShape cropShape) {
            this.mOptions.cropShape = cropShape;
            return this;
        }

        public ActivityBuilder setSnapRadius(float snapRadius) {
            this.mOptions.snapRadius = snapRadius;
            return this;
        }

        public ActivityBuilder setTouchRadius(float touchRadius) {
            this.mOptions.touchRadius = touchRadius;
            return this;
        }

        public ActivityBuilder setGuidelines(@NonNull Guidelines guidelines) {
            this.mOptions.guidelines = guidelines;
            return this;
        }

        public ActivityBuilder setScaleType(@NonNull ScaleType scaleType) {
            this.mOptions.scaleType = scaleType;
            return this;
        }

        public ActivityBuilder setShowCropOverlay(boolean showCropOverlay) {
            this.mOptions.showCropOverlay = showCropOverlay;
            return this;
        }

        public ActivityBuilder setAutoZoomEnabled(boolean autoZoomEnabled) {
            this.mOptions.autoZoomEnabled = autoZoomEnabled;
            return this;
        }

        public ActivityBuilder setMaxZoom(int maxZoom) {
            this.mOptions.maxZoom = maxZoom;
            return this;
        }

        public ActivityBuilder setInitialCropWindowPaddingRatio(float initialCropWindowPaddingRatio) {
            this.mOptions.initialCropWindowPaddingRatio = initialCropWindowPaddingRatio;
            return this;
        }

        public ActivityBuilder setFixAspectRatio(boolean fixAspectRatio) {
            this.mOptions.fixAspectRatio = fixAspectRatio;
            return this;
        }

        public ActivityBuilder setAspectRatio(int aspectRatioX, int aspectRatioY) {
            this.mOptions.aspectRatioX = aspectRatioX;
            this.mOptions.aspectRatioY = aspectRatioY;
            return this;
        }

        public ActivityBuilder setBorderLineThickness(float borderLineThickness) {
            this.mOptions.borderLineThickness = borderLineThickness;
            return this;
        }

        public ActivityBuilder setBorderLineColor(int borderLineColor) {
            this.mOptions.borderLineColor = borderLineColor;
            return this;
        }

        public ActivityBuilder setBorderCornerThickness(float borderCornerThickness) {
            this.mOptions.borderCornerThickness = borderCornerThickness;
            return this;
        }

        public ActivityBuilder setBorderCornerOffset(float borderCornerOffset) {
            this.mOptions.borderCornerOffset = borderCornerOffset;
            return this;
        }

        public ActivityBuilder setBorderCornerLength(float borderCornerLength) {
            this.mOptions.borderCornerLength = borderCornerLength;
            return this;
        }

        public ActivityBuilder setBorderCornerColor(int borderCornerColor) {
            this.mOptions.borderCornerColor = borderCornerColor;
            return this;
        }

        public ActivityBuilder setGuidelinesThickness(float guidelinesThickness) {
            this.mOptions.guidelinesThickness = guidelinesThickness;
            return this;
        }

        public ActivityBuilder setGuidelinesColor(int guidelinesColor) {
            this.mOptions.guidelinesColor = guidelinesColor;
            return this;
        }

        public ActivityBuilder setBackgroundColor(int backgroundColor) {
            this.mOptions.backgroundColor = backgroundColor;
            return this;
        }

        public ActivityBuilder setMinCropWindowSize(int minCropWindowWidth, int minCropWindowHeight) {
            this.mOptions.minCropWindowWidth = minCropWindowWidth;
            this.mOptions.minCropWindowHeight = minCropWindowHeight;
            return this;
        }

        public ActivityBuilder setMinCropResultSize(int minCropResultWidth, int minCropResultHeight) {
            this.mOptions.minCropResultWidth = minCropResultWidth;
            this.mOptions.minCropResultHeight = minCropResultHeight;
            return this;
        }

        public ActivityBuilder setMaxCropResultSize(int maxCropResultWidth, int maxCropResultHeight) {
            this.mOptions.maxCropResultWidth = maxCropResultWidth;
            this.mOptions.maxCropResultHeight = maxCropResultHeight;
            return this;
        }

        public ActivityBuilder setActivityTitle(String activityTitle) {
            this.mOptions.activityTitle = activityTitle;
            return this;
        }

        public ActivityBuilder setActivityMenuIconColor(int activityMenuIconColor) {
            this.mOptions.activityMenuIconColor = activityMenuIconColor;
            return this;
        }

        public ActivityBuilder setOutputUri(Uri outputUri) {
            this.mOptions.outputUri = outputUri;
            return this;
        }

        public ActivityBuilder setOutputCompressFormat(CompressFormat outputCompressFormat) {
            this.mOptions.outputCompressFormat = outputCompressFormat;
            return this;
        }

        public ActivityBuilder setOutputCompressQuality(int outputCompressQuality) {
            this.mOptions.outputCompressQuality = outputCompressQuality;
            return this;
        }

        public ActivityBuilder setRequestedSize(int reqWidth, int reqHeight) {
            this.mOptions.outputRequestWidth = reqWidth;
            this.mOptions.outputRequestHeight = reqHeight;
            return this;
        }

        public ActivityBuilder setNoOutputImage(boolean noOutputImage) {
            this.mOptions.noOutputImage = noOutputImage;
            return this;
        }

        public ActivityBuilder setInitialCropWindowRectangle(Rect initialCropWindowRectangle) {
            this.mOptions.initialCropWindowRectangle = initialCropWindowRectangle;
            return this;
        }

        public ActivityBuilder setInitialRotation(int initialRotation) {
            this.mOptions.initialRotation = initialRotation;
            return this;
        }

        public ActivityBuilder setAllowRotation(boolean allowRotation) {
            this.mOptions.allowRotation = allowRotation;
            return this;
        }

        public ActivityBuilder setAllowCounterRotation(boolean allowCounterRotation) {
            this.mOptions.allowCounterRotation = allowCounterRotation;
            return this;
        }

        public ActivityBuilder setRotationDegrees(int rotationDegrees) {
            this.mOptions.rotationDegrees = rotationDegrees;
            return this;
        }
    }

    public static final class ActivityResult implements Parcelable {
        public static final Creator<ActivityResult> CREATOR = new Creator<ActivityResult>() {
            public ActivityResult createFromParcel(Parcel in) {
                return new ActivityResult(in);
            }

            public ActivityResult[] newArray(int size) {
                return new ActivityResult[size];
            }
        };
        private final float[] mCropPoints;
        private final Rect mCropRect;
        private final Exception mError;
        private final int mRotation;
        private final Uri mUri;

        ActivityResult(Uri uri, Exception error, float[] cropPoints, Rect cropRect, int rotation) {
            this.mUri = uri;
            this.mError = error;
            this.mCropPoints = cropPoints;
            this.mCropRect = cropRect;
            this.mRotation = rotation;
        }

        protected ActivityResult(Parcel in) {
            this.mUri = (Uri) in.readParcelable(Uri.class.getClassLoader());
            this.mError = (Exception) in.readSerializable();
            this.mCropPoints = in.createFloatArray();
            this.mCropRect = (Rect) in.readParcelable(Rect.class.getClassLoader());
            this.mRotation = in.readInt();
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.mUri, flags);
            dest.writeSerializable(this.mError);
            dest.writeFloatArray(this.mCropPoints);
            dest.writeParcelable(this.mCropRect, flags);
            dest.writeInt(this.mRotation);
        }

        public int describeContents() {
            return 0;
        }

        public boolean isSuccessful() {
            return this.mError == null;
        }

        public Uri getUri() {
            return this.mUri;
        }

        public Exception getError() {
            return this.mError;
        }

        public float[] getCropPoints() {
            return this.mCropPoints;
        }

        public Rect getCropRect() {
            return this.mCropRect;
        }

        public int getRotation() {
            return this.mRotation;
        }
    }

    private CropImage() {
    }

    public static Bitmap toOvalBitmap(@NonNull Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawOval(new RectF(0.0f, 0.0f, (float) width, (float) height), paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        bitmap.recycle();
        return output;
    }

    public static void startPickImageActivity(@NonNull Activity activity) {
        activity.startActivityForResult(getPickImageChooserIntent(activity), 200);
    }

    public static Intent getPickImageChooserIntent(@NonNull Context context) {
        return getPickImageChooserIntent(context, context.getString(C0730R.string.pick_image_intent_chooser_title), false);
    }

    public static Intent getPickImageChooserIntent(@NonNull Context context, CharSequence title, boolean includeDocuments) {
        Intent target;
        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager = context.getPackageManager();
        allIntents.addAll(getCameraIntents(context, packageManager));
        allIntents.addAll(getGalleryIntents(packageManager, includeDocuments));
        if (VERSION.SDK_INT >= 23) {
            target = new Intent();
        } else {
            target = (Intent) allIntents.get(allIntents.size() - 1);
            allIntents.remove(allIntents.size() - 1);
        }
        Intent chooserIntent = Intent.createChooser(target, title);
        chooserIntent.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) allIntents.toArray(new Parcelable[allIntents.size()]));
        return chooserIntent;
    }

    public static List<Intent> getCameraIntents(@NonNull Context context, @NonNull PackageManager packageManager) {
        List<Intent> allIntents = new ArrayList<>();
        Uri outputFileUri = getCaptureImageOutputUri(context);
        Intent captureIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        for (ResolveInfo res : packageManager.queryIntentActivities(captureIntent, 0)) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
                intent.putExtra("output", outputFileUri);
            }
            allIntents.add(intent);
        }
        return allIntents;
    }

    public static List<Intent> getGalleryIntents(@NonNull PackageManager packageManager, boolean includeDocuments) {
        List<Intent> intents = new ArrayList<>();
        Intent galleryIntent = new Intent("android.intent.action.GET_CONTENT");
        galleryIntent.setType("image/*");
        for (ResolveInfo res : packageManager.queryIntentActivities(galleryIntent, 0)) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            intents.add(intent);
        }
        if (!includeDocuments) {
            Iterator it = intents.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Intent intent2 = (Intent) it.next();
                if (intent2.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                    intents.remove(intent2);
                    break;
                }
            }
        }
        return intents;
    }

    public static boolean isExplicitCameraPermissionRequired(@NonNull Context context) {
        if (VERSION.SDK_INT < 23 || !hasPermissionInManifest(context, "android.permission.CAMERA") || context.checkSelfPermission("android.permission.CAMERA") == 0) {
            return false;
        }
        return true;
    }

    public static boolean hasPermissionInManifest(@NonNull Context context, @NonNull String permissionName) {
        try {
            String[] declaredPermisisons = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (declaredPermisisons == null || declaredPermisisons.length <= 0) {
                return false;
            }
            for (String p : declaredPermisisons) {
                if (p.equalsIgnoreCase(permissionName)) {
                    return true;
                }
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static Uri getCaptureImageOutputUri(@NonNull Context context) {
        File getImage = context.getExternalCacheDir();
        if (getImage != null) {
            return Uri.fromFile(new File(getImage.getPath(), "pickImageResult.jpeg"));
        }
        return null;
    }

    public static Uri getPickImageResultUri(@NonNull Context context, @Nullable Intent data) {
        boolean isCamera = true;
        if (!(data == null || data.getData() == null)) {
            String action = data.getAction();
            isCamera = action != null && action.equals("android.media.action.IMAGE_CAPTURE");
        }
        return (isCamera || data.getData() == null) ? getCaptureImageOutputUri(context) : data.getData();
    }

    public static boolean isReadExternalStoragePermissionsRequired(@NonNull Context context, @NonNull Uri uri) {
        return VERSION.SDK_INT >= 23 && context.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0 && isUriRequiresPermissions(context, uri);
    }

    public static boolean isUriRequiresPermissions(@NonNull Context context, @NonNull Uri uri) {
        try {
            context.getContentResolver().openInputStream(uri).close();
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public static ActivityBuilder activity(@NonNull Uri uri) {
        if (uri != null && !uri.equals(Uri.EMPTY)) {
            return new ActivityBuilder(uri);
        }
        throw new IllegalArgumentException("Uri must be non null or empty");
    }

    public static ActivityResult getActivityResult(@Nullable Intent data) {
        if (data != null) {
            return (ActivityResult) data.getParcelableExtra(CROP_IMAGE_EXTRA_RESULT);
        }
        return null;
    }
}
