package backbencers.nub.dailycostcalc.activities;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.p000v4.app.NavUtils;
import android.system.ErrnoException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import backbencers.nub.dailycostcalc.C0374R;
import backbencers.nub.dailycostcalc.constant.Constant;
import backbencers.nub.dailycostcalc.constant.FileCreator;
import backbencers.nub.dailycostcalc.custom.CustomToast;
import backbencers.nub.dailycostcalc.objects.TessOCR;
import com.theartofdev.edmodo.cropper.CropImageView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ScanActivity extends Activity {
    public static final String DATA_PATH = (Environment.getExternalStorageDirectory().toString() + "/DemoOCR/");
    private static final String TAG = "ScanActivity";
    public static final String lang = "eng";
    private static View my_view;
    private String category;
    Bitmap converted;
    /* access modifiers changed from: private */
    public EditText etScanResultData;
    private File imgFile;
    private Uri mCropImageUri;
    private CropImageView mCropImageView;
    /* access modifiers changed from: private */
    public ProgressDialog mProgressDialog;
    /* access modifiers changed from: private */
    public TessOCR mTessOCR;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0374R.layout.activity_scan);
        my_view = getLayoutInflater().inflate(C0374R.layout.activity_scan, null);
        this.category = getIntent().getStringExtra("Category");
        this.etScanResultData = (EditText) findViewById(C0374R.C0376id.et_scan_result_data);
        this.mCropImageView = (CropImageView) findViewById(C0374R.C0376id.crop_image_view);
        this.mCropImageView.setClickable(true);
        String[] paths = {DATA_PATH, DATA_PATH + "tessdata/"};
        int length = paths.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String path = paths[i];
            File dir = new File(path);
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    Log.v("Main", "ERROR: Creation of directory " + path + " on sdcard failed");
                    break;
                }
                Log.v("Main", "Created directory " + path + " on sdcard");
            }
            i++;
        }
        if (!new File(DATA_PATH + "tessdata/" + lang + ".traineddata").exists()) {
            try {
                InputStream in = getAssets().open("eng.traineddata");
                OutputStream out = new FileOutputStream(DATA_PATH + "tessdata/" + lang + ".traineddata");
                byte[] buf = new byte[1024];
                while (true) {
                    int len = in.read(buf);
                    if (len <= 0) {
                        break;
                    }
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
            } catch (IOException e) {
            }
        }
        this.mTessOCR = new TessOCR();
    }

    public void onLoadImageClick(View view) {
        startActivityForResult(getPickImageChooserIntent(), 200);
    }

    public void onSaveImageData(View view) {
        if (this.mCropImageView == null) {
            return;
        }
        if (this.mCropImageView.getCroppedImage(500, 500) == null) {
            new CustomToast().Show_Toast(getApplicationContext(), my_view, "Please open or capture image first.");
        } else if (this.etScanResultData.getText().toString().trim().length() > 0) {
            Intent intent = new Intent(this, DebitEditorActivity.class);
            intent.putExtra(Constant.INTENT_SCAN_DATA, this.etScanResultData.getText().toString());
            intent.putExtra("Category", this.category);
            intent.putExtra(Constant.ACTIVITY_TYPE, Constant.ACTIVITY_TYPE_ADD);
            startActivity(intent);
            finish();
        } else {
            new CustomToast().Show_Toast(getApplicationContext(), my_view, "Please extract text from image.");
        }
    }

    public void onLaunchCamera(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        this.imgFile = FileCreator.createFile();
        intent.putExtra("output", Uri.fromFile(this.imgFile));
        intent.putExtra("android.intent.extra.videoQuality", 1);
        startActivityForResult(intent, 111);
    }

    public void onCropImageClick(View view) {
        if (this.mCropImageView != null) {
            Bitmap cropped = this.mCropImageView.getCroppedImage(500, 500);
            if (cropped != null) {
                this.mCropImageView.setImageBitmap(cropped);
                doOCR(convertColorIntoBlackAndWhiteImage(cropped));
                return;
            }
            new CustomToast().Show_Toast(getApplicationContext(), my_view, "Please open or capture image first.");
        }
    }

    public void onGoBack(View view) {
        if (this.etScanResultData.getText().toString().trim().length() > 0) {
            showUnsavedChangesDialog(new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    NavUtils.navigateUpFromSameTask(ScanActivity.this);
                }
            });
        } else {
            goToDebitEditActivity();
        }
    }

    private void showUnsavedChangesDialog(OnClickListener discardButtonClickListener) {
        Builder builder = new Builder(this);
        builder.setMessage(C0374R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(C0374R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(C0374R.string.keep_editing, new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        builder.create().show();
    }

    private void goToDebitEditActivity() {
        Intent intent = new Intent(this, DebitEditorActivity.class);
        intent.putExtra(Constant.ACTIVITY_TYPE, Constant.ACTIVITY_TYPE_ADD);
        startActivity(intent);
        finish();
    }

    public void doOCR(final Bitmap bitmap) {
        if (this.mProgressDialog == null) {
            this.mProgressDialog = ProgressDialog.show(this, "Processing", "Please wait...", true);
        } else {
            this.mProgressDialog.show();
        }
        new Thread(new Runnable() {
            public void run() {
                final String result = ScanActivity.this.mTessOCR.getOCRResult(bitmap).toLowerCase();
                ScanActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        if (result != null && !result.equals("")) {
                            String trim = result.trim();
                            ScanActivity.this.etScanResultData.setText(result);
                        }
                        ScanActivity.this.mProgressDialog.dismiss();
                    }
                });
            }
        }).start();
    }

    private Bitmap convertColorIntoBlackAndWhiteImage(Bitmap orginalBitmap) {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(colorMatrix);
        Bitmap blackAndWhiteBitmap = orginalBitmap.copy(Config.ARGB_8888, true);
        Paint paint = new Paint();
        paint.setColorFilter(colorMatrixFilter);
        new Canvas(blackAndWhiteBitmap).drawBitmap(blackAndWhiteBitmap, 0.0f, 0.0f, paint);
        return blackAndWhiteBitmap;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 200 && resultCode == -1) {
            Uri imageUri = getPickImageResultUri(data);
            boolean requirePermissions = false;
            if (VERSION.SDK_INT >= 23 && checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0 && isUriRequiresPermissions(imageUri)) {
                requirePermissions = true;
                this.mCropImageUri = imageUri;
                requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 0);
            }
            if (!requirePermissions) {
                this.mCropImageView.setImageUriAsync(imageUri);
                this.mCropImageView.setClickable(true);
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (this.mCropImageUri == null || grantResults.length <= 0 || grantResults[0] != 0) {
            Toast.makeText(this, "Required permissions are not granted", 1).show();
            return;
        }
        Log.d(TAG, "onRequestPermissionsResult: " + this.mCropImageUri.getPath());
        this.mCropImageView.setImageUriAsync(this.mCropImageUri);
        this.mCropImageView.setClickable(true);
    }

    public Intent getPickImageChooserIntent() {
        Uri outputFileUri = getCaptureImageOutputUri();
        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager = getPackageManager();
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
        Intent galleryIntent = new Intent("android.intent.action.GET_CONTENT");
        galleryIntent.setType("image/*");
        for (ResolveInfo res2 : packageManager.queryIntentActivities(galleryIntent, 0)) {
            Intent intent2 = new Intent(galleryIntent);
            intent2.setComponent(new ComponentName(res2.activityInfo.packageName, res2.activityInfo.name));
            intent2.setPackage(res2.activityInfo.packageName);
            allIntents.add(intent2);
        }
        Intent mainIntent = (Intent) allIntents.get(allIntents.size() - 1);
        Iterator it = allIntents.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Intent intent3 = (Intent) it.next();
            if (intent3.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                mainIntent = intent3;
                break;
            }
        }
        allIntents.remove(mainIntent);
        Intent chooserIntent = Intent.createChooser(mainIntent, "Select source");
        chooserIntent.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) allIntents.toArray(new Parcelable[allIntents.size()]));
        return chooserIntent;
    }

    private Uri getCaptureImageOutputUri() {
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            return Uri.fromFile(new File(getImage.getPath(), "pickImageResult.jpeg"));
        }
        return null;
    }

    public Uri getPickImageResultUri(Intent data) {
        boolean isCamera = true;
        if (!(data == null || data.getData() == null)) {
            String action = data.getAction();
            isCamera = action != null && action.equals("android.media.action.IMAGE_CAPTURE");
        }
        return isCamera ? getCaptureImageOutputUri() : data.getData();
    }

    public boolean isUriRequiresPermissions(Uri uri) {
        try {
            getContentResolver().openInputStream(uri).close();
            return false;
        } catch (FileNotFoundException e) {
            if (e.getCause() instanceof ErrnoException) {
                return true;
            }
            return false;
        } catch (Exception e2) {
            return false;
        }
    }
}
