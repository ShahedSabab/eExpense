package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.Range;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class BarChartRenderer extends BarLineScatterCandleBubbleRenderer {
    protected Paint mBarBorderPaint;
    protected BarBuffer[] mBarBuffers;
    protected RectF mBarRect = new RectF();
    protected BarDataProvider mChart;
    protected Paint mShadowPaint;

    public BarChartRenderer(BarDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.mChart = chart;
        this.mHighlightPaint = new Paint(1);
        this.mHighlightPaint.setStyle(Style.FILL);
        this.mHighlightPaint.setColor(Color.rgb(0, 0, 0));
        this.mHighlightPaint.setAlpha(120);
        this.mShadowPaint = new Paint(1);
        this.mShadowPaint.setStyle(Style.FILL);
        this.mBarBorderPaint = new Paint(1);
        this.mBarBorderPaint.setStyle(Style.STROKE);
    }

    public void initBuffers() {
        BarData barData = this.mChart.getBarData();
        this.mBarBuffers = new BarBuffer[barData.getDataSetCount()];
        for (int i = 0; i < this.mBarBuffers.length; i++) {
            IBarDataSet set = (IBarDataSet) barData.getDataSetByIndex(i);
            this.mBarBuffers[i] = new BarBuffer((set.isStacked() ? set.getStackSize() : 1) * set.getEntryCount() * 4, barData.getDataSetCount(), set.isStacked());
        }
    }

    public void drawData(Canvas c) {
        BarData barData = this.mChart.getBarData();
        for (int i = 0; i < barData.getDataSetCount(); i++) {
            IBarDataSet set = (IBarDataSet) barData.getDataSetByIndex(i);
            if (set.isVisible() && set.getEntryCount() > 0) {
                drawDataSet(c, set, i);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void drawDataSet(Canvas c, IBarDataSet dataSet, int index) {
        Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
        this.mShadowPaint.setColor(dataSet.getBarShadowColor());
        this.mBarBorderPaint.setColor(dataSet.getBarBorderColor());
        this.mBarBorderPaint.setStrokeWidth(Utils.convertDpToPixel(dataSet.getBarBorderWidth()));
        boolean drawBorder = dataSet.getBarBorderWidth() > 0.0f;
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        BarBuffer buffer = this.mBarBuffers[index];
        buffer.setPhases(phaseX, phaseY);
        buffer.setDataSet(index);
        buffer.setInverted(this.mChart.isInverted(dataSet.getAxisDependency()));
        buffer.setBarWidth(this.mChart.getBarData().getBarWidth());
        buffer.feed(dataSet);
        trans.pointValuesToPixel(buffer.buffer);
        if (this.mChart.isDrawBarShadowEnabled()) {
            for (int j = 0; j < buffer.size(); j += 4) {
                if (this.mViewPortHandler.isInBoundsLeft(buffer.buffer[j + 2])) {
                    if (!this.mViewPortHandler.isInBoundsRight(buffer.buffer[j])) {
                        break;
                    }
                    c.drawRect(buffer.buffer[j], this.mViewPortHandler.contentTop(), buffer.buffer[j + 2], this.mViewPortHandler.contentBottom(), this.mShadowPaint);
                }
            }
        }
        if (dataSet.getColors().size() > 1) {
            for (int j2 = 0; j2 < buffer.size(); j2 += 4) {
                if (this.mViewPortHandler.isInBoundsLeft(buffer.buffer[j2 + 2])) {
                    if (this.mViewPortHandler.isInBoundsRight(buffer.buffer[j2])) {
                        this.mRenderPaint.setColor(dataSet.getColor(j2 / 4));
                        c.drawRect(buffer.buffer[j2], buffer.buffer[j2 + 1], buffer.buffer[j2 + 2], buffer.buffer[j2 + 3], this.mRenderPaint);
                        if (drawBorder) {
                            c.drawRect(buffer.buffer[j2], buffer.buffer[j2 + 1], buffer.buffer[j2 + 2], buffer.buffer[j2 + 3], this.mBarBorderPaint);
                        }
                    } else {
                        return;
                    }
                }
            }
            return;
        }
        this.mRenderPaint.setColor(dataSet.getColor());
        for (int j3 = 0; j3 < buffer.size(); j3 += 4) {
            if (this.mViewPortHandler.isInBoundsLeft(buffer.buffer[j3 + 2])) {
                if (this.mViewPortHandler.isInBoundsRight(buffer.buffer[j3])) {
                    c.drawRect(buffer.buffer[j3], buffer.buffer[j3 + 1], buffer.buffer[j3 + 2], buffer.buffer[j3 + 3], this.mRenderPaint);
                    if (drawBorder) {
                        c.drawRect(buffer.buffer[j3], buffer.buffer[j3 + 1], buffer.buffer[j3 + 2], buffer.buffer[j3 + 3], this.mBarBorderPaint);
                    }
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void prepareBarHighlight(float x, float y1, float y2, float barWidthHalf, Transformer trans) {
        float top = y1;
        this.mBarRect.set(x - barWidthHalf, top, x + barWidthHalf, y2);
        trans.rectToPixelPhase(this.mBarRect, this.mAnimator.getPhaseY());
    }

    public void drawValues(Canvas c) {
        float negOffset;
        float f;
        float y;
        if (isDrawingValuesAllowed(this.mChart)) {
            List<IBarDataSet> dataSets = this.mChart.getBarData().getDataSets();
            float valueOffsetPlus = Utils.convertDpToPixel(4.5f);
            boolean drawValueAboveBar = this.mChart.isDrawValueAboveBarEnabled();
            for (int i = 0; i < this.mChart.getBarData().getDataSetCount(); i++) {
                IBarDataSet dataSet = (IBarDataSet) dataSets.get(i);
                if (dataSet.isDrawValuesEnabled() && dataSet.getEntryCount() != 0) {
                    applyValueTextStyle(dataSet);
                    boolean isInverted = this.mChart.isInverted(dataSet.getAxisDependency());
                    float valueTextHeight = (float) Utils.calcTextHeight(this.mValuePaint, "8");
                    float posOffset = drawValueAboveBar ? -valueOffsetPlus : valueTextHeight + valueOffsetPlus;
                    if (drawValueAboveBar) {
                        negOffset = valueTextHeight + valueOffsetPlus;
                    } else {
                        negOffset = -valueOffsetPlus;
                    }
                    if (isInverted) {
                        posOffset = (-posOffset) - valueTextHeight;
                        negOffset = (-negOffset) - valueTextHeight;
                    }
                    BarBuffer buffer = this.mBarBuffers[i];
                    if (!dataSet.isStacked()) {
                        for (int j = 0; ((float) j) < ((float) buffer.buffer.length) * this.mAnimator.getPhaseX(); j += 4) {
                            float x = (buffer.buffer[j] + buffer.buffer[j + 2]) / 2.0f;
                            if (!this.mViewPortHandler.isInBoundsRight(x)) {
                                break;
                            }
                            if (this.mViewPortHandler.isInBoundsY(buffer.buffer[j + 1]) && this.mViewPortHandler.isInBoundsLeft(x)) {
                                BarEntry entry = (BarEntry) dataSet.getEntryForIndex(j / 4);
                                float val = entry.getY();
                                drawValue(c, dataSet.getValueFormatter(), val, entry, i, x, val >= 0.0f ? buffer.buffer[j + 1] + posOffset : buffer.buffer[j + 3] + negOffset, dataSet.getValueTextColor(j / 4));
                            }
                        }
                    } else {
                        Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
                        int bufferIndex = 0;
                        int index = 0;
                        while (((float) index) < ((float) dataSet.getEntryCount()) * this.mAnimator.getPhaseX()) {
                            BarEntry entry2 = (BarEntry) dataSet.getEntryForIndex(index);
                            float[] vals = entry2.getYVals();
                            float x2 = (buffer.buffer[bufferIndex] + buffer.buffer[bufferIndex + 2]) / 2.0f;
                            int color = dataSet.getValueTextColor(index);
                            if (vals == null) {
                                if (!this.mViewPortHandler.isInBoundsRight(x2)) {
                                    break;
                                } else if (this.mViewPortHandler.isInBoundsY(buffer.buffer[bufferIndex + 1]) && this.mViewPortHandler.isInBoundsLeft(x2)) {
                                    drawValue(c, dataSet.getValueFormatter(), entry2.getY(), entry2, i, x2, buffer.buffer[bufferIndex + 1] + (entry2.getY() >= 0.0f ? posOffset : negOffset), color);
                                }
                            } else {
                                float[] transformed = new float[(vals.length * 2)];
                                float posY = 0.0f;
                                float negY = -entry2.getNegativeSum();
                                int k = 0;
                                int idx = 0;
                                while (k < transformed.length) {
                                    float value = vals[idx];
                                    if (value >= 0.0f) {
                                        posY += value;
                                        y = posY;
                                    } else {
                                        y = negY;
                                        negY -= value;
                                    }
                                    transformed[k + 1] = this.mAnimator.getPhaseY() * y;
                                    k += 2;
                                    idx++;
                                }
                                trans.pointValuesToPixel(transformed);
                                for (int k2 = 0; k2 < transformed.length; k2 += 2) {
                                    float f2 = transformed[k2 + 1];
                                    if (vals[k2 / 2] >= 0.0f) {
                                        f = posOffset;
                                    } else {
                                        f = negOffset;
                                    }
                                    float y2 = f2 + f;
                                    if (!this.mViewPortHandler.isInBoundsRight(x2)) {
                                        break;
                                    }
                                    if (this.mViewPortHandler.isInBoundsY(y2) && this.mViewPortHandler.isInBoundsLeft(x2)) {
                                        drawValue(c, dataSet.getValueFormatter(), vals[k2 / 2], entry2, i, x2, y2, color);
                                    }
                                }
                            }
                            bufferIndex = vals == null ? bufferIndex + 4 : bufferIndex + (vals.length * 4);
                            index++;
                        }
                    }
                }
            }
        }
    }

    public void drawHighlighted(Canvas c, Highlight[] indices) {
        float y1;
        float y2;
        BarData barData = this.mChart.getBarData();
        for (Highlight high : indices) {
            IBarDataSet set = (IBarDataSet) barData.getDataSetByIndex(high.getDataSetIndex());
            if (set != null && set.isHighlightEnabled()) {
                BarEntry e = (BarEntry) set.getEntryForXPos(high.getX());
                if (isInBoundsX(e, set)) {
                    Transformer trans = this.mChart.getTransformer(set.getAxisDependency());
                    this.mHighlightPaint.setColor(set.getHighLightColor());
                    this.mHighlightPaint.setAlpha(set.getHighLightAlpha());
                    if (!(high.getStackIndex() >= 0 && e.isStacked())) {
                        y1 = e.getY();
                        y2 = 0.0f;
                    } else if (this.mChart.isHighlightFullBarEnabled()) {
                        y1 = e.getPositiveSum();
                        y2 = -e.getNegativeSum();
                    } else {
                        Range range = e.getRanges()[high.getStackIndex()];
                        y1 = range.from;
                        y2 = range.f61to;
                    }
                    prepareBarHighlight(e.getX(), y1, y2, barData.getBarWidth() / 2.0f, trans);
                    setHighlightDrawPos(high, this.mBarRect);
                    c.drawRect(this.mBarRect, this.mHighlightPaint);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setHighlightDrawPos(Highlight high, RectF bar) {
        high.setDraw(bar.centerX(), bar.top);
    }

    public void drawExtras(Canvas c) {
    }
}
