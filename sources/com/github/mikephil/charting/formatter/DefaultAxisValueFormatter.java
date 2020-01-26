package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.FormattedStringCache.PrimFloat;
import java.text.DecimalFormat;

public class DefaultAxisValueFormatter implements AxisValueFormatter {
    protected int digits = 0;
    protected PrimFloat mFormattedStringCache;

    public DefaultAxisValueFormatter(int digits2) {
        this.digits = digits2;
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < digits2; i++) {
            if (i == 0) {
                b.append(".");
            }
            b.append("0");
        }
        this.mFormattedStringCache = new PrimFloat(new DecimalFormat("###,###,###,##0" + b.toString()));
    }

    public String getFormattedValue(float value, AxisBase axis) {
        return this.mFormattedStringCache.getFormattedValue(value);
    }

    public int getDecimalDigits() {
        return this.digits;
    }
}
