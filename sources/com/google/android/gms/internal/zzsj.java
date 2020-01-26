package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.SystemClock;
import com.google.android.gms.common.util.zzs;

public final class zzsj extends Drawable implements Callback {

    /* renamed from: CH */
    private boolean f776CH;

    /* renamed from: CM */
    private int f777CM;

    /* renamed from: CN */
    private int f778CN;

    /* renamed from: CO */
    private int f779CO;

    /* renamed from: CP */
    private int f780CP;

    /* renamed from: CQ */
    private int f781CQ;

    /* renamed from: CR */
    private boolean f782CR;

    /* renamed from: CS */
    private zzb f783CS;

    /* renamed from: CT */
    private Drawable f784CT;

    /* renamed from: CU */
    private Drawable f785CU;

    /* renamed from: CV */
    private boolean f786CV;

    /* renamed from: CW */
    private boolean f787CW;

    /* renamed from: CX */
    private boolean f788CX;

    /* renamed from: CY */
    private int f789CY;

    /* renamed from: eg */
    private long f790eg;
    private int mFrom;

    private static final class zza extends Drawable {
        /* access modifiers changed from: private */

        /* renamed from: CZ */
        public static final zza f791CZ = new zza();

        /* renamed from: Da */
        private static final C0756zza f792Da = new C0756zza();

        /* renamed from: com.google.android.gms.internal.zzsj$zza$zza reason: collision with other inner class name */
        private static final class C0756zza extends ConstantState {
            private C0756zza() {
            }

            public int getChangingConfigurations() {
                return 0;
            }

            public Drawable newDrawable() {
                return zza.f791CZ;
            }
        }

        private zza() {
        }

        public void draw(Canvas canvas) {
        }

        public ConstantState getConstantState() {
            return f792Da;
        }

        public int getOpacity() {
            return -2;
        }

        public void setAlpha(int i) {
        }

        public void setColorFilter(ColorFilter colorFilter) {
        }
    }

    static final class zzb extends ConstantState {

        /* renamed from: Db */
        int f793Db;
        int mChangingConfigurations;

        zzb(zzb zzb) {
            if (zzb != null) {
                this.mChangingConfigurations = zzb.mChangingConfigurations;
                this.f793Db = zzb.f793Db;
            }
        }

        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }

        public Drawable newDrawable() {
            return new zzsj(this);
        }
    }

    public zzsj(Drawable drawable, Drawable drawable2) {
        this(null);
        if (drawable == null) {
            drawable = zza.f791CZ;
        }
        this.f784CT = drawable;
        drawable.setCallback(this);
        this.f783CS.f793Db |= drawable.getChangingConfigurations();
        if (drawable2 == null) {
            drawable2 = zza.f791CZ;
        }
        this.f785CU = drawable2;
        drawable2.setCallback(this);
        this.f783CS.f793Db |= drawable2.getChangingConfigurations();
    }

    zzsj(zzb zzb2) {
        this.f777CM = 0;
        this.f779CO = 255;
        this.f781CQ = 0;
        this.f776CH = true;
        this.f783CS = new zzb(zzb2);
    }

    public boolean canConstantState() {
        if (!this.f786CV) {
            this.f787CW = (this.f784CT.getConstantState() == null || this.f785CU.getConstantState() == null) ? false : true;
            this.f786CV = true;
        }
        return this.f787CW;
    }

    public void draw(Canvas canvas) {
        boolean z = true;
        boolean z2 = false;
        switch (this.f777CM) {
            case 1:
                this.f790eg = SystemClock.uptimeMillis();
                this.f777CM = 2;
                break;
            case 2:
                if (this.f790eg >= 0) {
                    float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.f790eg)) / ((float) this.f780CP);
                    if (uptimeMillis < 1.0f) {
                        z = false;
                    }
                    if (z) {
                        this.f777CM = 0;
                    }
                    this.f781CQ = (int) ((Math.min(uptimeMillis, 1.0f) * ((float) (this.f778CN + 0))) + 0.0f);
                    break;
                }
                break;
        }
        z2 = z;
        int i = this.f781CQ;
        boolean z3 = this.f776CH;
        Drawable drawable = this.f784CT;
        Drawable drawable2 = this.f785CU;
        if (z2) {
            if (!z3 || i == 0) {
                drawable.draw(canvas);
            }
            if (i == this.f779CO) {
                drawable2.setAlpha(this.f779CO);
                drawable2.draw(canvas);
                return;
            }
            return;
        }
        if (z3) {
            drawable.setAlpha(this.f779CO - i);
        }
        drawable.draw(canvas);
        if (z3) {
            drawable.setAlpha(this.f779CO);
        }
        if (i > 0) {
            drawable2.setAlpha(i);
            drawable2.draw(canvas);
            drawable2.setAlpha(this.f779CO);
        }
        invalidateSelf();
    }

    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.f783CS.mChangingConfigurations | this.f783CS.f793Db;
    }

    public ConstantState getConstantState() {
        if (!canConstantState()) {
            return null;
        }
        this.f783CS.mChangingConfigurations = getChangingConfigurations();
        return this.f783CS;
    }

    public int getIntrinsicHeight() {
        return Math.max(this.f784CT.getIntrinsicHeight(), this.f785CU.getIntrinsicHeight());
    }

    public int getIntrinsicWidth() {
        return Math.max(this.f784CT.getIntrinsicWidth(), this.f785CU.getIntrinsicWidth());
    }

    public int getOpacity() {
        if (!this.f788CX) {
            this.f789CY = Drawable.resolveOpacity(this.f784CT.getOpacity(), this.f785CU.getOpacity());
            this.f788CX = true;
        }
        return this.f789CY;
    }

    @TargetApi(11)
    public void invalidateDrawable(Drawable drawable) {
        if (zzs.zzayn()) {
            Callback callback = getCallback();
            if (callback != null) {
                callback.invalidateDrawable(this);
            }
        }
    }

    public Drawable mutate() {
        if (!this.f782CR && super.mutate() == this) {
            if (!canConstantState()) {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
            this.f784CT.mutate();
            this.f785CU.mutate();
            this.f782CR = true;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        this.f784CT.setBounds(rect);
        this.f785CU.setBounds(rect);
    }

    @TargetApi(11)
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        if (zzs.zzayn()) {
            Callback callback = getCallback();
            if (callback != null) {
                callback.scheduleDrawable(this, runnable, j);
            }
        }
    }

    public void setAlpha(int i) {
        if (this.f781CQ == this.f779CO) {
            this.f781CQ = i;
        }
        this.f779CO = i;
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f784CT.setColorFilter(colorFilter);
        this.f785CU.setColorFilter(colorFilter);
    }

    public void startTransition(int i) {
        this.mFrom = 0;
        this.f778CN = this.f779CO;
        this.f781CQ = 0;
        this.f780CP = i;
        this.f777CM = 1;
        invalidateSelf();
    }

    @TargetApi(11)
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        if (zzs.zzayn()) {
            Callback callback = getCallback();
            if (callback != null) {
                callback.unscheduleDrawable(this, runnable);
            }
        }
    }

    public Drawable zzauw() {
        return this.f785CU;
    }
}
