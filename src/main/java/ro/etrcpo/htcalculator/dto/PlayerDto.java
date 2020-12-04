package ro.etrcpo.htcalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PlayerDto {
    private Long id;

    private String name;

    private Map<String, AbilityDto> skills;
}
