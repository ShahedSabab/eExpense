package com.github.mikephil.charting.utils;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.github.mikephil.charting.utils.ObjectPool.Poolable;
import java.util.List;

public class MPPointF extends Poolable {
    public static final Creator<MPPointF> CREATOR = new Creator<MPPointF>() {
        public MPPointF createFromParcel(Parcel in) {
            MPPointF r = new MPPointF(0.0f, 0.0f);
            r.my_readFromParcel(in);
            return r;
        }

        public MPPointF[] newArray(int size) {
            return new MPPointF[size];
        }
    };
    private static ObjectPool<MPPointF> pool = ObjectPool.create(32, new MPPointF(0.0f, 0.0f));

    /* renamed from: x */
    public float f72x;

    /* renamed from: y */
    public float f73y;

    static {
        pool.setReplenishPercentage(0.5f);
    }

    private MPPointF(float x, float y) {
        this.f72x = x;
        this.f73y = y;
    }

    public static MPPointF getInstance(float x, float y) {
        MPPointF result = (MPPointF) pool.get();
        result.f72x = x;
        result.f73y = y;
        return result;
    }

    public static void recycleInstance(MPPointF instance) {
        pool.recycle(instance);
    }

    public static void recycleInstances(List<MPPointF> instances) {
        pool.recycle(instances);
    }

    public void my_readFromParcel(Parcel in) {
        this.f72x = in.readFloat();
        this.f73y = in.readFloat();
    }

    public float getX() {
        return this.f72x;
    }

    public float getY() {
        return this.f73y;
    }

    /* access modifiers changed from: protected */
    public Poolable instantiate() {
        return new MPPointF(0.0f, 0.0f);
    }
}
