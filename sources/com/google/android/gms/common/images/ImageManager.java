package com.google.android.gms.common.images;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.p000v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.zzsl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ImageManager {
    /* access modifiers changed from: private */

    /* renamed from: Co */
    public static final Object f209Co = new Object();
    /* access modifiers changed from: private */

    /* renamed from: Cp */
    public static HashSet<Uri> f210Cp = new HashSet<>();

    /* renamed from: Cq */
    private static ImageManager f211Cq;

    /* renamed from: Cr */
    private static ImageManager f212Cr;
    /* access modifiers changed from: private */

    /* renamed from: Cs */
    public final ExecutorService f213Cs = Executors.newFixedThreadPool(4);
    /* access modifiers changed from: private */

    /* renamed from: Ct */
    public final zzb f214Ct;
    /* access modifiers changed from: private */

    /* renamed from: Cu */
    public final zzsl f215Cu;
    /* access modifiers changed from: private */

    /* renamed from: Cv */
    public final Map<zza, ImageReceiver> f216Cv;
    /* access modifiers changed from: private */

    /* renamed from: Cw */
    public final Map<Uri, ImageReceiver> f217Cw;
    /* access modifiers changed from: private */

    /* renamed from: Cx */
    public final Map<Uri, Long> f218Cx;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler(Looper.getMainLooper());

    @KeepName
    private final class ImageReceiver extends ResultReceiver {
        /* access modifiers changed from: private */

        /* renamed from: Cy */
        public final ArrayList<zza> f219Cy = new ArrayList<>();
        private final Uri mUri;

        ImageReceiver(Uri uri) {
            super(new Handler(Looper.getMainLooper()));
            this.mUri = uri;
        }

        public void onReceiveResult(int i, Bundle bundle) {
            ImageManager.this.f213Cs.execute(new zzc(this.mUri, (ParcelFileDescriptor) bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }

        public void zzauv() {
            Intent intent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
            intent.putExtra("com.google.android.gms.extras.uri", this.mUri);
            intent.putExtra("com.google.android.gms.extras.resultReceiver", this);
            intent.putExtra("com.google.android.gms.extras.priority", 3);
            ImageManager.this.mContext.sendBroadcast(intent);
        }

        public void zzb(zza zza) {
            com.google.android.gms.common.internal.zzc.zzhs("ImageReceiver.addImageRequest() must be called in the main thread");
            this.f219Cy.add(zza);
        }

        public void zzc(zza zza) {
            com.google.android.gms.common.internal.zzc.zzhs("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.f219Cy.remove(zza);
        }
    }

    public interface OnImageLoadedListener {
        void onImageLoaded(Uri uri, Drawable drawable, boolean z);
    }

    @TargetApi(11)
    private static final class zza {
        static int zza(ActivityManager activityManager) {
            return activityManager.getLargeMemoryClass();
        }
    }

    private static final class zzb extends LruCache<C0734zza, Bitmap> {
        public zzb(Context context) {
            super(zzbz(context));
        }

        @TargetApi(11)
        private static int zzbz(Context context) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            return (int) (((float) (((!((context.getApplicationInfo().flags & 1048576) != 0) || !zzs.zzayn()) ? activityManager.getMemoryClass() : zza.zza(activityManager)) * 1048576)) * 0.33f);
        }

        /* access modifiers changed from: protected */
        /* renamed from: zza */
        public int sizeOf(C0734zza zza, Bitmap bitmap) {
            return bitmap.getHeight() * bitmap.getRowBytes();
        }

        /* access modifiers changed from: protected */
        /* renamed from: zza */
        public void entryRemoved(boolean z, C0734zza zza, Bitmap bitmap, Bitmap bitmap2) {
            super.entryRemoved(z, zza, bitmap, bitmap2);
        }
    }

    private final class zzc implements Runnable {

        /* renamed from: CA */
        private final ParcelFileDescriptor f221CA;
        private final Uri mUri;

        public zzc(Uri uri, ParcelFileDescriptor parcelFileDescriptor) {
            this.mUri = uri;
            this.f221CA = parcelFileDescriptor;
        }

        public void run() {
            com.google.android.gms.common.internal.zzc.zzht("LoadBitmapFromDiskRunnable can't be executed in the main thread");
            boolean z = false;
            Bitmap bitmap = null;
            if (this.f221CA != null) {
                try {
                    bitmap = BitmapFactory.decodeFileDescriptor(this.f221CA.getFileDescriptor());
                } catch (OutOfMemoryError e) {
                    String valueOf = String.valueOf(this.mUri);
                    Log.e("ImageManager", new StringBuilder(String.valueOf(valueOf).length() + 34).append("OOM while loading bitmap for uri: ").append(valueOf).toString(), e);
                    z = true;
                }
                try {
                    this.f221CA.close();
                } catch (IOException e2) {
                    Log.e("ImageManager", "closed failed", e2);
                }
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            ImageManager.this.mHandler.post(new zzf(this.mUri, bitmap, z, countDownLatch));
            try {
                countDownLatch.await();
            } catch (InterruptedException e3) {
                String valueOf2 = String.valueOf(this.mUri);
                Log.w("ImageManager", new StringBuilder(String.valueOf(valueOf2).length() + 32).append("Latch interrupted while posting ").append(valueOf2).toString());
            }
        }
    }

    private final class zzd implements Runnable {

        /* renamed from: CB */
        private final zza f223CB;

        public zzd(zza zza) {
            this.f223CB = zza;
        }

        public void run() {
            com.google.android.gms.common.internal.zzc.zzhs("LoadImageRunnable must be executed on the main thread");
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.f216Cv.get(this.f223CB);
            if (imageReceiver != null) {
                ImageManager.this.f216Cv.remove(this.f223CB);
                imageReceiver.zzc(this.f223CB);
            }
            C0734zza zza = this.f223CB.f229CD;
            if (zza.uri == null) {
                this.f223CB.zza(ImageManager.this.mContext, ImageManager.this.f215Cu, true);
                return;
            }
            Bitmap zza2 = ImageManager.this.zza(zza);
            if (zza2 != null) {
                this.f223CB.zza(ImageManager.this.mContext, zza2, true);
                return;
            }
            Long l = (Long) ImageManager.this.f218Cx.get(zza.uri);
            if (l != null) {
                if (SystemClock.elapsedRealtime() - l.longValue() < 3600000) {
                    this.f223CB.zza(ImageManager.this.mContext, ImageManager.this.f215Cu, true);
                    return;
                }
                ImageManager.this.f218Cx.remove(zza.uri);
            }
            this.f223CB.zza(ImageManager.this.mContext, ImageManager.this.f215Cu);
            ImageReceiver imageReceiver2 = (ImageReceiver) ImageManager.this.f217Cw.get(zza.uri);
            if (imageReceiver2 == null) {
                imageReceiver2 = new ImageReceiver(zza.uri);
                ImageManager.this.f217Cw.put(zza.uri, imageReceiver2);
            }
            imageReceiver2.zzb(this.f223CB);
            if (!(this.f223CB instanceof com.google.android.gms.common.images.zza.zzc)) {
                ImageManager.this.f216Cv.put(this.f223CB, imageReceiver2);
            }
            synchronized (ImageManager.f209Co) {
                if (!ImageManager.f210Cp.contains(zza.uri)) {
                    ImageManager.f210Cp.add(zza.uri);
                    imageReceiver2.zzauv();
                }
            }
        }
    }

    @TargetApi(14)
    private static final class zze implements ComponentCallbacks2 {

        /* renamed from: Ct */
        private final zzb f225Ct;

        public zze(zzb zzb) {
            this.f225Ct = zzb;
        }

        public void onConfigurationChanged(Configuration configuration) {
        }

        public void onLowMemory() {
            this.f225Ct.evictAll();
        }

        public void onTrimMemory(int i) {
            if (i >= 60) {
                this.f225Ct.evictAll();
            } else if (i >= 20) {
                this.f225Ct.trimToSize(this.f225Ct.size() / 2);
            }
        }
    }

    private final class zzf implements Runnable {

        /* renamed from: CC */
        private boolean f226CC;
        private final Bitmap mBitmap;
        private final Uri mUri;
        private final CountDownLatch zzank;

        public zzf(Uri uri, Bitmap bitmap, boolean z, CountDownLatch countDownLatch) {
            this.mUri = uri;
            this.mBitmap = bitmap;
            this.f226CC = z;
            this.zzank = countDownLatch;
        }

        private void zza(ImageReceiver imageReceiver, boolean z) {
            ArrayList zza = imageReceiver.f219Cy;
            int size = zza.size();
            for (int i = 0; i < size; i++) {
                zza zza2 = (zza) zza.get(i);
                if (z) {
                    zza2.zza(ImageManager.this.mContext, this.mBitmap, false);
                } else {
                    ImageManager.this.f218Cx.put(this.mUri, Long.valueOf(SystemClock.elapsedRealtime()));
                    zza2.zza(ImageManager.this.mContext, ImageManager.this.f215Cu, false);
                }
                if (!(zza2 instanceof com.google.android.gms.common.images.zza.zzc)) {
                    ImageManager.this.f216Cv.remove(zza2);
                }
            }
        }

        public void run() {
            com.google.android.gms.common.internal.zzc.zzhs("OnBitmapLoadedRunnable must be executed in the main thread");
            boolean z = this.mBitmap != null;
            if (ImageManager.this.f214Ct != null) {
                if (this.f226CC) {
                    ImageManager.this.f214Ct.evictAll();
                    System.gc();
                    this.f226CC = false;
                    ImageManager.this.mHandler.post(this);
                    return;
                } else if (z) {
                    ImageManager.this.f214Ct.put(new C0734zza(this.mUri), this.mBitmap);
                }
            }
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.f217Cw.remove(this.mUri);
            if (imageReceiver != null) {
                zza(imageReceiver, z);
            }
            this.zzank.countDown();
            synchronized (ImageManager.f209Co) {
                ImageManager.f210Cp.remove(this.mUri);
            }
        }
    }

    private ImageManager(Context context, boolean z) {
        this.mContext = context.getApplicationContext();
        if (z) {
            this.f214Ct = new zzb(this.mContext);
            if (zzs.zzayq()) {
                zzaut();
            }
        } else {
            this.f214Ct = null;
        }
        this.f215Cu = new zzsl();
        this.f216Cv = new HashMap();
        this.f217Cw = new HashMap();
        this.f218Cx = new HashMap();
    }

    public static ImageManager create(Context context) {
        return zzg(context, false);
    }

    /* access modifiers changed from: private */
    public Bitmap zza(C0734zza zza2) {
        if (this.f214Ct == null) {
            return null;
        }
        return (Bitmap) this.f214Ct.get(zza2);
    }

    @TargetApi(14)
    private void zzaut() {
        this.mContext.registerComponentCallbacks(new zze(this.f214Ct));
    }

    public static ImageManager zzg(Context context, boolean z) {
        if (z) {
            if (f212Cr == null) {
                f212Cr = new ImageManager(context, true);
            }
            return f212Cr;
        }
        if (f211Cq == null) {
            f211Cq = new ImageManager(context, false);
        }
        return f211Cq;
    }

    public void loadImage(ImageView imageView, int i) {
        zza((zza) new com.google.android.gms.common.images.zza.zzb(imageView, i));
    }

    public void loadImage(ImageView imageView, Uri uri) {
        zza((zza) new com.google.android.gms.common.images.zza.zzb(imageView, uri));
    }

    public void loadImage(ImageView imageView, Uri uri, int i) {
        com.google.android.gms.common.images.zza.zzb zzb2 = new com.google.android.gms.common.images.zza.zzb(imageView, uri);
        zzb2.zzgg(i);
        zza((zza) zzb2);
    }

    public void loadImage(OnImageLoadedListener onImageLoadedListener, Uri uri) {
        zza((zza) new com.google.android.gms.common.images.zza.zzc(onImageLoadedListener, uri));
    }

    public void loadImage(OnImageLoadedListener onImageLoadedListener, Uri uri, int i) {
        com.google.android.gms.common.images.zza.zzc zzc2 = new com.google.android.gms.common.images.zza.zzc(onImageLoadedListener, uri);
        zzc2.zzgg(i);
        zza((zza) zzc2);
    }

    public void zza(zza zza2) {
        com.google.android.gms.common.internal.zzc.zzhs("ImageManager.loadImage() must be called in the main thread");
        new zzd(zza2).run();
    }
}
