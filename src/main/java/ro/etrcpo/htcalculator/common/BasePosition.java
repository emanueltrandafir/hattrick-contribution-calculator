package ro.etrcpo.htcalculator.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BasePosition {
    Goalkeeper("GK"),
    CentralDefender("CD"),
    WingBack("WB"),
    InnerMidfield("IM"),
    Winger("W"),
    Forward("F");

    private final String basePosition;

    BasePosition(String basePosition) {
        this.basePosition = basePosition;
    }

    public String getValue() {
        return this.basePosition;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(basePosition);
    }
}