package ru.learnhub.commondto;

import java.util.HashMap;
import java.util.Map;

// Этот момент я захардкодил, ибо уже не успеваю сделать нормально через БД
public enum TariffType {
    UNLIMITED("06", 1.0),
    PER_MINUTE("03", 1.5),
    REGULAR("11", 0.5);

    // Хэш-таблица для быстрого получения объекта enum по значению поля index
    private static Map<String, TariffType> tariffMap = new HashMap<>();

    static {
        for (TariffType tariffType : values()) {
            tariffMap.put(tariffType.tariffId, tariffType);
        }
    }

    private final String tariffId;
    private final double costPerMinute;

    TariffType(String tariffId, double costPerMinute) {
        this.tariffId = tariffId;
        this.costPerMinute = costPerMinute;
    }

    public static TariffType getInstanceById(String tariffId) {
        if (tariffMap.containsKey(tariffId)) {
            return tariffMap.get(tariffId);
        }
        return null;
    }

    public double getCostPerMinute() {
        return costPerMinute;
    }
    public double applyTariff(int minutes, String callType) {
        if (this == UNLIMITED) {
            return Math.max(minutes - 300 + 100, 100);
        } else if (this == PER_MINUTE) {
            return minutes * costPerMinute;
        } else {
            if (callType.equals("02"))
            {
                return 0;
            }
            return minutes - 100 <= 0 ?
                    minutes * costPerMinute :
                    100 * costPerMinute + (minutes - 100) * costPerMinute;
        }
    }

    @Override
    public String toString() {
        if (this == UNLIMITED) {
            return "06";
        } else if (this == PER_MINUTE) {
            return "03";
        } else {
            return "11";
        }
    }
}
