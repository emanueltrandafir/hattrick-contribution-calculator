package ro.etrcpo.htcalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.etrcpo.htcalculator.common.BasePosition;
import ro.etrcpo.htcalculator.common.Orientation;
import ro.etrcpo.htcalculator.common.PositionSide;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PositionDto {

    private BasePosition basePosition;

    private PositionSide positionSide;

    private Orientation orientation;

}

