package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.p003v7.widget.helper.ItemTouchHelper.Callback;
import android.widget.ImageView;
import com.google.android.gms.common.images.ImageManager.OnImageLoadedListener;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.internal.zzsj;
import com.google.android.gms.internal.zzsk;
import com.google.android.gms.internal.zzsl;
import java.lang.ref.WeakReference;

public abstract class zza {

    /* renamed from: CD */
    final C0734zza f229CD;

    /* renamed from: CE */
    protected int f230CE = 0;

    /* renamed from: CF */
    protected int f231CF = 0;

    /* renamed from: CG */
    protected boolean f232CG = false;

    /* renamed from: CH */
    private boolean f233CH = true;

    /* renamed from: CI */
    private boolean f234CI = false;

    /* renamed from: CJ */
    private boolean f235CJ = true;

    /* renamed from: com.google.android.gms.common.images.zza$zza reason: collision with other inner class name */
    static final class C0734zza {
        public final Uri uri;

        public C0734zza(Uri uri2) {
            this.uri = uri2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof C0734zza)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            return zzz.equal(((C0734zza) obj).uri, this.uri);
        }

        public int hashCode() {
            return zzz.hashCode(this.uri);
        }
    }

    public static final class zzb extends zza {

        /* renamed from: CK */
        private WeakReference<ImageView> f236CK;

        public zzb(ImageView imageView, int i) {
            super(null, i);
            com.google.android.gms.common.internal.zzc.zzu(imageView);
            this.f236CK = new WeakReference<>(imageView);
        }

        public zzb(ImageView imageView, Uri uri) {
            super(uri, 0);
            com.google.android.gms.common.internal.zzc.zzu(imageView);
            this.f236CK = new WeakReference<>(imageView);
        }

        private void zza(ImageView imageView, Drawable drawable, boolean z, boolean z2, boolean z3) {
            boolean z4 = !z2 && !z3;
            if (z4 && (imageView instanceof zzsk)) {
                int zzauy = ((zzsk) imageView).zzauy();
                if (this.f231CF != 0 && zzauy == this.f231CF) {
                    return;
                }
            }
            boolean zzc = zzc(z, z2);
            Drawable drawable2 = zzc ? zza(imageView.getDrawable(), drawable) : drawable;
            imageView.setImageDrawable(drawable2);
            if (imageView instanceof zzsk) {
                zzsk zzsk = (zzsk) imageView;
                zzsk.zzr(z3 ? this.f229CD.uri : null);
                zzsk.zzgi(z4 ? this.f231CF : 0);
            }
            if (zzc) {
                ((zzsj) drawable2).startTransition(Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zzb)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            ImageView imageView = (ImageView) this.f236CK.get();
            ImageView imageView2 = (ImageView) ((zzb) obj).f236CK.get();
            return (imageView2 == null || imageView == null || !zzz.equal(imageView2, imageView)) ? false : true;
        }

        public int hashCode() {
            return 0;
        }

        /* access modifiers changed from: protected */
        public void zza(Drawable drawable, boolean z, boolean z2, boolean z3) {
            ImageView imageView = (ImageView) this.f236CK.get();
            if (imageView != null) {
                zza(imageView, drawable, z, z2, z3);
            }
        }
    }

    public static final class zzc extends zza {

        /* renamed from: CL */
        private WeakReference<OnImageLoadedListener> f237CL;

        public zzc(OnImageLoadedListener onImageLoadedListener, Uri uri) {
            super(uri, 0);
            com.google.android.gms.common.internal.zzc.zzu(onImageLoadedListener);
            this.f237CL = new WeakReference<>(onImageLoadedListener);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zzc)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zzc zzc = (zzc) obj;
            OnImageLoadedListener onImageLoadedListener = (OnImageLoadedListener) this.f237CL.get();
            OnImageLoadedListener onImageLoadedListener2 = (OnImageLoadedListener) zzc.f237CL.get();
            return onImageLoadedListener2 != null && onImageLoadedListener != null && zzz.equal(onImageLoadedListener2, onImageLoadedListener) && zzz.equal(zzc.f229CD, this.f229CD);
        }

        public int hashCode() {
            return zzz.hashCode(this.f229CD);
        }

        /* access modifiers changed from: protected */
        public void zza(Drawable drawable, boolean z, boolean z2, boolean z3) {
            if (!z2) {
                OnImageLoadedListener onImageLoadedListener = (OnImageLoadedListener) this.f237CL.get();
                if (onImageLoadedListener != null) {
                    onImageLoadedListener.onImageLoaded(this.f229CD.uri, drawable, z3);
                }
            }
        }
    }

    public zza(Uri uri, int i) {
        this.f229CD = new C0734zza(uri);
        this.f231CF = i;
    }

    private Drawable zza(Context context, zzsl zzsl, int i) {
        return context.getResources().getDrawable(i);
    }

    /* access modifiers changed from: protected */
    public zzsj zza(Drawable drawable, Drawable drawable2) {
        if (drawable == null) {
            drawable = null;
        } else if (drawable instanceof zzsj) {
            drawable = ((zzsj) drawable).zzauw();
        }
        return new zzsj(drawable, drawable2);
    }

    /* access modifiers changed from: 0000 */
    public void zza(Context context, Bitmap bitmap, boolean z) {
        com.google.android.gms.common.internal.zzc.zzu(bitmap);
        zza(new BitmapDrawable(context.getResources(), bitmap), z, false, true);
    }

    /* access modifiers changed from: 0000 */
    public void zza(Context context, zzsl zzsl) {
        if (this.f235CJ) {
            zza(null, false, true, false);
        }
    }

    /* access modifiers changed from: 0000 */
    public void zza(Context context, zzsl zzsl, boolean z) {
        Drawable drawable = null;
        if (this.f231CF != 0) {
            drawable = zza(context, zzsl, this.f231CF);
        }
        zza(drawable, z, false, false);
    }

    /* access modifiers changed from: protected */
    public abstract void zza(Drawable drawable, boolean z, boolean z2, boolean z3);

    /* access modifiers changed from: protected */
    public boolean zzc(boolean z, boolean z2) {
        return this.f233CH && !z2 && !z;
    }

    public void zzgg(int i) {
        this.f231CF = i;
    }
}
