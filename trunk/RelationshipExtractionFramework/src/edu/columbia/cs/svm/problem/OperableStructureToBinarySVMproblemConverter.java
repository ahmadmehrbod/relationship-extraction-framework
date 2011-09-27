package edu.columbia.cs.svm.problem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.berkeley.compbio.jlibsvm.binary.BinaryClassificationProblemImpl;
import edu.berkeley.compbio.jlibsvm.scaler.NoopScalingModel;
import edu.columbia.cs.cg.relations.RelationshipType;
import edu.columbia.cs.og.structure.OperableStructure;

public class OperableStructureToBinarySVMproblemConverter {

	public static BinaryClassificationProblemImpl<String, OperableStructure> convert(
			List<OperableStructure> train, RelationshipType relLabel) {
		Map<OperableStructure,String> examples = new HashMap<OperableStructure,String>();
		Map<OperableStructure,Integer> exampleIds = new HashMap<OperableStructure,Integer>();
		
		Map<String,Integer> numDocs = new HashMap<String,Integer>();
		
		int i=0;
		for(OperableStructure sent : train){
			String label;
			Set<String> trueLabels = sent.getLabels();
			if(trueLabels.contains(relLabel.getType())){
				label=relLabel.getType();
			}else{
				label=RelationshipType.NOT_A_RELATIONSHIP;
			}
			examples.put(sent, label);
			exampleIds.put(sent, i);
			
			Integer res = numDocs.get(label);
			if(res==null){
				res=0;
			}
			numDocs.put(label, res+1);
			
			i++;
		}
		
		System.out.println(numDocs);
		
		BinaryClassificationProblemImpl<String,OperableStructure> result = 
			new BinaryClassificationProblemImpl<String,OperableStructure>(String.class, 
					examples, exampleIds, new NoopScalingModel<OperableStructure>(),
					relLabel.getType(),RelationshipType.NOT_A_RELATIONSHIP);
		
		return result;
	}

}
