package org.stupidstick.statistic.collectors;

import lombok.Getter;

@Getter
public class StringFullStatisticCollector extends StatisticCollector<String> {
    private Integer minLength = null;
    private Integer maxLength = null;

    @Override
    public void add(String element) {
        super.add(element);
        if (minLength == null || element.length() < minLength) minLength = element.length();
        if (maxLength == null || element.length() > maxLength) maxLength = element.length();
    }

    @Override
    public String statistic() {
        StringBuilder statisticBuilder = new StringBuilder(super.statistic());
        if (minLength != null) statisticBuilder.append("Min length: ").append(minLength).append("\n");
        if (maxLength != null) statisticBuilder.append("Max length: ").append(maxLength).append("\n");
        return statisticBuilder.toString();
    }
}
