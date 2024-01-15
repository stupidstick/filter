package org.stupidstick.statistic.collectors;

import lombok.Getter;

@Getter
public class NumberFullStatisticCollector<T extends Number & Comparable<T>> extends StatisticCollector<T> {
    private T minVal;
    private T maxVal;

    @Override
    public void add(T element) {
        super.add(element);
        if (minVal == null || element.compareTo(minVal) < 0) minVal = element;
        if (maxVal == null || element.compareTo(maxVal) > 0) maxVal = element;
    }

    @Override
    public String statistic() {
        StringBuilder builder = new StringBuilder(super.statistic());
        if (getMinVal() != null) builder.append("Min: ").append(getMinVal()).append("\n");
        if (getMaxVal() != null) builder.append("Max: ").append(maxVal).append("\n");
        return builder.toString();
    }
}
