package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.buffer.HorizontalBarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class HorizontalBarChartRenderer extends BarChartRenderer {
    public HorizontalBarChartRenderer(BarDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(chart, animator, viewPortHandler);
        this.mValuePaint.setTextAlign(Align.LEFT);
    }

    public void initBuffers() {
        BarData barData = this.mChart.getBarData();
        this.mBarBuffers = new HorizontalBarBuffer[barData.getDataSetCount()];
        for (int i = 0; i < this.mBarBuffers.length; i++) {
            IBarDataSet set = (IBarDataSet) barData.getDataSetByIndex(i);
            this.mBarBuffers[i] = new HorizontalBarBuffer((set.isStacked() ? set.getStackSize() : 1) * set.getEntryCount() * 4, barData.getDataSetCount(), set.isStacked());
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
        int j = 0;
        while (j < buffer.size() && this.mViewPortHandler.isInBoundsTop(buffer.buffer[j + 3])) {
            if (this.mViewPortHandler.isInBoundsBottom(buffer.buffer[j + 1])) {
                if (this.mChart.isDrawBarShadowEnabled()) {
                    c.drawRect(this.mViewPortHandler.contentLeft(), buffer.buffer[j + 1], this.mViewPortHandler.contentRight(), buffer.buffer[j + 3], this.mShadowPaint);
                }
                this.mRenderPaint.setColor(dataSet.getColor(j / 4));
                c.drawRect(buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2], buffer.buffer[j + 3], this.mRenderPaint);
                if (drawBorder) {
                    c.drawRect(buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2], buffer.buffer[j + 3], this.mBarBorderPaint);
                }
            }
            j += 4;
        }
    }

    public void drawValues(Canvas c) {
        float negOffset;
        float f;
        float y;
        float negOffset2;
        float f2;
        float negOffset3;
        float f3;
        if (isDrawingValuesAllowed(this.mChart)) {
            List<IBarDataSet> dataSets = this.mChart.getBarData().getDataSets();
            float valueOffsetPlus = Utils.convertDpToPixel(5.0f);
            boolean drawValueAboveBar = this.mChart.isDrawValueAboveBarEnabled();
            for (int i = 0; i < this.mChart.getBarData().getDataSetCount(); i++) {
                IBarDataSet dataSet = (IBarDataSet) dataSets.get(i);
                if (dataSet.isDrawValuesEnabled() && dataSet.getEntryCount() != 0) {
                    boolean isInverted = this.mChart.isInverted(dataSet.getAxisDependency());
                    applyValueTextStyle(dataSet);
                    float halfTextHeight = ((float) Utils.calcTextHeight(this.mValuePaint, "10")) / 2.0f;
                    ValueFormatter formatter = dataSet.getValueFormatter();
                    BarBuffer buffer = this.mBarBuffers[i];
                    if (!dataSet.isStacked()) {
                        for (int j = 0; ((float) j) < ((float) buffer.buffer.length) * this.mAnimator.getPhaseX(); j += 4) {
                            float y2 = (buffer.buffer[j + 1] + buffer.buffer[j + 3]) / 2.0f;
                            if (!this.mViewPortHandler.isInBoundsTop(buffer.buffer[j + 1])) {
                                break;
                            }
                            if (this.mViewPortHandler.isInBoundsX(buffer.buffer[j]) && this.mViewPortHandler.isInBoundsBottom(buffer.buffer[j + 1])) {
                                BarEntry e = (BarEntry) dataSet.getEntryForIndex(j / 4);
                                float val = e.getY();
                                String formattedValue = formatter.getFormattedValue(val, e, i, this.mViewPortHandler);
                                float valueTextWidth = (float) Utils.calcTextWidth(this.mValuePaint, formattedValue);
                                float posOffset = drawValueAboveBar ? valueOffsetPlus : -(valueTextWidth + valueOffsetPlus);
                                if (drawValueAboveBar) {
                                    negOffset3 = -(valueTextWidth + valueOffsetPlus);
                                } else {
                                    negOffset3 = valueOffsetPlus;
                                }
                                if (isInverted) {
                                    posOffset = (-posOffset) - valueTextWidth;
                                    negOffset3 = (-negOffset3) - valueTextWidth;
                                }
                                float f4 = buffer.buffer[j + 2];
                                if (val >= 0.0f) {
                                    f3 = posOffset;
                                } else {
                                    f3 = negOffset3;
                                }
                                drawValue(c, formattedValue, f4 + f3, y2 + halfTextHeight, dataSet.getValueTextColor(j / 2));
                            }
                        }
                    } else {
                        Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
                        int bufferIndex = 0;
                        int index = 0;
                        while (((float) index) < ((float) dataSet.getEntryCount()) * this.mAnimator.getPhaseX()) {
                            BarEntry e2 = (BarEntry) dataSet.getEntryForIndex(index);
                            int color = dataSet.getValueTextColor(index);
                            float[] vals = e2.getYVals();
                            if (vals == null) {
                                if (!this.mViewPortHandler.isInBoundsTop(buffer.buffer[bufferIndex + 1])) {
                                    break;
                                } else if (this.mViewPortHandler.isInBoundsX(buffer.buffer[bufferIndex]) && this.mViewPortHandler.isInBoundsBottom(buffer.buffer[bufferIndex + 1])) {
                                    String formattedValue2 = formatter.getFormattedValue(e2.getY(), e2, i, this.mViewPortHandler);
                                    float valueTextWidth2 = (float) Utils.calcTextWidth(this.mValuePaint, formattedValue2);
                                    float posOffset2 = drawValueAboveBar ? valueOffsetPlus : -(valueTextWidth2 + valueOffsetPlus);
                                    if (drawValueAboveBar) {
                                        negOffset2 = -(valueTextWidth2 + valueOffsetPlus);
                                    } else {
                                        negOffset2 = valueOffsetPlus;
                                    }
                                    if (isInverted) {
                                        posOffset2 = (-posOffset2) - valueTextWidth2;
                                        negOffset2 = (-negOffset2) - valueTextWidth2;
                                    }
                                    float f5 = buffer.buffer[bufferIndex + 2];
                                    if (e2.getY() >= 0.0f) {
                                        f2 = posOffset2;
                                    } else {
                                        f2 = negOffset2;
                                    }
                                    drawValue(c, formattedValue2, f5 + f2, buffer.buffer[bufferIndex + 1] + halfTextHeight, color);
                                }
                            } else {
                                float[] transformed = new float[(vals.length * 2)];
                                float posY = 0.0f;
                                float negY = -e2.getNegativeSum();
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
                                    transformed[k] = this.mAnimator.getPhaseY() * y;
                                    k += 2;
                                    idx++;
                                }
                                trans.pointValuesToPixel(transformed);
                                for (int k2 = 0; k2 < transformed.length; k2 += 2) {
                                    float val2 = vals[k2 / 2];
                                    String formattedValue3 = formatter.getFormattedValue(val2, e2, i, this.mViewPortHandler);
                                    float valueTextWidth3 = (float) Utils.calcTextWidth(this.mValuePaint, formattedValue3);
                                    float posOffset3 = drawValueAboveBar ? valueOffsetPlus : -(valueTextWidth3 + valueOffsetPlus);
                                    if (drawValueAboveBar) {
                                        negOffset = -(valueTextWidth3 + valueOffsetPlus);
                                    } else {
                                        negOffset = valueOffsetPlus;
                                    }
                                    if (isInverted) {
                                        posOffset3 = (-posOffset3) - valueTextWidth3;
                                        negOffset = (-negOffset) - valueTextWidth3;
                                    }
                                    float f6 = transformed[k2];
                                    if (val2 >= 0.0f) {
                                        f = posOffset3;
                                    } else {
                                        f = negOffset;
                                    }
                                    float x = f6 + f;
                                    float y3 = (buffer.buffer[bufferIndex + 1] + buffer.buffer[bufferIndex + 3]) / 2.0f;
                                    if (!this.mViewPortHandler.isInBoundsTop(y3)) {
                                        break;
                                    }
                                    if (this.mViewPortHandler.isInBoundsX(x) && this.mViewPortHandler.isInBoundsBottom(y3)) {
                                        drawValue(c, formattedValue3, x, y3 + halfTextHeight, color);
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

    /* access modifiers changed from: protected */
    public void drawValue(Canvas c, String valueText, float x, float y, int color) {
        this.mValuePaint.setColor(color);
        c.drawText(valueText, x, y, this.mValuePaint);
    }

    /* access modifiers changed from: protected */
    public void prepareBarHighlight(float x, float y1, float y2, float barWidthHalf, Transformer trans) {
        float left = y1;
        float right = y2;
        this.mBarRect.set(left, x - barWidthHalf, right, x + barWidthHalf);
        trans.rectToPixelPhaseHorizontal(this.mBarRect, this.mAnimator.getPhaseY());
    }

    /* access modifiers changed from: protected */
    public void setHighlightDrawPos(Highlight high, RectF bar) {
        high.setDraw(bar.centerY(), bar.right);
    }

    /* access modifiers changed from: protected */
    public boolean isDrawingValuesAllowed(ChartInterface chart) {
        return ((float) chart.getData().getEntryCount()) < ((float) chart.getMaxVisibleCount()) * this.mViewPortHandler.getScaleY();
    }
}
