package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendDirection;
import com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment;
import com.github.mikephil.charting.components.Legend.LegendOrientation;
import com.github.mikephil.charting.components.Legend.LegendVerticalAlignment;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LegendRenderer extends Renderer {
    protected ArrayList<Integer> colorsForComputeLegend = new ArrayList<>(16);
    protected FontMetrics fontMetricsForRenderLegent = new FontMetrics();
    protected ArrayList<String> labelsForComputeLegend = new ArrayList<>(16);
    protected Legend mLegend;
    protected Paint mLegendFormPaint;
    protected Paint mLegendLabelPaint;

    public LegendRenderer(ViewPortHandler viewPortHandler, Legend legend) {
        super(viewPortHandler);
        this.mLegend = legend;
        this.mLegendLabelPaint = new Paint(1);
        this.mLegendLabelPaint.setTextSize(Utils.convertDpToPixel(9.0f));
        this.mLegendLabelPaint.setTextAlign(Align.LEFT);
        this.mLegendFormPaint = new Paint(1);
        this.mLegendFormPaint.setStyle(Style.FILL);
        this.mLegendFormPaint.setStrokeWidth(3.0f);
    }

    public Paint getLabelPaint() {
        return this.mLegendLabelPaint;
    }

    public Paint getFormPaint() {
        return this.mLegendFormPaint;
    }

    public void computeLegend(ChartData<?> data) {
        if (!this.mLegend.isLegendCustom()) {
            ArrayList<String> labels = this.labelsForComputeLegend;
            ArrayList<Integer> colors = this.colorsForComputeLegend;
            labels.clear();
            colors.clear();
            for (int i = 0; i < data.getDataSetCount(); i++) {
                IDataSet dataSet = data.getDataSetByIndex(i);
                List<Integer> clrs = dataSet.getColors();
                int entryCount = dataSet.getEntryCount();
                if ((dataSet instanceof IBarDataSet) && ((IBarDataSet) dataSet).isStacked()) {
                    IBarDataSet bds = (IBarDataSet) dataSet;
                    String[] sLabels = bds.getStackLabels();
                    int j = 0;
                    while (j < clrs.size() && j < bds.getStackSize()) {
                        labels.add(sLabels[j % sLabels.length]);
                        colors.add(clrs.get(j));
                        j++;
                    }
                    if (bds.getLabel() != null) {
                        colors.add(Integer.valueOf(ColorTemplate.COLOR_SKIP));
                        labels.add(bds.getLabel());
                    }
                } else if (dataSet instanceof IPieDataSet) {
                    IPieDataSet pds = (IPieDataSet) dataSet;
                    int j2 = 0;
                    while (j2 < clrs.size() && j2 < entryCount) {
                        labels.add(((PieEntry) pds.getEntryForIndex(j2)).getLabel());
                        colors.add(clrs.get(j2));
                        j2++;
                    }
                    if (pds.getLabel() != null) {
                        colors.add(Integer.valueOf(ColorTemplate.COLOR_SKIP));
                        labels.add(pds.getLabel());
                    }
                } else if (!(dataSet instanceof ICandleDataSet) || ((ICandleDataSet) dataSet).getDecreasingColor() == 1122867) {
                    int j3 = 0;
                    while (j3 < clrs.size() && j3 < entryCount) {
                        if (j3 >= clrs.size() - 1 || j3 >= entryCount - 1) {
                            labels.add(data.getDataSetByIndex(i).getLabel());
                        } else {
                            labels.add(null);
                        }
                        colors.add(clrs.get(j3));
                        j3++;
                    }
                } else {
                    colors.add(Integer.valueOf(((ICandleDataSet) dataSet).getDecreasingColor()));
                    colors.add(Integer.valueOf(((ICandleDataSet) dataSet).getIncreasingColor()));
                    labels.add(null);
                    labels.add(dataSet.getLabel());
                }
            }
            if (!(this.mLegend.getExtraColors() == null || this.mLegend.getExtraLabels() == null)) {
                int[] extraColors = this.mLegend.getExtraColors();
                int length = extraColors.length;
                for (int i2 = 0; i2 < length; i2++) {
                    colors.add(Integer.valueOf(extraColors[i2]));
                }
                Collections.addAll(labels, this.mLegend.getExtraLabels());
            }
            this.mLegend.setComputedColors(colors);
            this.mLegend.setComputedLabels(labels);
        }
        Typeface tf = this.mLegend.getTypeface();
        if (tf != null) {
            this.mLegendLabelPaint.setTypeface(tf);
        }
        this.mLegendLabelPaint.setTextSize(this.mLegend.getTextSize());
        this.mLegendLabelPaint.setColor(this.mLegend.getTextColor());
        this.mLegend.calculateDimensions(this.mLegendLabelPaint, this.mViewPortHandler);
    }

    public void renderLegend(Canvas c) {
        float originPosX;
        double d;
        float originPosX2;
        float posY;
        float posY2;
        float f;
        float f2;
        if (this.mLegend.isEnabled()) {
            Typeface tf = this.mLegend.getTypeface();
            if (tf != null) {
                this.mLegendLabelPaint.setTypeface(tf);
            }
            this.mLegendLabelPaint.setTextSize(this.mLegend.getTextSize());
            this.mLegendLabelPaint.setColor(this.mLegend.getTextColor());
            float labelLineHeight = Utils.getLineHeight(this.mLegendLabelPaint, this.fontMetricsForRenderLegent);
            float labelLineSpacing = Utils.getLineSpacing(this.mLegendLabelPaint, this.fontMetricsForRenderLegent) + this.mLegend.getYEntrySpace();
            float formYOffset = labelLineHeight - (((float) Utils.calcTextHeight(this.mLegendLabelPaint, "ABC")) / 2.0f);
            String[] labels = this.mLegend.getLabels();
            int[] colors = this.mLegend.getColors();
            float formToTextSpace = this.mLegend.getFormToTextSpace();
            float xEntrySpace = this.mLegend.getXEntrySpace();
            LegendOrientation orientation = this.mLegend.getOrientation();
            LegendHorizontalAlignment horizontalAlignment = this.mLegend.getHorizontalAlignment();
            LegendVerticalAlignment verticalAlignment = this.mLegend.getVerticalAlignment();
            LegendDirection direction = this.mLegend.getDirection();
            float formSize = this.mLegend.getFormSize();
            float stackSpace = this.mLegend.getStackSpace();
            float yoffset = this.mLegend.getYOffset();
            float xoffset = this.mLegend.getXOffset();
            float originPosX3 = 0.0f;
            switch (horizontalAlignment) {
                case LEFT:
                    if (orientation == LegendOrientation.VERTICAL) {
                        originPosX3 = xoffset;
                    } else {
                        originPosX3 = this.mViewPortHandler.contentLeft() + xoffset;
                    }
                    if (direction == LegendDirection.RIGHT_TO_LEFT) {
                        originPosX3 += this.mLegend.mNeededWidth;
                        break;
                    }
                    break;
                case RIGHT:
                    if (orientation == LegendOrientation.VERTICAL) {
                        originPosX2 = this.mViewPortHandler.getChartWidth() - xoffset;
                    } else {
                        originPosX2 = this.mViewPortHandler.contentRight() - xoffset;
                    }
                    if (direction == LegendDirection.LEFT_TO_RIGHT) {
                        originPosX3 -= this.mLegend.mNeededWidth;
                        break;
                    }
                    break;
                case CENTER:
                    if (orientation == LegendOrientation.VERTICAL) {
                        originPosX = this.mViewPortHandler.getChartWidth() / 2.0f;
                    } else {
                        originPosX = this.mViewPortHandler.contentLeft() + (this.mViewPortHandler.contentWidth() / 2.0f);
                    }
                    originPosX3 = originPosX + (direction == LegendDirection.LEFT_TO_RIGHT ? xoffset : -xoffset);
                    if (orientation == LegendOrientation.VERTICAL) {
                        double d2 = (double) originPosX3;
                        if (direction == LegendDirection.LEFT_TO_RIGHT) {
                            d = (((double) (-this.mLegend.mNeededWidth)) / 2.0d) + ((double) xoffset);
                        } else {
                            d = (((double) this.mLegend.mNeededWidth) / 2.0d) - ((double) xoffset);
                        }
                        originPosX3 = (float) (d + d2);
                        break;
                    }
                    break;
            }
            switch (orientation) {
                case HORIZONTAL:
                    FSize[] calculatedLineSizes = this.mLegend.getCalculatedLineSizes();
                    FSize[] calculatedLabelSizes = this.mLegend.getCalculatedLabelSizes();
                    Boolean[] calculatedLabelBreakPoints = this.mLegend.getCalculatedLabelBreakPoints();
                    float posX = originPosX3;
                    float posY3 = 0.0f;
                    switch (verticalAlignment) {
                        case TOP:
                            posY3 = yoffset;
                            break;
                        case BOTTOM:
                            posY3 = (this.mViewPortHandler.getChartHeight() - yoffset) - this.mLegend.mNeededHeight;
                            break;
                        case CENTER:
                            posY3 = ((this.mViewPortHandler.getChartHeight() - this.mLegend.mNeededHeight) / 2.0f) + yoffset;
                            break;
                    }
                    int lineIndex = 0;
                    int count = labels.length;
                    for (int i = 0; i < count; i++) {
                        if (i < calculatedLabelBreakPoints.length && calculatedLabelBreakPoints[i].booleanValue()) {
                            posX = originPosX3;
                            posY3 += labelLineHeight + labelLineSpacing;
                        }
                        if (posX == originPosX3 && horizontalAlignment == LegendHorizontalAlignment.CENTER && lineIndex < calculatedLineSizes.length) {
                            posX += (direction == LegendDirection.RIGHT_TO_LEFT ? calculatedLineSizes[lineIndex].width : -calculatedLineSizes[lineIndex].width) / 2.0f;
                            lineIndex++;
                        }
                        boolean drawingForm = colors[i] != 1122868;
                        boolean isStacked = labels[i] == null;
                        if (drawingForm) {
                            if (direction == LegendDirection.RIGHT_TO_LEFT) {
                                posX -= formSize;
                            }
                            drawForm(c, posX, posY3 + formYOffset, i, this.mLegend);
                            if (direction == LegendDirection.LEFT_TO_RIGHT) {
                                posX += formSize;
                            }
                        }
                        if (!isStacked) {
                            if (drawingForm) {
                                if (direction == LegendDirection.RIGHT_TO_LEFT) {
                                    f2 = -formToTextSpace;
                                } else {
                                    f2 = formToTextSpace;
                                }
                                posX += f2;
                            }
                            if (direction == LegendDirection.RIGHT_TO_LEFT) {
                                posX -= calculatedLabelSizes[i].width;
                            }
                            drawLabel(c, posX, posY3 + labelLineHeight, labels[i]);
                            if (direction == LegendDirection.LEFT_TO_RIGHT) {
                                posX += calculatedLabelSizes[i].width;
                            }
                            f = direction == LegendDirection.RIGHT_TO_LEFT ? -xEntrySpace : xEntrySpace;
                        } else {
                            f = direction == LegendDirection.RIGHT_TO_LEFT ? -stackSpace : stackSpace;
                        }
                        posX += f;
                    }
                    return;
                case VERTICAL:
                    float stack = 0.0f;
                    boolean wasStacked = false;
                    float posY4 = 0.0f;
                    switch (verticalAlignment) {
                        case TOP:
                            if (horizontalAlignment == LegendHorizontalAlignment.CENTER) {
                                posY2 = 0.0f;
                            } else {
                                posY2 = this.mViewPortHandler.contentTop();
                            }
                            posY4 = posY2 + yoffset;
                            break;
                        case BOTTOM:
                            if (horizontalAlignment == LegendHorizontalAlignment.CENTER) {
                                posY = this.mViewPortHandler.getChartHeight();
                            } else {
                                posY = this.mViewPortHandler.contentBottom();
                            }
                            posY4 = posY - (this.mLegend.mNeededHeight + yoffset);
                            break;
                        case CENTER:
                            posY4 = ((this.mViewPortHandler.getChartHeight() / 2.0f) - (this.mLegend.mNeededHeight / 2.0f)) + this.mLegend.getYOffset();
                            break;
                    }
                    for (int i2 = 0; i2 < labels.length; i2++) {
                        Boolean drawingForm2 = Boolean.valueOf(colors[i2] != 1122868);
                        float posX2 = originPosX3;
                        if (drawingForm2.booleanValue()) {
                            if (direction == LegendDirection.LEFT_TO_RIGHT) {
                                posX2 += stack;
                            } else {
                                posX2 -= formSize - stack;
                            }
                            drawForm(c, posX2, posY4 + formYOffset, i2, this.mLegend);
                            if (direction == LegendDirection.LEFT_TO_RIGHT) {
                                posX2 += formSize;
                            }
                        }
                        if (labels[i2] != null) {
                            if (drawingForm2.booleanValue() && !wasStacked) {
                                posX2 += direction == LegendDirection.LEFT_TO_RIGHT ? formToTextSpace : -formToTextSpace;
                            } else if (wasStacked) {
                                posX2 = originPosX3;
                            }
                            if (direction == LegendDirection.RIGHT_TO_LEFT) {
                                posX2 -= (float) Utils.calcTextWidth(this.mLegendLabelPaint, labels[i2]);
                            }
                            if (!wasStacked) {
                                drawLabel(c, posX2, posY4 + labelLineHeight, labels[i2]);
                            } else {
                                posY4 += labelLineHeight + labelLineSpacing;
                                drawLabel(c, posX2, posY4 + labelLineHeight, labels[i2]);
                            }
                            posY4 += labelLineHeight + labelLineSpacing;
                            stack = 0.0f;
                        } else {
                            stack += formSize + stackSpace;
                            wasStacked = true;
                        }
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void drawForm(Canvas c, float x, float y, int index, Legend legend) {
        if (legend.getColors()[index] != 1122868) {
            this.mLegendFormPaint.setColor(legend.getColors()[index]);
            float formsize = legend.getFormSize();
            float half = formsize / 2.0f;
            switch (legend.getForm()) {
                case CIRCLE:
                    c.drawCircle(x + half, y, half, this.mLegendFormPaint);
                    return;
                case SQUARE:
                    c.drawRect(x, y - half, x + formsize, y + half, this.mLegendFormPaint);
                    return;
                case LINE:
                    c.drawLine(x, y, x + formsize, y, this.mLegendFormPaint);
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void drawLabel(Canvas c, float x, float y, String label) {
        c.drawText(label, x, y, this.mLegendLabelPaint);
    }
}
