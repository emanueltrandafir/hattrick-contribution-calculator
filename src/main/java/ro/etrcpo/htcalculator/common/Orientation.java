package ro.etrcpo.htcalculator.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Orientation {
    Normal(""),
    Offensive("o"),
    Defensive("d"),
    TowardsMiddle("tm"),
    TowardsWing("tw");

    public final String orientation;

    Orientation(String orientation) {
        this.orientation = orientation;
    }

    public String getValue() {
        return this.orientation;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(orientation);
    }
}