package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.net.Uri;
import android.widget.ImageView;

public final class zzsk extends ImageView {

    /* renamed from: Dc */
    private Uri f794Dc;

    /* renamed from: Dd */
    private int f795Dd;

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public int zzauy() {
        return this.f795Dd;
    }

    public void zzgi(int i) {
        this.f795Dd = i;
    }

    public void zzr(Uri uri) {
        this.f794Dc = uri;
    }
}
