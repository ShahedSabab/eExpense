package com.theartofdev.edmodo.cropper;

import android.content.Intent;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.support.p003v7.app.ActionBar;
import android.support.p003v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.theartofdev.edmodo.cropper.CropImage.ActivityResult;
import com.theartofdev.edmodo.cropper.CropImageView.OnSaveCroppedImageCompleteListener;
import com.theartofdev.edmodo.cropper.CropImageView.OnSetImageUriCompleteListener;
import java.io.File;
import java.io.IOException;

public class CropImageActivity extends AppCompatActivity implements OnSetImageUriCompleteListener, OnSaveCroppedImageCompleteListener {
    private CropImageView mCropImageView;
    private CropImageOptions mOptions;

    public void onCreate(Bundle savedInstanceState) {
        String title;
        super.onCreate(savedInstanceState);
        setContentView(C0730R.layout.crop_image_activity);
        this.mCropImageView = (CropImageView) findViewById(C0730R.C0732id.cropImageView);
        Intent intent = getIntent();
        Uri source = (Uri) intent.getParcelableExtra(CropImage.CROP_IMAGE_EXTRA_SOURCE);
        this.mOptions = (CropImageOptions) intent.getParcelableExtra(CropImage.CROP_IMAGE_EXTRA_OPTIONS);
        if (savedInstanceState == null) {
            this.mCropImageView.setImageUriAsync(source);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (this.mOptions.activityTitle == null || this.mOptions.activityTitle.isEmpty()) {
                title = getResources().getString(C0730R.string.crop_image_activity_title);
            } else {
                title = this.mOptions.activityTitle;
            }
            actionBar.setTitle((CharSequence) title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.mCropImageView.setOnSetImageUriCompleteListener(this);
        this.mCropImageView.setOnSaveCroppedImageCompleteListener(this);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.mCropImageView.setOnSetImageUriCompleteListener(null);
        this.mCropImageView.setOnSaveCroppedImageCompleteListener(null);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0730R.C0733menu.crop_image_menu, menu);
        if (!this.mOptions.allowRotation) {
            menu.removeItem(C0730R.C0732id.crop_image_menu_rotate_left);
            menu.removeItem(C0730R.C0732id.crop_image_menu_rotate_right);
        } else if (this.mOptions.allowCounterRotation) {
            menu.findItem(C0730R.C0732id.crop_image_menu_rotate_left).setVisible(true);
        }
        Drawable cropIcon = null;
        try {
            cropIcon = ContextCompat.getDrawable(this, C0730R.C0731drawable.crop_image_menu_crop);
            if (cropIcon != null) {
                menu.findItem(C0730R.C0732id.crop_image_menu_crop).setIcon(cropIcon);
            }
        } catch (Exception e) {
        }
        if (this.mOptions.activityMenuIconColor != 0) {
            updateMenuItemIconColor(menu, C0730R.C0732id.crop_image_menu_rotate_left, this.mOptions.activityMenuIconColor);
            updateMenuItemIconColor(menu, C0730R.C0732id.crop_image_menu_rotate_right, this.mOptions.activityMenuIconColor);
            if (cropIcon != null) {
                updateMenuItemIconColor(menu, C0730R.C0732id.crop_image_menu_crop, this.mOptions.activityMenuIconColor);
            }
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == C0730R.C0732id.crop_image_menu_crop) {
            cropImage();
            return true;
        } else if (item.getItemId() == C0730R.C0732id.crop_image_menu_rotate_left) {
            rotateImage(-this.mOptions.rotationDegrees);
            return true;
        } else if (item.getItemId() == C0730R.C0732id.crop_image_menu_rotate_right) {
            rotateImage(this.mOptions.rotationDegrees);
            return true;
        } else if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        } else {
            setResultCancel();
            return true;
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        setResultCancel();
    }

    public void onSetImageUriComplete(CropImageView view, Uri uri, Exception error) {
        if (error == null) {
            if (this.mOptions.initialCropWindowRectangle != null) {
                this.mCropImageView.setCropRect(this.mOptions.initialCropWindowRectangle);
            }
            if (this.mOptions.initialRotation > -1) {
                this.mCropImageView.setRotatedDegrees(this.mOptions.initialRotation);
                return;
            }
            return;
        }
        setResult(null, error);
    }

    public void onSaveCroppedImageComplete(CropImageView view, Uri uri, Exception error) {
        setResult(uri, error);
    }

    /* access modifiers changed from: protected */
    public void cropImage() {
        if (this.mOptions.noOutputImage) {
            setResult(null, null);
            return;
        }
        this.mCropImageView.saveCroppedImageAsync(getOutputUri(), this.mOptions.outputCompressFormat, this.mOptions.outputCompressQuality, this.mOptions.outputRequestWidth, this.mOptions.outputRequestHeight);
    }

    /* access modifiers changed from: protected */
    public void rotateImage(int degrees) {
        this.mCropImageView.rotateImage(degrees);
    }

    /* access modifiers changed from: protected */
    public Uri getOutputUri() {
        Uri outputUri = this.mOptions.outputUri;
        if (!outputUri.equals(Uri.EMPTY)) {
            return outputUri;
        }
        try {
            String ext = this.mOptions.outputCompressFormat == CompressFormat.JPEG ? ".jpg" : this.mOptions.outputCompressFormat == CompressFormat.PNG ? ".png" : ".wepb";
            return Uri.fromFile(File.createTempFile("cropped", ext, getCacheDir()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to create temp file for output image", e);
        }
    }

    /* access modifiers changed from: protected */
    public void setResult(Uri uri, Exception error) {
        setResult(error == null ? -1 : CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE, getResultIntent(uri, error));
        finish();
    }

    /* access modifiers changed from: protected */
    public void setResultCancel() {
        setResult(0);
        finish();
    }

    /* access modifiers changed from: protected */
    public Intent getResultIntent(Uri uri, Exception error) {
        ActivityResult result = new ActivityResult(uri, error, this.mCropImageView.getCropPoints(), this.mCropImageView.getCropRect(), this.mCropImageView.getRotatedDegrees());
        Intent intent = new Intent();
        intent.putExtra(CropImage.CROP_IMAGE_EXTRA_RESULT, result);
        return intent;
    }

    private void updateMenuItemIconColor(Menu menu, int itemId, int color) {
        MenuItem menuItem = menu.findItem(itemId);
        if (menuItem != null) {
            Drawable menuItemIcon = menuItem.getIcon();
            if (menuItemIcon != null) {
                try {
                    menuItemIcon.mutate();
                    menuItemIcon.setColorFilter(color, Mode.SRC_ATOP);
                    menuItem.setIcon(menuItemIcon);
                } catch (Exception e) {
                }
            }
        }
    }
}
