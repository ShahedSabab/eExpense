package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.components.YAxis.AxisDependency;

public class Highlight {
    private AxisDependency axis;
    private int mDataIndex;
    private int mDataSetIndex;
    private float mDrawX;
    private float mDrawY;
    private int mStackIndex;

    /* renamed from: mX */
    private float f59mX;
    private float mXPx;

    /* renamed from: mY */
    private float f60mY;
    private float mYPx;

    public Highlight(float x, int dataSetIndex) {
        this.f59mX = Float.NaN;
        this.f60mY = Float.NaN;
        this.mDataIndex = -1;
        this.mStackIndex = -1;
        this.f59mX = x;
        this.mDataSetIndex = dataSetIndex;
    }

    public Highlight(float x, int dataSetIndex, int stackIndex) {
        this(x, dataSetIndex);
        this.mStackIndex = stackIndex;
    }

    protected Highlight(float x, float y, float xPx, float yPx, int dataSetIndex, AxisDependency axis2) {
        this.f59mX = Float.NaN;
        this.f60mY = Float.NaN;
        this.mDataIndex = -1;
        this.mStackIndex = -1;
        this.f59mX = x;
        this.f60mY = y;
        this.mXPx = xPx;
        this.mYPx = yPx;
        this.mDataSetIndex = dataSetIndex;
        this.axis = axis2;
    }

    protected Highlight(float x, float y, float xPx, float yPx, int dataSetIndex, int stackIndex, AxisDependency axis2) {
        this(x, y, xPx, yPx, dataSetIndex, axis2);
        this.mStackIndex = stackIndex;
    }

    public float getX() {
        return this.f59mX;
    }

    public float getY() {
        return this.f60mY;
    }

    public float getXPx() {
        return this.mXPx;
    }

    public float getYPx() {
        return this.mYPx;
    }

    public int getDataIndex() {
        return this.mDataIndex;
    }

    public void setDataIndex(int mDataIndex2) {
        this.mDataIndex = mDataIndex2;
    }

    public int getDataSetIndex() {
        return this.mDataSetIndex;
    }

    public int getStackIndex() {
        return this.mStackIndex;
    }

    public boolean isStacked() {
        return this.mStackIndex >= 0;
    }

    public AxisDependency getAxis() {
        return this.axis;
    }

    public void setDraw(float x, float y) {
        this.mDrawX = x;
        this.mDrawY = y;
    }

    public float getDrawX() {
        return this.mDrawX;
    }

    public float getDrawY() {
        return this.mDrawY;
    }

    public boolean equalTo(Highlight h) {
        if (h != null && this.mDataSetIndex == h.mDataSetIndex && this.f59mX == h.f59mX && this.mStackIndex == h.mStackIndex && this.mDataIndex == h.mDataIndex) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "Highlight, x: " + this.f59mX + ", y: " + this.f60mY + ", dataSetIndex: " + this.mDataSetIndex + ", stackIndex (only stacked barentry): " + this.mStackIndex;
    }
}
