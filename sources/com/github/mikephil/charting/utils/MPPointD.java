package com.github.mikephil.charting.utils;

import com.github.mikephil.charting.utils.ObjectPool.Poolable;
import java.util.List;

public class MPPointD extends Poolable {
    private static ObjectPool<MPPointD> pool = ObjectPool.create(64, new MPPointD(0.0d, 0.0d));

    /* renamed from: x */
    public double f70x;

    /* renamed from: y */
    public double f71y;

    static {
        pool.setReplenishPercentage(0.5f);
    }

    public static MPPointD getInstance(double x, double y) {
        MPPointD result = (MPPointD) pool.get();
        result.f70x = x;
        result.f71y = y;
        return result;
    }

    public static void recycleInstance(MPPointD instance) {
        pool.recycle(instance);
    }

    public static void recycleInstances(List<MPPointD> instances) {
        pool.recycle(instances);
    }

    /* access modifiers changed from: protected */
    public Poolable instantiate() {
        return new MPPointD(0.0d, 0.0d);
    }

    private MPPointD(double x, double y) {
        this.f70x = x;
        this.f71y = y;
    }

    public String toString() {
        return "MPPointD, x: " + this.f70x + ", y: " + this.f71y;
    }
}
