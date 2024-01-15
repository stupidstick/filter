package org.stupidstick.statistic.collectors;

import lombok.Getter;
import org.stupidstick.configuration.StatisticConfiguration;

import java.math.BigDecimal;

@Getter
public class DecimalFullStatisticCollector extends NumberFullStatisticCollector<BigDecimal>{
    private BigDecimal sumVal = BigDecimal.ZERO;

    @Override
    public void add(BigDecimal element) {
        super.add(element);
        sumVal = sumVal.add(element);
    }

    public BigDecimal getAvg() {
        if (getElementsCount() == 0) return null;
        return sumVal.divide(new BigDecimal(getElementsCount()), StatisticConfiguration.AVG_ROUND_SCALE,
                StatisticConfiguration.AVG_ROUNDING_MODE);
    }

    @Override
    public String statistic() {
        StringBuilder builder = new StringBuilder(super.statistic());
        builder.append("Sum: ").append(sumVal).append("\n");
        if (getAvg() != null) builder.append("Avg: ").append(getAvg().stripTrailingZeros()).append("\n");
        return builder.toString();
    }
}