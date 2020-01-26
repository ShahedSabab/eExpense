package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import java.util.List;

public class ScatterData extends BarLineScatterCandleBubbleData<IScatterDataSet> {
    public ScatterData() {
    }

    public ScatterData(List<IScatterDataSet> dataSets) {
        super(dataSets);
    }

    public ScatterData(IScatterDataSet... dataSets) {
        super((T[]) dataSets);
    }

    public float getGreatestShapeSize() {
        float max = 0.0f;
        for (int i = 0; i < this.mDataSets.size(); i++) {
            float size = ((IScatterDataSet) this.mDataSets.get(i)).getScatterShapeSize();
            if (size > max) {
                max = size;
            }
        }
        return max;
    }
}
