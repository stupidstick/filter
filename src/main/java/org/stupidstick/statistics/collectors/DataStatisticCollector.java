package org.stupidstick.statistics.collectors;

import lombok.*;
import org.stupidstick.data.DataType;
import org.stupidstick.data.Pair;
import org.stupidstick.statistics.StatisticMode;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.EnumMap;
import java.util.Map;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class DataStatisticCollector {
    private final EnumMap<DataType, StatisticCollector<?>> collectors;
    @Getter
    private final StatisticMode mode;

    @SuppressWarnings("unchecked")
    public void add(@NonNull Pair<DataType, Object> data) {
        switch (data.key()) {
            case INTEGER -> ((StatisticCollector<BigInteger>) collectors.get(DataType.INTEGER))
                    .add((BigInteger) data.val());
            case DECIMAL -> ((StatisticCollector<BigDecimal>) collectors.get(DataType.DECIMAL))
                    .add((BigDecimal) data.val());
            case STRING -> ((StatisticCollector<String>) collectors.get(DataType.STRING))
                    .add((String) data.val());
        }
    }

    public static DataStatisticCollector createShortMode() {
        return new DataStatisticCollector(
                new EnumMap<>(Map.of(
                        DataType.INTEGER, new StatisticCollector<BigInteger>(),
                        DataType.DECIMAL, new StatisticCollector<BigDecimal>(),
                        DataType.STRING, new StatisticCollector<String>()
                )),
                StatisticMode.SHORT
        );
    }

    public static DataStatisticCollector createFullMode() {
        return new DataStatisticCollector(
                new EnumMap<>(Map.of(
                        DataType.INTEGER, new IntegerFullStatisticCollector(),
                        DataType.DECIMAL, new DecimalFullStatisticCollector(),
                        DataType.STRING, new StringFullStatisticCollector()
                )),
                StatisticMode.FULL
        );
    }

    public static DataStatisticCollector valueOf(StatisticMode mode) {
        return switch (mode) {
            case SHORT -> createShortMode();
            case FULL -> createFullMode();
        };
    }

    public EnumMap<DataType, StatisticCollector<?>> getCollectors() {
        return new EnumMap<>(collectors);
    }

}
