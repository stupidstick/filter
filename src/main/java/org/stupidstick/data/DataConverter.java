package org.stupidstick.data;

import java.math.BigDecimal;
import java.math.BigInteger;

public class DataConverter {
    public static Pair<DataType, Object> convert(String data) {
        try {
            return new Pair<>(DataType.INTEGER, new BigInteger(data));
        } catch (NumberFormatException ignored) {
        }

        try {
            return new Pair<>(DataType.DECIMAL, new BigDecimal(data));
        } catch (NumberFormatException ignored) {
        }

        return new Pair<>(DataType.STRING, data);
    }
}
