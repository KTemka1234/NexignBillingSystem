package ru.learnhub.commondto.dto;


import java.util.HashMap;
import java.util.Map;

public enum CallType {

    INCOMING("02"),
    OUTGOING("01");

    // Хэш-таблица для быстрого получения объекта enum по значению поля index
    private static Map<String, CallType> STRING_MAP = new HashMap<>();

    static {
        for (CallType callType : values()) {
            STRING_MAP.put(callType.index, callType);
        }
    }

    private final String index;

    CallType(String index) {
        this.index = index;
    }

    public String getIndex() {
        return index;
    }

    public static CallType getInstanceByIndex(String index) {
        if (STRING_MAP.containsKey(index)) {
            return STRING_MAP.get(index);
        }
        return null;
    }
}
