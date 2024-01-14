package org.stupidstick.statistic.collectors;


import lombok.Getter;

@Getter
public class StatisticCollector<T> {
    long elementsCount = 0;

    public void add(T element) {
        elementsCount++;
    }

    public String statistic() {
        return "Elements count: " + elementsCount + "; ";
    }

}
