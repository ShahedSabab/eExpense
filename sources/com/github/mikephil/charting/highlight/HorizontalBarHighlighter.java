package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.DataSet.Rounding;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.MPPointD;

public class HorizontalBarHighlighter extends BarHighlighter {
    public HorizontalBarHighlighter(BarDataProvider chart) {
        super(chart);
    }

    public Highlight getHighlight(float x, float y) {
        BarData barData = ((BarDataProvider) this.mChart).getBarData();
        MPPointD pos = getValsForTouch(y, x);
        Highlight high = getHighlightForX((float) pos.f71y, y, x);
        if (high == null) {
            return null;
        }
        IBarDataSet set = (IBarDataSet) barData.getDataSetByIndex(high.getDataSetIndex());
        if (set.isStacked()) {
            return getStackedHighlight(high, set, (float) pos.f71y, (float) pos.f70x);
        }
        MPPointD.recycleInstance(pos);
        return high;
    }

    /* access modifiers changed from: protected */
    public Highlight buildHighlight(IDataSet set, int dataSetIndex, float xVal, Rounding rounding) {
        Entry e = set.getEntryForXPos(xVal, rounding);
        MPPointD pixels = ((BarDataProvider) this.mChart).getTransformer(set.getAxisDependency()).getPixelsForValues(e.getY(), e.getX());
        return new Highlight(e.getX(), e.getY(), (float) pixels.f70x, (float) pixels.f71y, dataSetIndex, set.getAxisDependency());
    }

    /* access modifiers changed from: protected */
    public float getDistance(float x1, float y1, float x2, float y2) {
        return Math.abs(y1 - y2);
    }
}
