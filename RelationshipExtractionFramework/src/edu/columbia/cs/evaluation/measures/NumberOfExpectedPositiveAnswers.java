package edu.columbia.cs.evaluation.measures;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import edu.columbia.cs.cg.relations.RelationshipType;
import edu.columbia.cs.model.Model.PredictionProperties;
import edu.columbia.cs.og.structure.OperableStructure;

public class NumberOfExpectedPositiveAnswers implements Measure {

	@Override
	public double getValue(Map<OperableStructure, Set<String>> labels,
			Map<OperableStructure, Map<PredictionProperties, Object>> properties) {
		int result = 0;
		
		for(Entry<OperableStructure,Set<String>> entry : labels.entrySet()){
			Set<String> realLabel = entry.getKey().getLabels();
			result+=realLabel.size();
		}
		
		return result;
	}

}
