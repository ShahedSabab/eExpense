package com.github.mikephil.charting.renderer.scatter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import com.github.mikephil.charting.buffer.ScatterBuffer;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class SquareShapeRenderer implements ShapeRenderer {
    public void renderShape(Canvas c, IScatterDataSet dataSet, ViewPortHandler viewPortHandler, ScatterBuffer buffer, Paint renderPaint, float shapeSize) {
        float shapeHalf = shapeSize / 2.0f;
        float shapeHoleSizeHalf = Utils.convertDpToPixel(dataSet.getScatterShapeHoleRadius());
        float shapeStrokeSize = (shapeSize - (shapeHoleSizeHalf * 2.0f)) / 2.0f;
        float shapeStrokeSizeHalf = shapeStrokeSize / 2.0f;
        int shapeHoleColor = dataSet.getScatterShapeHoleColor();
        int i = 0;
        while (i < buffer.size()) {
            if (viewPortHandler.isInBoundsRight(buffer.buffer[i])) {
                if (viewPortHandler.isInBoundsLeft(buffer.buffer[i])) {
                    if (viewPortHandler.isInBoundsY(buffer.buffer[i + 1])) {
                        renderPaint.setColor(dataSet.getColor(i / 2));
                        if (((double) shapeSize) > 0.0d) {
                            renderPaint.setStyle(Style.STROKE);
                            renderPaint.setStrokeWidth(shapeStrokeSize);
                            c.drawRect((buffer.buffer[i] - shapeHoleSizeHalf) - shapeStrokeSizeHalf, (buffer.buffer[i + 1] - shapeHoleSizeHalf) - shapeStrokeSizeHalf, buffer.buffer[i] + shapeHoleSizeHalf + shapeStrokeSizeHalf, buffer.buffer[i + 1] + shapeHoleSizeHalf + shapeStrokeSizeHalf, renderPaint);
                            if (shapeHoleColor != 1122867) {
                                renderPaint.setStyle(Style.FILL);
                                renderPaint.setColor(shapeHoleColor);
                                c.drawRect(buffer.buffer[i] - shapeHoleSizeHalf, buffer.buffer[i + 1] - shapeHoleSizeHalf, buffer.buffer[i] + shapeHoleSizeHalf, buffer.buffer[i + 1] + shapeHoleSizeHalf, renderPaint);
                            }
                        } else {
                            renderPaint.setStyle(Style.FILL);
                            c.drawRect(buffer.buffer[i] - shapeHalf, buffer.buffer[i + 1] - shapeHalf, buffer.buffer[i] + shapeHalf, buffer.buffer[i + 1] + shapeHalf, renderPaint);
                        }
                    }
                }
                i += 2;
            } else {
                return;
            }
        }
    }
}
