package ro.etrcpo.htcalculator.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CalculatedContribution {
	
	private Map<TeamPart, Double> calculatedContribution = new HashMap<>();
	
}
