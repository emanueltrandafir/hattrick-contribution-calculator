package ro.etrcpo.htcalculator.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PositionSide {
    Central("C"),
    Left("L"),
    Right("R");

    public final String positionSide;

    PositionSide(String positionSide) {
        this.positionSide = positionSide;
    }

    public String getValue() {
        return this.positionSide;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(positionSide);
    }
}