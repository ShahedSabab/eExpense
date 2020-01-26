package com.google.android.gms.vision;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import com.google.android.gms.common.images.Size;
import java.io.IOException;
import java.lang.Thread.State;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CameraSource {
    @SuppressLint({"InlinedApi"})
    public static final int CAMERA_FACING_BACK = 0;
    @SuppressLint({"InlinedApi"})
    public static final int CAMERA_FACING_FRONT = 1;
    /* access modifiers changed from: private */
    public final Object aNc;
    /* access modifiers changed from: private */
    public Camera aNd;
    /* access modifiers changed from: private */
    public int aNe;
    /* access modifiers changed from: private */
    public Size aNf;
    /* access modifiers changed from: private */
    public float aNg;
    /* access modifiers changed from: private */
    public int aNh;
    /* access modifiers changed from: private */
    public int aNi;
    /* access modifiers changed from: private */
    public boolean aNj;
    private SurfaceView aNk;
    private SurfaceTexture aNl;
    private boolean aNm;
    /* access modifiers changed from: private */
    public Thread aNn;
    /* access modifiers changed from: private */
    public zzb aNo;
    /* access modifiers changed from: private */
    public Map<byte[], ByteBuffer> aNp;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public int zzbzf;

    public static class Builder {
        private final Detector<?> aNq;
        private CameraSource aNr = new CameraSource();

        public Builder(Context context, Detector<?> detector) {
            if (context == null) {
                throw new IllegalArgumentException("No context supplied.");
            } else if (detector == null) {
                throw new IllegalArgumentException("No detector supplied.");
            } else {
                this.aNq = detector;
                this.aNr.mContext = context;
            }
        }

        public CameraSource build() {
            CameraSource cameraSource = this.aNr;
            CameraSource cameraSource2 = this.aNr;
            cameraSource2.getClass();
            cameraSource.aNo = new zzb(this.aNq);
            return this.aNr;
        }

        public Builder setAutoFocusEnabled(boolean z) {
            this.aNr.aNj = z;
            return this;
        }

        public Builder setFacing(int i) {
            if (i == 0 || i == 1) {
                this.aNr.aNe = i;
                return this;
            }
            throw new IllegalArgumentException("Invalid camera: " + i);
        }

        public Builder setRequestedFps(float f) {
            if (f <= 0.0f) {
                throw new IllegalArgumentException("Invalid fps: " + f);
            }
            this.aNr.aNg = f;
            return this;
        }

        public Builder setRequestedPreviewSize(int i, int i2) {
            if (i <= 0 || i > 1000000 || i2 <= 0 || i2 > 1000000) {
                throw new IllegalArgumentException("Invalid preview size: " + i + "x" + i2);
            }
            this.aNr.aNh = i;
            this.aNr.aNi = i2;
            return this;
        }
    }

    public interface PictureCallback {
        void onPictureTaken(byte[] bArr);
    }

    public interface ShutterCallback {
        void onShutter();
    }

    private class zza implements PreviewCallback {
        private zza() {
        }

        public void onPreviewFrame(byte[] bArr, Camera camera) {
            CameraSource.this.aNo.zza(bArr, camera);
        }
    }

    private class zzb implements Runnable {
        static final /* synthetic */ boolean $assertionsDisabled = (!CameraSource.class.desiredAssertionStatus());
        private Detector<?> aNq;
        private boolean aNt = true;
        private long aNu;
        private int aNv = 0;
        private ByteBuffer aNw;

        /* renamed from: eg */
        private long f846eg = SystemClock.elapsedRealtime();
        private final Object zzako = new Object();

        zzb(Detector<?> detector) {
            this.aNq = detector;
        }

        /* access modifiers changed from: 0000 */
        @SuppressLint({"Assert"})
        public void release() {
            if ($assertionsDisabled || CameraSource.this.aNn.getState() == State.TERMINATED) {
                this.aNq.release();
                this.aNq = null;
                return;
            }
            throw new AssertionError();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
            r6.aNq.receiveFrame(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0078, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
            android.util.Log.e("CameraSource", "Exception thrown from receiver.", r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x008f, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x0090, code lost:
            com.google.android.gms.vision.CameraSource.zzb(r6.aNs).addCallbackBuffer(r2.array());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x009d, code lost:
            throw r0;
         */
        /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
        @android.annotation.SuppressLint({"InlinedApi"})
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r6 = this;
            L_0x0000:
                java.lang.Object r1 = r6.zzako
                monitor-enter(r1)
            L_0x0003:
                boolean r0 = r6.aNt     // Catch:{ all -> 0x0021 }
                if (r0 == 0) goto L_0x001b
                java.nio.ByteBuffer r0 = r6.aNw     // Catch:{ all -> 0x0021 }
                if (r0 != 0) goto L_0x001b
                java.lang.Object r0 = r6.zzako     // Catch:{ InterruptedException -> 0x0011 }
                r0.wait()     // Catch:{ InterruptedException -> 0x0011 }
                goto L_0x0003
            L_0x0011:
                r0 = move-exception
                java.lang.String r2 = "CameraSource"
                java.lang.String r3 = "Frame processing loop terminated."
                android.util.Log.d(r2, r3, r0)     // Catch:{ all -> 0x0021 }
                monitor-exit(r1)     // Catch:{ all -> 0x0021 }
            L_0x001a:
                return
            L_0x001b:
                boolean r0 = r6.aNt     // Catch:{ all -> 0x0021 }
                if (r0 != 0) goto L_0x0024
                monitor-exit(r1)     // Catch:{ all -> 0x0021 }
                goto L_0x001a
            L_0x0021:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0021 }
                throw r0
            L_0x0024:
                com.google.android.gms.vision.Frame$Builder r0 = new com.google.android.gms.vision.Frame$Builder     // Catch:{ all -> 0x0021 }
                r0.<init>()     // Catch:{ all -> 0x0021 }
                java.nio.ByteBuffer r2 = r6.aNw     // Catch:{ all -> 0x0021 }
                com.google.android.gms.vision.CameraSource r3 = com.google.android.gms.vision.CameraSource.this     // Catch:{ all -> 0x0021 }
                com.google.android.gms.common.images.Size r3 = r3.aNf     // Catch:{ all -> 0x0021 }
                int r3 = r3.getWidth()     // Catch:{ all -> 0x0021 }
                com.google.android.gms.vision.CameraSource r4 = com.google.android.gms.vision.CameraSource.this     // Catch:{ all -> 0x0021 }
                com.google.android.gms.common.images.Size r4 = r4.aNf     // Catch:{ all -> 0x0021 }
                int r4 = r4.getHeight()     // Catch:{ all -> 0x0021 }
                r5 = 17
                com.google.android.gms.vision.Frame$Builder r0 = r0.setImageData(r2, r3, r4, r5)     // Catch:{ all -> 0x0021 }
                int r2 = r6.aNv     // Catch:{ all -> 0x0021 }
                com.google.android.gms.vision.Frame$Builder r0 = r0.setId(r2)     // Catch:{ all -> 0x0021 }
                long r2 = r6.aNu     // Catch:{ all -> 0x0021 }
                com.google.android.gms.vision.Frame$Builder r0 = r0.setTimestampMillis(r2)     // Catch:{ all -> 0x0021 }
                com.google.android.gms.vision.CameraSource r2 = com.google.android.gms.vision.CameraSource.this     // Catch:{ all -> 0x0021 }
                int r2 = r2.zzbzf     // Catch:{ all -> 0x0021 }
                com.google.android.gms.vision.Frame$Builder r0 = r0.setRotation(r2)     // Catch:{ all -> 0x0021 }
                com.google.android.gms.vision.Frame r0 = r0.build()     // Catch:{ all -> 0x0021 }
                java.nio.ByteBuffer r2 = r6.aNw     // Catch:{ all -> 0x0021 }
                r3 = 0
                r6.aNw = r3     // Catch:{ all -> 0x0021 }
                monitor-exit(r1)     // Catch:{ all -> 0x0021 }
                com.google.android.gms.vision.Detector<?> r1 = r6.aNq     // Catch:{ Throwable -> 0x0078 }
                r1.receiveFrame(r0)     // Catch:{ Throwable -> 0x0078 }
                com.google.android.gms.vision.CameraSource r0 = com.google.android.gms.vision.CameraSource.this
                android.hardware.Camera r0 = r0.aNd
                byte[] r1 = r2.array()
                r0.addCallbackBuffer(r1)
                goto L_0x0000
            L_0x0078:
                r0 = move-exception
                java.lang.String r1 = "CameraSource"
                java.lang.String r3 = "Exception thrown from receiver."
                android.util.Log.e(r1, r3, r0)     // Catch:{ all -> 0x008f }
                com.google.android.gms.vision.CameraSource r0 = com.google.android.gms.vision.CameraSource.this
                android.hardware.Camera r0 = r0.aNd
                byte[] r1 = r2.array()
                r0.addCallbackBuffer(r1)
                goto L_0x0000
            L_0x008f:
                r0 = move-exception
                com.google.android.gms.vision.CameraSource r1 = com.google.android.gms.vision.CameraSource.this
                android.hardware.Camera r1 = r1.aNd
                byte[] r2 = r2.array()
                r1.addCallbackBuffer(r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.vision.CameraSource.zzb.run():void");
        }

        /* access modifiers changed from: 0000 */
        public void setActive(boolean z) {
            synchronized (this.zzako) {
                this.aNt = z;
                this.zzako.notifyAll();
            }
        }

        /* access modifiers changed from: 0000 */
        public void zza(byte[] bArr, Camera camera) {
            synchronized (this.zzako) {
                if (this.aNw != null) {
                    camera.addCallbackBuffer(this.aNw.array());
                    this.aNw = null;
                }
                if (!CameraSource.this.aNp.containsKey(bArr)) {
                    Log.d("CameraSource", "Skipping frame. Could not find ByteBuffer associated with the image data from the camera.");
                    return;
                }
                this.aNu = SystemClock.elapsedRealtime() - this.f846eg;
                this.aNv++;
                this.aNw = (ByteBuffer) CameraSource.this.aNp.get(bArr);
                this.zzako.notifyAll();
            }
        }
    }

    private class zzc implements android.hardware.Camera.PictureCallback {
        /* access modifiers changed from: private */
        public PictureCallback aNx;

        private zzc() {
        }

        public void onPictureTaken(byte[] bArr, Camera camera) {
            if (this.aNx != null) {
                this.aNx.onPictureTaken(bArr);
            }
            synchronized (CameraSource.this.aNc) {
                if (CameraSource.this.aNd != null) {
                    CameraSource.this.aNd.startPreview();
                }
            }
        }
    }

    private class zzd implements android.hardware.Camera.ShutterCallback {
        /* access modifiers changed from: private */
        public ShutterCallback aNy;

        private zzd() {
        }

        public void onShutter() {
            if (this.aNy != null) {
                this.aNy.onShutter();
            }
        }
    }

    static class zze {
        private Size aNA;
        private Size aNz;

        public zze(Camera.Size size, Camera.Size size2) {
            this.aNz = new Size(size.width, size.height);
            if (size2 != null) {
                this.aNA = new Size(size2.width, size2.height);
            }
        }

        public Size zzcll() {
            return this.aNz;
        }

        public Size zzclm() {
            return this.aNA;
        }
    }

    private CameraSource() {
        this.aNc = new Object();
        this.aNe = 0;
        this.aNg = 30.0f;
        this.aNh = 1024;
        this.aNi = 768;
        this.aNj = false;
        this.aNp = new HashMap();
    }

    static zze zza(Camera camera, int i, int i2) {
        int i3;
        zze zze2;
        zze zze3 = null;
        int i4 = Integer.MAX_VALUE;
        for (zze zze4 : zza(camera)) {
            Size zzcll = zze4.zzcll();
            int abs = Math.abs(zzcll.getHeight() - i2) + Math.abs(zzcll.getWidth() - i);
            if (abs < i4) {
                int i5 = abs;
                zze2 = zze4;
                i3 = i5;
            } else {
                i3 = i4;
                zze2 = zze3;
            }
            i4 = i3;
            zze3 = zze2;
        }
        return zze3;
    }

    static List<zze> zza(Camera camera) {
        Parameters parameters = camera.getParameters();
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        List supportedPictureSizes = parameters.getSupportedPictureSizes();
        ArrayList arrayList = new ArrayList();
        for (Camera.Size size : supportedPreviewSizes) {
            float f = ((float) size.width) / ((float) size.height);
            Iterator it = supportedPictureSizes.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Camera.Size size2 = (Camera.Size) it.next();
                if (Math.abs(f - (((float) size2.width) / ((float) size2.height))) < 0.01f) {
                    arrayList.add(new zze(size, size2));
                    break;
                }
            }
        }
        if (arrayList.size() == 0) {
            Log.w("CameraSource", "No preview sizes have a corresponding same-aspect-ratio picture size");
            for (Camera.Size zze2 : supportedPreviewSizes) {
                arrayList.add(new zze(zze2, null));
            }
        }
        return arrayList;
    }

    private void zza(Camera camera, Parameters parameters, int i) {
        int i2;
        int i3;
        int i4;
        int rotation = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getRotation();
        switch (rotation) {
            case 0:
                i2 = 0;
                break;
            case 1:
                i2 = 90;
                break;
            case 2:
                i2 = 180;
                break;
            case 3:
                i2 = 270;
                break;
            default:
                Log.e("CameraSource", "Bad rotation value: " + rotation);
                i2 = 0;
                break;
        }
        CameraInfo cameraInfo = new CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        if (cameraInfo.facing == 1) {
            i4 = (i2 + cameraInfo.orientation) % 360;
            i3 = (360 - i4) % 360;
        } else {
            i3 = ((cameraInfo.orientation - i2) + 360) % 360;
            i4 = i3;
        }
        this.zzbzf = i4 / 90;
        camera.setDisplayOrientation(i3);
        parameters.setRotation(i4);
    }

    @SuppressLint({"InlinedApi"})
    private byte[] zza(Size size) {
        byte[] bArr = new byte[(((int) Math.ceil(((double) ((long) (ImageFormat.getBitsPerPixel(17) * (size.getHeight() * size.getWidth())))) / 8.0d)) + 1)];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        if (!wrap.hasArray() || wrap.array() != bArr) {
            throw new IllegalStateException("Failed to create valid buffer for camera source.");
        }
        this.aNp.put(bArr, wrap);
        return bArr;
    }

    @SuppressLint({"InlinedApi"})
    static int[] zza(Camera camera, float f) {
        int i;
        int[] iArr;
        int i2 = (int) (1000.0f * f);
        int[] iArr2 = null;
        int i3 = Integer.MAX_VALUE;
        for (int[] iArr3 : camera.getParameters().getSupportedPreviewFpsRange()) {
            int abs = Math.abs(i2 - iArr3[0]) + Math.abs(i2 - iArr3[1]);
            if (abs < i3) {
                int i4 = abs;
                iArr = iArr3;
                i = i4;
            } else {
                i = i3;
                iArr = iArr2;
            }
            i3 = i;
            iArr2 = iArr;
        }
        return iArr2;
    }

    private static int zzaap(int i) {
        CameraInfo cameraInfo = new CameraInfo();
        for (int i2 = 0; i2 < Camera.getNumberOfCameras(); i2++) {
            Camera.getCameraInfo(i2, cameraInfo);
            if (cameraInfo.facing == i) {
                return i2;
            }
        }
        return -1;
    }

    @SuppressLint({"InlinedApi"})
    private Camera zzclk() {
        int zzaap = zzaap(this.aNe);
        if (zzaap == -1) {
            throw new RuntimeException("Could not find requested camera.");
        }
        Camera open = Camera.open(zzaap);
        zze zza2 = zza(open, this.aNh, this.aNi);
        if (zza2 == null) {
            throw new RuntimeException("Could not find suitable preview size.");
        }
        Size zzclm = zza2.zzclm();
        this.aNf = zza2.zzcll();
        int[] zza3 = zza(open, this.aNg);
        if (zza3 == null) {
            throw new RuntimeException("Could not find suitable preview frames per second range.");
        }
        Parameters parameters = open.getParameters();
        if (zzclm != null) {
            parameters.setPictureSize(zzclm.getWidth(), zzclm.getHeight());
        }
        parameters.setPreviewSize(this.aNf.getWidth(), this.aNf.getHeight());
        parameters.setPreviewFpsRange(zza3[0], zza3[1]);
        parameters.setPreviewFormat(17);
        zza(open, parameters, zzaap);
        if (this.aNj) {
            if (parameters.getSupportedFocusModes().contains("continuous-video")) {
                parameters.setFocusMode("continuous-video");
            } else {
                Log.i("CameraSource", "Camera auto focus is not supported on this device.");
            }
        }
        open.setParameters(parameters);
        open.setPreviewCallbackWithBuffer(new zza());
        open.addCallbackBuffer(zza(this.aNf));
        open.addCallbackBuffer(zza(this.aNf));
        open.addCallbackBuffer(zza(this.aNf));
        open.addCallbackBuffer(zza(this.aNf));
        return open;
    }

    public int getCameraFacing() {
        return this.aNe;
    }

    public Size getPreviewSize() {
        return this.aNf;
    }

    public void release() {
        synchronized (this.aNc) {
            stop();
            this.aNo.release();
        }
    }

    @RequiresPermission("android.permission.CAMERA")
    public CameraSource start() throws IOException {
        synchronized (this.aNc) {
            if (this.aNd == null) {
                this.aNd = zzclk();
                if (VERSION.SDK_INT >= 11) {
                    this.aNl = new SurfaceTexture(100);
                    this.aNd.setPreviewTexture(this.aNl);
                    this.aNm = true;
                } else {
                    this.aNk = new SurfaceView(this.mContext);
                    this.aNd.setPreviewDisplay(this.aNk.getHolder());
                    this.aNm = false;
                }
                this.aNd.startPreview();
                this.aNn = new Thread(this.aNo);
                this.aNo.setActive(true);
                this.aNn.start();
            }
        }
        return this;
    }

    @RequiresPermission("android.permission.CAMERA")
    public CameraSource start(SurfaceHolder surfaceHolder) throws IOException {
        synchronized (this.aNc) {
            if (this.aNd == null) {
                this.aNd = zzclk();
                this.aNd.setPreviewDisplay(surfaceHolder);
                this.aNd.startPreview();
                this.aNn = new Thread(this.aNo);
                this.aNo.setActive(true);
                this.aNn.start();
                this.aNm = false;
            }
        }
        return this;
    }

    public void stop() {
        synchronized (this.aNc) {
            this.aNo.setActive(false);
            if (this.aNn != null) {
                try {
                    this.aNn.join();
                } catch (InterruptedException e) {
                    Log.d("CameraSource", "Frame processing thread interrupted on release.");
                }
                this.aNn = null;
            }
            if (this.aNd != null) {
                this.aNd.stopPreview();
                this.aNd.setPreviewCallbackWithBuffer(null);
                try {
                    if (this.aNm) {
                        this.aNd.setPreviewTexture(null);
                    } else {
                        this.aNd.setPreviewDisplay(null);
                    }
                } catch (Exception e2) {
                    String str = "CameraSource";
                    String valueOf = String.valueOf(e2);
                    Log.e(str, new StringBuilder(String.valueOf(valueOf).length() + 32).append("Failed to clear camera preview: ").append(valueOf).toString());
                }
                this.aNd.release();
                this.aNd = null;
            }
            this.aNp.clear();
        }
        return;
    }

    public void takePicture(ShutterCallback shutterCallback, PictureCallback pictureCallback) {
        synchronized (this.aNc) {
            if (this.aNd != null) {
                zzd zzd2 = new zzd();
                zzd2.aNy = shutterCallback;
                zzc zzc2 = new zzc();
                zzc2.aNx = pictureCallback;
                this.aNd.takePicture(zzd2, null, null, zzc2);
            }
        }
    }
}
