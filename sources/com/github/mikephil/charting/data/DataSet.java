package com.github.mikephil.charting.data;

import com.github.mikephil.charting.data.Entry;
import java.util.ArrayList;
import java.util.List;

public abstract class DataSet<T extends Entry> extends BaseDataSet<T> {
    protected List<T> mValues = null;
    protected float mXMax = -3.4028235E38f;
    protected float mXMin = Float.MAX_VALUE;
    protected float mYMax = -3.4028235E38f;
    protected float mYMin = Float.MAX_VALUE;

    public enum Rounding {
        UP,
        DOWN,
        CLOSEST
    }

    public abstract DataSet<T> copy();

    public DataSet(List<T> values, String label) {
        super(label);
        this.mValues = values;
        if (this.mValues == null) {
            this.mValues = new ArrayList();
        }
        calcMinMax();
    }

    public void calcMinMax() {
        if (this.mValues != null && !this.mValues.isEmpty()) {
            this.mYMax = -3.4028235E38f;
            this.mYMin = Float.MAX_VALUE;
            this.mXMax = -3.4028235E38f;
            this.mXMin = Float.MAX_VALUE;
            for (T e : this.mValues) {
                calcMinMax(e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void calcMinMax(T e) {
        if (e != null) {
            if (e.getY() < this.mYMin) {
                this.mYMin = e.getY();
            }
            if (e.getY() > this.mYMax) {
                this.mYMax = e.getY();
            }
            if (e.getX() < this.mXMin) {
                this.mXMin = e.getX();
            }
            if (e.getX() > this.mXMax) {
                this.mXMax = e.getX();
            }
        }
    }

    public int getEntryCount() {
        return this.mValues.size();
    }

    public List<T> getValues() {
        return this.mValues;
    }

    public void setValues(List<T> values) {
        this.mValues = values;
        notifyDataSetChanged();
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(toSimpleString());
        for (int i = 0; i < this.mValues.size(); i++) {
            buffer.append(((Entry) this.mValues.get(i)).toString() + " ");
        }
        return buffer.toString();
    }

    public String toSimpleString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("DataSet, label: " + (getLabel() == null ? "" : getLabel()) + ", entries: " + this.mValues.size() + "\n");
        return buffer.toString();
    }

    public float getYMin() {
        return this.mYMin;
    }

    public float getYMax() {
        return this.mYMax;
    }

    public float getXMin() {
        return this.mXMin;
    }

    public float getXMax() {
        return this.mXMax;
    }

    public void addEntryOrdered(T e) {
        if (e != null) {
            if (this.mValues == null) {
                this.mValues = new ArrayList();
            }
            calcMinMax(e);
            if (this.mValues.size() <= 0 || ((Entry) this.mValues.get(this.mValues.size() - 1)).getX() <= e.getX()) {
                this.mValues.add(e);
                return;
            }
            this.mValues.add(getEntryIndex(e.getX(), Rounding.UP), e);
        }
    }

    public void clear() {
        this.mValues.clear();
        notifyDataSetChanged();
    }

    public boolean addEntry(T e) {
        if (e == null) {
            return false;
        }
        List<T> values = getValues();
        if (values == null) {
            values = new ArrayList<>();
        }
        calcMinMax(e);
        return values.add(e);
    }

    public boolean removeEntry(T e) {
        boolean removed = false;
        if (!(e == null || this.mValues == null)) {
            removed = this.mValues.remove(e);
            if (removed) {
                calcMinMax();
            }
        }
        return removed;
    }

    public int getEntryIndex(Entry e) {
        return this.mValues.indexOf(e);
    }

    public T getEntryForXPos(float xPos, Rounding rounding) {
        int index = getEntryIndex(xPos, rounding);
        if (index > -1) {
            return (Entry) this.mValues.get(index);
        }
        return null;
    }

    public T getEntryForXPos(float xPos) {
        return getEntryForXPos(xPos, Rounding.CLOSEST);
    }

    public T getEntryForIndex(int index) {
        return (Entry) this.mValues.get(index);
    }

    public int getEntryIndex(float xPos, Rounding rounding) {
        if (this.mValues == null || this.mValues.isEmpty()) {
            return -1;
        }
        int low = 0;
        int high = this.mValues.size() - 1;
        while (low < high) {
            int m = (low + high) / 2;
            if (Math.abs(((Entry) this.mValues.get(m + 1)).getX() - xPos) <= Math.abs(((Entry) this.mValues.get(m)).getX() - xPos)) {
                low = m + 1;
            } else {
                high = m;
            }
        }
        if (high == -1) {
            return high;
        }
        float closestXPos = ((Entry) this.mValues.get(high)).getX();
        if (rounding == Rounding.UP) {
            if (closestXPos >= xPos || high >= this.mValues.size() - 1) {
                return high;
            }
            return high + 1;
        } else if (rounding != Rounding.DOWN || closestXPos <= xPos || high <= 0) {
            return high;
        } else {
            return high - 1;
        }
    }

    public List<T> getEntriesForXPos(float xVal) {
        List<T> entries = new ArrayList<>();
        int low = 0;
        int high = this.mValues.size() - 1;
        while (true) {
            if (low > high) {
                break;
            }
            int m = (high + low) / 2;
            T entry = (Entry) this.mValues.get(m);
            if (xVal == entry.getX()) {
                while (m > 0 && ((Entry) this.mValues.get(m - 1)).getX() == xVal) {
                    m--;
                }
                int high2 = this.mValues.size();
                while (m < high2) {
                    T entry2 = (Entry) this.mValues.get(m);
                    if (entry2.getX() != xVal) {
                        break;
                    }
                    entries.add(entry2);
                    m++;
                }
            } else if (xVal > entry.getX()) {
                low = m + 1;
            } else {
                high = m - 1;
            }
        }
        return entries;
    }
}
