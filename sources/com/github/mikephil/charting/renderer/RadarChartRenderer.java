package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class RadarChartRenderer extends LineRadarRenderer {
    protected RadarChart mChart;
    protected Path mDrawDataSetSurfacePathBuffer = new Path();
    protected Path mDrawHighlightCirclePathBuffer = new Path();
    protected Paint mHighlightCirclePaint;
    protected Paint mWebPaint;

    public RadarChartRenderer(RadarChart chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.mChart = chart;
        this.mHighlightPaint = new Paint(1);
        this.mHighlightPaint.setStyle(Style.STROKE);
        this.mHighlightPaint.setStrokeWidth(2.0f);
        this.mHighlightPaint.setColor(Color.rgb(255, 187, 115));
        this.mWebPaint = new Paint(1);
        this.mWebPaint.setStyle(Style.STROKE);
        this.mHighlightCirclePaint = new Paint(1);
    }

    public Paint getWebPaint() {
        return this.mWebPaint;
    }

    public void initBuffers() {
    }

    public void drawData(Canvas c) {
        RadarData radarData = (RadarData) this.mChart.getData();
        int mostEntries = ((IRadarDataSet) radarData.getMaxEntryCountSet()).getEntryCount();
        List<IRadarDataSet> dataSets = radarData.getDataSets();
        int setCount = dataSets.size();
        for (int i = 0; i < setCount; i++) {
            IRadarDataSet set = (IRadarDataSet) dataSets.get(i);
            if (set.isVisible() && set.getEntryCount() > 0) {
                drawDataSet(c, set, mostEntries);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void drawDataSet(Canvas c, IRadarDataSet dataSet, int mostEntries) {
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        float sliceangle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF center = this.mChart.getCenterOffsets();
        MPPointF pOut = MPPointF.getInstance(0.0f, 0.0f);
        Path surface = this.mDrawDataSetSurfacePathBuffer;
        surface.reset();
        boolean hasMovedToPoint = false;
        for (int j = 0; j < dataSet.getEntryCount(); j++) {
            this.mRenderPaint.setColor(dataSet.getColor(j));
            Utils.getPosition(center, (((RadarEntry) dataSet.getEntryForIndex(j)).getY() - this.mChart.getYChartMin()) * factor * phaseY, (((float) j) * sliceangle * phaseX) + this.mChart.getRotationAngle(), pOut);
            if (!Float.isNaN(pOut.f72x)) {
                if (!hasMovedToPoint) {
                    surface.moveTo(pOut.f72x, pOut.f73y);
                    hasMovedToPoint = true;
                } else {
                    surface.lineTo(pOut.f72x, pOut.f73y);
                }
            }
        }
        if (dataSet.getEntryCount() > mostEntries) {
            surface.lineTo(center.f72x, center.f73y);
        }
        surface.close();
        if (dataSet.isDrawFilledEnabled()) {
            Drawable drawable = dataSet.getFillDrawable();
            if (drawable != null) {
                drawFilledPath(c, surface, drawable);
            } else {
                drawFilledPath(c, surface, dataSet.getFillColor(), dataSet.getFillAlpha());
            }
        }
        this.mRenderPaint.setStrokeWidth(dataSet.getLineWidth());
        this.mRenderPaint.setStyle(Style.STROKE);
        if (!dataSet.isDrawFilledEnabled() || dataSet.getFillAlpha() < 255) {
            c.drawPath(surface, this.mRenderPaint);
        }
        MPPointF.recycleInstance(center);
        MPPointF.recycleInstance(pOut);
    }

    public void drawValues(Canvas c) {
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        float sliceangle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF center = this.mChart.getCenterOffsets();
        MPPointF pOut = MPPointF.getInstance(0.0f, 0.0f);
        float yoffset = Utils.convertDpToPixel(5.0f);
        for (int i = 0; i < ((RadarData) this.mChart.getData()).getDataSetCount(); i++) {
            IRadarDataSet dataSet = (IRadarDataSet) ((RadarData) this.mChart.getData()).getDataSetByIndex(i);
            if (dataSet.isDrawValuesEnabled() && dataSet.getEntryCount() != 0) {
                applyValueTextStyle(dataSet);
                for (int j = 0; j < dataSet.getEntryCount(); j++) {
                    RadarEntry entry = (RadarEntry) dataSet.getEntryForIndex(j);
                    Utils.getPosition(center, (entry.getY() - this.mChart.getYChartMin()) * factor * phaseY, (((float) j) * sliceangle * phaseX) + this.mChart.getRotationAngle(), pOut);
                    drawValue(c, dataSet.getValueFormatter(), entry.getY(), entry, i, pOut.f72x, pOut.f73y - yoffset, dataSet.getValueTextColor(j));
                }
            }
        }
        MPPointF.recycleInstance(center);
        MPPointF.recycleInstance(pOut);
    }

    public void drawExtras(Canvas c) {
        drawWeb(c);
    }

    /* access modifiers changed from: protected */
    public void drawWeb(Canvas c) {
        float sliceangle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        float rotationangle = this.mChart.getRotationAngle();
        MPPointF center = this.mChart.getCenterOffsets();
        this.mWebPaint.setStrokeWidth(this.mChart.getWebLineWidth());
        this.mWebPaint.setColor(this.mChart.getWebColor());
        this.mWebPaint.setAlpha(this.mChart.getWebAlpha());
        int xIncrements = this.mChart.getSkipWebLineCount() + 1;
        int maxEntryCount = ((IRadarDataSet) ((RadarData) this.mChart.getData()).getMaxEntryCountSet()).getEntryCount();
        MPPointF p = MPPointF.getInstance(0.0f, 0.0f);
        for (int i = 0; i < maxEntryCount; i += xIncrements) {
            Utils.getPosition(center, this.mChart.getYRange() * factor, (((float) i) * sliceangle) + rotationangle, p);
            c.drawLine(center.f72x, center.f73y, p.f72x, p.f73y, this.mWebPaint);
        }
        MPPointF.recycleInstance(p);
        this.mWebPaint.setStrokeWidth(this.mChart.getWebLineWidthInner());
        this.mWebPaint.setColor(this.mChart.getWebColorInner());
        this.mWebPaint.setAlpha(this.mChart.getWebAlpha());
        int labelCount = this.mChart.getYAxis().mEntryCount;
        MPPointF p1out = MPPointF.getInstance(0.0f, 0.0f);
        MPPointF p2out = MPPointF.getInstance(0.0f, 0.0f);
        for (int j = 0; j < labelCount; j++) {
            for (int i2 = 0; i2 < ((RadarData) this.mChart.getData()).getEntryCount(); i2++) {
                float r = (this.mChart.getYAxis().mEntries[j] - this.mChart.getYChartMin()) * factor;
                Utils.getPosition(center, r, (((float) i2) * sliceangle) + rotationangle, p1out);
                Utils.getPosition(center, r, (((float) (i2 + 1)) * sliceangle) + rotationangle, p2out);
                c.drawLine(p1out.f72x, p1out.f73y, p2out.f72x, p2out.f73y, this.mWebPaint);
            }
        }
        MPPointF.recycleInstance(p1out);
        MPPointF.recycleInstance(p2out);
    }

    public void drawHighlighted(Canvas c, Highlight[] indices) {
        float sliceangle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF center = this.mChart.getCenterOffsets();
        MPPointF pOut = MPPointF.getInstance(0.0f, 0.0f);
        RadarData radarData = (RadarData) this.mChart.getData();
        int length = indices.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < length) {
                Highlight high = indices[i2];
                IRadarDataSet set = (IRadarDataSet) radarData.getDataSetByIndex(high.getDataSetIndex());
                if (set != null && set.isHighlightEnabled()) {
                    RadarEntry e = (RadarEntry) set.getEntryForIndex((int) high.getX());
                    if (isInBoundsX(e, set)) {
                        Utils.getPosition(center, (e.getY() - this.mChart.getYChartMin()) * factor * this.mAnimator.getPhaseY(), (high.getX() * sliceangle * this.mAnimator.getPhaseX()) + this.mChart.getRotationAngle(), pOut);
                        high.setDraw(pOut.f72x, pOut.f73y);
                        drawHighlightLines(c, pOut.f72x, pOut.f73y, set);
                        if (set.isDrawHighlightCircleEnabled() && !Float.isNaN(pOut.f72x) && !Float.isNaN(pOut.f73y)) {
                            int strokeColor = set.getHighlightCircleStrokeColor();
                            if (strokeColor == 1122867) {
                                strokeColor = set.getColor(0);
                            }
                            if (set.getHighlightCircleStrokeAlpha() < 255) {
                                strokeColor = ColorTemplate.getColorWithAlphaComponent(strokeColor, set.getHighlightCircleStrokeAlpha());
                            }
                            drawHighlightCircle(c, pOut, set.getHighlightCircleInnerRadius(), set.getHighlightCircleOuterRadius(), set.getHighlightCircleFillColor(), strokeColor, set.getHighlightCircleStrokeWidth());
                        }
                    }
                }
                i = i2 + 1;
            } else {
                MPPointF.recycleInstance(center);
                MPPointF.recycleInstance(pOut);
                return;
            }
        }
    }

    public void drawHighlightCircle(Canvas c, MPPointF point, float innerRadius, float outerRadius, int fillColor, int strokeColor, float strokeWidth) {
        c.save();
        float outerRadius2 = Utils.convertDpToPixel(outerRadius);
        float innerRadius2 = Utils.convertDpToPixel(innerRadius);
        if (fillColor != 1122867) {
            Path p = this.mDrawHighlightCirclePathBuffer;
            p.reset();
            p.addCircle(point.f72x, point.f73y, outerRadius2, Direction.CW);
            if (innerRadius2 > 0.0f) {
                p.addCircle(point.f72x, point.f73y, innerRadius2, Direction.CCW);
            }
            this.mHighlightCirclePaint.setColor(fillColor);
            this.mHighlightCirclePaint.setStyle(Style.FILL);
            c.drawPath(p, this.mHighlightCirclePaint);
        }
        if (strokeColor != 1122867) {
            this.mHighlightCirclePaint.setColor(strokeColor);
            this.mHighlightCirclePaint.setStyle(Style.STROKE);
            this.mHighlightCirclePaint.setStrokeWidth(Utils.convertDpToPixel(strokeWidth));
            c.drawCircle(point.f72x, point.f73y, outerRadius2, this.mHighlightCirclePaint);
        }
        c.restore();
    }
}
