package com.github.mikephil.charting.data;

public abstract class BaseEntry {
    private Object mData;

    /* renamed from: y */
    private float f54y;

    public BaseEntry() {
        this.f54y = 0.0f;
        this.mData = null;
    }

    public BaseEntry(float y) {
        this.f54y = 0.0f;
        this.mData = null;
        this.f54y = y;
    }

    public BaseEntry(float y, Object data) {
        this(y);
        this.mData = data;
    }

    public float getY() {
        return this.f54y;
    }

    public void setY(float y) {
        this.f54y = y;
    }

    public Object getData() {
        return this.mData;
    }

    public void setData(Object data) {
        this.mData = data;
    }
}
