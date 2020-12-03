package ro.etrcpo.htcalculator.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PlayerSkillContribution {

    private Player player;

    private Position position;

    private String centralOrSideFlag;

    private List<CalculatedContribution> calculatedContributionList;
}
